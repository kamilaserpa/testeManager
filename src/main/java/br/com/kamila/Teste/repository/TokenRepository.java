package br.com.kamila.Teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.kamila.Teste.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
	
	@Query("SELECT t FROM Token t WHERE t.token = :token")
	public Token getByToken(@Param("token") String token);

}
