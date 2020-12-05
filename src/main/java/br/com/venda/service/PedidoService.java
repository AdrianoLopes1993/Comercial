package br.com.venda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.venda.dto.ClienteDTO;
import br.com.venda.model.Pedido;
import br.com.venda.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private RestTemplate rt;

	@Value("${url.consulta.cliente}")
	private String consultaCliente;

	public Pedido salvarPedido(Pedido pedido) {

		Long id = pedido.getCodigoCliente();

		ClienteDTO dto = rt.getForObject(consultaCliente + "/" + id, ClienteDTO.class);

		return repository.save(pedido);
	}

	public List<Pedido> listarPedido() {
		return repository.findAll();
	}

	public Optional<Pedido> listarPedidoId(Long id) {
		return repository.findById(id);
	}

	public void deletarPedidoId(Long id) {
		repository.deleteById(id);
	}

}
