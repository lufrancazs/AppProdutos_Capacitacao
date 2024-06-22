package br.com.lmf.AppProdutos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lmf.AppProdutos.entities.Estoque;
import br.com.lmf.AppProdutos.entities.Produto;
import br.com.lmf.AppProdutos.repositories.EstoqueRepository;
import br.com.lmf.AppProdutos.repositories.ProdutoRepository;
import br.com.lmf.AppProdutos.services.interfaces.EstoqueServiceInterface;

@Service
public class EstoqueService implements EstoqueServiceInterface{
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Estoque save(Estoque estoque) {
		
		if(estoque.getProduto().getId() != null) {
			Optional<Produto> findProduto = produtoRepository.findById(estoque.getProduto().getId());
			
			if(!findProduto.isEmpty()) {
				estoque.setProduto(findProduto.get());
				return estoqueRepository.save(estoque);
			} else {
				System.out.println("Produto não encontrado ID: " + estoque.getProduto().getId());
				return null;
			}
			
		}else {
			System.out.println("Produto não encontrado!");
			return null;
		}
	}

	@Override
	public Optional<Estoque> findById(Long id) {
		
		return estoqueRepository.findById(id);
	}

	@Override
	public List<Estoque> findAll() {
		return estoqueRepository.findAll();
	}

	@Override
	public Estoque update(Estoque estoque) {
		
		Optional<Estoque> findEstoque = estoqueRepository.findById(estoque.getProduto().getId());
		
		if(findEstoque.isPresent()) {
			Estoque uptEstoque = findEstoque.get();
			uptEstoque.setQuantidade(estoque.getQuantidade());
			
			return estoqueRepository.save(uptEstoque);
		} else {
			return save(estoque);
		}
		
	}

	@Override
	public void delete(Long id) {
		estoqueRepository.deleteById(id);
	}

}
