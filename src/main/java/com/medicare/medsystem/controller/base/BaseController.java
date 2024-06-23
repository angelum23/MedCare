package com.medicare.medsystem.controller.base;

import com.medicare.medsystem.domain.Base.IBaseEntity;
import com.medicare.medsystem.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            var registro = service.recuperar(id);
            return Success(registro);
        } catch (Exception e){
            return Error("Erro ao recuperar registro! " + e.getMessage());
        }
    }

    @GetMapping("/RecuperarTodos")
    public ResponseEntity<Object> recuperar() {
        try {
            var registro = service.recuperar();
            return Success(registro);
        } catch (Exception e){
            return Error("Erro ao recuperar registros! " + e.getMessage());
        }
    }

    @PostMapping("/Inserir")
    public ResponseEntity<Object> inserir(@RequestBody T entidade) {
        try {
            Integer id = service.salvar(entidade);
            return Success("Registro inserido com sucesso: " + id);
        } catch(Exception e) {
            return Error("Erro ao inserir registro! " + e.getMessage());
        }
    }

    @PutMapping("/Alterar")
    public ResponseEntity<Object> alterar(@RequestBody T entidade) {
        try {
            Integer id = service.salvar(entidade);
            return Success("Registro alterado com sucesso: " + id);
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

    public ResponseEntity<Object> Success(List<T> entidade) {
        return ResponseEntity.status(200).body(entidade);
    }

    public ResponseEntity<Object> Error(String texto) {
        return ResponseEntity.status(500).body(texto);
    }
}