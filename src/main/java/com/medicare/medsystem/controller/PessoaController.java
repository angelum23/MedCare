package com.medicare.medsystem.controller;

import com.medicare.medsystem.controller.base.BaseController;
import com.medicare.medsystem.domain.Dto.InserirPessoaDto;
import com.medicare.medsystem.domain.Dto.ListarPessoaDto;
import com.medicare.medsystem.domain.Pessoa;
import com.medicare.medsystem.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Pessoa")
public class PessoaController extends BaseController<Pessoa> {
    private final PessoaService service;

    public PessoaController(PessoaService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/ListarPessoa")
    public ResponseEntity<Object> listar(@RequestBody ListarPessoaDto filtrosPessoa) {
        try {
            var pessoasEncontradas = service.listar(filtrosPessoa);
            return Success(pessoasEncontradas);
        } catch (Exception e){
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
