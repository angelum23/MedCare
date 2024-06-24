package com.medicare.medsystem.domain.Dto;

import com.medicare.medsystem.domain.Enum.EnumTipoAgendamento;
import lombok.Getter;

@Getter
public class ListarAgendamentoDto extends ListarDto {
    private final EnumTipoAgendamento tipo;
    public ListarAgendamentoDto(Integer page, Integer rowsPerPage, EnumTipoAgendamento tipo) {
        super(page, rowsPerPage);
        this.tipo = tipo;
    }
}
