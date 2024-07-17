package com.medicare.medsystem.domain;

import com.medicare.medsystem.domain.Base.IBaseEntity;
import com.medicare.medsystem.domain.Enum.EnumTipoIdentificacao;
import com.medicare.medsystem.domain.Enum.EnumTipoPessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa implements IBaseEntity, Serializable {
    @Serial
    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idpessoa")
    private Integer id;

    @Column(name = "removido")
    private Boolean removido = false;

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

    @OneToMany(mappedBy = "paciente")
    private List<Agendamento> agendamentosPaciente;

    @OneToMany(mappedBy = "medico")
    private List<Agendamento> agendamentosMedico;
}
