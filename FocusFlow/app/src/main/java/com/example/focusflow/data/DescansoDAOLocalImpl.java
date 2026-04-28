package com.example.focusflow.data;

import com.example.focusflow.models.Descanso;
import com.example.focusflow.services.IDescansoDAO;
import java.util.ArrayList;
import java.util.List;

public class DescansoDAOLocalImpl implements IDescansoDAO {

    @Override
    public List<Descanso> obtenerTodosLosDescansos() {
        List<Descanso> lista = new ArrayList<>();
        String[] nombres = {"RUNNING", "FOTOGRAFIA", "ESTIRAMIENTO", "SNACK_AGUA", "RESPIRACION"};
        for (int i = 0; i < nombres.length; i++) {
            Descanso d = new Descanso();
            d.setId(String.valueOf(i + 1));
            d.setNombre(nombres[i]);
            lista.add(d);
        }
        return lista;
    }
}