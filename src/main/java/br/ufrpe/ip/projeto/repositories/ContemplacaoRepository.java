package br.ufrpe.ip.projeto.repositories;

import br.ufrpe.ip.projeto.models.Contemplacao;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.repositories.interfaces.IContemplacaoRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContemplacaoRepository implements IContemplacaoRepository {
    private static ContemplacaoRepository instance;
    private ArrayList<Contemplacao> contemplacoes;

    public ContemplacaoRepository() {
        this.contemplacoes = new ArrayList<>();
    }

    public static ContemplacaoRepository getInstance() {
        if (instance == null) {
            instance = new ContemplacaoRepository();
        }
        return instance;
    }

    @Override
    public ArrayList<Contemplacao> getAllContemplacoes() {
        if (contemplacoes == null) {
            return null;
        }
        return contemplacoes;
    }

    @Override
    public Contemplacao getContemplacaoByContrato(Contrato contrato) {
        for (Contemplacao contemplacao : contemplacoes) {
            if(contemplacao.getContratoContemplacao().equals(contrato)) {
                return contemplacao;
            }
        }
        return null;
    }

    @Override
    public Contemplacao getContemplacaoById(int idContemplacao) {
        for (Contemplacao contemplacao : contemplacoes) {
            if(contemplacao.getIdContemplacao() == idContemplacao) {
                return contemplacao;
            }
        }
        return null;
    }

    @Override
    public Contemplacao createContemplacao(Contrato contratoContemplcao) {
        Contemplacao contemplacao = new Contemplacao(contratoContemplcao);
        contemplacoes.add(contemplacao);
        return contemplacao;
    }

    @Override
    public void updateDataContemplacao(int idContemplacao, LocalDate dataContemplacao) {
        Contemplacao contemplacao = getContemplacaoById(idContemplacao);
        contemplacao.setDataContemplacao(dataContemplacao);
    }

    @Override
    public void deleteContemplacao(int idContemplacao) {
        contemplacoes.remove(getContemplacaoById(idContemplacao));
    }
}
