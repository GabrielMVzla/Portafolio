package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CrudControlador;
import objetos.ObjetoMensajePersonaRFC;
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
import java.awt.SystemColor;

public class CrudVistaInicio extends JFrame 
{

	private static final long serialVersionUID = 1L;

	private JLabel lblRfc, lblNombre, lblEdad, lblIdciudad, lblCatalogoUsuarios;
	private JPanel panel, panel_Form, panel_Encabezado;// , panel_1;
	private JTextField txtRfc, txtNombre, txtEdad, txtIdCiudad;
	private Dimension dimensionInicial;

	public JButton btnGuardar, btnModificar, btnEliminar, btnConsultar,btnRecuperar;
	public JMenuBar menuBar;
	public JMenu mnOpciones;
	public JMenuItem mntmSalir;

//	public JLabel lblLblimagen;
	
	public CrudVistaInicio()
	{
		super("CRUD");
		caracteristicasVentana(); //Damos dimensiones a la ventana
		HazInterface();			  //inicializamos y ubicamos componentes
	}
	
    private void caracteristicasVentana() 
    {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	setSize(new Dimension(600, 325));
    	dimensionInicial = new Dimension(getSize());

    	setLocationRelativeTo(null);
    	
    	UIManager.LookAndFeelInfo[] apariencias =  UIManager.getInstalledLookAndFeels();
    	try {
    		UIManager.setLookAndFeel( apariencias[1].getClassName() );
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
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_Form = new JPanel();
		panel_Form.setBackground(SystemColor.menu);
		panel.add(panel_Form, BorderLayout.CENTER);
		panel_Form.setLayout(new GridLayout(0, 3, 5, 5));
		
		lblRfc = new JLabel("  RFC");
		panel_Form.add(lblRfc);
		
		txtRfc = new JTextField();
		txtRfc.setColumns(10);
		panel_Form.add(txtRfc);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(new Color(204, 255, 204));
		panel_Form.add(btnGuardar);
		
		lblNombre = new JLabel("  Nombre");
		panel_Form.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setText("");
		txtNombre.setColumns(10);
		panel_Form.add(txtNombre);
		
		btnRecuperar = new JButton("Recuperar");
		panel_Form.add(btnRecuperar);
		
		lblEdad = new JLabel("  Edad");
		panel_Form.add(lblEdad);
		
		txtEdad = new JTextField();
		txtEdad.setColumns(10);
		panel_Form.add(txtEdad);
		
		btnModificar = new JButton("Modificar");
		panel_Form.add(btnModificar);
		
		lblIdciudad = new JLabel("  IdCiudad");
		panel_Form.add(lblIdciudad);
		
		txtIdCiudad = new JTextField();
		txtIdCiudad.setText("");
		txtIdCiudad.setColumns(10);
		panel_Form.add(txtIdCiudad);
		
		btnConsultar = new JButton("Consultar");
		panel_Form.add(btnConsultar);
		
		lblRelleno1 = new JLabel("");
		panel_Form.add(lblRelleno1);
		
		lblRelleno2 = new JLabel("");
		panel_Form.add(lblRelleno2);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(255, 182, 193));
		panel_Form.add(btnEliminar);
	
		panel_Encabezado = new JPanel();
		panel_Encabezado.setBackground(SystemColor.menu);

		panel.add(panel_Encabezado, BorderLayout.NORTH);
		
		lblCatalogoUsuarios = new JLabel("Catálogo Usuarios");
		lblCatalogoUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblCatalogoUsuarios.setFont(new Font("Tahoma", Font.BOLD, sizeBaselblCatUsu));
		panel_Encabezado.add(lblCatalogoUsuarios);
	}
	
	public void llenaDatos(ObjetoMensajePersonaRFC msjPersonRecuperada) 
	{
		txtNombre.setText(msjPersonRecuperada.getDatos().getNombre());
		txtEdad.setText(String.valueOf(msjPersonRecuperada.getDatos().getEdad()));
		txtIdCiudad.setText(String.valueOf(msjPersonRecuperada.getDatos().getIdCiudad()));
	}
	public Dimension getDimensionInicial()
	{
		return dimensionInicial;
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
	
	//Error cuando te quieres conectar a la BD
	public void errorConexion(boolean errorValue) 
	{
		if(!errorValue)
		{
			JOptionPane.showMessageDialog(null, "Sin conexión en la base de datos, Verifica los datos\nde conexión en \"CrudBD\" y reinicia el programa");
		}
	}
		
	private final int sizeBaselblCatUsu = 16, sizeBaseComun = 11;
	private JLabel lblRelleno1;
	private JLabel lblRelleno2;
//			  sizeBaselblRFC = sizeBaseComun, sizeBaselblNombre = sizeBaseComun, sizeBaselblEdad = sizeBaseComun, sizeBaselblIdCiudad = sizeBaseComun;

	//cambiamos el tamaño de la letra (en labels y botones) dependiendo del tamaño del frame
	public void sizeFuenteLetra(int size)
	{
		//Tamaño de los labels
		int incTitulo = (int) (size * 1.5);
		nvoSizeFte(lblCatalogoUsuarios	, new Font("Tahoma",Font.BOLD,((sizeBaselblCatUsu + incTitulo))));
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
	//calculo para el incremento de tamaño de fuente en la vista principal
	public int calculoIncremento(Dimension dimensionInicial, Dimension dimensionActual) 
	{
		int 	incrementoHasta = 40,
				incrementoIniAncho = 0, incrementoIniAlto = 0, diferenciaAlto = 0,
				incrementoActAncho = 0, incrementoActAlto = 0, diferenciaAncho = 0;
		
		//medidas base
		incrementoIniAlto = dimensionInicial.height/incrementoHasta;
		incrementoIniAncho = dimensionInicial.width/incrementoHasta;
		//medidas actuales
		incrementoActAlto = dimensionActual.height/incrementoHasta;
		incrementoActAncho = dimensionActual.width/incrementoHasta;
		
		diferenciaAlto = incrementoActAlto - incrementoIniAlto;
		diferenciaAncho = incrementoActAncho - incrementoIniAncho;
		
		if(diferenciaAlto >= 1) //crece cuando se hace alta la ventana
		{				
			return diferenciaAlto;
		}
		if(diferenciaAlto >= 1 && diferenciaAncho >= 1) //crece escaladamente dependiendo de qué crezca 
		{
			if(diferenciaAlto > diferenciaAncho)
			{
				return diferenciaAncho;
			}
			else if(diferenciaAlto < diferenciaAncho)
			{
				return diferenciaAlto;
			}
		}
		return 0;
	}
}