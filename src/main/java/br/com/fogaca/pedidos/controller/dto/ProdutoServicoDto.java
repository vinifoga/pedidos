package br.com.fogaca.pedidos.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.fogaca.pedidos.model.ProdutoServico;
import br.com.fogaca.pedidos.model.Tipo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoServicoDto {
    private UUID id;
    private Tipo tipo;
    private String marca;
    private String descricao;
    private BigDecimal valorCusto;
    private BigDecimal valorVenda;
    private Double quantidadeDisponivel;
    private boolean ativo = true;

    public ProdutoServicoDto(ProdutoServico produtoServico){
        this.id = produtoServico.getId();
        this.tipo = produtoServico.getTipo();
        this.marca = produtoServico.getMarca();
        this.descricao = produtoServico.getDescricao();
        this.valorCusto = produtoServico.getValorCusto();
        this.valorVenda = produtoServico.getValorVenda();
        this.quantidadeDisponivel = produtoServico.getQuantidadeDisponivel();
        this.ativo = produtoServico.isAtivo();
    }

    public static Page<ProdutoServicoDto> converterProdutoDtoPage(Page<ProdutoServico> produtos){
        return produtos.map(ProdutoServicoDto::new);
    }

    public static List<ProdutoServicoDto> converterProdutoDtoList(List<ProdutoServico> produtos){
        return produtos.stream().map(ProdutoServicoDto::new).collect(Collectors.toList());
    }
}
