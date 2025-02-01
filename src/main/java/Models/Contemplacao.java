package Models;

import java.time.LocalDate;
import Enums.StatusContratoEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Contemplacao {
	private static int idContemplacao;
    private Contrato contratoContemplacao;
	private LocalDate dataContemplacao;
	
	public Contemplacao(Contrato contratoContemplacao) {
		this.contratoContemplacao = contratoContemplacao;
		this.dataContemplacao = java.time.LocalDate.now();
		idContemplacao = new Random().nextInt(10000000) + 1;
	}

	public Contrato getContratoContemplacao() {
		return contratoContemplacao;
	}

	public void setContratoContemplacao(Contrato contratoContemplacao) {
		this.contratoContemplacao = contratoContemplacao;
	}

	public LocalDate getDataContemplacao() {
		return dataContemplacao;
	}

	public void setDataContemplacao(LocalDate dataContemplacao) {
		this.dataContemplacao = dataContemplacao;
	}

	public int getIdContemplacao() {
		return idContemplacao;
	}

	public void sorteioContemplacao() {
		Random rand = new Random();
		List<Contrato> contratosAtivos = new ArrayList<>();
    
        for (Contrato contrato : contratoContemplacao.getGrupoAssociado().getListaContratos()) {
        if (contrato.getStatusContrato() == StatusContratoEnum.ATIVO) {
            contratosAtivos.add(contrato);
        }
    }
      if (!contratosAtivos.isEmpty()) {
               int indiceSorteado = rand.nextInt(contratosAtivos.size());
               Contrato contratoContemplado = contratosAtivos.get(indiceSorteado);
               contratoContemplado.setStatusContrato(StatusContratoEnum.CONTEMPLADO);
               addOrdenarListaContemp(contratoContemplado);
               System.out.println("\nContrato contemplado: " + contratoContemplado.getCliente().getNome());
    } else {
        System.out.println("\nNão há contratos ativos para contemplação.");
      }
	}
	private void addOrdenarListaContemp (Contrato contrato) {
        if(contrato != null){
		GrupoConsorcio grupo = contrato.getGrupoAssociado();
		grupo.getListaContratosContemplados().add(contrato);
        } else {
            System.out.println("\nContrato inexistente! Contemplação não adicionada!");
        }
	}

	@Override
	public String toString() {
		return "\n" + getContratoContemplacao() + "(Data: " + getDataContemplacao() + ")\nId: " + getIdContemplacao();
	}
}
