package br.com.riverlandschool.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.SwingConstants;

import br.com.riverlandschool.dao.UsuarioDao;
import br.com.riverlandschool.model.UsuarioModel;

public class FormPrincipal extends JFrame {

	private FormPerfil formPerfil;
	private FormCadastroUsuario formCadastroUsuario;
	private FormCadastroAulas formCadastroAulas;
	private FormCadastroNotas formCadastroNotas;
	private Relatorio relatorio;
	private Relatorio2 relatorio2;
	private BackupRestoreGUI backupRestoreGUI;
	
	public static JMenu mnPerfil;
	public static JMenuItem mntmMeuPerfil;
	public static JMenuItem mntmUsuarios;

	public static JMenu mnGestaoDisciplinas;
	public static JMenuItem mntmAulas;
	public static JMenuItem mntmNotas;
	public static JMenuItem mntmFazerBackup;

	public static JMenu mnRelatorio;
	public static JMenuItem mntmBoletimAluno;
	public static JMenuItem mntmBoletim;

	public static JMenu mnBackup;
	
    private UsuarioModel obj;
    private UsuarioDao dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println(ex);
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPrincipal frame = new FormPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormPrincipal() {
		
    	obj = new UsuarioModel();
    	try {
			dao = new UsuarioDao();
		} catch (Exception ex) {
			Logger.getLogger(FormCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
		}
    	
		setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormPrincipal.class.getResource("/br/com/riverlandschool/icons/icone48px.png")));
        setTitle("RIVERLAND SCHOOL");
		setFont(new Font("Cambria", Font.ITALIC, 12));
        setExtendedState(MAXIMIZED_BOTH); //Maximiza o JFPrincipal no tamanho total da tela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		
		JLabel logo = new JLabel("");
        logo.setIcon(new ImageIcon(FormPrincipal.class.getResource("/br/com/riverlandschool/icons/logo640px.png")));
        logo.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza a imagem
		getContentPane().add(logo, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnPerfil = new JMenu("Perfil");
		mnPerfil.setFont(new Font("Century", Font.PLAIN, 14));
		menuBar.add(mnPerfil);
		
		mntmMeuPerfil = new JMenuItem("Meu perfil");
		mntmMeuPerfil.setIcon(new ImageIcon(FormPrincipal.class.getResource("/br/com/riverlandschool/icons/perfil24px.png")));
		mntmMeuPerfil.setFont(new Font("Century", Font.PLAIN, 14));
		mnPerfil.add(mntmMeuPerfil);
		
		mntmUsuarios = new JMenuItem("Usuários");
		mntmUsuarios.setIcon(new ImageIcon(FormPrincipal.class.getResource("/br/com/riverlandschool/icons/usuarios24px.png")));
		mntmUsuarios.setFont(new Font("Century", Font.PLAIN, 14));
		mnPerfil.add(mntmUsuarios);

		mnGestaoDisciplinas = new JMenu("Gestão de disciplinas");
		mnGestaoDisciplinas.setBackground(new Color(192, 192, 192));
		mnGestaoDisciplinas.setFont(new Font("Century", Font.PLAIN, 14));
		menuBar.add(mnGestaoDisciplinas);
		
		mntmAulas = new JMenuItem("Aulas");
		mntmAulas.setIcon(new ImageIcon(FormPrincipal.class.getResource("/br/com/riverlandschool/icons/aula24px.png")));
		mntmAulas.setFont(new Font("Century", Font.PLAIN, 14));
		mnGestaoDisciplinas.add(mntmAulas);
		
		mntmNotas = new JMenuItem("Notas");
		mntmNotas.setIcon(new ImageIcon(FormPrincipal.class.getResource("/br/com/riverlandschool/icons/nota24px.png")));
		mntmNotas.setFont(new Font("Century", Font.PLAIN, 14));
		mnGestaoDisciplinas.add(mntmNotas);
		
		mnRelatorio = new JMenu("Relatório");
		mnRelatorio.setFont(new Font("Century", Font.PLAIN, 14));
		menuBar.add(mnRelatorio);
		
		mntmBoletimAluno = new JMenuItem("Boletim Aluno");
		mntmBoletimAluno.setIcon(new ImageIcon(FormPrincipal.class.getResource("/br/com/riverlandschool/icons/arquivos24px.png")));
		mntmBoletimAluno.setFont(new Font("Century", Font.PLAIN, 14));
		mnRelatorio.add(mntmBoletimAluno);
		
		mntmBoletim = new JMenuItem("Boletim");
		mntmBoletim.setIcon(new ImageIcon(FormPrincipal.class.getResource("/br/com/riverlandschool/icons/arquivos24px.png")));
		mntmBoletim.setFont(new Font("Century", Font.PLAIN, 14));
		mnRelatorio.add(mntmBoletim);
		
		mnBackup = new JMenu("Backup");
		mnBackup.setFont(new Font("Century", Font.PLAIN, 14));
		menuBar.add(mnBackup);
		
		mntmFazerBackup = new JMenuItem("Fazer Backup");
		mntmFazerBackup.setIcon(new ImageIcon(FormPrincipal.class.getResource("/br/com/riverlandschool/icons/backup24px.png")));
		mnBackup.add(mntmFazerBackup);
		
		// Ação do botão Cadastro de Aluno
		mntmUsuarios.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(formCadastroUsuario == null || !formCadastroUsuario.isVisible()) {
		        	formCadastroUsuario = new FormCadastroUsuario();
		        	formCadastroUsuario.setLocationRelativeTo(null);		//Abre o JFrame no meio da tela
		        	formCadastroUsuario.setResizable(false); 				//Trava o tamanho da tela, não deixa maximizar
					formCadastroUsuario.setVisible(true);
		        } else {
		        	formCadastroUsuario.toFront();							//Trás o JFrame para frente
		        }
		        formCadastroUsuario.requestFocus();
			}
		});
		
		// Ação do botão Cadastro de Professor
		mntmMeuPerfil.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(formPerfil == null || !formPerfil.isVisible()) {
					formPerfil = new FormPerfil();
					formPerfil.setLocationRelativeTo(null);
					formPerfil.setResizable(false);
					formPerfil.setVisible(true);
					
			        UsuarioDao usuarioDao = new UsuarioDao();
			        try {
			            ArrayList<UsuarioModel> listaUsuarios = usuarioDao.getUsuarioPerfil(obj);
			            
			            for (UsuarioModel usuario : listaUsuarios) {
			            
				            	FormPerfil.txtNome.setText(usuario.getNome());
				            	FormPerfil.txtEmail.setText(usuario.getEmail());
				            	FormPerfil.txtSenha.setText(usuario.getSenha());
				            	if(usuario.getTipoUsuario().equals("Aluno")) {
				            	FormPerfil.comboBoxTipoUsuario.setSelectedItem("Aluno");
				            	}else if(usuario.getTipoUsuario().equals("Coordenador")) {
					            FormPerfil.comboBoxTipoUsuario.setSelectedItem("Coordenador");
				            	}else if(usuario.getTipoUsuario().equals("Professor")) {
						        FormPerfil.comboBoxTipoUsuario.setSelectedItem("Professor");
					            }
				            	FormPerfil.txtCpf.setText(usuario.getCpf());
				            	FormPerfil.txtRa.setText(usuario.getRa());
				            	FormPerfil.txtDisciplina.setText(usuario.getDisciplina());
			            	
			            	}  
			            			            
			        } catch (Exception e1) {
			            e1.printStackTrace();
			        }
					
				} else {
					formPerfil.toFront();
				}
				formPerfil.requestFocus();
			}
		});
		mntmNotas.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(formCadastroNotas == null || !formCadastroNotas.isVisible()) {
					formCadastroNotas = new FormCadastroNotas();
					formCadastroNotas.setLocationRelativeTo(null);
					formCadastroNotas.setResizable(false);
					formCadastroNotas.setVisible(true);
				} else {
					formCadastroNotas.toFront();
				}
				formCadastroNotas.requestFocus();
			}
		});
		
		mntmAulas.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(formCadastroAulas == null || !formCadastroAulas.isVisible()) {
					formCadastroAulas = new FormCadastroAulas();
					formCadastroAulas.setLocationRelativeTo(null);
					formCadastroAulas.setResizable(false);
					formCadastroAulas.setVisible(true);
				} else {
					formCadastroAulas.toFront();
				}
				formCadastroAulas.requestFocus();
			}
		});
		
		mntmBoletimAluno.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(relatorio == null || !relatorio.isVisible()) {
					relatorio = new Relatorio();
					relatorio.setLocationRelativeTo(null);
					relatorio.setResizable(false);
					relatorio.setVisible(true);
				} else {
					relatorio.toFront();
				}
				relatorio.requestFocus();
			}
		});
		
		mntmBoletim.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(relatorio2 == null || !relatorio2.isVisible()) {
					relatorio2 = new Relatorio2();
					relatorio2.setLocationRelativeTo(null);
					relatorio2.setResizable(false);
					relatorio2.setVisible(true);
				} else {
					relatorio2.toFront();
				}
				relatorio2.requestFocus();
			}
		});
		
		mntmFazerBackup.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(backupRestoreGUI == null || !backupRestoreGUI.isVisible()) {
					backupRestoreGUI = new BackupRestoreGUI();
					backupRestoreGUI.setLocationRelativeTo(null);
					backupRestoreGUI.setResizable(false);
					backupRestoreGUI.setVisible(true);
				} else {
					relatorio2.toFront();
				}
				relatorio2.requestFocus();
			}
		});
	}
}
