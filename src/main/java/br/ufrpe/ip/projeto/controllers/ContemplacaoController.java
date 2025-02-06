package br.ufrpe.ip.projeto.controllers;

import br.ufrpe.ip.projeto.models.Contemplacao;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.repositories.ContemplacaoRepository;
import br.ufrpe.ip.projeto.repositories.interfaces.IContemplacaoRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class ContemplacaoController {
    private static ContemplacaoController instance;
    private final IContemplacaoRepository repositoryContemplacao;

    public ContemplacaoController() {
        this.repositoryContemplacao = ContemplacaoRepository.getInstance();
    }

    public static ContemplacaoController getInstance() {
        if (instance == null) {
            instance = new ContemplacaoController();
        }
        return instance;
    }

    public ArrayList<Contemplacao> getAllContemplacoes() {
        return this.repositoryContemplacao.getAllContemplacoes();
    } //exceptions: ArrayVazio

    public Contemplacao getContemplacaoById(int idContemplacao) {
        return this.repositoryContemplacao.getContemplacaoById(idContemplacao);
    } //exceptions: ContemplacaoInexistente

    public void createContemplacao(Contrato contratoContemplacao) {
        this.repositoryContemplacao.createContemplacao(contratoContemplacao);
    } //exceptions: CampoInvalido, ContemplacaoExistente

    public void updateDataContemplacao(int idContemplacao, LocalDate dataContemplacao) {
        this.repositoryContemplacao.updateDataContemplacao(idContemplacao, dataContemplacao);
    } //exceptions: ContemplacaoInexistente, DataContemplacaoInvalida

    public void deleteContemplacao(int idContemplacao) {
        this.repositoryContemplacao.deleteContemplacao(idContemplacao);
    } //exceptions: ContemplacaoInexistente
}
