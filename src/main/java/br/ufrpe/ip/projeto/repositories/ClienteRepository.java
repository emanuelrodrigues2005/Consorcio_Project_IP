package br.ufrpe.ip.projeto.repositories;

import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.repositories.interfaces.IClienteRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClienteRepository implements IClienteRepository, Serializable {
    private ArrayList<Cliente> clientes;
    private static ClienteRepository instance = null;

    private ClienteRepository() {
        this.clientes = new ArrayList<>();
    }

    public static ClienteRepository getInstance() {
        if(ClienteRepository.instance == null) {
            ClienteRepository.instance = ClienteRepository.lerArquivo();
        }
        return ClienteRepository.instance;
    }

    private static ClienteRepository lerArquivo() {
        ClienteRepository instanceLocal;

        File in = new File("clientes.dat");
        FileInputStream fis;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(in);
            ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            instanceLocal = (ClienteRepository) o;
        } catch (Exception e) {
            instanceLocal = new ClienteRepository();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    //ver oq colocar aqui
                }
            }
        }
        return instanceLocal;
    }

    @Override
    public List<Cliente> getAllClientes() {
        return Collections.unmodifiableList(clientes);
    }

    @Override
    public void salvarArquivo() {
        if(ClienteRepository.getInstance() == null) {
            return;
        }

        File out = new File("clientes.dat");
        FileOutputStream fos;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(out);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ClienteRepository.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    //Ver oq colocar aqui
                }
            }
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
   public void createCliente(String nomeCliente, String cpfCliente, String telefoneCliente, String emailCliente, String senhaCliente) {
        Cliente cliente = new Cliente(nomeCliente, cpfCliente, telefoneCliente, emailCliente, senhaCliente);
        this.clientes.add(cliente);
   }

   @Override
    public void updateNome(Cliente cliente, String nome) {
        cliente.setNome(nome);
    }

    @Override
    public void updateTelefone(Cliente cliente, String telefone) {
        cliente.setTelefone(telefone);
    }

    @Override
    public void updateEmail(Cliente cliente, String email) {
        cliente.setEmail(email);
    }

    @Override
    public void updateSenha(Cliente cliente, String senha) {
        cliente.setSenha(senha);
    }

   @Override
    public void deleteCliente(String cpf) {
        clientes.remove(getClienteByCpf(cpf));
   }
}