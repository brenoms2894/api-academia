package com.residencia.academia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;

	public List<Turma> findAllTurma() {
		return turmaRepository.findAll();
	}

	public Turma findTurmaById(Integer id) {
		return turmaRepository.findById(id).isPresent() ? turmaRepository.findById(id).get() : null;
	}

	public TurmaDTO findTurmaDTOById(Integer id) {
		Turma turma = turmaRepository.findById(id).isPresent() ? turmaRepository.findById(id).get() : null;
		TurmaDTO turmaDTO = new TurmaDTO();
		if (null != turma) {
			turmaDTO = converterTurmaParaDTO(turma);
		}
		return turmaDTO;
	}

	public TurmaDTO converterTurmaParaDTO(Turma turma) {
		TurmaDTO turmaDTO = new TurmaDTO();
		turmaDTO.setIdTurma(turma.getIdTurma());
		turmaDTO.setHorarioTurma(turma.getHorarioTurma());
		turmaDTO.setDuracaoTurma(turma.getDuracaoTurma());
		turmaDTO.setDataInicio(turma.getDataInicio());
		turmaDTO.setDataFim(turma.getDataFim());
		turmaDTO.setInstrutor(turma.getInstrutor());
		turmaDTO.setAtividade(turma.getAtividade());

		return turmaDTO;

	}

	public Turma converterDTOParaTurma(TurmaDTO turmaDTO) {
		Turma turma = new Turma();
		turma.setIdTurma(turmaDTO.getIdTurma());
		turma.setHorarioTurma(turmaDTO.getHorarioTurma());
		turma.setDuracaoTurma(turmaDTO.getDuracaoTurma());
		turma.setDataInicio(turmaDTO.getDataInicio());
		turma.setDataFim(turmaDTO.getDataFim());
		turma.setInstrutor(turmaDTO.getInstrutor());
		turma.setAtividade(turmaDTO.getAtividade());

		return turma;
	}

	public Turma saveTurma(Turma turma) {
		return turmaRepository.save(turma);
	}
	
	public Turma saveTurmaDTO(TurmaDTO turmaDTO) {
		Turma turma = converterDTOParaTurma(turmaDTO);
		return turmaRepository.save(turma);
	}

	public Turma updateTurma(Turma turma) {
		return turmaRepository.save(turma);
	}

	/*public boolean deleteTurmaComConferencia(Integer id) {
		if (turmaRepository.findById(id).isPresent()) {
			turmaRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}*/

	public void deleteTurma(Integer id) {
		turmaRepository.deleteById(id);
	}

}
