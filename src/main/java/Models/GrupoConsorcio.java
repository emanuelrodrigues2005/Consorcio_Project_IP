package Models;

import Enums.StatusContratoEnum;
import Enums.StatusGrupoConsorcioEnum;

import java.util.ArrayList;

public class GrupoConsorcio {
    private String nomeGrupo;
    private int numeroParticipantes;
    private double valorTotal;
    private double taxaAdmin;
    private double valorParcela;
    private ArrayList<Contrato> listaContratos;
    private ArrayList<Contrato> listaContratosContemplados;
    private StatusGrupoConsorcioEnum statusGrupoConsorcio;

    public GrupoConsorcio(String nomeGrupo, int numeroParticipantes, double valorTotal, double taxaAdmin) {
        this.nomeGrupo = nomeGrupo;
        this.numeroParticipantes = numeroParticipantes;
        this.valorTotal = valorTotal;
        this.taxaAdmin = taxaAdmin;
        this.valorParcela = ((getValorTotal() + getValorTotal() * getTaxaAdmin()) / getNumeroParticipantes());
        this.listaContratos = new ArrayList<>();
        this.listaContratosContemplados = new ArrayList<>();
        this.statusGrupoConsorcio = StatusGrupoConsorcioEnum.ATIVO;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public int getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public void setNumeroParticipantes(int numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getTaxaAdmin() {
        return taxaAdmin;
    }

    public void setTaxaAdmin(double taxaAdmin) {
        this.taxaAdmin = taxaAdmin;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public ArrayList<Contrato> getListaContratos() {
        return listaContratos;
    }

    public void setListaContratos(ArrayList<Contrato> listaContratos) {
        this.listaContratos = listaContratos;
    }

    public ArrayList<Contrato> getListaContratosContemplados() {
        return listaContratosContemplados;
    }

    public StatusGrupoConsorcioEnum getStatusGrupoConsorcio() {
        return statusGrupoConsorcio;
    }

    public void setStatusGrupoConsorcio(StatusGrupoConsorcioEnum statusGrupoConsorcio) {
        this.statusGrupoConsorcio = statusGrupoConsorcio;
    }

    public void atualizarTaxa(double novaTaxaAdmin) {
        if(novaTaxaAdmin >= 0) {
            this.taxaAdmin = novaTaxaAdmin;
            System.out.printf("Taxa de administração atualizada para: %d", novaTaxaAdmin);
        } else {
            System.out.println("Não é possível atualizar a taxa de administração, pois é um valor negativo");
        }
    }

    public void encerrarGrupo(StatusGrupoConsorcioEnum novoStatus) {
        if(novoStatus == StatusGrupoConsorcioEnum.ENCERRADO) {
            this.statusGrupoConsorcio = novoStatus;
            System.out.println("Grupo encerrado");
        } else {
            System.out.println("Não foi possível encerrar o grupo");
        }
    }

    public void exibirSaldoDevedor() {
        System.out.println("Saldos Devedores: ");
        for(Contrato c : listaContratos) {
            if(c.getSaldoDevedor() > 0) {
                System.out.printf("Cliente: %s | Saldo Devedor: %d\n", c.getCliente().getNome(), c.getSaldoDevedor());
            }
        }
    }

    public void exibirFinancas() {
        double valorArrecadado = 0;
        double valorPendente = 0;
        for(Contrato c : listaContratos) {
            valorArrecadado += (c.getParcelasPagas() * this.getValorParcela());
        }

        valorPendente = this.getValorTotal() - valorArrecadado;

        System.out.println("Finanças: ");
        System.out.printf("Valor Arrecadado: %.2f\n", valorArrecadado);
        System.out.printf("Valor Pendente: %.2f\n", valorPendente);
    }

    public void reajusteParcela() {
        int desistentes = 0;

        for(Contrato c : listaContratos) {
            if(c.getStatusContrato() == StatusContratoEnum.ENCERRADO) {
                desistentes++;
            }
        }

        if(desistentes > 0) {
            int novoNumeroParticipantes = this.getNumeroParticipantes() - desistentes;
            setNumeroParticipantes(novoNumeroParticipantes);

            double novoValorParcela = ((getValorTotal() + getValorTotal() * getTaxaAdmin()) / novoNumeroParticipantes);
            this.setValorParcela(novoValorParcela);

            System.out.printf("Parcela reajustada: %.2f | Número de desistências: %d\n", novoValorParcela, novoNumeroParticipantes);
        } else {
            System.out.println("Não houve desistências");
        }
    }

    public String toString() {
        return "Grupo: " + getNomeGrupo() + " { "
                + "\n  Número de Participantes: " + getNumeroParticipantes() + " | "
                + "\n  Valor total do Consórcio: " + getValorTotal() + " | "
                + "\n  Valor de Parcela: " + getValorParcela() + " | "
                + "\n  Taxa de Administração: " + getTaxaAdmin()
                + "\n  Status: " + getStatusGrupoConsorcio()
                + "\n}";
    }
}