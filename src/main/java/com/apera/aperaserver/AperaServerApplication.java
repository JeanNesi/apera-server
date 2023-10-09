package com.apera.aperaserver;

import com.apera.aperaserver.model.*;
import com.apera.aperaserver.service.CarteiraService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AperaServerApplication {

	public static void main(String[] args) {

		Carteira carteira1 = new Carteira();
		carteira1.setValorTotal(1000.0);

		Carteira carteira2 = new Carteira();
		carteira2.setValorTotal(500.0);

		Carteira carteira3 = new Carteira();
		carteira3.setValorTotal(1500.0);

		List<Carteira> carteiras = new ArrayList<>();
		carteiras.add(carteira1);
		carteiras.add(carteira2);
		carteiras.add(carteira3);

		CarteiraService carteiraService = new CarteiraService();

		List<Carteira> carteirasOrdenadas = carteiraService.ordenarCarteirasPorValorTotal(carteiras);

		for (Carteira carteira : carteirasOrdenadas) {
			System.out.println("Valor Total da Carteira: " + carteira.getValorTotal());
		}
	}

	}

