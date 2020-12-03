package br.com.venda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.venda.model.Pedido;
import br.com.venda.service.PedidoService;

@RestController
@RequestMapping("/v1/pedido")
public class PedidoController {

	@Autowired
	private PedidoService service;

	@PostMapping("/salvar")
	public Pedido salvaPedido(@RequestBody Pedido pedido) {
		return service.salvarPedido(pedido);
	}

	@GetMapping("/listar")
	public List<Pedido> listaPedido() {
		return service.listarPedido();
	}

	@GetMapping("/listar/{id}")
	public Optional<Pedido> listaPedidoId(@PathVariable("id") Long id) {
		return service.listarPedidoId(id);
	}
	
	@DeleteMapping("/deletar/{id}")
	public void deletaPedidoId(@PathVariable("id") Long id) {
		service.deletarPedidoId(id);
	}

}
