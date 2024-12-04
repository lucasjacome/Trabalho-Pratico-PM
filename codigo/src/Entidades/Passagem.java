package Entidades;

import Enums.StatusPassagem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Passagem {
    private List<Voo> voos;
    private double taxaAgencia;
    private StatusPassagem status;

    public Passagem(double taxaAgencia) {
        if (taxaAgencia < 0) {
            throw new IllegalArgumentException("A taxa da agência não pode ser negativa.");
        }
        this.voos = new ArrayList<>();
        this.taxaAgencia = taxaAgencia;
        this.status = StatusPassagem.PASSAGEM_ADQUIRIDA;
    }

    public void adicionarVoo(Voo voo) {
        if (voo == null) {
            throw new IllegalArgumentException("O voo não pode ser nulo.");
        }
        voos.add(voo);
    }

    public void setStatus(StatusPassagem novoStatus) {
        if (novoStatus == null) {
            throw new IllegalArgumentException("O status da passagem não pode ser nulo.");
        }
        this.status = novoStatus;
    }

    public StatusPassagem getStatus() {
        return status;
    }

    public Voo getVooPrincipal() {
        if (voos.isEmpty()) {
            throw new IllegalStateException("A passagem não possui voos associados.");
        }
        return voos.get(0);
    }

    public double calcularPrecoTotal() {
        double total = taxaAgencia;
        boolean temVooInternacional = false;

        for (Voo voo : voos) {
            total += voo.getTarifaBasica();
            if (voo.isInternacional()) {
                temVooInternacional = true;
            }
        }

        if (temVooInternacional) {
            System.out.println("Preço total mantido em dólar (USD).");
        } else {
            System.out.println("Preço total em reais (BRL).");
        }

        return total;
    }

    public double calcularCustoTotalComBagagens(int quantidadeDeBagagens) {
        if (quantidadeDeBagagens < 0) {
            throw new IllegalArgumentException("A quantidade de bagagens não pode ser negativa.");
        }

        double custoBagagem = 0.0;

        if (!voos.isEmpty()) {
            Voo primeiroVoo = voos.get(0);
            if (quantidadeDeBagagens > 0) {
                custoBagagem += primeiroVoo.getValorPrimeiraBagagem();
            }
            if (quantidadeDeBagagens > 1) {
                custoBagagem += (quantidadeDeBagagens - 1) * primeiroVoo.getValorBagagensAdicionais();
            }
        }

        return calcularPrecoTotal() + custoBagagem;
    }

    public boolean realizarCheckIn() {
        if (voos.isEmpty()) {
            System.out.println("A passagem não possui voos associados.");
            return false;
        }

        Voo primeiroVoo = voos.get(0);
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime inicioCheckIn = primeiroVoo.getDataHoraVoo().minusHours(48);
        LocalDateTime fimCheckIn = primeiroVoo.getDataHoraVoo().minusMinutes(30);

        if (agora.isAfter(inicioCheckIn) && agora.isBefore(fimCheckIn)) {
            this.status = StatusPassagem.CHECK_IN_REALIZADO;
            System.out.println("Check-in realizado com sucesso.");
            return true;
        } else {
            System.out.println("Não é possível realizar o check-in fora do período permitido.");
            return false;
        }
    }

    public void registrarNoShow() {
        if (status != StatusPassagem.CHECK_IN_REALIZADO) {
            this.status = StatusPassagem.NO_SHOW;
            System.out.println("Passagem registrada com status NO SHOW.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Passagem:\n");

        for (Voo voo : voos) {
            sb.append(voo).append("\n");
        }

        sb.append("Status: ").append(status).append("\n");
        sb.append("Taxa da Agência: ").append(String.format("%.2f", taxaAgencia)).append("\n");
        sb.append("Total a ser pago (sem bagagens): ").append(String.format("%.2f", calcularPrecoTotal())).append("\n");

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Passagem passagem = (Passagem) obj;
        return Double.compare(passagem.taxaAgencia, taxaAgencia) == 0 &&
                Objects.equals(voos, passagem.voos) &&
                status == passagem.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(voos, taxaAgencia, status);
    }
}
