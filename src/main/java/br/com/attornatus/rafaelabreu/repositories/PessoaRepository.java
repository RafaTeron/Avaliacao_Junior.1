package br.com.attornatus.rafaelabreu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.attornatus.rafaelabreu.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>  {

}
