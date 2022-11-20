package br.com.fogaca.pedidos.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pedi_itns")
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "pedi_codi", foreignKey = @ForeignKey(name = "fk_pedi_item_pedi"))
    private Pedido pedido;

    @OneToOne
    @JoinColumn(name = "prod_serv_codi", foreignKey = @ForeignKey(name = "fk_prod_serv_codi"))
    private ProdutoServico produtoServico;

    @Column(name = "qtd")
    private Double quantidade;

    @Column(name = "val")
    private BigDecimal valor;

    public PedidoItem() {
    }

    public PedidoItem(Pedido pedido, ProdutoServico produtoServico, Double quantidade, BigDecimal valor) {
        this.pedido = pedido;
        this.produtoServico = produtoServico;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    
}
