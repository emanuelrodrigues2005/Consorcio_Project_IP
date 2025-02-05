package br.ufrpe.ip.projeto.repositories.interfaces;

import java.util.List;

import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import br.ufrpe.ip.projeto.repositories.ContratoRepository;

public interface IContratoRepository {

    List<Contrato> getAllContratos();
    
    List<Contrato> getAllContratosByCPF(Cliente cliente);

    Contrato getContratoByCPFNomeGrupo(Cliente cliente, GrupoConsorcio grupoConsorcio);

    List<Contrato> getContratosByNomeGrupo(GrupoConsorcio grupoConsorcio);

    void createContrato(Cliente cliente, GrupoConsorcio grupoConsorcio);

    void updateParcelasPagas(Contrato contrato);

    void updateSaldoDevedor(Contrato contrato);

    void updateValorPago(Contrato contrato);

    void updateSaldoDevolução(Contrato contrato);

    void deleteContrato(Contrato contrato);
}