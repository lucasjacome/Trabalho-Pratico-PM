package Managers;

import Entidades.Bilhete;

import java.util.ArrayList;
import java.util.List;

public class BilheteManager {
    List<Bilhete> bilhetes;

    public BilheteManager() {this.bilhetes = new ArrayList<Bilhete>();}

    public List<Bilhete> listarTodosBilhetes(){
        return new ArrayList<>(bilhetes);
    }
}
