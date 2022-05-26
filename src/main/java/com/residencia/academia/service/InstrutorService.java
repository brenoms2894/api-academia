package com.residencia.academia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.dto.InstrutorDTO;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.InstrutorRepository;

@Service
public class InstrutorService {

	@Autowired
	InstrutorRepository instrutorRepository;

	public List<Instrutor> findAllInstrutor() {
		return instrutorRepository.findAll();
	}

	public Instrutor findInstrutorById(Integer id) {
		
		 return instrutorRepository.findById(id).isPresent() ?
		 instrutorRepository.findById(id).get() : null;
		 
	}

	public InstrutorDTO findInstrutorDTOById(Integer id) {
		Instrutor instrutor = instrutorRepository.findById(id).isPresent() ? instrutorRepository.findById(id).get()
				: null;
		InstrutorDTO instrutorDTO = new InstrutorDTO();
		if (null != instrutor) {
			instrutorDTO = converterInstrutorParaDTO(instrutor);
		}
		return instrutorDTO;
	}

	public InstrutorDTO converterInstrutorParaDTO(Instrutor instrutor) {
		InstrutorDTO instrutorDTO = new InstrutorDTO();
		instrutorDTO.setDataNascimento(instrutor.getDataNascimento());
		instrutorDTO.setIdInstrutor(instrutor.getIdInstrutor());
		instrutorDTO.setNomeInstrutor(instrutor.getNomeInstrutor());
		instrutorDTO.setRg(instrutor.getRg());
		instrutorDTO.setTitulacaoInstrutor(instrutor.getTitulacaoInstrutor());

		List<Integer> listaIdTurmasDTO = new ArrayList<>();

		for (Turma turma : instrutor.getTurmaList()) {
			listaIdTurmasDTO.add(turma.getIdTurma());
		}

		instrutorDTO.setTurmaDTOList(listaIdTurmasDTO);

		return instrutorDTO;
	}

	public Instrutor converterDTOParaInstrutor(InstrutorDTO instrutorDTO) {
		Instrutor instrutor = new Instrutor();
		instrutor.setDataNascimento(instrutorDTO.getDataNascimento());
		instrutor.setIdInstrutor(instrutorDTO.getIdInstrutor());
		instrutor.setNomeInstrutor(instrutorDTO.getNomeInstrutor());
		instrutor.setRg(instrutorDTO.getRg());
		instrutor.setTitulacaoInstrutor(instrutorDTO.getTitulacaoInstrutor());

		return instrutor;
	}

	/*
	 * public Instrutor findInstrutorDTOById(Integer id) { return
	 * instrutorRepository.findById(id).isPresent() ?
	 * instrutorRepository.findById(id).get() : null; }
	 */

	public Instrutor saveInstrutor(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}

	public Instrutor saveDTO(InstrutorDTO instrutorDTO) {
		Instrutor instrutor = converterDTOParaInstrutor(instrutorDTO);
		return instrutorRepository.save(instrutor);
	}

	public Instrutor updateInstrutor(Instrutor instrutor) {
		return instrutorRepository.save(instrutor);
	}

	public void deleteInstrutor(Integer id) {
		Instrutor inst = instrutorRepository.findById(id).get();
		instrutorRepository.delete(inst);
	}

	public Instrutor saveInstrutorDTO(InstrutorDTO instrutorDTO) {
		Instrutor instrutor = converterDTOParaInstrutor(instrutorDTO);
		return instrutorRepository.save(instrutor);
	}

}
