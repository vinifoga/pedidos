package br.com.fogaca.pedidos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.aspectj.lang.annotation.Before;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import br.com.fogaca.pedidos.exception.BadRequestException;
import br.com.fogaca.pedidos.model.ProdutoServico;
import br.com.fogaca.pedidos.model.Tipo;
import br.com.fogaca.pedidos.repository.ProdutoServicoRepository;

@SpringBootTest
public class ProdutoServiceTest {

    private static final UUID ID = UUID.fromString("6a66a480-e461-48c9-bce7-1d5230686b31");
    private static final Tipo TIPO = Tipo.PRODUTO;
    private static final String MARCA = "DELL";
    private static final String DESCRICAO = "Notebook";
    private static final BigDecimal VALORCUSTO = new BigDecimal("2000");
    private static final BigDecimal VALORVENDA = new BigDecimal("3500");
    private static final Double QUANTIDADE = 30.0;
    private static final boolean ATIVO = true; 

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoServicoRepository produtoRepository;

    private ProdutoServico produto;

    private Optional<ProdutoServico> opProduto;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        iniciarProduto();
    }

    @Test
    void quandoAcharIdRetornarProduto() {
        Mockito.when(produtoRepository.findById(Mockito.any())).thenReturn(opProduto);
        ProdutoServico resposta = produtoService.findById(ID);
        assertNotNull(resposta);
        assertEquals(ProdutoServico.class, resposta.getClass());
        assertEquals(ID, resposta.getId());
        assertEquals(TIPO, resposta.getTipo());
        assertEquals(MARCA, resposta.getMarca());
        assertEquals(DESCRICAO, resposta.getDescricao());
        assertEquals(VALORCUSTO, resposta.getValorCusto());
        assertEquals(VALORVENDA, resposta.getValorVenda());
        assertEquals(QUANTIDADE, resposta.getQuantidadeDisponivel());
        assertEquals(ATIVO, resposta.isAtivo());

    }

    @Test
    void quandoNaoEncontrarProdutoRetornarException(){
        when(produtoRepository.findById(any())).thenThrow(new BadRequestException("Objeto não encontrado"));

        try{
            produtoService.findById(ID);
        } catch (Exception ex){
            assertEquals(BadRequestException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    @Test
    void quandoBuscarTodosRetornarListaProdutos() {
        when(produtoRepository.findAll()).thenReturn(List.of(produto));
        List<ProdutoServico> resposta = produtoService.list();
        assertNotNull(resposta);
        assertEquals(1, resposta.size());
        assertEquals(ProdutoServico.class, resposta.get(0).getClass());
        assertEquals(ID, resposta.get(0).getId());  
        assertEquals(TIPO, resposta.get(0).getTipo());
        assertEquals(MARCA, resposta.get(0).getMarca());
        assertEquals(DESCRICAO, resposta.get(0).getDescricao());
        assertEquals(VALORCUSTO, resposta.get(0).getValorCusto());
        assertEquals(VALORVENDA, resposta.get(0).getValorVenda());
        assertEquals(QUANTIDADE, resposta.get(0).getQuantidadeDisponivel());
        assertEquals(ATIVO, resposta.get(0).isAtivo());
    }
    

    private void iniciarProduto(){
        produto = new ProdutoServico(ID,TIPO, MARCA,DESCRICAO,VALORCUSTO,VALORVENDA,QUANTIDADE,ATIVO);
        opProduto = Optional.of(new ProdutoServico(ID,TIPO, MARCA,DESCRICAO,VALORCUSTO,VALORVENDA,QUANTIDADE,ATIVO));
    }
}
