package com.medicare.medsystem.controller;

import com.medicare.medsystem.controller.base.BaseController;
import com.medicare.medsystem.domain.GradeHorario;
import com.medicare.medsystem.service.BaseService;
import com.medicare.medsystem.service.GradeHorarioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/GradeHorario")
public class GradeHorarioController extends BaseController<GradeHorario> {
    public GradeHorarioController(GradeHorarioService service) {
        super(service);
    }
}
