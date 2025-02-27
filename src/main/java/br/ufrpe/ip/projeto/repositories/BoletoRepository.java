package br.ufrpe.ip.projeto.repositories;

import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.repositories.interfaces.IBoletoRepository;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoletoRepository implements IBoletoRepository, Serializable {
    private static BoletoRepository instance;
    private ArrayList<Boleto> boletos;

    private BoletoRepository() {
        this.boletos = new ArrayList<>();
    }

    public static BoletoRepository getInstance() {
        if (instance == null) {
            BoletoRepository.instance = BoletoRepository.lerArquivo();
        }
        return BoletoRepository.instance;
    }

    @Override
    public List<Boleto> getAllBoletos() {
        return Collections.unmodifiableList(boletos);
    }

    @Override
    public List<Boleto> getAllBoletosByContrato(String idContrato) {
        List<Boleto> newboletos = new ArrayList<>();
        for (Boleto boleto : boletos) {
            if (boleto.getContratoBoleto().getIdContrato().equalsIgnoreCase(idContrato))  {
                newboletos.add(boleto);
            }
        }
        return newboletos;
    }

    private static BoletoRepository lerArquivo() {
        BoletoRepository instanceLocal;

        File in = new File("boletos.dat");
        FileInputStream fis;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(in);
            ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            instanceLocal = (BoletoRepository) o;
        } catch (Exception e) {
            instanceLocal = new BoletoRepository();
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
        if(BoletoRepository.getInstance() == null) {
            return;
        }

        File out = new File("boletos.dat");
        FileOutputStream fos;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(out);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(BoletoRepository.getInstance());
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
    public String getIdBoleto(Contrato contrato, int numeroParcela) {
        for (Boleto boleto : boletos) {
            if (boleto.getContratoBoleto().equals(contrato) &&
                    boleto.getContratoBoleto().getCliente().getCpf().equals(contrato.getCliente().getCpf()) &&
                    boleto.getNumeroParcela() == numeroParcela) {
                    return boleto.getIdBoleto();
            }
        }
        return null;
    }

    @Override
    public Boleto getBoletoById(String idBoleto) {
        for (Boleto boleto : boletos) {
            if (boleto.getIdBoleto().equals(idBoleto)) {
                return boleto;
            }
        }
        return null;
    }

    @Override
    public Boleto getBoletoByContrato(Contrato contrato) {
        for (Boleto boleto : boletos) {
            if(boleto.getContratoBoleto() != null && contrato != null &&
                    boleto.getContratoBoleto().getIdContrato().equals(contrato.getIdContrato())) {
                return boleto;
            }
        }
        return null;
    }

    @Override
    public Boleto createBoleto(Contrato contrato) {
        Boleto boleto = new Boleto(contrato);
        boletos.add(boleto);
        return boleto;
    }

    @Override
    public void updateDataPagamento(Boleto boleto) {
        boleto.setDataPagamento(LocalDate.now());
    }

    @Override
    public void updateStatusBoleto(Boleto boleto, StatusBoletoEnum statusBoleto) {
        boleto.setStatusBoleto(statusBoleto);
    }

    @Override
    public void deleteBoleto(String idBoleto) {
        boletos.remove(getBoletoById(idBoleto));
    }
}