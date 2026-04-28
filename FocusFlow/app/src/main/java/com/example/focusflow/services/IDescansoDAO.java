package com.example.focusflow.services;

import com.example.focusflow.models.Descanso;
import java.util.List;

public interface IDescansoDAO {
    List<Descanso> obtenerTodosLosDescansos();
}