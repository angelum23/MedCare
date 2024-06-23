package com.medicare.medsystem.domain.Dto;

import lombok.Getter;

@Getter
public class ListarDto {
    private final Integer pages;
    private final Integer rowsPerPage;

    public ListarDto(Integer pages, Integer rowsPerPage) {
        this.pages = pages;
        this.rowsPerPage = rowsPerPage;
    }
}

