package br.com.fogaca.pedidos.controller.form;

import br.com.fogaca.pedidos.model.Usuario;

public class UsuarioForm {

    private String nomeUsuario;

    public Usuario converter(){
        return new Usuario(nomeUsuario);
    }
}
