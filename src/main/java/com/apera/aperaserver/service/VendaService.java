package com.apera.aperaserver.service;

import com.apera.aperaserver.enterprise.ValidationException;
import com.apera.aperaserver.enterprise.QuantidadeVendaException;
import com.apera.aperaserver.model.Venda;
import com.apera.aperaserver.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Venda salvarVenda(Venda entity) throws QuantidadeVendaException {
        if (entity.getLancamento() == null) {
            throw new ValidationException("O lannçamento não pode ser nulo!");
        }

        Optional<Venda> vendaParametro = vendaRepository.findById(entity.getLancamento().getId());

        if (vendaParametro.isEmpty()) {
            throw new ValidationException("A não pode ser nula!");
        }

        Double quantidadeDesejada = entity.getLancamento().getQuantidade();
        Double quantidadePossui = vendaParametro.get().getLancamento().getQuantidade();

        if (quantidadeDesejada > quantidadePossui) {
            throw new QuantidadeVendaException();
        }

        return vendaRepository.save(entity);
    }

    public List<Venda> buscarTodos(String filter) {
        return vendaRepository.findAll(filter, Venda.class);
    }

    public Page<Venda> buscarTodos(String filter, Pageable pageable) {
        return vendaRepository.findAll(filter, Venda.class, pageable);
    }

}
