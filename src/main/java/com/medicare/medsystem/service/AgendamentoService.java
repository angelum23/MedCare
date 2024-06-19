package com.medicare.medsystem.service;

import com.medicare.medsystem.domain.Agendamento;
import com.medicare.medsystem.repository.IAgendamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService extends BaseService<Agendamento>{
    public AgendamentoService(IAgendamentoRepository repository) {
        super(repository);
    }
}
