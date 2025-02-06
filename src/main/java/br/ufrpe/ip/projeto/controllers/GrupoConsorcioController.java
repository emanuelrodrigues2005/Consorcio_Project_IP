package br.ufrpe.ip.projeto.controllers;

import java.util.List;

import br.ufrpe.ip.projeto.enums.StatusGrupoConsorcioEnum;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import br.ufrpe.ip.projeto.repositories.GrupoConsorcioRepository;
import br.ufrpe.ip.projeto.repositories.interfaces.IGrupoConsorcioRepository;

public class GrupoConsorcioController {
    private static GrupoConsorcioController instancia;
    private final IGrupoConsorcioRepository repositorioGrupo;

    public GrupoConsorcioController() {
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

    public GrupoConsorcio getGrupoById(int idGrupo) {
        return this.repositorioGrupo.getGrupoById(idGrupo);
    }

    public List<GrupoConsorcio> getAllGrupos() {
        return this.repositorioGrupo.getAllGrupos();
    }

    public void updateParticipantes(GrupoConsorcio grupoConsorcio, int novoNumParticipantes) {
        if (grupoConsorcio.getNumeroParticipantes() != novoNumParticipantes) {
            this.repositorioGrupo.updateParticipantes(grupoConsorcio, novoNumParticipantes);   
        }
    }

    public void updateNomeGrupo(GrupoConsorcio grupoConsorcio, String novoNome) {
        if (grupoConsorcio.getNomeGrupo() != novoNome) {
            this.repositorioGrupo.updateNomeGrupo(grupoConsorcio, novoNome);
        }
    }

    public void updateTaxaAdmin(GrupoConsorcio grupoConsorcio, double novaTaxa) {
        if (grupoConsorcio.getTaxaAdmin() != novaTaxa && novaTaxa > 0) {
            this.repositorioGrupo.updateTaxaAdmin(grupoConsorcio, novaTaxa);
        }
    }

    public void updateStatusGrupo(GrupoConsorcio grupoConsorcio, StatusGrupoConsorcioEnum novoStatus) {
        if (grupoConsorcio.getStatusGrupoConsorcio() != novoStatus) {

        }
    }


    
}
