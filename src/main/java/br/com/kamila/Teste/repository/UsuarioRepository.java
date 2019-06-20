package br.com.kamila.Teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import br.com.kamila.Teste.model.Usuario;

@Component
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT u FROM Usuario u WHERE u.login = :login")
	Usuario getByLogin(@Param("login") String login);

}
