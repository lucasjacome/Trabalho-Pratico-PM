package Entidades;

public enum StatusPassageiro {
    REGULAR("Regular"),
    VIP("Vip");

    private final String descricao;

    StatusPassageiro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}