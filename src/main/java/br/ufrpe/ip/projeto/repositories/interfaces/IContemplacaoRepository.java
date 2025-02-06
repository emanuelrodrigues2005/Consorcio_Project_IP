package br.ufrpe.ip.projeto.repositories.interfaces;

import br.ufrpe.ip.projeto.models.Contemplacao;
import br.ufrpe.ip.projeto.models.Contrato;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IContemplacaoRepository {
    ArrayList<Contemplacao> getAllContemplacoes();

    Contemplacao getContemplacaoByContrato(Contrato contrato);

    Contemplacao getContemplacaoById(int idContemplacao);

    void createContemplacao(Contrato contratoContemplacao);

    void updateDataContemplacao(int idContemplacao, LocalDate dataContemplacao);

    void deleteContemplacao(int idContemplacao);
}
