package com.medicare.medsystem.domain.Dto;

import com.medicare.medsystem.domain.Documento;
import com.medicare.medsystem.domain.Pessoa;

import java.util.Optional;

public record InserirPessoaDto(Pessoa pessoa, Optional<Documento> documento) {
}
