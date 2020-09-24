package vistas;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.CrudControlador;
import objetos.ObjetoPersonaRFC;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Color;

public class CrudVistaConsulta extends JDialog {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private JPanel panel, panelBusqueda, panelTabla;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblBusquedarfc, lblPgina;
	private JTextField txtBuscarRfc;
	
	public JButton btnSiguiente, btnAnterior, btnBuscarRfc,btnConsultaTodo;

	public CrudVistaConsulta(CrudVistaInicio vista, boolean modal)
    {
        super(vista, modal);             
        
        caracteristicasVentana();	//Damos dimensiones a la ventana
        inicializarComponentes();   //inicializamos y ubicamos componentes
    }
    private void caracteristicasVentana() 
    {
        setSize(1200, 422);
        setTitle("Consulta Datos");                  
        setLocationRelativeTo(null);
        setResizable(false);
	}
    private void inicializarComponentes()
	{
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	panel = new JPanel();
    	panel.setBackground(SystemColor.menu);
    	panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		
		modelo = new DefaultTableModel();
		panel.setLayout(new BorderLayout(5, 5));
		
		panelTabla = new JPanel();
		panelTabla.setBackground(SystemColor.menu);
		panel.add(panelTabla);
		panelTabla.setLayout(new BorderLayout(5, 5));
		
		scrollPane = new JScrollPane();
		panelTabla.add(scrollPane, BorderLayout.CENTER);
		table = new JTable(modelo);
		estableceModel();
		
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		scrollPane.setViewportView(table);
		
		btnAnterior  = new JButton("< Anterior");
		btnAnterior.setMnemonic('a');
		panelTabla.add(btnAnterior, BorderLayout.WEST);
		btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAnterior.setHorizontalAlignment(SwingConstants.LEFT);
		btnAnterior.setEnabled(false);
		
		lblPgina = new JLabel("Página");
		panelTabla.add(lblPgina, BorderLayout.SOUTH);
		lblPgina.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPgina.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnSiguiente = new JButton("Siguiente >");
		btnSiguiente.setMnemonic('d');
		panelTabla.add(btnSiguiente, BorderLayout.EAST);
		btnSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSiguiente.setHorizontalAlignment(SwingConstants.RIGHT);
		
		panelBusqueda = new JPanel();
		panelBusqueda.setBackground(SystemColor.menu);
		panel.add(panelBusqueda, BorderLayout.NORTH);
		panelBusqueda.setLayout(new GridLayout(0, 4, 0, 0));
		
		btnConsultaTodo = new JButton("Consulta General");
		panelBusqueda.add(btnConsultaTodo);
		
		lblBusquedarfc = new JLabel("Inserta RFC para Buscar  ");
		lblBusquedarfc.setHorizontalAlignment(SwingConstants.RIGHT);
		panelBusqueda.add(lblBusquedarfc);
		
		txtBuscarRfc = new JTextField();
		panelBusqueda.add(txtBuscarRfc);
		txtBuscarRfc.setColumns(10);
		
		btnBuscarRfc = new JButton("Buscar");
		panelBusqueda.add(btnBuscarRfc);
	}
	public void vaciaaRfc() 
	{
		txtBuscarRfc.setText("");
	}
	public String getRfc() 
	{
		return txtBuscarRfc.getText();
	}
    
    private void estableceModel() 
    {
       	table.setModel(new DefaultTableModel( 
    			new Object[][] {
    			},
    			new String[] {
    				"No", "RFC", "Nombre", "Edad", "ID Ciudad"
    			}
    		));
    }
	
    public void muestraDatosTabla(ArrayList<ObjetoPersonaRFC> datosPersona, int inicioPagina, int limiteTuplas, boolean activoBtnAnterior, boolean activoBtnSiguiente) //pintamos los datos de la tabla
	//cada indice contiene un renglón con 4 columnas de la info de un registro
	{   
    	estableceModel();//nueva llamada al método que crea objeto modelo ya que lanzaba unos errores en consola, establecía un índice límite y si se superaba lanzaba errores en consola
    	
		String rfc = "", nombre = "";
		int edad = -1, idCiudad = -1;
		int tupla = inicioPagina;
		inicioPagina = (inicioPagina/limiteTuplas) + 1; // +1 para que no comience desde 0
		
		btnAnterior.setEnabled(activoBtnAnterior);
		btnSiguiente.setEnabled(activoBtnSiguiente);
		
		lblPgina.setForeground(Color.black);
		lblPgina.setText("Página " + inicioPagina);
		
		if(!activoBtnAnterior)
		{
			lblPgina.setText("Primer página");
			lblPgina.setForeground(Color.RED);
		}
		else if(!activoBtnSiguiente)
		{
			lblPgina.setText("Última página");
			lblPgina.setForeground(Color.RED);
		}
		
		for (int i = 0; i < datosPersona.size(); i++) //for simplemente desglosa el array de objetos y lo añade a la tabla de esta vista  
		{
			tupla++;
			rfc = datosPersona.get(i).getRfc();
			nombre = datosPersona.get(i).getNombre();
			edad = datosPersona.get(i).getEdad();
			idCiudad = datosPersona.get(i).getIdCiudad();
			
			Object datos1[] = {tupla, rfc, nombre, edad, idCiudad};
			((DefaultTableModel) table.getModel()).addRow(datos1); //se agregan al componente tabla

		}
	}
    public void muestraDatosTabla(ObjetoPersonaRFC datosPersona) //pintamos los datos de la tabla
    //cada indice contiene un renglón con 4 columnas de la info de un registro
    {   
    	estableceModel();
    	
		lblPgina.setText("Primer página");
		lblPgina.setForeground(Color.RED);
    	
    	btnAnterior.setEnabled(false);
    	btnSiguiente.setEnabled(false);
    	    	
		Object datos1[] = 
			{
				String.valueOf(1),
				datosPersona.getRfc(),
				datosPersona.getNombre(), 
				datosPersona.getEdad(),
				datosPersona.getIdCiudad()
			};
		((DefaultTableModel) table.getModel()).addRow(datos1); //se agregan al componente tabla	
    }
	public void eliminarDatosTablas() //para que no se apilen con el repintado cuando se vuelve a consultar
	{
	    DefaultTableModel tb = (DefaultTableModel) table.getModel();
	    int a = table.getRowCount()-1;
	    
	    for (int i = a; i >= 0; i--)     
	    {
	    	tb.removeRow(tb.getRowCount()-1);
	    }
	    
	    
	}
	
	public void setControlador(CrudControlador c)
	{
		btnSiguiente.addActionListener(c);
		btnAnterior.addActionListener(c);
		btnBuscarRfc.addActionListener(c);
		btnConsultaTodo.addActionListener(c);
	}
}
