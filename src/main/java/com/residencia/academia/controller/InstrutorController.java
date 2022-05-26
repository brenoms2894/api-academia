package com.residencia.academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.academia.dto.InstrutorDTO;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.InstrutorService;

@RestController
@RequestMapping("/instrutor")
public class InstrutorController {
	@Autowired
	InstrutorService instrutorService;

	@GetMapping
	public ResponseEntity<List<Instrutor>> findAllInstrutor() {
		List<Instrutor> instrutorList = instrutorService.findAllInstrutor();
		return new ResponseEntity<>(instrutorList, HttpStatus.OK);
	}

	@GetMapping("/dto/{id}")
	public ResponseEntity<InstrutorDTO> findInstrutorDTOById(@PathVariable Integer id) {
		// return new ResponseEntity<>(instrutorService.findInstrutorById(id),
		// HttpStatus.OK);
		InstrutorDTO instrutorDTO = instrutorService.findInstrutorDTOById(id);
		if (null == instrutorDTO) {
			throw new NoSuchElementFoundException(
					"Não foi encontrado o Instrutor com o id " + id);
		} else {
			return new ResponseEntity<>(instrutorDTO, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Instrutor> findInstrutorById(@PathVariable Integer id) {
		// return new ResponseEntity<>(instrutorService.findInstrutorById(id),
		// HttpStatus.OK);
		Instrutor instrutor = instrutorService.findInstrutorById(id);
		if (null == instrutor) {
			throw new NoSuchElementFoundException(
					"Não foi encontrado o Instrutor com o id " + id);
		} else {
			return new ResponseEntity<>(instrutor, HttpStatus.OK);
		}
	}

	@PostMapping
	public ResponseEntity<Instrutor> saveInstrutor(@RequestBody Instrutor instrutor) {
		Instrutor novoInstrutor = instrutorService.saveInstrutor(instrutor);
		return new ResponseEntity<>(novoInstrutor, HttpStatus.CREATED);
	}

	@PostMapping("/dto")
	public ResponseEntity<Instrutor> saveInstrutorDTO(@RequestBody InstrutorDTO instrutorDTO) {
		Instrutor novoInstrutor = instrutorService.saveDTO(instrutorDTO);
		return new ResponseEntity<>(novoInstrutor, HttpStatus.CREATED);
	}

	/*
	 * @PutMapping public ResponseEntity<Instrutor> updateInstrutor(@RequestBody
	 * Instrutor instrutor) { Instrutor novoInstrutor =
	 * instrutorService.updateInstrutor(instrutor); return new
	 * ResponseEntity<>(novoInstrutor, HttpStatus.OK); }
	 */
	@PutMapping
	public ResponseEntity<Instrutor> update(@RequestBody Instrutor instrutor) {
		Instrutor instrutorFound = instrutorService.findInstrutorById(instrutor.getIdInstrutor());
		if (instrutorFound == null) {
			throw new NoSuchElementFoundException(
					"Não foi encontrado o Instrutor com o id " + instrutor.getIdInstrutor());
		} else {
			Instrutor novoInstrutor = instrutorService.updateInstrutor(instrutor);
			return new ResponseEntity<>(novoInstrutor, HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInstrutor(@PathVariable Integer id) {
		instrutorService.deleteInstrutor(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}