package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.Base.IBaseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class BaseService<T extends IBaseEntity> {
    public T recuperar(Integer id) throws Exception{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<T> recuperar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Integer salvar(T entidade) throws Exception {
        if (entidade.getId() == null) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void remover(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}