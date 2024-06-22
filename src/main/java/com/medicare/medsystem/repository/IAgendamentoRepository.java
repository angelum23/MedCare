package com.medicare.medsystem.repository;

import com.medicare.medsystem.domain.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgendamentoRepository extends JpaRepository<Agendamento, Integer> {
}
