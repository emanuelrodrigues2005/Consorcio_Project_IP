package br.ufrpe.ip.projeto.repositories;

import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.repositories.interfaces.IBoletoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoletoRepository implements IBoletoRepository {
    private static BoletoRepository instance;
    private ArrayList<Boleto> boletos;

    private BoletoRepository() {
        this.boletos = new ArrayList<>();
    }

    public static BoletoRepository getInstance() {
        if (instance == null) {
            instance = new BoletoRepository();
        }
        return instance;
    }

    @Override
    public List<Boleto> getAllBoletos() {
        return Collections.unmodifiableList(boletos);
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
            if(boleto.getContratoBoleto().getIdContrato().equals(contrato.getIdContrato())) {
                return boleto;
            }
        }
        return null;
    }

    @Override
    public Boleto createBoleto(Contrato contrato, LocalDate dataVencimento, int numeroParcela) {
        Boleto boleto = new Boleto(contrato, dataVencimento, numeroParcela);
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