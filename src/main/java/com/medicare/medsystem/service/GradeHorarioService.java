package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.GradeHorario;
import com.medicare.medsystem.repository.IGradeHorarioRepository;
import org.springframework.stereotype.Service;

@Service
public class GradeHorarioService extends BaseService<GradeHorario>{
    public GradeHorarioService(IGradeHorarioRepository repository) {
        super(repository);
    }
}
