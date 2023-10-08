package com.apera.aperaserver.service;

import com.apera.aperaserver.model.Lancamento;
import com.apera.aperaserver.repository.AtivoRepository;
import com.apera.aperaserver.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;
    @Autowired
    private AtivoRepository ativoRepository;

    public void salvarLancamento(Lancamento lancamento) {
        VerificaAtivoExistente(lancamento);
        lancamentoRepository.save(lancamento);
    }
    //Regra de negócio 5
    public String VerificaAtivoExistente(Lancamento lancamento) {
        if (ativoRepository.existsById(lancamento.getAtivo().getId())) {
            alterarQuantidadeCarteira(lancamento.getId());
        }
        else {
            salvarAtivoCarteira(lancamento.getId());
        }
        return null;
    }

    @Transactional
    public void salvarAtivoCarteira(Long ativo) {
        // Salvar ativo na carteira do usuário;
    }

    @Transactional
    public void alterarQuantidadeCarteira(Long ativo) {
        // Alterar quantidade ativo na carteira do usuário
    }


}
