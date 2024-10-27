public class Aeroporto {
    private String nome;
    private String sigla;
    private String cidade;
    private String estado;
    private String pais;
    private String teste;

    public Aeroporto(String nome, String sigla, String cidade, String estado, String pais) {
        this.nome = nome;
        this.sigla = sigla;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.teste = teste;
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
        return this.sigla.equals(that.sigla); // Comparar pela sigla do aeroporto
    }

    @Override
    public int hashCode() {
        return sigla.hashCode();
    }
}