package br.ufrpe.ip.projeto.repositories.interfaces;

import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Contrato;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface IBoletoRepository {
    List<Boleto> getAllBoletos();

    void salvarArquivo();

    String getIdBoleto(Contrato contrato, int numeroParcela);

    Boleto getBoletoById(String idBoleto);

    Boleto getBoletoByContrato(Contrato contrato);

    Boleto createBoleto(Contrato contrato, LocalDate dataVencimento, int numeroParcela);

    void updateDataPagamento(Boleto boleto);

    void updateStatusBoleto(Boleto boleto, StatusBoletoEnum statusBoleto);

    void deleteBoleto(String idBoleto);
}