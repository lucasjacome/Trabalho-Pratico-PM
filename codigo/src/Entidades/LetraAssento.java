package Entidades;

public enum LetraAssento {
    A, B, C, D, E, F;

    public static boolean isLetraValida(String letra) {
        if (letra == null || letra.trim().isEmpty()) {
            return false;
        }
        try {
            LetraAssento.valueOf(letra.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static LetraAssento fromString(String letra) {
        if (!isLetraValida(letra)) {
            throw new IllegalArgumentException("Letra de assento inválida: " + letra);
        }
        return LetraAssento.valueOf(letra.toUpperCase());
    }
}