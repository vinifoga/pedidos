package br.com.fogaca.pedidos.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fogaca.pedidos.exception.BadRequestException;
import br.com.fogaca.pedidos.model.Usuario;
import br.com.fogaca.pedidos.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> list(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(UUID id){
        return usuarioRepository.findById(id).orElseThrow(()-> new BadRequestException("Objeto não encontrado"));
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public void deleteById(UUID id){
        usuarioRepository.deleteById(id);
    }

    public Page<Usuario> findAll(Pageable pageable){
        return usuarioRepository.findAll(pageable);
    }
}
