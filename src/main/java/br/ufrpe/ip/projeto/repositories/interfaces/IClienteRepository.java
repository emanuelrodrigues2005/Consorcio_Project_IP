package br.ufrpe.ip.projeto.repositories.interfaces;

import br.ufrpe.ip.projeto.models.Cliente;

public interface IClienteRepository {
    void getAllClientes();

    Cliente getClienteByCpf(String cpf);

    Cliente createCliente(Cliente cliente);

    Cliente updateCliente(Cliente cliente);

    void deleteCliente(String cpf);
}
