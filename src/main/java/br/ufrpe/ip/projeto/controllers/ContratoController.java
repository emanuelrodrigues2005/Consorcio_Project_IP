package br.ufrpe.ip.projeto.controllers;

import java.time.LocalDate;
import java.util.List;

import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.enums.StatusContratoEnum;
import br.ufrpe.ip.projeto.exceptions.CampoInvalidoException;
import br.ufrpe.ip.projeto.exceptions.ContratoDuplicadoException;
import br.ufrpe.ip.projeto.exceptions.ContratoInvalidoException;
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

    public void createContrato(Cliente cliente, GrupoConsorcio grupoConsorcio) throws ContratoDuplicadoException, CampoInvalidoException {
        if (cliente == null) {
            throw new CampoInvalidoException("Cliente");
        }
        if (grupoConsorcio == null) {
            throw new CampoInvalidoException("Grupo");
        }
        Contrato contrato = new Contrato(cliente, grupoConsorcio);
        if (this.repositorioContrato.existeContrato(contrato)) {
            throw new ContratoDuplicadoException(contrato.getIdContrato());
        }

        this.repositorioContrato.createContrato(cliente, grupoConsorcio);
        this.repositorioContrato.salvarArquivo();
    }
    // exceptions: contratoDuplicado, clienteInvalido, grupoInvalido, contratoInvalido

    public void registrarPagamento(Cliente cliente, GrupoConsorcio grupoConsorcio, Boleto boleto)  {
        Contrato contrato = this.repositorioContrato.getContratoByCPFIdGrupo(cliente, grupoConsorcio);

        this.repositorioContrato.updateParcelasPagas(contrato);
        this.repositorioContrato.updateSaldoDevedor(contrato);
        this.repositorioContrato.updateValorPago(contrato);
        this.repositorioContrato.salvarArquivo();
    }// throw boletoPendente/Atrasado

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

    public boolean cancelarContrato(Cliente cliente, GrupoConsorcio grupoAssociado) throws ContratoInvalidoException, CampoInvalidoException {
        Contrato contrato = this.repositorioContrato.getContratoByCPFIdGrupo(cliente, grupoAssociado);
        if (contrato != null) {
            this.updateStatusContrato(contrato, StatusContratoEnum.ENCERRADO);
            this.updateSaldoDevolucao(contrato);
            this.updateDataEncerramento(contrato, LocalDate.now());
            return true;
        }
        return false;
    }

    public void updateStatusContrato(Contrato contrato, StatusContratoEnum statusContrato) throws CampoInvalidoException, ContratoInvalidoException {
        if (statusContrato == null) {
            throw new CampoInvalidoException("Status");
        }
        if (contrato == null) {
            throw new ContratoInvalidoException("Contrato inválido.");
        }
        repositorioContrato.updateStatusContrato(contrato, statusContrato);
    } //exceptions: CampoInvalido, ContratoInvalido

    public void updateParcelasPagas(Contrato contrato) throws ContratoInvalidoException {
        if (contrato == null) {
            throw new ContratoInvalidoException("Contrato inválido.");
        }
        this.repositorioContrato.updateParcelasPagas(contrato);
    } //exceptions: CampoInvalido, ContratoInvalido

    public void updateSaldoDevedor(Contrato contrato) throws ContratoInvalidoException {
        if (contrato == null) {
            throw new ContratoInvalidoException("Contrato inválido.");
        }
        this.repositorioContrato.updateSaldoDevedor(contrato);
    } //exceptions: CampoInvalido, ContratoInvalido

    public void updateValorPago(Contrato contrato) throws ContratoInvalidoException {
        if (contrato == null) {
            throw new ContratoInvalidoException("Contrato inválido.");
        }
        this.repositorioContrato.updateValorPago(contrato);
    } //exceptions: CampoInvalido, ContratoInvalido

    public void updateSaldoDevolucao(Contrato contrato) throws ContratoInvalidoException {
        if (contrato == null) {
            throw new ContratoInvalidoException("Contrato inválido.");
        }
        this.repositorioContrato.updateSaldoDevolucao(contrato);
    } //exceptions: CampoInvalido, ContratoInvalido

    public void updateDataEncerramento(Contrato contrato, LocalDate dataEncerramento) throws ContratoInvalidoException {
        if (contrato == null) {
            throw new ContratoInvalidoException("Contrato inválido.");
        }
        this.repositorioContrato.updateDataEncerramento(contrato, dataEncerramento);
    } //exceptions: CampoInvalido, ContratoInvalido

    public void deleteContrato(Contrato contrato) throws ContratoInvalidoException {
        if (contrato == null) {
            throw new ContratoInvalidoException("Contrato inválido.");
        }
        this.repositorioContrato.deleteContrato(contrato);
    } //throw contratoInexistente, contratoInvalido
}
