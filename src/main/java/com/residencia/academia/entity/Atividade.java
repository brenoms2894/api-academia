package com.residencia.academia.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "atividade")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idAtividade")
public class Atividade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_atividade")
	private Integer idAtividade;

	@Column(name = "nome")
	private Date horarioAtividade;

	@ManyToOne
	@JsonBackReference
	// @JsonIgnore
	@JoinColumn(name = "id_turma", referencedColumnName = "id_turma")
	private Turma turma;

	public Integer getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(Integer idAtividade) {
		this.idAtividade = idAtividade;
	}

	public Date getHorarioAtividade() {
		return horarioAtividade;
	}

	public void setHorarioAtividade(Date horarioAtividade) {
		this.horarioAtividade = horarioAtividade;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}