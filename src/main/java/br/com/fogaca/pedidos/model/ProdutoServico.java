package br.com.fogaca.pedidos.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "prod_serv")
public class ProdutoServico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private Tipo tipo;

    @Column(name = "marc")
    private String marca;

    @Column(name = "dscr")
    private String descricao;
    
    @Column(name = "val_cust", nullable = false)
    private BigDecimal valorCusto;

    @Column(name = "val_vend", nullable = false)
    private BigDecimal valorVenda;

    @Column(name = "qtd_dispo")
    private Double quantidadeDisponivel;
    
    @Column(name = "ativ")
    private boolean ativo = true;

    public ProdutoServico() {
    }

    public ProdutoServico(Tipo tipo, String marca, String descricao, BigDecimal valorCusto, BigDecimal valorVenda,
            Double quantidadeDisponivel, boolean ativo) {
        this.tipo = tipo;
        this.marca = marca;
        this.descricao = descricao;
        this.valorCusto = valorCusto;
        this.valorVenda = valorVenda;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.ativo = ativo;
    }

    public ProdutoServico(UUID id, Tipo tipo, String marca, String descricao, BigDecimal valorCusto,
            BigDecimal valorVenda, Double quantidadeDisponivel, boolean ativo) {
        this.id = id;
        this.tipo = tipo;
        this.marca = marca;
        this.descricao = descricao;
        this.valorCusto = valorCusto;
        this.valorVenda = valorVenda;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.ativo = ativo;
    }

    

    
}
