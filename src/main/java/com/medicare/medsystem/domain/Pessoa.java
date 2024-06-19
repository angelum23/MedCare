package com.medicare.medsystem.domain;

import com.medicare.medsystem.domain.Base.BaseEntity;
import com.medicare.medsystem.domain.Base.IBaseEntity;
import com.medicare.medsystem.domain.Enum.EnumTipoIdentificacao;
import com.medicare.medsystem.domain.Enum.EnumTipoPessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa extends BaseEntity implements IBaseEntity {
    @Column(name = "nome")
    String nome;

    @Column(name = "tipo")
    EnumTipoPessoa tipo;

    @Column(name = "tipoidentificacao")
    EnumTipoIdentificacao enumTipoIdentificacao;

    @Column(name = "identificacao")
    String identificacao;

    @OneToOne
    @JoinColumn(name = "iddocumento")
    private Documento documento;

    @OneToMany(mappedBy = "pessoa")
    private List<Agendamento> agendamentosPaciente;
}
