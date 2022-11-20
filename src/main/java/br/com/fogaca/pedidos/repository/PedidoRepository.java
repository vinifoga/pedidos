package br.com.fogaca.pedidos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fogaca.pedidos.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID>{
}
