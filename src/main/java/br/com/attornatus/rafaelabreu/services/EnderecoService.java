package br.com.attornatus.rafaelabreu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attornatus.rafaelabreu.entities.Endereco;
import br.com.attornatus.rafaelabreu.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;
	
	public List<Endereco> findAll() {
		return repository.findAll();
	}
	
	public Endereco findById(Long id) {
		Optional<Endereco> obj =repository.findById(id);
		return obj.get();
	}
	
	public Endereco save(Endereco obj) {
	    return repository.save(obj);
	}
	
	public Endereco update(Long id, Endereco obj) {
	    Endereco entity = repository.getReferenceById(id);
	    updateData(entity, obj);
	    return repository.save(entity);
	}
	
	private void updateData(Endereco entity, Endereco obj) {
		entity.setLogradouro(obj.getLogradouro());
		entity.setCep(obj.getCep());
		entity.setNumero(obj.getNumero());
		entity.setCidade(obj.getCidade());
		entity.setTipo(obj.getTipo());
	}

	public void deleteById(Long id) {
	    repository.deleteById(id);
	}

}
