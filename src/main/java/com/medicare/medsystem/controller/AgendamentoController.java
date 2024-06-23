package com.medicare.medsystem.controller;

import com.medicare.medsystem.controller.base.BaseController;
import com.medicare.medsystem.domain.Agendamento;
import com.medicare.medsystem.domain.Dto.ListarAgendamentoDto;
import com.medicare.medsystem.service.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
            var registro = service.recuperar(); //todo recuperar por dia
            return Success(registro);
        } catch (Exception e){
            return Error("Erro ao recuperar registros! " + e.getMessage());
        }
    }

    @GetMapping("/Listar")
    public ResponseEntity<Object> listar(@RequestBody ListarAgendamentoDto dto) {
        try {
            //var registro = service.recuperar(dto); //todo recuperar por dia
            return Success("Not implemented yet!");
        } catch (Exception e) {
            return Error("Erro ao recuperar registros! " + e.getMessage());
        }
    }

    @PostMapping("/Encerrar")
    public ResponseEntity<Object> encerrar(@RequestParam("id") Integer id) {
        try {
            //var registro = service.encerrar(id); //todo encerrar o agendamento
            return Success("Not implemented yet!");
        } catch (Exception e){
            return Error("Erro ao encerrar registro! " + e.getMessage());
        }
    }

    @PostMapping("/Folgar")
    public ResponseEntity<Object> folgar(@RequestParam("dia") Date dia) {
        try {
            //var registro = service.folgar(dia); //todo criar folga
            return Success("Not implemented yet!");
        } catch (Exception e){
            return Error("Erro ao criar folga! " + e.getMessage());
        }
    }
}
