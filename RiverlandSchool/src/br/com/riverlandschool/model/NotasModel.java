package br.com.riverlandschool.model;

import br.com.riverlandschool.view.FormCadastroNotas;

public class NotasModel {
	
    private  String nome;
    private  String ra;
    private  Double notaBimestre1;
    private  Double notaBimestre2;
    private  Double notaBimestre3;
    private  Double notaBimestre4;
    
    
    public NotasModel(String nome, String ra, Double notaBimestre1, Double notaBimestre2, Double notaBimestre3, Double notaBimestre4) {
        this.nome = nome;
        this.ra = ra;
        this.notaBimestre1 = notaBimestre1;
        this.notaBimestre2 = notaBimestre2;
        this.notaBimestre3 = notaBimestre3;
        this.notaBimestre4 = notaBimestre4;

    }
    

	public NotasModel() {
    	
    }


	public  String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public  Double getNotaBimestre1() {
		return notaBimestre1;
	}

	public  void setNotaBimestre1(Double notaBimestre1) {
		this.notaBimestre1 = notaBimestre1;
	}

	public  Double getNotaBimestre2() {
		return notaBimestre2;
	}

	public  void setNotaBimestre2(Double notaBimestre2) {
		this.notaBimestre2 = notaBimestre2;
	}

	public  Double getNotaBimestre3() {
		return notaBimestre3;
	}

	public  void setNotaBimestre3(Double notaBimestre3) {
		this.notaBimestre3 = notaBimestre3;
	}

	public  Double getNotaBimestre4() {
		return notaBimestre4;
	}

	public  void setNotaBimestre4(Double notaBimestre4) {
		this.notaBimestre4 = notaBimestre4;
	}
}
