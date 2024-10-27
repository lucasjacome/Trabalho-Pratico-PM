import java.util.ArrayList;
import java.util.List;

public class Aeronave {
    private String modelo;
    private int capacidadePassageiros;
    private int capacidadeCarga;
    private int numeroFileiras;
    private LetraAssento letraAssento;

    public Aeronave(String modelo, int capacidadeCarga, int capacidadePassageiros, int numeroFileiras){
        this.modelo = modelo;
        this.capacidadeCarga = capacidadeCarga;
        this.capacidadePassageiros = capacidadePassageiros;
        this.numeroFileiras = numeroFileiras;
    }

    public String getModelo() {
        return modelo;
    }

    public int getNumeroFileiras() {
        return numeroFileiras;
    }

    public int getCapacidadeCarga() {
        return capacidadeCarga;
    }

    public int getCapacidadePassageiros() {
        return capacidadePassageiros;
    }

    public LetraAssento getLetraAssento() {
        return letraAssento;
    }

    public List<String> gerarAssentos(){
        List<String> assentos = new ArrayList<>();
        for (int i = 1; i <= numeroFileiras; i++){
            for (LetraAssento letra : LetraAssento.values()){
                assentos.add(i + String.valueOf(letra));
            }
        }
        return assentos;
    }
}
