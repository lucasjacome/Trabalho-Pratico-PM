package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogDAOImpl implements ILog {
    private static final String LOG_FILE = "logs.txt";

    @Override
    public void salvarLog(String mensagem) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(mensagem);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao salvar log: " + e.getMessage());
        }
    }

    @Override
    public List<String> recuperarLogs() {
        List<String> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                logs.add(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao recuperar logs: " + e.getMessage());
        }
        return logs;
    }
}
