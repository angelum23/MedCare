package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.Agendamento;
import com.medicare.medsystem.domain.Documento;
import com.medicare.medsystem.repository.IAgendamentoRepository;
import com.medicare.medsystem.repository.IDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentoService extends BaseService<Documento>{
    @Autowired
    private IDocumentoRepository repository;

    @Override
    protected JpaRepository<Documento, Integer> getRepository() {
        return repository;
    }
}
