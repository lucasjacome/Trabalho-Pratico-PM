package Entidades;

import java.util.Objects;

public class PassageiroEmbarque {
    private Passageiro passageiro;
    private boolean embarcado;

    public PassageiroEmbarque(Passageiro passageiro) {
        if (passageiro == null) {
            throw new IllegalArgumentException("Passageiro não pode ser nulo.");
        }
        this.passageiro = passageiro;
        this.embarcado = false;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public boolean isEmbarcado() {
        return embarcado;
    }

    public void setEmbarcado(boolean embarcado) {
        this.embarcado = embarcado;
    }

    @Override
    public String toString() {
        return String.format("Passageiro: %s, Embarcado: %s", passageiro, embarcado ? "Sim" : "Não");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        PassageiroEmbarque that = (PassageiroEmbarque) obj;
        return Objects.equals(passageiro, that.passageiro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passageiro);
    }
}
