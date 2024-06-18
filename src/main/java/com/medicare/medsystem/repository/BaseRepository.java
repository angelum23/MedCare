package com.medicare.medsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract interface BaseRepository<T> extends JpaRepository<T, Integer> {
    // Métodos de consulta customizados podem ser adicionados aqui
}
