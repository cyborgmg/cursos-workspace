package com.bezkoder.spring.springpageable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringPageableApplication /*implements CommandLineRunner*/ {

	/*@Autowired
	private TabelaRepository tabelaRepository;*/

	public static void main(String[] args) {
		SpringApplication.run(SpringPageableApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		tabelaRepository.findAll().forEach(t-> System.out.println(t.toString()));
	}*/
}
