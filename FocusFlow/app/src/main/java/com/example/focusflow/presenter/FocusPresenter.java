package com.example.focusflow.presenter;

import com.example.focusflow.models.*;
import com.example.focusflow.services.*;
import com.example.focusflow.views.IFocusView;

public class FocusPresenter {

    private final IFocusView vista;
    private final SesionService sesionService;
    private final UsuarioService usuarioService;
    private final IGestorFotos gestorFotos;
    private final IGestorUbicacion gestorUbicacion;

    public FocusPresenter(IFocusView vista, SesionService sesionService,
                          UsuarioService usuarioService, IGestorFotos gestorFotos,
                          IGestorUbicacion gestorUbicacion) {
        this.vista = vista;
        this.sesionService = sesionService;
        this.usuarioService = usuarioService;
        this.gestorFotos = gestorFotos;
        this.gestorUbicacion = gestorUbicacion;
    }

    public void iniciarFocusFlow() {
        String nombreUsuario = vista.pedirNombreUsuario();
        Usuario usuario = usuarioService.buscarUsuario(nombreUsuario);

        if (usuario != null) {
            vista.mostrarMensaje("¡Bienvenido de nuevo, " + usuario.getNombre() + "!");
        } else {
            vista.mostrarMensaje("Primera vez. Creando perfil para " + nombreUsuario + "...");
            usuario = usuarioService.registrarNuevoUsuario(nombreUsuario);
        }

        String idUsuario = usuario.getId();
        String objetivo = vista.pedirObjetivoEstudio();

        if (gestorFotos != null) gestorFotos.tomarYGuardarFoto("INICIO");
        if (gestorUbicacion != null) gestorUbicacion.iniciarRastreo();

        boolean interrumpida = vista.confirmarInterrupcion();
        int tiempo = interrumpida ? 45 : 90;
        int estres = vista.pedirNivelEstres();

        SesionEstudio sesion = sesionService.registrarSesion(objetivo, idUsuario, tiempo, interrumpida, estres);

        if (gestorUbicacion != null) {
            double distancia = gestorUbicacion.calcularDistanciaRecorrida();
            sesion.setLongitud(distancia); // guardamos distancia provisionalmente
        }

        vista.mostrarPantallaDescanso(sesion.getTipoDescanso());
        vista.mostrarResumen(sesionService.obtenerHistorial(idUsuario));
    }
}