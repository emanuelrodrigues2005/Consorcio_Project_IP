package br.ufrpe.ip.projeto.repositories.interfaces;

import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Contrato;

import java.time.LocalDate;

public interface IBoletoRepository {
    int getIdBoleto(Contrato contrato, int numeroParcela);

    Boleto getBoletoById(int idBoleto);

    Boleto createBoleto(Contrato contrato, LocalDate dataEmissao, LocalDate dataVencimento, int numeroParcela);

    void updateDataVencimento(Boleto boleto, LocalDate dataVencimento);

    void updateDataEmissao(Boleto boleto, LocalDate dataEmissao);

    void updateDataPagamento(Boleto boleto, LocalDate dataPagamento);

    void updateStatusBoleto(Boleto boleto, StatusBoletoEnum statusBoleto);

    void deleteBoleto(int idBoleto);
}