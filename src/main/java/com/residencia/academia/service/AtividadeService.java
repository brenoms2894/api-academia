package com.residencia.academia.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.entity.Atividade;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.AtividadeRepository;

@Service
public class AtividadeService {
	@Autowired
	AtividadeRepository atividadeRepository;
	
	public List<Atividade> findAllAtividade(){
		return atividadeRepository.findAll();
	}
	
	public Atividade findAtividadeById(Integer id) {
		return atividadeRepository.findById(id).isPresent() ?
			atividadeRepository.findById(id).get() :
				null;
	}
	
	public Atividade saveAtividade(Atividade atividade) {
		return atividadeRepository.save(atividade);
	}
	
	public Atividade updateAtividade(Atividade atividade) {
		return atividadeRepository.save(atividade);
	}
	
	public boolean deleteatividadeComConferencia(Integer id) {
		if(atividadeRepository.findById(id).isPresent()) {
			atividadeRepository.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

	public void deleteAtividade(Integer id) {
		atividadeRepository.deleteById(id);
	}
	
	
}

