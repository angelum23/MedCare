package com.medicare.medsystem.domain;

import com.medicare.medsystem.domain.Base.BaseEntity;
import com.medicare.medsystem.domain.Base.IBaseEntity;
import com.medicare.medsystem.domain.Enum.EnumDiaSemana;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradeHorario extends BaseEntity implements IBaseEntity {
    @Column(name = "inicioexpediente")
    Date inicioExpediente;

    @Column(name = "fimexpediente")
    Date fimExpediente;

    @Column(name = "duracaohorario")
    Integer duracaoHorario;

    @Column(name = "diasemana")
    EnumDiaSemana diaSemana;

    @Column(name = "descricao")
    String descricao;

    @OneToMany(mappedBy = "agendamento")
    private List<Agendamento> agendamentos;
}
