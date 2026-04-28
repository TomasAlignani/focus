package com.example.focusflow.services;

import com.example.focusflow.models.*;
import java.util.List;

public class SesionService {

    private final ISesionDAO sesionDAO;
    private final IDescansoDAO descansoDAO;

    public SesionService(ISesionDAO sesionDAO, IDescansoDAO descansoDAO) {
        this.sesionDAO = sesionDAO;
        this.descansoDAO = descansoDAO;
    }

    public TipoDescanso calcularDescanso(int estres) {
        if (estres <= 2) return TipoDescanso.RUNNING;
        if (estres <= 4) return TipoDescanso.FOTOGRAFIA;
        if (estres <= 6) return TipoDescanso.ESTIRAMIENTO;
        if (estres <= 8) return TipoDescanso.SNACK_AGUA;
        return TipoDescanso.RESPIRACION;
    }

    public SesionEstudio registrarSesion(String objetivo, String usuarioId,
                                         int tiempoEstudiado, boolean interrumpida, int estres) {
        SesionEstudio sesion = new SesionEstudio(objetivo);
        sesion.setUsuarioId(usuarioId);
        sesion.setTiempoEstudiado(tiempoEstudiado);
        sesion.setNivelEstres(estres);
        sesion.setEstadoSesion(interrumpida ? EstadoSesion.INTERRUMPIDA : EstadoSesion.COMPLETADA);

        TipoDescanso descanso = calcularDescanso(estres);
        sesion.setTipoDescanso(descanso);

        sesionDAO.guardarSesion(sesion);
        return sesion;
    }

    public List<SesionEstudio> obtenerHistorial(String usuarioId) {
        return sesionDAO.listarSesiones(usuarioId);
    }
}