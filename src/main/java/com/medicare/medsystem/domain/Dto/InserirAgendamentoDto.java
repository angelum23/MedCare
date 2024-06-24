package com.medicare.medsystem.domain.Dto;

import com.medicare.medsystem.domain.Agendamento;
import com.medicare.medsystem.domain.Documento;
import com.medicare.medsystem.domain.Pessoa;

import java.util.Optional;

public record InserirAgendamentoDto(Agendamento agendamento, Optional<Documento> documento) {
}
