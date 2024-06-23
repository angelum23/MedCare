package com.medicare.medsystem.controller;

import com.medicare.medsystem.controller.base.BaseController;
import com.medicare.medsystem.domain.Dto.ListarPessoaDto;
import com.medicare.medsystem.domain.Pessoa;
import com.medicare.medsystem.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Pessoa")
public class PessoaController extends BaseController<Pessoa> {
    public PessoaController(PessoaService service) {
        super(service);
    }

    @GetMapping("/Listar")
    public ResponseEntity<Object> listar(@RequestBody ListarPessoaDto dto) {
        try {
            //var registro = service.recuperar(dto); //todo recuperar paginado e com filtro
            return Success("Not implemented yet!");
        } catch (Exception e){
            return Error("Erro ao recuperar registros! " + e.getMessage());
        }
    }
}
