package main.view;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import main.logic.FileManager;
import main.pojo.Match;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPanel {
	private JPanel panel = null;
	private FileManager fileManager = null;
	private JTextField localTeamTxt;
	private JTextField rivalTeamTxt;
	private JTextField localGoalsTxt;
	private JTextField rivalGoalsTxt;
	private JTextField matchPlaceTxt;
	private JTextField matchDateTxt;
	private DefaultTableModel tableModel = null;
	public JTable messageTable = null;
	ArrayList<Match> loadedMatches = null;

	public MainPanel(ArrayList<JPanel> pannels, ArrayList<Match> matches) {

		panel = new JPanel();
		panel.setBounds(0, 0, 500, 700);

		fileManager = new FileManager();
		panel.setLayout(null);

		localTeamTxt = new JTextField();
		localTeamTxt.setBounds(173, 37, 225, 23);
		panel.add(localTeamTxt);
		localTeamTxt.setColumns(10);

		rivalTeamTxt = new JTextField();
		rivalTeamTxt.setColumns(10);
		rivalTeamTxt.setBounds(173, 75, 225, 23);
		panel.add(rivalTeamTxt);

		localGoalsTxt = new JTextField();
		localGoalsTxt.setColumns(10);
		localGoalsTxt.setBounds(173, 119, 225, 23);
		panel.add(localGoalsTxt);

		rivalGoalsTxt = new JTextField();
		rivalGoalsTxt.setColumns(10);
		rivalGoalsTxt.setBounds(173, 157, 225, 23);
		panel.add(rivalGoalsTxt);

		matchPlaceTxt = new JTextField();
		matchPlaceTxt.setColumns(10);
		matchPlaceTxt.setBounds(173, 198, 225, 23);
		panel.add(matchPlaceTxt);

		matchDateTxt = new JTextField();
		matchDateTxt.setColumns(10);
		matchDateTxt.setBounds(173, 236, 225, 23);
		panel.add(matchDateTxt);

		JLabel lblLocalTeam = new JLabel("Equipo local");
		lblLocalTeam.setBounds(32, 41, 116, 19);
		panel.add(lblLocalTeam);

		JLabel lblVisitorTeam = new JLabel("Equipo visitante");
		lblVisitorTeam.setBounds(32, 79, 116, 19);
		panel.add(lblVisitorTeam);

		JLabel lblLocalGoals = new JLabel("Goles local");
		lblLocalGoals.setBounds(32, 123, 116, 19);
		panel.add(lblLocalGoals);

		JLabel lblVisitorGoals = new JLabel("Goles Visitantes");
		lblVisitorGoals.setBounds(32, 161, 116, 19);
		panel.add(lblVisitorGoals);

		JLabel lblMatchPlace = new JLabel("Lugar");
		lblMatchPlace.setBounds(32, 198, 116, 19);
		panel.add(lblMatchPlace);

		JLabel lblMatchDate = new JLabel("Fecha");
		lblMatchDate.setBounds(32, 236, 116, 19);
		panel.add(lblMatchDate);

		tableModel = new DefaultTableModel();
		tableModel.addColumn("Local");
		tableModel.addColumn("Visitante");
		tableModel.addColumn("GolesLoc");
		tableModel.addColumn("GolesVis");
		tableModel.addColumn("Lugar");
		tableModel.addColumn("Fecha");
		messageTable = new JTable(tableModel);
		messageTable.setBounds(0, 0, 690, 220);
		panel.add(messageTable);

		JScrollPane scrollPane = new JScrollPane(messageTable);
		scrollPane.setLocation(0, 330);
		scrollPane.setSize(485, 369);
		panel.add(scrollPane);

		JButton saveButton = new JButton("Guardar");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fileManager.writeMatches(matches);
					JOptionPane.showMessageDialog(null, "Se han guardado los mensajes", "OK!",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo", "ERROR!",
							JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error en la escritura del archivo", "ERROR!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		saveButton.setBounds(300, 296, 98, 23);
		panel.add(saveButton);

		JButton addBtn = new JButton("Añadir");
		addBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String localTeam = localTeamTxt.getText();
				String rivalTeam = rivalTeamTxt.getText();
				String matchPlace = matchPlaceTxt.getText();
				String matchDate = matchDateTxt.getText();
				if ((localTeam.length() < 1 || localTeam.length() > 20) && localTeam.isBlank() != true) {
					JOptionPane.showMessageDialog(null, "El equipo local debe tener entre 1 y 20 caracteres.", "ERROR!",
							JOptionPane.ERROR_MESSAGE);
					eraseTextFields();
				}
				if ((rivalTeam.length() < 1 || rivalTeam.length() > 20) && rivalTeam.isBlank() != true) {
					JOptionPane.showMessageDialog(null, "El equipo visitante debe tener entre 1 y 20 caracteres.",
							"ERROR!", JOptionPane.ERROR_MESSAGE);
					eraseTextFields();
				}
				if ((matchPlace.length() < 1 || matchPlace.length() > 20) && matchPlace.isBlank() != true) {
					JOptionPane.showMessageDialog(null, "El lugar debe tener entre 1 y 20 caracteres", "ERROR!",
							JOptionPane.ERROR_MESSAGE);
					eraseTextFields();
				}
				if ((!matchDate.matches("\\d{2}/\\d{2}/\\d{2}")) && matchDate.isBlank() != true) {
					JOptionPane.showMessageDialog(null,
							"La fecha ha sido mal escrita, por favor, intentelo de nuevo (formato dd/MM/yy)", "ERROR!",
							JOptionPane.ERROR_MESSAGE);
					eraseTextFields();
				}
				try {
					int localGoals = Integer.parseInt(localGoalsTxt.getText());
					int rivalGoals = Integer.parseInt(rivalGoalsTxt.getText());
					if (localGoals < 0 || localGoals > 99 || rivalGoals < 0 || rivalGoals > 99) {
						JOptionPane.showMessageDialog(null,
								"Los numeros deben de ser de uno a dos digitos, por favor, intentelo de nuevo",
								"ERROR!", JOptionPane.ERROR_MESSAGE);
						eraseTextFields();
					}
					Match match = new Match(localTeam, rivalTeam, localGoals, rivalGoals, matchPlace, matchDate);
					matches.add(match);
					eraseTextFields();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Ha habido un error escribiendo el partido", "ERROR!",
							JOptionPane.INFORMATION_MESSAGE);
					eraseTextFields();
				}
			}
		});
		addBtn.setBounds(50, 296, 98, 23);
		panel.add(addBtn);

		JButton loadButton = new JButton("Cargar");
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<Match> loadedMatches = fileManager.loadMatches();
					if (loadedMatches != null && !loadedMatches.isEmpty()) {
						matches.clear();
						
						matches.addAll(loadedMatches);

						readMatches(matches, tableModel);

						JOptionPane.showMessageDialog(null, "Se han cargado los mensajes", "OK!!",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "No hay mensajes para cargar", "Aviso",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error en la lectura del archivo", "ERROR!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		loadButton.setBounds(173, 296, 98, 23);
		panel.add(loadButton);
	}

	public JPanel getPanel() {
		return panel;
	}

	private void readMatches(ArrayList<Match> matches, DefaultTableModel model) {
		model.setRowCount(0);
		if (matches != null) {
			for (Match match : matches) {
				Object[] row = { match.getLocalTeam(), match.getRivalTeam(), match.getLocalGoals(),
						match.getRivalGoals(), match.getMatchPlace(), match.getMatchDate() };
				model.addRow(row);
			}
		}
	}

	private void eraseTextFields() {
		localTeamTxt.setText("");
		rivalTeamTxt.setText("");
		localGoalsTxt.setText("");
		rivalGoalsTxt.setText("");
		matchPlaceTxt.setText("");
		matchDateTxt.setText("");
	}
}
