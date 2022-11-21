package br.com.fogaca.pedidos.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.fogaca.pedidos.model.PedidoItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    
    public static Page<PedidoItemDto> converterPedidoItemDtoPage(Page<PedidoItem> itens){
        return itens.map(PedidoItemDto::new);
    }

    public static List<PedidoItemDto> converterPedidoItemDtoList(List<PedidoItem> itens){
        return itens.stream().map(PedidoItemDto::new).collect(Collectors.toList());
    }

}
