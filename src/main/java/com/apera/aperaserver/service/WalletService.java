package com.apera.aperaserver.service;

import com.apera.aperaserver.enterprise.NotFoundException;
import com.apera.aperaserver.model.QRelease;
import com.apera.aperaserver.model.QWallet;
import com.apera.aperaserver.model.Wallet;
import com.apera.aperaserver.repository.WalletRepository;
import com.querydsl.jpa.impl.JPADeleteClause;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public List<Wallet> findAllWalletByUser(Long userId) {
        return (List<Wallet>) walletRepository.findAll(QWallet.wallet.user.id.eq(userId), Sort.by(Sort.Direction.ASC, "createdAt"));
    }

    @Transactional
    public Wallet findWalletById(Long id) {
        return walletRepository.findById(id).orElse(null);
    }

    @Transactional
    public Wallet createWallet(Wallet entity) {
       long userWalletsCount = walletRepository.count(QWallet.wallet.user.id.eq(entity.getUser().getId()));

       if(userWalletsCount > 3){
           throw new NotFoundException("Você já atingiu o limite máximo de carteiras!");
       }

        return walletRepository.save(entity);
    }

    public Wallet updateWallet(Long id, Wallet entity) {
        Optional<Wallet> existingWalletOptional = walletRepository.findById(id);
        if (existingWalletOptional.isEmpty()) {
            throw new NotFoundException("Carteira não encontrada!");
        }

        Wallet existingWallet = existingWalletOptional.get();

        modelMapper.map(entity, existingWallet);

        return walletRepository.save(existingWallet);
    }

    @Transactional
    public void deleteWallet(Long id) {
        Optional<Wallet> existingWalletOptional = walletRepository.findById(id);
        if (existingWalletOptional.isEmpty()) {
            throw new NotFoundException("Carteira não encontrada!");
        }

        QRelease release = QRelease.release;
        new JPADeleteClause(entityManager, release).where(release.wallet.eq(findWalletById(id))).execute();

        walletRepository.deleteById(id);
    }

    @Transactional
    public Page<Wallet> buscarTodos(String filter, Pageable pageable) {
        return walletRepository.findAll(filter.replace(" ", "+"), Wallet.class, pageable);
    }

//    public List<Wallet> ordenarCarteirasPorValorTotal(List<Wallet> carteiras) {
//        Comparator<Wallet> comparador = Comparator.comparingDouble(Wallet::getValorTotal);
//
//        carteiras.sort(comparador);
//
//        return carteiras;
//    }
}
