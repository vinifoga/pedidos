package br.com.fogaca.pedidos.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.fogaca.pedidos.model.Pedido;
import br.com.fogaca.pedidos.repository.PedidoRepository;

import org.springframework.data.domain.Pageable;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> list(){
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(UUID id){
        return pedidoRepository.findById(id);
    }

    public void save(Pedido pedido){
        pedidoRepository.save(pedido);
    }

    public void deleteById(UUID id){
        pedidoRepository.deleteById(id);
    }

    public Page<Pedido> findAll(Pageable pageable){
        return pedidoRepository.findAll(pageable);
    }
}
