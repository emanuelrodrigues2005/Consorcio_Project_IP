package br.ufrpe.ip.projeto.repositories.interfaces;

import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Contrato;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IBoletoRepository {
    ArrayList<Boleto> getAllBoletos();

    int getIdBoleto(Contrato contrato, int numeroParcela);

    Boleto getBoletoById(int idBoleto);

    Boleto getBoletoByContrato(Contrato contrato);

    Boleto createBoleto(Contrato contrato, LocalDate dataVencimento, int numeroParcela);

    void updateDataPagamento(Boleto boleto);

    void updateStatusBoleto(Boleto boleto, StatusBoletoEnum statusBoleto);

    void deleteBoleto(int idBoleto);
}