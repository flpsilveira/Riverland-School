package br.com.riverlandschool.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import br.com.riverlandschool.model.AulasModel;
import br.com.riverlandschool.view.FormLoginEscola;


public class AulasDao {
	
	public static boolean incluirAula(AulasModel aulas) throws SQLException {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
			con.setAutoCommit(false);
			String sql = "INSERT INTO Aulas (Nome_Professor, Disciplina, Data_Aula, Conteudo_Aula) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aulas.getProfessor());
			ps.setString(2, aulas.getDisciplina());
			ps.setString(3, aulas.getDataAula());
			ps.setString(4, aulas.getConteudoAula());
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

	public static boolean alterarAula(AulasModel aulas) throws SQLException {
		String nomeProfessor = FormLoginEscola.getNomeProfessor();

		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
			con.setAutoCommit(false);
			String sql = "UPDATE Aulas SET Nome_Professor = ?, Disciplina = ?, Data_Aula = ?, Conteudo_Aula = ? WHERE Data_Aula = ? AND Nome_Professor = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aulas.getProfessor());
			ps.setString(2, aulas.getDisciplina());
			ps.setString(3, aulas.getDataAula());
			ps.setString(4, aulas.getConteudoAula());
			ps.setString(5, aulas.getDataAula());
			ps.setString(6, nomeProfessor);

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
	
	public static AulasModel pesquisarAula(AulasModel aulas) throws SQLException {
		String nomeProfessor = FormLoginEscola.getNomeProfessor();

		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
			con.setAutoCommit(false);
			String sql = "SELECT * FROM Aulas WHERE Data_Aula = ? AND Nome_Professor = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aulas.getDataAula());
			ps.setString(2, nomeProfessor);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				AulasModel aula = new AulasModel();
				aula.setProfessor(rs.getString("Nome_Professor"));
				aula.setDisciplina(rs.getString("Disciplina"));
				aula.setDataAula(rs.getString("Data_Aula"));
				aula.setConteudoAula(rs.getString("Conteudo_Aula"));
				rs.close();
				ps.close();
				con.close();
				return aula;
			}
			rs.close();
			ps.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean excluirAula(AulasModel aulas) throws SQLException {
		String nomeProfessor = FormLoginEscola.getNomeProfessor();

		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
			con.setAutoCommit(false);
			String sql = "DELETE FROM Aulas WHERE Data_Aula = ? AND Nome_Professor = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aulas.getDataAula());
			ps.setString(2, nomeProfessor);
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
			}
