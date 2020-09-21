package vistas;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import objetos.ObjetoPersonaRFC;

public class CrudVistaConsulta extends JDialog {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelo;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	
    public CrudVistaConsulta(CrudVistaInicio vista, boolean modal)
    {
        super(vista, modal);             
        
        caracteristicasVentana();	//Damos dimensiones a la ventana
        inicializarComponentes();   //inicializamos y ubicamos componentes
    }
    
    private void caracteristicasVentana() 
    {
        setSize(933, 428);
        setTitle("Consulta Datos");                  
        setLocationRelativeTo(null);
        setResizable(false);
	}
    private void inicializarComponentes()
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 917, 389);

		contentPane.add(scrollPane);
		
		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setModel(new DefaultTableModel
				(
					new Object[][] {},
					new String[] {"RFC", "Nombre", "Edad", "ID Ciudad"}
				));
		scrollPane.setViewportView(table);
	}
	
    public void muestraDatosTabla(ArrayList<ObjetoPersonaRFC> datosPersona) //pintamos los datos de la tabla
	//cada indice contiene un renglón con 4 columnas de la info de un registro
	{   
		String rfc = "", nombre = "";
		int edad = -1, idCiudad = -1;
		
		for (int i = 0; i < datosPersona.size(); i++) //for simplemente desglosa el array de objetos y lo añade a la tabla de esta vista  
		{
			rfc = datosPersona.get(i).getRfc();
			nombre = datosPersona.get(i).getNombre();
			edad = datosPersona.get(i).getEdad();
			idCiudad = datosPersona.get(i).getIdCiudad();
			
			Object datos1[] = {rfc, nombre, edad, idCiudad};
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
}
