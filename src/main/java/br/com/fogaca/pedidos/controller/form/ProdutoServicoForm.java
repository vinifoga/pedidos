package br.com.fogaca.pedidos.controller.form;

import java.math.BigDecimal;

import br.com.fogaca.pedidos.model.ProdutoServico;
import br.com.fogaca.pedidos.model.Tipo;

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
}
