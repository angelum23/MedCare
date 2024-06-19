package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.Base.IBaseEntity;
import com.medicare.medsystem.repository.IBaseRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public abstract class BaseService<T extends IBaseEntity> {
    private final IBaseRepository<T> repository;

    protected BaseService(IBaseRepository<T> repository) {
        this.repository = repository;
    }

    public T recuperar(Integer id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow();
    }

    public List<T> recuperar() {
        return repository.findAll();
    }

    public Integer salvar(T entidade) throws Exception {
        var id = entidade.getId();
        exceptionSeNaoExiste(id);

        repository.save(entidade);
        return id;
    }

    public void remover(Integer id) throws Exception {
        exceptionSeNaoExiste(id);

        repository.deleteById(id);
    }

    private void exceptionSeNaoExiste(Integer id) throws Exception {
        if (repository.existsById(id)) return;

        throw new NoSuchElementException("Registro não encontrado");
    }
}