package br.rodrigo.springboot.springvault;

import br.rodrigo.springboot.springvault.repository.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringVaultApplication implements CommandLineRunner {

	@Autowired
	private FooRepository fooRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringVaultApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fooRepository.findAll().forEach(foo->System.out.println(foo.getId()));
	}
}
