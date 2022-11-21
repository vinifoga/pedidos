package br.com.fogaca.pedidos.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.fogaca.pedidos.model.Pedido;
import br.com.fogaca.pedidos.model.PedidoItem;
import br.com.fogaca.pedidos.model.SituacaoPedido;
import lombok.Data;

@Data
public class PedidoDto {
    private UUID id;
    private String cpfCnpjComprador;
    private BigDecimal valor = BigDecimal.ZERO;
    private BigDecimal desconto = BigDecimal.ZERO;
    private BigDecimal valorTotal = BigDecimal.ZERO;
    private SituacaoPedido situacaoPedido = SituacaoPedido.ABERTO;
    private LocalDateTime dataCadastro;
    private UUID usuarioId;
    private List<PedidoItem> itensPedido = new ArrayList<>();

    public PedidoDto (Pedido pedido){
        this.id = pedido.getId();
        this.cpfCnpjComprador = pedido.getCpfCnpjComprador();
        this.valor = pedido.getValor();
        this.desconto = pedido.getDesconto();
        this.valorTotal = pedido.getValorTotal();
        this.situacaoPedido = pedido.getSituacaoPedido();
        this.dataCadastro = pedido.getDataCadastro();
        this.usuarioId = pedido.getUsuario().getId();
        this.itensPedido = pedido.getItensPedido();       
    }
}
