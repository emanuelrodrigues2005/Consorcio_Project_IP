package br.ufrpe.ip.projeto.controllers;

import br.ufrpe.ip.projeto.enums.StatusContratoEnum;
import br.ufrpe.ip.projeto.models.Contemplacao;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.repositories.ContemplacaoRepository;
import br.ufrpe.ip.projeto.repositories.interfaces.IContemplacaoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContemplacaoController {
    private static ContemplacaoController instance;
    private final IContemplacaoRepository repositoryContemplacao;

    private  ContemplacaoController() {
        this.repositoryContemplacao = ContemplacaoRepository.getInstance();
    }

    public static ContemplacaoController getInstance() {
        if (instance == null) {
            instance = new ContemplacaoController();
        }
        return instance;
    }

    public List<Contemplacao> getAllContemplacoes() {
        return this.repositoryContemplacao.getAllContemplacoes();
    } //exceptions: ArrayVazio

    public Contemplacao getContemplacaoById(String idContemplacao) {
        return this.repositoryContemplacao.getContemplacaoById(idContemplacao);
    } //exceptions: ContemplacaoInexistente

    public void createContemplacao(Contrato contratoContemplacao) {
        this.repositoryContemplacao.createContemplacao(contratoContemplacao);
    } //exceptions: CampoInvalido, ContemplacaoExistente

    public void updateDataContemplacao(String idContemplacao, LocalDate dataContemplacao) {
        this.repositoryContemplacao.updateDataContemplacao(idContemplacao, dataContemplacao);
    } //exceptions: ContemplacaoInexistente, DataContemplacaoInvalida

    public void deleteContemplacao(String idContemplacao) {
        this.repositoryContemplacao.deleteContemplacao(idContemplacao);
    } //exceptions: ContemplacaoInexistente

    public String sorteioContemplacao() {
        ContratoController contratoController = ContratoController.getInstancia();
        List<Contrato> contratosAtivos = new ArrayList<>();

        for (Contrato contrato : contratoController.getAllContratos()) {
            if (contrato.getStatusContrato().equals(StatusContratoEnum.ATIVO)) {
                contratosAtivos.add(contrato);
            }
        }

        if (contratosAtivos.isEmpty()) {
            return null;
        }

        Random random = new Random();
        Contrato contratoSorteado = contratosAtivos.get(random.nextInt(contratosAtivos.size()));
        this.createContemplacao(contratoSorteado);
        contratoController.updateStatusContrato(contratoSorteado, StatusContratoEnum.CONTEMPLADO);

        return contratoSorteado.getIdContrato();
    } //exceptions: ContratoInexistente, ArrayVazio
}
