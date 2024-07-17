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

    private final String AgendamentoDiarioCacheKey = "agendaDia";

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
        cache.clearList(AgendamentoDiarioCacheKey);
    }

    private void salvarCache(Agendamento agendamento) {
        var registrosCache = cache.getList(AgendamentoDiarioCacheKey);

        if(registrosCache.isEmpty()) {
            cache.rightPush(AgendamentoDiarioCacheKey, agendamento);
            return;
        }

        if(agendamento.getId() > 0) {
            cache.clearList(AgendamentoDiarioCacheKey);
            return;
        }

        var diaInicioCache = registrosCache.get(0).getHoraInicio().getDate();
        var diaInicioAgendamento = agendamento.getHoraInicio().getDate();

        if(diaInicioCache != diaInicioAgendamento) {
            cache.clearList(AgendamentoDiarioCacheKey);
        }

        cache.rightPush(AgendamentoDiarioCacheKey, agendamento);
    }

    private void validarHorarioNaoAgendado(Agendamento agendamento) {
        var agendamentos = repository.findAllByHoraInicioGreaterThanEqualAndHoraFimLessThanEqualAndRemovidoIsFalse(
            agendamento.getHoraInicio(),
            agendamento.getHoraFim()
        );

        if(!agendamentos.isEmpty()) {
            throw new IllegalArgumentException("Este horário já está agendado, por favor escolha outro");
        }
    }

    private void validarSeExisteGradeNoHorario(Agendamento agendamento) {
        var gradeHorarios = gradeHorarioRepository.findAllByInicioExpedienteGreaterThanEqualAndFimExpedienteLessThanEqualAndRemovidoIsFalse(
            agendamento.getHoraInicio(),
            agendamento.getHoraFim()
        );

        if(gradeHorarios.isEmpty()) {
            throw new IllegalArgumentException("Nenhum horário disponível para o agendamento");
        }
    }

    public List<Agendamento> recuperarPorDia(Date data) {
        Date inicio = DateUtils.truncate(data, Calendar.DATE);
        var fim = DateUtils.addDays(inicio, 1);

        return repository.findAllByHoraInicioGreaterThanEqualAndHoraFimLessThanEqualAndRemovidoIsFalse(inicio, fim);
    }

    public List<Agendamento> recuperarHoje() {
        var agendamentosCacheados = cache.getList(AgendamentoDiarioCacheKey);
        if(agendamentosCacheados != null) return agendamentosCacheados;

        return recuperarPorDia(new Date());
    }

    public void folgar(Date data) throws Exception{
        var agendamentos = recuperarPorDia(data);
        agendamentos.forEach(agendamento -> agendamento.setRemovido(true));
        repository.saveAll(agendamentos);

        var dataInicio = DateUtils.truncate(data, Calendar.DATE);
        var dataFim = DateUtils.addDays(data, 1);

        var folga = new Agendamento();
        folga.setTipo(EnumTipoAgendamento.Folga);
        folga.setHoraInicio(dataInicio);
        folga.setHoraFim(dataFim);

        salvar(folga);
    }

    public Integer salvarComDocumento(InserirAgendamentoDto agendamentoDto) throws Exception{
        if(agendamentoDto.documento().isPresent()) {
            documentoService.salvar(agendamentoDto.documento().get());
        }

        return salvar(agendamentoDto.agendamento());
    }

    public List<Agendamento> listar(ListarAgendamentoDto dto) throws Exception {
        exceptionSeNull(dto.getTipo(), Optional.of("Tipo de agendamento não informado!"));

        var agendamentos = repository.findAllByTipoEqualsAndRemovidoIsFalse(dto.getTipo());

        return super.listar(dto, Optional.ofNullable(agendamentos));
    }
}
