package com.medicare.medsystem.domain;

import com.medicare.medsystem.domain.Base.IBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Documento implements IBaseEntity, Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iddocumento")
    private Integer id;

    @Column(name = "removido")
    private Boolean removido = false;

    @Column(name = "urldocumento")
    String urlDocumento;

    @Column(name = "descricao")
    String descricao;
}
