package Repository;

import Models.Boleto;
import Models.Contrato;
import Enums.StatusBoletoEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoletoRepository {
    private List<Boleto> boletos = new ArrayList<>();

    public void createBoleto(Contrato contratoBoleto, LocalDate dataEmissao, LocalDate dataVencimento, int numeroParcela) {
        if (contratoBoleto == null) {
            System.out.println("Erro: O contrato do boleto não pode ser nulo."); return;
        }
        if (dataEmissao == null || dataVencimento == null) {
            System.out.println("Erro: As datas de emissão e vencimento não podem ser nulas."); return;
        }
        if (dataVencimento.isBefore(dataEmissao)) {
            System.out.println("Erro: A data de vencimento não pode ser anterior à data de emissão."); return;
        }

        Boleto boleto = new Boleto(contratoBoleto, dataEmissao, dataVencimento, numeroParcela);
        boletos.add(boleto);
        System.out.println("Boleto criado com sucesso!");
        System.out.println("ID: " + boleto.getIdBoleto() + ", Parcela: " + numeroParcela + ", Vencimento: " + dataVencimento);
    }

    public int getIdBoleto(Contrato contrato, String cpfCliente, int numeroParcela) {
        for (Boleto boleto : boletos) {
            if (boleto.getContratoBoleto().equals(contrato) &&
                    boleto.getContratoBoleto().getCliente().getCpf().equals(cpfCliente) &&
                    boleto.getNumeroParcela() == numeroParcela) {
                return boleto.getIdBoleto();
            }
        }
        System.out.println("Erro: Nenhum boleto encontrado com os parâmetros fornecidos.");
        return -1;
    }

    public Boleto getBoletoById(int idBoleto) {
        for (Boleto boleto : boletos) {
            if (boleto.getIdBoleto() == idBoleto) {
                return boleto;
            }
        }
        System.out.println("Nenhum boleto com ID " + idBoleto + " encontrado.");
        return null;
    }

    public void updateBoleto(int idBoleto, Boleto dadosAtualizacao) {
        Boleto boletoExistente = getBoletoById(idBoleto);
        if (boletoExistente != null) {
            if (dadosAtualizacao.getDataEmissao() != null) {
                boletoExistente.setDataEmissao(dadosAtualizacao.getDataEmissao());
            }
            if (dadosAtualizacao.getDataVencimento() != null) {
                boletoExistente.setDataVencimento(dadosAtualizacao.getDataVencimento());
            }
            System.out.println("Boleto com ID " + idBoleto + " atualizado com sucesso.");
        } else {
            System.out.println("Nenhum boleto com ID " + idBoleto + " encontrado para atualização.");
        }
    }

    public void deleteBoleto(int idBoleto) {
        Boleto boleto = getBoletoById(idBoleto);
        if (boleto != null) {
            boletos.remove(boleto);
            System.out.println("Boleto com ID " + idBoleto + " removido com sucesso.");
        } else {
            System.out.println("Nenhum boleto com ID " + idBoleto + " encontrado para exclusão.");
        }
    }

    public void verificarAllVencimentos() {
        for (Boleto boleto : boletos) {
            atualizarStatusBoleto(boleto);
        }
    }

    public void verificarVencimento(int idBoleto) {
        Boleto boleto = getBoletoById(idBoleto);
        if (boleto != null) {
            atualizarStatusBoleto(boleto);
        } else {
            System.out.println("Nenhum boleto com ID " + idBoleto + " encontrado.");
        }
    }

    public void atualizarStatusBoleto(Boleto boleto) {
        if (boleto.getStatusBoleto() == StatusBoletoEnum.PENDENTE && LocalDate.now().isAfter(boleto.getDataVencimento())) {
            boleto.setStatusBoleto(StatusBoletoEnum.ATRASADO);
            boleto.getContratoBoleto().getListaBoletosAtrasados().add(boleto);
            System.out.println("Boleto ID " + boleto.getIdBoleto() + " atualizado para ATRASADO.");
        }
    }

    public void printarBoleto(int idBoleto) {
        Boleto boleto = getBoletoById(idBoleto);
        if (boleto != null) {
            System.out.println("\n=== Informações do Boleto ===");
            System.out.println("Cpf do Cliente: " + (boleto.getContratoBoleto() != null ? boleto.getContratoBoleto().getCliente().getCpf() : "Nenhum contrato associado"));
            System.out.println("ID do Boleto: " + boleto.getIdBoleto());
            System.out.println("Parcela: " + boleto.getNumeroParcela());
            System.out.println("Valor a ser pago: R$ " + boleto.getValorBoleto());
            System.out.println("Data de Emissão: " + boleto.getDataEmissao());
            System.out.println("Data de Vencimento: " + boleto.getDataVencimento());
            System.out.println("Data de Pagamento: " + (boleto.getDataPagamento() != null ? boleto.getDataPagamento() : "Não pago"));
            System.out.println("Status do Boleto: " + boleto.getStatusBoleto());
        } else {
            System.out.println("Boleto com ID " + idBoleto + " não encontrado.");
        }
    }

    public void printAllBoletosContratos(Contrato contrato) {
        if (contrato == null) {
            System.out.println("O contrato fornecido é inválido.");
            return;
        }
        boolean encontrouBoletos = false;
        System.out.println("--- Boletos do Contrato ---");
        for (Boleto boleto : boletos) {
            if (boleto.getContratoBoleto() != null && boleto.getContratoBoleto().equals(contrato)) {
                encontrouBoletos = true;
                printarBoleto(boleto.getIdBoleto());
            }
        }
        if (!encontrouBoletos) {
            System.out.println("Nenhum boleto encontrado para o contrato fornecido.");
        }
        System.out.println("--- Fim da Lista ---");
    }

    public void printAllBoletos() {
        if (boletos.isEmpty()) {
            System.out.println("Nenhum boleto foi encontrado.");
        } else {
            System.out.println("--- Lista de Todos os Boletos ---");
            for (Boleto boleto : boletos) {
                System.out.println(boleto);
            }
            System.out.println("--- Fim da Lista ---");
        }
    }

    public void realizarPagamento(int idBoleto) {
        Boleto boleto = getBoletoById(idBoleto);
        if (boleto != null) {
            if (boleto.getStatusBoleto() == StatusBoletoEnum.PENDENTE) {
                boleto.setStatusBoleto(StatusBoletoEnum.PAGO);
                boleto.setDataPagamento(LocalDate.now());
                System.out.println("Boleto pago com sucesso.");
            } else if (boleto.getStatusBoleto() == StatusBoletoEnum.ATRASADO) {
                boleto.setStatusBoleto(StatusBoletoEnum.PAGO);
                boleto.setDataPagamento(LocalDate.now());
                System.out.println("Boleto atrasado pago com sucesso. Multa aplicada.");
            } else {
                System.out.println("Este boleto já foi pago anteriormente.");
            }
        } else {
            System.out.println("Boleto com ID " + idBoleto + " não encontrado.");
        }
    }
}