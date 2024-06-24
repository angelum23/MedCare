package com.medicare.medsystem.repository;

import com.medicare.medsystem.domain.Enum.EnumTipoPessoa;
import com.medicare.medsystem.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPessoaRepository extends JpaRepository<Pessoa, Integer> {
    List<Pessoa> findAllByTipoEqualsAndRemovidoIsFalse(EnumTipoPessoa tipo);
}
