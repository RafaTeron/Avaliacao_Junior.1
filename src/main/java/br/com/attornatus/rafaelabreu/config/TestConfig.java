package br.com.attornatus.rafaelabreu.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.attornatus.rafaelabreu.entities.Pessoa;
import br.com.attornatus.rafaelabreu.repositories.PessoaRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Pessoa p1 = new Pessoa(null,"Rafael",sdf.parse("15/01/1996"));
		Pessoa p2 = new Pessoa(null,"Heitor",sdf.parse("19/04/2023"));
		Pessoa p3 = new Pessoa(null,"Thayna",sdf.parse("02/05/1996"));
		
		pessoaRepository.saveAll(Arrays.asList(p1,p2,p3));
		
	}

}
