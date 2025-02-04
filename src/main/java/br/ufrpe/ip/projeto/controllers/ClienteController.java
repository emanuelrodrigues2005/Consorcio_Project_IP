package br.ufrpe.ip.projeto.controllers;

import br.ufrpe.ip.projeto.repositories.ClienteRepository;
import br.ufrpe.ip.projeto.repositories.interfaces.IClienteRepository;

public class ClienteController {
    private static ClienteController instancia;
    private final IClienteRepository repositorioCliente;

    public ClienteController() {
        this.repositorioCliente = ClienteRepository.getInstance();
    }

    public static ClienteController getInstance() {
        if (instancia == null) {
            instancia = new ClienteController();
        }
        return instancia;
    }

    
}
