package br.com.lmf.AppProdutos.services.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.lmf.AppProdutos.entities.Estoque;

public interface EstoqueServiceInterface {
	
	Estoque save(Estoque estoque);
		Optional<Estoque> findById(Long id);
		List<Estoque> findAll();
		Estoque update(Estoque estoque);
		void delete(Long id);

}
