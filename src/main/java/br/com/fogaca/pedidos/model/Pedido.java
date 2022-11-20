package br.com.fogaca.pedidos.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Data
@Entity 
@Table(name = "pedi")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "cpf_cnpj_comp")
    private String cpfCnpjComprador;

    @Column(name = "val")
    private BigDecimal valor = BigDecimal.ZERO;
    
    @Column(name = "dsct")
    private BigDecimal desconto = BigDecimal.ZERO;

    @Column(name = "val_tot")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Column(name = "situ")
    @Enumerated(EnumType.STRING)
    private SituacaoPedido situacaoPedido = SituacaoPedido.ABERTO;

    @Column(name = "data_cada")
    private LocalDateTime dataCadastro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usua_pedi", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name = "itns")
    @OneToMany(mappedBy = "pedido")
    @JsonIgnore
    private List<PedidoItem> itensPedido = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(String cpfCnpjComprador, BigDecimal valor, BigDecimal desconto, BigDecimal valorTotal,
            SituacaoPedido situacaoPedido, LocalDateTime dataCadastro, Usuario usuario, List<PedidoItem> itensPedido) {
        this.cpfCnpjComprador = cpfCnpjComprador;
        this.valor = valor;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
        this.situacaoPedido = situacaoPedido;
        this.dataCadastro = dataCadastro;
        this.usuario = usuario;
        this.itensPedido = itensPedido;
    }

    

    
}
