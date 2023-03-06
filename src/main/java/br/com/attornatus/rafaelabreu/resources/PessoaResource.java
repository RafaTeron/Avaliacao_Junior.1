package br.com.attornatus.rafaelabreu.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.attornatus.rafaelabreu.entities.Endereco;
import br.com.attornatus.rafaelabreu.entities.Pessoa;
import br.com.attornatus.rafaelabreu.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {
	@Autowired
	private PessoaService service;

	@GetMapping
	public ResponseEntity<List<Pessoa>> findAll() {
		List<Pessoa> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
		Pessoa obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<Pessoa> insert(@RequestBody Pessoa pessoa) {
		Pessoa obj = service.save(pessoa);
		return ResponseEntity.ok().body(obj);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(value = "/{id}/addEndereco")
	public ResponseEntity<Endereco> addEndereco(@PathVariable Long id, @RequestBody Endereco endereco){
		Endereco obj = service.addEndereco(endereco, id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value = "/{id}/updateEndereco")
	public ResponseEntity<Endereco> updateEndereco(@PathVariable Long id, @RequestBody Endereco endereco) throws Exception{
		Endereco obj = service.updateEndereco(endereco, id);
		return ResponseEntity.ok().body(obj);
	}

}
