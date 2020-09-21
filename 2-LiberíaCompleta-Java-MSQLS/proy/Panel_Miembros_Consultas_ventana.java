package proy;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Panel_Miembros_Consultas_ventana extends JFrame implements ActionListener{

	private JPanel contentPane;
	JButton btnMiembros;
	JButton btnMiembrosAdultos;
	JButton btnMiembrosJovenes;
	Panel_Miembros_C_1 panelMiembrosC1;
	Panel_Miembros_C_2 panelMiembrosC2;
	Panel_Miembros_C_3 panelMiembrosC3;
	Panel_Miembros panel_Miembros;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel_Miembros_Consultas_ventana frame = new Panel_Miembros_Consultas_ventana();
					frame.setVisible(true);
				} catch (Exception e) {	e.printStackTrace();	} 
				catch (Throwable e) {e.printStackTrace();}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Throwable 
	 */
	public Panel_Miembros_Consultas_ventana() throws Throwable {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 36, 730, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnMiembros = new JButton("miembros");
		btnMiembros.addActionListener(this);
		btnMiembros.setBounds(10, 11, 136, 23);
		contentPane.add(btnMiembros);
		
		btnMiembrosAdultos = new JButton("miembros adultos");
		btnMiembrosAdultos.setBounds(302, 11, 136, 23);
		btnMiembrosAdultos.addActionListener(this);
		contentPane.add(btnMiembrosAdultos);
		
		btnMiembrosJovenes = new JButton("Miembros Jovenes");
		btnMiembrosJovenes.setBounds(568, 11, 136, 23);
		btnMiembrosJovenes.addActionListener(this);
		contentPane.add(btnMiembrosJovenes);
		
		
		panelMiembrosC1 = new Panel_Miembros_C_1();
		contentPane.add(panelMiembrosC1);
		panelMiembrosC1.setVisible(true);
		panelMiembrosC2 = new Panel_Miembros_C_2();
		contentPane.add(panelMiembrosC2);
		panelMiembrosC2.setVisible(false);
		panelMiembrosC3 = new Panel_Miembros_C_3();
		contentPane.add(panelMiembrosC3);
		panelMiembrosC3.setVisible(false);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(btnMiembros == e.getSource()) 
		{	
			panelMiembrosC1.setVisible(true);
			panelMiembrosC2.setVisible(false);
			panelMiembrosC3.setVisible(false);
		}
		if(btnMiembrosAdultos == e.getSource()) 
		{
			panelMiembrosC2.setVisible(true);
			panelMiembrosC1.setVisible(false);
			panelMiembrosC3.setVisible(false);
		}
		if(btnMiembrosJovenes == e.getSource()) 
		{
			panelMiembrosC3.setVisible(true);
			panelMiembrosC1.setVisible(false);
			panelMiembrosC2.setVisible(false);
		}
		
	}
}
