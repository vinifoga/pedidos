package br.com.fogaca.pedidos.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fogaca.pedidos.controller.dto.UsuarioDto;
import br.com.fogaca.pedidos.controller.form.UsuarioForm;
import br.com.fogaca.pedidos.model.Usuario;
import br.com.fogaca.pedidos.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Cacheable(value = "listaUsuario")
    public List<UsuarioDto> list(UUID usuarioId){
        if(usuarioId == null){
            return UsuarioDto.converterUsuarioDtoList(usuarioService.list());
        } else {
            List<Usuario> usuarios = new ArrayList<>();
            usuarios.add(usuarioService.findById(usuarioId).get());
            return UsuarioDto.converterUsuarioDtoList(usuarios);
        }
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listUsuario", allEntries = true)
    public ResponseEntity<UsuarioDto> create(@RequestBody @Valid UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder){
        Usuario usuario = usuarioForm.converter();
        usuarioService.save(usuario);
        URI uri = uriBuilder.path("usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }

    @GetMapping("/id/{id}")
	public ResponseEntity<UsuarioDto> findById(@PathVariable UUID id){
		if(usuarioService.findById(id).isPresent()) {
			return ResponseEntity.ok(new UsuarioDto(usuarioService.findById(id).get()));
		}
		return ResponseEntity.notFound().build();
	}

    @PutMapping("/update/{id}")
	@Transactional
	@CacheEvict(value = "listaUsuario", allEntries = true)
	public ResponseEntity<UsuarioDto> update(@PathVariable UUID id, @RequestBody @Valid UsuarioForm usuarioForm){
		if(usuarioService.findById(id).isPresent()) {
			Usuario usuario = usuarioForm.update(id, usuarioService);
			return ResponseEntity.ok(new UsuarioDto(usuario));
		}
		return ResponseEntity.notFound().build();
	}

    @DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaUsuario", allEntries = true)
	public ResponseEntity<UsuarioDto> delete(@PathVariable UUID id){
		if(usuarioService.findById(id).isPresent()) {
			usuarioService.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
