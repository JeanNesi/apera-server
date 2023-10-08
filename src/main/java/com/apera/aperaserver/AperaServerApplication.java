package com.apera.aperaserver;

import com.apera.aperaserver.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class AperaServerApplication {

	public static void main(String[] args) throws QuantidadeVendaException {

		// SpringApplication.run(AperaServerApplication.class, args);
		Ativo ativo1 = new Ativo();
		ativo1.setNome("MGLU3");
		ativo1.setRazaoSocial("Magazine Luiza LTDA");
		ativo1.setLogo("MAGALU");
		ativo1.setDataValidade(LocalDate.of(2023, 10, 1));

		Lancamento lancamento1 = new Lancamento();
		lancamento1.setAtivo(ativo1);
		lancamento1.setQuantidade(2.0);
		lancamento1.setCustoExtra(0.00);
		lancamento1.setPreco(100.00);
		lancamento1.setTipoAtivo(TipoAtivo.ACAO);
		lancamento1.setTipoLancamento(TipoLancamento.COMPRA);

		Lancamento lancamento2 = new Lancamento();
		lancamento2.setAtivo(ativo1);
		lancamento2.setQuantidade(3.0);
		lancamento2.setCustoExtra(0.00);
		lancamento2.setPreco(120.00);
		lancamento2.setTipoAtivo(TipoAtivo.ACAO);
		lancamento2.setTipoLancamento(TipoLancamento.VENDA);


	}

}
