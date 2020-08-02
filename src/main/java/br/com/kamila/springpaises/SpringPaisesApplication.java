package br.com.kamila.springpaises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.kamila.springpaises.model.Pais;
import br.com.kamila.springpaises.model.Usuario;
import br.com.kamila.springpaises.service.PaisService;
import br.com.kamila.springpaises.service.UsuarioService;


@SpringBootApplication
public class SpringPaisesApplication {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PaisService paisService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringPaisesApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner () {
		return args -> {
			if(usuarioService.findAll().size() == (int)0) {
				usuarioService.save(new Usuario("convidado", "manager", "Usuario convidado", false));
				usuarioService.save(new Usuario("admin", "suporte", "Gestor", true));
				paisService.save(new Pais("Brasil", "BR", "Brasileiro"));
				paisService.save(new Pais("Argentina", "AR", "Argentino"));
				paisService.save(new Pais("Alemanha", "AL", "Alemão"));
			}
			
		};
	}

}
