package com.apera.aperaserver.service;

import com.apera.aperaserver.enterprise.NotFoundException;
import com.apera.aperaserver.model.QRelease;
import com.apera.aperaserver.model.Wallet;
import com.apera.aperaserver.repository.ReleaseRepository;
import com.apera.aperaserver.repository.WalletRepository;
import com.querydsl.jpa.impl.JPADeleteClause;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    ReleaseRepository releaseRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public Wallet salvarCarteira(Wallet entity) { return walletRepository.save(entity); }

    @Transactional
    public Page<Wallet> buscarTodos(String filter, Pageable pageable) {
        return walletRepository.findAll(filter, Wallet.class, pageable);
    }

    @Transactional
    public Wallet buscarPorId(Long id) {
        return walletRepository.findById(id).orElse(null);
    }

    @Transactional
    public Wallet alterar(Long id, Wallet entity) {
        Optional<Wallet> existingCarteiraOptional = walletRepository.findById(id);
        if (existingCarteiraOptional.isEmpty()) {
            throw new NotFoundException("Carteira não encontrada!");
        }

        Wallet existingCarteira = existingCarteiraOptional.get();

        modelMapper.map(entity, existingCarteira);

        return walletRepository.save(entity);
    }

    @Transactional
    public void remover(Long id) {
        Optional<Wallet> existingCarteiraOptional = walletRepository.findById(id);
        if (existingCarteiraOptional.isEmpty()) {
            throw new NotFoundException("Carteira não encontrada!");
        }
        QRelease release = QRelease.release;
        new JPADeleteClause(entityManager, release).where(release.wallet.eq(buscarPorId(id))).execute();

        walletRepository.deleteById(id); // Tratar para caso não encontrar a Carteira
    }

//    public List<Wallet> ordenarCarteirasPorValorTotal(List<Wallet> carteiras) {
//        Comparator<Wallet> comparador = Comparator.comparingDouble(Wallet::getValorTotal);
//
//        carteiras.sort(comparador);
//
//        return carteiras;
//    }
}
