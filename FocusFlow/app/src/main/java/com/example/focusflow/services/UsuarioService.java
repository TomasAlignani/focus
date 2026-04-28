package com.example.focusflow.services;

import com.example.focusflow.models.Usuario;

public class UsuarioService {

    private final IUsuarioDAO usuarioDAO;

    public UsuarioService(IUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario buscarUsuario(String nombre) {
        return usuarioDAO.buscarPorNombre(nombre);
    }

    public Usuario registrarNuevoUsuario(String nombre) {
        Usuario nuevo = new Usuario(nombre);
        return usuarioDAO.guardarUsuario(nuevo);
    }
}