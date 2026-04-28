package com.example.focusflow.data;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.focusflow.models.Usuario;
import com.example.focusflow.services.IUsuarioDAO;

public class UsuarioDAOLocalImpl implements IUsuarioDAO {

    private static final String PREFS = "focusflow_users";
    private final SharedPreferences prefs;

    public UsuarioDAOLocalImpl(Context context) {
        prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    @Override
    public Usuario buscarPorNombre(String nombre) {
        String id = prefs.getString("user_" + nombre, null);
        if (id == null) return null;
        Usuario u = new Usuario(nombre);
        u.setId(id);
        return u;
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        String id = java.util.UUID.randomUUID().toString();
        usuario.setId(id);
        prefs.edit().putString("user_" + usuario.getNombre(), id).apply();
        return usuario;
    }
}