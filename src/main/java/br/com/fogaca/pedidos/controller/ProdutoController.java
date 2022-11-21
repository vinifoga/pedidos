package br.com.fogaca.pedidos.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fogaca.pedidos.controller.dto.ProdutoServicoDto;
import br.com.fogaca.pedidos.controller.form.ProdutoServicoForm;
import br.com.fogaca.pedidos.controller.form.UsuarioForm;
import br.com.fogaca.pedidos.exception.BadRequestException;
import br.com.fogaca.pedidos.model.Pedido;
import br.com.fogaca.pedidos.model.PedidoItem;
import br.com.fogaca.pedidos.model.ProdutoServico;
import br.com.fogaca.pedidos.model.Usuario;
import br.com.fogaca.pedidos.service.PedidoService;
import br.com.fogaca.pedidos.service.ProdutoService;
import br.com.fogaca.pedidos.service.UsuarioService;

@RestController
@RequestMapping("/produto-servico")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    @Cacheable(value = "listaProduto")
    public List<ProdutoServicoDto> list(){
            List<ProdutoServico> produtos = produtoService.list();
            return ProdutoServicoDto.converterProdutoDtoList(produtos);
    }

    @GetMapping
    @RequestMapping("/page")
    public Page<ProdutoServicoDto> listPage( @PageableDefault(sort= {"descricao"}, direction = Direction.DESC, page = 0, size = 20) Pageable paginacao){
        Page<ProdutoServico> produtos = produtoService.findAll(paginacao);
        return ProdutoServicoDto.converterProdutoDtoPage(produtos);
    }

    @PostMapping
    @Transactional
	@CacheEvict(value = "listaProduto", allEntries = true)
    public ResponseEntity<ProdutoServicoDto> create(@RequestBody @Valid ProdutoServicoForm produtoForm, UriComponentsBuilder uriBuilder){
        ProdutoServico produto = produtoForm.converter();
        if(produto.isAtivo()==false){
            throw new BadRequestException("Produto Inativo, não é possível adicionar");
        }
        produtoService.save(produto);
        URI uri = uriBuilder.path("produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoServicoDto(produto));
    }

    @GetMapping("/id/{id}")
	public ResponseEntity<ProdutoServicoDto> findById(@PathVariable UUID id){
		return ResponseEntity.ok(new ProdutoServicoDto(produtoService.findById(id)));
	}

    @PutMapping("/update/{id}")
	@Transactional
	@CacheEvict(value = "listaProduto", allEntries = true)
	public ResponseEntity<ProdutoServicoDto> update(@PathVariable UUID id, @RequestBody @Valid ProdutoServicoForm produtoForm){
		ProdutoServico produto = produtoForm.update(id, produtoService);
		return ResponseEntity.ok(new ProdutoServicoDto(produto));
	}

    @DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaProduto", allEntries = true)
	public ResponseEntity<ProdutoServicoDto> delete(@PathVariable UUID id){
        List<Pedido> pedidos = pedidoService.list();
        for (Pedido pedido : pedidos) {
            List<PedidoItem> itensPedido = pedido.getItensPedido();
            for (PedidoItem item : itensPedido) {
                if(item.getProdutoServico().equals(produtoService.findById(id))){
                    throw new BadRequestException("Existe pedido com esse produto, não é possível excluir");
                }
            }
        }
		produtoService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
