package br.com.fogaca.pedidos.controller.dto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.fogaca.pedidos.model.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {
    private UUID id;
    private String nomeUsuario;

    public UsuarioDto (Usuario usuario){
        this.id = usuario.getId();
        this.nomeUsuario = usuario.getNomeUsuario();
    }    

    public static Page<UsuarioDto> converterUsuarioDtoPage(Page<Usuario> usuarios){
        return usuarios.map(UsuarioDto::new);
    }

    public static List<UsuarioDto> converterUsuarioDtoList(List<Usuario> usuarios){
        return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }
}
