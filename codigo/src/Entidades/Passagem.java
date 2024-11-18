package Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Passagem {
    private List<Voo> voos;
    private double taxaAgencia;
    private StatusPassagem status; // Atributo para registrar o status da passagem

    public Passagem(double taxaAgencia) {
        this.voos = new ArrayList<>();
        this.taxaAgencia = taxaAgencia;
        this.status = StatusPassagem.PASSAGEM_ADQUIRIDA; // Status inicial
    }

    // Adicionar voo à passagem
    public void adicionarVoo(Voo voo) {
        voos.add(voo);
    }

    // Alterar o status da passagem
    public void setStatus(StatusPassagem novoStatus) {
        this.status = novoStatus;
    }

    // Retornar o status atual da passagem
    public StatusPassagem getStatus() {
        return status;
    }

    public Voo getVoo() {
        if (!voos.isEmpty()) {
            return voos.get(0); // Retorna o primeiro voo da lista
        }
        throw new IllegalStateException("A passagem não possui voos associados.");
    }

    // Calcular o preço total levando em conta voos internacionais
    public double calcularPrecoTotal() {
        double total = 0.0;
        boolean hasInternacional = false;

        for (Voo voo : voos) {
            total += voo.getTarifaBasica(); // Somando tarifa básica de cada voo
            if (voo.isInternacional()) {
                hasInternacional = true; // Se algum voo for internacional, indicamos isso
            }
        }

        total += taxaAgencia; // Somando a taxa da agência

        if (hasInternacional) {
            System.out.println("Preço total mantido em dólar (USD).");
        } else {
            System.out.println("Preço total em reais (BRL).");
        }

        return total;
    }

    // Calcular o custo total da passagem incluindo bagagens adicionais
    public double calcularCustoTotalComBagagens(int quantidadeDeBagagensAdicionais) {
        double custoBagagem = 0.0;

        // Verifica se há voos na passagem
        if (!voos.isEmpty()) {
            Voo primeiroVoo = voos.get(0); // Assume que as tarifas de bagagem são iguais em todos os voos
            custoBagagem += primeiroVoo.getValorPrimeiraBagagem(); // Valor da primeira bagagem
            custoBagagem += quantidadeDeBagagensAdicionais * primeiroVoo.getValorBagagensAdicionais(); // Valor das
                                                                                                       // bagagens
                                                                                                       // adicionais
        }

        return calcularPrecoTotal() + custoBagagem; // Soma o total do voo com o custo das bagagens
    }

    // Realizar check-in para a passagem
    public boolean realizarCheckIn() {
        if (voos.isEmpty()) {
            System.out.println("A passagem não possui voos associados.");
            return false;
        }

        Voo primeiroVoo = voos.get(0);
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime horarioVoo = primeiroVoo.getDataHoraVoo();
        LocalDateTime inicioCheckIn = horarioVoo.minusHours(48);
        LocalDateTime fimCheckIn = horarioVoo.minusMinutes(30);

        if (agora.isAfter(inicioCheckIn) && agora.isBefore(fimCheckIn)) {
            this.status = StatusPassagem.CHECK_IN_REALIZADO;
            System.out.println("Check-in realizado com sucesso.");
            return true;
        } else {
            System.out.println("Não é possível realizar o check-in fora do período permitido.");
            return false;
        }
    }

    // Registrar NO SHOW para a passagem
    public void registrarNoShow() {
        if (status != StatusPassagem.CHECK_IN_REALIZADO) {
            this.status = StatusPassagem.NO_SHOW;
            System.out.println("Entidades.Passagem registrada com status NO SHOW.");
        }
    }

    @Override
    public String toString() {
        StringBuilder passagem = new StringBuilder();
        passagem.append("Entidades.Passagem:\n");

        // Listar os voos da passagem
        for (Voo voo : voos) {
            passagem.append(voo).append("\n");
        }

        passagem.append("Status: ").append(status).append("\n"); // Exibir o status da passagem
        passagem.append("Taxa da Agência: ").append(String.format("%.2f", taxaAgencia)).append("\n");
        passagem.append("Total a ser pago (sem bagagens): ").append(String.format("%.2f", calcularPrecoTotal()))
                .append("\n");

        return passagem.toString();
    }
}
