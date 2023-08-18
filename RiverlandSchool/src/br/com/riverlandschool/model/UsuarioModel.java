
package br.com.riverlandschool.model;

public class UsuarioModel {

    private  String nome;
    private  String email;
    private  String senha;
    private  String tipoUsuario;
    private  String ra;
    private  String cpf;
    private  String disciplina;
    private  String serie;

    public UsuarioModel(String nome, String email, String senha, String tipoUsuario, String ra, String cpf, String disciplina, String serie) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.ra = ra;
        this.cpf = cpf;
        this.disciplina = disciplina;
        this.serie = serie;
    }
    
    //Contrutor GRID
//    public UsuarioModel(String nome, String email) {
//        this.nome = nome;
//        this.email = email;
//    }
    
	public UsuarioModel() {
    	
    }

 
    public  String getNome() {
        return nome;
    }

	public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }
    
    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }
    


}
