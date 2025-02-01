package Repository; 

import java.time.LocalDate;
import java.util.ArrayList;

import Models.Contemplacao;
import Models.Contrato;

public class ContemplacaoRepository {
    private ArrayList<Contemplacao> contemplacoes;
    
    public ContemplacaoRepository() {
        this.contemplacoes = new ArrayList<>();
    }


    public void createContemplacao(Contrato contratoContemplacao){
        if (contratoContemplacao != null) {
            Contemplacao contemplacao = new Contemplacao(contratoContemplacao);
            contemplacoes.add(contemplacao);
            System.out.printf("\nContemplação criada e adicionada com sucesso!\n\n%sData: %s\n", contratoContemplacao.toString(), contemplacao.getDataContemplacao());
        }
    }

    public void getAllContemplacoes(){
        if(contemplacoes.isEmpty()){
            System.out.println("\nNão há Contemplações registradas!\n");
        } else {
            System.out.println("\nContratos Contemplados: \n");
            for(Contemplacao contemplacao : contemplacoes){
                System.out.println(contemplacao);
            }
        }
    }
    public Contemplacao getContemplacaoByContrato(Contrato contrato){
        for(Contemplacao contemplacao : contemplacoes) {
            if(contemplacao.getContratoContemplacao() == contrato) {
                System.out.println("\nContemplação associada a este contrato foi encontrada");
                System.out.println(contemplacao);
                return contemplacao;
            }
        }
        System.out.println("\nContemplação associada a este contrato não foi encontrada");
        return null;
    }
    public Contemplacao getContemplacaoById(int idProcurado) {
        for(Contemplacao contemplacao : contemplacoes){
            if(contemplacao.getIdContemplacao() == idProcurado) {
                System.out.println("\nContemplação encontrada:\n" + contemplacao);
                return contemplacao; 
            }
        }
        System.out.println("\nContemplação não encontrada");
        return null;
    }

    public void updateContemplacao(int idContemplado, LocalDate dataContemplacao) {
        if(dataContemplacao != null){
            getContemplacaoById(idContemplado).setDataContemplacao(dataContemplacao);
            System.out.println("\nData atualizada com sucesso.");
            return;
        }
        System.out.println("\nHouve um erro no preenchimento de dados.");
    }

    public void deleteContemplacao(int id) {
        Contemplacao contemplacao = getContemplacaoById(id);
            if (contemplacao != null){
                contemplacoes.remove(contemplacao);
                System.out.println("\nContemplação removida com sucesso.");
                return;
            }
         System.out.println("\nNão foi possível remover a contemplação desejada.");
    }
}
