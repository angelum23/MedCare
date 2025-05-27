package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.Dto.InserirPessoaDto;
import com.medicare.medsystem.domain.Dto.ListarPessoaDto;
import com.medicare.medsystem.domain.Pessoa;
import com.medicare.medsystem.repository.IPessoaRepository;
import com.medicare.medsystem.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService extends BaseService<Pessoa> {
    @Autowired
    private IPessoaRepository repository;
    @Autowired
    private DocumentoService documentoService;

    @Override
    protected JpaRepository<Pessoa, Integer> getRepository() {
        return repository;
    }

    public Integer salvarComFoto(InserirPessoaDto dadosPessoa) throws Exception {
        if (dadosPessoa.documento().isPresent()) {
            documentoService.salvar(dadosPessoa.documento().get());
        }

        return super.salvar(dadosPessoa.pessoa());
    }

    public List<Pessoa> listar(ListarPessoaDto filtros) throws Exception {
        exceptionSeNull(filtros.getTipo(), Optional.of("Tipo de pessoa não informado!"));

        var pessoasFiltradas = repository.findAllByTipoEqualsAndRemovidoIsFalse(filtros.getTipo());

        return super.listar(filtros, Optional.ofNullable(pessoasFiltradas));
    }
}
