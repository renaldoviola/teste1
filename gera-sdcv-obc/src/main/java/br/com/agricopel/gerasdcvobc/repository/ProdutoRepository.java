package br.com.agricopel.gerasdcvobc.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.com.agricopel.gerasdcvobc.model.Produto;

public interface ProdutoRepository extends Repository<Produto, Long> {

	public Optional<Produto> findByCodigo(String codigo);

}
