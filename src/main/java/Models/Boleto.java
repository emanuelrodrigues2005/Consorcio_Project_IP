package Models;

import Enums.StatusBoletoEnum;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Boleto {
    private static final Set<Integer> idsUsados = new HashSet<>();
    private static final Random random = new Random();
    private int idBoleto;
    private Contrato contratoBoleto;
    private double valorBoleto;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private LocalDate dataEmissao;
    private StatusBoletoEnum statusBoleto;
    private int numeroParcela;

    public Boleto(Contrato contratoBoleto, LocalDate dataEmissao, LocalDate dataVencimento, int numeroParcela) {
        this.idBoleto = gerarIdUnico();
        this.contratoBoleto = contratoBoleto;
        this.numeroParcela = numeroParcela;
        this.valorBoleto = contratoBoleto.getGrupoAssociado().getValorParcela();
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.statusBoleto = StatusBoletoEnum.PENDENTE;
        this.dataPagamento = null;
    }

    private int gerarIdUnico() {
        int id;
        do {
            id = 10000 + random.nextInt(90000);
        } while (idsUsados.contains(id));
        idsUsados.add(id);
        return id;
    }

    // Getters e Setters
    public int getIdBoleto() { return idBoleto; }

    public Contrato getContratoBoleto() { return contratoBoleto; }

    public double getValorBoleto() { return valorBoleto; }

    public LocalDate getDataVencimento() { return dataVencimento; }

    public LocalDate getDataPagamento() { return dataPagamento; }

    public LocalDate getDataEmissao() { return dataEmissao; }

    public StatusBoletoEnum getStatusBoleto() { return statusBoleto; }

    public int getNumeroParcela() { return numeroParcela; }

    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }

    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }

    public void setDataEmissao(LocalDate dataEmsissao) { this.dataEmissao = dataEmsissao; }

    public void setStatusBoleto(StatusBoletoEnum statusBoleto) { this.statusBoleto = statusBoleto; }
}