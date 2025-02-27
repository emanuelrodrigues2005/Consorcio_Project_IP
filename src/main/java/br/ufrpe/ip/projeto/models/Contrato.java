package br.ufrpe.ip.projeto.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import br.ufrpe.ip.projeto.enums.StatusContratoEnum;

public class Contrato implements Serializable {
    private Cliente cliente;
    private GrupoConsorcio grupoAssociado;
    private String idContrato;
    private int parcelasPagas;
    private double valorPago;
    private double saldoDevedor;
    private double saldoDevolucao;
    private StatusContratoEnum statusContrato;
    private LocalDate dataInicio;
    private LocalDate dataEncerramento;

    public Contrato(Cliente cliente, GrupoConsorcio grupoAssociado) {
        this.cliente = cliente;
        this.grupoAssociado = grupoAssociado;
        this.idContrato = UUID.randomUUID().toString();
        this.parcelasPagas = 0;
        this.valorPago = 0;
        this.saldoDevedor = grupoAssociado.getValorTotal() + (grupoAssociado.getValorTotal() * grupoAssociado.getTaxaAdmin());
        this.statusContrato = StatusContratoEnum.ATIVO;
        this.saldoDevolucao = 0;
        this.dataInicio = LocalDate.now();
    }

    public String getIdContrato() {
        return idContrato;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(LocalDate data) {
        this.dataEncerramento = data;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getNomeCliente() {
        return this.cliente.getNome();
    }

    public GrupoConsorcio getGrupoAssociado() {
        return grupoAssociado;
    }

    public int getParcelasPagas() {
        return parcelasPagas;
    }

    public void setParcelasPagas(int numParcelas) {
        this.parcelasPagas = numParcelas;
    }

    public double getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(double novoSaldo) {
        this.saldoDevedor = novoSaldo;
    }

    public StatusContratoEnum getStatusContrato() {
        return statusContrato;
    }

    public void setStatusContrato(StatusContratoEnum status) {
        this.statusContrato = status;
    }

    public String getStatusContratoString() {
        return statusContrato.name();
    }

    public double getSaldoDevolucao() {
        return saldoDevolucao;
    }

    public void setSaldoDevolucao(double novoSaldoDevolucao) {
        this.saldoDevolucao = novoSaldoDevolucao;
    }

    public String getNomeGrupoConsorcio() {
        return this.grupoAssociado.getNomeGrupo();
    }

    @Override
    public String toString() {
        return idContrato;
    }
}