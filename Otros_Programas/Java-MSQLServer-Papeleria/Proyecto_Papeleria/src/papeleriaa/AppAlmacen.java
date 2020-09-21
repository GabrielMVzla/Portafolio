package papeleriaa;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.SystemColor;

public class AppAlmacen extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppAlmacen frame = new AppAlmacen();
					frame.setVisible(true);
				}
				catch (Exception e) {e.printStackTrace();	} 
				catch (Throwable e) {	e.printStackTrace();}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	JButton btnAgregarArtculo;
	JButton btnConsultarArticulos;
	private JLabel label;
	private JLabel lblPapelera;
	JPanel panel;
	AddRem ar;
	Articulos_C1 ac1;
	private JButton button;
	private JLabel lblInicio;
	private JButton btnConsultarHistorial;
	Articulo_Historial_C1 ahc1;
	public AppAlmacen() throws Throwable {
		
		setResizable(false);
		UIManager.LookAndFeelInfo[] apariencias =  UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel( apariencias[1].getClassName() );
			SwingUtilities.updateComponentTreeUI( this );

		} catch (ClassNotFoundException e) {e.printStackTrace();} 
		catch (InstantiationException e) {e.printStackTrace();} 
		catch (IllegalAccessException e) {e.printStackTrace();}
		catch (UnsupportedLookAndFeelException e) {e.printStackTrace();} 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(25, 100, 544, 365);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAgregarArtculo = new JButton("Agrega/Vende art\u00EDculo");
		btnAgregarArtculo.setBounds(10, 75, 159, 53);
		btnAgregarArtculo.addActionListener(this);
		contentPane.add(btnAgregarArtculo);
		
		btnConsultarArticulos = new JButton("Consultar articulos");
		btnConsultarArticulos.setBounds(10, 139, 159, 53);
		btnConsultarArticulos.addActionListener(this);
		contentPane.add(btnConsultarArticulos);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBounds(190, 11, 328, 304);
		contentPane.add(panel);
		panel.setLayout(null);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon("imagenes\\imprenta.png"));
		label.setBounds(43, 32, 250, 239);
		panel.add(label);
		
		lblInicio = new JLabel("INICIO");
		lblInicio.setFont(new Font("Rockwell", Font.BOLD, 17));
		lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio.setBounds(89, 7, 144, 28);
		panel.add(lblInicio);
		setTitle("Imprenta");
		
		ar = new AddRem();
		ar.setVisible(false);
		getContentPane().add(ar);

		ac1 = new Articulos_C1();
		ac1.setVisible(false);
		
		
		button = new JButton("");
		button.setIcon(new ImageIcon("imagenes\\iconotito.png"));
		button.setBounds(10, 11, 159, 53);
		button.addActionListener(this);
		contentPane.add(button);
		
		lblPapelera = new JLabel("IMPRENTA");
		lblPapelera.setBounds(10, 286, 159, 29);
		contentPane.add(lblPapelera);
		lblPapelera.setFont(new Font("Rockwell", Font.BOLD, 17));
		lblPapelera.setForeground(SystemColor.menu);
		lblPapelera.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnConsultarHistorial = new JButton("Consultar Historial");
		btnConsultarHistorial.addActionListener(this);
		btnConsultarHistorial.setBounds(10, 203, 159, 53);
		contentPane.add(btnConsultarHistorial);
		
		ahc1 = new Articulo_Historial_C1();
		
		ahc1.setVisible(false);
//		getContentPane().add(ahc1);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b == btnAgregarArtculo) {
			
			panel.setVisible(false);
			ar.setVisible(true);
			
		}
		if(b == btnConsultarArticulos) {
			
			
			ac1.setVisible(true);
			
			
		}
		if(b == button) {
			
			ar.setVisible(false);
			panel.setVisible(true);
			
			
		}
		if(btnConsultarHistorial == b) {
			ahc1.setVisible(true);
		}
		
	}
}
