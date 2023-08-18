package br.com.riverlandschool.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorio2 extends JFrame {
    private JRadioButton radioRA;
    private JComboBox<String> comboRA;
    private JRadioButton radioSerie;
    private JComboBox<String> comboSerie;

    public Relatorio2() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorio2.class.getResource("/br/com/riverlandschool/icons/icone48px.png")));
    	getContentPane().setBackground(new Color(255, 255, 255));
        setTitle("RIVERLAND SCHOOL - Relatório");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(342, 230);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 304, 100);
        panel.setBorder(BorderFactory.createTitledBorder("Filtros"));
        getContentPane().add(panel);
        panel.setLayout(null);

        radioRA = new JRadioButton("RA");
        radioRA.setBounds(20, 40, 80, 20);
        radioRA.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        radioRA.setSelected(true);
        panel.add(radioRA);
        radioRA.setVisible(false);

        comboRA = new JComboBox<>();
        comboRA.setBounds(110, 40, 150, 25);
        panel.add(comboRA);
        comboRA.setVisible(false);
        
        radioSerie = new JRadioButton("Série");
        radioSerie.setBounds(20, 40, 80, 20);
        radioSerie.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
        panel.add(radioSerie);

        comboSerie = new JComboBox<>();
        comboSerie.setBounds(110, 39, 150, 25);
        comboSerie.addItem("1º Ano");
        comboSerie.addItem("2º Ano");
        comboSerie.addItem("3º Ano");
        panel.add(comboSerie);

        ButtonGroup group = new ButtonGroup();
        group.add(radioRA);
        group.add(radioSerie);

        comboSerie.setEnabled(false);

        radioRA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboRA.setEnabled(true);
                comboSerie.setEnabled(false);
            }
        });

        radioSerie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboRA.setEnabled(false);
                comboSerie.setEnabled(true);
            }
        });

        JButton btnRelatorio = new JButton(" Gerar Boletim");
        btnRelatorio.setIcon(new ImageIcon(Relatorio2.class.getResource("/br/com/riverlandschool/icons/imprimir24px.png")));
        btnRelatorio.setBounds(94, 133, 130, 30);
        btnRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorio();
            }
        });
        getContentPane().add(btnRelatorio);
    }

    private void gerarRelatorio() {
        try {
            String ra = null;
            String serie = null;
            
            if (radioRA.isSelected()) {
                ra = comboRA.getSelectedItem().toString();
            } else if (radioSerie.isSelected()) {
                serie = comboSerie.getSelectedItem().toString();
            }

            // Carregar o arquivo de relatório
            JasperReport report = JasperCompileManager.compileReport("Relatorio\\Blank_A4_Landscape.jrxml");
//            C:\Users\MACKENZIE\RiverlandSchool\RiverlandSchool\Relatorio
            String query;
            if (ra != null) {
                query = "SELECT * FROM NOTAS WHERE RA_Aluno = '" + ra + "'";
            } else {
                query = "SELECT * FROM NOTAS WHERE Serie = '" + serie + "'";
            }
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("QUERY", query);

            // Conectar ao banco de dados, se necessário
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
            
            // Preencher o relatório
            JasperPrint print = JasperFillManager.fillReport(report, parametros, connection);
            
            // Exibir ou exportar o relatório conforme necessário
            JasperViewer.viewReport(print, false);

            // Verificar se a pasta "C:/Relatorios" existe
            File relatoriosDir = new File("C:/Relatorios");
            if (!relatoriosDir.exists()) {
                // Criar a pasta "C:/Relatorios" se não existir
                relatoriosDir.mkdirs();
                JOptionPane.showMessageDialog(null, "Pasta 'C:/Relatorios' foi criada.");
            }

            // Exportar o relatório em formato PDF para a pasta "C:/Relatorios"
            String relatorioPath = "C:/Relatorios/TESTE.pdf";
            JasperExportManager.exportReportToPdfFile(print, relatorioPath);
            System.out.println("Relatório exportado para: " + relatorioPath);

            // Fechar a conexão, se aplicável
            if (connection != null) {
                connection.close();
            }
        } catch (JRException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void carregarRAAlunos() {
        // Realize a consulta ao banco de dados para obter a lista de RA dos alunos
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT RA_Aluno FROM Notas");

            comboRA.removeAllItems();
            while (resultSet.next()) {
                String ra = resultSet.getString("RA_Aluno");
                comboRA.addItem(ra);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Relatorio2 example = new Relatorio2();
                example.setVisible(true);
                example.carregarRAAlunos();
            }
        });
    }
}

