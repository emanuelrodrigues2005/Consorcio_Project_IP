package br.ufrpe.ip.projeto.repositories;

import br.ufrpe.ip.projeto.models.Contemplacao;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.repositories.interfaces.IContemplacaoRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContemplacaoRepository implements IContemplacaoRepository, Serializable {
    private static ContemplacaoRepository instance;
    private ArrayList<Contemplacao> contemplacoes;

    private ContemplacaoRepository() {
        this.contemplacoes = new ArrayList<>();
    }

    public static ContemplacaoRepository getInstance() {
        if (instance == null) {
            ContemplacaoRepository.instance = ContemplacaoRepository.lerArquivo();
        }
        return instance;
    }

    private static ContemplacaoRepository lerArquivo() {
        ContemplacaoRepository instanceLocal;

        File in = new File("contemplacoes.dat");
        FileInputStream fis;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(in);
            ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            instanceLocal = (ContemplacaoRepository) o;
        } catch (Exception e) {
            instanceLocal = new ContemplacaoRepository();
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
    public List<Contemplacao> getAllContemplacoes() {
        return Collections.unmodifiableList(contemplacoes);
    }

    @Override
    public void salvarArquivo() {
        if(ContemplacaoRepository.getInstance() == null) {
            return;
        }

        File out = new File("contemplacoes.dat");
        FileOutputStream fos;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(out);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ContemplacaoRepository.getInstance());
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
    public Contemplacao getContemplacaoByContrato(Contrato contrato) {
        for (Contemplacao contemplacao : contemplacoes) {
            if(contemplacao.getContratoContemplacao().getIdContrato() == contrato.getIdContrato()) {
                return contemplacao;
            }
        }
        return null;
    }

    @Override
    public Contemplacao getContemplacaoById(String idContemplacao) {
        for (Contemplacao contemplacao : contemplacoes) {
            if(contemplacao.getIdContemplacao().equals(idContemplacao)) {
                return contemplacao;
            }
        }
        return null;
    }

    @Override
    public void createContemplacao(Contrato contratoContemplcao) {
        Contemplacao contemplacao = new Contemplacao(contratoContemplcao);
        this.contemplacoes.add(contemplacao);
    }

    @Override
    public void updateDataContemplacao(String idContemplacao, LocalDate dataContemplacao) {
        Contemplacao contemplacao = getContemplacaoById(idContemplacao);
        contemplacao.setDataContemplacao(dataContemplacao);
    }

    @Override
    public void deleteContemplacao(String idContemplacao) {
        contemplacoes.remove(getContemplacaoById(idContemplacao));
    }
}
