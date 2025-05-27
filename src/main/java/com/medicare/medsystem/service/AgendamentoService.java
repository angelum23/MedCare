package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.Agendamento;
import com.medicare.medsystem.domain.Dto.InserirAgendamentoDto;
import com.medicare.medsystem.domain.Dto.ListarAgendamentoDto;
import com.medicare.medsystem.domain.Enum.EnumTipoAgendamento;
import com.medicare.medsystem.repository.IAgendamentoRepository;
import com.medicare.medsystem.repository.IGradeHorarioRepository;
import com.medicare.medsystem.service.base.BaseService;
import com.medicare.medsystem.service.base.ICacheListService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService extends BaseService<Agendamento> {
    @Autowired
    private IAgendamentoRepository repository;
    @Autowired
    private IGradeHorarioRepository gradeHorarioRepository;
    @Autowired
    private DocumentoService documentoService;
    @Autowired ICacheListService<Agendamento> cache;

    private static final String AGENDAMENTO_DIARIO_CACHE_KEY = "agendaDia";

    @Override
    protected JpaRepository<Agendamento, Integer> getRepository() {
        return repository;
    }

    @Override
    public Integer salvar(Agendamento agendamento) throws Exception {
        //validarSeExisteGradeNoHorario(agendamento);
        //validarHorarioNaoAgendado(agendamento);

        salvarCache(agendamento);

        return super.salvar(agendamento);
    }

    @Override
    public void remover(Integer id) throws Exception {
        super.remover(id);
        cache.clearList(AGENDAMENTO_DIARIO_CACHE_KEY);
    }

    private void salvarCache(Agendamento agendamento) {
        var agendamentosCacheados = cache.getList(AGENDAMENTO_DIARIO_CACHE_KEY);

        if (agendamentosCacheados.isEmpty()) {
            cache.rightPush(AGENDAMENTO_DIARIO_CACHE_KEY, agendamento);
            return;
        }

        if (agendamento.getId() > 0) {
            cache.clearList(AGENDAMENTO_DIARIO_CACHE_KEY);
            return;
        }
        var diaCache = agendamentosCacheados.get(0).getHoraInicio().getDate();
        var diaNovoAgendamento = agendamento.getHoraInicio().getDate();

        if (diaCache != diaNovoAgendamento) {
            cache.clearList(AGENDAMENTO_DIARIO_CACHE_KEY);
        }

        cache.rightPush(AGENDAMENTO_DIARIO_CACHE_KEY, agendamento);
    }

    private void validarHorarioNaoAgendado(Agendamento agendamento) {
        var agendamentos = repository.findAllByHoraInicioGreaterThanEqualAndHoraFimLessThanEqualAndRemovidoIsFalse(
            agendamento.getHoraInicio(),
            agendamento.getHoraFim()
        );

        if (!agendamentos.isEmpty()) {
            throw new IllegalArgumentException("Este horário já está agendado, por favor escolha outro");
        }
    }

    private void validarSeExisteGradeNoHorario(Agendamento agendamento) {
        var gradeHorarios = gradeHorarioRepository.findAllByInicioExpedienteGreaterThanEqualAndFimExpedienteLessThanEqualAndRemovidoIsFalse(
            agendamento.getHoraInicio(),
            agendamento.getHoraFim()
        );

        if (gradeHorarios.isEmpty()) {
            throw new IllegalArgumentException("Nenhum horário disponível para o agendamento");
        }
    }

    public List<Agendamento> recuperarPorDia(Date data) {
        Date dataInicio = DateUtils.truncate(data, Calendar.DATE);
        var dataFim = DateUtils.addDays(dataInicio, 1);

        return repository.findAllByHoraInicioGreaterThanEqualAndHoraFimLessThanEqualAndRemovidoIsFalse(dataInicio, dataFim);
    }

    public List<Agendamento> recuperarHoje() {
        var agendamentosCacheados = cache.getList(AGENDAMENTO_DIARIO_CACHE_KEY);
        if (agendamentosCacheados != null) {
            return agendamentosCacheados;
        }

        return recuperarPorDia(new Date());
    }

    public void folgar(Date data) throws Exception {
        var agendamentosDoDia = recuperarPorDia(data);
        agendamentosDoDia.forEach(agendamento -> agendamento.setRemovido(true));
        repository.saveAll(agendamentosDoDia);

        var dataInicio = DateUtils.truncate(data, Calendar.DATE);
        var dataFim = DateUtils.addDays(data, 1);

        var registroDeFolga = new Agendamento();
        registroDeFolga.setTipo(EnumTipoAgendamento.Folga);
        registroDeFolga.setHoraInicio(dataInicio);
        registroDeFolga.setHoraFim(dataFim);

        salvar(registroDeFolga);
    }

    public Integer salvarComDocumento(InserirAgendamentoDto dadosAgendamento) throws Exception {
        if (dadosAgendamento.documento().isPresent()) {
            documentoService.salvar(dadosAgendamento.documento().get());
        }

        return salvar(dadosAgendamento.agendamento());
    }

    public List<Agendamento> listar(ListarAgendamentoDto filtros) throws Exception {
        exceptionSeNull(filtros.getTipo(), Optional.of("Tipo de agendamento não informado!"));

        var agendamentosFiltrados = repository.findAllByTipoEqualsAndRemovidoIsFalse(filtros.getTipo());

        return super.listar(filtros, Optional.ofNullable(agendamentosFiltrados));
    }
}
