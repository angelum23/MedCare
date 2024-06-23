package com.medicare.medsystem.domain.Dto;

import com.medicare.medsystem.domain.Enum.EnumTipoPessoa;
import lombok.Getter;

@Getter
public class ListarPessoaDto extends ListarDto {
    private final EnumTipoPessoa tipo;
    public ListarPessoaDto(Integer pages, Integer rowsPerPage, EnumTipoPessoa tipo) {
        super(pages, rowsPerPage);
        this.tipo = tipo;
    }
}
