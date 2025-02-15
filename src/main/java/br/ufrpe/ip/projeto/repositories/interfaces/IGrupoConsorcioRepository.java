package br.ufrpe.ip.projeto.repositories.interfaces;

import java.util.List;

import br.ufrpe.ip.projeto.enums.StatusGrupoConsorcioEnum;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;

public interface IGrupoConsorcioRepository {

    List<GrupoConsorcio> getAllGrupos();

    GrupoConsorcio getGrupoById(String idGrupo);

    void createGrupoConsorcio(String nomeGrupo, int numeroMaximoParticipantes,double valorTotal, double taxaAdmin);

    void updateParticipantes(GrupoConsorcio grupoConsorcio, int novoNumParticipantes);

    void updateNomeGrupo(GrupoConsorcio grupoConsorcio, String novoNome);

    void updateTaxaAdmin(GrupoConsorcio grupoConsorcio, double novaTaxa);

    void updateStatusGrupo(GrupoConsorcio grupoConsorcio, StatusGrupoConsorcioEnum novoStatus);

    void deleteGrupoConsorcio(GrupoConsorcio grupoConsorcio);

    void updateValorParcela(GrupoConsorcio grupoConsorcio, double novoValor);

}
