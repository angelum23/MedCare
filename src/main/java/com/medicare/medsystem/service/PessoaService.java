package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.Agendamento;
import com.medicare.medsystem.domain.Dto.InserirPessoaDto;
import com.medicare.medsystem.domain.Dto.ListarAgendamentoDto;
import com.medicare.medsystem.domain.Dto.ListarPessoaDto;
import com.medicare.medsystem.domain.Pessoa;
import com.medicare.medsystem.repository.IPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService extends BaseService<Pessoa>{
    @Autowired
    private IPessoaRepository repository;
    @Autowired
    private DocumentoService documentoService;

    @Override
    protected JpaRepository<Pessoa, Integer> getRepository() {
        return repository;
    }

    public Integer salvarComFoto(InserirPessoaDto pessoaDto) throws Exception {
        if(pessoaDto.documento().isPresent()) {
            documentoService.salvar(pessoaDto.documento().get());
        }

        return super.salvar(pessoaDto.pessoa());
    }

    public List<Pessoa> listar(ListarPessoaDto dto) throws Exception {
        exceptionSeNull(dto.getTipo(), Optional.of("Tipo de pessoa não informado!"));

        var pessoas = repository.findAllByTipoEqualsAndRemovidoIsFalse(dto.getTipo());

        return super.listar(dto, Optional.ofNullable(pessoas));
    }
}
