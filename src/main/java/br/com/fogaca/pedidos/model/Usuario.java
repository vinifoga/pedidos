package br.com.fogaca.pedidos.model;

import java.util.UUID;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usua")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "nome")
    private String nomeUsuario;

    public Usuario() {
    }

    public Usuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }   
    
}
