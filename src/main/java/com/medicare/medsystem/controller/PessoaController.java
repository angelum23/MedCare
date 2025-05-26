package com.medicare.medsystem.controller;

import com.medicare.medsystem.controller.base.BaseController;
import com.medicare.medsystem.domain.Dto.InserirPessoaDto;
import com.medicare.medsystem.domain.Dto.ListarPessoaDto;
import com.medicare.medsystem.domain.Pessoa;
import com.medicare.medsystem.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.medicare.medsystem.domain.Enum.EnumTipoPessoa;
import java.util.Arrays;

@RestController
@RequestMapping("/Pessoa")
public class PessoaController extends BaseController<Pessoa> {
    private final PessoaService service;

    public PessoaController(PessoaService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/ListarPessoa")
    public ResponseEntity<Object> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int rowsPerPage,
            @RequestParam(required = false) String tipo) {
        try {
            EnumTipoPessoa enumTipo = tipo != null ? EnumTipoPessoa.valueOf(tipo) : null;
            var filtrosPessoa = new ListarPessoaDto(page, rowsPerPage, enumTipo);
            var pessoasEncontradas = service.listar(filtrosPessoa);
            return Success(pessoasEncontradas);
        } catch (IllegalArgumentException e) {
            return Error("Tipo de pessoa inválido. Tipos válidos: " + Arrays.toString(EnumTipoPessoa.values()));
        } catch (Exception e) {
            return Error("Erro ao recuperar registros! " + e.getMessage());
        }
    }

    @PostMapping("/InserirPessoa")
    public ResponseEntity<Object> inserir(@RequestBody InserirPessoaDto dadosPessoa) {
        try {
            Integer idPessoa = service.salvarComFoto(dadosPessoa);
            return Success("Registro inserido com sucesso: " + idPessoa);
        } catch(Exception e) {
            return Error("Erro ao inserir registro! " + e.getMessage());
        }
    }

    @PutMapping("/AlterarPessoa")
    public ResponseEntity<Object> alterar(@RequestBody InserirPessoaDto dadosAtualizacao) {
        try {
            Integer idPessoa = service.salvarComFoto(dadosAtualizacao);
            return Success("Registro alterado com sucesso: " + idPessoa);
        } catch(Exception e) {
            return Error("Erro ao alterar registro! " + e.getMessage());
        }
    }
}
