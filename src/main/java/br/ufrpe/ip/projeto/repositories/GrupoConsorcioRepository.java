package br.ufrpe.ip.projeto.repositories;

import br.ufrpe.ip.projeto.enums.StatusGrupoConsorcioEnum;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import br.ufrpe.ip.projeto.repositories.interfaces.IGrupoConsorcioRepository;

import java.util.ArrayList;
import java.util.List;

public class GrupoConsorcioRepository implements IGrupoConsorcioRepository {
    private static GrupoConsorcioRepository instancia;
    private ArrayList<GrupoConsorcio> grupos;

    private GrupoConsorcioRepository() {
        this.grupos = new ArrayList<>();
    }

    public static GrupoConsorcioRepository getInstancia() {
        if (instancia == null) {
            instancia = new GrupoConsorcioRepository();
        }
        return instancia;
    }
    @Override
    public List<GrupoConsorcio> getAllGrupos() {
        return grupos;
    }
    @Override
    public GrupoConsorcio getGrupoById(int idGrupo) {
        for (GrupoConsorcio grupo : grupos) {
            if (grupo.getIdGrupo() == idGrupo) {
                System.out.println(grupo);
                return grupo;
            }
        }
        return null;
    }
    @Override
    public void createGrupoConsorcio(String nomeGrupo, int numeroParticipantes, double valorTotal, double taxaAdmin) {
        GrupoConsorcio grupoConsorcio = new GrupoConsorcio(nomeGrupo, numeroParticipantes, valorTotal, taxaAdmin);
        grupos.add(grupoConsorcio);
    }
    @Override
    public void updateParticipantes(GrupoConsorcio grupoConsorcio, int novoNumParticipantes) {
        grupoConsorcio.setNumeroParticipantes(novoNumParticipantes);
    }
    @Override
    public void updateNomeGrupo(GrupoConsorcio grupoConsorcio, String novoNome) {
        grupoConsorcio.setNomeGrupo(novoNome);
    }
    @Override
    public void updateTaxaAdmin(GrupoConsorcio grupoConsorcio, double novaTaxa) {
        grupoConsorcio.setTaxaAdmin(novaTaxa);
    }
    @Override
    public void updateStatusGrupo(GrupoConsorcio grupoConsorcio, StatusGrupoConsorcioEnum novoStatus) {
        grupoConsorcio.setStatusGrupoConsorcio(novoStatus);
    }
    @Override
    public void updateValorParcela(GrupoConsorcio grupoConsorcio, double novoValor) {
        grupoConsorcio.setValorParcela(novoValor);
    }
    @Override
    public void deleteGrupoConsorcio(GrupoConsorcio grupoConsorcio) {
        grupos.remove(grupoConsorcio);
    }
}
