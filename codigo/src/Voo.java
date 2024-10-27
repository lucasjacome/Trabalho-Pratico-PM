import java.time.LocalDateTime;

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
    private Aeronave aeronave;

    public Voo(Aeroporto origem, Aeroporto destino, LocalDateTime dataHoraVoo, String codigoVoo, CompanhiaAerea companhia,
               Aeronave aeronave, double tarifaBasica, double tarifaBusiness, double tarifaPremium, String moeda) {
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
    }

    // Getters
    public Aeronave getAeronave() {
        return aeronave;
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

    // Verifica se o voo é internacional
    public boolean isInternacional() {
        return moeda.equals("USD");
    }

    // Métodos para obter valores de bagagem
    public double getValorPrimeiraBagagem() {
        return companhia.getValorPrimeiraBagagem();
    }

    public double getValorBagagensAdicionais() {
        return companhia.getValorBagagensAdicionais();
    }

    @Override
    public String toString() {
        return String.format("Voo {codigo: %s, origem: %s, destino: %s, aeronave: %s, tarifa: %.2f %s}",
                codigoVoo, origem.getNome(), destino.getNome(), aeronave.getModelo(), tarifaBasica, moeda);
    }
}