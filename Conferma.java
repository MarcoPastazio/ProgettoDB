package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ImplementazioneDAO.ImplementazioneInsegnanteDAO;
import ImplementazioneDAO.ImplementazioneStudenteDAO;
import Model.Insegnante;
import Model.Studente;
import Model.Utente;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Conferma extends JFrame {

	public JFrame frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	
	/**
	 * Create the frame.
	 */
	public Conferma (JFrame framechiamante) {
		frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 394);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		textField = new JTextField();
		textField.setBounds(415, 78, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(415, 187, 96, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Insegnante");
		rdbtnNewRadioButton.setBackground(Color.WHITE);
		rdbtnNewRadioButton.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		rdbtnNewRadioButton.setBounds(127, 251, 111, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Studente");
		rdbtnNewRadioButton_1.setBackground(Color.WHITE);
		rdbtnNewRadioButton_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		rdbtnNewRadioButton_1.setBounds(400, 251, 111, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel = new JLabel("Mail");
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblNewLabel.setBounds(142, 80, 49, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(142, 189, 74, 20);
		contentPane.add(lblNewLabel_1);
		
		
		JButton btnNewButton = new JButton("INDIETRO");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				framechiamante.setVisible(true);
			}
		});
		btnNewButton.setBounds(190, 306, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CONFERMA");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnNewRadioButton_1.isSelected() & !rdbtnNewRadioButton.isSelected()) {
					
					String login = textField.getText();
					String password = textField_1.getText();
					Studente st = new Studente(login, password);
					ImplementazioneStudenteDAO ue = new ImplementazioneStudenteDAO();
					
					if(ue.LoginB(st)) {
						JFrame frameDaChiamare = new HomeStudente(frame);
						frameDaChiamare.setVisible(true);
						frame.setVisible(false);
					}
				}else if(rdbtnNewRadioButton.isSelected() & !rdbtnNewRadioButton_1.isSelected()) {
					
					String login = textField.getText();
					String password = textField_1.getText();
					Insegnante ins = new Insegnante(login, password);
					ImplementazioneInsegnanteDAO ue = new ImplementazioneInsegnanteDAO();
					
					if(ue.LoginB(ins)) {
						JFrame frameDaChiamare = new HomeInsegnante(frame);
						frameDaChiamare.setVisible(true);
						frame.setVisible(false);
					}
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_1.setBounds(337, 306, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Se sei gi\u00E0 registrato, metti solo mail e password");
		lblNewLabel_2.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(182, 31, 267, 23);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(75, 31, 469, 298);
		contentPane.add(panel);
	}

}
