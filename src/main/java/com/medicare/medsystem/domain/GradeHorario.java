package com.medicare.medsystem.domain;

import com.medicare.medsystem.domain.Base.IBaseEntity;
import com.medicare.medsystem.domain.Enum.EnumDiaSemana;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradeHorario implements IBaseEntity, Serializable {
    @Serial
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idgradehorario")
    private Integer id;

    @Column(name = "removido")
    private Boolean removido = false;

    @Column(name = "inicioexpediente")
    Date inicioExpediente;

    @Column(name = "fimexpediente")
    Date fimExpediente;

    @Column(name = "diasemana")
    EnumDiaSemana diaSemana;

    @Column(name = "descricao")
    String descricao;

    @OneToMany(mappedBy = "gradeHorario")
    private List<Agendamento> agendamentos;
}
