package br.ufrpe.ip.projeto.controllers;

import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.repositories.ClienteRepository;
import br.ufrpe.ip.projeto.repositories.interfaces.IClienteRepository;

public class LoginController {
    private static LoginController instance;
    private final IClienteRepository repositoryLogin;
    private Cliente clienteLogado = new Cliente("a", "a", "a", "a", "a");

    private LoginController() {
        this.repositoryLogin = ClienteRepository.getInstance();
    }

    public static LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

    public void efetuarLogin(String cpf, String senha) {
        if (this.clienteLogado != null) {
            // throw new ClienteJaLogado(parãmetro a definir);
        }

        Cliente clienteASerLogado = this.repositoryLogin.getClienteByCpf(cpf);

        if (clienteASerLogado.getSenha().equals(senha)) {
            this.clienteLogado = clienteASerLogado;
        } else {
            //throw new ClienteSenhaIncorreta(parâmetro a definir);
        }
    } //exceptions: ClienteInexistente, ClienteJaLogado, ClienteSenhaIncorreta

    public Cliente getClienteLogado() {
        return this.clienteLogado;
    }
}
