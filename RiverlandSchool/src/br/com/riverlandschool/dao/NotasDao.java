package br.com.riverlandschool.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.riverlandschool.model.AulasModel;
import br.com.riverlandschool.model.NotasModel;
import br.com.riverlandschool.view.FormCadastroNotas;
import br.com.riverlandschool.view.FormLoginEscola;

public class NotasDao {
	
	public static boolean cadastrarNotas(NotasModel notas) throws SQLException {
	    int response = JOptionPane.showConfirmDialog(null, "Deseja Realmente Cadastrar o Registro?", "Confirmar",
	            JOptionPane.YES_NO_OPTION);

	    if (response == JOptionPane.NO_OPTION) {
	        JOptionPane.getDefaultLocale();
	    } else if (response == JOptionPane.YES_OPTION) {
	        Connection c = null;
	        try {
	            Class.forName("org.sqlite.JDBC");
	            c = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
	            c.setAutoCommit(false);


	            
	            String raAluno = notas.getRa();
	            String query = "SELECT COUNT(*) AS count, Serie FROM Notas WHERE RA_Aluno = ?";
	            PreparedStatement stmtCount = c.prepareStatement(query);
	            stmtCount.setString(1, raAluno);
	            ResultSet rsCount = stmtCount.executeQuery();
	            int count = rsCount.getInt("count");
	            String serieAluno = rsCount.getString("Serie");
	            stmtCount.close();
	            rsCount.close();
	            
		        String query1 = "SELECT Serie FROM Usuarios WHERE RA = ?";
		        PreparedStatement stmtSerie = c.prepareStatement(query1);
		        stmtSerie.setString(1, raAluno);
		        ResultSet rsSerie = stmtSerie.executeQuery();
		        String serieAluno1 = rsSerie.getString("Serie");
		        stmtSerie.close();
		        rsSerie.close();

	            String disciplinaProfessor = FormLoginEscola.getDisciplinaProfessor();
	            String sql = "";
	            if (count > 0) {
	                if (disciplinaProfessor.equalsIgnoreCase("Matemática")) {
	                    sql = "UPDATE Notas SET Nota_Bim1_Mat = ?, Nota_Bim2_Mat = ?, Nota_Bim3_Mat = ?, Nota_Bim4_Mat = ?, Serie = ? WHERE RA_Aluno = ?";
	                } else if (disciplinaProfessor.equalsIgnoreCase("Português")) {
	                    sql = "UPDATE Notas SET Nota_Bim1_Port = ?, Nota_Bim2_Port = ?, Nota_Bim3_Port = ?, Nota_Bim4_Port = ?, Serie = ? WHERE RA_Aluno = ?";
	                } else if (disciplinaProfessor.equalsIgnoreCase("História")) {
	                    sql = "UPDATE Notas SET Nota_Bim1_His = ?, Nota_Bim2_His = ?, Nota_Bim3_His = ?, Nota_Bim4_His = ?, Serie = ? WHERE RA_Aluno = ?";
	                } else if (disciplinaProfessor.equalsIgnoreCase("Geografia")) {
	                    sql = "UPDATE Notas SET Nota_Bim1_Geo = ?, Nota_Bim2_Geo = ?, Nota_Bim3_Geo = ?, Nota_Bim4_Geo = ?, Serie = ? WHERE RA_Aluno = ?";
	                }

	                PreparedStatement stmtUpdate = c.prepareStatement(sql);
	                stmtUpdate.setDouble(1, notas.getNotaBimestre1());
	                stmtUpdate.setDouble(2, notas.getNotaBimestre2());
	                stmtUpdate.setDouble(3, notas.getNotaBimestre3());
	                stmtUpdate.setDouble(4, notas.getNotaBimestre4());
	                stmtUpdate.setString(5, serieAluno1);
	                stmtUpdate.setString(6, raAluno);
	                stmtUpdate.execute();
	                stmtUpdate.close();
	            } else {
	                if (disciplinaProfessor.equalsIgnoreCase("Matemática")) {
	                    sql = "INSERT INTO Notas (Nome_Aluno, RA_Aluno, Nota_Bim1_Mat, Nota_Bim2_Mat, Nota_Bim3_Mat, Nota_Bim4_Mat, Serie) VALUES (?, ?, ?, ?, ?, ?, ?)";
	                } else if (disciplinaProfessor.equalsIgnoreCase("Português")) {
	                    sql = "INSERT INTO Notas (Nome_Aluno, RA_Aluno, Nota_Bim1_Port, Nota_Bim2_Port, Nota_Bim3_Port, Nota_Bim4_Port, Serie) VALUES (?, ?, ?, ?, ?, ?, ?)";
	                } else if (disciplinaProfessor.equalsIgnoreCase("História")) {
	                    sql = "INSERT INTO Notas (Nome_Aluno, RA_Aluno, Nota_Bim1_His, Nota_Bim2_His, Nota_Bim3_His, Nota_Bim4_His, Serie) VALUES (?, ?, ?, ?, ?, ?, ?)";
	                } else if (disciplinaProfessor.equalsIgnoreCase("Geografia")) {
	                    sql = "INSERT INTO Notas (Nome_Aluno, RA_Aluno, Nota_Bim1_Geo, Nota_Bim2_Geo, Nota_Bim3_Geo, Nota_Bim4_Geo, Serie) VALUES (?, ?, ?, ?, ?, ?, ?)";
	                }

	                PreparedStatement stmtInsert = c.prepareStatement(sql);
	                stmtInsert.setString(1, notas.getNome());
	                stmtInsert.setString(2, notas.getRa());
	                stmtInsert.setDouble(3, notas.getNotaBimestre1());
	                stmtInsert.setDouble(4, notas.getNotaBimestre2());
	                stmtInsert.setDouble(5, notas.getNotaBimestre3());
	                stmtInsert.setDouble(6, notas.getNotaBimestre4());
	                stmtInsert.setString(7, serieAluno1);
	                stmtInsert.executeUpdate();
	                stmtInsert.close();
	            }

	            c.commit();
	            c.setAutoCommit(true);
	            c.close();

	            return true;
	        } catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            return false;
	        }
	    }

	    return false;
	}


	            

//	public static boolean cadastrarNotas(NotasModel notas) throws SQLException {
//		int response = JOptionPane.showConfirmDialog(null, "Deseja Realmente Cadastrar o Registro?", "Confirmar",
//				JOptionPane.YES_NO_OPTION);
//
//		if (response == JOptionPane.NO_OPTION) {
//			JOptionPane.getDefaultLocale();
//		} else if (response == JOptionPane.YES_OPTION) {
//
//			Connection c = null;
//			try {
//				Class.forName("org.sqlite.JDBC");
//				c = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
//				c.setAutoCommit(false);
//				
//				String raAluno = notas.getRa();
//		        String query = "SELECT Serie FROM Usuarios WHERE RA = ?";
//		        PreparedStatement stmtSerie = c.prepareStatement(query);
//		        stmtSerie.setString(1, raAluno);
//		        ResultSet rsSerie = stmtSerie.executeQuery();
//		        String serieAluno = rsSerie.getString("Serie");
//		        stmtSerie.close();
//		        rsSerie.close();
//				
//				String disciplinaProfessor = FormLoginEscola.getDisciplinaProfessor();
//				String sql = "";
//				if(disciplinaProfessor.equalsIgnoreCase("Matemática")) {
//					 sql = "INSERT INTO Notas (Nome_Aluno, RA_Aluno, Nota_Bim1_Mat, Nota_Bim2_Mat, Nota_Bim3_Mat, Nota_Bim4_Mat)" +
//						     "VALUES (?, ?, ?, ?, ?, ?)";
//				}else if(disciplinaProfessor.equalsIgnoreCase("Português")) {
//					 sql = "INSERT INTO Notas (Nome_Aluno, RA_Aluno, Nota_Bim1_Port, Nota_Bim2_Port, Nota_Bim3_Port, Nota_Bim4_Port)" +
//						     "VALUES (?, ?, ?, ?, ?, ?)";
//				}else if(disciplinaProfessor.equalsIgnoreCase("História")) {
//					 sql = "INSERT INTO Notas (Nome_Aluno, RA_Aluno, Nota_Bim1_His, Nota_Bim2_His, Nota_Bim3_His, Nota_Bim4_His)" +
//						     "VALUES (?, ?, ?, ?, ?, ?)";
//				}else if(disciplinaProfessor.equalsIgnoreCase("Geografia")) {
//					 sql = "INSERT INTO Notas (Nome_Aluno, RA_Aluno, Nota_Bim1_Geo, Nota_Bim2_Geo, Nota_Bim3_Geo, Nota_Bim4_Geo, Serie)" +
//						     "VALUES (?, ?, ?, ?, ?, ?, ?)";
//				}
//				
//				PreparedStatement stmt = c.prepareStatement(sql);
//				stmt.setString(1, notas.getNome());
//				stmt.setString(2, notas.getRa());
//				stmt.setDouble(3, notas.getNotaBimestre1());
//				stmt.setDouble(4, notas.getNotaBimestre2());
//				stmt.setDouble(5, notas.getNotaBimestre3());
//				stmt.setDouble(6, notas.getNotaBimestre4());
//				stmt.setString(7, serieAluno);
//				stmt.execute();
//				stmt.close();
//				c.commit();
//				c.close();
//				return true;
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//		return false;
//	}
	
	public static NotasModel pesquisarNotas(String raAluno, String disciplinaProfessor) throws SQLException {
	    Connection c = null;
	    NotasModel notas = new NotasModel();

	    try {
	        Class.forName("org.sqlite.JDBC");
	        c = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");

	        String sql = "";

	        if (disciplinaProfessor.equalsIgnoreCase("Matemática")) {
	            sql = "SELECT Nota_Bim1_Mat, Nota_Bim2_Mat, Nota_Bim3_Mat, Nota_Bim4_Mat FROM Notas WHERE RA_Aluno = ?";
	        } else if (disciplinaProfessor.equalsIgnoreCase("Português")) {
	            sql = "SELECT Nota_Bim1_Port, Nota_Bim2_Port, Nota_Bim3_Port, Nota_Bim4_Port FROM Notas WHERE RA_Aluno = ?";
	        } else if (disciplinaProfessor.equalsIgnoreCase("História")) {
	            sql = "SELECT Nota_Bim1_His, Nota_Bim2_His, Nota_Bim3_His, Nota_Bim4_His FROM Notas WHERE RA_Aluno = ?";
	        } else if (disciplinaProfessor.equalsIgnoreCase("Geografia")) {
	            sql = "SELECT Nota_Bim1_Geo, Nota_Bim2_Geo, Nota_Bim3_Geo, Nota_Bim4_Geo FROM Notas WHERE RA_Aluno = ?";
	        }

	        PreparedStatement stmtSelect = c.prepareStatement(sql);
	        stmtSelect.setString(1, raAluno);
	        ResultSet rs = stmtSelect.executeQuery();

	        if (rs.next()) {
	            notas.setNotaBimestre1(rs.getDouble(1));
	            notas.setNotaBimestre2(rs.getDouble(2));
	            notas.setNotaBimestre3(rs.getDouble(3));
	            notas.setNotaBimestre4(rs.getDouble(4));
	        }

	        rs.close();
	        stmtSelect.close();
	        c.close();
	    } catch (Exception e) {
	        System.err.println(e.getClass().getName() + ": " + e.getMessage());
	    }

	    return notas;
	}



	
//	public static NotasModel pesquisarNotas(NotasModel obj) {
//	    Connection c = null;
//	    PreparedStatement stmt = null;
//	    ResultSet rs = null;
//	    try {
//	        Class.forName("org.sqlite.JDBC");
//	        c = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
//	        c.setAutoCommit(false);
//
//	        // Utilize PreparedStatement para evitar problemas com SQL Injection
//	        String query = "SELECT Nome_Aluno, RA_Aluno, Nota_Bimestre1, Nota_Bimestre2, Nota_Bimestre3, Nota_Bimestre4 FROM NOTAS WHERE RA_Aluno = ?";
//	        stmt = c.prepareStatement(query);
//	        stmt.setString(1, obj.getRa());
//
//	        rs = stmt.executeQuery();
//
//	        if (rs.next()) {
//	            NotasModel nota = new NotasModel();
//
//	            nota.setNome(rs.getString("Nome_Aluno"));
//	            nota.setRa(rs.getString("RA_Aluno"));
//	            nota.setNotaBimestre1(rs.getDouble("Nota_Bimestre1"));
//	            nota.setNotaBimestre2(rs.getDouble("Nota_Bimestre2"));
//	            nota.setNotaBimestre3(rs.getDouble("Nota_Bimestre3"));
//	            nota.setNotaBimestre4(rs.getDouble("Nota_Bimestre4"));
//
//	            return nota;
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    } finally {
//	        // Certifique-se de fechar as conexões, declarações e resultados do conjunto
//	        try {
//	            if (rs != null)
//	                rs.close();
//	            if (stmt != null)
//	                stmt.close();
//	            if (c != null)
//	                c.close();
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//
//	    return null;
//	}
	public static void excluirNotas(String disciplinaProfessor) throws SQLException {
	    Connection c = null;

	    try {
	        Class.forName("org.sqlite.JDBC");
	        c = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");

	        String sql = "";

	        if (disciplinaProfessor.equalsIgnoreCase("Matemática")) {
	            sql = "UPDATE Notas SET Nota_Bim1_Mat = NULL, Nota_Bim2_Mat = NULL, Nota_Bim3_Mat = NULL, Nota_Bim4_Mat = NULL WHERE 1=1";
	        } else if (disciplinaProfessor.equalsIgnoreCase("Português")) {
	            sql = "UPDATE Notas SET Nota_Bim1_Port = NULL, Nota_Bim2_Port = NULL, Nota_Bim3_Port = NULL, Nota_Bim4_Port = NULL WHERE 1=1";
	        } else if (disciplinaProfessor.equalsIgnoreCase("História")) {
	            sql = "UPDATE Notas SET Nota_Bim1_His = NULL, Nota_Bim2_His = NULL, Nota_Bim3_His = NULL, Nota_Bim4_His = NULL WHERE 1=1";
	        } else if (disciplinaProfessor.equalsIgnoreCase("Geografia")) {
	            sql = "UPDATE Notas SET Nota_Bim1_Geo = NULL, Nota_Bim2_Geo = NULL, Nota_Bim3_Geo = NULL, Nota_Bim4_Geo = NULL WHERE 1=1";
	        }

	        Statement stmt = c.createStatement();
	        stmt.executeUpdate(sql);

	        stmt.close();
	        c.close();
	    } catch (Exception e) {
	        System.err.println(e.getClass().getName() + ": " + e.getMessage());
	    }
	}

	
//	public static boolean excluirNotas(NotasModel obj) {
//
//		int response = JOptionPane.showConfirmDialog(null, "Deseja Realmente Deletar o Registro?", "Confirmar",
//				JOptionPane.YES_NO_OPTION);
//
//		if (response == JOptionPane.NO_OPTION) {
//			JOptionPane.getDefaultLocale();
//		} else if (response == JOptionPane.YES_OPTION) {
//			Connection c = null;
//			Statement stmt = null;
//			try {
//				Class.forName("org.sqlite.JDBC");
//				c = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
//				c.setAutoCommit(false);
//				stmt = c.createStatement();
//				String sql = "DELETE FROM NOTAS WHERE RA_Aluno =" + "'" + FormCadastroNotas.comboBoxRA.getSelectedItem() + "'" ;				
//				stmt.executeUpdate(sql);
//				c.commit();
//				stmt.close();
//				c.close();
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(new JFrame(), "Registro inexistente!!!", "Atenção",
//						JOptionPane.ERROR_MESSAGE);
//			}
//		}
//		System.out.println("Registro Deletado com Sucesso!");
//		return false;
//	}
	
	public static boolean alterarNotas(NotasModel notas) throws SQLException {
	    int response = JOptionPane.showConfirmDialog(null, "Deseja Realmente Alterar o Registro?", "Confirmar",
	            JOptionPane.YES_NO_OPTION);

	    if (response == JOptionPane.NO_OPTION) {
	        JOptionPane.getDefaultLocale();
	    } else if (response == JOptionPane.YES_OPTION) {
	        Connection c = null;
	        try {
	            Class.forName("org.sqlite.JDBC");
	            c = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
	            c.setAutoCommit(false);

	            String raAluno = notas.getRa();
	            String disciplinaProfessor = FormLoginEscola.getDisciplinaProfessor();
	            String sql = "";

	            if (disciplinaProfessor.equalsIgnoreCase("Matemática")) {
	                sql = "UPDATE Notas SET Nota_Bim1_Mat = ?, Nota_Bim2_Mat = ?, Nota_Bim3_Mat = ?, Nota_Bim4_Mat = ?  WHERE RA_Aluno = ?";
	            } else if (disciplinaProfessor.equalsIgnoreCase("Português")) {
	                sql = "UPDATE Notas SET Nota_Bim1_Port = ?, Nota_Bim2_Port = ?, Nota_Bim3_Port = ?, Nota_Bim4_Port = ?  WHERE RA_Aluno = ?";
	            } else if (disciplinaProfessor.equalsIgnoreCase("História")) {
	                sql = "UPDATE Notas SET Nota_Bim1_His = ?, Nota_Bim2_His = ?, Nota_Bim3_His = ?, Nota_Bim4_His = ? WHERE RA_Aluno = ?";
	            } else if (disciplinaProfessor.equalsIgnoreCase("Geografia")) {
	                sql = "UPDATE Notas SET Nota_Bim1_Geo = ?, Nota_Bim2_Geo = ?, Nota_Bim3_Geo = ?, Nota_Bim4_Geo = ? WHERE RA_Aluno = ?";
	            }

	            PreparedStatement stmtUpdate = c.prepareStatement(sql);
	            stmtUpdate.setDouble(1, notas.getNotaBimestre1());
	            stmtUpdate.setDouble(2, notas.getNotaBimestre2());
	            stmtUpdate.setDouble(3, notas.getNotaBimestre3());
	            stmtUpdate.setDouble(4, notas.getNotaBimestre4());
	            stmtUpdate.setString(5, raAluno);
	            stmtUpdate.executeUpdate();
	            stmtUpdate.close();

	            c.commit();
	            c.setAutoCommit(true);
	            c.close();

	            return true;
	        } catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            return false;
	        }
	    }

	    return false;
	}

//	public static boolean alterarNotas(NotasModel notas) {
//	    int response = JOptionPane.showConfirmDialog(null, "Deseja Realmente Alterar o Registro?", "Confirmar",
//	            JOptionPane.YES_NO_OPTION);
//
//	    if (response == JOptionPane.NO_OPTION) {
//	        JOptionPane.getDefaultLocale();
//	    } else if (response == JOptionPane.YES_OPTION) {
//	        Connection c = null;
//	        try {
//	            Class.forName("org.sqlite.JDBC");
//	            c = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
//	            c.setAutoCommit(false);
//	            String sql = "UPDATE NOTAS SET Nota_Bimestre1 = ?, Nota_Bimestre2 = ?, Nota_Bimestre3 = ?, Nota_Bimestre4 = ? WHERE RA_Aluno = ?";
//
//		        PreparedStatement stmt = c.prepareStatement(sql);
//				stmt.setDouble(1, notas.getNotaBimestre1());
//				stmt.setDouble(2, notas.getNotaBimestre2());
//				stmt.setDouble(3, notas.getNotaBimestre3());
//				stmt.setDouble(4, notas.getNotaBimestre4());
//	            stmt.setString(5, notas.getRa());
//
//
//	            int affectedRows = stmt.executeUpdate();
//	            if (affectedRows > 0) {
//	                JOptionPane.showMessageDialog(new JFrame(), "Registro alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
//	            } else {
//	                JOptionPane.showMessageDialog(new JFrame(), "Nenhum registro encontrado para alteração.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
//	            }
//
//	            stmt.close();
//	            c.commit();
//	            c.close();
//	        } catch (ClassNotFoundException e) {
//	            e.printStackTrace();
//	        } catch (SQLException e) {
//	            JOptionPane.showMessageDialog(new JFrame(), "Ocorreu um erro ao alterar o registro.", "Erro", JOptionPane.ERROR_MESSAGE);
//	        }
//	    }
//		return false;
//	}
}
