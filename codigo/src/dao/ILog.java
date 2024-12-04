package dao;

import java.util.List;

public interface ILog {
    void salvarLog(String mensagem);
    List<String> recuperarLogs();
}
