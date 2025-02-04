package br.ufrpe.ip.projeto.repositories;

import java.util.ArrayList;

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

    public void getAllContratos() {
        if (contratos.isEmpty()) {
            System.out.println("Nenhum contrato foi criado");
        } else {
            for(Contrato contrato : contratos) {
                System.out.println(contrato);
            }
        }
        System.out.println("Todos os contratos foram listados acima.");
    } // retornar string

    public void getAllContratosByCPF(Cliente cliente) {
        for (Contrato contrato : contratos) {
            if (contrato.getCliente().getCpf().equalsIgnoreCase(cliente.getCpf())) {
                System.out.println(contrato.toString());
            } 
        }
        System.out.println("Todos os contratos do cliente foram listados acima.");
    } // reestruturar método para receber parametro string
    
    public Contrato getContratoByCPFNomeGrupo(Cliente cliente, GrupoConsorcio grupoAssociado) {
        for (Contrato contrato : contratos) {
            if (contrato.getCliente().getCpf().equalsIgnoreCase(cliente.getCpf()) && contrato.getGrupoAssociado().getNomeGrupo().equalsIgnoreCase(grupoAssociado.getNomeGrupo())) {
                return contrato;
            }
        }
        System.out.println("Cliente não encontrado");
        return null;
    } // receber parametri string em vez de cliente

    public String getContratosByNomeGrupo(GrupoConsorcio grupoAssociado) {
        for (Contrato contrato : contratos) {
            if(contrato.getGrupoAssociado().getNomeGrupo().equalsIgnoreCase(grupoAssociado.getNomeGrupo())) {
                int counter = 0;
                for(Contrato contratoListado : contrato.getGrupoAssociado().getListaContratos()) {
                    System.out.println(contratoListado);
                    counter++;
                }
                if (counter > 0) {
                    return "Todos os contratos do grupo foram listados acima.";
                }
                return "O grupo não possui contratos ativos.";
            }
        }
        return "Grupo não encontrado.";
    } 

    public void createContrato(Cliente cliente, GrupoConsorcio grupoAssociado) {
        Contrato contrato = new Contrato(cliente, grupoAssociado);
        if (getContratoByCPFNomeGrupo(cliente, grupoAssociado) == null)  {
            contratos.add(contrato);
            grupoAssociado.getListaContratos().add(contrato);
            cliente.getContratos().add(contrato);
            System.out.printf("Contrato do cliente %s, pertencente ao grupo %s foi criado com sucesso.\n", cliente.getNome(), grupoAssociado.getNomeGrupo());
        } else {
            System.out.println("Contrato pertencente a este cliente neste grupo já existe.");
        }
    } // receber string cpf em vez de cliente

    private void updateContrato(Cliente cliente, int novasParcelas, double novoSaldoDevedor, Boleto boleto) {
        for(Contrato contrato : contratos) {
            if(contrato.getCliente().getCpf().equalsIgnoreCase(cliente.getCpf())) {
                if (novasParcelas > contrato.getParcelasPagas()) {
                    contrato.setParcelasPagas(novasParcelas);
                }
                if (novoSaldoDevedor < contrato.getSaldoDevedor()) {
                    contrato.setSaldoDevedor(novoSaldoDevedor);
                }
                if (boleto != null) {
                    if (contrato.getListaBoletosAtrasados().contains(boleto)) {
                        contrato.getListaBoletosAtrasados().remove(boleto);
                    }
                    contrato.addBoleto(boleto);
                }
            }
        }
    } // método genérico não é a call, vi no projeto de jp. vou refazer tudo em metodos especificos

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