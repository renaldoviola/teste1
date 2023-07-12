package br.com.agricopel.gerasdcvobc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.com.agricopel.gerasdcvobc.model.Filial;

public interface FilialRepository extends Repository<Filial, Long> {

	public List<Filial> findAll();

	public Optional<Filial> findById(Integer id);

}
