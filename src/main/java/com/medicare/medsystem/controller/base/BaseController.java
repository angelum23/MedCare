package com.medicare.medsystem.controller.base;

import com.medicare.medsystem.domain.Base.IBaseEntity;
import com.medicare.medsystem.domain.Dto.ListarDto;
import com.medicare.medsystem.service.base.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Base")
public abstract class BaseController<T extends IBaseEntity> {

    private final BaseService<T> service;

    public BaseController(BaseService<T> service) {
        this.service = service;
    }

    @GetMapping("/Recuperar")
    public ResponseEntity<Object> recuperar(@RequestParam("id") Integer id) {
        try {
            var entidadeRecuperada = service.recuperar(id);
            return Success(entidadeRecuperada);
        } catch (Exception e){
            return Error("Erro ao recuperar registro! " + e.getMessage());
        }
    }

    @GetMapping("/RecuperarTodos")
    public ResponseEntity<Object> recuperar() {
        try {
            var entidadesRecuperadas = service.recuperar();
            return Success(entidadesRecuperadas);
        } catch (Exception e){
            return Error("Erro ao recuperar registros! " + e.getMessage());
        }
    }

    @GetMapping("/Listar")
    public ResponseEntity<Object> listar(@RequestBody ListarDto filtros) {
        try {
            var listaFiltrada = service.listar(filtros, Optional.empty());
            return Success(listaFiltrada);
        } catch (Exception e){
            return Error("Erro ao recuperar registros! " + e.getMessage());
        }
    }

    @PostMapping("/Inserir")
    public ResponseEntity<Object> inserir(@RequestBody T dadosEntrada) {
        try {
            Integer id = service.salvar(dadosEntrada);
            return Success("Registro inserido com sucesso: " + id);
        } catch(Exception e) {
            return Error("Erro ao inserir registro! " + e.getMessage());
        }
    }

    @PutMapping("/Alterar")
    public ResponseEntity<Object> alterar(@RequestBody T entidade) {
        try {
            Integer idEntidade = service.salvar(entidade);
            return Success("Registro alterado com sucesso: " + idEntidade);
        } catch(Exception e) {
            return Error("Erro ao alterar registro! " + e.getMessage());
        }
    }

    @DeleteMapping("/Remover")
    public ResponseEntity<Object> remover(@RequestParam("id") Integer id) {
        try {
            service.remover(id);
            return Success("Registro removido com sucesso!");
        }
        catch(Exception e) {
            return Error("Erro ao remover registro!" + e.getMessage());
        }
    }

    public ResponseEntity<Object> Success(String texto) {
        return ResponseEntity.status(200).body(texto);
    }

    public ResponseEntity<Object> Success(T entidade) {
        return ResponseEntity.status(200).body(entidade);
    }

    public ResponseEntity<Object> Success(List<T> listaEntidades) {
        return ResponseEntity.status(200).body(listaEntidades);
    }

    public ResponseEntity<Object> Error(String texto) {
        return ResponseEntity.status(500).body(texto);
    }
}