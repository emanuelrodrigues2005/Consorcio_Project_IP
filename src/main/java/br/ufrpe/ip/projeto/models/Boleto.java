package br.ufrpe.ip.projeto.models;

import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Boleto implements Serializable {
    private String idBoleto;
    private Contrato contratoBoleto;
    private double valorBoleto;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private LocalDate dataEmissao;
    private StatusBoletoEnum statusBoleto;
    private int numeroParcela;

    public Boleto(Contrato contratoBoleto, LocalDate dataVencimento, int numeroParcela) {
        this.idBoleto = UUID.randomUUID().toString();
        this.contratoBoleto = contratoBoleto;
        this.numeroParcela = numeroParcela;
        this.valorBoleto = contratoBoleto.getGrupoAssociado().getValorParcela();
        this.dataEmissao = LocalDate.now();
        this.dataVencimento = dataVencimento;
        this.statusBoleto = StatusBoletoEnum.PENDENTE;
        this.dataPagamento = null;
    }

    public String getIdBoleto() { return idBoleto; }

    public Contrato getContratoBoleto() { return contratoBoleto; }

    public double getValorBoleto() { return valorBoleto; }

    public void setValorBoleto(double valorBoleto) {
        this.valorBoleto = valorBoleto;
    }

    public LocalDate getDataVencimento() { return dataVencimento; }

    public LocalDate getDataPagamento() { return dataPagamento; }

    public LocalDate getDataEmissao() { return dataEmissao; }

    public StatusBoletoEnum getStatusBoleto() { return statusBoleto; }

    public String getStatusBoletoString() {
        return statusBoleto.name();
    }

    public int getNumeroParcela() { return numeroParcela; }

    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }

    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }

    public void setDataEmissao(LocalDate dataEmsissao) { this.dataEmissao = dataEmsissao; }

    public void setStatusBoleto(StatusBoletoEnum statusBoleto) { this.statusBoleto = statusBoleto; }
}