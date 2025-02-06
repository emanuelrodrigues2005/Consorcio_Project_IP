package br.ufrpe.ip.projeto.controllers;

import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.repositories.BoletoRepository;
import br.ufrpe.ip.projeto.repositories.interfaces.IBoletoRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class BoletoController {
    private static BoletoController instance;
    private final IBoletoRepository repositoryBoleto;

    public BoletoController() {
        repositoryBoleto = new BoletoRepository();
    }

    public static BoletoController getInstance() {
        if (instance == null) {
            instance = new BoletoController();
        }
        return instance;
    }

    public ArrayList<Boleto> getAllBoletos() {
        return this.repositoryBoleto.getAllBoletos();
    } //exceptions: ArrayVazio

    public int getIdBoleto(Contrato contratoBoleto, int numeroParcela) {
        return this.repositoryBoleto.getIdBoleto(contratoBoleto, numeroParcela);
    } //exceptions: IdBoletoInexistente, ContratoInexistente, CampoInvalido

    public Boleto getBoletoById(int idBoleto) {
        return this.repositoryBoleto.getBoletoById(idBoleto);
    } //exceptions: BoletoInexistente, IdBoletoInexistente, CampoInvalido

    public Boleto getBoletoByContrato(Contrato contrato) {
        return this.repositoryBoleto.getBoletoByContrato(contrato);
    } //exceptions: BoletoInexistente, ContratoInexistente, CampoInvalido

    public void createBoleto(Contrato contratoBoleto, LocalDate dataVencimento, int numeroParcela) {
        this.repositoryBoleto.createBoleto(contratoBoleto, dataVencimento, numeroParcela);
    } //exceptions: ContratoInexistente, CampoInvalido

    public void updateDataPagamento(Boleto boleto) {
        this.repositoryBoleto.updateDataPagamento(boleto);
    } //exceptions: BoletoInexistente, CampoInvalido

    public void updateStatusBoleto(Boleto boleto, StatusBoletoEnum statusBoleto) {
        this.repositoryBoleto.updateStatusBoleto(boleto, statusBoleto);
    } //exceptions: BoletoInexistente, CampoInvalido

    public void deleteBoleto(int idBoleto) {
        this.repositoryBoleto.deleteBoleto(idBoleto);
    } //exceptions: BoletoInexistente, CampoInvalido

    public void atualizarBoletoVencido(Boleto boleto) {
        if(boleto.getStatusBoleto() == StatusBoletoEnum.PENDENTE && LocalDate.now().isAfter(boleto.getDataVencimento())) {
            updateStatusBoleto(boleto, StatusBoletoEnum.ATRASADO);

            boleto.setValorBoleto(boleto.getValorBoleto() + (boleto.getValorBoleto() * 0.05));
        }
    } //exceptions: BoletoInexistente

    public void verificarBoletosVencidos() {
        for (Boleto boleto : this.repositoryBoleto.getAllBoletos()) {
            atualizarBoletoVencido(boleto);
        }
    }

    public void realizarPagamento(int idBoleto) {
        Boleto boleto  = this.repositoryBoleto.getBoletoById(idBoleto);

        if(boleto != null) {
            this.updateDataPagamento(boleto);
            this.updateStatusBoleto(boleto, StatusBoletoEnum.PAGO);
        }
    } //exceptions: BoletoInexistente
}
