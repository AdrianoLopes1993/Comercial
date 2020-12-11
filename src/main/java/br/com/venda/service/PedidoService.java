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

	public Pedido salvarPedido(Pedido pedido) {

		Long idCliente = pedido.getCodigoCliente();
		ClienteDTO dtoCliente = rt.getForObject(consultaCliente + "/" + idCliente, ClienteDTO.class);

		Long idProduto = pedido.getCodigoProduto();
		ProdutoDTO dtoProduto = rt.getForObject(consultaProduto + "/" + idProduto, ProdutoDTO.class);

		Pedido ped = new Pedido();
		ped.setCodigoCliente(pedido.getCodigoCliente());
		ped.setCpf(dtoCliente.getCpf());
		ped.setNomeCliente(dtoCliente.getNomeCliente());
		ped.setTelefone(dtoCliente.getTelefone());

		ped.setCodigoProduto(pedido.getCodigoProduto());
		ped.setCusto(dtoProduto.getCusto());
		ped.setFornecedor(dtoProduto.getFornecedor());
		ped.setNomeProduto(dtoProduto.getNomeProduto());
		ped.setValorFinal(dtoProduto.getValorFinal());

		ped.setFormaPagamento(pedido.getFormaPagamento());

		return repository.save(ped);

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
