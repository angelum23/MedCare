package com.medicare.medsystem.controller;

import com.medicare.medsystem.controller.base.BaseController;
import com.medicare.medsystem.domain.Dto.InserirSemanalDto;
import com.medicare.medsystem.domain.Dto.ListarDto;
import com.medicare.medsystem.domain.GradeHorario;
import com.medicare.medsystem.service.GradeHorarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/GradeHorario")
public class GradeHorarioController extends BaseController<GradeHorario> {
    private final GradeHorarioService service;
    public GradeHorarioController(GradeHorarioService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/Listar")
    public ResponseEntity<Object> listar(@RequestBody ListarDto dto) {
        try {
            //var registro = service.recuperar(dto); //todo listar paginado
            return Success("Not implemented yet!");
        } catch (Exception e){
            return Error("Erro ao recuperar registros! " + e.getMessage());
        }
    }

    @PostMapping("/InserirSemanal")
    public ResponseEntity<Object> inserirSemanal(@RequestBody InserirSemanalDto dto) {
        try {
            var ids = service.inserirSemanal(dto);
            return Success("Not implemented yet!");
        } catch (Exception e){
            return Error("Erro ao recuperar registros! " + e.getMessage());
        }
    }
}
