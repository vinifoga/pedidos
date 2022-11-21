package br.com.fogaca.pedidos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import br.com.fogaca.pedidos.exception.BadRequestException;
import br.com.fogaca.pedidos.model.Usuario;
import br.com.fogaca.pedidos.repository.UsuarioRepository;

@SpringBootTest
public class UsuarioServiceTest {

    private static final UUID ID = UUID.fromString("a6ca95c3-f6bb-48d7-8c0b-b14201ba8404");
    private static final String NOME = "Vinicius Fernandes";

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    private Optional<Usuario> opUsuario;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        iniciarUsuario();
    }

    @Test
    void quandoAcharIdRetornarUsuario() {
        Mockito.when(usuarioRepository.findById(Mockito.any())).thenReturn(opUsuario);
        Usuario resposta = usuarioService.findById(ID);
        assertNotNull(resposta);
        assertEquals(Usuario.class, resposta.getClass());
        assertEquals(ID, resposta.getId());
        assertEquals(NOME, resposta.getNomeUsuario());
    }

    @Test
    void quandoNaoEncontrarUsuarioRetornarException(){
        when(usuarioRepository.findById(any())).thenThrow(new BadRequestException("Objeto não encontrado"));

        try{
            usuarioService.findById(ID);
        } catch (Exception ex){
            assertEquals(BadRequestException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    @Test
    void quandoBuscarTodosRetornarListaUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        List<Usuario> resposta = usuarioService.list();
        assertNotNull(resposta);
        assertEquals(1, resposta.size());
        assertEquals(Usuario.class, resposta.get(0).getClass());
        assertEquals(ID, resposta.get(0).getId());
        assertEquals(NOME, resposta.get(0).getNomeUsuario());

    }

    private void iniciarUsuario(){
        usuario = new Usuario(ID, NOME);
        opUsuario = Optional.of(new Usuario(ID,NOME));
    }
}
