package com.residencia.academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.academia.entity.Atividade;
import com.residencia.academia.exception.AtividadeNotFoundException;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.AtividadeService;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {
	@Autowired
	private AtividadeService atividadeService;

	@GetMapping
	public ResponseEntity<List<Atividade>> findAllAtividade() {
		return new ResponseEntity<>(atividadeService.findAllAtividade(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Atividade> findAtividadeById(@PathVariable Integer id) {
		Atividade atividade = atividadeService.findAtividadeById(id);
		if (null == atividade)
			throw new NoSuchElementFoundException("Não foi encontrada a Atividade com o ID: " + id);
		else
			return new ResponseEntity<>(atividadeService.findAtividadeById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Atividade> saveAtividade(@RequestBody Atividade atividade) {
		return new ResponseEntity<>(atividadeService.saveAtividade(atividade), HttpStatus.CREATED);
	}

	/*@PutMapping
	public ResponseEntity<Atividade> updateAtividade(@RequestBody Atividade atividade) {
		return new ResponseEntity<>(atividadeService.updateAtividade(atividade), HttpStatus.OK);
	}*/
	@PutMapping
    public ResponseEntity<Atividade> update(@RequestBody Atividade atividade) {
    	Atividade atividadeFound = atividadeService.findAtividadeById(atividade.getIdAtividade());
    	if (atividadeFound == null) {
    		throw new NoSuchElementFoundException("Não foi encontrado o Atividade com o id " + atividade.getIdAtividade());
        }
        else {
        	Atividade novoAtividade = atividadeService.updateAtividade(atividade);
    		return new ResponseEntity<>(novoAtividade, HttpStatus.OK);
        }     	
    }

	@DeleteMapping
	public ResponseEntity<String> deleteAtividadeComConferencia(@PathVariable Integer id) {
		Atividade atividade = atividadeService.findAtividadeById(id);
		if (null == atividade)
			throw new NoSuchElementFoundException(
					"Não foi possivel excluir a Atividade, " + "pois não foi " + "encontrada uma Atividade com o id: " + id);
		atividadeService.deleteAtividade(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	@ExceptionHandler(AtividadeNotFoundException.class)
	public ResponseEntity<String> handleNoInstrutorFoundException(AtividadeNotFoundException exception) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(exception.getErrMsg());
	}
}
