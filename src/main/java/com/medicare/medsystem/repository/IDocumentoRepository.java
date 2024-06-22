package com.medicare.medsystem.repository;

import com.medicare.medsystem.domain.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentoRepository extends JpaRepository<Documento, Integer> {
}
