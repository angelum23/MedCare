package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.GradeHorario;
import com.medicare.medsystem.repository.IGradeHorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GradeHorarioService extends BaseService<GradeHorario>{
    @Autowired
    private IGradeHorarioRepository repository;

    @Override
    protected JpaRepository<GradeHorario, Integer> getRepository() {
        return repository;
    }
}
