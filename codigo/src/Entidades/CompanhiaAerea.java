package Entidades;

import java.util.Objects;

public class CompanhiaAerea {
    private String nome;
    private String codigo;
    private String razaoSocial;
    private String cnpj;
    private double valorPrimeiraBagagem;
    private double valorBagagensAdicionais;
    private boolean vipBeneficioAtivo;

    public CompanhiaAerea(String nome, String codigo, String razaoSocial, String cnpj, double valorPrimeiraBagagem,
            double valorBagagensAdicionais) {
        if (nome == null || nome.trim().isEmpty() ||
                codigo == null || codigo.trim().isEmpty() ||
                razaoSocial == null || razaoSocial.trim().isEmpty() ||
                cnpj == null || cnpj.trim().isEmpty() ||
                valorPrimeiraBagagem < 0 || valorBagagensAdicionais < 0) {
            throw new IllegalArgumentException("Dados inválidos para criação da companhia aérea.");
        }
        this.nome = nome;
        this.codigo = codigo;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.valorPrimeiraBagagem = valorPrimeiraBagagem;
        this.valorBagagensAdicionais = valorBagagensAdicionais;
        this.vipBeneficioAtivo = true;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public double getValorPrimeiraBagagem() {
        return valorPrimeiraBagagem;
    }

    public double getValorBagagensAdicionais() {
        return valorBagagensAdicionais;
    }

    public boolean isVipBeneficioAtivo() {
        return vipBeneficioAtivo;
    }

    public void setVipBeneficioAtivo(boolean ativo) {
        this.vipBeneficioAtivo = ativo;
    }

    @Override
    public String toString() {
        return String.format(
                "Companhia Aérea: %s [%s], Razão Social: %s, CNPJ: %s, Valor Primeira Bagagem: %.2f, Valor Bagagens Adicionais: %.2f, Benefício VIP: %s",
                nome, codigo, razaoSocial, cnpj, valorPrimeiraBagagem, valorBagagensAdicionais,
                vipBeneficioAtivo ? "Ativo" : "Inativo");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        CompanhiaAerea that = (CompanhiaAerea) obj;
        return cnpj.equals(that.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }
}
