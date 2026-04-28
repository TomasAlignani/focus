package com.example.focusflow.services;

import com.example.focusflow.models.Usuario;

public interface IUsuarioDAO {
    Usuario buscarPorNombre(String nombre);
    Usuario guardarUsuario(Usuario usuario);
}