package com.medicare.medsystem.domain.Dto;

import lombok.Getter;

@Getter
public class ListarDto {
    private final Integer page;
    private final Integer rowsPerPage;

    public ListarDto(Integer page, Integer rowsPerPage) {
        this.page = page;
        this.rowsPerPage = rowsPerPage;
    }
}

