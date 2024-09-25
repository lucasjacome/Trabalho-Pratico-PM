public class CompanhiaAerea {
    private String nome;
    private String codigo;
    private String razaoSocial;
    private String cnpj;
    private double valorPrimeiraBagagem;
    private double valorBagagensAdicionais;

    public CompanhiaAerea(String nome, String codigo, String razaoSocial, String cnpj, double valorPrimeiraBagagem,
            double valorBagagensAdicionais) {
        this.nome = nome;
        this.codigo = codigo;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.valorPrimeiraBagagem = valorPrimeiraBagagem;
        this.valorBagagensAdicionais = valorBagagensAdicionais;
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
}
