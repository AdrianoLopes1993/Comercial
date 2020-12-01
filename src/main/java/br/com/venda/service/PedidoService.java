package br.com.venda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.venda.model.Pedido;
import br.com.venda.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido salvarPedido(Pedido pedido) {
		return repository.save(pedido);
	}

	public List<Pedido> listarPedido() {
		return repository.findAll();
	}

	public Optional<Pedido> listarPedidoId(Long id) {
		return repository.findById(id);
	}

}
