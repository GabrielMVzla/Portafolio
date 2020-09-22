package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CrudControlador;
import objetos.ObjetoPersonaRFC;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.BorderLayout;

public class CrudVistaInicio extends JFrame 
{

	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	public JMenuBar menuBar;
	public JMenu mnOpciones;
	public JMenuItem mntmSalir;
	JLabel lblRfc, lblNombre, lblEdad, lblIdciudad,  lblCatalogoUsuarios;
	private JLabel label,label_1,label_2, label_3, label_4, label_5, label_7; //labels para acomodar
	public JTextField txtRfc, txtNombre, txtEdad, txtIdCiudad;
	public JButton btnGuardar, btnModificar, btnEliminar, btnConsultar,btnRecuperar;
	public Dimension dimensionInicial;
	
	public CrudVistaInicio()
	{
		super("CRUD");
		caracteristicasVentana(); //Damos dimensiones a la ventana
		HazInterface();			  //inicializamos y ubicamos componentes
	}
	
    private void caracteristicasVentana() 
    {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	dimensionInicial = new Dimension(545, 298);
    	setSize(new Dimension(868, 298));

    	setLocationRelativeTo(null);
    	
    	UIManager.LookAndFeelInfo[] apariencias =  UIManager.getInstalledLookAndFeels();
    	try {
    		UIManager.setLookAndFeel( apariencias[3].getClassName() );
    		SwingUtilities.updateComponentTreeUI( this );
    		
    	} catch (ClassNotFoundException e) {e.printStackTrace();} 
    	catch (InstantiationException e) {e.printStackTrace();} 
    	catch (IllegalAccessException e) {e.printStackTrace();}
    	catch (UnsupportedLookAndFeelException e) {e.printStackTrace();
    	} 
    }
    
	private void HazInterface() 
	{
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.setHorizontalAlignment(SwingConstants.LEFT);
		mnOpciones.add(mntmSalir);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		label_5 = new JLabel("");
		contentPane.add(label_5);
		
		lblCatalogoUsuarios = new JLabel("Catálogo Usuarios");
		lblCatalogoUsuarios.setFont(new Font("Tahoma", Font.BOLD, sizeBaselblCatUsu));
		lblCatalogoUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCatalogoUsuarios);
		
		label_7 = new JLabel("");
		contentPane.add(label_7);
		
		lblRfc  = new JLabel("RFC");
		contentPane.add(lblRfc);
		
		txtRfc = new JTextField();
		contentPane.add(txtRfc);
		txtRfc.setColumns(10);
		
		btnEliminar  = new JButton("Eliminar");
		btnEliminar.setBackground((Color.red));
		contentPane.add(btnEliminar);
		
		lblNombre = new JLabel("Nombre");
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setText("");
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		btnRecuperar = new JButton("Recuperar");
		contentPane.add(btnRecuperar);
		
		lblEdad  = new JLabel("Edad");
		contentPane.add(lblEdad);
		
		txtEdad = new JTextField();
		contentPane.add(txtEdad);
		txtEdad.setColumns(10);
		
		btnModificar  = new JButton("Modificar");
		contentPane.add(btnModificar);
		
		lblIdciudad  = new JLabel("IdCiudad");
		contentPane.add(lblIdciudad);
		
		txtIdCiudad = new JTextField();
		txtIdCiudad.setText("");
		contentPane.add(txtIdCiudad);
		txtIdCiudad.setColumns(10);
		
		btnConsultar = new JButton("Consultar");
		contentPane.add(btnConsultar);
		
		label = new JLabel("");
		contentPane.add(label);
		
		label_1 = new JLabel("");
		contentPane.add(label_1);
		
		label_2 = new JLabel("");
		contentPane.add(label_2);
		
		label_3 = new JLabel("");
		contentPane.add(label_3);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(new Color(152, 251, 152));
		contentPane.add(btnGuardar);
		
		label_4 = new JLabel("");
		contentPane.add(label_4);
	}
	
	//como los objetos están en la vista, desed este método recuperamos tales datos
	public ObjetoPersonaRFC datos()
	{
		ObjetoPersonaRFC datos;
		
		try 
		{
			datos = new ObjetoPersonaRFC(
					txtRfc.getText(),							//rfc
					txtNombre.getText(),						//nombre
					Integer.parseInt(txtEdad.getText()),		//edad
					Integer.parseInt(txtIdCiudad.getText())		//idCiudad
					);
			return datos;
		} 
		catch (Exception e) 
		{
			datos = new ObjetoPersonaRFC(txtRfc.getText(),txtNombre.getText(),-1,-1);
			return datos;
		}
	}
	
	public void limpiarPantalla()
	{
		txtRfc.setText("");
		txtNombre.setText("");
		txtEdad.setText("");
		txtIdCiudad.setText("");
	}
	//Asigna control de eventos en botones
	public void setControlador(CrudControlador c)
	{
		btnGuardar.addActionListener(c);
		btnModificar.addActionListener(c);
		btnRecuperar.addActionListener(c);
		btnConsultar.addActionListener(c);
		btnEliminar.addActionListener(c);
		mntmSalir.addActionListener(c);
		this.addComponentListener(c);
	}
	public void Muestrate()
	{
		setVisible(true);
	}
	
	//Muestra el mensaje de error o éxito
	public void muestraExitoError(String mensaje)
	{
		JOptionPane.showMessageDialog(null, mensaje);
	}

	//Error cuando te quieres conectar a la BD
	public void errorConexion(boolean errorValue) 
	{
		if(!errorValue)
		{
			JOptionPane.showMessageDialog(null, "Error al conectarse a la Base de Datos, Verifica los datos de conexión en \"CrudBD\" coincidan con tu tabla y reinicia el programa");
		}
	}
	
	final int sizeBaselblCatUsu = 18, sizeBaseComun = 11;
//			  sizeBaselblRFC = sizeBaseComun, sizeBaselblNombre = sizeBaseComun, sizeBaselblEdad = sizeBaseComun, sizeBaselblIdCiudad = sizeBaseComun;

	//cambiamos el tamaño de la letra (en labels y botones) dependiendo del tamaño del frame
	public void sizeFuenteLetra(int size)
	{
		//Tamaño de los labels
		nvoSizeFte(lblCatalogoUsuarios	, new Font("Tahoma",Font.BOLD,(sizeBaselblCatUsu + size)));
		nvoSizeFte(lblRfc				, new Font("Tahoma",Font.PLAIN,(sizeBaseComun + size)));
		nvoSizeFte(lblNombre			, new Font("Tahoma",Font.PLAIN,(sizeBaseComun + size)));
		nvoSizeFte(lblEdad				, new Font("Tahoma",Font.PLAIN,(sizeBaseComun + size)));
		nvoSizeFte(lblIdciudad			, new Font("Tahoma",Font.PLAIN,(sizeBaseComun + size)));
		
		//Tamaño de los botones en método sobrecargado
		nvoSizeFte(btnGuardar			, new Font("Tahoma",Font.PLAIN,(sizeBaseComun + size)));
		nvoSizeFte(btnRecuperar			, new Font("Tahoma",Font.PLAIN,(sizeBaseComun + size)));
		nvoSizeFte(btnModificar			, new Font("Tahoma",Font.PLAIN,(sizeBaseComun + size)));
		nvoSizeFte(btnConsultar			, new Font("Tahoma",Font.PLAIN,(sizeBaseComun + size)));
		nvoSizeFte(btnEliminar			, new Font("Tahoma",Font.PLAIN,(sizeBaseComun + size)));
		
		//Tamaño de los textos en los campos de textos en método sobrecargado
		nvoSizeFte(txtRfc				, new Font("Tahoma",Font.PLAIN,(sizeBaseComun + size)));
		nvoSizeFte(txtNombre			, new Font("Tahoma",Font.PLAIN,(sizeBaseComun + size)));
		nvoSizeFte(txtEdad				, new Font("Tahoma",Font.PLAIN,(sizeBaseComun + size)));
		nvoSizeFte(txtIdCiudad			, new Font("Tahoma",Font.PLAIN,(sizeBaseComun + size)));
		
		
		
	}
	public void nvoSizeFte(JLabel label, Font fuente) 
	{
		label.setFont(fuente);
	}
	public void nvoSizeFte(JButton boton, Font fuente) 
	{
		boton.setFont(fuente);
	}
	public void nvoSizeFte(JTextField txt, Font fuente) 
	{
		txt.setFont(fuente);
	}
}