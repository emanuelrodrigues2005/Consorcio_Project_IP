package br.ufrpe.ip.projeto.repositories.interfaces;

import br.ufrpe.ip.projeto.models.Contemplacao;
import br.ufrpe.ip.projeto.models.Contrato;

import java.time.LocalDate;
import java.util.List;

public interface IContemplacaoRepository {
    List<Contemplacao> getAllContemplacoes();

    Contemplacao getContemplacaoByContrato(Contrato contrato);

    Contemplacao getContemplacaoById(String idContemplacao);

    void createContemplacao(Contrato contratoContemplacao);

    void updateDataContemplacao(String idContemplacao, LocalDate dataContemplacao);

    void deleteContemplacao(String idContemplacao);
}
