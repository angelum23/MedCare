package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.Pessoa;
import com.medicare.medsystem.repository.IPessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class PessoaService extends BaseService<Pessoa>{
    public PessoaService(IPessoaRepository repository) {
        super(repository);
    }
}
