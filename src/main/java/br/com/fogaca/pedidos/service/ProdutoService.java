package br.com.fogaca.pedidos.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fogaca.pedidos.exception.BadRequestException;
import br.com.fogaca.pedidos.model.ProdutoServico;
import br.com.fogaca.pedidos.repository.ProdutoServicoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoServicoRepository produtoRepository;

    public List<ProdutoServico> list(){
        return produtoRepository.findAll();
    }

    public ProdutoServico findById(UUID id){
        return produtoRepository.findById(id).orElseThrow(()-> new BadRequestException("Objeto não encontrado"));
    }

    public void save(ProdutoServico produto){
        produtoRepository.save(produto);
    }

    public void deleteById(UUID id){
        produtoRepository.deleteById(id);
    }

    public Page<ProdutoServico> findAll(Pageable pageable){
        return produtoRepository.findAll(pageable);
    }
}
