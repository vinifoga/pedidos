package br.com.fogaca.pedidos.controller.form;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.fogaca.pedidos.model.Usuario;
import br.com.fogaca.pedidos.service.UsuarioService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioForm {

    @NotNull @NotEmpty
    private String nomeUsuario;

    public Usuario converter(){
        return new Usuario(nomeUsuario);
    }

    public Usuario update(UUID id, UsuarioService usuarioService) {
        Usuario usuario = usuarioService.findById(id);
        usuario.setNomeUsuario(this.nomeUsuario);
        return usuario;
    }
}
