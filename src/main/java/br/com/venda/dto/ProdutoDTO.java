package br.com.venda.dto;

public class ProdutoDTO {

	private String fornecedor;

	private String nomeProduto;

	private Long custo;

	private Long valorFinal;

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Long getCusto() {
		return custo;
	}

	public void setCusto(Long custo) {
		this.custo = custo;
	}

	public Long getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(Long valorFinal) {
		this.valorFinal = valorFinal;
	}

}
