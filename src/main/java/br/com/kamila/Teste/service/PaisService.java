package br.com.kamila.Teste.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kamila.Teste.model.Pais;
import br.com.kamila.Teste.repository.PaisRepository;

@Service
public class PaisService {

	@Autowired
	private PaisRepository paisRepository;
	
	public Pais save(Pais pais) {
		return paisRepository.save(pais);
	}

	public List<Pais> findAll() {
		return paisRepository.findAll();
	}

}
