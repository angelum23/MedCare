package com.medicare.medsystem.service.base;

import com.medicare.medsystem.domain.Base.IBaseEntity;
import com.medicare.medsystem.domain.Dto.ListarDto;
import com.medicare.medsystem.utils.Paginador;
import com.medicare.medsystem.utils.ValidacaoUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
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

    public Integer salvar(T registro) throws Exception {
        registro = getRepository().save(registro);
        return registro.getId();
    }

    public void remover(Integer id) throws Exception {
        exceptionSeNaoExiste(id);

        var registro = getRepository().findById(id).orElseThrow();
        registro.setRemovido(true);

        getRepository().save(registro);
    }

    private void exceptionSeNaoExiste(Integer id) throws Exception {
        if (getRepository().existsById(id)) return;

        throw new Exception("Registro não encontrado");
    }

    public List<T> listar(ListarDto filtros, Optional<List<T>> registrosFiltrados) throws Exception {
        // Validações
        Objects.requireNonNull(filtros, "Filtros não informados");
        ValidacaoUtils.validarMaiorQueZero(filtros.getPage(), "Número da página inválido");
        ValidacaoUtils.validarMaiorQueZero(filtros.getRowsPerPage(), "Quantidade de itens por página inválida");

        // Obtém todos os registros (filtrados ou não)
        List<T> todosRegistros = registrosFiltrados.orElseGet(this::recuperar);

        // Aplica a paginação
        return Paginador.paginar(todosRegistros, filtros.getPage(), filtros.getRowsPerPage());
    }

    @Deprecated
    public void exceptionSeNull(Object valor, Optional<String> mensagemErro) throws Exception {
        ValidacaoUtils.validarNaoNulo(valor, mensagemErro.orElse("Registro não encontrado!"));
    }
}
