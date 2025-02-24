package br.ufrpe.ip.projeto.controllers;

import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.repositories.BoletoRepository;
import br.ufrpe.ip.projeto.repositories.interfaces.IBoletoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoletoController {
    private static BoletoController instance;
    private final IBoletoRepository repositoryBoleto;
    public static final double ADICIONAL_MULTA = 0.05;

    private BoletoController() {
        this.repositoryBoleto = BoletoRepository.getInstance();
    }

    public static BoletoController getInstance() {
        if (instance == null) {
            instance = new BoletoController();
        }
        return instance;
    }

    public List<Boleto> getAllBoletos() {
        return this.repositoryBoleto.getAllBoletos();
    } //exceptions: ArrayVazio

    public String getIdBoleto(Contrato contratoBoleto, int numeroParcela) {
        return this.repositoryBoleto.getIdBoleto(contratoBoleto, numeroParcela);
    } //exceptions: IdBoletoInexistente, ContratoInexistente, CampoInvalido

    public Boleto getBoletoById(String idBoleto) {
        return this.repositoryBoleto.getBoletoById(idBoleto);
    } //exceptions: BoletoInexistente, IdBoletoInexistente, CampoInvalido

    public Boleto getBoletoByContrato(Contrato contrato) {
        return this.repositoryBoleto.getBoletoByContrato(contrato);
    } //exceptions: BoletoInexistente, ContratoInexistente, CampoInvalido

    public void createBoleto(Contrato contratoBoleto, LocalDate dataVencimento, int numeroParcela) {
        this.repositoryBoleto.createBoleto(contratoBoleto, dataVencimento, numeroParcela);
        this.repositoryBoleto.salvarArquivo();
    } //exceptions: ContratoInexistente, CampoInvalido

    public void updateDataPagamento(Boleto boleto) {
        this.repositoryBoleto.updateDataPagamento(boleto);
        this.repositoryBoleto.salvarArquivo();
    } //exceptions: BoletoInexistente, CampoInvalido

    public void updateStatusBoleto(Boleto boleto, StatusBoletoEnum statusBoleto) {
        this.repositoryBoleto.updateStatusBoleto(boleto, statusBoleto);
        this.repositoryBoleto.salvarArquivo();
    } //exceptions: BoletoInexistente, CampoInvalido

    public void deleteBoleto(String idBoleto) {
        this.repositoryBoleto.deleteBoleto(idBoleto);
        this.repositoryBoleto.salvarArquivo();
    } //exceptions: BoletoInexistente, CampoInvalido

    public void atualizarBoletoVencido(Boleto boleto) {
        if(boleto.getStatusBoleto() == StatusBoletoEnum.PENDENTE && LocalDate.now().isAfter(boleto.getDataVencimento())) {
            updateStatusBoleto(boleto, StatusBoletoEnum.ATRASADO);

            boleto.setValorBoleto(boleto.getValorBoleto() + (boleto.getValorBoleto() * ADICIONAL_MULTA));
            this.repositoryBoleto.salvarArquivo();
        }
    } //exceptions: BoletoInexistente

    public void verificarBoletosVencidos() {
        for (Boleto boleto : this.repositoryBoleto.getAllBoletos()) {
            atualizarBoletoVencido(boleto);
        }
    }

    public void realizarPagamento(String idBoleto) {
        Boleto boleto  = this.repositoryBoleto.getBoletoById(idBoleto);

        if(boleto != null) {
            this.updateDataPagamento(boleto);
            this.updateStatusBoleto(boleto, StatusBoletoEnum.PAGO);
            this.repositoryBoleto.salvarArquivo();
        }
    } //exceptions: BoletoInexistente
}
