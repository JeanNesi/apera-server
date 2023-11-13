package com.apera.aperaserver.service;

import com.apera.aperaserver.enterprise.NotFoundException;
import com.apera.aperaserver.model.Carteira;
import com.apera.aperaserver.repository.CarteiraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CarteiraService {

    @Autowired
    CarteiraRepository carteiraRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public Carteira salvarCarteira(Carteira entity) {
        return carteiraRepository.save(entity);
    }

    @Transactional
    public List<Carteira> buscarTodos() {
       return carteiraRepository.findAll();
    }

    @Transactional
    public Carteira buscarPorId(Long id) {
        return carteiraRepository.findById(id).orElse(null);
    }

    @Transactional
    public Carteira alterar(Long id, Carteira entity) {
        Optional<Carteira> existingCarteiraOptional = carteiraRepository.findById(id);
        if (existingCarteiraOptional.isEmpty()) {
            throw new NotFoundException("Carteira não encontrada!");
        }

        Carteira existingCarteira = existingCarteiraOptional.get();

        modelMapper.map(entity, existingCarteira);

        return carteiraRepository.save(entity);
    }

    @Transactional
    public void remover(Long id) {
        carteiraRepository.deleteById(id); // Tratar para caso não encontrar a Carteira
    }
    public List<Carteira> ordenarCarteirasPorValorTotal(List<Carteira> carteiras) {
        Comparator<Carteira> comparador = Comparator.comparingDouble(Carteira::getValorTotal);

        carteiras.sort(comparador);

        return carteiras;
    }
}
