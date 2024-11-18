package Entidades;

import java.util.ArrayList;
import java.util.List;

public class CancelamentoVoo {
    private List<Voo> voos;
 

    public CancelamentoVoo() {
        this.voos = new ArrayList<>();
    }

    // Adicionar um voo à lista
    public void adicionarVoo(Voo voo) {
        voos.add(voo);
    }

    // Cancelar um voo específico
    public boolean cancelarVoo(String numeroVoo) {
        for (Voo voo : voos) {
            if (voo.getCodigoVoo().equals(numeroVoo) && !voo.isCancelado()) {
                voo.cancelar();
                System.out.println("Entidades.Voo " + numeroVoo + " foi cancelado com sucesso.");
                return true;
            }
        }
        System.out.println("Entidades.Voo " + numeroVoo + " não encontrado ou já está cancelado.");
        return false;
    }

    // Reverter o cancelamento de um voo
    public boolean reverterCancelamento(String numeroVoo) {
        for (Voo voo : voos) {
            if (voo.getCodigoVoo().equals(numeroVoo) && voo.isCancelado()) {
                voo.reverterCancelamento();
                System.out.println("Cancelamento do voo " + numeroVoo + " foi revertido.");
                return true;
            }
        }
        System.out.println("Entidades.Voo " + numeroVoo + " não encontrado ou não está cancelado.");
        return false;
    }

    // Listar voos cancelados
    public void listarVoosCancelados() {
        System.out.println("Voos cancelados:");
        for (Voo voo : voos) {
            if (voo.isCancelado()) {
                System.out.println(voo);
            }
        }
    }

    // Listar todos os voos
    public void listarTodosVoos() {
        System.out.println("Todos os voos:");
        for (Voo voo : voos) {
            System.out.println(voo);
        }
    }
}
