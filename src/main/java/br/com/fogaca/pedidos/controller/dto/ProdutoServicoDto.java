package br.com.fogaca.pedidos.controller.dto;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.fogaca.pedidos.model.ProdutoServico;
import br.com.fogaca.pedidos.model.Tipo;
import lombok.Data;

@Data
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
}
