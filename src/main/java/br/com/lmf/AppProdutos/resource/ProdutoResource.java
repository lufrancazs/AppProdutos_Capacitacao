package br.com.lmf.AppProdutos.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lmf.AppProdutos.dto.ProdutoDTO;
import br.com.lmf.AppProdutos.dto.ProdutoSimplesDTO;
import br.com.lmf.AppProdutos.entities.Produto;
import br.com.lmf.AppProdutos.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;

	@Operation(summary = "Busca Todos os Registos de Produtos")
	@GetMapping
	public ResponseEntity<List<Produto>> findAll(){
		List<Produto> produto = produtoService.findAll();
		
		if(produto == null) {
			return ResponseEntity.notFound().build();
		}
		if(produto.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produto);
	}
	
	@Operation(summary = "Busca Registros por ID de Produtos")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Produto>> findById(@PathVariable Long id){
		Optional<Produto> produto = produtoService.findById(id);
		
		if(produto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(produto);
	}
	
	@Operation(summary = "Grava o Registro de Produtos")
	@PostMapping
	public ResponseEntity<Produto> create(@RequestBody Produto produto){
		Produto newProduto = produtoService.save(produto);
		
		if(newProduto == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(newProduto);
	}
	
	@Operation(summary = "Atualiza o Registro do Produto, por ID")
	@PutMapping
	public ResponseEntity<Produto> update(@RequestBody Produto produto){
		Produto uptProduto = produtoService.update(produto);
		if(uptProduto == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(uptProduto);
	}
	
	@Operation(summary = "Exclui o Registro de Produto")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Pesquisa de uma lista contendo os dados de Produto e sua quantidade em estoque")
	@GetMapping("/produtoAndQte") //http://localhost:8081/api/produtos/produtoAndQte 
	public ResponseEntity<List<ProdutoDTO>> findProdutoAndQuantidade(){
		List<ProdutoDTO> produtoDtos = produtoService.findProdutoAndQuantidade();
		if(produtoDtos == null) {
			return ResponseEntity.notFound().build();
		}
		if(produtoDtos.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produtoDtos);
	}
	
	@Operation(summary = "Pesquisa de uma lista contendo os dados de Produto e sua quantidade em estoque filtrado por quantidade menor que o parâmetro")
	@GetMapping("/produtoAndQte/{qte}") //http://localhost:8081/api/produtos/produtoAndQte/ 
	public ResponseEntity<List<ProdutoDTO>> findProdutoAndQuantidadeMenor(@PathVariable Integer qte){
		List<ProdutoDTO> produtoDtos = produtoService.findProdutoAndQuantidadeMenor(qte);
		if(produtoDtos == null) {
			return ResponseEntity.notFound().build();
		}
		if(produtoDtos.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produtoDtos);
	}
	
	@Operation(summary = "Pesquisa de uma lista contendo os dados de Produto e sua quantidade em estoque com o preço de varejo")
	@GetMapping("/produtoAndQteVarejo") //http://localhost:8081/api/produtos/produtoAndQteVarejo 
	public ResponseEntity<List<ProdutoSimplesDTO>> findProdutoAndQuantidadeVarejo(){
		List<ProdutoSimplesDTO> produtoDtos = produtoService.findProdutosPrecoVarejo();
		if(produtoDtos == null) {
			return ResponseEntity.notFound().build();
		}
		if(produtoDtos.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produtoDtos);
	}
	
	
}
