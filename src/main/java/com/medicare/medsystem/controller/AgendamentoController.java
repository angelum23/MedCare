package com.medicare.medsystem.controller;

import com.medicare.medsystem.controller.base.BaseController;
import com.medicare.medsystem.domain.Agendamento;
import com.medicare.medsystem.domain.Dto.ListarAgendamentoDto;
import com.medicare.medsystem.service.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/Agendamento")
public class AgendamentoController extends BaseController<Agendamento> {
    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/RecuperarDia")
    public ResponseEntity<Object> recuperarDia() {
        try {
            var registros = service.recuperarPorDia(Optional.empty());
            return Success(registros);
        } catch (Exception e){
            return Error("Erro ao recuperar registros! " + e.getMessage());
        }
    }

    @GetMapping("/Listar")
    public ResponseEntity<Object> listar(@RequestBody ListarAgendamentoDto dto) {
        try {
            //var registro = service.recuperar(dto); //todo listar agendamentos paginado
            return Success("Not implemented yet!");
        } catch (Exception e) {
            return Error("Erro ao recuperar registros! " + e.getMessage());
        }
    }

    @PostMapping("/Folgar")
    public ResponseEntity<Object> folgar(@RequestParam("dia") Date data) {
        try {
            service.folgar(data);
            return Success("Not implemented yet!");
        } catch (Exception e){
            return Error("Erro ao criar folga! " + e.getMessage());
        }
    }
}
