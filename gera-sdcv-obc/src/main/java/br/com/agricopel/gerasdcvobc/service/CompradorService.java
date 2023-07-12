package br.com.agricopel.gerasdcvobc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agricopel.gerasdcvobc.model.Comprador;
import br.com.agricopel.gerasdcvobc.repository.CompradorRepository;

@Service
public class CompradorService {

	@Autowired
	private CompradorRepository compradorRepository;

	public List<Comprador> findAll() {
		return compradorRepository.findAll();
	}

	public Comprador findById(Integer id) {
		Optional<Comprador> optional = compradorRepository.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		} else {
			Comprador comprador = new Comprador();

			comprador.setEmail("leandro@agricopel.com.br");
			comprador.setLoginObc("admin");
			
			return comprador;
		}
	}

}