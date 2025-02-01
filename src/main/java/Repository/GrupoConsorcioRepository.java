package Repository;

import Enums.StatusGrupoConsorcioEnum;
import Models.GrupoConsorcio;
import java.util.ArrayList;

public class GrupoConsorcioRepository {
    private ArrayList<GrupoConsorcio> grupos;

    public GrupoConsorcioRepository() {
        this.grupos = new ArrayList<>();
    }

    public void getAllGrupos() {
        if (grupos.isEmpty()) {
            System.out.println("\nNenhum grupo foi criado!\n");
        } else {
            System.out.println("\nLista de grupos:");
            for (GrupoConsorcio grupo : grupos) {
                System.out.printf("- %s (participantes: %d)\n", grupo.getNomeGrupo(), grupo.getNumeroParticipantes());
            }
        }
    }

    public GrupoConsorcio getGrupoByName(String nomeGrupo) {
        for (GrupoConsorcio grupo : grupos) {
            if (grupo.getNomeGrupo().equalsIgnoreCase(nomeGrupo)) {
                System.out.println(grupo);
                return grupo;
            }
        }
        return null;
    }

    public GrupoConsorcio createGrupoConsorcio(String nomeGrupo, int numeroParticipantes, double valorTotal, double taxaAdmin) {
        if(getGrupoByName(nomeGrupo) == null){
            GrupoConsorcio grupoConsorcio = new GrupoConsorcio(nomeGrupo, numeroParticipantes, valorTotal, taxaAdmin);

            grupos.add(grupoConsorcio);

            System.out.println(grupoConsorcio);

            System.out.printf("Grupo '%s' criado e adicionado com sucesso!\n\n", grupoConsorcio.getNomeGrupo());
            return grupoConsorcio;
        }
        return null;
    }

    public GrupoConsorcio updateGrupoConsorcio(
            String nomeGrupo,
            int novoNumeroParticipantes,
            double novoValorTotal,
            double novoTaxaAdmin,
            StatusGrupoConsorcioEnum statusGrupoConsorcio
    ) {
        GrupoConsorcio grupoTemp = getGrupoByName(nomeGrupo);

        if (grupoTemp != null) {
            if (novoNumeroParticipantes > 0) {
                grupoTemp.setNumeroParticipantes(novoNumeroParticipantes);
            }

            if (novoValorTotal > 0) {
                grupoTemp.setValorTotal(novoValorTotal);
            }

            if (novoTaxaAdmin >= 0) {
                grupoTemp.setTaxaAdmin(novoTaxaAdmin);
            }

            if (statusGrupoConsorcio != null) {
                grupoTemp.setStatusGrupoConsorcio(statusGrupoConsorcio);
            }

            double novoValorParcela = (novoValorTotal + (novoValorTotal * novoTaxaAdmin)) / novoNumeroParticipantes;

            if (novoValorParcela > 0) {
                grupoTemp.setValorParcela(novoValorParcela);
            }

            System.out.println(grupoTemp);
            System.out.printf("\nGrupo '%s' atualizado com sucesso!\n", grupoTemp.getNomeGrupo());
            return grupoTemp;
        } else {
            System.out.println("Erro: Grupo não encontrado para atualização!");
        }
        return null;
    }

    public void deleteGrupoConsorcio(String nomeGrupo) {
        GrupoConsorcio grupoTemp = getGrupoByName(nomeGrupo);

        if (grupoTemp != null) {
            grupos.remove(grupoTemp);
            System.out.printf("Grupo '%s' deletado com sucesso!\n", grupoTemp.getNomeGrupo());
        } else {
            System.out.println("Grupo não encontrado para exclusão!");
        }
    }
}
