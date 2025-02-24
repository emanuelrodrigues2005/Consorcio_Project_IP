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
    
    private ContratoController() {
        this.repositorioContrato = ContratoRepository.getInstancia();
    }

    public static ContratoController getInstancia() {
        if (instancia == null) {
            instancia = new ContratoController();
        }
        return instancia;
    }

    public void createContrato(Cliente cliente, GrupoConsorcio grupoConsorcio) {
        if (!this.repositorioContrato.existeContrato(this.repositorioContrato.getContratoByCPFIdGrupo(cliente, grupoConsorcio))) {
            if(cliente == null) {
                return;
            } else if (grupoConsorcio == null) {
                return;
            }
            this.repositorioContrato.createContrato(cliente, grupoConsorcio);
            this.repositorioContrato.salvarArquivo();
        }
    } // exceptions: contratoDuplicado, clienteInvalido, grupoInvalido, contratoInvalido

    public void registrarPagamento(Cliente cliente, GrupoConsorcio grupoConsorcio, Boleto boleto) {
        Contrato contrato = this.repositorioContrato.getContratoByCPFIdGrupo(cliente, grupoConsorcio);
        if (boleto.getStatusBoleto() == StatusBoletoEnum.PAGO) {
            this.repositorioContrato.updateParcelasPagas(contrato);
            this.repositorioContrato.updateSaldoDevedor(contrato);
            this.repositorioContrato.updateValorPago(contrato);
            this.repositorioContrato.salvarArquivo();
            return;
        }
        // throw boletoPendente/Atrasado 
    }

    public Contrato getContratoByCPFNomeGrupo(Cliente cliente, GrupoConsorcio grupoAssociado) {
        return this.repositorioContrato.getContratoByCPFIdGrupo(cliente, grupoAssociado);
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

    public Contrato getContratoByIdContrato(String idContrato) {
        return this.repositorioContrato.getContratoByIdContrato(idContrato);
    }

    public boolean cancelarContrato(Cliente cliente, GrupoConsorcio grupoAssociado) {
        Contrato contrato = this.repositorioContrato.getContratoByCPFIdGrupo(cliente, grupoAssociado);
        if (contrato != null) {
            this.updateStatusContrato(contrato, StatusContratoEnum.ENCERRADO);
            this.updateSaldoDevolucao(contrato);
            this.updateDataEncerramento(contrato, LocalDate.now());
            return true;
        }
        return false;
    }

    public void updateStatusContrato(Contrato contrato, StatusContratoEnum statusContrato) {
        this.repositorioContrato.updateStatusContrato(contrato, statusContrato);
    } //exceptions: CampoInvalido, ContratoInvalido

    public void updateParcelasPagas(Contrato contrato) {
        this.repositorioContrato.updateParcelasPagas(contrato);
    } //exceptions: CampoInvalido, ContratoInvalido

    public void updateSaldoDevedor(Contrato contrato) {
        this.repositorioContrato.updateSaldoDevedor(contrato);
    } //exceptions: CampoInvalido, ContratoInvalido

    public void updateValorPago(Contrato contrato) {
        this.repositorioContrato.updateValorPago(contrato);
    } //exceptions: CampoInvalido, ContratoInvalido

    public void updateSaldoDevolucao(Contrato contrato) {
        this.repositorioContrato.updateSaldoDevolucao(contrato);
    } //exceptions: CampoInvalido, ContratoInvalido

    public void updateDataEncerramento(Contrato contrato, LocalDate dataEncerramento) {
        this.repositorioContrato.updateDataEncerramento(contrato, dataEncerramento);
    } //exceptions: CampoInvalido, ContratoInvalido

    public void deleteContrato(Contrato contrato) {
        if (contrato != null) {
            this.repositorioContrato.deleteContrato(contrato); //fornecer o idContrato como parâmetro ao invés do contrato em si
        }
    } //throw contratoInexistente, contratoInvalido
}
