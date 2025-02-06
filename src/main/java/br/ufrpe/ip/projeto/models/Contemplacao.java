package br.ufrpe.ip.projeto.models;

import java.time.LocalDate;
import br.ufrpe.ip.projeto.enums.StatusContratoEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Contemplacao {
	private static int idContemplacao;
    private Contrato contratoContemplacao;
	private LocalDate dataContemplacao;
	
	public Contemplacao(Contrato contratoContemplacao) {
		this.contratoContemplacao = contratoContemplacao;
		this.dataContemplacao = java.time.LocalDate.now();
		idContemplacao = new Random().nextInt(10000000) + 1;
	}

	public Contrato getContratoContemplacao() {
		return contratoContemplacao;
	}

	public void setContratoContemplacao(Contrato contratoContemplacao) {
		this.contratoContemplacao = contratoContemplacao;
	}

	public LocalDate getDataContemplacao() {
		return dataContemplacao;
	}

	public void setDataContemplacao(LocalDate dataContemplacao) {
		this.dataContemplacao = dataContemplacao;
	}

	public int getIdContemplacao() {
		return idContemplacao;
	}

	@Override
	public String toString() {
		return "\n" + getContratoContemplacao() + "(Data: " + getDataContemplacao() + ")\nId: " + getIdContemplacao();
	}
}
