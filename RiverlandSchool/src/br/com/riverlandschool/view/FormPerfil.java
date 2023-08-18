package br.com.riverlandschool.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.riverlandschool.dao.UsuarioDao;
import br.com.riverlandschool.model.UsuarioModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class FormPerfil extends JFrame {

	public static JTextField txtNome;
	public static JTextField txtEmail;
	public static JPasswordField txtSenha;
	public static JTextField txtRa;
	public static JTextField txtCpf;
	public static JTextField txtDisciplina;
	
	public static JComboBox<String> comboBoxTipoUsuario;
	
    private JLabel lblNome;
    private JLabel lblEmail;
    private JLabel lblSenha;
    private JLabel lblRa;
    private JLabel lblCpf;
    private JLabel lblDisciplina;
    
    public static JPanel contentPane;
    private JPanel jpUsuario;
    private JPanel jpTipoUsuario;
    
    private JButton btnAlterar;
    private JButton btnCancelar;
    private JButton btnSalvar;
    
    private UsuarioModel obj;
    private UsuarioDao dao;

	/**
	 * Create the frame.
	 */
	public FormPerfil() {
    	obj = new UsuarioModel();
    	try {
			dao = new UsuarioDao();
		} catch (Exception ex) {
			Logger.getLogger(FormCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
		}
    	
		setTitle("RIVERLAND SCHOOL - Perfil");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormPerfil.class.getResource("/br/com/riverlandschool/icons/icone48px.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 492, 460);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jpUsuario = new JPanel(null);
		jpUsuario.setBackground(new Color(255, 255, 255));
		jpUsuario.setBounds(10, 11, 451, 143);
		contentPane.add(jpUsuario);
		jpUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(
        		null, "Usuário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
        		javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", Font.PLAIN, 14)));
		
		lblNome = new JLabel("Nome completo");
		lblNome.setBounds(24, 29, 125, 14);
		jpUsuario.add(lblNome);
		lblNome.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		txtNome.setEnabled(false);
		txtNome.setBounds(24, 48, 392, 25);
		jpUsuario.add(txtNome);
		txtNome.setColumns(10);
		
		lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(24, 84, 46, 14);
		jpUsuario.add(lblEmail);
		lblEmail.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		txtEmail.setEnabled(false);
		txtEmail.setBounds(24, 100, 191, 25);
		jpUsuario.add(txtEmail);
		txtEmail.setColumns(10);
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(225, 84, 55, 14);
		jpUsuario.add(lblSenha);
		lblSenha.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		txtSenha.setEnabled(false);
		txtSenha.setBounds(225, 100, 191, 25);
		jpUsuario.add(txtSenha);
		
		jpTipoUsuario = new JPanel(null);
		jpTipoUsuario.setBackground(new Color(255, 255, 255));
		jpTipoUsuario.setBounds(10, 165, 451, 184);
		contentPane.add(jpTipoUsuario);
		jpTipoUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(
        		null, "Tipo usuário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
        		javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", Font.PLAIN, 14)));
		
		comboBoxTipoUsuario = new JComboBox();
		comboBoxTipoUsuario.setEnabled(false);
		comboBoxTipoUsuario.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		comboBoxTipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"Selecionar", "Aluno", "Professor", "Coordenador"}));
		comboBoxTipoUsuario.setBounds(24, 26, 166, 25);
		jpTipoUsuario.add(comboBoxTipoUsuario);
		comboBoxTipoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tipoUsuario = comboBoxTipoUsuario.getSelectedItem().toString();
                if(tipoUsuario.equals("Selecionar")) {
                	lblRa.setVisible(false);
                	txtRa.setVisible(false);
                	lblCpf.setVisible(false);
                	txtCpf.setVisible(false);
                	lblDisciplina.setVisible(false);
                	txtDisciplina.setVisible(false);
                }
                if (tipoUsuario.equals("Aluno")) {
                	lblRa.setVisible(true);
                	txtRa.setVisible(true);
                	lblCpf.setVisible(false);
                	txtCpf.setVisible(false);
                	lblDisciplina.setVisible(false);
                	txtDisciplina.setVisible(false);
                }
                if (tipoUsuario.equals("Professor")) {
                	lblRa.setVisible(false);
                	txtRa.setVisible(false);
                	lblCpf.setVisible(true);
                	txtCpf.setVisible(true);
                	lblDisciplina.setVisible(true);
                	txtDisciplina.setVisible(true);
                }
                if (tipoUsuario.equals("Coordenador")) {
                	lblRa.setVisible(false);
                	txtRa.setVisible(false);
                	lblCpf.setVisible(true);
                	txtCpf.setVisible(true);
                	lblDisciplina.setVisible(false);
                	txtDisciplina.setVisible(false);
                }
            }
        });
		
		lblRa = new JLabel("RA");
		lblRa.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblRa.setBounds(24, 62, 46, 14);
		jpTipoUsuario.add(lblRa);
		lblRa.setVisible(false);
		
		txtRa = new JTextField();
		txtRa.setEnabled(false);
		txtRa.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		txtRa.setBounds(24, 78, 392, 25);
		jpTipoUsuario.add(txtRa);
		txtRa.setColumns(10);
		txtRa.setVisible(false);
		
		lblCpf = new JLabel("CPF");
		lblCpf.setBounds(24, 62, 46, 14);
		jpTipoUsuario.add(lblCpf);
		lblCpf.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblCpf.setVisible(false);
		
		txtCpf = new JTextField();
		txtCpf.setEnabled(false);
		txtCpf.setBounds(24, 78, 392, 25);
		jpTipoUsuario.add(txtCpf);
		txtCpf.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		txtCpf.setColumns(10);
		txtCpf.setVisible(false);
		
		lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		lblDisciplina.setBounds(24, 118, 66, 14);
		jpTipoUsuario.add(lblDisciplina);
		lblDisciplina.setVisible(false);
		
		txtDisciplina = new JTextField();
		txtDisciplina.setEnabled(false);
		txtDisciplina.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		txtDisciplina.setBounds(24, 136, 392, 25);
		jpTipoUsuario.add(txtDisciplina);
		txtDisciplina.setColumns(10);
		txtDisciplina.setVisible(false);
		
		btnAlterar = new JButton(" Alterar");
		btnAlterar.setIcon(new ImageIcon(FormPerfil.class.getResource("/br/com/riverlandschool/icons/editar24px.png")));
		btnAlterar.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		btnAlterar.setBounds(65, 360, 104, 33);
		contentPane.add(btnAlterar);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setEnabled(true);
        		txtEmail.setEnabled(false);
        		txtSenha.setEnabled(true);
        		comboBoxTipoUsuario.setEnabled(false);
        		txtRa.setEnabled(false);
        		txtCpf.setEnabled(false);
        		txtDisciplina.setEnabled(false);
        		btnAlterar.setEnabled(false);
        		btnCancelar.setEnabled(true);
        		btnSalvar.setEnabled(true);
			}
		});
		
		btnCancelar = new JButton(" Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setIcon(new ImageIcon(FormPerfil.class.getResource("/br/com/riverlandschool/icons/cancelar24px.png")));
		btnCancelar.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		btnCancelar.setBounds(179, 360, 112, 33);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setEnabled(false);
        		txtEmail.setEnabled(false);
        		txtSenha.setEnabled(false);
        		comboBoxTipoUsuario.setEnabled(false);
        		txtRa.setEnabled(false);
        		txtCpf.setEnabled(false);
        		txtDisciplina.setEnabled(false);
        		btnAlterar.setEnabled(true);
        		btnCancelar.setEnabled(false);
        		btnSalvar.setEnabled(false);
			}
		});
		
		btnSalvar = new JButton(" Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.setIcon(new ImageIcon(FormPerfil.class.getResource("/br/com/riverlandschool/icons/salvar24px.png")));
		btnSalvar.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		btnSalvar.setBounds(301, 360, 104, 33);
		contentPane.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					if(preencheUsuario()) {
						if(validaCampos()) {
							if(UsuarioDao.alterarUsuario(obj)) {
								JOptionPane.showMessageDialog(new JFrame(), "Usuário alterado com sucesso!",
	                                    "RIVERLAND SCHOOL", JOptionPane.INFORMATION_MESSAGE);
								txtNome.setEnabled(false);
				        		txtEmail.setEnabled(false);
				        		txtSenha.setEnabled(false);
				        		comboBoxTipoUsuario.setEnabled(false);
				        		txtRa.setEnabled(false);
				        		txtCpf.setEnabled(false);
				        		txtDisciplina.setEnabled(false);
				        		btnAlterar.setEnabled(true);
				        		btnCancelar.setEnabled(false);
				        		btnSalvar.setEnabled(false);
							}
						}
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(new JFrame(), "Erro ao salvar:"+erro.getMessage(),
							"RIVERLAND SCHOOL", JOptionPane.ERROR_MESSAGE);
				}
            }
        });
		
	}
	
    //Método para validar se os campos estão preenchidos
    private boolean validaCampos() {
    	String msg = "";
    	if(txtNome.getText().length()<3 || txtNome.getText().length()>100) {
    		msg+="\nO campo Nome precisa possuir entre 3 a 100 caracteres!";
    	}
    	if(txtEmail.getText().length()<14 || txtEmail.getText().length()>50) {
    		msg+="\nO campo E-mail precisa possuir entre 14 a 50 caracteres!";
    	} else {
    		if(!txtEmail.getText().contains("@riverland.com") ) {
        		msg+="\nO campo E-mail precisa ser @riverland.com";
        	}
    	}
    	if(txtSenha.getText().length()<6 || txtSenha.getText().length()>12) {
    		msg+="\nO campo Senha precisa possuir entre 6 a 12 caracteres!";
    	}
    	if(comboBoxTipoUsuario.getSelectedItem().equals("Selecionar")) {
    		msg+="\nO selecione um tipo de usuário!";
    	}
    	if(comboBoxTipoUsuario.getSelectedItem().equals("Aluno")) {
    		if(txtRa.getText().length()<3 || txtRa.getText().length()>10) {
    			msg+="\nO campo RA precisa possuir entre 3 a 10 caracteres!";
    		}
    	}
    	if(comboBoxTipoUsuario.getSelectedItem().equals("Coordenador")) {
    		if(txtCpf.getText().length()<11 || txtCpf.getText().length()>11) {
    			msg+="\nO campo CPF precisa possuir entre 11 caracteres!";
    		}
    	}
    	if(comboBoxTipoUsuario.getSelectedItem().equals("Professor")) {
    		if(txtCpf.getText().length()<11 || txtCpf.getText().length()>11) {
    			msg+="\nO campo CPF precisa possuir entre 11 caracteres!";
    		}
    		if(txtDisciplina.getText().equals("") || txtDisciplina.getText().equals(null)) {
    			msg+="\nO campo Disciplina precisa possuir ser preenchido!";
    		}
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
    private boolean preencheUsuario() throws Exception{
    	obj.setNome(txtNome.getText());
    	obj.setEmail(txtEmail.getText());
    	obj.setSenha(txtSenha.getText());
    	obj.setTipoUsuario(comboBoxTipoUsuario.getSelectedItem().toString());
    	obj.setRa(txtRa.getText());
    	obj.setCpf(txtCpf.getText());
    	obj.setDisciplina(txtDisciplina.getText());
    	return true;
    }
}
