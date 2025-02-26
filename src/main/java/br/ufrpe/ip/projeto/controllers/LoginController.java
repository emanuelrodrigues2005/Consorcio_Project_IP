package br.ufrpe.ip.projeto.controllers;

import br.ufrpe.ip.projeto.exceptions.ClienteDuplicadoException;
import br.ufrpe.ip.projeto.exceptions.ClienteInexistenteException;
import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.repositories.ClienteRepository;
import br.ufrpe.ip.projeto.repositories.interfaces.IClienteRepository;

public class LoginController {
    private static LoginController instance;
    private final IClienteRepository repositoryLogin;
    private Cliente clienteLogado;

    private LoginController() {
        this.repositoryLogin = ClienteRepository.getInstance();
    }

    public static LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

    public void efetuarLogin(String cpf, String senha) throws ClienteDuplicadoException, ClienteInexistenteException {
        if (clienteLogado != null) {
          if (clienteLogado.getCpf().equals(cpf)) {
              throw new ClienteDuplicadoException(clienteLogado.getCpf());
          }
        }

        Cliente clienteASerLogado = this.repositoryLogin.getClienteByCpf(cpf);

        if (clienteASerLogado == null || clienteASerLogado.getSenha() == null || !clienteASerLogado.getSenha().equals(senha)) {
            throw new ClienteInexistenteException(cpf);
        } else {
            this.clienteLogado = clienteASerLogado;
        }
    }

    public Cliente getClienteLogado() {
        return this.clienteLogado;
    }
}
