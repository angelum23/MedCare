package com.medicare.medsystem.domain;

import com.medicare.medsystem.domain.Base.BaseEntity;
import com.medicare.medsystem.domain.Base.IBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Documento extends BaseEntity implements IBaseEntity {
    @Column(name = "urldocumento")
    String urlDocumento;
    @Column(name = "descricao")
    String descricao;
}
