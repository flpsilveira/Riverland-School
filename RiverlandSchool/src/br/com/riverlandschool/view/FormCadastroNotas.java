package br.com.riverlandschool.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.riverlandschool.dao.AulasDao;
import br.com.riverlandschool.dao.NotasDao;
import br.com.riverlandschool.dao.UsuarioDao;
import br.com.riverlandschool.model.AulasModel;
import br.com.riverlandschool.model.NotasModel;
import java.awt.Color;
import java.awt.Toolkit;

public class FormCadastroNotas extends JFrame {

    private JPanel contentPane;
    public static JComboBox<String> comboBoxRA;
    public static JTextField textFieldNomeAluno;
    public static JTextField textFieldNotaBimestre1;
    public static JTextField textFieldNotaBimestre2;
    public static JTextField textFieldNotaBimestre3;
    public static JTextField textFieldNotaBimestre4;

    public static JButton btnLimpar;
    
    private NotasDao dao;
    private NotasModel obj;

    /**
     * Create the frame.
     */
    public FormCadastroNotas() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(FormCadastroNotas.class.getResource("/br/com/riverlandschool/icons/icone48px.png")));
    	setTitle("RIVERLAND SCHOOL - Cadastro de Notas");
    	
    	obj = new NotasModel();
    	try {
			dao = new NotasDao();
		} catch (Exception ex) {
			Logger.getLogger(FormCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
		}
    	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 480, 233);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNomeAluno = new JLabel("Aluno");
        lblNomeAluno.setBounds(10, 11, 100, 20);
        lblNomeAluno.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        contentPane.add(lblNomeAluno);

        textFieldNomeAluno = new JTextField();
        textFieldNomeAluno.setEditable(false);
        textFieldNomeAluno.setBounds(113, 10, 340, 25);
        contentPane.add(textFieldNomeAluno);
        textFieldNomeAluno.setColumns(10);

        JLabel lblRA = new JLabel("RA do Aluno");
        lblRA.setBounds(10, 47, 81, 20);
        lblRA.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        contentPane.add(lblRA);

        comboBoxRA = new JComboBox<String>();
        comboBoxRA.setBounds(113, 46, 228, 25);
        comboBoxRA.addItem(""); // Campo em branco no primeiro lugar
        popularComboboxRA();
        contentPane.add(comboBoxRA);

        JLabel lblNotaBimestre1 = new JLabel("Nota Bimestre 1");
        lblNotaBimestre1.setBounds(10, 83, 89, 20);
        lblNotaBimestre1.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        contentPane.add(lblNotaBimestre1);

        textFieldNotaBimestre1 = new JTextField();
        textFieldNotaBimestre1.setBounds(113, 82, 110, 25);
        contentPane.add(textFieldNotaBimestre1);
        textFieldNotaBimestre1.setColumns(10);

        JLabel lblNotaBimestre2 = new JLabel("Nota Bimestre 2");
        lblNotaBimestre2.setBounds(10, 119, 89, 20);
        lblNotaBimestre2.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        contentPane.add(lblNotaBimestre2);

        textFieldNotaBimestre2 = new JTextField();
        textFieldNotaBimestre2.setBounds(113, 118, 110, 25);
        contentPane.add(textFieldNotaBimestre2);
        textFieldNotaBimestre2.setColumns(10);

        JLabel lblNotaBimestre3 = new JLabel("Nota Bimestre 3");
        lblNotaBimestre3.setBounds(252, 82, 89, 20);
        lblNotaBimestre3.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        contentPane.add(lblNotaBimestre3);

        textFieldNotaBimestre3 = new JTextField();
        textFieldNotaBimestre3.setBounds(345, 82, 110, 25);
        contentPane.add(textFieldNotaBimestre3);
        textFieldNotaBimestre3.setColumns(10);

        JLabel lblNotaBimestre4 = new JLabel("Nota Bimestre 4");
        lblNotaBimestre4.setBounds(252, 116, 89, 20);
        lblNotaBimestre4.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        contentPane.add(lblNotaBimestre4);

        textFieldNotaBimestre4 = new JTextField();
        textFieldNotaBimestre4.setBounds(345, 118, 110, 25);
        contentPane.add(textFieldNotaBimestre4);
        textFieldNotaBimestre4.setColumns(10);

        // Botão de Salvar
        JButton btnSalvar = new JButton(" Salvar");
        btnSalvar.setEnabled(false);
        btnSalvar.setBounds(123, 150, 104, 33);
        btnSalvar.setIcon(new ImageIcon(FormCadastroNotas.class.getResource("/br/com/riverlandschool/icons/salvar24px.png")));
        contentPane.add(btnSalvar);
        // Ação do botão Salvar
        btnSalvar.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
             	try {
 					if(preencheNota()) {
 							if(NotasDao.cadastrarNotas(obj)) {
 								JOptionPane.showMessageDialog(new JFrame(), "Nota cadastrada com sucesso!",
 	                                    "RIVERLAND SCHOOL", JOptionPane.INFORMATION_MESSAGE);
 								limparCampos();
 							}
 						
 					}
 				} catch (Exception erro) {
 					JOptionPane.showMessageDialog(new JFrame(), "Erro ao salvar:"+erro.getMessage(),
 							"RIVERLAND SCHOOL", JOptionPane.ERROR_MESSAGE);
 				}
             }
         });

        // Botão de Excluir
        JButton btnExcluir = new JButton(" Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.setVisible(false);
        btnExcluir.setIcon(new ImageIcon(FormCadastroNotas.class.getResource("/br/com/riverlandschool/icons/cancelar24px.png")));
        btnExcluir.setBounds(123, 150, 104, 33);
        contentPane.add(btnExcluir);
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String disciplinaProfessor = FormLoginEscola.getDisciplinaProfessor();
                    NotasDao.excluirNotas(disciplinaProfessor);
                    JOptionPane.showMessageDialog(new JFrame(), "Registro excluído com sucesso!",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(new JFrame(), "Erro ao excluir: " + erro.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Botão de Alterar
        JButton btnAlterar = new JButton(" Alterar");
        btnAlterar.setEnabled(false);
        btnAlterar.setVisible(false);
        btnAlterar.setIcon(new ImageIcon(FormCadastroNotas.class.getResource("/br/com/riverlandschool/icons/editar24px.png")));
        btnAlterar.setBounds(237, 150, 104, 33);
        contentPane.add(btnAlterar);
        btnAlterar.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent evt) {
     	        try {
     	            if (preencheNota()) {
     	                if (NotasDao.alterarNotas(obj)) {
     	                    JOptionPane.showMessageDialog(new JFrame(), "Nota alterada com sucesso!",
     	                            "RIVERLAND SCHOOL", JOptionPane.INFORMATION_MESSAGE);
     	                    limparCampos();
     	                }
     	            }
     	        } catch (Exception erro) {
     	            JOptionPane.showMessageDialog(new JFrame(), "Erro ao alterar: " + erro.getMessage(),
     	                    "Erro", JOptionPane.ERROR_MESSAGE);
     	        }
     	    }
     	});

        // Botão de Consultar
     // Botão de Consultar
        JButton btnConsultar = new JButton(" Consultar");
        btnConsultar.setIcon(new ImageIcon(FormCadastroNotas.class.getResource("/br/com/riverlandschool/icons/pesquisar24px.png")));
        btnConsultar.setBounds(345, 42, 110, 33);
        contentPane.add(btnConsultar);
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (preencheNota()) {
                        String raAluno = obj.getRa();
                        String disciplinaProfessor = FormLoginEscola.getDisciplinaProfessor();

                        try {
                            NotasModel notas = NotasDao.pesquisarNotas(raAluno, disciplinaProfessor);

                            if (notas != null) {
                                // Preencher os campos com os dados da nota encontrada
                                textFieldNotaBimestre1.setText(Double.toString(notas.getNotaBimestre1()));
                                textFieldNotaBimestre2.setText(Double.toString(notas.getNotaBimestre2()));
                                textFieldNotaBimestre3.setText(Double.toString(notas.getNotaBimestre3()));
                                textFieldNotaBimestre4.setText(Double.toString(notas.getNotaBimestre4()));
                                btnSalvar.setEnabled(true);
                                btnLimpar.setEnabled(true);
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Nenhuma Nota encontrada!",
                                        "RIVERLAND SCHOOL", JOptionPane.WARNING_MESSAGE);
                                limparCampos();
                            }
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(new JFrame(), "Erro ao pesquisar: " + e.getMessage(),
                                    "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (HeadlessException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

//        JButton btnConsultar = new JButton("Consultar");
//        btnConsultar.setBounds(298, 338, 90, 30);
//        contentPane.add(btnConsultar);
//        btnConsultar.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                try {
//                    if (preencheNota()) {
//                        NotasModel notaEncontrada = NotasDao.pesquisarNotas(obj);
//                        if (notaEncontrada != null && (notaEncontrada.getNotaBimestre1() != null || notaEncontrada.getNotaBimestre2() != null ||
//                                notaEncontrada.getNotaBimestre3() != null || notaEncontrada.getNotaBimestre4() != null)) {
//                            // Preencher os campos com os dados da aula encontrada
//                            textFieldNotaBimestre1.setText(notaEncontrada.getNotaBimestre1() != null ? Double.toString(notaEncontrada.getNotaBimestre1()) : "");
//                            textFieldNotaBimestre2.setText(notaEncontrada.getNotaBimestre2() != null ? Double.toString(notaEncontrada.getNotaBimestre2()) : "");
//                            textFieldNotaBimestre3.setText(notaEncontrada.getNotaBimestre3() != null ? Double.toString(notaEncontrada.getNotaBimestre3()) : "");
//                            textFieldNotaBimestre4.setText(notaEncontrada.getNotaBimestre4() != null ? Double.toString(notaEncontrada.getNotaBimestre4()) : "");
//                        } else {
//                            JOptionPane.showMessageDialog(new JFrame(), "Nenhuma Nota encontrada!",
//                                    "RIVERLAND SCHOOL", JOptionPane.WARNING_MESSAGE);
//                            limparCampos();
//                        }
//                    }
//                } catch (Exception erro) {
//                    JOptionPane.showMessageDialog(new JFrame(), "Erro ao pesquisar: " + erro.getMessage(),
//                            "Erro", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });

        // Botão de Limpar
        btnLimpar = new JButton(" Limpar");
        btnLimpar.setEnabled(false);
        btnLimpar.setIcon(new ImageIcon(FormCadastroNotas.class.getResource("/br/com/riverlandschool/icons/apagar24px.png")));
        btnLimpar.setBounds(237, 150, 104, 33);
        contentPane.add(btnLimpar);
        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	limparCampos();
            }
        });



        // Atualizar o campo de nome do aluno ao selecionar um RA
        comboBoxRA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ra = (String) comboBoxRA.getSelectedItem();
                popularCampoNomeAluno(ra);
            }
        });
    }

    private void popularComboboxRA() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");

            // Consulta SQL para obter os RAs e nomes dos alunos
            String sql = "SELECT RA, NOME FROM USUARIOS WHERE TIPO_USUARIO = 'Aluno'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            // Limpar os itens existentes na combobox
            comboBoxRA.removeAllItems();

            // Adicionar campo em branco no primeiro lugar
            comboBoxRA.addItem("");

            while (rs.next()) {
                String ra = rs.getString("RA");
                comboBoxRA.addItem(ra);
            }

            // Fechar a conexão com o banco de dados
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void popularCampoNomeAluno(String ra) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");

            // Consulta SQL para obter o nome do aluno
            String sql = "SELECT NOME FROM USUARIOS WHERE RA = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, ra);

            rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("NOME");
                textFieldNomeAluno.setText(nome);
            } else {
                textFieldNomeAluno.setText("");
            }

            // Fechar a conexão com o banco de dados
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Método para prenecher o objeto

private boolean preencheNota() throws Exception {
    obj.setNome(textFieldNomeAluno.getText());
    obj.setRa((String) comboBoxRA.getSelectedItem());
    obj.setNotaBimestre1(textFieldNotaBimestre1.getText().isEmpty() ? null : Double.parseDouble(textFieldNotaBimestre1.getText()));
    obj.setNotaBimestre2(textFieldNotaBimestre2.getText().isEmpty() ? null : Double.parseDouble(textFieldNotaBimestre2.getText()));
    obj.setNotaBimestre3(textFieldNotaBimestre3.getText().isEmpty() ? null : Double.parseDouble(textFieldNotaBimestre3.getText()));
    obj.setNotaBimestre4(textFieldNotaBimestre4.getText().isEmpty() ? null : Double.parseDouble(textFieldNotaBimestre4.getText()));

    return true;
}


    private void limparCampos() {
        // Limpar os campos
    	textFieldNomeAluno.setText("");
        textFieldNotaBimestre1.setText("");
        textFieldNotaBimestre2.setText("");
        textFieldNotaBimestre3.setText("");
        textFieldNotaBimestre4.setText("");
        
        // Definir combobox como vazio
        comboBoxRA.setSelectedIndex(0);
    }}
