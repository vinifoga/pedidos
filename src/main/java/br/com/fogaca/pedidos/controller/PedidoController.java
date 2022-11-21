package br.com.fogaca.pedidos.controller;

import java.lang.StackWalker.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fogaca.pedidos.controller.dto.PedidoDto;
import br.com.fogaca.pedidos.controller.dto.PedidoItemDto;
import br.com.fogaca.pedidos.controller.dto.PedidoDto;
import br.com.fogaca.pedidos.controller.form.PedidoForm;
import br.com.fogaca.pedidos.controller.form.UsuarioForm;
import br.com.fogaca.pedidos.exception.BadRequestException;
import br.com.fogaca.pedidos.model.Pedido;
import br.com.fogaca.pedidos.model.PedidoItem;
import br.com.fogaca.pedidos.model.SituacaoPedido;
import br.com.fogaca.pedidos.model.Usuario;
import br.com.fogaca.pedidos.service.PedidoItemService;
import br.com.fogaca.pedidos.service.PedidoService;
import br.com.fogaca.pedidos.service.ProdutoService;
import br.com.fogaca.pedidos.service.UsuarioService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoItemService pedidoItemService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    @Cacheable(value = "listaPedido")
    public List<PedidoDto> list(){
            List<Pedido> pedidos = pedidoService.list();
            return PedidoDto.converterUsuarioDtoList(pedidos);
    }

    @GetMapping
    @RequestMapping("/page")
    public Page<PedidoDto> listPage(@PageableDefault(sort= {"dataCadastro"}, direction = Direction.DESC, page = 0, size = 20) Pageable paginacao){
        Page<Pedido> pedidos = pedidoService.findAll(paginacao);
        return PedidoDto.converterPedidoDtoPage(pedidos);
    }

    @PostMapping
    @Transactional
	@CacheEvict(value = "listaPedido", allEntries = true)
    public ResponseEntity<PedidoDto> create(@RequestBody @Valid PedidoForm pedidoForm, UriComponentsBuilder uriBuilder){
        Pedido pedido = pedidoForm.converter(usuarioService, pedidoService, produtoService);
        pedidoService.save(pedido);
        URI uri = uriBuilder.path("pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(new PedidoDto(pedido));
    }

    @GetMapping("/id/{id}")
	public ResponseEntity<PedidoDto> findById(@PathVariable UUID id){
		return ResponseEntity.ok(new PedidoDto(pedidoService.findById(id)));
	}

    @PutMapping("/update/{id}")
	@Transactional
	@CacheEvict(value = "listaPedido", allEntries = true)
	public ResponseEntity<PedidoDto> update(@PathVariable UUID id, @RequestBody @Valid PedidoForm pedidoForm){
		Pedido pedido = pedidoForm.update(id, pedidoService, usuarioService);
		return ResponseEntity.ok(new PedidoDto(pedido));
	}

    @PutMapping("/desconto;{id}")
    public ResponseEntity<PedidoDto> aplicaDesconto(@PathVariable UUID id, @RequestParam("perc_desc") Double perc){
        Pedido pedido = pedidoService.findById(id);
        if(pedido.getSituacaoPedido().toString().equals(SituacaoPedido.ABERTO.toString())){
            throw new BadRequestException("Pedido não está em Aberto, não é possível aplicar desconto");
        }
        List<PedidoItem> itens = pedidoItemService.findByPedidoId(pedido.getId());
        pedido.setItensPedido(itens);

        if(pedidoService.getDesconto(pedido, perc)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            throw new BadRequestException("Não é itens do tipo Produto");
        }
    }

    @DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaPedido", allEntries = true)
	public ResponseEntity<PedidoDto> delete(@PathVariable UUID id){
		pedidoService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
