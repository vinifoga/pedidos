package br.com.fogaca.pedidos.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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

import br.com.fogaca.pedidos.controller.dto.PedidoItemDto;
import br.com.fogaca.pedidos.controller.form.PedidoItemForm;
import br.com.fogaca.pedidos.controller.form.UsuarioForm;
import br.com.fogaca.pedidos.model.Pedido;
import br.com.fogaca.pedidos.model.PedidoItem;
import br.com.fogaca.pedidos.model.Usuario;
import br.com.fogaca.pedidos.service.PedidoItemService;
import br.com.fogaca.pedidos.service.PedidoService;
import br.com.fogaca.pedidos.service.ProdutoService;

@RestController
@RequestMapping("/pedidoItem")
public class PedidoItemController {
    
    @Autowired
    private PedidoItemService itemPedidoService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    @Cacheable(value = "listaItens")
    public List<PedidoItemDto> list(){
            List<PedidoItem> itens = itemPedidoService.list();
            return PedidoItemDto.converterPedidoItemDtoList(itens);
    }

    @GetMapping
    @RequestMapping("/page")
    public Page<PedidoItemDto> listPage( @PageableDefault(sort= {"nomeUsuario"}, direction = Direction.DESC, page = 0, size = 20) Pageable paginacao){
        Page<PedidoItem> itens = itemPedidoService.findAll(paginacao);
        return PedidoItemDto.converterPedidoItemDtoPage(itens);
    }

    @PostMapping
    @Transactional
	@CacheEvict(value = "listaItens", allEntries = true)
    public ResponseEntity<PedidoItemDto> create(@RequestBody @Valid PedidoItemForm PedidoItemForm, UriComponentsBuilder uriBuilder){
        PedidoItem item = PedidoItemForm.converter(pedidoService, produtoService);
        itemPedidoService.save(item);
        URI uri = uriBuilder.path("itens/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new PedidoItemDto(item));
    }

    @GetMapping("/id/{id}")
	public ResponseEntity<PedidoItemDto> findById(@PathVariable UUID id){
		return ResponseEntity.ok(new PedidoItemDto(itemPedidoService.findById(id)));
	}

    @PutMapping("/update/{id}")
	@Transactional
	@CacheEvict(value = "listaItens", allEntries = true)
	public ResponseEntity<PedidoItemDto> update(@PathVariable UUID id, @RequestBody @Valid PedidoItemForm pedidoItemForm){
		PedidoItem item = pedidoItemForm.update(id, itemPedidoService, pedidoService, produtoService);
		return ResponseEntity.ok(new PedidoItemDto(item));
	}

    @DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaItens", allEntries = true)
	public ResponseEntity<PedidoItemDto> delete(@PathVariable UUID id){
		itemPedidoService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
