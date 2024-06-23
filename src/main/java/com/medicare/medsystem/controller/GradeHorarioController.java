package com.medicare.medsystem.controller;

import com.medicare.medsystem.controller.base.BaseController;
import com.medicare.medsystem.domain.Dto.InserirSemanalDto;
import com.medicare.medsystem.domain.Dto.ListarDto;
import com.medicare.medsystem.domain.GradeHorario;
import com.medicare.medsystem.service.GradeHorarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/GradeHorario")
public class GradeHorarioController extends BaseController<GradeHorario> {
    public GradeHorarioController(GradeHorarioService service) {
        super(service);
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

    @GetMapping("/InserirSemanal")
    public ResponseEntity<Object> inserirSemanal(@RequestBody InserirSemanalDto dto) {
        try {
            //var registro = service.inserirSemanal(dto); //todo inserir registros para toda a semana
            return Success("Not implemented yet!");
        } catch (Exception e){
            return Error("Erro ao recuperar registros! " + e.getMessage());
        }
    }
}
