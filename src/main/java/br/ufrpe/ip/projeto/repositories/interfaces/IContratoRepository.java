package br.ufrpe.ip.projeto.repositories.interfaces;

import java.time.LocalDate;
import java.util.List;

import br.ufrpe.ip.projeto.enums.StatusContratoEnum;
import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;

public interface IContratoRepository {

    List<Contrato> getAllContratos();
    
    List<Contrato> getAllContratosByCPF(Cliente cliente);

    Contrato getContratoByCPFIdGrupo(Cliente cliente, GrupoConsorcio grupoConsorcio);

    List<Contrato> getContratosByIdGrupo(GrupoConsorcio grupoConsorcio);

    boolean existeContrato(Contrato contrato);

    Contrato getContratoByIdContrato(String idContrato);

    void createContrato(Cliente cliente, GrupoConsorcio grupoConsorcio);

    void updateParcelasPagas(Contrato contrato);

    void updateSaldoDevedor(Contrato contrato);

    void updateValorPago(Contrato contrato);

    void updateSaldoDevolucao(Contrato contrato);

    void updateStatusContrato(Contrato contrato, StatusContratoEnum status);

    void updateDataEncerramento(Contrato contrato, LocalDate data);

    void deleteContrato(Contrato contrato);
}