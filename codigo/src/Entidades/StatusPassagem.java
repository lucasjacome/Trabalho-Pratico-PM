package Entidades;

public enum StatusPassagem {
    PASSAGEM_ADQUIRIDA("Passagem adquirida"),
    PASSAGEM_CANCELADA("Passagem cancelada"),
    CHECK_IN_REALIZADO("Check-in realizado"),
    EMBARQUE_REALIZADO("Embarque realizado"),
    NO_SHOW("No-show");

    private final String descricao;

    StatusPassagem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
