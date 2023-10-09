package com.apera.aperaserver.service;

import com.apera.aperaserver.model.Carteira;


import java.util.Comparator;
import java.util.List;

// Regra de negócio 6
/*A carteira deve ser ordenada pelo valor total dos ativos.*/

public class CarteiraService {
    public List<Carteira> ordenarCarteirasPorValorTotal(List<Carteira> carteiras) {
        Comparator<Carteira> comparador = Comparator.comparingDouble(Carteira::getValorTotal);

        carteiras.sort(comparador);

        return carteiras;
    }



}
