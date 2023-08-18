package br.com.riverlandschool.dao;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.riverlandschool.model.UsuarioModel;
import br.com.riverlandschool.view.FormCadastroUsuario;
import br.com.riverlandschool.view.FormLoginEscola;

public class UsuarioDao {

	public static boolean incluirUsuario(UsuarioModel usuario) throws SQLException {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
			con.setAutoCommit(false);
			String sql = "INSERT INTO USUARIOS (NOME, EMAIL, SENHA, TIPO_USUARIO, RA, CPF, DISCIPLINA, SERIE)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getSenha());
			ps.setString(4, usuario.getTipoUsuario());
			ps.setString(5, usuario.getRa());
			ps.setString(6, usuario.getCpf());
			ps.setString(7, usuario.getDisciplina());
			ps.setString(8, usuario.getSerie());
			ps.execute();
			ps.close();
			con.commit();
			con.close();
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<UsuarioModel> pesquisarUsuario(String selectedValue, String selectedRdb) throws Exception {
	    ArrayList<UsuarioModel> listaUsuarios = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        Class.forName("org.sqlite.JDBC");
	        con = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
	        con.setAutoCommit(false);
	        System.out.println("Opened database successfully");

	        StringBuilder queryBuilder = new StringBuilder("SELECT NOME, EMAIL, SENHA, TIPO_USUARIO, RA, CPF, DISCIPLINA, SERIE FROM USUARIOS WHERE TIPO_USUARIO = ?");

	        String selectedRadioButton = null;

	        if (FormCadastroUsuario.rdbtnNome.isSelected()) {
	            selectedRadioButton = "Nome";
	        } else if (FormCadastroUsuario.rdbtnRa.isSelected()) {
	            selectedRadioButton = "RA";
	        } else if (FormCadastroUsuario.rdbtnCpf.isSelected()) {
	            selectedRadioButton = "CPF";
	        } else if (FormCadastroUsuario.rdbtnDisciplina.isSelected()) {
	            selectedRadioButton = "Disciplina";
	        }

	        if (selectedValue.equals("Aluno")) {
	            if (selectedRadioButton != null && selectedRadioButton.equals("Nome")) {
	                queryBuilder.append(" AND NOME = ?");
	            } else if (selectedRadioButton != null && selectedRadioButton.equals("RA")) {
	                queryBuilder.append(" AND RA = ?");
	            }
	        } else if (selectedValue.equals("Coordenador")) {
	            if (selectedRadioButton != null && selectedRadioButton.equals("Nome")) {
	                queryBuilder.append(" AND NOME = ?");
	            } else if (selectedRadioButton != null && selectedRadioButton.equals("CPF")) {
	                queryBuilder.append(" AND CPF = ?");
	            }
	        } else if (selectedValue.equals("Professor")) {
	            if (selectedRadioButton != null && selectedRadioButton.equals("Nome")) {
	                queryBuilder.append(" AND NOME = ?");
	            } else if (selectedRadioButton != null && selectedRadioButton.equals("CPF")) {
	                queryBuilder.append(" AND CPF = ?");
	            } else if (selectedRadioButton != null && selectedRadioButton.equals("Disciplina")) {
	                queryBuilder.append(" AND DISCIPLINA = ?");
	            }
	        }

	        ps = con.prepareStatement(queryBuilder.toString());
	        ps.setString(1, selectedValue);

	        UsuarioModel usuario = new UsuarioModel();
	        
	        if (selectedRadioButton != null) {
	            ps.setString(2, "Felipe");
	        }

	        rs = ps.executeQuery();

	        while (rs.next()) {
	            UsuarioModel obj = new UsuarioModel(
	                    rs.getString("NOME"),
	                    rs.getString("EMAIL"),
	                    rs.getString("SENHA"),
	                    rs.getString("TIPO_USUARIO"),
	                    rs.getString("RA"),
	                    rs.getString("CPF"),
	                    rs.getString("DISCIPLINA"),
	                    rs.getString("SERIE"));
	            listaUsuarios.add(obj);
	        }

	        return listaUsuarios;

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(new JFrame(), "Ocorreu um erro ao pesquisar o registro.", "Erro", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        if (rs != null) {
	            rs.close();
	        }
	        if (ps != null) {
	            ps.close();
	        }
	        if (con != null) {
	            con.close();
	        }
	    }
	    return null;
	}


	
	public ArrayList<UsuarioModel> getUsuario(String selectedValue) throws Exception{
		ArrayList<UsuarioModel> listaUsuarios = new ArrayList<>();
		Connection con = null;
		java.sql.Statement ps = null;
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
		con.setAutoCommit(false);
		System.out.println("Opened database successfully");
		ps = con.createStatement();
		String Query = null;
		
		Query = ("SELECT NOME, EMAIL, SENHA, TIPO_USUARIO, RA, CPF, DISCIPLINA, SERIE FROM USUARIOS WHERE TIPO_USUARIO = " + "'" + FormCadastroUsuario.comboBoxPesquisar.getSelectedItem() + "'" + " ;");
		
		ResultSet rs = ps.executeQuery(Query);
		
		while(rs.next()) {			
			UsuarioModel obj = new UsuarioModel(
					rs.getString("NOME"), 
					rs.getString("EMAIL"),
					rs.getString("SENHA"), 
					rs.getString("TIPO_USUARIO"),
					rs.getString("RA"), 
					rs.getString("CPF"),
					rs.getString("DISCIPLINA"),
					rs.getString("SERIE"));
			listaUsuarios.add(obj);
		}
        rs.close();
        ps.close();
        con.close();

		return listaUsuarios;
    }
	
	public static boolean alterarUsuario(UsuarioModel usuario) {
	    Connection con = null;
	    try {
	        Class.forName("org.sqlite.JDBC");
	        con = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
	        con.setAutoCommit(false);
	        
	        String sql = "UPDATE USUARIOS SET NOME = ?, EMAIL = ?, SENHA = ?, TIPO_USUARIO = ?, RA = ?, CPF = ?, DISCIPLINA = ?, SERIE = ?";
	        String campoIdentificacao = null;
	        
	        if (usuario.getTipoUsuario().equals("Aluno")) {
	            campoIdentificacao = "RA";
	        } else if (usuario.getTipoUsuario().equals("Professor")) {
	            campoIdentificacao = "CPF";
	        } else if (usuario.getTipoUsuario().equals("Coordenador")) {
	            campoIdentificacao = "CPF";
	        }
	        
	        sql += " WHERE " + campoIdentificacao + " = ?";
	        
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, usuario.getNome());
	        ps.setString(2, usuario.getEmail());
	        ps.setString(3, usuario.getSenha());
	        ps.setString(4, usuario.getTipoUsuario());
	        ps.setString(5, usuario.getRa());
	        ps.setString(6, usuario.getCpf());
	        ps.setString(7, usuario.getDisciplina());
	        ps.setString(8, usuario.getSerie());
	        ps.setString(9, campoIdentificacao.equals("RA") ? usuario.getRa() : usuario.getCpf());
	        
	        int affectedRows = ps.executeUpdate();
	        if (affectedRows > 0) {
	            JOptionPane.showMessageDialog(new JFrame(), "Registro alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(new JFrame(), "Nenhum registro encontrado para alteração.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	        }

	        ps.close();
	        con.commit();
	        con.close();
	        return true;
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(new JFrame(), "Ocorreu um erro ao alterar o registro.", "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	    return false;
	}

	
	public static boolean excluirUsuario() {
	    int response = JOptionPane.showConfirmDialog(null, "Deseja Realmente Deletar o Registro?", "Confirmar",
	            JOptionPane.YES_NO_OPTION);

	    if (response == JOptionPane.NO_OPTION) {
	        JOptionPane.getDefaultLocale();
	    } else if (response == JOptionPane.YES_OPTION) {
	        Connection con = null;
	        Statement ps = null;
	        try {
	            Class.forName("org.sqlite.JDBC");
	            con = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
	            con.setAutoCommit(false);
	            ps = con.createStatement();
	            String sql = null;

	            if (FormCadastroUsuario.comboBoxTipoUsuario.getSelectedItem().equals("Aluno")) {
	                sql = "DELETE from USUARIOS WHERE RA='" + FormCadastroUsuario.txtRa.getText() + "';";
	            } else if (FormCadastroUsuario.comboBoxTipoUsuario.getSelectedItem().equals("Professor")
	                    || FormCadastroUsuario.comboBoxTipoUsuario.getSelectedItem().equals("Coordenador")) {
	                sql = "DELETE from USUARIOS WHERE CPF='" + FormCadastroUsuario.txtCpf.getText() + "';";
	            }

	            ps.executeUpdate(sql);
	            con.commit();
	            ps.close();
	            con.close();
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(new JFrame(), "Erro ao excluir: " + e.getMessage(), "Erro",
	                    JOptionPane.ERROR_MESSAGE);
	        }
	    }
	    System.out.println("Registro Deletado com Sucesso!");
	    return false;
	}
	
	public ArrayList<UsuarioModel> getUsuarioPerfil(UsuarioModel obj2) throws Exception{
		ArrayList<UsuarioModel> listaUsuarios = new ArrayList<>();
		Connection con = null;
		java.sql.Statement ps = null;
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
		con.setAutoCommit(false);
		System.out.println("Opened database successfully");
		ps = con.createStatement();
		String Query = null;
		
		String cpf = FormLoginEscola.getCpf();
		String ra = FormLoginEscola.getRaAluno();

		if(cpf != null) {
		Query = ("SELECT NOME, EMAIL, SENHA, TIPO_USUARIO, RA, CPF, DISCIPLINA, SERIE FROM USUARIOS WHERE CPF = " + "'" + cpf + "'");
		}
		
		if(ra != null) {
		Query = ("SELECT NOME, EMAIL, SENHA, TIPO_USUARIO, RA, CPF, DISCIPLINA, SERIE FROM USUARIOS WHERE RA = " + "'" + ra + "'");
		}
		ResultSet rs = ps.executeQuery(Query);
		
		while(rs.next()) {			
			UsuarioModel obj = new UsuarioModel(
					rs.getString("NOME"), 
					rs.getString("EMAIL"),
					rs.getString("SENHA"), 
					rs.getString("TIPO_USUARIO"),
					rs.getString("RA"), 
					rs.getString("CPF"),
					rs.getString("DISCIPLINA"),
					rs.getString("SERIE"));
			listaUsuarios.add(obj);
		}
        rs.close();
        ps.close();
        con.close();

		return listaUsuarios;
    }

}
