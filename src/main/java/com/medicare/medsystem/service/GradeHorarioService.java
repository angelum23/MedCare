package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.Dto.InserirSemanalDto;
import com.medicare.medsystem.domain.GradeHorario;
import com.medicare.medsystem.repository.IGradeHorarioRepository;
import com.medicare.medsystem.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeHorarioService extends BaseService<GradeHorario> {
    @Autowired
    private IGradeHorarioRepository repository;

    @Override
    protected JpaRepository<GradeHorario, Integer> getRepository() {
        return repository;
    }

    @Override
    public Integer salvar(GradeHorario gradeHorario) throws Exception {
        validarSeExisteGradeNoHorario(gradeHorario);
        return super.salvar(gradeHorario);
    }

    public List<Integer> inserirSemanal(InserirSemanalDto dadosGradeSemanal) throws Exception{
        List<Integer> idsCriados = new ArrayList<>();

        idsCriados.add(super.salvar(dadosGradeSemanal.segunda()));
        idsCriados.add(super.salvar(dadosGradeSemanal.terca()));
        idsCriados.add(super.salvar(dadosGradeSemanal.quarta()));
        idsCriados.add(super.salvar(dadosGradeSemanal.quinta()));
        idsCriados.add(super.salvar(dadosGradeSemanal.sexta()));
        idsCriados.add(super.salvar(dadosGradeSemanal.sabado()));
        idsCriados.add(super.salvar(dadosGradeSemanal.domingo()));

        return idsCriados;
    }


    private void validarSeExisteGradeNoHorario(GradeHorario gradeHorario) {
        var horariosConflitantes = repository.findAllByDiaSemanaEqualsAndInicioExpedienteGreaterThanEqualAndFimExpedienteLessThanEqualAndRemovidoIsFalse(
                gradeHorario.getDiaSemana(),
                gradeHorario.getInicioExpediente(),
                gradeHorario.getFimExpediente()
        );

        if(!horariosConflitantes.isEmpty()) {
            throw new IllegalArgumentException("Este horário está agendado, por favor escolha outro");
        }
    }
}
