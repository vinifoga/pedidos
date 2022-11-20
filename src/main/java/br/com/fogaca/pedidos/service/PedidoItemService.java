package br.com.fogaca.pedidos.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fogaca.pedidos.model.PedidoItem;
import br.com.fogaca.pedidos.repository.PedidoItemRepository;

@Service
public class PedidoItemService {
    
    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    public List<PedidoItem> list(){
        return pedidoItemRepository.findAll();
    }

    public Optional<PedidoItem> findById(UUID id){
        return pedidoItemRepository.findById(id);
    }

    public void save(PedidoItem itemPedido){
        pedidoItemRepository.save(itemPedido);
    }

    public void deleteById(UUID id){
        pedidoItemRepository.deleteById(id);
    }

    public Page<PedidoItem> findAll(Pageable pageable){
        return pedidoItemRepository.findAll(pageable);
    }
}
