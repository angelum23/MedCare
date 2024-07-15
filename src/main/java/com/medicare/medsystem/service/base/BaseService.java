package com.medicare.medsystem.service.base;

import com.medicare.medsystem.domain.Base.IBaseEntity;
import com.medicare.medsystem.domain.Dto.ListarDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public abstract class BaseService<T extends IBaseEntity> {
    protected abstract JpaRepository<T, Integer> getRepository();

    public T recuperar(Integer id) throws NoSuchElementException {
        return getRepository().findById(id).orElseThrow();
    }

    public List<T> recuperar() {
        return getRepository().findAll();
    }

    public Integer salvar(T entidade) throws Exception {
        entidade = getRepository().save(entidade);
        return entidade.getId();
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

    public List<T> listar(ListarDto dto, Optional<List<T>> registrosParam) throws Exception {
        exceptionSeNull(dto, Optional.of("Filtros não informados"));
        exceptionSeNull(dto.getPage(), Optional.of("Pagina não informada"));
        exceptionSeNull(dto.getRowsPerPage(), Optional.of("Quantidade de linhas por pagina não informada"));

        var registros = registrosParam.orElseGet(() -> getRepository().findAll());

        var quantidadeSkip = (dto.getPage() - 1) * dto.getRowsPerPage();

        if(registros.size() < quantidadeSkip) {
            throw new IllegalArgumentException("Nenhum registro encontrado");
        }

        var pagina = registros.stream().skip(quantidadeSkip).limit(dto.getRowsPerPage()).toList();
        return pagina;
    }

    public void exceptionSeNull(Object reg, Optional<String> mensagemErro) throws Exception {
        if (reg == null) {
            throw new Exception(mensagemErro.orElse("Registro não encontrado!"));
        }
    }
}