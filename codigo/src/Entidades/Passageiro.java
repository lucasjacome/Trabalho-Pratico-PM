package Entidades;

import java.util.Objects;

public class Passageiro {
    private String nome;
    private String sobrenome;
    private String documento;
    private String email;
    private StatusPassageiro vip;

    public Passageiro(String nome, String sobrenome, String documento, String email) {
        if (nome == null || nome.trim().isEmpty() ||
                sobrenome == null || sobrenome.trim().isEmpty() ||
                documento == null || documento.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Todos os campos são obrigatórios e devem ser válidos.");
        }
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.email = email;
        this.vip = StatusPassageiro.REGULAR;
    }

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

    public double calcularValorBagagem(CompanhiaAerea companhia, int qntBagagem) {
        if (companhia == null || qntBagagem < 0) {
            throw new IllegalArgumentException(
                    "Companhia não pode ser nula e a quantidade de bagagens deve ser positiva.");
        }
        if (!isVip() || !companhia.isVipBeneficioAtivo()) {
            return qntBagagem * companhia.getValorPrimeiraBagagem();
        }
        if (qntBagagem <= 1) {
            return 0.0;
        }
        int bagagensPagas = qntBagagem - 1;
        return bagagensPagas * companhia.getValorBagagensAdicionais() * 0.5;
    }

    @Override
    public String toString() {
        return String.format("%s %s (Documento: %s, Email: %s)", nome, sobrenome, documento, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Passageiro that = (Passageiro) obj;
        return documento.equals(that.documento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documento);
    }
}
