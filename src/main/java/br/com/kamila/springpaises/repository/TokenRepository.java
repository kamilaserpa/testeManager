package br.com.kamila.springpaises.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.kamila.springpaises.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
	
	@Query("SELECT t FROM Token t WHERE t.cdToken = :cdToken")
	public Token getByCdToken(@Param("cdToken") String cdToken);

}
