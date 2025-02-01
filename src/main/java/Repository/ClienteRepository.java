package Repository;

import Models.Cliente;
import Models.Contrato;

import java.util.ArrayList;

public class ClienteRepository {
    private ArrayList<Cliente> clientes;

    public ClienteRepository() {
        this.clientes = new ArrayList<>();
    }

    public void getAllClientes() {
        if (clientes.isEmpty()) {
            System.out.println("\nNenhum cliente foi cadastrado!\n");
        } else {
            System.out.println("\nLista de clientes:\n");
            for (Cliente cliente : clientes) {
                System.out.printf("Cliente: %s\n", cliente.getNome());

                for (Contrato contrato : cliente.getContratos()) {
                    System.out.printf("%s;\nSaldo Devedor: %.2f;\nStatus do Contrato: %s.\n\n", contrato.getGrupoAssociado(), contrato.getSaldoDevedor(), contrato.getStatusContrato());
                }
            }
        }
    }

    public Cliente getClienteByCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                System.out.println(cliente);
                return cliente;
            }
        }
        return null;
    }

    public Cliente createCliente(String nome, String cpf, String telefone, String email) {
        if (getClienteByCpf(cpf) == null) {
            Cliente cliente = new Cliente(nome, cpf, telefone, email);

            clientes.add(cliente);

            System.out.println(cliente);

            System.out.printf("Cliente '%s' adicionado com sucesso!\n\n", cliente.getNome());
            return cliente;
        } else {
            System.out.println("Cpf já cadastrado para um cliente!");
        }
        return null;
    }

    public Cliente updateCliente(String cpf, String novoNome, String novoTelefone, String novoEmail) {
        Cliente clienteTemp = getClienteByCpf(cpf);

        if (clienteTemp != null) {
            if (!novoNome.isEmpty()) {
                clienteTemp.setNome(novoNome);
            }

            if (!novoTelefone.isEmpty()) {
                clienteTemp.setTelefone(novoTelefone);
            }

            if (!novoEmail.isEmpty()) {
                clienteTemp.setEmail(novoEmail);
            }

            System.out.println(clienteTemp);
            System.out.println("\nCliente atualizado com sucesso!\n");
            return clienteTemp;
        } else {
            System.out.println("\nNenhum cliente encontrado com o CPF informado!\n");
        }
        return null;
    }

    public void deleteCliente(String cpf) {
        Cliente clienteTemp = getClienteByCpf(cpf);

        if (clienteTemp != null) {
            clientes.remove(clienteTemp);
            System.out.printf("\nCliente '%s' deletado com sucesso!\n", clienteTemp.getNome());
        } else {
            System.out.println("\nNenhum cliente encontrado com o CPF informado!\n");
        }
    }
}