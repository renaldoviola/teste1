package br.com.agricopel.gerasdcvobc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agricopel.gerasdcvobc.model.Produto;
import br.com.agricopel.gerasdcvobc.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Optional<Produto> findByCodigo(String codigo) {
		return produtoRepository.findByCodigo(codigo);
	}

}
