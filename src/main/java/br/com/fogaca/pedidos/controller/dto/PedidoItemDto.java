package br.com.fogaca.pedidos.controller.dto;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.fogaca.pedidos.model.PedidoItem;
import lombok.Data;

@Data
public class PedidoItemDto {
    private UUID id;
    private UUID pedidoId;
    private UUID produtoServicoId;
    private Double quantidade;
    private BigDecimal valor;

    public PedidoItemDto (PedidoItem pedidoItem){
        this.id = pedidoItem.getId();
        this.pedidoId = pedidoItem.getPedido().getId();
        this.produtoServicoId = pedidoItem.getProdutoServico().getId();
        this.quantidade = pedidoItem.getQuantidade();
        this.valor = pedidoItem.getValor();
    }    

}
