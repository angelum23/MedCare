package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.Documento;
import com.medicare.medsystem.repository.IDocumentoRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentoService extends BaseService<Documento>{
    public DocumentoService(IDocumentoRepository repository) {
        super(repository);
    }
}
