package br.com.riverlandschool.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;

public class BackupRestoreGUI extends JFrame {
	
	private JButton btnBackup;
	private JButton btnRestore;
	private JProgressBar progressBar;

	public BackupRestoreGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BackupRestoreGUI.class.getResource("/br/com/riverlandschool/icons/icone48px.png")));
		setTitle("RIVERLAND SCHOOL - Backup e Restore");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(365, 111);
		getContentPane().setLayout(new FlowLayout());

		btnBackup = new JButton("Fazer Backup");
		btnBackup.setIcon(new ImageIcon(BackupRestoreGUI.class.getResource("/br/com/riverlandschool/icons/backup24px.png")));
		btnBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fazerBackup();
			}
		});

		btnRestore = new JButton("Fazer Restore");
		btnRestore.setIcon(new ImageIcon(BackupRestoreGUI.class.getResource("/br/com/riverlandschool/icons/restore24px.png")));
		btnRestore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fazerRestore();
			}
		});

		progressBar = new JProgressBar(0, 100);
		progressBar.setStringPainted(true);

		getContentPane().add(btnBackup);
		getContentPane().add(btnRestore);
		getContentPane().add(progressBar);
	}

	private void fazerBackup() {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() throws Exception {
				progressBar.setValue(0);
				progressBar.setIndeterminate(true);

				try {
					// Simulando o processo de backup com uma pausa de 3 segundos
					TimeUnit.SECONDS.sleep(3);

					String caminhoOrigem = "src/br/com/riverlandschool/dao/Riverland.db";
					String caminhoDestino = "C:/backup/Riverland.db";
					File origem = new File(caminhoOrigem);
					File destino = new File(caminhoDestino);

					criarPastaBackup(destino.getParentFile());

					Files.copy(origem.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

					progressBar.setIndeterminate(false);
					progressBar.setValue(100);

					JOptionPane.showMessageDialog(null, "Backup realizado com sucesso!");
				} catch (IOException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao realizar o backup:\n" + ex.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}

				return null;
			}
		};

		worker.execute();
	}

	private void fazerRestore() {
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() throws Exception {
				progressBar.setValue(0);
				progressBar.setIndeterminate(true);

				try {
					// Simulando o processo de restore com uma pausa de 3 segundos
					TimeUnit.SECONDS.sleep(3);

					String caminhoBackup = "C:/backup/Riverland.db";
					String caminhoDestino = "src/br/com/riverlandschool/dao/Riverland.db";
					File backup = new File(caminhoBackup);
					File destino = new File(caminhoDestino);

					// Fechar o arquivo do banco de dados, se estiver aberto
					if (Files.exists(destino.toPath())) {
					    try {
					        Files.delete(destino.toPath());
					    } catch (IOException ex) {
					        ex.printStackTrace();
					        JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo do banco de dados:\n" + ex.getMessage(), "Erro",
					                JOptionPane.ERROR_MESSAGE);
					        return null;
					    }
					}
					
					Files.copy(backup.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

					progressBar.setIndeterminate(false);
					progressBar.setValue(100);

					JOptionPane.showMessageDialog(null, "Restore concluído com sucesso!");
				} catch (IOException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Erro ao realizar o restore:\n" + ex.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
				}

				return null;
			}
		};

		worker.execute();
	}

	private void criarPastaBackup(File pastaBackup) {
		if (!pastaBackup.exists()) {
			try {
				pastaBackup.mkdirs();
				JOptionPane.showMessageDialog(null,
						"Pasta de backup criada com sucesso!\nLocal da pasta: " + pastaBackup.getAbsolutePath(),
						"Pasta de Backup", JOptionPane.INFORMATION_MESSAGE);
			} catch (SecurityException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro ao criar a pasta de backup:\n" + ex.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BackupRestoreGUI frame = new BackupRestoreGUI();
				frame.setVisible(true);
			}
		});
	}
}
