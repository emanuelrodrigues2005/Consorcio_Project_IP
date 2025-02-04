package br.ufrpe.ip.projeto.repositories;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.enums.StatusContratoEnum;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import br.ufrpe.ip.projeto.repositories.interfaces.IContratoRepository;
import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.models.Boleto;

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

    public List<Contrato> getAllContratos() {
        if (contratos.isEmpty()) {
            return null;
        } 
        return contratos;
    }

    public List<Contrato> getAllContratosByCPF(Cliente cliente) {
        if (cliente.getContratos().isEmpty()) {
            return null;
        }
        return cliente.getContratos();
    } 
    
    public Contrato getContratoByCPFNomeGrupo(Cliente cliente, GrupoConsorcio grupoAssociado) {
        for (Contrato contrato : contratos) {
            if (contrato.getCliente().getCpf().equalsIgnoreCase(cliente.getCpf()) && 
                contrato.getGrupoAssociado().getNomeGrupo().equalsIgnoreCase(grupoAssociado.getNomeGrupo())) {
                return contrato;
            }
        }
        return null;
    } 

    public List<Contrato> getContratosByNomeGrupo(GrupoConsorcio grupoAssociado) {
        if (grupoAssociado.getListaContratos().isEmpty()) {
            return null;
        }
        return grupoAssociado.getListaContratos();
    }
    
    public void createContrato(Cliente cliente, GrupoConsorcio grupoAssociado) {
        Contrato contrato = new Contrato(cliente, grupoAssociado);
        contratos.add(contrato);
    } // exceptions: Contrato já existe, clienteInvalido, grupoInvalido

    public void update

    public void pagarParcela(Cliente cliente, GrupoConsorcio grupoAssociado, Boleto boleto) {
        if (getContratoByCPFNomeGrupo(cliente, grupoAssociado) != null & !(getContratoByCPFNomeGrupo(cliente, grupoAssociado).getListaBoletosPagos().contains(boleto))) {
            if (boleto.getStatusBoleto() == StatusBoletoEnum.PAGO) {
                Contrato contrato = getContratoByCPFNomeGrupo(cliente, grupoAssociado);
                updateContrato(cliente, contrato.getParcelasPagas() + 1, contrato.getSaldoDevedor() - contrato.getGrupoAssociado().getValorParcela(), boleto);
                System.out.println("A parcela foi paga com sucesso.");
            } else if (boleto.getStatusBoleto() == StatusBoletoEnum.PENDENTE) {
                System.out.println("O boleto ainda está pendente de pagamento. Operação negada");
            } else if (boleto.getStatusBoleto() == StatusBoletoEnum.ATRASADO) {
                System.out.println("O boleto ainda está pendente de pagamento e está atrasado no momento. Operação negada");
            } 
        } else {
            System.out.println("O cliente não foi encontrado ou o boleto já foi pago.");
        }
    } // reestruturar método para receber Contrato e Boleto como parametros 

    public void cancelarContrato(Cliente cliente, GrupoConsorcio grupoAssociado) {
        if (getContratoByCPFNomeGrupo(cliente, grupoAssociado) != null) {
            Contrato contrato = getContratoByCPFNomeGrupo(cliente, grupoAssociado);
            contrato.setStatusContrato(StatusContratoEnum.ENCERRADO);
            contrato.setSaldoDevolucao(contrato.calcularSaldoDevolucao());
            System.out.printf("Contrato cancelado com sucesso, suas parcelas pagas serão devolvidas após o término do consórcio.\n" );
        } else {
            System.out.println("Contrato não encontrado não pôde ser cancelado.");
        }
    } // refazer a estrutura do método para parâmetro único (Contrato)

    public void deleteContrato(Cliente cliente, GrupoConsorcio grupoAssociado) {
        Contrato contrato = getContratoByCPFNomeGrupo(cliente, grupoAssociado);
        if (contrato != null) {
            contratos.remove(contrato);
            System.out.println("Contrato removido com sucesso.");
        } else {
            System.out.println("Contrato não encontrado não pôde ser removido.");
        }
    } // refazer a estrutura do método para parâmetro único (Contrato)

    @Override
    public void getAllContratosByCPF() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllContratosByCPF'");
    }

    @Override
    public Contrato getContratoByCPFNomeGrupo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getContratoByCPFNomeGrupo'");
    }

    @Override
    public void createContrato() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createContrato'");
    }

    @Override
    public void pagarParcela() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pagarParcela'");
    }

    @Override
    public void cancelarContrato() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelarContrato'");
    }

    @Override
    public void deleteContrato() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteContrato'");
    }
}