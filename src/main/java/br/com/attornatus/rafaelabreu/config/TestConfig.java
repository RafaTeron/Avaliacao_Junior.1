package br.com.attornatus.rafaelabreu.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.attornatus.rafaelabreu.entities.Endereco;
import br.com.attornatus.rafaelabreu.entities.Pessoa;
import br.com.attornatus.rafaelabreu.entities.enums.TipoEndereco;
import br.com.attornatus.rafaelabreu.repositories.EnderecoRepository;
import br.com.attornatus.rafaelabreu.repositories.PessoaRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public void run(String... args) throws Exception {
		
	    
		Endereco end1 = new Endereco(null,"Av Padre rose", "99999-99", 99, "Rio de Janeiro", TipoEndereco.PRINCIPAL, null);
		Endereco end2 = new Endereco(null,"Av brasil", "99999-99", 99, "Rio de Janeiro", TipoEndereco.SECUNDARIO, null);
		Endereco end3 = new Endereco(null,"Rua Passos", "99999-99", 99, "Rio de Janeiro", TipoEndereco.PRINCIPAL, null);
		Endereco end4 = new Endereco(null,"Rua Paraguai", "99999-99", 99, "Rio de Janeiro", TipoEndereco.PRINCIPAL, null);
		Endereco end5 = new Endereco(null,"Av Rio Branco", "99999-99", 99, "Rio de Janeiro", TipoEndereco.SECUNDARIO , null);
		
		Pessoa p1 = new Pessoa(null,"Rafael",sdf.parse("15/01/1996"));
		Pessoa p2 = new Pessoa(null,"Heitor",sdf.parse("19/04/2023"));
		Pessoa p3 = new Pessoa(null,"Thayna",sdf.parse("02/05/1996"));
		
		enderecoRepository.saveAll(Arrays.asList(end1,end2,end3,end4,end5));
		pessoaRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		end1.setPessoa(p1);
		end2.setPessoa(p1);
		end3.setPessoa(p2);
		end4.setPessoa(p3);
		end5.setPessoa(p3);
		
		enderecoRepository.saveAll(Arrays.asList(end1,end2,end3,end4,end5));
	}

}
