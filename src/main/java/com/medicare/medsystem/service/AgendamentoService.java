package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.Agendamento;
import com.medicare.medsystem.repository.IAgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService extends BaseService<Agendamento>{
    @Autowired
    private IAgendamentoRepository repository;

    @Override
    protected JpaRepository<Agendamento, Integer> getRepository() {
        return repository;
    }
}
