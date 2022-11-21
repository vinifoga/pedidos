package br.com.fogaca.pedidos.controller.form;

import java.util.UUID;

import br.com.fogaca.pedidos.model.Usuario;
import br.com.fogaca.pedidos.service.UsuarioService;

public class UsuarioForm {

    private String nomeUsuario;

    public Usuario converter(){
        return new Usuario(nomeUsuario);
    }

    public Usuario update(UUID id, UsuarioService usuarioService) {
        Usuario usuario = usuarioService.findById(id).get();
        usuario.setNomeUsuario(this.nomeUsuario);
        return usuario;
    }
}
