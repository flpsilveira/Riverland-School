package br.com.riverlandschool.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FormLoginEscola {

    private JFrame frmLogin;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;

    private static String nomeProfessor;
    private static String disciplinaProfessor;
    private static String raAluno;
    private static String cpf;


    
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
                    FormLoginEscola window = new FormLoginEscola();
                    window.frmLogin.setLocationRelativeTo(null);	//Abre o JFrame no meio da tela
                    window.frmLogin.setResizable(false); 			//Trava o tamanho da tela, não deixa maximizar
                    window.frmLogin.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public FormLoginEscola() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
    	frmLogin = new JFrame();
    	frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(FormLoginEscola.class.getResource("/br/com/riverlandschool/icons/icone48px.png")));
        frmLogin.setTitle("RIVERLAND SCHOOL - Login");
        frmLogin.getContentPane().setBackground(new Color(255, 255, 255));
        frmLogin.setBounds(100, 100, 531, 366);
        frmLogin.getContentPane().setLayout(null);

        JLabel lblUsuario = new JLabel("Usuário");
        lblUsuario.setFont(new Font("Century", Font.BOLD, 16));
        lblUsuario.setBounds(252, 75, 89, 32);
        frmLogin.getContentPane().add(lblUsuario);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setFont(new Font("Century", Font.BOLD, 16));
        lblSenha.setBounds(252, 141, 89, 14);
        frmLogin.getContentPane().add(lblSenha);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Cambria", Font.PLAIN, 16));
        txtUsuario.setBounds(252, 104, 230, 30);
        frmLogin.getContentPane().add(txtUsuario);
        txtUsuario.setColumns(10);

        txtSenha = new JPasswordField();
        txtSenha.setFont(new Font("Cambria", Font.PLAIN, 16));
        txtSenha.setBounds(252, 166, 230, 30);
        frmLogin.getContentPane().add(txtSenha);

        JButton btnEntrar = new JButton("");
        btnEntrar.setIcon(new ImageIcon(FormLoginEscola.class.getResource("/br/com/riverlandschool/icons/btnEntrar.png")));
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = txtUsuario.getText();
                String senha = new String(txtSenha.getPassword());

                if (isValidUser(email, senha)) {
                    if (isUserCoordenador(email)) {
                        FormPrincipal principal = new FormPrincipal();
                        principal.setVisible(true);
                        FormPrincipal.mnPerfil.setVisible(true);
                        FormPrincipal.mntmMeuPerfil.setVisible(true);
                        FormPrincipal.mntmUsuarios.setVisible(true);
                        FormPrincipal.mnGestaoDisciplinas.setVisible(false);
                        FormPrincipal.mntmAulas.setVisible(false);
                        FormPrincipal.mntmNotas.setVisible(false);
                        FormPrincipal.mnRelatorio.setVisible(true);
                        FormPrincipal.mntmBoletimAluno.setVisible(false);
                        FormPrincipal.mntmBoletim.setVisible(true);
                        FormPrincipal.mnBackup.setVisible(true);
                        FormPrincipal.mntmFazerBackup.setVisible(true);
                    } else if (isUserProfessor(email)) {
                    	FormPrincipal principal = new FormPrincipal();
                        principal.setVisible(true);
                        FormPrincipal.mnPerfil.setVisible(true);
                        FormPrincipal.mntmMeuPerfil.setVisible(true);
                        FormPrincipal.mntmUsuarios.setVisible(false);
                        FormPrincipal.mnGestaoDisciplinas.setVisible(true);
                        FormPrincipal.mntmAulas.setVisible(true);
                        FormPrincipal.mntmNotas.setVisible(true);
                        FormPrincipal.mnRelatorio.setVisible(true);
                        FormPrincipal.mntmBoletimAluno.setVisible(false);
                        FormPrincipal.mntmBoletim.setVisible(true);
                        FormPrincipal.mnBackup.setVisible(false);
                        FormPrincipal.mntmFazerBackup.setVisible(false);
                    } else if (isUserAluno(email)) {
                    	FormPrincipal principal = new FormPrincipal();
                        principal.setVisible(true);
                        FormPrincipal.mnPerfil.setVisible(true);
                        FormPrincipal.mntmMeuPerfil.setVisible(true);
                        FormPrincipal.mntmUsuarios.setVisible(false);
                        FormPrincipal.mnGestaoDisciplinas.setVisible(false);
                        FormPrincipal.mntmAulas.setVisible(false);
                        FormPrincipal.mntmNotas.setVisible(false);
                        FormPrincipal.mnRelatorio.setVisible(true);
                        FormPrincipal.mntmBoletimAluno.setVisible(true);
                        FormPrincipal.mntmBoletim.setVisible(false);
                        FormPrincipal.mnBackup.setVisible(false);
                        FormPrincipal.mntmFazerBackup.setVisible(false);
                    }
                } else {
                    System.out.println("Usuário e/ou senha inválidos");
                }
            }
        });
        btnEntrar.setBounds(307, 220, 110, 30);
        frmLogin.getContentPane().add(btnEntrar);
        
        JPanel panelCinza = new JPanel();
        panelCinza.setBounds(201, 0, 315, 327);
        frmLogin.getContentPane().add(panelCinza);
        
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(FormLoginEscola.class.getResource("/br/com/riverlandschool/icons/logo196px.png")));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setBounds(0, 58, 202, 192);
        frmLogin.getContentPane().add(lblLogo); 
    }

    private boolean isValidUser(String email, String senha) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean isValid = false;
        try {
            // Abrir conexão com o banco de dados
            conn = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");

            //
            // Preparar consulta SQL
            String sql = "SELECT COUNT(*) FROM USUARIOS WHERE EMAIL = ? AND SENHA = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            // Executar consulta SQL e obter resultado
            rs = stmt.executeQuery();
            int count = rs.getInt(1);

            // Verificar se há exatamente um usuário com as credenciais fornecidas
            isValid = (count == 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar conexões, stmt e rs
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!isValid) {
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos", "Erro de autenticação", JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    private boolean isUserCoordenador(String email) {
        boolean isCoordenador = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
            String sql = "SELECT TIPO_USUARIO, CPF FROM USUARIOS WHERE EMAIL = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String tipoUsuario = rs.getString("TIPO_USUARIO");
                isCoordenador = tipoUsuario.equalsIgnoreCase("Coordenador");
            }
            if (isCoordenador) {
                cpf = rs.getString("CPF");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return isCoordenador;
    }

    private boolean isUserProfessor(String email) {
        boolean isProfessor = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
            String sql = "SELECT TIPO_USUARIO, NOME, DISCIPLINA, CPF FROM USUARIOS WHERE EMAIL = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String tipoUsuario = rs.getString("TIPO_USUARIO");
                isProfessor = tipoUsuario.equalsIgnoreCase("Professor");
                
                if (isProfessor) {
                    nomeProfessor = rs.getString("NOME");
                    disciplinaProfessor = rs.getString("DISCIPLINA");
                    cpf = rs.getString("CPF");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null)
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return isProfessor;
            }

            private boolean isUserAluno(String email) {
                boolean isAluno = false;
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                try {
                    conn = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
                    String sql = "SELECT TIPO_USUARIO, RA FROM USUARIOS WHERE EMAIL = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, email);
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        String tipoUsuario = rs.getString("TIPO_USUARIO");
                        isAluno = tipoUsuario.equalsIgnoreCase("Aluno");
                    }
                    if (isAluno) {
                        raAluno = rs.getString("RA");
                    }
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return isAluno;
            }
            
            public static String getNomeProfessor() {
                return nomeProfessor;
            }

            public static String getDisciplinaProfessor() {
                return disciplinaProfessor;
            }
            
            public static String getRaAluno() {
                return raAluno;
            }
            
            public static String getCpf() {
                return cpf;
            }
        }
