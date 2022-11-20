package br.com.fogaca.pedidos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fogaca.pedidos.model.ProdutoServico;

public interface ProdutoServicoRepository extends JpaRepository<ProdutoServico, UUID>{
    
}
