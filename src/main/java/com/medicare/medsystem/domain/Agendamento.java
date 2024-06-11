package com.medicare.medsystem.domain;

import com.medicare.medsystem.domain.Base.BaseEntity;
import com.medicare.medsystem.domain.Base.IBaseEntity;
import com.medicare.medsystem.domain.Enum.EnumTipoAgendamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento extends BaseEntity implements IBaseEntity {
    @Column(name = "descricao")
    String descricao;

    @Column(name = "horainicio")
    Date horaInicio;

    @Column(name = "diatodo")
    Boolean diaTodo;

    @Column(name = "quanthorarios")
    Integer quantHorarios;

    @Column(name = "tipo")
    EnumTipoAgendamento tipo;

    @Column(name = "dadosconsulta")
    String dadosConsulta;

    @ManyToOne
    @JoinColumn(name = "idmedico")
    private Pessoa medico;

    @ManyToOne
    @JoinColumn(name = "idpaciente")
    private Pessoa paciente;

    @ManyToOne
    @JoinColumn(name = "idgradehorario")
    private GradeHorario gradeHorario;
}
