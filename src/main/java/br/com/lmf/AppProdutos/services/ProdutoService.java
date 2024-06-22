package br.com.lmf.AppProdutos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lmf.AppProdutos.dto.ProdutoDTO;
import br.com.lmf.AppProdutos.dto.ProdutoSimplesDTO;
import br.com.lmf.AppProdutos.entities.Produto;
import br.com.lmf.AppProdutos.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto save(Produto produto) {
		
		if(produto.getNome() == null) {
			System.out.println("Nome do produto vazio");
		}
		if(produto.getCodigoBarras() == null) {
			System.out.println("Códgo de barras do produto vazio");
		}
		
		return produtoRepository.save(produto);
	}
	
	public List<Produto> findAll(){
		return produtoRepository.findAll();
	}
	
	public Optional<Produto> findById(Long id) {
		
		return produtoRepository.findById(id);
	}
	
	public Produto update(Produto produto) {
		Optional<Produto> findByProduto = produtoRepository.findById(produto.getId());
		
		if(findByProduto.isPresent()) {
			Produto uptProduto = findByProduto.get();
			uptProduto.setCodigoBarras(produto.getCodigoBarras());
			uptProduto.setNome(produto.getNome());
			uptProduto.setPreco(produto.getPreco());
			
			return produtoRepository.save(uptProduto);
		}
		
		return produtoRepository.save(produto);
		
	}
	
	public void delete(Long id) {
		produtoRepository.deleteById(id);
	}
	
	/**
	 * Consulta em banco de dados, onde retorna o Produto com sua respectiva 
	 * quantidade que seja <span>menor</menor> que o parametro passado
	 * @param resultado objeto do BD
	 * @return objeto ProdutoDTO
	 */
	
	public List<ProdutoDTO> findProdutoAndQuantidade(){
		
		List<Object[]> listResult = produtoRepository.findProdutoAndQuantidade();
		List<ProdutoDTO> listProdutoDTO = new ArrayList<ProdutoDTO>();
		
		for(Object[] o : listResult) {
			ProdutoDTO pDto = returnBDProdutoDTO(o);
			listProdutoDTO.add(pDto);
		}
		
		return listProdutoDTO;
	}
	
	
	public List<ProdutoDTO> findProdutoAndQuantidadeMenor(Integer qte){
		List<Object[]> listResult = produtoRepository.findProdutoAndQuantidadeMenor(qte);
		List<ProdutoDTO> listProdutoDTO = new ArrayList<ProdutoDTO>();
		
		for(Object[] o : listResult) {
			ProdutoDTO pDto = returnBDProdutoDTO(o);
			listProdutoDTO.add(pDto);
		}
		
		return listProdutoDTO;
	}
	
	public List<ProdutoSimplesDTO> findProdutosPrecoVarejo(){
		List<Object[]> listResult = produtoRepository.findProdutoAndQuantidade();
		List<ProdutoSimplesDTO> listProdutoSimplesDTO = new ArrayList<ProdutoSimplesDTO>();
		
		for(Object[] o : listResult) {
			ProdutoSimplesDTO pDTO = returnDBProdutoSimplesDTO(o);
			listProdutoSimplesDTO.add(pDTO);
		}
		
		return listProdutoSimplesDTO;
		
	}
	
	
	/**
	 * Conversão de objeto recebido do banco de dados para DTO de produtos
	 * @param resultado objeto do BD
	 * @return objeto ProdutoDTO
	 */
	
	private ProdutoDTO returnBDProdutoDTO(Object[] resultado) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		if(resultado != null) {
			produtoDTO.setId(	((Long)resultado[0]).longValue()	);
			produtoDTO.setCodigoBarras(	((String)resultado[1])	);
			produtoDTO.setNome(	((String)resultado[2])	);
			produtoDTO.setPrice(	((Double)resultado[3]).doubleValue()	);
			produtoDTO.setQuantidade(	((Integer)resultado[4]).intValue()	);
			
		}
		return produtoDTO;
	}
	
	private ProdutoSimplesDTO returnDBProdutoSimplesDTO(Object[] resultado) {
		if(resultado != null) {
			ProdutoSimplesDTO produtoSimplesDTO = new ProdutoSimplesDTO(
					((Long)resultado[0]).longValue(), 
					(String)resultado[1], 
					((Double)resultado[3]).doubleValue(), 
					(((Double)resultado[3]).doubleValue() * 1.02),
					((Integer)resultado[4]).intValue());
			return produtoSimplesDTO;
		}else {
			return null;
		}
	}

}
