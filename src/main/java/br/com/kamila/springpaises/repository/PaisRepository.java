package br.com.kamila.springpaises.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.kamila.springpaises.model.Pais;

public interface PaisRepository extends JpaRepository<Pais, Long> {

	@Query("SELECT p FROM Pais p WHERE p.nome LIKE CONCAT('%',:nome,'%')")
	List<Pais> pesquisarNome(@Param("nome") String nome);

}
