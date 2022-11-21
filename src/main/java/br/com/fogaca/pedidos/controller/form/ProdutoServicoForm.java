package br.com.fogaca.pedidos.controller.form;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.expression.spel.ast.PropertyOrFieldReference;

import br.com.fogaca.pedidos.model.ProdutoServico;
import br.com.fogaca.pedidos.model.Tipo;
import br.com.fogaca.pedidos.service.ProdutoService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoServicoForm {
    private Tipo tipo;
    private String marca;
    private String descricao;
    private BigDecimal valorCusto;
    private BigDecimal valorVenda;
    private Double quantidadeDisponivel;
    private boolean ativo;

    public ProdutoServico converter(){
        return new ProdutoServico(tipo, marca, descricao, valorCusto, valorVenda, quantidadeDisponivel, ativo);
    }

    public ProdutoServico update(UUID id, ProdutoService produtoService) {
        ProdutoServico produto = produtoService.findById(id);
        produto.setTipo(this.tipo);
        produto.setMarca(this.marca);
        produto.setDescricao(this.descricao);
        produto.setValorCusto(this.valorCusto);
        produto.setValorVenda(this.valorVenda);
        produto.setQuantidadeDisponivel(this.quantidadeDisponivel);
        produto.setAtivo(this.ativo);
        return produto;
    }
}
