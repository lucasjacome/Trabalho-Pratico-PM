package Entidades;

import java.util.ArrayList;
import java.util.List;

public class CancelamentoVoo {
    private List<Voo> voos;

    public CancelamentoVoo() {
        this.voos = new ArrayList<>();
    }

    public void adicionarVoo(Voo voo) {
        if (voo == null) {
            throw new IllegalArgumentException("O voo não pode ser nulo.");
        }
        voos.add(voo);
    }

    public boolean cancelarVoo(String numeroVoo) {
        for (Voo voo : voos) {
            if (voo.getCodigoVoo().equals(numeroVoo) && !voo.isCancelado()) {
                voo.cancelar();
                return true;
            }
        }
        return false;
    }

    public boolean reverterCancelamento(String numeroVoo) {
        for (Voo voo : voos) {
            if (voo.getCodigoVoo().equals(numeroVoo) && voo.isCancelado()) {
                voo.reverterCancelamento();
                return true;
            }
        }
        return false;
    }

    public List<Voo> listarVoosCancelados() {
        List<Voo> voosCancelados = new ArrayList<>();
        for (Voo voo : voos) {
            if (voo.isCancelado()) {
                voosCancelados.add(voo);
            }
        }
        return voosCancelados;
    }

    public List<Voo> listarTodosVoos() {
        return new ArrayList<>(voos);
    }
}
