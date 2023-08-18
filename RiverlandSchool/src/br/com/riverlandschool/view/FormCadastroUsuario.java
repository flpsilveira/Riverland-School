package br.com.riverlandschool.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import br.com.riverlandschool.dao.UsuarioDao;
import br.com.riverlandschool.model.UsuarioModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;

public class FormCadastroUsuario extends JFrame {

	public static JTextField txtNome;
	public static JTextField txtEmail;
	public static JTextField txtSenha;
	public static JTextField txtRa;
	public static JTextField txtCpf;
	public static JTextField txtDisciplina;
	public static JTextField txtPesquisar;
    
	public static JComboBox<String> comboBoxTipoUsuario;
	public static JComboBox<String> comboBoxPesquisar;
    
    private JLabel lblRa;
    private JLabel lblCpf;
    private JLabel lblDisciplina;
    private JLabel lblNome;
    private JLabel lblEmail;
    private JLabel lblSenha;
    
    public static JPanel contentPane;
    private JPanel jpUsuario;
    private JPanel jpTipoUsuario;
    private JPanel jpPesquisar;
    
    private JButton btnVoltar;
    private JButton btnConsulta;
    private JButton btnNovo;
    private JButton btnCancelar;
    private JButton btnLimpar;
    private JButton btnSalvar;
    private JButton btnSalvarAlt;
    private JButton btnAlterar;
    private JButton btnDeletar;
    
    public static ButtonGroup group;
    public static JRadioButton rdbtnNome;
    public static JRadioButton rdbtnRa;
    public static JRadioButton rdbtnCpf;
    public static JRadioButton rdbtnDisciplina;
    
    public JTable table;
    
    private UsuarioModel obj;
    private UsuarioDao dao;
    private boolean listaUsuarios;
    private JLabel lblTipoUsuarioPesquisa;
    public static JComboBox<String> comboBoxSerie;
    
    /**
     * Create the frame.
     */
    public FormCadastroUsuario() {
    	
    	obj = new UsuarioModel();
    	try {
			dao = new UsuarioDao();
		} catch (Exception ex) {
			Logger.getLogger(FormCadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
		}
    	
    	setIconImage(Toolkit.getDefaultToolkit().getImage(FormCadastroUsuario.class.getResource("/br/com/riverlandschool/icons/icone48px.png")));
    	setTitle("RIVERLAND SHOOL - Usuários");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 959, 482);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        btnSalvar = new JButton(" Salvar");
        btnSalvar.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/riverlandschool/icons/salvar24px.png")));
        btnSalvar.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        btnSalvar.setBounds(357, 388, 104, 33);
        contentPane.add(btnSalvar);
        btnSalvar.setEnabled(false);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					if(preencheUsuario()) {
						if(validaCampos()) {
							if(UsuarioDao.incluirUsuario(obj)) {
								JOptionPane.showMessageDialog(new JFrame(), "Usuário cadastrado com sucesso!",
	                                    "RIVERLAND SCHOOL", JOptionPane.INFORMATION_MESSAGE);
								bloquearCampos();
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
        
        btnVoltar = new JButton(" Voltar");
        btnVoltar.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/riverlandschool/icons/voltar24px.png")));
        btnVoltar.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        btnVoltar.setBounds(10, 11, 104, 33);
        contentPane.add(btnVoltar);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual e volta para a janela principal
            }
        });
        
        btnLimpar = new JButton(" Limpar");
        btnLimpar.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/riverlandschool/icons/apagar24px.png")));
        btnLimpar.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        btnLimpar.setBounds(243, 388, 104, 33);
        contentPane.add(btnLimpar);
        btnLimpar.setEnabled(false);
        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
        
        btnCancelar = new JButton(" Cancelar");
        btnCancelar.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/riverlandschool/icons/cancelar24px.png")));
        btnCancelar.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        btnCancelar.setBounds(121, 388, 112, 33);
        contentPane.add(btnCancelar);
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		bloquearCampos();
        		limparCampos();
        	}
        });
        
        btnNovo = new JButton(" Novo");
        btnNovo.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/riverlandschool/icons/adicionar24px.png")));
        btnNovo.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        btnNovo.setBounds(10, 388, 104, 33);
        contentPane.add(btnNovo);
        btnNovo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		txtNome.setEnabled(true);
        		txtEmail.setEnabled(true);
        		txtSenha.setEnabled(true);
        		comboBoxTipoUsuario.setEnabled(true);
        		comboBoxSerie.setEnabled(true);
        		comboBoxPesquisar.setEnabled(false);
        		txtRa.setEnabled(true);
        		txtCpf.setEnabled(true);
        		txtDisciplina.setEnabled(true);
        		btnNovo.setEnabled(false);
        		btnCancelar.setEnabled(true);
        		btnLimpar.setEnabled(true);
        		btnSalvar.setEnabled(true);
        		btnSalvarAlt.setEnabled(false);
        		btnAlterar.setEnabled(false);
        		btnDeletar.setEnabled(false);
        		limparCampos();
        	}
        });
        
        jpUsuario = new JPanel(null);
        jpUsuario.setBackground(new Color(255, 255, 255));
        jpUsuario.setBounds(10, 55, 451, 145);
        contentPane.add(jpUsuario);       
        jpUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(
        		null, "Usuário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
        		javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", Font.PLAIN, 14)));
        
        lblNome = new JLabel("Nome completo");
        lblNome.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        lblNome.setBounds(24, 29, 125, 14);
        jpUsuario.add(lblNome);
                
        txtNome = new JTextField();
        txtNome.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        txtNome.setBounds(24, 48, 392, 25);
        jpUsuario.add(txtNome);
        txtNome.setColumns(10);
        txtNome.setEnabled(false);
                        
        lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        lblEmail.setBounds(24, 79, 46, 14);
        jpUsuario.add(lblEmail);
        
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        txtEmail.setBounds(24, 97, 191, 25);
        jpUsuario.add(txtEmail);
        txtEmail.setColumns(10);
        txtEmail.setEnabled(false);
        
        lblSenha = new JLabel("Senha");
        lblNome.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        lblSenha.setBounds(225, 80, 46, 14);
        jpUsuario.add(lblSenha);
        
	    txtSenha = new JPasswordField();
	    txtSenha.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
	    txtSenha.setBounds(225, 97, 191, 25);
	    jpUsuario.add(txtSenha);
	    txtSenha.setColumns(10);
	    txtSenha.setEnabled(false);
        
        jpTipoUsuario = new JPanel(null);
        jpTipoUsuario.setBackground(new Color(255, 255, 255));
        jpTipoUsuario.setBounds(10, 211, 451, 166);
        contentPane.add(jpTipoUsuario);
        jpTipoUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(
        		null, "Tipo Usuário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
        		javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", Font.PLAIN, 14)));
                
        comboBoxTipoUsuario = new JComboBox<String>();
        comboBoxTipoUsuario.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        comboBoxTipoUsuario.setBounds(24, 26, 166, 25);
        jpTipoUsuario.add(comboBoxTipoUsuario);
        comboBoxTipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"Selecionar", "Aluno", "Professor", "Coordenador"}));
        comboBoxTipoUsuario.setEnabled(false);
        comboBoxTipoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tipoUsuario = comboBoxTipoUsuario.getSelectedItem().toString();
                if(tipoUsuario.equals("Selecionar")) {
                	comboBoxSerie.setVisible(false);
                	lblRa.setVisible(false);
                	txtRa.setVisible(false);
                	lblCpf.setVisible(false);
                	txtCpf.setVisible(false);
                	lblDisciplina.setVisible(false);
                	txtDisciplina.setVisible(false);
                	comboBoxSerie.setVisible(false);
                }
                if (tipoUsuario.equals("Aluno")) {
                	comboBoxSerie.setVisible(true);
                	lblRa.setVisible(true);
                	txtRa.setVisible(true);
                	lblCpf.setVisible(false);
                	txtCpf.setVisible(false);
                	lblDisciplina.setVisible(false);
                	txtDisciplina.setVisible(false);
                	comboBoxSerie.setVisible(true);
                }
                if (tipoUsuario.equals("Professor")) {
                	comboBoxSerie.setVisible(false);
                	lblRa.setVisible(false);
                	txtRa.setVisible(false);
                	lblCpf.setVisible(true);
                	txtCpf.setVisible(true);
                	lblDisciplina.setVisible(true);
                	txtDisciplina.setVisible(true);
                	comboBoxSerie.setVisible(false);
                }
                if (tipoUsuario.equals("Coordenador")) {
                	comboBoxSerie.setVisible(false);
                	lblRa.setVisible(false);
                	txtRa.setVisible(false);
                	lblCpf.setVisible(true);
                	txtCpf.setVisible(true);
                	lblDisciplina.setVisible(false);
                	txtDisciplina.setVisible(false);
                	comboBoxSerie.setVisible(false);
                }
            }
        });

        
        lblRa = new JLabel("RA");
        lblRa.setBounds(24, 57, 46, 14);
        jpTipoUsuario.add(lblRa);
        lblRa.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        lblRa.setVisible(false);
        
        txtRa = new JTextField();
        txtRa.setEnabled(false);
        txtRa.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        txtRa.setBounds(24, 73, 392, 25);
        jpTipoUsuario.add(txtRa);
        txtRa.setColumns(10);
        txtRa.setVisible(false);
        
        lblCpf = new JLabel("CPF");
        lblCpf.setBounds(24, 57, 33, 14);
        jpTipoUsuario.add(lblCpf);
        lblCpf.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        lblCpf.setVisible(false);
        
        txtCpf = new JTextField();
        txtCpf.setEnabled(false);
        txtCpf.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        txtCpf.setBounds(24, 74, 392, 20);
        jpTipoUsuario.add(txtCpf);
        txtCpf.setColumns(10);
        txtCpf.setVisible(false);
        
        lblDisciplina = new JLabel("Disciplina");
        lblDisciplina.setBounds(24, 105, 66, 14);
        jpTipoUsuario.add(lblDisciplina);
        lblDisciplina.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        lblDisciplina.setVisible(false);
        
        txtDisciplina = new JTextField();
        txtDisciplina.setEnabled(false);
        txtDisciplina.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        txtDisciplina.setBounds(24, 122, 392, 25);
        jpTipoUsuario.add(txtDisciplina);
        txtDisciplina.setColumns(10);
        txtDisciplina.setVisible(false);

        comboBoxSerie = new JComboBox();
        comboBoxSerie.setEnabled(false);
        comboBoxSerie.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        comboBoxSerie.setModel(new DefaultComboBoxModel(new String[] {"Selecionar", "1º Ano", "2º Ano", "3º Ano"}));
        comboBoxSerie.setBounds(250, 26, 166, 25);
        jpTipoUsuario.add(comboBoxSerie);
        comboBoxSerie.setVisible(false);
        
        jpPesquisar = new JPanel(null);
        jpPesquisar.setBounds(478, 11, 451, 415);
        jpPesquisar.setBorder(javax.swing.BorderFactory.createTitledBorder(
        		null, "Pesquisar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
        		javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", Font.PLAIN, 14)));
        contentPane.add(jpPesquisar);
        
        lblTipoUsuarioPesquisa = new JLabel("Tipo Usuario");
        lblTipoUsuarioPesquisa.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        lblTipoUsuarioPesquisa.setBounds(22, 28, 78, 14);
        jpPesquisar.add(lblTipoUsuarioPesquisa);
        
        comboBoxPesquisar = new JComboBox();
        comboBoxPesquisar.setBounds(22, 45, 172, 24);
        jpPesquisar.add(comboBoxPesquisar);
        comboBoxPesquisar.setModel(new DefaultComboBoxModel(new String[] {"Selecionar", "Aluno", "Professor", "Coordenador"}));
        comboBoxPesquisar.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        comboBoxPesquisar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		limparCampos();
        		bloquearCampos();
        		try {
        			String selectedValue = comboBoxPesquisar.getSelectedItem().toString();
            		if(!comboBoxPesquisar.getSelectedItem().equals("Selecionar")) {
            			if(comboBoxPesquisar.getSelectedItem().equals("Aluno")) {
            				rdbtnNome.setVisible(false);
            				rdbtnRa.setVisible(false);
            				rdbtnCpf.setVisible(false);
            				rdbtnDisciplina.setVisible(false);
            			}
        				if(comboBoxPesquisar.getSelectedItem().equals("Coordenador")) {
        					rdbtnNome.setVisible(false);
            				rdbtnRa.setVisible(false);
            				rdbtnCpf.setVisible(false);
            				rdbtnDisciplina.setVisible(false);
            			}
        				if(comboBoxPesquisar.getSelectedItem().equals("Professor")) {
        					rdbtnNome.setVisible(false);
            				rdbtnRa.setVisible(false);
            				rdbtnCpf.setVisible(false);
            				rdbtnDisciplina.setVisible(false);
            			}
                		
        				UsuarioDao usuarioDao = new UsuarioDao();
        				ArrayList<UsuarioModel> listaUsuarios = usuarioDao.getUsuario(selectedValue);
                        DefaultTableModel mp = (DefaultTableModel) table.getModel();
                        mp.setNumRows(0);
                        for (UsuarioModel u : listaUsuarios) {
                            Object[] rowData = { u.getNome(), u.getEmail() };
                            mp.addRow(rowData);
                        }
            		} else {
            			rdbtnNome.setVisible(false);
        				rdbtnRa.setVisible(false);
        				rdbtnCpf.setVisible(false);
        				rdbtnDisciplina.setVisible(false);
            		}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(new JFrame(), "Erro ao salvar:"+erro.getMessage(),
							"RIVERLAND SCHOOL", JOptionPane.ERROR_MESSAGE);
				}
        	}
        });

        btnAlterar = new JButton(" Alterar");
        btnAlterar.setBounds(60, 373, 104, 33);
        jpPesquisar.add(btnAlterar);
        btnAlterar.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/riverlandschool/icons/editar24px.png")));
        btnAlterar.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        btnAlterar.setEnabled(false);
        btnAlterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtNome.setEnabled(true);
        		txtEmail.setEnabled(true);
        		txtSenha.setEnabled(true);
        		comboBoxTipoUsuario.setEnabled(false);
        		comboBoxSerie.setEnabled(true);
        		comboBoxPesquisar.setEnabled(false);
        		txtRa.setEnabled(false);
        		txtCpf.setEnabled(false);
        		txtDisciplina.setEnabled(true);
        		btnNovo.setEnabled(false);
        		btnCancelar.setEnabled(true);
        		btnLimpar.setEnabled(false);
        		btnSalvar.setEnabled(false);
        		btnSalvarAlt.setEnabled(true);
        		btnAlterar.setEnabled(false);
        		btnDeletar.setEnabled(false);
            }
        });
        
        btnSalvarAlt = new JButton(" Salvar");
        btnSalvarAlt.setBounds(174, 373, 104, 33);
        jpPesquisar.add(btnSalvarAlt);
        btnSalvarAlt.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/riverlandschool/icons/salvar24px.png")));
        btnSalvarAlt.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        btnSalvarAlt.setEnabled(false);
        btnSalvarAlt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					if(preencheUsuario()) {
						if(validaCampos()) {
							if(UsuarioDao.alterarUsuario(obj)) {
								JOptionPane.showMessageDialog(new JFrame(), "Usuário alterado com sucesso!",
	                                    "RIVERLAND SCHOOL", JOptionPane.INFORMATION_MESSAGE);
								limparCampos();
								bloquearCampos();
							}
						}
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(new JFrame(), "Erro ao salvar:"+erro.getMessage(),
							"RIVERLAND SCHOOL", JOptionPane.ERROR_MESSAGE);
				}
            }
        });
        
        btnDeletar = new JButton(" Excluir");
        btnDeletar.setBounds(288, 373, 104, 33);
        jpPesquisar.add(btnDeletar);
        btnDeletar.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/riverlandschool/icons/lixeira24px.png")));
        btnDeletar.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        btnDeletar.setEnabled(false);
        btnDeletar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (UsuarioDao.excluirUsuario()) {
                        bloquearCampos();
                        limparCampos();
                        JOptionPane.showMessageDialog(new JFrame(), "Registro excluído com sucesso!",
                                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(new JFrame(), "Erro ao excluir: " + erro.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
        txtPesquisar = new JTextField();
        txtPesquisar.setEnabled(false);
        txtPesquisar.setVisible(false);
        txtPesquisar.setBounds(22, 80, 370, 24);
        jpPesquisar.add(txtPesquisar);
        txtPesquisar.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        txtPesquisar.setColumns(10);
        
        btnConsulta = new JButton("");
        btnConsulta.setEnabled(false);
        btnConsulta.setVisible(false);
        btnConsulta.setBounds(402, 76, 37, 33);
        jpPesquisar.add(btnConsulta);
        btnConsulta.setIcon(new ImageIcon(FormCadastroUsuario.class.getResource("/br/com/riverlandschool/icons/pesquisar24px.png")));
        btnConsulta.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        btnConsulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	String selectedValue = comboBoxPesquisar.getSelectedItem().toString();
                	String selectedRdb = group.getSelection().toString();
                	UsuarioDao usuarioDao = new UsuarioDao();
    				ArrayList<UsuarioModel> listaUsuarios = usuarioDao.pesquisarUsuario(selectedValue, selectedRdb);
                    DefaultTableModel mp = (DefaultTableModel) table.getModel();
                    mp.setNumRows(0);
                    for (UsuarioModel u : listaUsuarios) {
                        Object[] rowData = { u.getNome(), u.getEmail() };
                        mp.addRow(rowData);
                    }
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(new JFrame(), "Erro ao alterar:"+erro.getMessage(),
							"RIVERLAND SCHOOL", JOptionPane.ERROR_MESSAGE);
				}
            }
        });
        
        rdbtnNome = new JRadioButton("Nome");
        rdbtnNome.setEnabled(false);
        rdbtnNome.setVisible(false);
        rdbtnNome.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        rdbtnNome.setBounds(200, 45, 57, 24);
        jpPesquisar.add(rdbtnNome);
        rdbtnNome.setVisible(false);
        
        rdbtnRa = new JRadioButton("RA");
        rdbtnRa.setEnabled(false);
        rdbtnRa.setVisible(false);
        rdbtnRa.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        rdbtnRa.setBounds(259, 45, 48, 24);
        jpPesquisar.add(rdbtnRa);
        rdbtnRa.setVisible(false);
        
        rdbtnCpf = new JRadioButton("CPF");
        rdbtnCpf.setEnabled(false);
        rdbtnCpf.setVisible(false);
        rdbtnCpf.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        rdbtnCpf.setBounds(259, 45, 48, 24);
        jpPesquisar.add(rdbtnCpf);
        rdbtnCpf.setVisible(false);
        
        rdbtnDisciplina = new JRadioButton("Disciplina");
        rdbtnDisciplina.setEnabled(false);
        rdbtnDisciplina.setVisible(false);
        rdbtnDisciplina.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        rdbtnDisciplina.setBounds(309, 45, 75, 24);
        jpPesquisar.add(rdbtnDisciplina);
        rdbtnDisciplina.setVisible(false);
        
        group = new ButtonGroup();
        group.add(rdbtnNome);
        group.add(rdbtnRa);
        group.add(rdbtnCpf);
        group.add(rdbtnDisciplina);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(22, 80, 412, 282);
        jpPesquisar.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        table.setFillsViewportHeight(true);
        table.setForeground(new Color(0, 0, 0));
        table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        table.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        table.setBackground(new Color(240, 240, 240));
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Nome", "E-mail"
        	}
        ));
        table.getColumnModel().getColumn(0).setPreferredWidth(300);
        table.getColumnModel().getColumn(0).setMinWidth(25);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setMinWidth(25);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linhaSelecionada = table.getSelectedRow();
                if (linhaSelecionada > -1) {
                    ArrayList<UsuarioModel> listaUsuarios;
                    try {
                        UsuarioDao usuarioDao = new UsuarioDao(); // Crie uma instância de UsuarioDao
                        String selectedValue = comboBoxPesquisar.getSelectedItem().toString(); // Obtenha o valor selecionado corretamente
                        listaUsuarios = usuarioDao.getUsuario(selectedValue);
                        if (listaUsuarios != null && linhaSelecionada < listaUsuarios.size()) {
                            UsuarioModel usuario = listaUsuarios.get(linhaSelecionada);
                            txtNome.setText(usuario.getNome());
                            txtEmail.setText(usuario.getEmail());
                            txtSenha.setText(usuario.getSenha());
                            comboBoxTipoUsuario.setSelectedItem(usuario.getTipoUsuario());
                            txtRa.setText(usuario.getRa());
                            txtCpf.setText(usuario.getCpf());
                            txtDisciplina.setText(usuario.getDisciplina());
                            comboBoxSerie.setSelectedItem(usuario.getSerie());
                            bloquearCampos();
                            btnAlterar.setEnabled(true);
                            btnDeletar.setEnabled(true);
                        } else {
                            btnAlterar.setEnabled(false);
                            btnDeletar.setEnabled(false);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
    }
    
    // Método para limpar os campos do formulário
    private void limparCampos() {
        txtNome.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        comboBoxTipoUsuario.setSelectedItem("Selecionar");
        txtRa.setText("");
        txtCpf.setText("");
        txtDisciplina.setText("");
        comboBoxSerie.setSelectedItem("Selecionar");

    }
    
    public void bloquearCampos() {
    	txtNome.setEnabled(false);
		txtEmail.setEnabled(false);
		txtSenha.setEnabled(false);
		comboBoxTipoUsuario.setEnabled(false);
		comboBoxSerie.setEnabled(false);
		comboBoxPesquisar.setEnabled(true);
		txtRa.setEnabled(false);
		txtCpf.setEnabled(false);
		txtDisciplina.setEnabled(false);
		btnNovo.setEnabled(true);
		btnCancelar.setEnabled(false);
		btnLimpar.setEnabled(false);
		btnSalvar.setEnabled(false);
		btnSalvarAlt.setEnabled(false);
		btnAlterar.setEnabled(false);
		btnDeletar.setEnabled(false);
    }
    
    //Método para validar se os campos estão prenechidos
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
    	if(obj.getTipoUsuario().equals("Aluno")) {
    	obj.setSerie(comboBoxSerie.getSelectedItem().toString());
    	}
    	return true;
    }
}

