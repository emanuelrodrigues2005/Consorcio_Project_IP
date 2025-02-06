package br.ufrpe.ip.projeto.models;

import br.ufrpe.ip.projeto.enums.StatusGrupoConsorcioEnum;

import java.util.Random;

public class GrupoConsorcio {
    private String nomeGrupo;
    private int idGrupo;
    private int numeroParticipantes;
    private double valorTotal;
    private double taxaAdmin;
    private double valorParcela;
    private StatusGrupoConsorcioEnum statusGrupoConsorcio;

    public GrupoConsorcio(String nomeGrupo, int numeroParticipantes, double valorTotal, double taxaAdmin) {
        this.nomeGrupo = nomeGrupo;
        this.idGrupo = new Random().nextInt();
        this.numeroParticipantes = numeroParticipantes;
        this.valorTotal = valorTotal;
        this.taxaAdmin = taxaAdmin;
        this.valorParcela = ((getValorTotal() + getValorTotal() * getTaxaAdmin()) / getNumeroParticipantes());
        this.statusGrupoConsorcio = StatusGrupoConsorcioEnum.ATIVO;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public int getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public void setNumeroParticipantes(int numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getTaxaAdmin() {
        return taxaAdmin;
    }

    public void setTaxaAdmin(double taxaAdmin) {
        this.taxaAdmin = taxaAdmin;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public StatusGrupoConsorcioEnum getStatusGrupoConsorcio() {
        return statusGrupoConsorcio;
    }

    public void setStatusGrupoConsorcio(StatusGrupoConsorcioEnum statusGrupoConsorcio) {
        this.statusGrupoConsorcio = statusGrupoConsorcio;
    }

    // exibirSaldoDevedor é função da gui

    // exibirFinanças é função da gui

    public String toString() {
        return "Grupo: " + getNomeGrupo() + " { "
                + "\n  Número de Participantes: " + getNumeroParticipantes() + " | "
                + "\n  Valor total do Consórcio: " + getValorTotal() + " | "
                + "\n  Valor de Parcela: " + getValorParcela() + " | "
                + "\n  Taxa de Administração: " + getTaxaAdmin()
                + "\n  Status: " + getStatusGrupoConsorcio()
                + "\n}";
    }
}