public class Passageiro {
    private String nome;
    private String sobrenome;
    private String documento; // RG, CPF ou Passaporte

    public Passageiro(String nome, String sobrenome, String documento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
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

    @Override
    public String toString() {
        return nome + " " + sobrenome + " (Documento: " + documento + ")";
    }
}
