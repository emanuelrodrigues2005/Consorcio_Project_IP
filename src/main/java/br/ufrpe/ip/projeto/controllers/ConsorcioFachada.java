package br.ufrpe.ip.projeto.controllers;

import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.enums.StatusContratoEnum;
import br.ufrpe.ip.projeto.enums.StatusGrupoConsorcioEnum;
import br.ufrpe.ip.projeto.exceptions.*;
import br.ufrpe.ip.projeto.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsorcioFachada implements IConsorcio{
    private static ConsorcioFachada instance;

    private final BoletoController boletoController;
    private final ClienteController clienteController;
    private final ContratoController contratoController;
    private final ContemplacaoController contemplacaoController;
    private final GrupoConsorcioController grupoConsorcioController;
    private final LoginController loginController;

    private ConsorcioFachada() {
        this.boletoController = BoletoController.getInstance();
        this.clienteController = ClienteController.getInstance();
        this.grupoConsorcioController = GrupoConsorcioController.getInstancia();
        this.contemplacaoController = ContemplacaoController.getInstance();
        this.contratoController = ContratoController.getInstancia();
        this.loginController = LoginController.getInstance();
    }

    public static ConsorcioFachada getInstance() {
        if(instance == null) {
            instance = new ConsorcioFachada();
        }
        return instance;
    }

    @Override
    public List<Boleto> getAllBoletos() throws ArrayVazioException {
        return this.boletoController.getAllBoletos();
    }

    @Override
    public String getIdBoleto(Contrato contratoBoleto, int numeroParcela) throws BoletoInexistenteException, CampoInvalidoException, BoletoInexistenteException {
        return this.boletoController.getIdBoleto(contratoBoleto, numeroParcela);
    }

    @Override
    public Boleto getBoletoById(String idBoleto) throws BoletoInexistenteException, CampoInvalidoException {
        return this.boletoController.getBoletoById(idBoleto);
    }

    @Override
    public Boleto getBoletoByContrato(Contrato contratoBoleto) throws BoletoInexistenteException, CampoInvalidoException, ContratoInvalidoException {
        return this.boletoController.getBoletoByContrato(contratoBoleto);
    }

    @Override
    public void createBoleto(Contrato contratoBoleto) throws CampoInvalidoException {
        this.boletoController.createBoleto(contratoBoleto);
    }

    @Override
    public void updateDataPagamento(Boleto boleto) throws BoletoInexistenteException, CampoInvalidoException {
        this.boletoController.updateDataPagamento(boleto);
    }

    @Override
    public void updateStatusBoleto(Boleto boleto, StatusBoletoEnum statusBoleto) throws BoletoInexistenteException, CampoInvalidoException {
        this.boletoController.updateStatusBoleto(boleto, statusBoleto);
    }

    @Override
    public void deleteBoleto(String idBoleto) throws BoletoInexistenteException, CampoInvalidoException {
        this.boletoController.deleteBoleto(idBoleto);
    }

    @Override
    public void atualizarBoletoVencidos(Boleto boleto) throws BoletoInexistenteException, CampoInvalidoException {
        this.boletoController.atualizarBoletoVencido(boleto);
    }

    @Override
    public void verificarBoletoVencidos() throws BoletoInexistenteException, CampoInvalidoException {
        this.boletoController.verificarBoletosVencidos();
    }

    @Override
    public void realizarPagamento(String idBoleto) throws BoletoInexistenteException, CampoInvalidoException {
        this.boletoController.realizarPagamento(idBoleto);
    }

    @Override
    public List<Cliente> getAllClientes() {
        return this.clienteController.getAllClientes();
    }

    @Override
    public Cliente getClienteByCpf(String cpf) {
        return this.clienteController.getClienteByCpf(cpf);
    }

    @Override
    public void createCliente(String nomeCliente, String cpfCliente, String telefoneCliente, String emailCliente, String senhaCliente) {
        this.clienteController.createCliente(nomeCliente, cpfCliente, telefoneCliente, emailCliente, senhaCliente);
    }

    @Override
    public void updateNome(String nomeCliente, String cpfCliente) {
        this.clienteController.updateNome(nomeCliente, cpfCliente);
    }

    @Override
    public void updateTelefone(String telefoneCliente, String cpfCliente) {
        this.clienteController.updateTelefone(telefoneCliente, cpfCliente);
    }

    @Override
    public void updateEmail(String emailCliente, String cpfCliente) {
        this.clienteController.updateEmail(emailCliente, cpfCliente);
    }

    @Override
    public void updateSenha(String senhaCliente, String cpfCliente) {
        this.clienteController.updateSenha(senhaCliente, cpfCliente);
    }

    @Override
    public void deleteCliente(String cpfCliente) {
        this.clienteController.deleteCliente(cpfCliente);
    }

    @Override
    public boolean validarCpf(String cpfCliente) {
        return this.clienteController.validarCpf(cpfCliente);
    }

    @Override
    public List<Contemplacao> getAllContemplacao() throws ArrayVazioException {
        return this.contemplacaoController.getAllContemplacoes();
    }

    @Override
    public Contemplacao getContemplacaoById(String idContemplacao) throws ContemplacaoInexistenteException {
        return this.contemplacaoController.getContemplacaoById(idContemplacao);
    }

    @Override
    public Contemplacao createContemplacao(Contrato contrato) throws ContemplacaoInexistenteException, CampoInvalidoException {
        return this.contemplacaoController.createContemplacao(contrato);
    }

    @Override
    public void updateDataContemplacao(String idContemplacao, LocalDate dataContemplacao) throws ContemplacaoInexistenteException, DataContemplacaoInvalidaException {
        this.contemplacaoController.updateDataContemplacao(idContemplacao, dataContemplacao);
    }

    @Override
    public void deleteContemplacao(String idContemplacao) throws ContemplacaoInexistenteException {
        this.contemplacaoController.deleteContemplacao(idContemplacao);
    }

    @Override
    public String sorteioContemplacao() throws ContratoInvalidoException, ContemplacaoInexistenteException, ArrayVazioException, CampoInvalidoException {
        return this.contemplacaoController.sorteioContemplacao();
    }

    @Override
    public void createContrato(Cliente cliente, GrupoConsorcio grupoConsorcio) throws CampoInvalidoException {
        this.contratoController.createContrato(cliente, grupoConsorcio);
    }

    @Override
    public void registrarPagamento(Cliente cliente, GrupoConsorcio grupoConsorcio, Boleto boleto) throws CampoInvalidoException {
        this.contratoController.registrarPagamento(cliente, grupoConsorcio, boleto);
    }

    @Override
    public Contrato getContratoByCPFNomeGrupo(Cliente cliente, GrupoConsorcio grupoAssociado) {
        return this.contratoController.getContratoByCPFNomeGrupo(cliente, grupoAssociado);
    }

    @Override
    public List<Contrato> getAllContratos() {
        return this.contratoController.getAllContratos();
    }

    @Override
    public List<Contrato> getContratosByIdGrupo(GrupoConsorcio grupoAssociado) {
        return this.contratoController.getContratosByIdGrupo(grupoAssociado);
    }

    @Override
    public List<Cliente> getAllClientesByGrupo(GrupoConsorcio grupoConsorcio) {
        return this.contratoController.getAllClientesByGrupo(grupoConsorcio);
    }

    @Override
    public List<Contrato> getAllContratosByCPF(Cliente cliente) {
        return this.contratoController.getAllContratosByCPF(cliente);
    }

    @Override
    public boolean existeContrato(Contrato contrato) {
        return this.contratoController.existeContrato(contrato);
    }

    @Override
    public Contrato getContratoByIdContrato(String idContrato) {
        return this.contratoController.getContratoByIdContrato(idContrato);
    }

    @Override
    public boolean cancelarContrato(Cliente cliente, GrupoConsorcio grupoAssociado) throws ContratoInvalidoException, CampoInvalidoException {
        return this.contratoController.cancelarContrato(cliente, grupoAssociado);
    }

    @Override
    public void updateStatusContrato(Contrato contrato, StatusContratoEnum statusContrato) throws ContratoInvalidoException, CampoInvalidoException {
        this.contratoController.updateStatusContrato(contrato, statusContrato);
    }

    @Override
    public void updateParcelasPagas(Contrato contrato) throws ContratoInvalidoException {
        this.contratoController.updateParcelasPagas(contrato);
    }

    @Override
    public void updateSaldoDevedor(Contrato contrato) throws ContratoInvalidoException {
        this.contratoController.updateSaldoDevedor(contrato);
    }

    @Override
    public void updateValorPago(Contrato contrato) throws ContratoInvalidoException {
        this.contratoController.updateValorPago(contrato);
    }

    @Override
    public void updateSaldoDevolucao(Contrato contrato) throws ContratoInvalidoException {
        this.contratoController.updateSaldoDevolucao(contrato);
    }

    @Override
    public void updateDataEncerramento(Contrato contrato, LocalDate dataEncerramento) throws ContratoInvalidoException {
        this.contratoController.updateDataEncerramento(contrato, dataEncerramento);
    }

    @Override
    public void deleteContrato(Contrato contrato) throws ContratoInvalidoException {
        this.contratoController.deleteContrato(contrato);
    }

    @Override
    public void createGrupoConsorcio(String nomeGrupo, int numeroMaximoParticipantes, double valorTotal, double taxaAdmin) throws CampoInvalidoException {
        this.grupoConsorcioController.createGrupoConsorcio(nomeGrupo, numeroMaximoParticipantes, valorTotal, taxaAdmin);
    }

    @Override
    public GrupoConsorcio getGrupoById(String idGrupo) throws IdGrupoConsorcioInexistenteException {
        return this.grupoConsorcioController.getGrupoById(idGrupo);
    }

    @Override
    public List<GrupoConsorcio> getAllGrupos() throws ArrayVazioException {
        return this.grupoConsorcioController.getAllGrupos();
    }

    @Override
    public void updateParticipantes(GrupoConsorcio grupoConsorcio, int novoNumParticipantes) throws GrupoConsorcioInexistenteException, CampoInvalidoException {
        this.grupoConsorcioController.updateParticipantes(grupoConsorcio, novoNumParticipantes);
    }

    @Override
    public void updateNomeGrupo(GrupoConsorcio grupoConsorcio, String novoNome) throws GrupoConsorcioInexistenteException, CampoInvalidoException {
        this.grupoConsorcioController.updateNomeGrupo(grupoConsorcio, novoNome);
    }

    @Override
    public void updateTaxaAdmin(GrupoConsorcio grupoConsorcio, double novaTaxa) throws GrupoConsorcioInexistenteException, CampoInvalidoException {
        this.grupoConsorcioController.updateTaxaAdmin(grupoConsorcio, novaTaxa);
    }

    @Override
    public void updateStatusGrupo(GrupoConsorcio grupoConsorcio, StatusGrupoConsorcioEnum novoStatus) {
        this.grupoConsorcioController.updateStatusGrupo(grupoConsorcio, novoStatus);
    }

    @Override
    public void deleteGrupoConsorcio(GrupoConsorcio grupoConsorcio) throws GrupoConsorcioInexistenteException {
        this.grupoConsorcioController.deleteGrupoConsorcio(grupoConsorcio);
    }

    @Override
    public void reajusteParcela(GrupoConsorcio grupoConsorcio) {
        this.grupoConsorcioController.reajusteParcela(grupoConsorcio);
    }

    @Override
    public double getValorPago(String idGrupo) throws IdGrupoConsorcioInexistenteException {
        return this.grupoConsorcioController.getValorPago(idGrupo);
    }

    @Override
    public void efutuarLogin(String cpf, String senha) {
        this.loginController.efetuarLogin(cpf, senha);
    }

    @Override
    public Cliente getClienteLogado() {
        return this.loginController.getClienteLogado();
    }

    @Override
    public double calcularInadimplencia(GrupoConsorcio grupoConsorcio) throws ArrayVazioException {
        List<Contrato> contratosInadimplentes = new ArrayList<>();
        for (Boleto boleto : this.getAllBoletos()) {
            if (getContratosByIdGrupo(grupoConsorcio).contains(boleto.getContratoBoleto())) {
                if (boleto.getStatusBoleto() == StatusBoletoEnum.ATRASADO && !(contratosInadimplentes.contains(boleto.getContratoBoleto()))) {
                    contratosInadimplentes.add(boleto.getContratoBoleto());
                }
            }
        }
        System.out.println((double)contratosInadimplentes.size()/grupoConsorcio.getNumeroParticipantes() * 100); //debugging
        return grupoConsorcio.getNumeroParticipantes() != 0 ? (double)contratosInadimplentes.size()/grupoConsorcio.getNumeroParticipantes() * 100 : 0;
    }
}
