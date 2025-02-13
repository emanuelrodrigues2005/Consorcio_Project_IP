package br.ufrpe.ip.projeto.controllers;

import java.util.List;

import br.ufrpe.ip.projeto.enums.StatusContratoEnum;
import br.ufrpe.ip.projeto.enums.StatusGrupoConsorcioEnum;
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

    public void createGrupoConsorcio(String nomeGrupo, int numeroParticipantes, double valorTotal, double taxaAdmin) {
        if (numeroParticipantes > 0 && valorTotal > 0 && taxaAdmin > 0) {
            this.repositorioGrupo.createGrupoConsorcio(nomeGrupo, numeroParticipantes, valorTotal, taxaAdmin);
        }
        // throw campoInvalido;
    }

    public GrupoConsorcio getGrupoById(String idGrupo) {
        return this.repositorioGrupo.getGrupoById(idGrupo);
    } //IdGrupoConsorcioInexistente

    public List<GrupoConsorcio> getAllGrupos() {
        return this.repositorioGrupo.getAllGrupos();
    } //ArrayVazio

    public void updateParticipantes(GrupoConsorcio grupoConsorcio, int novoNumParticipantes) {
        if (grupoConsorcio.getNumeroParticipantes() != novoNumParticipantes) {
            this.repositorioGrupo.updateParticipantes(grupoConsorcio, novoNumParticipantes);   
        }
    } //GrupoConsorcioInexistente, CampoInvalido

    public void updateNomeGrupo(GrupoConsorcio grupoConsorcio, String novoNome) {
        if (grupoConsorcio.getNomeGrupo().equals(novoNome)) {
            this.repositorioGrupo.updateNomeGrupo(grupoConsorcio, novoNome);
        }
    } //GrupoConsorcioInexistente, CampoInvalido

    public void updateTaxaAdmin(GrupoConsorcio grupoConsorcio, double novaTaxa) {
        if (grupoConsorcio.getTaxaAdmin() != novaTaxa && novaTaxa > 0) {
            this.repositorioGrupo.updateTaxaAdmin(grupoConsorcio, novaTaxa);
        }
    } //GrupoConsorcioInexistente, CampoInvalido

    public void updateStatusGrupo(GrupoConsorcio grupoConsorcio, StatusGrupoConsorcioEnum novoStatus) {
        if (grupoConsorcio.getStatusGrupoConsorcio() != novoStatus && statusValido(novoStatus)) {
            this.repositorioGrupo.updateStatusGrupo(grupoConsorcio, novoStatus);
        }
    } //GrupoConsorcioInexistente, CampoInvalido

    private boolean statusValido(StatusGrupoConsorcioEnum status) {
        switch (status) {
            case StatusGrupoConsorcioEnum.ATIVO: return true;
            case StatusGrupoConsorcioEnum.ENCERRADO: return true;
            default: return false;
        }
    } //CampoInvalido

    public void deleteGrupoConsorcio(GrupoConsorcio grupoConsorcio) {
        if (this.repositorioGrupo.getAllGrupos().contains(grupoConsorcio)) {
            this.repositorioGrupo.deleteGrupoConsorcio(grupoConsorcio); //fornecer idGrupo
        }
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
        }
    } //GrupoConsorcioInexistente

    public double getValorPago(String idGrupo) {
        IContratoRepository repositorioContratos = ContratoRepository.getInstancia();
        double somaValorPago = 0;

        for(Contrato contrato : repositorioContratos.getContratosByIdGrupo(repositorioGrupo.getGrupoById(idGrupo))) {
            somaValorPago = somaValorPago + contrato.getValorPago();
        }
        return somaValorPago;
    } //IdGrupoInvalido
}