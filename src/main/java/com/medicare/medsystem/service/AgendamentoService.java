package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.Agendamento;
import com.medicare.medsystem.domain.Dto.InserirAgendamentoDto;
import com.medicare.medsystem.domain.Dto.ListarAgendamentoDto;
import com.medicare.medsystem.domain.Dto.ListarDto;
import com.medicare.medsystem.domain.Enum.EnumTipoAgendamento;
import com.medicare.medsystem.repository.IAgendamentoRepository;
import com.medicare.medsystem.repository.IGradeHorarioRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService extends BaseService<Agendamento>{
    @Autowired
    private IAgendamentoRepository repository;
    @Autowired
    private IGradeHorarioRepository gradeHorarioRepository;
    @Autowired
    private DocumentoService documentoService;

    @Override
    protected JpaRepository<Agendamento, Integer> getRepository() {
        return repository;
    }

    @Override
    public Integer salvar(Agendamento agendamento) throws Exception {
        validarSeExisteGradeNoHorario(agendamento);
        validarHorarioNaoAgendado(agendamento);
        return super.salvar(agendamento);
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

    public List<Agendamento> recuperarPorDia(Optional<Date> data) {
        Date inicio;
        inicio = data.isPresent() ? DateUtils.truncate(data.get(), Calendar.DATE)
                                  : DateUtils.truncate(new Date(), Calendar.DATE);
        var fim = DateUtils.addDays(inicio, 1);

        return repository.findAllByHoraInicioGreaterThanEqualAndHoraFimLessThanEqualAndRemovidoIsFalse(inicio, fim);
    }

    public void folgar(Date data) throws Exception{
        var agendamentos = recuperarPorDia(Optional.ofNullable(data));
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

        return super.salvar(agendamentoDto.agendamento());
    }

    public List<Agendamento> listar(ListarAgendamentoDto dto) throws Exception {
        exceptionSeNull(dto.getTipo(), Optional.of("Tipo de agendamento não informado!"));

        var agendamentos = repository.findAllByTipoEqualsAndRemovidoIsFalse(dto.getTipo());

        return super.listar(dto, Optional.ofNullable(agendamentos));
    }
}
