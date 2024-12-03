package Entidades;

import java.util.Objects;

public class Aeroporto {
    private String nome;
    private String sigla;
    private String cidade;
    private String estado;
    private String pais;

    public Aeroporto(String nome, String sigla, String cidade, String estado, String pais) {
        if (sigla == null || sigla.length() != 3) {
            throw new IllegalArgumentException("A sigla deve conter exatamente 3 caracteres.");
        }
        this.nome = nome != null && !nome.trim().isEmpty() ? nome : "Não definido";
        this.sigla = sigla.toUpperCase();
        this.cidade = cidade != null && !cidade.trim().isEmpty() ? cidade : "Não definida";
        this.estado = estado != null && !estado.trim().isEmpty() ? estado : "Não definido";
        this.pais = pais != null && !pais.trim().isEmpty() ? pais : "Não definido";
    }

    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Aeroporto that = (Aeroporto) obj;
        return sigla.equals(that.sigla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sigla);
    }

    @Override
    public String toString() {
        return String.format("%s (%s), %s, %s, %s", nome, sigla, cidade, estado, pais);
    }
}
