package br.ufrpe.ip.projeto.controllers;

import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.enums.StatusContratoEnum;
import br.ufrpe.ip.projeto.enums.StatusGrupoConsorcioEnum;
import br.ufrpe.ip.projeto.exceptions.ArrayVazioException;
import br.ufrpe.ip.projeto.exceptions.CampoInvalidoException;
import br.ufrpe.ip.projeto.exceptions.ClienteDuplicadoException;
import br.ufrpe.ip.projeto.exceptions.ClienteInexistenteException;
import br.ufrpe.ip.projeto.models.*;

import java.time.LocalDate;
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
    public List<Boleto> getAllBoletos() {
        return this.boletoController.getAllBoletos();
    }

    @Override
    public String getIdBoleto(Contrato contratoBoleto, int numeroParcela) {
        return this.boletoController.getIdBoleto(contratoBoleto, numeroParcela);
    }

    @Override
    public Boleto getBoletoById(String idBoleto) {
        return this.boletoController.getBoletoById(idBoleto);
    }

    @Override
    public Boleto getBoletoByContrato(Contrato contratoBoleto) {
        return this.boletoController.getBoletoByContrato(contratoBoleto);
    }

    @Override
    public void createBoleto(Contrato contratoBoleto) {
        this.boletoController.createBoleto(contratoBoleto);
    }

    @Override
    public void updateDataPagamento(Boleto boleto) {
        this.boletoController.updateDataPagamento(boleto);
    }

    @Override
    public void updateStatusBoleto(Boleto boleto, StatusBoletoEnum statusBoleto) {
        this.boletoController.updateStatusBoleto(boleto, statusBoleto);
    }

    @Override
    public void deleteBoleto(String idBoleto) {
        this.boletoController.deleteBoleto(idBoleto);
    }

    @Override
    public void atualizarBoletoVencidos(Boleto boleto) {
        this.boletoController.atualizarBoletoVencido(boleto);
    }

    @Override
    public void verificarBoletoVencidos() {
        this.boletoController.verificarBoletosVencidos();
    }

    @Override
    public void realizarPagamento(String idBoleto) {
        this.boletoController.realizarPagamento(idBoleto);
    }

    @Override
    public List<Cliente> getAllClientes() throws ArrayVazioException {
        return this.clienteController.getAllClientes();
    }

    @Override
    public Cliente getClienteByCpf(String cpf) throws ClienteInexistenteException {
        return this.clienteController.getClienteByCpf(cpf);
    }

    @Override
    public void createCliente(String nomeCliente, String cpfCliente, String telefoneCliente, String emailCliente, String senhaCliente)
            throws CampoInvalidoException, ClienteDuplicadoException {
        this.clienteController.createCliente(nomeCliente, cpfCliente, telefoneCliente, emailCliente, senhaCliente);
    }

    @Override
    public void updateNome(String nomeCliente, String cpfCliente) throws CampoInvalidoException, ClienteInexistenteException{
        this.clienteController.updateNome(nomeCliente, cpfCliente);
    }

    @Override
    public void updateTelefone(String telefoneCliente, String cpfCliente) throws CampoInvalidoException, ClienteInexistenteException{
        this.clienteController.updateTelefone(telefoneCliente, cpfCliente);
    }

    @Override
    public void updateEmail(String emailCliente, String cpfCliente) throws CampoInvalidoException, ClienteInexistenteException {
        this.clienteController.updateEmail(emailCliente, cpfCliente);
    }

    @Override
    public void updateSenha(String senhaCliente, String cpfCliente) throws CampoInvalidoException, ClienteInexistenteException{
        this.clienteController.updateSenha(senhaCliente, cpfCliente);
    }

    @Override
    public void deleteCliente(String cpfCliente) throws ClienteInexistenteException{
        this.clienteController.deleteCliente(cpfCliente);
    }

    @Override
    public boolean validarCpf(String cpfCliente) {
        return this.clienteController.validarCpf(cpfCliente);
    }

    @Override
    public List<Contemplacao> getAllContemplacao() {
        return this.contemplacaoController.getAllContemplacoes();
    }

    @Override
    public Contemplacao getContemplacaoById(String idContemplacao) {
        return this.contemplacaoController.getContemplacaoById(idContemplacao);
    }

    @Override
    public Contemplacao createContemplacao(Contrato contrato) {
        return this.contemplacaoController.createContemplacao(contrato);
    }

    @Override
    public void updateDataContemplacao(String idContemplacao, LocalDate dataContemplacao) {
        this.contemplacaoController.updateDataContemplacao(idContemplacao, dataContemplacao);
    }

    @Override
    public void deleteContemplacao(String idContemplacao) {
        this.contemplacaoController.deleteContemplacao(idContemplacao);
    }

    @Override
    public String sorteioContemplacao() {
        return this.contemplacaoController.sorteioContemplacao();
    }

    @Override
    public void createContrato(Cliente cliente, GrupoConsorcio grupoConsorcio) {
        this.contratoController.createContrato(cliente, grupoConsorcio);
    }

    @Override
    public void registrarPagamento(Cliente cliente, GrupoConsorcio grupoConsorcio, Boleto boleto) {
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
    public List<Cliente> getAllClientesByGrupo(GrupoConsorcio grupoConsorcio) {
        return this.contratoController.getAllClientesByGrupo(grupoConsorcio);
    }

    @Override
    public List<Contrato> getContratosByIdGrupo(GrupoConsorcio grupoAssociado) {
        return this.contratoController.getContratosByIdGrupo(grupoAssociado);
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
    public boolean cancelarContrato(Cliente cliente, GrupoConsorcio grupoAssociado) {
        return this.contratoController.cancelarContrato(cliente, grupoAssociado);
    }

    @Override
    public void updateStatusContrato(Contrato contrato, StatusContratoEnum statusContrato) {
        this.contratoController.updateStatusContrato(contrato, statusContrato);
    }

    @Override
    public void updateParcelasPagas(Contrato contrato) {
        this.contratoController.updateParcelasPagas(contrato);
    }

    @Override
    public void updateSaldoDevedor(Contrato contrato) {
        this.contratoController.updateSaldoDevedor(contrato);
    }

    @Override
    public void updateValorPago(Contrato contrato) {
        this.contratoController.updateValorPago(contrato);
    }

    @Override
    public void updateSaldoDevolucao(Contrato contrato) {
        this.contratoController.updateSaldoDevolucao(contrato);
    }

    @Override
    public void updateDataEncerramento(Contrato contrato, LocalDate dataEncerramento) {
        this.contratoController.updateDataEncerramento(contrato, dataEncerramento);
    }

    @Override
    public void deleteContrato(Contrato contrato) {
        this.contratoController.deleteContrato(contrato);
    }

    @Override
    public void createGrupoConsorcio(String nomeGrupo, int numeroMaximoParticipantes, double valorTotal, double taxaAdmin) {
        this.grupoConsorcioController.createGrupoConsorcio(nomeGrupo, numeroMaximoParticipantes, valorTotal, taxaAdmin);
    }

    @Override
    public GrupoConsorcio getGrupoById(String idGrupo) {
        return this.grupoConsorcioController.getGrupoById(idGrupo);
    }

    @Override
    public List<GrupoConsorcio> getAllGrupos() {
        return this.grupoConsorcioController.getAllGrupos();
    }

    @Override
    public void updateParticipantes(GrupoConsorcio grupoConsorcio, int novoNumParticipantes) {
        this.grupoConsorcioController.updateParticipantes(grupoConsorcio, novoNumParticipantes);
    }

    @Override
    public void updateNomeGrupo(GrupoConsorcio grupoConsorcio, String novoNome) {
        this.grupoConsorcioController.updateNomeGrupo(grupoConsorcio, novoNome);
    }

    @Override
    public void updateTaxaAdmin(GrupoConsorcio grupoConsorcio, double novaTaxa) {
        this.grupoConsorcioController.updateTaxaAdmin(grupoConsorcio, novaTaxa);
    }

    @Override
    public void updateStatusGrupo(GrupoConsorcio grupoConsorcio, StatusGrupoConsorcioEnum novoStatus) {
        this.grupoConsorcioController.updateStatusGrupo(grupoConsorcio, novoStatus);
    }

    @Override
    public void deleteGrupoConsorcio(GrupoConsorcio grupoConsorcio) {
        this.grupoConsorcioController.deleteGrupoConsorcio(grupoConsorcio);
    }

    @Override
    public void reajusteParcela(GrupoConsorcio grupoConsorcio) {
        this.grupoConsorcioController.reajusteParcela(grupoConsorcio);
    }

    @Override
    public double getValorPago(String idGrupo) {
        return this.grupoConsorcioController.getValorPago(idGrupo);
    }

    @Override
    public void efutuarLogin(String cpf, String senha) throws ClienteDuplicadoException, ClienteInexistenteException {
        this.loginController.efetuarLogin(cpf, senha);
    }

    @Override
    public Cliente getClienteLogado() {
        return this.loginController.getClienteLogado();
    }
}
