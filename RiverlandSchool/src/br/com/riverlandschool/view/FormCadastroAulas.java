package br.com.riverlandschool.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import br.com.riverlandschool.dao.AulasDao;
import br.com.riverlandschool.dao.UsuarioDao;
import br.com.riverlandschool.model.AulasModel;
import br.com.riverlandschool.model.NotasModel;
import br.com.riverlandschool.model.UsuarioModel;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class FormCadastroAulas extends JFrame {

    private JPanel contentPane;
    public static JTextField textFieldProfessor;
    public static JTextField textFieldDisciplina;
    public static JTextField textFieldDataAula;
    public static JTextArea textAreaConteudoAula;
    
    private AulasDao dao;
    private AulasModel obj;
    
    private JButton btnIncluir;
    private JButton btnExcluir;
    private JButton btnAlterar;
    private JButton btnLimpar;
    private JButton btnPesquisar;

    /**
     * Create the frame.
     */
    public FormCadastroAulas() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(FormCadastroAulas.class.getResource("/br/com/riverlandschool/icons/icone48px.png")));
    	setTitle("RIVERLAND SCHOOL - Cadastro de Aulas");
    	
    	obj = new AulasModel();
    	try {
			dao = new AulasDao();
		} catch (Exception ex) {
			Logger.getLogger(FormCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
		}
    	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 463, 308);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(218, 218, 218));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblProfessor = new JLabel("Professor");
        lblProfessor.setBounds(10, 11, 60, 20);
        lblProfessor.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        contentPane.add(lblProfessor);

        textFieldProfessor = new JTextField();
        textFieldProfessor.setEditable(false);
        textFieldProfessor.setBounds(10, 32, 248, 25);
        contentPane.add(textFieldProfessor);
        textFieldProfessor.setColumns(10);
        textFieldProfessor.setText(FormLoginEscola.getNomeProfessor());


        JLabel lblDisciplina = new JLabel("Disciplina");
        lblDisciplina.setBounds(268, 10, 100, 20);
        lblDisciplina.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        contentPane.add(lblDisciplina);

        textFieldDisciplina = new JTextField();
        textFieldDisciplina.setEditable(false);
        textFieldDisciplina.setBounds(268, 32, 172, 25);
        contentPane.add(textFieldDisciplina);
        textFieldDisciplina.setColumns(10);
        textFieldDisciplina.setText(FormLoginEscola.getDisciplinaProfessor());


        JLabel lblDataAula = new JLabel("Data Aula");
        lblDataAula.setBounds(10, 72, 100, 20);
        lblDataAula.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        contentPane.add(lblDataAula);

        textFieldDataAula = new JTextField();
        textFieldDataAula.setBounds(73, 69, 227, 25);
        contentPane.add(textFieldDataAula);
        textFieldDataAula.setColumns(10);

        JLabel lblConteudoAula = new JLabel("Conteúdo Aula");
        lblConteudoAula.setBounds(10, 103, 100, 20);
        lblConteudoAula.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        contentPane.add(lblConteudoAula);

        textAreaConteudoAula = new JTextArea();
        textAreaConteudoAula.setBounds(10, 124, 430, 100);
        textAreaConteudoAula.setBorder(UIManager.getBorder("Button.border"));
        contentPane.add(textAreaConteudoAula);

        btnIncluir = new JButton(" Incluir");
        btnIncluir.setIcon(new ImageIcon(FormCadastroAulas.class.getResource("/br/com/riverlandschool/icons/adicionar24px.png")));
        btnIncluir.setBounds(10, 235, 100, 30);
        contentPane.add(btnIncluir);
        btnIncluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					if(preencheAula()) {
						if(validarCampos()) {
							if(AulasDao.incluirAula(obj)) {
								JOptionPane.showMessageDialog(new JFrame(), "Aula cadastrada com sucesso!",
	                                    "RIVERLAND SCHOOL", JOptionPane.INFORMATION_MESSAGE);
								limparCampos();
							}
						}
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(new JFrame(), "Erro ao salvar:"+erro.getMessage(),
							"RIVERLAND SCHOOL", JOptionPane.ERROR_MESSAGE);
				}
            }
        });
        
        // Botão de Excluir
        btnExcluir = new JButton(" Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.setIcon(new ImageIcon(FormCadastroAulas.class.getResource("/br/com/riverlandschool/icons/cancelar24px.png")));
        btnExcluir.setBounds(120, 235, 100, 30);
        contentPane.add(btnExcluir);
        // Ação do botão Excluir
        btnExcluir.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
                 try {
                     if (AulasDao.excluirAula(obj)) {
                         limparCampos();
                         JOptionPane.showMessageDialog(new JFrame(), "Registro excluído com sucesso!",
                                 "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                         limparCampos();
                         bloquearCampos();
                     }
                 } catch (Exception erro) {
                     JOptionPane.showMessageDialog(new JFrame(), "Erro ao excluir: " + erro.getMessage(),
                             "Erro", JOptionPane.ERROR_MESSAGE);
                 }
             }
         });
        // Botão de Alterar
        btnAlterar = new JButton(" Alterar");
        btnAlterar.setEnabled(false);
        btnAlterar.setIcon(new ImageIcon(FormCadastroAulas.class.getResource("/br/com/riverlandschool/icons/editar24px.png")));
        btnAlterar.setBounds(230, 235, 100, 30);
        contentPane.add(btnAlterar);
        // Ação do botão Alterar
        btnAlterar.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent evt) {
        	        try {
        	            if (preencheAula()) {
        	            	if(validarCampos()){
        	            		if (AulasDao.alterarAula(obj)) {
            	                    JOptionPane.showMessageDialog(new JFrame(), "Aula alterada com sucesso!",
            	                            "RIVERLAND SCHOOL", JOptionPane.INFORMATION_MESSAGE);
            	                    limparCampos();
            	                    bloquearCampos();
            	                }
        	            	}
        	            }
        	        } catch (Exception erro) {
        	            JOptionPane.showMessageDialog(new JFrame(), "Erro ao alterar: " + erro.getMessage(),
        	                    "Erro", JOptionPane.ERROR_MESSAGE);
        	        }
        	    }
        	});

        // Botão de Limpar
        btnLimpar = new JButton(" Limpar");
        btnLimpar.setEnabled(false);
        btnLimpar.setIcon(new ImageIcon(FormCadastroAulas.class.getResource("/br/com/riverlandschool/icons/apagar24px.png")));
        btnLimpar.setBounds(340, 235, 100, 30);
        contentPane.add(btnLimpar);
        // Ação do botão Limpar
        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                limparCampos();
                bloquearCampos();
            }
        });
        
     // Botão de Pesquisar
        btnPesquisar = new JButton(" Pesquisar");
        btnPesquisar.setIcon(new ImageIcon(FormCadastroAulas.class.getResource("/br/com/riverlandschool/icons/pesquisar24px.png")));
        btnPesquisar.setBounds(330, 68, 110, 30);
        contentPane.add(btnPesquisar);
     // Ação do botão Pesquisar
        btnPesquisar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (preencheAula()) {
                        AulasModel aulaEncontrada = AulasDao.pesquisarAula(obj);
                        if (aulaEncontrada != null) {
                            // Preencher os campos com os dados da aula encontrada
                            textFieldProfessor.setText(aulaEncontrada.getProfessor());
                            textFieldDisciplina.setText(aulaEncontrada.getDisciplina());
                            textFieldDataAula.setText(aulaEncontrada.getDataAula());
                            textAreaConteudoAula.setText(aulaEncontrada.getConteudoAula());
                            btnIncluir.setEnabled(false);
                        	btnAlterar.setEnabled(true);
                        	btnExcluir.setEnabled(true);
                        	btnLimpar.setEnabled(true);
                        	btnPesquisar.setEnabled(true);
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Nenhuma aula encontrada para a data informada!",
                                    "RIVERLAND SCHOOL", JOptionPane.WARNING_MESSAGE);
                            limparCampos();
                        }
                    }
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(new JFrame(), "Erro ao pesquisar: " + erro.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
    

    private void limparCampos() {
        textFieldDataAula.setText("");
        textAreaConteudoAula.setText("");
    }
    
    public void bloquearCampos() {
    	btnIncluir.setEnabled(true);
    	btnAlterar.setEnabled(false);
    	btnExcluir.setEnabled(false);
    	btnLimpar.setEnabled(false);
    	btnPesquisar.setEnabled(true);
    }
    
    private boolean validarCampos() {
    	String msg = "";
    	
    	if(textFieldDataAula.getText().length()<10 || textFieldDataAula.getText().length()>10) {
    		msg+="\nO campo Data Aula precisa Seguir o padrão dd/mm/aaaa!";
    	}
    	if(textAreaConteudoAula.getText().length()<3 || textAreaConteudoAula.getText().length()>100) {
    		msg+="\nO campo Conteúdo aula precisa possuir entre 3 a 100 caracteres!";
    	}
    	if(msg.isEmpty()) {
    		return true;
    	} else {
    		JOptionPane.showMessageDialog(this, "Os campos abaixo precisam de atenção:"
    				+msg, "Atenção", JOptionPane.ERROR_MESSAGE);
    	}
    	return false;
    }
    
    //Método para prenecher o objeto
    private boolean preencheAula() throws Exception{
    	obj.setProfessor(textFieldProfessor.getText());
    	obj.setDisciplina(textFieldDisciplina.getText());
    	obj.setDataAula(textFieldDataAula.getText());
    	obj.setConteudoAula(textAreaConteudoAula.getText());

    	return true;
    }
}


