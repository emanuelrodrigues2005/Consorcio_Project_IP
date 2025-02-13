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
        List<Boleto> boletos = this.repositoryBoleto.getAllBoletos();
        if (boletos.isEmpty()) {
            throw new ArrayVazioException("Nenhum boleto encontrado.");
        }
        return boletos;
    }

    public String getIdBoleto(Contrato contratoBoleto, int numeroParcela) {
        if (contratoBoleto == null) {
            throw new ContratoInexistenteException("Contrato não encontrado.");
        }
        if (numeroParcela <= 0) {
            throw new CampoInvalidoException("Número de parcela deve ser maior que zero.");
        }
        String idBoleto = this.repositoryBoleto.getIdBoleto(contratoBoleto, numeroParcela);
        if (idBoleto == null) {
            throw new IDBoletoInexistenteException("Id do boleto não encontrado.");
        }
        return idBoleto;
    }

    public Boleto getBoletoById(String idBoleto) {
        if (idBoleto == null) {
            throw new IDBoletoInexistenteException("ID do boleto é inválido ou não foi informado.");
        }
        Boleto boleto = this.repositoryBoleto.getBoletoById(idBoleto);
        if (boleto == null) {
            throw new BoletoInexistenteException("Boleto não encontrado.");
        }
        return boleto;
    }

    public List<Boleto> getBoletosByContrato(Contrato contrato) {
        if (contrato == null) {
            throw new ContratoInexistenteException("Contrato inválido.");
        }
        List<Boleto> boletos = this.repositoryBoleto.getBoletosByContrato(contrato);
        if (boletos == null) {
            throw new BoletoInexistenteException("Nenhum boleto encontrado para este contrato.");
        }
        return boletos;
    }

    public void createBoleto(Contrato contratoBoleto, LocalDate dataVencimento, int numeroParcela) {
        if (contratoBoleto == null) {
            throw new ContratoInexistenteException("Contrato inválido.");
        }
        if (dataVencimento == null || numeroParcela <= 0) {
            throw new CampoInvalidoException("Dados inválidos para criação do boleto.");
        }
        this.repositoryBoleto.createBoleto(contratoBoleto, dataVencimento, numeroParcela);
    }

    public void updateDataPagamento(Boleto boleto) {
        if (boleto == null) {
            throw new BoletoInexistenteException("Boleto inexistente.");
        }
        this.repositoryBoleto.updateDataPagamento(boleto);
    }

    public void updateStatusBoleto(Boleto boleto, StatusBoletoEnum statusBoleto) {
        if (boleto == null) {
            throw new BoletoInexistenteException("Boleto inexistente.");
        }
        this.repositoryBoleto.updateStatusBoleto(boleto, statusBoleto);
    }

    public void deleteBoleto(String idBoleto) {
        if (idBoleto == null) {
            throw new IDBoletoInexistenteException("ID do boleto é inválido ou não foi informado.");
        }
        this.repositoryBoleto.deleteBoleto(idBoleto);
    }

    public void atualizarBoletoVencido(Boleto boleto) {
        if (boleto == null) {
            throw new BoletoInexistenteException("Boleto inexistente.");
        }
        if (boleto.getStatusBoleto() == StatusBoletoEnum.PENDENTE && LocalDate.now().isAfter(boleto.getDataVencimento())) {
            updateStatusBoleto(boleto, StatusBoletoEnum.ATRASADO);
            boleto.setValorBoleto(boleto.getValorBoleto() + (boleto.getValorBoleto() * ADICIONAL_MULTA));
        }
    } 

    public void verificarBoletosVencidos() {
        List<Boleto> boletos = this.repositoryBoleto.getAllBoletos();
        if (boletos.isEmpty()) {
            throw new ArrayVazioException("Nenhum boleto cadastrado.");
        }
        for (Boleto boleto : boletos) {
            atualizarBoletoVencido(boleto);
        }
    }

    public void realizarPagamento(String idBoleto) {
        if (idBoleto == null) {
            throw new IDBoletoInexistenteException("ID do boleto é inválido ou não foi informado.");
        }
        Boleto boleto = this.repositoryBoleto.getBoletoById(idBoleto);
        if (boleto == null) {
            throw new BoletoInexistenteException("Boleto não encontrado.");
        }
        this.updateDataPagamento(boleto);
        this.updateStatusBoleto(boleto, StatusBoletoEnum.PAGO);
    }
}