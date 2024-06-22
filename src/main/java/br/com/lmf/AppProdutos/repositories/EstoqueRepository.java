package br.com.lmf.AppProdutos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lmf.AppProdutos.entities.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long>{

}
