package com.medicare.medsystem.controller;

import com.medicare.medsystem.controller.base.BaseController;
import com.medicare.medsystem.domain.Agendamento;
import com.medicare.medsystem.domain.Dto.InserirAgendamentoDto;
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
            var registros = service.recuperarHoje();
            return Success(registros);
        } catch (Exception e){
            return Error("Erro ao recuperar agendamentos do dia! " + e.getMessage());
        }
    }

    @GetMapping("/ListarAgendamento")
    public ResponseEntity<Object> listar(@RequestBody ListarAgendamentoDto dto) {
        try {
            var registro = service.listar(dto);
            return Success(registro);
        } catch (Exception e) {
            return Error("Erro ao recuperar agendamentos! " + e.getMessage());
        }
    }

    @PostMapping("/Folgar")
    public ResponseEntity<Object> folgar(@RequestParam("dia") Date data) {
        try {
            service.folgar(data);
            return Success("Folga registrada com sucesso");
        } catch (Exception e){
            return Error("Erro ao criar folga! " + e.getMessage());
        }
    }

    @PostMapping("/InserirAgendamento")
    public ResponseEntity<Object> inserir(@RequestBody InserirAgendamentoDto agendamentoDto) {
        try {
            Integer id = service.salvarComDocumento(agendamentoDto);
            return Success("Agendamento inserido com sucesso: " + id);
        } catch(Exception e) {
            return Error("Erro ao inserir registro! " + e.getMessage());
        }
    }

    @PutMapping("/AlterarAgendamento")
    public ResponseEntity<Object> alterar(@RequestBody InserirAgendamentoDto agendamentoDto) {
        try {
            Integer id = service.salvarComDocumento(agendamentoDto);
            return Success("Agendamento alterado com sucesso: " + id);
        } catch(Exception e) {
            return Error("Erro ao alterar registro! " + e.getMessage());
        }
    }
}
