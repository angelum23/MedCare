package com.medicare.medsystem.controller;

import com.medicare.medsystem.controller.base.BaseController;
import com.medicare.medsystem.domain.Dto.InserirSemanalDto;
import com.medicare.medsystem.domain.Dto.ListarDto;
import com.medicare.medsystem.domain.GradeHorario;
import com.medicare.medsystem.service.GradeHorarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GradeHorario")
public class GradeHorarioController extends BaseController<GradeHorario> {
    private final GradeHorarioService service;
    public GradeHorarioController(GradeHorarioService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/ListarGradeHorario")
    public ResponseEntity<Object> listar(@RequestBody ListarDto dto) {
        try {
            var registros = service.listar(dto, Optional.empty());
            return Success(registros);
        } catch (Exception e){
            return Error("Erro ao recuperar grades de horário! " + e.getMessage());
        }
    }

    @PostMapping("/InserirSemanal")
    public ResponseEntity<Object> inserirSemanal(@RequestBody InserirSemanalDto dto) {
        try {
            List<Integer> ids = service.inserirSemanal(dto);
            List<String> idsString = ids.stream().map(String::valueOf).toList();
            return Success("Registros inseridos com sucesso: " + String.join(", ", idsString));
        } catch (Exception e){
            return Error("Erro ao recuperar registros! " + e.getMessage());
        }
    }
}
