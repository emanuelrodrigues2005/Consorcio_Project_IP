package br.ufrpe.ip.projeto.repositories;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import br.ufrpe.ip.projeto.repositories.interfaces.IContratoRepository;
import br.ufrpe.ip.projeto.enums.StatusContratoEnum;
import br.ufrpe.ip.projeto.models.Cliente;

public class ContratoRepository implements IContratoRepository, Serializable{
    private static ContratoRepository instancia;
    private ArrayList<Contrato> contratos;
    

    private ContratoRepository() {
        this.contratos = new ArrayList<>();
    }
    
    public static ContratoRepository getInstancia() {
        if (instancia == null) {
            ContratoRepository.instancia = ContratoRepository.lerArquivo();
        }
        return ContratoRepository.instancia;
    }

    private static ContratoRepository lerArquivo() {
        ContratoRepository instanceLocal;

        File in = new File("contratos.dat");
        FileInputStream fis;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(in);
            ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            instanceLocal = (ContratoRepository) o;
        } catch (Exception e) {
            instanceLocal = new ContratoRepository();
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
        if(ContratoRepository.getInstancia() == null) {
            return;
        }

        File out = new File("contratos.dat");
        FileOutputStream fos;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(out);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ContratoRepository.getInstancia());
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
    public List<Contrato> getAllContratos() {
        return Collections.unmodifiableList(contratos);
    } // exceptions: semContratosRegistrados

    @Override
    public List<Cliente> getAllClientesByGrupo(GrupoConsorcio grupoConsorcio) {
        List<Cliente> clientesByGrupo = new ArrayList<>();
        for (Contrato contrato : contratos) {
            if (contrato.getCliente() != null && contrato.getGrupoAssociado() != null && grupoConsorcio != null &&
                    contrato.getGrupoAssociado().getIdGrupo().equals(grupoConsorcio.getIdGrupo())) {
                clientesByGrupo.add(contrato.getCliente());
            }
        }
        return clientesByGrupo;
    }

    @Override
    public List<Contrato> getAllContratosByCPF(Cliente cliente) {
        List<Contrato> contratosCliente = new ArrayList<>();
        for (Contrato contrato : contratos) {
            if (contrato.getCliente() != null && contrato.getCliente().getCpf().equalsIgnoreCase(cliente.getCpf())) {
                contratosCliente.add(contrato);
            }
        }
        return Collections.unmodifiableList(contratosCliente);
    } // exceptions: clienteInvalido

    @Override
    public Contrato getContratoByCPFIdGrupo(Cliente cliente, GrupoConsorcio grupoAssociado) {
        for (Contrato contrato : contratos) {
            if (contrato.getCliente() != null && contrato.getCliente().getCpf().equalsIgnoreCase(cliente.getCpf()) &&
                contrato.getGrupoAssociado().getIdGrupo().equals(grupoAssociado.getIdGrupo())) {
                return contrato;
            }
        }
        return null;
    } // exceptions: clienteInvalido, grupoInvalido, contratoInexistente

    @Override
    public List<Contrato> getContratosByIdGrupo(GrupoConsorcio grupoAssociado) {
        List<Contrato> contratosGrupo = new ArrayList<>();
        for (Contrato contrato : contratos) {
            if (contrato.getGrupoAssociado() != null && grupoAssociado != null &&
                    contrato.getGrupoAssociado().getIdGrupo().equals(grupoAssociado.getIdGrupo())) {
                contratosGrupo.add(contrato);
            }
        }
        return Collections.unmodifiableList(contratosGrupo);
    } // exceptions: grupoInvalido

    @Override
    public boolean existeContrato(Contrato contrato) {
        for (Contrato contrato2 : contratos) {
            if (contrato != null && contrato2.getIdContrato().equals(contrato.getIdContrato())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Contrato getContratoByIdContrato(String idContrato) {
        for (Contrato contrato : contratos) {
            if (contrato.getIdContrato().equals(idContrato)) {
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
    public void updateSaldoDevolucao(Contrato contrato) {
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
        contratos.remove(contrato); //fornecer idContrato
    } // exceptions: contratoInexistente

}