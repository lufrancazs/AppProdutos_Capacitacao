package br.com.lmf.AppProdutos.dto;

public class ProdutoDTO {
	
	private Long id;
	private String codigoBarras;
	private String nome;
	private Double price;
	private Integer quantidade;
	
	public ProdutoDTO() {
	}
	
	
	public ProdutoDTO(Long id, String codigoBarras, String nome, Double price, Integer quantidade) {
		super();
		this.id = id;
		this.codigoBarras = codigoBarras;
		this.nome = nome;
		this.price = price;
		this.quantidade = quantidade;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCodigoBarras() {
		return codigoBarras;
	}


	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Integer getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	

}
