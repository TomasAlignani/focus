package com.example.focusflow.models;

import java.util.UUID;

public class SesionEstudio {
    private String id;
    private String objetivo;
    private int tiempoEstudiado;
    private int nivelEstres;
    private EstadoSesion estadoSesion;
    private TipoDescanso tipoDescanso;
    private String usuarioId;
    private String descansoId;
    private double latitud;
    private double longitud;

    public SesionEstudio(String objetivo) {
        this.id = UUID.randomUUID().toString();
        this.objetivo = objetivo;
    }

    public String getId() { return id; }
    public String getObjetivo() { return objetivo; }
    public int getTiempoEstudiado() { return tiempoEstudiado; }
    public int getNivelEstres() { return nivelEstres; }
    public TipoDescanso getTipoDescanso() { return tipoDescanso; }
    public EstadoSesion getEstadoSesion() { return estadoSesion; }
    public String getUsuarioId() { return usuarioId; }
    public String getDescansoId() { return descansoId; }
    public double getLatitud() { return latitud; }
    public double getLongitud() { return longitud; }

    public void setId(String id) { this.id = id; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }
    public void setTiempoEstudiado(int t) { this.tiempoEstudiado = t; }
    public void setNivelEstres(int n) { this.nivelEstres = n; }
    public void setEstadoSesion(EstadoSesion e) { this.estadoSesion = e; }
    public void setTipoDescanso(TipoDescanso t) { this.tipoDescanso = t; }
    public void setUsuarioId(String id) { this.usuarioId = id; }
    public void setDescansoId(String id) { this.descansoId = id; }
    public void setLatitud(double latitud) { this.latitud = latitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }
}