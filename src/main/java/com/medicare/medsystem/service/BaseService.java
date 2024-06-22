package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.Base.IBaseEntity;
import com.medicare.medsystem.repository.IBaseRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public abstract class BaseService<T extends IBaseEntity> {
    @Autowired
    private IBaseRepository<T> repository;

    protected IBaseRepository<T> getRepository() {
        return repository;
    }

    public T recuperar(Integer id) throws NoSuchElementException {
        return (T) getRepository().findById(id).orElseThrow();
    }

    public List<T> recuperar() {
        return getRepository().findAll();
    }

    public Integer salvar(T entidade) throws Exception {
        var id = entidade.getId();
        exceptionSeNaoExiste(id);

        getRepository().save(entidade);
        return id;
    }

    public void remover(Integer id) throws Exception {
        exceptionSeNaoExiste(id);

        getRepository().deleteById(id);
    }

    private void exceptionSeNaoExiste(Integer id) throws Exception {
        if (getRepository().existsById(id)) return;

        throw new NoSuchElementException("Registro não encontrado");
    }
}