package br.com.fogaca.pedidos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fogaca.pedidos.model.PedidoItem;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, UUID>{
    
}
