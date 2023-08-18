package br.com.riverlandschool.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class DatabaseBackup {
	

	    public static void fazerBackup() throws IOException {
	        String sistemaOperacional = System.getProperty("os.name").toLowerCase();
	        String caminhoOrigem = "src/br/com/riverlandschool/dao/Riverland.db";
	        String caminhoDestino;

	        if (sistemaOperacional.contains("win")) {
	            // Windows
	            caminhoDestino = "C:/backup/Riverland.db";
	        } else {
	            // Linux
	            caminhoDestino = "/home/usuario/backup/Riverland.db";
	        }

	        File origem = new File(caminhoOrigem);
	        File destino = new File(caminhoDestino);

	        criarPastaBackup(destino.getParentFile());

	        Files.copy(origem.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
	        System.out.println("Backup realizado com sucesso em: " + caminhoDestino);
	    }

	    private static void criarPastaBackup(File pastaBackup) {
	        if (!pastaBackup.exists()) {
	            pastaBackup.mkdirs();
	            System.out.println("Pasta de backup criada em: " + pastaBackup.getAbsolutePath());
	        }
	    }

	    public static void main(String[] args) {
	        try {
	            fazerBackup();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}


//	  public static void fazerBackup() throws IOException {
//	        String sistemaOperacional = System.getProperty("os.name").toLowerCase();
//	        String caminhoOrigem = "src/br/com/riverlandschool/dao/Riverland.db";
//	        String caminhoDestino;
//
//	        if (sistemaOperacional.contains("win")) {
//	            // Windows
//	            caminhoDestino = "C:/backup/Riverland.db";
//	        } else {
//	            // Linux
//	            caminhoDestino = "/home/usuario/backup/Riverland.db";
//	        }
//
//	        File origem = new File(caminhoOrigem);
//	        File destino = new File(caminhoDestino);
//
//	        Files.copy(origem.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
//	        System.out.println("Backup realizado com sucesso em: " + caminhoDestino);
//	    }
//
//	    public static void main(String[] args) {
//	        try {
//	            fazerBackup();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    }
	

