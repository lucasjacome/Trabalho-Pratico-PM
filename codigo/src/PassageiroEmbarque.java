import java.util.ArrayList;
import java.util.List;

public class PassageiroEmbarque {
    private Passageiro passageiro;
    private boolean embarcado;

    public PassageiroEmbarque(Passageiro passageiro) {
        this.passageiro = passageiro;
        this.embarcado = false; // Inicialmente não embarcado
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
}
