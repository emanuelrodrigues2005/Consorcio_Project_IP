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

public interface IConsorcio {
    List<Boleto> getAllBoletos();

    String getIdBoleto(Contrato contratoBoleto, int numeroParcela);

    List<Boleto> getAllBoletosByContrato(String idContrato);

    Boleto getBoletoById(String idBoleto);

    Boleto getBoletoByContrato(Contrato contratoBoleto);

    void createBoleto(Contrato contratoBoleto);

    void updateDataPagamento(Boleto boleto);

    void updateStatusBoleto(Boleto boleto, StatusBoletoEnum statusBoleto);

    void deleteBoleto(String idBoleto);

    void atualizarBoletoVencidos(Boleto boleto);

    void verificarBoletoVencidos();

    void realizarPagamento(String idBoleto);

    List<Cliente> getAllClientes() throws ArrayVazioException;

    Cliente getClienteByCpf(String cpf) throws ClienteInexistenteException;

    void createCliente(String nomeCliente, String cpfCliente, String telefoneCliente, String emailCliente, String senhaCliente)
            throws CampoInvalidoException, ClienteDuplicadoException ;

    void updateNome(String nomeCliente, String cpfCliente) throws CampoInvalidoException, ClienteInexistenteException;

    void updateTelefone(String telefoneCliente, String cpfCliente) throws CampoInvalidoException, ClienteInexistenteException;

    void updateEmail(String emailCliente, String cpfCliente) throws ClienteInexistenteException, CampoInvalidoException;

    void updateSenha(String senhaCliente, String cpfCliente) throws CampoInvalidoException, ClienteInexistenteException;

    void deleteCliente(String cpfCliente) throws ClienteInexistenteException;

    boolean validarCpf(String cpfCliente);

    List<Contemplacao> getAllContemplacao();

    Contemplacao getContemplacaoById(String idContemplacao);

    Contemplacao createContemplacao(Contrato contrato);

    void updateDataContemplacao(String idContemplacao, LocalDate dataContemplacao);

    void deleteContemplacao(String idContemplacao);

    String sorteioContemplacao();

    void createContrato(Cliente cliente, GrupoConsorcio grupoConsorcio);

    void registrarPagamento(Cliente cliente, GrupoConsorcio grupoConsorcio, Boleto boleto);

    Contrato getContratoByCPFNomeGrupo(Cliente cliente, GrupoConsorcio grupoAssociado);

    List<Contrato> getAllContratos();

    List<Contrato> getContratosByIdGrupo(GrupoConsorcio grupoAssociado);

    List<Contrato> getAllContratosByCPF(Cliente cliente);

    List<Cliente> getAllClientesByGrupo(GrupoConsorcio grupoConsorcio);

    boolean existeContrato(Contrato contrato);

    Contrato getContratoByIdContrato(String idContrato);

    boolean cancelarContrato(Cliente cliente, GrupoConsorcio grupoAssociado);

    void updateStatusContrato(Contrato contrato, StatusContratoEnum statusContrato);

    void updateParcelasPagas(Contrato contrato);

    void updateSaldoDevedor(Contrato contrato);


    void updateValorPago(Contrato contrato);

    void updateSaldoDevolucao(Contrato contrato);

    void updateDataEncerramento(Contrato contrato, LocalDate dataEncerramento);

    void deleteContrato(Contrato contrato);

    void createGrupoConsorcio(String nomeGrupo, int numeroMaximoParticipantes, double valorTotal, double taxaAdmin);

    GrupoConsorcio getGrupoById(String idGrupo);

    List<GrupoConsorcio> getAllGrupos();

    void updateParticipantes(GrupoConsorcio grupoConsorcio, int novoNumParticipantes);

    void updateNomeGrupo(GrupoConsorcio grupoConsorcio, String novoNome);

    void updateTaxaAdmin(GrupoConsorcio grupoConsorcio, double novaTaxa);

    void updateStatusGrupo(GrupoConsorcio grupoConsorcio, StatusGrupoConsorcioEnum novoStatus);

    void deleteGrupoConsorcio(GrupoConsorcio grupoConsorcio);

    void reajusteParcela(GrupoConsorcio grupoConsorcio);

    double getValorPago(String idGrupo);

    void efutuarLogin(String cpf, String senha) throws ClienteDuplicadoException, ClienteInexistenteException;

    Cliente getClienteLogado();
}
