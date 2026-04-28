package com.example.focusflow.data;

import android.content.Context;
import androidx.room.*;
import com.example.focusflow.models.SesionEstudio;
import com.example.focusflow.models.EstadoSesion;
import com.example.focusflow.models.TipoDescanso;
import com.example.focusflow.services.ISesionDAO;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;

public class SesionDAOLocalImpl implements ISesionDAO {

    // --- Entidad Room ---
    @Entity(tableName = "sesiones")
    public static class SesionEntity {
        @PrimaryKey
        @NonNull
        public String id = "";
        public String objetivo;
        public String usuarioId;
        public int tiempoEstudiado;
        public int nivelEstres;
        public String estadoSesion;
        public String tipoDescanso;
        public double latitud;
        public double longitud;
    }

    // --- DAO Room ---
    @Dao
    public interface SesionRoomDAO {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertar(SesionEntity sesion);

        @Query("SELECT * FROM sesiones WHERE usuarioId = :uid")
        List<SesionEntity> listarPorUsuario(String uid);
    }

    // --- Base de datos Room ---
    @Database(entities = {SesionEntity.class}, version = 1)
    public abstract static class AppDatabase extends RoomDatabase {
        public abstract SesionRoomDAO sesionDAO();
    }

    private final SesionRoomDAO dao;

    public SesionDAOLocalImpl(Context context) {
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "focusflow-db")
                .allowMainThreadQueries()
                .build();
        this.dao = db.sesionDAO();
    }

    @Override
    public void guardarSesion(SesionEstudio sesion) {
        SesionEntity e = new SesionEntity();
        e.id = sesion.getId();
        e.objetivo = sesion.getObjetivo();
        e.usuarioId = sesion.getUsuarioId();
        e.tiempoEstudiado = sesion.getTiempoEstudiado();
        e.nivelEstres = sesion.getNivelEstres();
        e.estadoSesion = sesion.getEstadoSesion() != null ? sesion.getEstadoSesion().name() : "";
        e.tipoDescanso = sesion.getTipoDescanso() != null ? sesion.getTipoDescanso().name() : "";
        e.latitud = sesion.getLatitud();
        e.longitud = sesion.getLongitud();
        dao.insertar(e);
    }

    @Override
    public List<SesionEstudio> listarSesiones(String usuarioId) {
        List<SesionEntity> entities = dao.listarPorUsuario(usuarioId);
        List<SesionEstudio> resultado = new ArrayList<>();
        for (SesionEntity e : entities) {
            SesionEstudio s = new SesionEstudio(e.objetivo);
            s.setId(e.id);
            s.setUsuarioId(e.usuarioId);
            s.setTiempoEstudiado(e.tiempoEstudiado);
            s.setNivelEstres(e.nivelEstres);
            s.setEstadoSesion(EstadoSesion.valueOf(e.estadoSesion));
            s.setTipoDescanso(TipoDescanso.valueOf(e.tipoDescanso));
            s.setLatitud(e.latitud);
            s.setLongitud(e.longitud);
            resultado.add(s);
        }
        return resultado;
    }
}