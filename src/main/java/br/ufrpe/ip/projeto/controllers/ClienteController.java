package br.ufrpe.ip.projeto.controllers;

import br.ufrpe.ip.projeto.exceptions.ArrayVazioException;
import br.ufrpe.ip.projeto.exceptions.CampoInvalidoException;
import br.ufrpe.ip.projeto.exceptions.ClienteDuplicadoException;
import br.ufrpe.ip.projeto.exceptions.ClienteInexistenteException;
import br.ufrpe.ip.projeto.models.Cliente;
import br.ufrpe.ip.projeto.repositories.ClienteRepository;
import br.ufrpe.ip.projeto.repositories.interfaces.IClienteRepository;

import java.util.List;

public class ClienteController {
    private static ClienteController instancia;
    private final IClienteRepository repositorioCliente;

    private ClienteController() {
        this.repositorioCliente = ClienteRepository.getInstance();
    }

    public static ClienteController getInstance() {
        if (instancia == null) {
            instancia = new ClienteController();
        }
        return instancia;
    }

    public List<Cliente> getAllClientes() throws ArrayVazioException{
        if (repositorioCliente.getAllClientes().isEmpty()){
            throw new ArrayVazioException("Não Há Clientes Registrados!");
        }
        return this.repositorioCliente.getAllClientes();
    }// exceptions: ArrayVazio

    public Cliente getClienteByCpf(String cpf) throws ClienteInexistenteException{
        if(repositorioCliente.getClienteByCpf(cpf) == null) {  throw new ClienteInexistenteException(cpf); }
        return this.repositorioCliente.getClienteByCpf(cpf);
    }

    public void createCliente(String nomeCliente, String cpfCliente, String telefoneCliente, String emailCliente, String senhaCliente) throws CampoInvalidoException, ClienteDuplicadoException {
        if(!validarCpf(cpfCliente)) {
            throw new CampoInvalidoException(cpfCliente);
        }
        if(repositorioCliente.getClienteByCpf(cpfCliente) != null) {
            throw new ClienteDuplicadoException(cpfCliente);
        }
        repositorioCliente.createCliente(nomeCliente, cpfCliente, telefoneCliente, emailCliente, senhaCliente);
        this.repositorioCliente.salvarArquivo();
    }

    public void updateNome(String nomeCliente, String cpfCliente) throws CampoInvalidoException, ClienteInexistenteException{
        if (nomeCliente.isEmpty() || cpfCliente.isEmpty()) {
            throw new CampoInvalidoException("Campos Vazios!");
        }else if (getClienteByCpf(cpfCliente) == null) {
            throw new ClienteInexistenteException(cpfCliente);
        }
        else {
            this.repositorioCliente.updateNome(getClienteByCpf(cpfCliente), nomeCliente);
            this.repositorioCliente.salvarArquivo();
        }
    }

    public void updateTelefone(String telefoneCliente, String cpfCliente) throws CampoInvalidoException, ClienteInexistenteException {
        if (telefoneCliente.isEmpty() || cpfCliente.isEmpty()) {
            throw new CampoInvalidoException("Campos Vazios!");
        }
        else if (getClienteByCpf(cpfCliente) == null) {
            throw new ClienteInexistenteException(cpfCliente);
        }
        else {
            this.repositorioCliente.updateTelefone(getClienteByCpf(cpfCliente), telefoneCliente);
            this.repositorioCliente.salvarArquivo();
        }
    }

    public void updateEmail(String emailCliente, String cpfCliente) throws CampoInvalidoException, ClienteInexistenteException {
        if (emailCliente.isEmpty() || cpfCliente.isEmpty()) {
            throw new CampoInvalidoException("Campos Vazios!");
        }
        else if (getClienteByCpf(cpfCliente) == null) {
            throw new ClienteInexistenteException(cpfCliente);
        }
        else {
            this.repositorioCliente.updateEmail(getClienteByCpf(cpfCliente), emailCliente);
            this.repositorioCliente.salvarArquivo();
        }
    }

    public void updateSenha(String senhaCliente, String cpfCliente) throws CampoInvalidoException, ClienteInexistenteException{
        if (senhaCliente.isEmpty() || cpfCliente.isEmpty()) {
            throw new CampoInvalidoException("Campos Vazios!");
        }
        else if (getClienteByCpf(cpfCliente) == null) {
            throw new ClienteInexistenteException(cpfCliente);
        }
        else {
            this.repositorioCliente.updateSenha(getClienteByCpf(cpfCliente), senhaCliente);
            this.repositorioCliente.salvarArquivo();
        }
    }

    public void deleteCliente(String cpfCliente) throws ClienteInexistenteException {
        if(repositorioCliente.getClienteByCpf(cpfCliente) == null) {
           throw new ClienteInexistenteException(cpfCliente);
        }
        repositorioCliente.deleteCliente(cpfCliente);
        this.repositorioCliente.salvarArquivo();
    }

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
