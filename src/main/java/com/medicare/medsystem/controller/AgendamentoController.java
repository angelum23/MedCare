package com.medicare.medsystem.controller;

import com.medicare.medsystem.controller.base.BaseController;
import com.medicare.medsystem.domain.Agendamento;
import com.medicare.medsystem.domain.Dto.InserirAgendamentoDto;
import com.medicare.medsystem.domain.Dto.ListarAgendamentoDto;
import com.medicare.medsystem.service.AgendamentoService;
import org.springframework.format.annotation.DateTimeFormat;
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
            var listaAgendamentos = service.recuperarHoje();
            return Success(listaAgendamentos);
        } catch (Exception e){
            return Error("Erro ao recuperar agendamentos do dia! " + e.getMessage());
        }
    }

    @GetMapping("/ListarAgendamento")
    public ResponseEntity<Object> listar(@RequestBody ListarAgendamentoDto filtros) {
        try {
            var agendamentosFiltrados = service.listar(filtros);
            return Success(agendamentosFiltrados);
        } catch (Exception e) {
            return Error("Erro ao recuperar agendamentos! " + e.getMessage());
        }
    }

    @PostMapping("/Folgar")
    public ResponseEntity<Object> folgar(@RequestParam("dia") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date diaFolga) {
        try {
            service.folgar(diaFolga);
            return Success("Folga registrada com sucesso");
        } catch (Exception e){
            return Error("Erro ao criar folga! " + e.getMessage());
        }
    }

    @PostMapping("/InserirAgendamento")
    public ResponseEntity<Object> inserir(@RequestBody InserirAgendamentoDto dadosAgendamento) {
        try {
            Integer idAgendamento = service.salvarComDocumento(dadosAgendamento);
            return Success("Agendamento inserido com sucesso: " + idAgendamento);
        } catch(Exception e) {
            return Error("Erro ao inserir agendamento! " + e.getMessage());
        }
    }

    @PutMapping("/AlterarAgendamento")
    public ResponseEntity<Object> alterar(@RequestBody InserirAgendamentoDto dadosAtualizacao) {
        try {
            Integer idAgendamento = service.salvarComDocumento(dadosAtualizacao);
            return Success("Agendamento alterado com sucesso: " + idAgendamento);
        } catch(Exception e) {
            return Error("Erro ao alterar agendamento! " + e.getMessage());
        }
    }
}