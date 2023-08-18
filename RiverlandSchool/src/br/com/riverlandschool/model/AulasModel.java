package br.com.riverlandschool.model;

import br.com.riverlandschool.view.FormCadastroAulas;
import br.com.riverlandschool.view.FormCadastroNotas;

public class AulasModel {
	
	  private String professor;
	  private String disciplina;
	  private String dataAula;
	  private String conteudoAula;
	  

	public AulasModel(String professor, String disciplina, String dataAula, String conteudoAula) {
		super();
		this.professor = professor;
		this.disciplina = disciplina;
		this.dataAula = dataAula;
		this.conteudoAula = conteudoAula;
	}

	public AulasModel() {
		super();
	}


	public String getProfessor() {
		return professor;
	}


	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getDisciplina() {
		return disciplina;
	}


	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}


	public String getDataAula() {
		return dataAula;
	}


	public void setDataAula(String dataAula) {
		this.dataAula = dataAula;
	}


	public String getConteudoAula() {
		return conteudoAula;
	}


	public void setConteudoAula(String conteudoAula) {
		this.conteudoAula = conteudoAula;
	}

	  
	
	  
}
