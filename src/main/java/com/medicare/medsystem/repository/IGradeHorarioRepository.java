package com.medicare.medsystem.repository;

import com.medicare.medsystem.domain.GradeHorario;
import com.medicare.medsystem.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGradeHorarioRepository extends IBaseRepository<GradeHorario> {
}
