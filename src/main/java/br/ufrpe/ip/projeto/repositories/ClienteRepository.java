package br.ufrpe.ip.projeto.repositories;

import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.models.Contrato;
import br.ufrpe.ip.projeto.repositories.interfaces.IClienteRepository;

import java.io.Serializable;
import java.util.ArrayList;

public class ClienteRepository implements IClienteRepository {
    private ArrayList<Cliente> clientes;
    private static ClienteRepository instance = null;

    private ClienteRepository() {
        this.clientes = new ArrayList<>();
    }

    public ClienteRepository getInstance() {
        if(ClienteRepository.instance == null) {
            ClienteRepository.instance = new ClienteRepository();
        }
        return ClienteRepository.instance;
    }

    @Override
    public void getAllClientes() {
        for (Cliente cliente : clientes) {
            cliente.toString();
        }
    }

    @Override
    public Cliente getClienteByCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if(cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

   @Override
   public Cliente createCliente(Cliente cliente) {
        this.clientes.add(cliente);
        return cliente;
   }

   @Override
   public Cliente updateCliente(Cliente cliente) {
        Cliente c = this.getClienteByCpf(cliente.getCpf());
        c.setNome(cliente.getNome());
        c.setCpf(cliente.getCpf());
        c.setTelefone(cliente.getTelefone());
        c.setEmail(cliente.getEmail());
        return c;
   }

   @Override
    public void deleteCliente(String cpf) {
        clientes.remove(getClienteByCpf(cpf));
   }
}