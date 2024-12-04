package Entidades;

public class Bilhete {
    private Passageiro passageiro;
    private Voo voo;

    public Bilhete(Passageiro passageiro, Voo voo) {
        if (passageiro == null || voo == null) {
            throw new IllegalArgumentException("Passageiro e voo não podem ser nulos.");
        }
        this.passageiro = passageiro;
        this.voo = voo;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public Voo getVoo() {
        return voo;
    }

    public boolean emitir() {
        if (voo.isInternacional()) {
            if (isPassaporteValido(passageiro.getDocumento())) {
                System.out.println("Bilhete emitido para voo internacional: " + voo);
                System.out.println("Passageiro: " + passageiro);
                return true;
            } else {
                System.out.println("Erro: Documento inválido para voo internacional. Deve ser um passaporte válido.");
                return false;
            }
        } else {
            if (isDocumentoNacionalValido(passageiro.getDocumento())) {
                System.out.println("Bilhete emitido para voo nacional: " + voo);
                System.out.println("Passageiro: " + passageiro);
                return true;
            } else {
                System.out.println("Erro: Documento inválido para voo nacional. Deve ser RG ou CPF.");
                return false;
            }
        }
    }

    private boolean isPassaporteValido(String documento) {
        return documento != null && documento.matches("[A-Z]{2}\\d{6}");
    }

    private boolean isDocumentoNacionalValido(String documento) {
        return documento != null && (documento.length() == 11 || documento.length() == 9);
    }

    // Método para gerar o cartão de embarque
    public String cartaoDeEmbarque() {
        return String.format("----- Cartão de Embarque -----\n" +
                "Passageiro: %s %s\n" +
                "Voo: %s\n" +
                "Origem: %s\n" +
                "Destino: %s\n" +
                "Data: %s\n" +
                "Tarifa: %.2f %s\n" +
                "-----------------------------",
                passageiro.getNome(), passageiro.getSobrenome(),
                voo.getCodigoVoo(),
                voo.getOrigem().getSigla(), voo.getDestino().getSigla(),
                voo.getDataHoraVoo().toLocalDate(),
                voo.getTarifaBasica(), voo.getMoeda());
    }

    @Override
    public String toString() {
        return String.format("Bilhete [Passageiro: %s, Voo: %s]", passageiro, voo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Bilhete bilhete = (Bilhete) obj;
        return passageiro.equals(bilhete.passageiro) && voo.equals(bilhete.voo);
    }

    @Override
    public int hashCode() {
        int result = passageiro.hashCode();
        result = 31 * result + voo.hashCode();
        return result;
    }
}
