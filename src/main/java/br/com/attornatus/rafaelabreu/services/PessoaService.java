package br.com.attornatus.rafaelabreu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attornatus.rafaelabreu.entities.Endereco;
import br.com.attornatus.rafaelabreu.entities.Pessoa;
import br.com.attornatus.rafaelabreu.entities.enums.TipoEndereco;
import br.com.attornatus.rafaelabreu.repositories.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository repository;
	@Autowired
	private EnderecoService enderecoService;
	
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
	
	
	public Endereco addEndereco(Endereco endereco, Long idPessoa) {
		Optional<Pessoa> obj = repository.findById(idPessoa);
		endereco.setPessoa(obj.get());
		List<Endereco> enderecos =  obj.get().getEndereco();
		if(enderecos.isEmpty()) {
			endereco.setTipo(TipoEndereco.PRINCIPAL);
			obj.get().getEndereco().add(endereco);
			return enderecoService.save(endereco);			
		}
		if(endereco.getTipo() == TipoEndereco.PRINCIPAL) {
			for(Endereco objEndereco : obj.get().getEndereco()) {
				objEndereco.setTipo(TipoEndereco.SECUNDARIO);
				enderecoService.update(objEndereco.getId(), objEndereco);
			}
			obj.get().getEndereco().add(endereco);
			return enderecoService.save(endereco);
		}
		endereco.setTipo(TipoEndereco.SECUNDARIO);
	    return enderecoService.save(endereco);
	}
	
	public Endereco updateEndereco(Endereco endereco, Long idPessoa) throws Exception {
		Optional<Pessoa> obj = repository.findById(idPessoa);
		endereco.setPessoa(obj.get());
		
		boolean existe = false;
		for(Endereco objEndereco : obj.get().getEndereco()) {
			if(endereco.getId() == objEndereco.getId()){
				existe = true;
			}		
		}
		if(!existe) {
			throw new Exception("Endereço não existe");
		}
		
		if(endereco.getTipo() == TipoEndereco.SECUNDARIO) {
			return enderecoService.save(endereco);
		}
		
		for(Endereco objEndereco : obj.get().getEndereco()) {
			objEndereco.setTipo(TipoEndereco.SECUNDARIO);
			enderecoService.update(objEndereco.getId(), objEndereco);
		}
		obj.get().getEndereco().add(endereco);
		return enderecoService.save(endereco);
	}
}
