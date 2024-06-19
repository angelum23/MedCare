package com.medicare.medsystem.controller;

import com.medicare.medsystem.controller.base.BaseController;
import com.medicare.medsystem.domain.Agendamento;
import com.medicare.medsystem.domain.Pessoa;
import com.medicare.medsystem.service.AgendamentoService;
import com.medicare.medsystem.service.BaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Agendamento")
public class AgendamentoController extends BaseController<Agendamento> {
    public AgendamentoController(AgendamentoService service) {
        super(service);
    }
}
