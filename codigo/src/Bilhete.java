public class Bilhete {
    private Passageiro passageiro;
    private Voo voo;

    public Bilhete(Passageiro passageiro, Voo voo) {
        this.passageiro = passageiro;
        this.voo = voo;
    }

    public boolean emitir() {
        if (voo.isInternacional()) {
            // Para voos internacionais, o documento deve ser Passaporte
            if (isPassaporteValido(passageiro.getDocumento())) {
                System.out.println("Bilhete emitido para voo internacional: " + voo);
                System.out.println("Passageiro: " + passageiro);
                return true;
            } else {
                System.out.println(
                        "Erro: Documento inválido para voo internacional. Deve ser um passaporte válido (duas letras e seis dígitos).");
                return false;
            }
        } else {
            // Para voos nacionais, o documento deve ser RG ou CPF (simulando com base no
            // tamanho)
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

    // Método para validar se o passaporte segue o formato: duas letras seguidas de
    // seis dígitos
    private boolean isPassaporteValido(String documento) {
        return documento.matches("[A-Z]{2}\\d{6}");
    }

    // Método para validar documentos nacionais (RG ou CPF - 9 ou 11 dígitos
    // simulados)
    private boolean isDocumentoNacionalValido(String documento) {
        return documento.length() == 11 || documento.length() == 9; // Simulação de validação para CPF e RG
    }
}
