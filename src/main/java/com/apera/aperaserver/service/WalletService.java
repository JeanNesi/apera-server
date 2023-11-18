package com.apera.aperaserver.service;

import com.apera.aperaserver.enterprise.NotFoundException;
import com.apera.aperaserver.model.Wallet;
import com.apera.aperaserver.repository.WalletRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public Wallet salvarCarteira(Wallet entity) {
        return walletRepository.save(entity);
    }

    @Transactional
    public List<Wallet> buscarTodos() {
       return walletRepository.findAll();
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



//    @Transactional
//    public void remover(Long id) {
//        walletRepository.deleteById(id); // Tratar para caso não encontrar a Carteira
//    }
//    public List<Wallet> ordenarCarteirasPorValorTotal(List<Wallet> carteiras) {
//        Comparator<Wallet> comparador = Comparator.comparingDouble(Wallet::getValorTotal);
//
//        carteiras.sort(comparador);
//
//        return carteiras;
//    }
}
