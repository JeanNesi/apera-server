package com.apera.aperaserver.service;

import com.apera.aperaserver.model.Carteira;


import java.util.Comparator;
import java.util.List;

public class CarteiraService {
    public List<Carteira> ordenarCarteirasPorValorTotal(List<Carteira> carteiras) {
        Comparator<Carteira> comparador = Comparator.comparingDouble(Carteira::getValorTotal);

        carteiras.sort(comparador);

        return carteiras;
    }



}
