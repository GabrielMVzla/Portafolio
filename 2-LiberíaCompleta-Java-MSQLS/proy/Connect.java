package proy;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class Connect extends JFrame implements ActionListener  {

	private JPanel contentPane;
	JButton btnInicio;
	Panel_Inicio panel_Inico;
	Panel_Miembros panel_Miembros;
	private JButton btnAgregaMiembro;
	private JButton btnLibros;
	private JButton btnPrstamos;
	Panel_Alta_Libros pal;
	Panel_Prestamos pp;
	Panel_Pago ppago;
	private JMenu mnMs;
	private JMenuItem mntmAcercaDe;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connect frame = new Connect();
					frame.setVisible(true);
				} catch (Exception e) {e.printStackTrace();} 
				  catch (Throwable e) {e.printStackTrace();}
			}
		});
	}

	public Connect() throws Throwable 
	{
		
		UIManager.LookAndFeelInfo[] apariencias =  UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel( apariencias[1].getClassName() );
			SwingUtilities.updateComponentTreeUI( this );

		} catch (ClassNotFoundException e) {e.printStackTrace();} 
		catch (InstantiationException e) {e.printStackTrace();} 
		catch (IllegalAccessException e) {e.printStackTrace();}
		catch (UnsupportedLookAndFeelException e) {e.printStackTrace();} 
		 this.setTitle("Biblioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550+106, 350+20);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmSalir = new JMenuItem("salir");
		mntmSalir.addActionListener(
	            new ActionListener(){
	                public void actionPerformed(ActionEvent e)
	                {
	                    System.exit(0);
	                }
	            }
	        );
		menuBar.add(mntmSalir);
		
//		mnMs = new JMenu("M\u00E1s");
//		menuBar.add(mnMs);
//		
//		mntmAcercaDe = new JMenuItem("Acerca de...");
//		
//		mnMs.add(mntmAcercaDe);
//		mntmSalir.addActionListener(
//	            new ActionListener(){
//	                public void actionPerformed(ActionEvent e)
//	                {
//	                    System.exit(0);
//	                }
//	            }
//	        );
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel_Miembros = new Panel_Miembros();
		panel_Miembros.setVisible(false);
		contentPane.add(panel_Miembros);

		panel_Inico = new Panel_Inicio();
		contentPane.add(panel_Inico);
		panel_Inico.setVisible(true);
//		panel_Inico.setEnabled(true);
		
		btnInicio = new JButton("Inicio");
		btnInicio.addActionListener(this);
		btnInicio.setBounds(6, 10, 89, 23);
		contentPane.add(btnInicio);
		
		btnAgregaMiembro = new JButton("miembros");
		btnAgregaMiembro.addActionListener(this);
		btnAgregaMiembro.setToolTipText("gestiona miembros");
		btnAgregaMiembro.setBounds(6, 45, 89, 23);
		contentPane.add(btnAgregaMiembro);
		
		btnLibros = new JButton("Libros");
		btnLibros.setBounds(6, 80, 90, 23);
		btnLibros.addActionListener(this);
		contentPane.add(btnLibros);
		
		btnPrstamos = new JButton("Pr\u00E9stamos");
		btnPrstamos.setBounds(6, 115, 90, 23);
		btnPrstamos.addActionListener(this);
		contentPane.add(btnPrstamos);
		
		pal = new Panel_Alta_Libros();
		pal.setVisible(false);
		contentPane.add(pal);

		pp = new Panel_Prestamos();
		pp.setVisible(false);
		contentPane.add(pp);

		ppago = new Panel_Pago();
		ppago.setVisible(true);
		ppago.setBounds(522,10,124,290);
		contentPane.add(ppago);
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
//		JRadioButton rb = (JRadioButton) e.getSource();
		
		if(b == btnInicio)
		{		
			panel_Inico.setVisible(true);
			panel_Miembros.setVisible(false);		
			pal.setVisible(false);
			pp.setVisible(false);
			ppago.setVisible(true);

//			panel_Inico.setEnabled(true);
		}
		if(b == btnAgregaMiembro)
		{
			
//			panel_miembros.setEnabled(true);
			panel_Miembros.setVisible(true);		
//			panel_Inico.setEnabled(false);
			panel_Inico.setVisible(false);
			pal.setVisible(false);
			pp.setVisible(false);
			ppago.setVisible(false);

		}
		if(b == btnLibros)
		{
		
//			panel_miembros.setEnabled(true);
			panel_Miembros.setVisible(false);		
//			panel_Inico.setEnabled(false);
			panel_Inico.setVisible(false);
			pal.setVisible(true);

			pp.setVisible(false);
			ppago.setVisible(false);
		}
		if(b == btnPrstamos)
		{
			
//			panel_miembros.setEnabled(true);
			panel_Miembros.setVisible(false);		
//			panel_Inico.setEnabled(false);
			panel_Inico.setVisible(false);
			pal.setVisible(false);
			pp.setVisible(true);
			ppago.setVisible(false);
		}
	}
}
