package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.Dto.InserirSemanalDto;
import com.medicare.medsystem.domain.GradeHorario;
import com.medicare.medsystem.repository.IGradeHorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeHorarioService extends BaseService<GradeHorario>{
    @Autowired
    private IGradeHorarioRepository repository;

    @Override
    protected JpaRepository<GradeHorario, Integer> getRepository() {
        return repository;
    }

    @Override
    public Integer salvar(GradeHorario entity) throws Exception {
        validarSeExisteGradeNoHorario(entity);
        return super.salvar(entity);
    }

    public List<Integer> inserirSemanal(InserirSemanalDto dto) throws Exception{
        List<Integer> ids = new ArrayList<>();

        ids.add(super.salvar(dto.segunda()));
        ids.add(super.salvar(dto.terca()));
        ids.add(super.salvar(dto.quarta()));
        ids.add(super.salvar(dto.quinta()));
        ids.add(super.salvar(dto.sexta()));
        ids.add(super.salvar(dto.sabado()));
        ids.add(super.salvar(dto.domingo()));

        return ids;
    }


    private void validarSeExisteGradeNoHorario(GradeHorario gradeHorario) {
        var horariosJaExistentes = repository.findAllByDiaSemanaEqualsAndInicioExpedienteGreaterThanEqualAndFimExpedienteLessThanEqualAndRemovidoIsFalse(
                gradeHorario.getDiaSemana(),
                gradeHorario.getInicioExpediente(),
                gradeHorario.getFimExpediente()
        );

        if(!horariosJaExistentes.isEmpty()) {
            throw new IllegalArgumentException("Este horário está agendado, por favor escolha outro");
        }
    }
}
