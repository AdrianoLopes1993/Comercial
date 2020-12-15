package br.com.venda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.venda.dto.ClienteDTO;
import br.com.venda.dto.ProdutoDTO;
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

	@Value("${url.consulta.produto}")
	private String consultaProduto;

	public List<Pedido> listarPedido() {
		return repository.findAll();
	}

	public Optional<Pedido> listarPedidoId(Long id) {
		return repository.findById(id);
	}

	public void deletarPedidoId(Long id) {
		repository.deleteById(id);
	}

	public Pedido salvarPedido(Pedido pedido) {

		Long idCliente = pedido.getCodigoCliente();
		ClienteDTO objectCliente = rt.getForObject(consultaCliente + "/" + idCliente, ClienteDTO.class);

		Long idProduto = pedido.getCodigoProduto();
		ProdutoDTO objectProduto = rt.getForObject(consultaProduto + "/" + idProduto, ProdutoDTO.class);

		Pedido ped = new Pedido();
		ped.setCodigoCliente(pedido.getCodigoCliente());
		ped.setNomeCliente(objectCliente.getNomeCliente());
		ped.setCpf(objectCliente.getCpf());
		ped.setTelefone(objectCliente.getTelefone());

		ped.setCodigoProduto(pedido.getCodigoProduto());
		ped.setFornecedor(objectProduto.getFornecedor());
		ped.setNomeProduto(objectProduto.getNomeProduto());
		ped.setCusto(objectProduto.getCusto());
		ped.setValorFinal(objectProduto.getValorFinal());

		ped.setFormaPagamento(pedido.getFormaPagamento());

		Pedido save = repository.save(ped);
		return save;
	}
}
