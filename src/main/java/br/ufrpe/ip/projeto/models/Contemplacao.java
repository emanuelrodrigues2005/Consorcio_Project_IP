package br.ufrpe.ip.projeto.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Contemplacao implements Serializable {
	private String idContemplacao;
	private Contrato contratoContemplacao;
	private LocalDate dataContemplacao;

	public Contemplacao(Contrato contratoContemplacao) {
		this.contratoContemplacao = contratoContemplacao;
		this.dataContemplacao = LocalDate.now();
		this.idContemplacao = UUID.randomUUID().toString();
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

	public String getIdContemplacao() {
		return idContemplacao;
	}

	@Override
	public String toString() {
		return "\n" + getContratoContemplacao() + " (Data: " + getDataContemplacao() + ")\nId: " + getIdContemplacao();
	}
}
