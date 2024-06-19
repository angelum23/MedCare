package com.medicare.medsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBaseRepository<T> extends JpaRepository<T, Integer> {
}
