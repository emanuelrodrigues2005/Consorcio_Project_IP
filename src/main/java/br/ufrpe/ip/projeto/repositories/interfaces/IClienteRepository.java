package br.ufrpe.ip.projeto.repositories.interfaces;

import br.ufrpe.ip.projeto.models.Cliente;

import java.util.List;

public interface IClienteRepository {
    List<Cliente> getAllClientes();

    Cliente getClienteByCpf(String cpf);

    void createCliente(String nomeCliente, String cpfCliente, String telefoneCliente, String emailCliente, String senhaCliente);

    void updateNome(Cliente cliente, String nome);

    void updateTelefone(Cliente cliente, String telefone);

    void updateEmail(Cliente cliente, String email);

    void updateSenha(Cliente cliente, String senha);

    void deleteCliente(String cpf);
}
