package br.com.fogaca.pedidos.controller.form;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import br.com.fogaca.pedidos.model.PedidoItem;
import br.com.fogaca.pedidos.service.PedidoItemService;
import br.com.fogaca.pedidos.service.PedidoService;
import br.com.fogaca.pedidos.service.ProdutoService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoItemForm {
    private UUID pedidoId;
    private UUID produtoServicoId;
    private Double quantidade;
    private BigDecimal valor;
    private static Optional<PedidoItem> findById;

    public PedidoItem converter(PedidoService pedidoService, ProdutoService produtoService){
        return new PedidoItem(pedidoService.findById(pedidoId),produtoService.findById(pedidoId), quantidade, valor);
    }

    public PedidoItem update(UUID id, PedidoItemService itemPedidoService, PedidoService pedidoService, ProdutoService produtoService) {
        PedidoItem item = itemPedidoService.findById(id);
        item.setPedido(pedidoService.findById(this.pedidoId));
        item.setProdutoServico(produtoService.findById(this.produtoServicoId));
        item.setQuantidade(this.quantidade);
        item.setValor(this.valor);
        return item;
    }
}
