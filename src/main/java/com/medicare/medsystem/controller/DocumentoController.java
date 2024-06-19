package com.medicare.medsystem.controller;

import com.medicare.medsystem.controller.base.BaseController;
import com.medicare.medsystem.domain.Agendamento;
import com.medicare.medsystem.domain.Documento;
import com.medicare.medsystem.service.BaseService;
import com.medicare.medsystem.service.DocumentoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Documento")
public class DocumentoController extends BaseController<Documento> {
    public DocumentoController(DocumentoService service) {
        super(service);
    }
}
