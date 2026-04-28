package com.example.focusflow.data;

import android.content.Context;
import android.location.Location;
import com.example.focusflow.services.IGestorUbicacion;

public class GpsManager implements IGestorUbicacion {

    private final Context context;
    private Location ubicacionInicio;
    private Location ubicacionActual;

    public GpsManager(Context context) {
        this.context = context;
    }

    @Override
    public void iniciarRastreo() {
        // TODO: implementar con FusedLocationProviderClient
        // Aquí capturarás la ubicación de inicio
    }

    @Override
    public double calcularDistanciaRecorrida() {
        // TODO: calcular distancia entre ubicacionInicio y ubicacionActual
        if (ubicacionInicio == null || ubicacionActual == null) return 0.0;
        return ubicacionInicio.distanceTo(ubicacionActual);
    }
}