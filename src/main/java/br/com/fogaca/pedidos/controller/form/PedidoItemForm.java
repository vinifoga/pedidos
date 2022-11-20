package br.com.fogaca.pedidos.controller.form;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.fogaca.pedidos.model.PedidoItem;
import br.com.fogaca.pedidos.service.PedidoService;
import br.com.fogaca.pedidos.service.ProdutoService;
import lombok.Data;

@Data
public class PedidoItemForm {
    private UUID pedidoId;
    private UUID produtoServicoId;
    private Double quantidade;
    private BigDecimal valor;

    public PedidoItem converter(PedidoService pedidoService, ProdutoService produtoService){
        return new PedidoItem(pedidoService.findById(pedidoId).get(),produtoService.findById(pedidoId).get(), quantidade, valor);
    }
}
