package com.medicare.medsystem.repository;

import com.medicare.medsystem.domain.Enum.EnumDiaSemana;
import com.medicare.medsystem.domain.GradeHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IGradeHorarioRepository extends JpaRepository<GradeHorario, Integer> {
    List<GradeHorario> findAllByInicioExpedienteGreaterThanEqualAndFimExpedienteLessThanEqualAndRemovidoIsFalse(Date dataInicio, Date dataFim);
    List<GradeHorario> findAllByDiaSemanaEqualsAndInicioExpedienteGreaterThanEqualAndFimExpedienteLessThanEqualAndRemovidoIsFalse(EnumDiaSemana diaSemana, Date dataInicio, Date dataFim);
}
