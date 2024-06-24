package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.Base.IBaseEntity;
import com.medicare.medsystem.domain.Dto.ListarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public abstract class BaseService<T extends IBaseEntity> {
    private JpaRepository<T, Integer> repository;

    protected JpaRepository<T, Integer> getRepository() {
        return repository;
    }

    public T recuperar(Integer id) throws NoSuchElementException {
        return getRepository().findById(id).orElseThrow();
    }

    public List<T> recuperar() {
        return getRepository().findAll();
    }

    public Integer salvar(T entidade) throws Exception {
        var id = entidade.getId();

        getRepository().save(entidade);
        return id;
    }

    public void remover(Integer id) throws Exception {
        exceptionSeNaoExiste(id);

        var entidade = getRepository().findById(id).orElseThrow();
        entidade.setRemovido(true);

        getRepository().save(entidade);
    }

    private void exceptionSeNaoExiste(Integer id) throws Exception {
        if (getRepository().existsById(id)) return;

        throw new Exception("Registro não encontrado");
    }

    public List<T> listar(ListarDto dto, Optional<List<T>> registrosParam) {
        var registros = registrosParam.orElseGet(() -> getRepository().findAll());

        var quantidadeSkip = dto.getPages() * dto.getRowsPerPage();

        if(registros.size() < quantidadeSkip) {
            throw new IllegalArgumentException("Nenhum registro encontrado");
        }

        var pagina = registros.stream().skip(dto.getPages()).limit(dto.getRowsPerPage()).toList();
        return pagina;
    }
}