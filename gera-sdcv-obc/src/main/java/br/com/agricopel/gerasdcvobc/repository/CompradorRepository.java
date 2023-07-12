package br.com.agricopel.gerasdcvobc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.com.agricopel.gerasdcvobc.model.Comprador;

public interface CompradorRepository extends Repository<Comprador, Long> {

	public List<Comprador> findAll();

	public Optional<Comprador> findById(Integer id);
}