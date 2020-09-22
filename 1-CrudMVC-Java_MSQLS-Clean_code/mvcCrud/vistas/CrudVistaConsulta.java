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

public class CrudVistaConsulta extends JDialog {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	public JButton btnSiguiente, btnAnterior;
	JLabel lblPgina;
	
    public CrudVistaConsulta(CrudVistaInicio vista, boolean modal)
    {
        super(vista, modal);             
        
        caracteristicasVentana();	//Damos dimensiones a la ventana
        inicializarComponentes();   //inicializamos y ubicamos componentes
    }
    
    private void caracteristicasVentana() 
    {
        setSize(861, 409);
        setTitle("Consulta Datos");                  
        setLocationRelativeTo(null);
        setResizable(false);
	}
    private void inicializarComponentes()
	{
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 840, 319);

		contentPane.add(scrollPane);
		
		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "RFC", "Nombre", "Edad", "ID Ciudad"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		scrollPane.setViewportView(table);
		
		btnSiguiente = new JButton("Siguiente                    >");
		btnSiguiente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSiguiente.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSiguiente.setBounds(589, 335, 261, 34);
		contentPane.add(btnSiguiente);
		
		btnAnterior  = new JButton("<                     Anterior");
		btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAnterior.setHorizontalAlignment(SwingConstants.LEFT);
		btnAnterior.setBounds(5, 335, 261, 34);
		btnAnterior.setEnabled(false);
		contentPane.add(btnAnterior);
		
		lblPgina = new JLabel("Página");
		lblPgina.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPgina.setHorizontalAlignment(SwingConstants.CENTER);
		lblPgina.setBounds(296, 335, 261, 34);
		contentPane.add(lblPgina);
	}
	
    public void muestraDatosTabla(ArrayList<ObjetoPersonaRFC> datosPersona, int inicioPagina, int limiteTuplas, boolean activoBtnAnterior,boolean activoBtnSiguiente) //pintamos los datos de la tabla
	//cada indice contiene un renglón con 4 columnas de la info de un registro
	{   
		String rfc = "", nombre = "";
		int edad = -1, idCiudad = -1;
		int tupla = inicioPagina;
		inicioPagina = (inicioPagina/limiteTuplas) + 1; // +1 para que no comience desde 0
		
		btnAnterior.setEnabled(activoBtnAnterior);
		btnSiguiente.setEnabled(activoBtnSiguiente);
		
		lblPgina.setText("Página " + inicioPagina);
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
	public void eliminar() //para que no se apilen con el repintado cuando se vuelve a consultar
	{
	    DefaultTableModel tb = (DefaultTableModel) table.getModel();
	    int a = table.getRowCount()-1;
	    
	    for (int i = a; i >= 0; i--)           
	    	tb.removeRow(tb.getRowCount()-1);
	}
	
	public void setControlador(CrudControlador c)
	{
//		dimension.addActionListener(c);
		btnSiguiente.addActionListener(c);
		btnAnterior.addActionListener(c);
	
	}
}
