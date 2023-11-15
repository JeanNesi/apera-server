package com.apera.aperaserver.service;

import com.apera.aperaserver.model.Compra;
import com.apera.aperaserver.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompraService {

//Regra de negócio 2
/*Ao adicionar um novo lançamento na carteira, deverá ser adicionado
um registro detalhado do lançamento, para exclusão ou edição posteriormente.*/

    @Autowired
    private CompraRepository compraRepository;

    @Transactional
    public Compra salvarCompra(Compra entity) {
        return compraRepository.save(entity);
    }

    public List<Compra> buscarTodos() {
        return compraRepository.findAll();
    }

    public Compra buscarPorId(Long id) {
        return compraRepository.findById(id).orElse(null);
    }

}
