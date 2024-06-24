package com.medicare.medsystem.repository;

import com.medicare.medsystem.domain.Agendamento;
import com.medicare.medsystem.domain.Enum.EnumTipoAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IAgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    List<Agendamento> findAllByHoraInicioGreaterThanEqualAndHoraFimLessThanEqualAndRemovidoIsFalse(Date horaInicio, Date horaFim);
    List<Agendamento> findAllByTipoEqualsAndRemovidoIsFalse(EnumTipoAgendamento tipo);
}
