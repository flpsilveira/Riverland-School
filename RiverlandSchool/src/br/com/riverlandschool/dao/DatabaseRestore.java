package br.com.riverlandschool.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class DatabaseRestore {
	
	public static void fazerRestore() throws IOException {
        String sistemaOperacional = System.getProperty("os.name").toLowerCase();
        String caminhoBackup;

        if (sistemaOperacional.contains("win")) {
            // Windows
            caminhoBackup = "C:/backup/Riverland.db";
        } else {
            // Linux
            caminhoBackup = "/home/usuario/backup/Riverland.db";
        }

        String caminhoDestino = "src/br/com/riverlandschool/dao/Riverland.db";
        File backup = new File(caminhoBackup);
        File destino = new File(caminhoDestino);

        Files.copy(backup.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Restauração concluída com sucesso");
    }

    public static void main(String[] args) {
        try {
            fazerRestore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
