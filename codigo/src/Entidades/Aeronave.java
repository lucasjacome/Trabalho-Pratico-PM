package Entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Aeronave {
    private String modelo;
    private int capacidadePassageiros;
    private int capacidadeCarga;
    private int numeroFileiras;

    public Aeronave(String modelo, int capacidadeCarga, int capacidadePassageiros, int numeroFileiras) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("O modelo da aeronave não pode ser vazio.");
        }
        if (capacidadeCarga <= 0 || capacidadePassageiros <= 0 || numeroFileiras <= 0) {
            throw new IllegalArgumentException("Capacidades e número de fileiras devem ser maiores que zero.");
        }
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

    public List<String> gerarAssentos() {
        List<String> assentos = new ArrayList<>();
        int assentosPorFileira = 6; // A, B, C, D, E, F
        int totalAssentos = Math.min(capacidadePassageiros, numeroFileiras * assentosPorFileira);

        for (int i = 1; i <= numeroFileiras; i++) {
            for (char letra = 'A'; letra <= 'F'; letra++) {
                if (assentos.size() < totalAssentos) {
                    assentos.add(i + String.valueOf(letra));
                } else {
                    break;
                }
            }
        }
        return assentos;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Aeronave aeronave = (Aeronave) obj;
        return modelo.equalsIgnoreCase(aeronave.modelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelo.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("Modelo: %s, Capacidade de Passageiros: %d, Capacidade de Carga: %dkg, Fileiras: %d",
                modelo, capacidadePassageiros, capacidadeCarga, numeroFileiras);
    }
}
