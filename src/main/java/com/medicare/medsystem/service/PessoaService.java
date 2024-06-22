package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.Pessoa;
import com.medicare.medsystem.repository.IPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PessoaService extends BaseService<Pessoa>{
    @Autowired
    private IPessoaRepository repository;

    @Override
    protected JpaRepository<Pessoa, Integer> getRepository() {
        return repository;
    }
}
