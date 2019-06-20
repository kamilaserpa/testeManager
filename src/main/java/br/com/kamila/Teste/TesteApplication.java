package br.com.kamila.Teste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.kamila.Teste.model.Pais;
import br.com.kamila.Teste.model.Usuario;
import br.com.kamila.Teste.service.PaisService;
import br.com.kamila.Teste.service.UsuarioService;


@SpringBootApplication
public class TesteApplication {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PaisService paisService;
	
	public static void main(String[] args) {
		SpringApplication.run(TesteApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner () {
		return args -> {			
			usuarioService.save(new Usuario("convidado", "manager", "Usuario convidado", false));
			usuarioService.save(new Usuario("admin", "suporte", "Gestor", true));
			paisService.save(new Pais("Brasil", "BR", "Brasileiro"));
			paisService.save(new Pais("Argentina", "AR", "Argentino"));
			paisService.save(new Pais("Alemmanha", "AL", "Alemão"));
		};
	}

}
