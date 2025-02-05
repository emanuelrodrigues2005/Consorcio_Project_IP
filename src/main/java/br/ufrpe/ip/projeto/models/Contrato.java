package br.ufrpe.ip.projeto.models;

import br.ufrpe.ip.projeto.enums.StatusContratoEnum;

public class Contrato {
    private Cliente cliente;
    private GrupoConsorcio grupoAssociado;
    private int parcelasPagas;
    private double valorPago;
    private double saldoDevedor;
    private double saldoDevolucao;
    private StatusContratoEnum statusContrato;

    public Contrato(Cliente cliente, GrupoConsorcio grupoAssociado) {
        this.cliente = cliente;
        this.grupoAssociado = grupoAssociado;
        this.parcelasPagas = 0;
        this.valorPago = 0;
        this.saldoDevedor = grupoAssociado.getValorTotal() + (grupoAssociado.getValorTotal() * grupoAssociado.getTaxaAdmin());
        this.statusContrato = StatusContratoEnum.ATIVO;
        this.saldoDevolucao = 0;
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