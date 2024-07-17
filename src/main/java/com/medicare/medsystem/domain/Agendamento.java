package com.medicare.medsystem.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.medicare.medsystem.domain.Base.IBaseEntity;
import com.medicare.medsystem.domain.Enum.EnumTipoAgendamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento implements IBaseEntity, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idagendamento")
    private Integer id;

    @Column(name = "removido")
    private Boolean removido = false;

    @Column(name = "descricao")
    String descricao;

    @Column(name = "horainicio")
    Date horaInicio;

    @Column(name = "horafim")
    Date horaFim;

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
    @JoinColumn(name = "id")
    private GradeHorario gradeHorario;
}
