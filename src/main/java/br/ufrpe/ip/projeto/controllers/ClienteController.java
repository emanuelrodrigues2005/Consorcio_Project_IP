package br.ufrpe.ip.projeto.controllers;

import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.repositories.ClienteRepository;
import br.ufrpe.ip.projeto.repositories.interfaces.IClienteRepository;

import java.util.ArrayList;

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

    public ArrayList<Cliente> getAllClientes() {
        return this.repositorioCliente.getAllClientes();
    }// exceptions: ArrayVazio

    public Cliente getClienteByCpf(String cpf) {
        return this.repositorioCliente.getClienteByCpf(cpf);
    } //exceptions: ClienteInexistente

    public void createCliente(String nomeCliente, String cpfCliente, String telefoneCliente, String emailCliente) {
        if(validarCpf(cpfCliente)) {
            if(repositorioCliente.getClienteByCpf(cpfCliente) == null) {
                repositorioCliente.createCliente(nomeCliente, cpfCliente, telefoneCliente, emailCliente);
            }
        }
    } //exceptions: ClienteDuplicado, CampoInvalido

    public void updateNome(String nomeCliente, String cpfCliente) {
        this.repositorioCliente.updateNome(getClienteByCpf(cpfCliente), nomeCliente);
    } //exceptions: CampoInvalido, ClienteInexistente

    public void updateTelefone(String telefoneCliente, String cpfCliente) {
        this.repositorioCliente.updateTelefone(getClienteByCpf(cpfCliente), telefoneCliente);
    } //exceptions: CampoInvalido, ClienteInexistente

    public void updateEmail(String emailCliente, String cpfCliente) {
        this.repositorioCliente.updateEmail(getClienteByCpf(cpfCliente), emailCliente);
    } //exceptions: CampoInvalido, ClienteInexistente

    public void deleteCliente(String cpfCliente) {
        if(repositorioCliente.getClienteByCpf(cpfCliente) != null) {
            repositorioCliente.deleteCliente(cpfCliente);
        }
    } //exceptions: ClienteInexistente

    private boolean verificarDigito(String cpf, int posicao) {
        int soma = 0;
        int peso = posicao + 1;

        for(int i = 0; i < posicao; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso;
            peso--;
        }

        int resto = soma % 11;
        int digitoVerificador;

        if(resto < 2) {
            digitoVerificador = 0;
        } else {
            digitoVerificador = 11 - resto;
        }
        return digitoVerificador == Character.getNumericValue(cpf.charAt(posicao));
    }

    public boolean validarCpf(String cpf) {
        if(cpf == null || cpf.length() != 11) {
            return false;
        }
        return verificarDigito(cpf, 9) && verificarDigito(cpf, 10);
    }
}
