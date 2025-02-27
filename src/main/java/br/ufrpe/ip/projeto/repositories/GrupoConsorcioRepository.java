package br.ufrpe.ip.projeto.repositories;

import br.ufrpe.ip.projeto.enums.StatusGrupoConsorcioEnum;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import br.ufrpe.ip.projeto.repositories.interfaces.IGrupoConsorcioRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrupoConsorcioRepository implements IGrupoConsorcioRepository, Serializable {
    private static GrupoConsorcioRepository instancia;
    private ArrayList<GrupoConsorcio> grupos;

    private GrupoConsorcioRepository() {
        this.grupos = new ArrayList<>();
    }

    public static GrupoConsorcioRepository getInstancia() {
        if (instancia == null) {
            GrupoConsorcioRepository.instancia = GrupoConsorcioRepository.lerArquivo();
        }
        return GrupoConsorcioRepository.instancia;
    }

    private static GrupoConsorcioRepository lerArquivo() {
        GrupoConsorcioRepository instanceLocal;

        File in = new File("grupos.dat");
        FileInputStream fis;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(in);
            ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            instanceLocal = (GrupoConsorcioRepository) o;
        } catch (Exception e) {
            instanceLocal = new GrupoConsorcioRepository();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    //ver oq colocar aqui
                }
            }
        }
        return instanceLocal;
    }

    @Override
    public void salvarArquivo() {
        if(GrupoConsorcioRepository.getInstancia() == null) {
            return;
        }

        File out = new File("grupos.dat");
        FileOutputStream fos;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(out);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(GrupoConsorcioRepository.getInstancia());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    //Ver oq colocar aqui
                }
            }
        }
    }

    @Override
    public List<GrupoConsorcio> getAllGrupos() {
        return Collections.unmodifiableList(grupos);
    }

    @Override
    public GrupoConsorcio getGrupoById(String idGrupo) {
        for (GrupoConsorcio grupo : grupos) {
            if (grupo.getIdGrupo().equals(idGrupo)) {
                System.out.println(grupo);
                return grupo;
            }
        }
        return null;
    }

    @Override
    public void createGrupoConsorcio(String nomeGrupo, int numeroMaximoParticipantes,double valorTotal, double taxaAdmin) {
        GrupoConsorcio grupoConsorcio = new GrupoConsorcio(nomeGrupo, numeroMaximoParticipantes,valorTotal, taxaAdmin);
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
