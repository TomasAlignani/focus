package com.example.focusflow.services;

import com.example.focusflow.models.SesionEstudio;
import java.util.List;

public interface ISesionDAO {
    void guardarSesion(SesionEstudio sesion);
    List<SesionEstudio> listarSesiones(String usuarioId);
}