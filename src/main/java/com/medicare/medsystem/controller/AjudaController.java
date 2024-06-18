package com.medicare.medsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medicare.medsystem.domain.Dto.ProjetoInfo;

@RestController
@RequestMapping("/Ajuda")
public class AjudaController {
    @GetMapping("")
    public ResponseEntity<Object> recuperar() {
        String[] estudantes = {"Ângelo José da Rosa", "Gabriel Macieski"};
        var retorno = new ProjetoInfo(estudantes, "MedCare", "Saúde e Bem estar: Mapeamento de atendimentos médicos");
        return ResponseEntity.status(500).body(retorno);
    }
}
