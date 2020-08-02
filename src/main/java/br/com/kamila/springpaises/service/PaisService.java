package br.com.kamila.springpaises.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kamila.springpaises.model.Pais;
import br.com.kamila.springpaises.repository.PaisRepository;

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

	public List<Pais> pesquisarNome(String nome) {
		return paisRepository.pesquisarNome(nome);
	}

	public Pais getOne(Long id) {
		return paisRepository.getOne(id);
	}

	public boolean delete(Long id) {
		try {
			paisRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
