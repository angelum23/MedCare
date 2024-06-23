package com.medicare.medsystem.domain.Dto;

import com.medicare.medsystem.domain.GradeHorario;

public record InserirSemanalDto(GradeHorario segunda,
                                GradeHorario terca,
                                GradeHorario quarta,
                                GradeHorario quinta,
                                GradeHorario sexta,
                                GradeHorario sabado,
                                GradeHorario domingo) {
}
