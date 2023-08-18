package br.com.riverlandschool.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.Font;
import java.awt.Toolkit;

public class Relatorio extends JFrame {
    public Relatorio() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorio.class.getResource("/br/com/riverlandschool/icons/icone48px.png")));
        setTitle("RIVERLAND SCHOOL - Gerar Boletim");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(397, 193);
        setLocationRelativeTo(null);

        JButton btnRelatorio = new JButton("Gerar Relatório");
        btnRelatorio.setIcon(new ImageIcon(Relatorio.class.getResource("/br/com/riverlandschool/icons/imprimir24px.png")));
        btnRelatorio.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
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
        	
        	String ra = FormLoginEscola.getRaAluno();//
        	
//        	String ra = "123654456";
            // Carregar o arquivo de relatório
            JasperReport report = JasperCompileManager.compileReport("Relatorio\\Blank_A4_Landscape.jrxml");
//          C:\Users\MACKENZIE\RiverlandSchool\RiverlandSchool\Relatorio

            String query = "SELECT * FROM NOTAS WHERE RA_Aluno = " + "'" + ra + "'";
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
                System.out.println("Pasta 'C:/Relatorios' foi criada.");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Relatorio example = new Relatorio();
                example.setVisible(true);
            }
        });
    }
}
