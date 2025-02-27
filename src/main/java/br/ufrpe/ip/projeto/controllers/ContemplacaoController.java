package br.ufrpe.ip.projeto.controllers;

import br.ufrpe.ip.projeto.enums.StatusContratoEnum;
import br.ufrpe.ip.projeto.exceptions.*;
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

    public List<Contemplacao> getAllContemplacoes() throws ArrayVazioException {
        List<Contemplacao> contemplacoes = this.repositoryContemplacao.getAllContemplacoes();
        if (contemplacoes.isEmpty()) {
            throw new ArrayVazioException("Nenhuma contemplação encontrada.");
        }
        return contemplacoes;
    } //exceptions: ArrayVazio

    public Contemplacao getContemplacaoById(String idContemplacao) throws ContemplacaoInexistenteException {
        Contemplacao contemplacao = this.repositoryContemplacao.getContemplacaoById(idContemplacao);
        if (contemplacao == null) {
            throw new ContemplacaoInexistenteException(idContemplacao);
        }
        return contemplacao;
    } //exceptions: ContemplacaoInexistente

    public Contemplacao createContemplacao(Contrato contratoContemplacao) throws CampoInvalidoException, ContemplacaoInexistenteException {
        if (contratoContemplacao == null || contratoContemplacao.getIdContrato() == null) {
            throw new CampoInvalidoException("Contrato de contemplação");
        }
        if (this.repositoryContemplacao.getContemplacaoById(contratoContemplacao.getIdContrato()) != null) {
            throw new ContemplacaoInexistenteException(contratoContemplacao.getIdContrato());
        }
        Contemplacao contemplacao = this.repositoryContemplacao.createContemplacao(contratoContemplacao);
        this.repositoryContemplacao.salvarArquivo();
        return contemplacao;
    } //exceptions: CampoInvalido, ContemplacaoExistente

    public void updateDataContemplacao(String idContemplacao, LocalDate dataContemplacao) throws ContemplacaoInexistenteException, DataContemplacaoInvalidaException {
        if (this.repositoryContemplacao.getContemplacaoById(idContemplacao) == null) {
            throw new ContemplacaoInexistenteException(idContemplacao);
        }
        if (dataContemplacao.isAfter(LocalDate.now())) {
            throw new DataContemplacaoInvalidaException("A data de contemplação não pode ser futura.");
        }
        this.repositoryContemplacao.updateDataContemplacao(idContemplacao, dataContemplacao);
        this.repositoryContemplacao.salvarArquivo();
    } //exceptions: ContemplacaoInexistente, DataContemplacaoInvalida

    public void deleteContemplacao(String idContemplacao) throws ContemplacaoInexistenteException {
        if (this.repositoryContemplacao.getContemplacaoById(idContemplacao) == null) {
            throw new ContemplacaoInexistenteException(idContemplacao);
        }
        this.repositoryContemplacao.deleteContemplacao(idContemplacao);
        this.repositoryContemplacao.salvarArquivo();
    } //exceptions: ContemplacaoInexistente

    public String sorteioContemplacao() throws ArrayVazioException, ContratoInvalidoException, ContemplacaoInexistenteException, CampoInvalidoException {
        ContratoController contratoController = ContratoController.getInstancia();
        List<Contrato> contratosAtivos = new ArrayList<>();

        for (Contrato contrato : contratoController.getAllContratos()) {
            if (contrato.getStatusContrato().equals(StatusContratoEnum.ATIVO)) {
                contratosAtivos.add(contrato);
            }
        }
        if (contratosAtivos.isEmpty()) {
            throw new ArrayVazioException("Não há contratos ativos para contemplação.");
        }
        Random random = new Random();
        Contrato contratoSorteado = contratosAtivos.get(random.nextInt(contratosAtivos.size()));

        if (contratoSorteado == null) {
            throw new ContratoInvalidoException("Contrato sorteado é inexistente.");
        }

        Contemplacao contemplacao = this.createContemplacao(contratoSorteado);
        contratoController.updateStatusContrato(contratoSorteado, StatusContratoEnum.CONTEMPLADO);
        this.repositoryContemplacao.salvarArquivo();

        return contemplacao.getIdContemplacao();
    } //exceptions: ContratoInexistente, ArrayVazio
}
