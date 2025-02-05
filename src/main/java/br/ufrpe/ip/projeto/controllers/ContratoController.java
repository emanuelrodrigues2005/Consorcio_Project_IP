package br.ufrpe.ip.projeto.controllers;

import java.time.LocalDate;

import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.enums.StatusContratoEnum;
import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import br.ufrpe.ip.projeto.repositories.ContratoRepository;
import br.ufrpe.ip.projeto.repositories.interfaces.IContratoRepository;

public class ContratoController {
    private static ContratoController instancia;
    private final IContratoRepository repositorioContrato;
    
    public ContratoController() {
        this.repositorioContrato = ContratoRepository.getInstancia();
    }

    public static ContratoController getInstancia() {
        if (instancia == null) {
            instancia = new ContratoController();
        }
        return instancia;
    }

    

    public boolean cancelarContrato(Cliente cliente, GrupoConsorcio grupoAssociado) {
        Contrato contrato = repositorioContrato.getContratoByCPFNomeGrupo(cliente, grupoAssociado);
        if (contrato != null) {
            repositorioContrato.updateStatusContrato(contrato, StatusContratoEnum.ENCERRADO);
            repositorioContrato.updateSaldoDevolução(contrato);
            repositorioContrato.updateDataEncerramento(contrato, LocalDate.now());
            return true;
        }
        return false;
    } 

    // public void pagarParcela(Cliente cliente, GrupoConsorcio grupoAssociado, Boleto boleto) {
    //     if (getContratoByCPFNomeGrupo(cliente, grupoAssociado) != null & !(getContratoByCPFNomeGrupo(cliente, grupoAssociado).getListaBoletosPagos().contains(boleto))) {
    //         if (boleto.getStatusBoleto() == StatusBoletoEnum.PAGO) {
    //             Contrato contrato = getContratoByCPFNomeGrupo(cliente, grupoAssociado);
    //             updateContrato(cliente, contrato.getParcelasPagas() + 1, contrato.getSaldoDevedor() - contrato.getGrupoAssociado().getValorParcela(), boleto);
    //             System.out.println("A parcela foi paga com sucesso.");
    //         } else if (boleto.getStatusBoleto() == StatusBoletoEnum.PENDENTE) {
    //             System.out.println("O boleto ainda está pendente de pagamento. Operação negada");
    //         } else if (boleto.getStatusBoleto() == StatusBoletoEnum.ATRASADO) {
    //             System.out.println("O boleto ainda está pendente de pagamento e está atrasado no momento. Operação negada");
    //         } 
    //     } else {
    //         System.out.println("O cliente não foi encontrado ou o boleto já foi pago.");
    //     }
    // } // reestruturar método para receber Contrato e Boleto como parametros 
}
