package com.medicare.medsystem.domain.Dto;

import com.medicare.medsystem.domain.Enum.EnumTipoAgendamento;
import lombok.Getter;

@Getter
public class ListarAgendamentoDto extends ListarDto {
    private final EnumTipoAgendamento tipo;
    public ListarAgendamentoDto(Integer pages, Integer rowsPerPage, EnumTipoAgendamento tipo) {
        super(pages, rowsPerPage);
        this.tipo = tipo;
    }
}
