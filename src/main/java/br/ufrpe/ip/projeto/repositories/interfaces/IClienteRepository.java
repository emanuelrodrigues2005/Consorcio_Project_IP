package br.ufrpe.ip.projeto.repositories.interfaces;

import br.ufrpe.ip.projeto.models.Cliente;

import java.util.ArrayList;

public interface IClienteRepository {
    ArrayList<Cliente> getAllClientes();

    Cliente getClienteByCpf(String cpf);

    Cliente createCliente(Cliente cliente);

    void updateNome(Cliente cliente, String nome);

    void updateCpf(Cliente cliente, String cpf);

    void updateTelefone(Cliente cliente, String telefone);

    void updateEmail(Cliente cliente, String email);

    void deleteCliente(String cpf);
}
