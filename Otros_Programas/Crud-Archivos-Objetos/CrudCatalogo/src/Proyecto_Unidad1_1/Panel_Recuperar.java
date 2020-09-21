package Proyecto_Unidad1_1;

import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Panel_Recuperar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	public Panel_Recuperar() throws IOException 
	{
	    setBounds(0, 0, 660, 398);
	    setLayout(null);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(10, 5, 640, 376);
	    add(scrollPane);
	    
	    table = new JTable();
	    table.setModel(new DefaultTableModel(
	    	new Object[][] {
	    	},
	    	new String[] {
	    		"Clave", "Descripción", "Unidad de Medida", "Existencia", "Activo"
	    	}
	    ));
	    table.getColumnModel().getColumn(0).setMinWidth(30);
	    scrollPane.setViewportView(table);
	    
		String textoCompleto = ArchivoArtis.MuestraArchivo();
		int vueltas = textoCompleto.length()/62, vueltas_j = 0;
		
	    for (int i = 0; i < vueltas; i++)
	    {
	    	String cve = "", unidad = "", existe = "", descripcion = "", alta = "";

	    	vueltas_j = (int) ((i + 1) * 62);

		    for (int j = (vueltas_j - 62); j < vueltas_j; j++)
		    {
		    	if(cve.length() < 6 )
		    		cve = cve + textoCompleto.charAt(j);
		    	
		    	else if(descripcion.length() < 30)
		    		descripcion = descripcion + textoCompleto.charAt(j);
		    		
		    	else if(unidad.length() < 15)
		    		unidad = unidad + textoCompleto.charAt(j);
		    	
		    	else if(existe.length() < 6)
		    		existe = existe + textoCompleto.charAt(j);

		    	else if(alta.length() < 5)
		    		alta = alta + textoCompleto.charAt(j);
		    	
			}
		    Object datos1[] = {cve, descripcion, unidad, existe, alta};
		    ((DefaultTableModel) table.getModel()).addRow(datos1);
	    }	    
	}
}
