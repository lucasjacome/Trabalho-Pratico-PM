import java.util.List;

public class CancelamentoVoo {
    private List<Voo> voos;

    // realiza o cancelamento do voo
    public void cancelarVoo(String codigoVoo) {
        Voo voo = voos.get(codigoVoo);
        // verifica se há existência do voo
        if (voo != null) {
            System.out.println("Voo " + codigoVoo + " cancelado com sucesso.");
        } else {
            System.out.println("Voo não encontrado.");
        }
    }

    // realiza cancelamento das passagens do voo
    public void CancelamentoPassagem() {
        for (Passagem passagem : passagens) {
            if (!passagem.isCancelada()) {
                passagem.cancelar();
                for (Assento assento : passagem.getAssentosReservados()) {
                    assento.liberar();
                }
            }
        }
    }
}

