package br.ufrpe.ip.projeto.controllers;

import java.time.LocalDate;
import java.util.List;

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

    public void createContrato(Cliente cliente, GrupoConsorcio grupoConsorcio) {
        if (!this.repositorioContrato.existeContrato(this.repositorioContrato.getContratoByCPFNomeGrupo(cliente, grupoConsorcio))) {
            this.repositorioContrato.createContrato(cliente, grupoConsorcio);
        }
    } // exceptions: contratoDuplicado, clienteInvalido, grupoInvalido, contratoInvalido

    public void registrarPagamento(Cliente cliente, GrupoConsorcio grupoConsorcio, Boleto boleto) {
        Contrato contrato = this.repositorioContrato.getContratoByCPFNomeGrupo(cliente, grupoConsorcio);
        if (boleto.getStatusBoleto() == StatusBoletoEnum.PAGO) {
            this.repositorioContrato.updateParcelasPagas(contrato);
            this.repositorioContrato.updateSaldoDevedor(contrato);
            this.repositorioContrato.updateValorPago(contrato);
            return;
        }
        // throw boletoPendente/Atrasado 
    }

    public Contrato getContratoByCPFNomeGrupo(Cliente cliente, GrupoConsorcio grupoAssociado) {
        return this.repositorioContrato.getContratoByCPFNomeGrupo(cliente, grupoAssociado);
    }

    public List<Contrato> getAllContratos() {
        return this.repositorioContrato.getAllContratos();
    }

    public List<Contrato> getAllContratosByCPF(Cliente cliente) {
        return this.repositorioContrato.getAllContratosByCPF(cliente);   
    }

    public List<Contrato> getContratosByIdGrupo(GrupoConsorcio grupoAssociado) {
        return this.repositorioContrato.getContratosByIdGrupo(grupoAssociado);
    }

    public boolean existeContrato(Contrato contrato) {
        return this.repositorioContrato.existeContrato(contrato);
    }

    public Contrato getContratoByIdContrato(int idContrato) {
        return this.repositorioContrato.getContratoByIdContrato(idContrato);
    }

    public boolean cancelarContrato(Cliente cliente, GrupoConsorcio grupoAssociado) {
        Contrato contrato = this.repositorioContrato.getContratoByCPFNomeGrupo(cliente, grupoAssociado);
        if (contrato != null) {
            this.repositorioContrato.updateStatusContrato(contrato, StatusContratoEnum.ENCERRADO);
            this.repositorioContrato.updateSaldoDevolução(contrato);
            this.repositorioContrato.updateDataEncerramento(contrato, LocalDate.now());
            return true;
        }
        return false;
    } 

    public void deleteContrato(Contrato contrato) {
        if (contrato != null) {
            this.repositorioContrato.deleteContrato(contrato);
        }
    }
}
