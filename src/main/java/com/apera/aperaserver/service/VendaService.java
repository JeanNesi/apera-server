package com.apera.aperaserver.service;

import com.apera.aperaserver.model.Lancamento;
import com.apera.aperaserver.model.QuantidadeVendaException;
import com.apera.aperaserver.model.Venda;
import com.apera.aperaserver.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

//Regra de negócio 4
/*Ao realizar um lançamento de venda, só deverá ser aceito a venda
de quantidades menores ou iguais a quantidade da ação na carteira.*/

//Regra de negócio 2
/*Ao adicionar um novo lançamento na carteira, deverá ser adicionado
um registro detalhado do lançamento, para exclusão ou edição posteriormente.*/

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    public String verificaQuantidadeVenda(Lancamento lancamento) throws QuantidadeVendaException {
        if (lancamento == null) {
            return "Lançamento está nulo";
        }
        Double quantidadeDesejada = lancamento.getQuantidade();
        Optional<Venda> vendaParametro = vendaRepository.findById(lancamento.getId());
        if (vendaParametro.isEmpty()) {
            return "A venda está nula";
        }
        Double quantidadePossui = vendaParametro.get().getLancamento().getQuantidade();
        if(quantidadeDesejada > quantidadePossui) {
            throw new QuantidadeVendaException();
        }
        return null;
    }

    @Transactional
    public String salvarVenda(Venda venda) throws QuantidadeVendaException {
        verificaQuantidadeVenda(venda.getLancamento());
        vendaRepository.save(venda);
        return "Venda bem sucedida";
    }

}
