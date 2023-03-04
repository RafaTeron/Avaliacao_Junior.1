package br.com.attornatus.rafaelabreu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attornatus.rafaelabreu.entities.Pessoa;
import br.com.attornatus.rafaelabreu.repositories.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository repository;
	
	public List<Pessoa> findAll() {
		return repository.findAll();
	}
	
	public Pessoa findById(Long id) {
		Optional<Pessoa> obj =repository.findById(id);
		return obj.get();
	}
	
	public Pessoa save(Pessoa pessoa) {
	    return repository.save(pessoa);
	}
	
	public Pessoa update(Long id, Pessoa obj) {
	    Pessoa entity = repository.getReferenceById(id);
	    updateData(entity, obj);
	    return repository.save(entity);
	}
	
	private void updateData(Pessoa entity, Pessoa obj) {
		entity.setNome(obj.getNome());
		entity.setDataNascimento(obj.getDataNascimento());		
	}

	public void deleteById(Long id) {
	    repository.deleteById(id);
	}

}
