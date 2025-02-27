package br.ufrpe.ip.projeto.controllers;

import br.ufrpe.ip.projeto.enums.StatusBoletoEnum;
import br.ufrpe.ip.projeto.enums.StatusContratoEnum;
import br.ufrpe.ip.projeto.enums.StatusGrupoConsorcioEnum;
import br.ufrpe.ip.projeto.exceptions.*;
import br.ufrpe.ip.projeto.models.*;

import java.time.LocalDate;
import java.util.List;

public interface IConsorcio {
    List<Boleto> getAllBoletos() throws ArrayVazioException;

    String getIdBoleto(Contrato contratoBoleto, int numeroParcela) throws BoletoInexistenteException, CampoInvalidoException, IdBoletoInexistenteException;

    Boleto getBoletoById(String idBoleto) throws BoletoInexistenteException, CampoInvalidoException;

    Boleto getBoletoByContrato(Contrato contratoBoleto) throws BoletoInexistenteException, CampoInvalidoException, ContratoInvalidoException;

    void createBoleto(Contrato contratoBoleto) throws CampoInvalidoException;

    void updateDataPagamento(Boleto boleto) throws BoletoInexistenteException, CampoInvalidoException;

    void updateStatusBoleto(Boleto boleto, StatusBoletoEnum statusBoleto) throws BoletoInexistenteException, CampoInvalidoException;

    void deleteBoleto(String idBoleto) throws BoletoInexistenteException, CampoInvalidoException;

    void atualizarBoletoVencidos(Boleto boleto) throws BoletoInexistenteException, CampoInvalidoException;

    void verificarBoletoVencidos() throws BoletoInexistenteException, CampoInvalidoException;

    void realizarPagamento(String idBoleto) throws BoletoInexistenteException, CampoInvalidoException;

    List<Cliente> getAllClientes();

    Cliente getClienteByCpf(String cpf);

    void createCliente(String nomeCliente, String cpfCliente, String telefoneCliente, String emailCliente, String senhaCliente);

    void updateNome(String nomeCliente, String cpfCliente);

    void updateTelefone(String telefoneCliente, String cpfCliente);

    void updateEmail(String emailCliente, String cpfCliente);

    void updateSenha(String senhaCliente, String cpfCliente);

    void deleteCliente(String cpfCliente);

    boolean validarCpf(String cpfCliente);

    List<Contemplacao> getAllContemplacao() throws ArrayVazioException;

    Contemplacao getContemplacaoById(String idContemplacao) throws ContemplacaoInexistenteException;

    Contemplacao createContemplacao(Contrato contrato) throws ContemplacaoInexistenteException, CampoInvalidoException;

    void updateDataContemplacao(String idContemplacao, LocalDate dataContemplacao) throws ContemplacaoInexistenteException, DataContemplacaoInvalidaException;

    void deleteContemplacao(String idContemplacao) throws ContemplacaoInexistenteException;

    String sorteioContemplacao() throws ContratoInvalidoException, ContemplacaoInexistenteException, ArrayVazioException, CampoInvalidoException;

    void createContrato(Cliente cliente, GrupoConsorcio grupoConsorcio) throws CampoInvalidoException;

    void registrarPagamento(Cliente cliente, GrupoConsorcio grupoConsorcio, Boleto boleto);

    Contrato getContratoByCPFNomeGrupo(Cliente cliente, GrupoConsorcio grupoAssociado);

    List<Contrato> getAllContratos();

    List<Contrato> getContratosByIdGrupo(GrupoConsorcio grupoAssociado);

    List<Contrato> getAllContratosByCPF(Cliente cliente);

    boolean existeContrato(Contrato contrato);

    Contrato getContratoByIdContrato(String idContrato);

    boolean cancelarContrato(Cliente cliente, GrupoConsorcio grupoAssociado) throws ContratoInvalidoException, CampoInvalidoException;

    void updateStatusContrato(Contrato contrato, StatusContratoEnum statusContrato) throws ContratoInvalidoException, CampoInvalidoException;

    void updateParcelasPagas(Contrato contrato) throws ContratoInvalidoException;

    void updateSaldoDevedor(Contrato contrato) throws ContratoInvalidoException;


    void updateValorPago(Contrato contrato) throws ContratoInvalidoException;

    void updateSaldoDevolucao(Contrato contrato) throws ContratoInvalidoException;

    void updateDataEncerramento(Contrato contrato, LocalDate dataEncerramento) throws ContratoInvalidoException;

    void deleteContrato(Contrato contrato) throws ContratoInvalidoException;

    void createGrupoConsorcio(String nomeGrupo, int numeroMaximoParticipantes, double valorTotal, double taxaAdmin) throws CampoInvalidoException;

    GrupoConsorcio getGrupoById(String idGrupo) throws IdGrupoConsorcioInexistenteException;

    List<GrupoConsorcio> getAllGrupos() throws ArrayVazioException;

    void updateParticipantes(GrupoConsorcio grupoConsorcio, int novoNumParticipantes) throws GrupoConsorcioInexistenteException, CampoInvalidoException;

    void updateNomeGrupo(GrupoConsorcio grupoConsorcio, String novoNome) throws GrupoConsorcioInexistenteException, CampoInvalidoException;

    void updateTaxaAdmin(GrupoConsorcio grupoConsorcio, double novaTaxa) throws GrupoConsorcioInexistenteException, CampoInvalidoException;

    void updateStatusGrupo(GrupoConsorcio grupoConsorcio, StatusGrupoConsorcioEnum novoStatus);

    void deleteGrupoConsorcio(GrupoConsorcio grupoConsorcio) throws GrupoConsorcioInexistenteException;

    void reajusteParcela(GrupoConsorcio grupoConsorcio);

    double getValorPago(String idGrupo) throws IdGrupoConsorcioInexistenteException;

    void efutuarLogin(String cpf, String senha);

    Cliente getClienteLogado();
}
