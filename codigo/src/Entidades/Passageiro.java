package Entidades;

import Enums.StatusPassageiro;

public class Passageiro {
    private String nome;
    private String sobrenome;
    private String documento; // RG, CPF ou Passaporte
    private String email;
    private StatusPassageiro vip;


    public Passageiro(String nome, String sobrenome, String documento, String email) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.email = email;
        this.vip = StatusPassageiro.REGULAR;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public boolean isVip() {
        return this.vip == StatusPassageiro.VIP;
    }

    public void setVipStatus(boolean isVip) {
        this.vip = isVip ? StatusPassageiro.VIP : StatusPassageiro.REGULAR;
    }

    public void cancelarVoo(Voo voo) {
        if (isVip()) {
            System.out.println("Cancelamento realizado sem custo adiciona para passageiro VIP.");
        } else {
            System.out.println("Cancelamento para passageiros regulares sujeito a custos.");
        }
    }

    public double calcularValorBagagem(CompanhiaAerea companhia, int qntBagagem) {
        if (!isVip() || !companhia.isVipBeneficioAtivo()) {
            return qntBagagem * companhia.getValorPrimeiraBagagem();
        }

        // Caso tenha 1 franquia, ela vai ser gratuita
        if (qntBagagem <= 1) {
            return 0.0;
        }

        int bagagensPagas = qntBagagem - 1;

        return bagagensPagas * (companhia.getValorBagagensAdicionais() * 0.5);
    }

    @Override
    public String toString() {
        return nome + " " + sobrenome + " (Documento: " + documento + ", " + "Email: " + email + ")";
    }
}
