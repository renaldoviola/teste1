package br.com.agricopel.gerasdcvobc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agricopel.gerasdcvobc.model.Filial;
import br.com.agricopel.gerasdcvobc.repository.FilialRepository;

@Service
public class FilialService {

	@Autowired
	private FilialRepository filialRepository;

	public List<Filial> findAll() {
		return filialRepository.findAll();
	}

	public Optional<Filial> findById(Integer id) {
		return filialRepository.findById(id);
	}

}
