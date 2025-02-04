package br.ufrpe.ip.projeto.controllers;

import br.ufrpe.ip.projeto.repositories.ContratoRepository;
import br.ufrpe.ip.projeto.repositories.interfaces.IContratoRepository;

public class ContratoController {
    private static ContratoController instancia;
    private final IContratoRepository repositorioContrato;
    
    public ContratoController() {
        this.repositorioContrato = ContratoRepository.getInstancia();
    }

    public static ContratoController getInstancia() {
        if (instancia == null) {
            instancia = new ContratoController();
        }
        return instancia;
    }
}
