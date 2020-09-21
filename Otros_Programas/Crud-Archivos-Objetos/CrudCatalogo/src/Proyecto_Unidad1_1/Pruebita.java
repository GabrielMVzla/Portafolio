package Proyecto_Unidad1_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Pruebita extends JFrame implements ActionListener  {

	/**
	 * configurar el int private, string string double
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	PanelArtics pa;
	Panel_inicio inicio;
	Panel_Recuperar recuperar;
	/**
	 * Launch the application.
	 */
	static Pruebita frame;
	public static void main(String[] args) throws InterruptedException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Pruebita();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
//	while(true) {	Thread.sleep(1000);  System.out.println(frame.getSize());}
	
	}

	JMenuBar menuBar;
	JMenu mnOpciones, ayuda;
	public JMenuItem mntmMostrar, mntmConsultarCatlogo;
	public static JMenuItem mntmGuardar;
	public JMenuItem mntmRecuperar;
	public JMenuItem mntmImprimir;
	public JMenuItem mntmSalir_1;
	public JMenuItem acerca;
    public static Visible visible; 

	public Pruebita() throws IOException {
		
		UIManager.LookAndFeelInfo[] apariencias =  UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel( apariencias[3].getClassName() );
			SwingUtilities.updateComponentTreeUI( this );

		} catch (ClassNotFoundException e) {e.printStackTrace();} 
		catch (InstantiationException e) {e.printStackTrace();} 
		catch (IllegalAccessException e) {e.printStackTrace();}
		catch (UnsupportedLookAndFeelException e) {e.printStackTrace();} 
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 440); //[width=676,height=382]
		setTitle("Artículos");
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnOpciones = new JMenu("Archivo");
		menuBar.add(mnOpciones);
		
		mntmMostrar = new JMenuItem("Actualizar Catálogo");
		mnOpciones.add(mntmMostrar);
		mntmMostrar.addActionListener(this);
		
		mntmConsultarCatlogo = new JMenuItem("Consultar Catálogo");
		mnOpciones.add(mntmConsultarCatlogo);
		mntmConsultarCatlogo.addActionListener(this);
		
		mntmGuardar = new JMenuItem("Guardar");
		mnOpciones.add(mntmGuardar);
//		mntmGuardar.addActionListener(this);
		
		mntmRecuperar = new JMenuItem("Recuperar");
		mnOpciones.add(mntmRecuperar);
		mntmRecuperar.addActionListener(this);
		
		mntmImprimir = new JMenuItem("Imprimir");
		mnOpciones.add(mntmImprimir);
		mntmImprimir.addActionListener(this);
		
		mntmSalir_1 = new JMenuItem("Salir");
		mnOpciones.add(mntmSalir_1);
		mntmSalir_1.addActionListener(this);
		
		ayuda = new JMenu("Ayuda");
		menuBar.add(ayuda);
		
		acerca = new JMenuItem("Acerca de...");
		ayuda.add(acerca);
		acerca.addActionListener(this);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		visible = new Visible();
		visible.setVisi(false);
	    
	    pa = new PanelArtics();
//	    contentPane.add(pa);
	    pa.setVisible(false);
	    
	    recuperar = new Panel_Recuperar();
//	    contentPane.add(recuperar);
	    recuperar.setVisible(false);
	  
	    inicio = new Panel_inicio();
	    contentPane.add(inicio);
	    inicio.setVisible(true);


	    inicio.setBorder(new EmptyBorder(5, 5, 5, 5));

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JMenuItem fuente = (JMenuItem) e.getSource();
		
		if ( fuente == mntmMostrar )
		{
			
			visible.setVisi(true);
		    pa = new PanelArtics();
		    pa.setVisible(true);
		    contentPane.add(pa);
//		    pa.setVisible(false);
//			System.out.println("mostrar actu");
			inicio.setVisible(false);
		    recuperar.setVisible(false);
			recuperar.setEnabled(false);

		}
		if(fuente == mntmConsultarCatlogo)
		{
			
    		JOptionPane.showMessageDialog(null, "Reservado para futuras versiones", "No Disponible", JOptionPane.INFORMATION_MESSAGE);

//			pa.setVisible(false);
//		    recuperar.setVisible(false);
//			recuperar.setEnabled(false);
//
//			inicio.setVisible(true);
			
		}
		if(fuente == mntmRecuperar)
		{
			visible.setVisi(false);

		   try {
			recuperar = new Panel_Recuperar();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		    contentPane.add(recuperar);
			pa.setVisible(false);
			pa.setEnabled(false);
			inicio.setVisible(false);
			
			recuperar.setVisible(true);
		}
//		if(fuente == mntmGuardar) 
//		{
//			JOptionPane.showMessageDialog(null, "Reservado para futuras versiones", "No Disponible", JOptionPane.INFORMATION_MESSAGE);
//		}
		if(fuente == mntmImprimir) 
		{
    		JOptionPane.showMessageDialog(null, "Reservado para futuras versiones", "No Disponible", JOptionPane.INFORMATION_MESSAGE);
		}
		if(fuente == acerca)
		{
    		JOptionPane.showMessageDialog(null, "Félix Camacho Gerardo\nLeyva Fonseca Julio Cesar\r\n" + 
    				"", "Autores", JOptionPane.INFORMATION_MESSAGE);
		}		
		if(fuente == mntmSalir_1)
		{
			System.exit( 0 ); // sale de la aplicación
		}		
	}
}
