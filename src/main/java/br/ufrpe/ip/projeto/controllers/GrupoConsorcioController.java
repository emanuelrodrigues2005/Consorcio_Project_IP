package br.ufrpe.ip.projeto.controllers;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.ip.projeto.enums.StatusContratoEnum;
import br.ufrpe.ip.projeto.enums.StatusGrupoConsorcioEnum;
import br.ufrpe.ip.projeto.exceptions.*;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import br.ufrpe.ip.projeto.repositories.ContratoRepository;
import br.ufrpe.ip.projeto.repositories.GrupoConsorcioRepository;
import br.ufrpe.ip.projeto.repositories.interfaces.IContratoRepository;
import br.ufrpe.ip.projeto.repositories.interfaces.IGrupoConsorcioRepository;

public class GrupoConsorcioController {
    private static GrupoConsorcioController instancia;
    private final IGrupoConsorcioRepository repositorioGrupo;

    private GrupoConsorcioController() {
        this.repositorioGrupo = GrupoConsorcioRepository.getInstancia();
    }

    public static GrupoConsorcioController getInstancia() {
        if (instancia == null) {
            instancia = new GrupoConsorcioController();
        }
        return instancia;
    }

    public void createGrupoConsorcio(String nomeGrupo, int numeroMaximoParticipantes, double valorTotal, double taxaAdmin) throws CampoInvalidoException {
        List<String> camposInvalidos = new ArrayList<>();
        if (numeroMaximoParticipantes <= 0) {
            camposInvalidos.add("Número máximo de participantes");
        }

        if (valorTotal <= 0) {
            camposInvalidos.add("Valor total");
        }

        if (taxaAdmin <= 0){
            camposInvalidos.add("Taxa administrativa");
        }

        if (!camposInvalidos.isEmpty()) {
            throw new CampoInvalidoException(camposInvalidos);
        }
        this.repositorioGrupo.createGrupoConsorcio(nomeGrupo, numeroMaximoParticipantes, valorTotal, taxaAdmin);
        this.repositorioGrupo.salvarArquivo();
    }


    public GrupoConsorcio getGrupoById(String idGrupo) throws IdGrupoConsorcioInexistenteException {
        GrupoConsorcio grupo = this.repositorioGrupo.getGrupoById(idGrupo);
        if (grupo == null) {
            throw new IdGrupoConsorcioInexistenteException(idGrupo);
        }
        return grupo;
    } //IdGrupoConsorcioInexistente

    public List<GrupoConsorcio> getAllGrupos() throws ArrayVazioException {
        List<GrupoConsorcio> grupos = this.repositorioGrupo.getAllGrupos();
        if (grupos.isEmpty()) {
            throw new ArrayVazioException("Não há grupos de consórcio cadastrados.");
        }
        return grupos;
    } //ArrayVazio

    public void updateParticipantes(GrupoConsorcio grupoConsorcio, int novoNumParticipantes) throws GrupoConsorcioInexistenteException, CampoInvalidoException {
        if (grupoConsorcio == null) {
            throw new GrupoConsorcioInexistenteException("Grupo não encontrado");
        }
        if (novoNumParticipantes < 0) {
            throw new CampoInvalidoException("Número de participantes");
        }
        this.repositorioGrupo.updateParticipantes(grupoConsorcio, novoNumParticipantes);
    } //GrupoConsorcioInexistente, CampoInvalido

    public void updateNomeGrupo(GrupoConsorcio grupoConsorcio, String novoNome) throws GrupoConsorcioInexistenteException, CampoInvalidoException {
        if (grupoConsorcio == null) {
            throw new GrupoConsorcioInexistenteException("Grupo não encontrado");
        }
        if (novoNome == null || novoNome.isEmpty()) {
            throw new CampoInvalidoException("Nome do grupo inválido");
        }
        this.repositorioGrupo.updateNomeGrupo(grupoConsorcio, novoNome);
        this.repositorioGrupo.salvarArquivo();
    } //GrupoConsorcioInexistente, CampoInvalido

    public void updateTaxaAdmin(GrupoConsorcio grupoConsorcio, double novaTaxa) throws GrupoConsorcioInexistenteException, CampoInvalidoException {
        if (grupoConsorcio == null) {
            throw new GrupoConsorcioInexistenteException("Grupo não encontrado");
        }
        if (novaTaxa <= 0) {
            throw new CampoInvalidoException("Taxa administrativa inválida");
        }
        this.repositorioGrupo.updateTaxaAdmin(grupoConsorcio, novaTaxa);
        this.repositorioGrupo.salvarArquivo();
    } //GrupoConsorcioInexistente, CampoInvalido

    public void updateStatusGrupo(GrupoConsorcio grupoConsorcio, StatusGrupoConsorcioEnum novoStatus) {
        if (grupoConsorcio.getStatusGrupoConsorcio() != novoStatus && statusValido(novoStatus)) {
            this.repositorioGrupo.updateStatusGrupo(grupoConsorcio, novoStatus);
            this.repositorioGrupo.salvarArquivo();
        }
    } //GrupoConsorcioInexistente, CampoInvalido

    private boolean statusValido(StatusGrupoConsorcioEnum status) {
        switch (status) {
            case StatusGrupoConsorcioEnum.ATIVO: return true;
            case StatusGrupoConsorcioEnum.ENCERRADO: return true;
            default: return false;
        }
    } //CampoInvalido

    public void deleteGrupoConsorcio(GrupoConsorcio grupoConsorcio) throws GrupoConsorcioInexistenteException {
        if (grupoConsorcio == null || !this.repositorioGrupo.getAllGrupos().contains(grupoConsorcio)) {
            throw new GrupoConsorcioInexistenteException("Grupo não encontrado para exclusão");
        }
        this.repositorioGrupo.deleteGrupoConsorcio(grupoConsorcio);
        this.repositorioGrupo.salvarArquivo();
    } //GrupoConsorcioInexistente

    public void reajusteParcela(GrupoConsorcio grupoConsorcio) {
        IContratoRepository repositorioContratos = ContratoRepository.getInstancia();
        int desistentes = 0;
        for (Contrato contrato : repositorioContratos.getAllContratos()) {
            if (contrato.getGrupoAssociado().getIdGrupo().equals(grupoConsorcio.getIdGrupo()) && contrato.getStatusContrato() == StatusContratoEnum.ENCERRADO) {
                desistentes++;
            }
        }
        if (desistentes > 0) {
            int novoNumParticipantes = grupoConsorcio.getNumeroParticipantes() - desistentes;
            this.repositorioGrupo.updateParticipantes(grupoConsorcio, novoNumParticipantes);
            double novoValorParcela = ((grupoConsorcio.getValorTotal() + grupoConsorcio.getValorTotal() * grupoConsorcio.getTaxaAdmin()) / novoNumParticipantes);
            this.repositorioGrupo.updateValorParcela(grupoConsorcio, novoValorParcela);
            this.repositorioGrupo.salvarArquivo();
        }
    } //GrupoConsorcioInexistente

    public double getValorPago(String idGrupo) throws IdGrupoConsorcioInexistenteException {
        GrupoConsorcio grupo = this.repositorioGrupo.getGrupoById(idGrupo);
        if (grupo == null) {
            throw new IdGrupoConsorcioInexistenteException(idGrupo);
        }

        IContratoRepository repositorioContratos = ContratoRepository.getInstancia();
        double somaValorPago = 0;
        for (Contrato contrato : repositorioContratos.getContratosByIdGrupo(grupo)) {
            somaValorPago += contrato.getValorPago();
        }
        return somaValorPago;
    } //IdGrupoInvalido
}