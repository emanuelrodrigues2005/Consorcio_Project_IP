package br.ufrpe.ip.projeto.repositories.interfaces;

import br.ufrpe.ip.projeto.models.Boleto;
import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.models.GrupoConsorcio;
import br.ufrpe.ip.projeto.repositories.ContratoRepository;

public interface IContratoRepository {

    void getAllContratos();
    
    void getAllContratosByCPF();

    Contrato getContratoByCPFNomeGrupo();

    String getContratosByNomeGrupo(GrupoConsorcio grupoConsorcio);

    void createContrato();

    void pagarParcela();

    void cancelarContrato();

    void deleteContrato();
}