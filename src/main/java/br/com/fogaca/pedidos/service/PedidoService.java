package br.com.fogaca.pedidos.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.fogaca.pedidos.exception.BadRequestException;
import br.com.fogaca.pedidos.model.Pedido;
import br.com.fogaca.pedidos.model.PedidoItem;
import br.com.fogaca.pedidos.model.Tipo;
import br.com.fogaca.pedidos.repository.PedidoRepository;

import org.springframework.data.domain.Pageable;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> list(){
        return pedidoRepository.findAll();
    }

    public Pedido findById(UUID id){
        return pedidoRepository.findById(id).orElseThrow(()-> new BadRequestException("Objeto não encontrado"));
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

    public boolean getDesconto(Pedido pedido, Double perc) {
        List<PedidoItem> itens = pedido.getItensPedido().stream().filter(p -> p.getProdutoServico().getTipo().equals(Tipo.PRODUTO)).collect(Collectors.toList());
        if(!itens.isEmpty()){
            pedido.setDesconto(BigDecimal.valueOf(perc));
            pedido.setValorTotal(pedido.getValorTotal().subtract(pedido.getValorTotal().multiply(BigDecimal.valueOf(perc/100))));
            return true;
        }
        return false;
    }

}
