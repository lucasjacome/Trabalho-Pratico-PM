package Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Voo {
    private Aeroporto origem;
    private Aeroporto destino;
    private LocalDateTime dataHoraVoo;
    private String codigoVoo;
    private CompanhiaAerea companhia;
    private double tarifaBasica;
    private double tarifaBusiness;
    private double tarifaPremium;
    private String moeda;
    private List<Assento> assentos;
    private Aeronave aeronave;
    private boolean cancelado;
    private List<PassageiroEmbarque> passageiros;

    public Voo(Aeroporto origem, Aeroporto destino, LocalDateTime dataHoraVoo, String codigoVoo,
            CompanhiaAerea companhia, Aeronave aeronave, double tarifaBasica, double tarifaBusiness,
            double tarifaPremium, String moeda) {
        if (origem == null || destino == null || dataHoraVoo == null || codigoVoo == null || companhia == null
                || aeronave == null || moeda == null) {
            throw new IllegalArgumentException("Nenhum campo pode ser nulo.");
        }
        if (tarifaBasica <= 0 || tarifaBusiness <= 0 || tarifaPremium <= 0) {
            throw new IllegalArgumentException("As tarifas devem ser maiores que zero.");
        }

        this.origem = origem;
        this.destino = destino;
        this.dataHoraVoo = dataHoraVoo;
        this.codigoVoo = codigoVoo;
        this.companhia = companhia;
        this.tarifaBasica = tarifaBasica;
        this.tarifaBusiness = tarifaBusiness;
        this.tarifaPremium = tarifaPremium;
        this.moeda = moeda;
        this.aeronave = aeronave;
        this.cancelado = false;
        this.assentos = Assento.inicializarAssentos(aeronave.gerarAssentos());
        this.passageiros = new ArrayList<>();
    }

    public Aeronave getAeronave() {
        return aeronave;
    }

    public List<PassageiroEmbarque> getPassageiros() {
        return passageiros;
    }

    public Aeroporto getOrigem() {
        return origem;
    }

    public Aeroporto getDestino() {
        return destino;
    }

    public LocalDateTime getDataHoraVoo() {
        return dataHoraVoo;
    }

    public String getCodigoVoo() {
        return codigoVoo;
    }

    public CompanhiaAerea getCompanhia() {
        return companhia;
    }

    public double getTarifaBasica() {
        return tarifaBasica;
    }

    public double getTarifaBusiness() {
        return tarifaBusiness;
    }

    public double getTarifaPremium() {
        return tarifaPremium;
    }

    public String getMoeda() {
        return moeda;
    }

    public void adicionarPassageiro(Passageiro passageiro) {
        if (passageiro == null) {
            throw new IllegalArgumentException("O passageiro não pode ser nulo.");
        }
        passageiros.add(new PassageiroEmbarque(passageiro));
    }

    public void registrarEmbarque(String documentoPassageiro) {
        for (PassageiroEmbarque pe : passageiros) {
            if (pe.getPassageiro().getDocumento().equals(documentoPassageiro)) {
                pe.setEmbarcado(true);
                return;
            }
        }
        throw new IllegalArgumentException("Passageiro com o documento especificado não encontrado.");
    }

    public void verificarNoShow() {
        for (PassageiroEmbarque pe : passageiros) {
            if (!pe.isEmbarcado()) {
                System.out.println("Passageiro " + pe.getPassageiro().getNome() + " não embarcou (NO SHOW).");
            }
        }
    }

    public boolean verificarDisponibilidadeAssento(String numeroAssento) {
        return Assento.verificarDisponibilidade(assentos, numeroAssento);
    }

    public boolean reservarAssento(String numeroAssento) {
        return Assento.reservarAssento(assentos, numeroAssento);
    }

    public boolean isInternacional() {
        return moeda.equals("USD");
    }

    public double getValorPrimeiraBagagem() {
        return companhia.getValorPrimeiraBagagem();
    }

    public double getValorBagagensAdicionais() {
        return companhia.getValorBagagensAdicionais();
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void cancelar() {
        this.cancelado = true;
    }

    public void reverterCancelamento() {
        this.cancelado = false;
    }

    @Override
    public String toString() {
        return String.format("Voo {codigo: %s, origem: %s, destino: %s, aeronave: %s, tarifa: %.2f %s}",
                codigoVoo, origem.getSigla(), destino.getSigla(), aeronave.getModelo(), tarifaBasica, moeda);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Voo voo = (Voo) obj;
        return codigoVoo.equals(voo.codigoVoo);
    }

    @Override
    public int hashCode() {
        return codigoVoo.hashCode();
    }
}
