package br.ufrpe.ip.projeto.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import br.ufrpe.ip.projeto.repositories.interfaces.IContratoRepository;
import br.ufrpe.ip.projeto.enums.StatusContratoEnum;
import br.ufrpe.ip.projeto.models.Cliente;

public class ContratoRepository implements IContratoRepository{
    private static ContratoRepository instancia;
    private ArrayList<Contrato> contratos;
    

    public ContratoRepository() {
        this.contratos = new ArrayList<>();
    }
    
    public static ContratoRepository getInstancia() {
        if (instancia == null) {
            instancia = new ContratoRepository();
        }
        return instancia;
    }
    
    @Override
    public List<Contrato> getAllContratos() {
        return contratos;
    } // exceptions: semContratosRegistrados

    @Override
    public List<Contrato> getAllContratosByCPF(Cliente cliente) {
        List<Contrato> contratosCliente = new ArrayList<>();
        for (Contrato contrato : contratos) {
            if (contrato.getCliente().getCpf().equalsIgnoreCase(cliente.getCpf())) {
                contratosCliente.add(contrato);
            }
        }
        return contratosCliente;
    } // exceptions: clienteInvalido

    @Override
    public Contrato getContratoByCPFNomeGrupo(Cliente cliente, GrupoConsorcio grupoAssociado) {
        for (Contrato contrato : contratos) {
            if (contrato.getCliente().getCpf().equalsIgnoreCase(cliente.getCpf()) && 
                contrato.getGrupoAssociado().getNomeGrupo().equalsIgnoreCase(grupoAssociado.getNomeGrupo())) {
                return contrato;
            }
        }
        return null;
    } // exceptions: clienteInvalido, grupoInvalido, contratoInexistente

    @Override
    public List<Contrato> getContratosByIdGrupo(GrupoConsorcio grupoAssociado) {
        List<Contrato> contratosGrupo = new ArrayList<>();
        for (Contrato contrato : contratos) {
            if (contrato.getGrupoAssociado().getIdGrupo() == grupoAssociado.getIdGrupo()) {
                contratosGrupo.add(contrato);
            }
        }
        return contratosGrupo;
    } // exceptions: grupoInvalido

    @Override
    public boolean existeContrato(Contrato contrato) {
        for (Contrato contrato2 : contratos) {
            if (contrato2.getIdContrato() == contrato.getIdContrato()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Contrato getContratoByIdContrato(int idContrato) {
        for (Contrato contrato : contratos) {
            if (contrato.getIdContrato() == idContrato) {
                return contrato;
            }
        }
        return null;
    }

    @Override
    public void createContrato(Cliente cliente, GrupoConsorcio grupoAssociado) {
        Contrato contrato = new Contrato(cliente, grupoAssociado);
        contratos.add(contrato);
    } 

    @Override
    public void updateParcelasPagas(Contrato contrato) {
        contrato.setParcelasPagas(contrato.getParcelasPagas() + 1);
    } // exceptions: contratoInexistente

    @Override
    public void updateSaldoDevedor(Contrato contrato) {
        contrato.setSaldoDevedor(contrato.getSaldoDevedor() - contrato.getGrupoAssociado().getValorParcela());
    } // exceptions: contratoInexistente

    @Override
    public void updateValorPago(Contrato contrato) {
        contrato.setValorPago(contrato.getValorPago() + contrato.getGrupoAssociado().getValorParcela());
    } // exceptions: contratoInexistente

    @Override
    public void updateSaldoDevolução(Contrato contrato) {
        contrato.setSaldoDevolucao(calcularSaldoDevolucao(contrato));
    } // exceptions: contratoInexistente

    @Override
    public void updateStatusContrato(Contrato contrato, StatusContratoEnum status) {
        contrato.setStatusContrato(status);
    }
    @Override
    public void updateDataEncerramento(Contrato contrato, LocalDate data) {
        contrato.setDataEncerramento(data);
    }

    private double calcularSaldoDevolucao(Contrato contrato) {
        return (contrato.getGrupoAssociado().getValorTotal() / contrato.getGrupoAssociado().getNumeroParticipantes()) * contrato.getParcelasPagas();
    } // exceptions: contratoInexistente

    @Override
    public void deleteContrato(Contrato contrato) {
        contratos.remove(contrato);
    } // exceptions: contratoInexistente

}