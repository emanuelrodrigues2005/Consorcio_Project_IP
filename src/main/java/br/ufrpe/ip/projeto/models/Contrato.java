package br.ufrpe.ip.projeto.models;

import java.time.LocalDate;
import java.util.Random;

import br.ufrpe.ip.projeto.enums.StatusContratoEnum;

public class Contrato {
    private Cliente cliente;
    private GrupoConsorcio grupoAssociado;
    private int idContrato;
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
        this.idContrato = new Random().nextInt();
        this.parcelasPagas = 0;
        this.valorPago = 0;
        this.saldoDevedor = grupoAssociado.getValorTotal() + (grupoAssociado.getValorTotal() * grupoAssociado.getTaxaAdmin());
        this.statusContrato = StatusContratoEnum.ATIVO;
        this.saldoDevolucao = 0;
        this.dataInicio = LocalDate.now();
    }

    public int getIdContrato() {
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

    public double getSaldoDevolucao() {
        return saldoDevolucao;
    }

    public void setSaldoDevolucao(double novoSaldoDevolucao) {
        this.saldoDevolucao = novoSaldoDevolucao;
    } 

    public String toString() {
        return "Cliente: " + this.getCliente().getNome() + "\nCPF: " + this.getCliente().getCpf() + "\nGrupo associado: " + this.getGrupoAssociado().getNomeGrupo() + "\nStatus: " + this.getStatusContrato() + "\nParcelas pagas: " + this.getParcelasPagas() + "\nSaldo devedor: " + this.getSaldoDevedor() + "\n";
    }
}