//package br.com.riverlandschool.controller;
//
//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.view.JasperViewer;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class GeraRelatorio {
//    public static void main(String[] args) {
//        // Construir a consulta desejada
//        String consulta = "SELECT * FROM tabela";
//
//        // Carregar o arquivo de relatório
//        try {
//            JasperReport report = JasperCompileManager.compileReport("C:/Relatorios");
//
//            // Criar um mapa de parâmetros
//            Map<String, Object> parametros = new HashMap<>();
//            parametros.put("consultaParametro", consulta);
//
//            // Conectar ao banco de dados, se necessário
//            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/br/com/riverlandschool/dao/Riverland.db");
//
//            // Preencher o relatório
//            JasperPrint print = JasperFillManager.fillReport(report, parametros, connection);
//
//            // Exibir ou exportar o relatório conforme necessário
//            JasperViewer.viewReport(print);
//            // ou
//            JasperExportManager.exportReportToPdfFile(print, "C:/Relatorios");
//
//            // Fechar a conexão, se aplicável
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (JRException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
