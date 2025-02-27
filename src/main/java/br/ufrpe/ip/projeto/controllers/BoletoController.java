package br.ufrpe.ip.projeto.controllers;

import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.exceptions.*;
import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.repositories.BoletoRepository;
import br.ufrpe.ip.projeto.repositories.interfaces.IBoletoRepository;

import java.time.LocalDate;
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

    public List<Boleto> getAllBoletos() throws ArrayVazioException {
        List<Boleto> boletos = this.repositoryBoleto.getAllBoletos();
        if (boletos.isEmpty()) {
            throw new ArrayVazioException("Nenhum boleto encontrado.");
        }
        return boletos;
    } //exceptions: ArrayVazio

    public String getIdBoleto(Contrato contratoBoleto, int numeroParcela) throws IdBoletoInexistenteException, ContratoInexistenteException, CampoInvalidoException {
        if (contratoBoleto == null) {
            throw new ContratoInexistenteException("Contrato não encontrado.");
        }
        if (numeroParcela <= 0) {
            throw new CampoInvalidoException("numeroParcela");

        }
        String idBoleto = this.repositoryBoleto.getIdBoleto(contratoBoleto, numeroParcela);
        if (idBoleto == null) {
            throw new IdBoletoInexistenteException("Boleto não encontrado.");
        }
        return idBoleto;
    } //exceptions: IdBoletoInexistente, ContratoInexistente, CampoInvalido

    public Boleto getBoletoById(String idBoleto) throws BoletoInexistenteException, CampoInvalidoException {
        if (idBoleto == null || idBoleto.trim().isEmpty()) {
            throw new CampoInvalidoException("idBoleto");
        }
        Boleto boleto = this.repositoryBoleto.getBoletoById(idBoleto);
        if (boleto == null) {
            throw new BoletoInexistenteException(idBoleto);
        }
        return boleto;
    } //exceptions: BoletoInexistente, CampoInvalido

    public Boleto getBoletoByContrato(Contrato contrato) throws BoletoInexistenteException, ContratoInvalidoException, CampoInvalidoException {
        if (contrato == null) {
            throw new ContratoInvalidoException("Contrato nulo fornecido.");
        }
        String idContrato = contrato.getIdContrato();
        if (idContrato == null || idContrato.trim().isEmpty()) {
            throw new ContratoInvalidoException("Contrato com ID inválido: " + idContrato);
        }
        Boleto boleto = this.repositoryBoleto.getBoletoByContrato(contrato);
        if (boleto == null) {
            throw new BoletoInexistenteException("Boleto não encontrado");
        }
        return boleto;
    } //exceptions: BoletoInexistente, ContratoInexistente, CampoInvalido

    public void createBoleto(Contrato contratoBoleto) throws ContratoInexistenteException, CampoInvalidoException {
        if (contratoBoleto == null) {
            throw new ContratoInexistenteException("Contrato inválido.");
        }
        this.repositoryBoleto.createBoleto(contratoBoleto);
        this.repositoryBoleto.salvarArquivo();
    } //exceptions: ContratoInexistente, CampoInvalido

    public void updateDataPagamento(Boleto boleto) throws BoletoInexistenteException, CampoInvalidoException {
        if (boleto == null) {
            throw new BoletoInexistenteException("Boleto inválido.");
        }
        this.repositoryBoleto.updateDataPagamento(boleto);
        this.repositoryBoleto.salvarArquivo();
    } //exceptions: BoletoInexistente, CampoInvalido

    public void updateStatusBoleto(Boleto boleto, StatusBoletoEnum statusBoleto) throws BoletoInexistenteException, CampoInvalidoException {
        if (boleto == null) {
            throw new BoletoInexistenteException("Boleto inválido.");
        }
        if (statusBoleto == null) {
            throw new CampoInvalidoException("statusBoleto");
        }
        this.repositoryBoleto.updateStatusBoleto(boleto, statusBoleto);
        this.repositoryBoleto.salvarArquivo();
    } //exceptions: BoletoInexistente, CampoInvalido

    public void deleteBoleto(String idBoleto) throws BoletoInexistenteException, CampoInvalidoException {
        if (idBoleto == null || idBoleto.trim().isEmpty()) {
            throw new CampoInvalidoException("idBoleto");
        }
        Boleto boleto = this.repositoryBoleto.getBoletoById(idBoleto);
        if (boleto == null) {
            throw new BoletoInexistenteException("Boleto não encontrado.");
        }
        this.repositoryBoleto.deleteBoleto(idBoleto);
        this.repositoryBoleto.salvarArquivo();
    } //exceptions: BoletoInexistente, CampoInvalido

    public void atualizarBoletoVencido(Boleto boleto) throws BoletoInexistenteException, CampoInvalidoException {
        if (boleto == null) {
            throw new BoletoInexistenteException("Boleto inválido.");
        }
        if (boleto.getStatusBoleto() == StatusBoletoEnum.PENDENTE && LocalDate.now().isAfter(boleto.getDataVencimento())) {
            updateStatusBoleto(boleto, StatusBoletoEnum.ATRASADO);
            boleto.setValorBoleto(boleto.getValorBoleto() + (boleto.getValorBoleto() * ADICIONAL_MULTA));
            this.repositoryBoleto.salvarArquivo();
        }
    } //exceptions: BoletoInexistente

    public void verificarBoletosVencidos() throws BoletoInexistenteException, CampoInvalidoException {
        for (Boleto boleto : this.repositoryBoleto.getAllBoletos()) {
            atualizarBoletoVencido(boleto);
        }
    }

    public void realizarPagamento(String idBoleto) throws BoletoInexistenteException, CampoInvalidoException {
        if (idBoleto == null || idBoleto.trim().isEmpty()) {
            throw new CampoInvalidoException("idBoleto.");
        }
        Boleto boleto = this.repositoryBoleto.getBoletoById(idBoleto);
        if (boleto == null) {
            throw new BoletoInexistenteException("Boleto não encontrado.");
        }
        this.updateDataPagamento(boleto);
        this.updateStatusBoleto(boleto, StatusBoletoEnum.PAGO);
        this.repositoryBoleto.salvarArquivo();
    }//exceptions: BoletoInexistente
}
