package br.com.fogaca.pedidos.controller.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.com.fogaca.pedidos.model.Pedido;
import br.com.fogaca.pedidos.model.PedidoItem;
import br.com.fogaca.pedidos.model.SituacaoPedido;
import br.com.fogaca.pedidos.service.PedidoService;
import br.com.fogaca.pedidos.service.ProdutoService;
import br.com.fogaca.pedidos.service.UsuarioService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoForm {
    
    private String cpfCnpjComprador;
    private BigDecimal valor;
    private BigDecimal desconto;
    private BigDecimal valorTotal;
    private SituacaoPedido situacaoPedido;
    private LocalDateTime dataCadastro;
    private UUID usuarioId;
    private List<PedidoItem> itensPedido;

    public Pedido converter(UsuarioService usuarioService, PedidoService pedidoService, ProdutoService produtoService){
        return new Pedido(cpfCnpjComprador, valor, desconto, valorTotal, situacaoPedido, dataCadastro, usuarioService.findById(usuarioId), itensPedido);
    }

    public Pedido update(UUID id, PedidoService pedidoService, UsuarioService usuarioService) {
        Pedido pedido = pedidoService.findById(id);
        pedido.setCpfCnpjComprador(this.cpfCnpjComprador);
        pedido.setValor(this.valor);
        pedido.setDesconto(this.desconto);
        pedido.setValorTotal(this.valorTotal);
        pedido.setSituacaoPedido(this.situacaoPedido);
        pedido.setDataCadastro(this.dataCadastro);
        pedido.setUsuario(usuarioService.findById(this.usuarioId));
        pedido.setItensPedido(this.itensPedido);

        return null;
    }
    
}
