package Proyecto_Unidad1_1;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.management.Descriptor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.Box;

@SuppressWarnings("serial")
public class PanelArtics extends JPanel implements ActionListener, KeyListener, MouseListener
{
	private JTextField txt_cve, txt_unidad, txt_descripcion, txt_buscar, txt_existe;
	JButton btn_guardar, btn_buscar, btn_mod, btn_cancelar, btn_darBaja, btn_busca_otro;
//	PanelGuardado pg;
	JLabel lblCve;
	JMenuItem guardar;
    Visible visible; 

	public PanelArtics() 
	{
	    ManejadorArchivo miOyente = new ManejadorArchivo(); // manejador elementos del menú archivo

	    setBounds(0, 0, 660, 398);
		setLayout(null);
		setBackground(new Color(240, 248, 255));
		setVisible(true);
		JLabel label = new JLabel("Clave:");
		label.setBounds(42, 296, 62, 22);
		add(label);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(370, 296, 72, 22);
		add(lblDescripcin);
		
		JLabel label_1 = new JLabel("Unidad de Medida:");
		label_1.setBounds(42, 326, 102, 22);
		add(label_1);

		JLabel label_2 = new JLabel("Existencia:");
		label_2.setBounds(370, 326, 86, 22);
		add(label_2);
		
		JLabel img = new JLabel(new ImageIcon("articles01 (1).jpg"));
		img.setBounds(-50, 0, 761, 275);
	    add(img);
	    
	    lblCve = new JLabel("cve: ");
	    lblCve.setBounds(189, 360, 46, 22);
	    add(lblCve);

		txt_cve = new JTextField();
		txt_cve.setBounds(157, 296, 102, 20);
		txt_cve.addKeyListener(this);
		add(txt_cve);
		txt_cve.setColumns(10);
		
		txt_unidad = new JTextField();
		txt_unidad.setBounds(157, 326, 102, 20);
		txt_unidad.addKeyListener(this);
		txt_unidad.addMouseListener(this);
		add(txt_unidad);
		txt_unidad.setColumns(10);
		
		txt_existe = new JTextField();
		txt_existe.setBounds(467, 326, 146, 20);
		txt_existe.addKeyListener(this);
		txt_existe.addMouseListener(this);
		add(txt_existe);
		txt_existe.setColumns(10);
		
		txt_descripcion = new JTextField();
		txt_descripcion.setBounds(467, 296, 146, 20);
		txt_descripcion.addKeyListener(this);
		txt_descripcion.addMouseListener(this);
		add(txt_descripcion);
		txt_descripcion.setColumns(10);
		
		txt_buscar = new JTextField();
		txt_buscar.setEnabled(false); 
		txt_buscar.setBounds(231, 360, 102, 20);
		txt_buscar.addKeyListener(this);
		txt_buscar.addMouseListener(this);
		add(txt_buscar);
		txt_buscar.setColumns(10);
		
		btn_buscar = new JButton("Buscar");
		btn_buscar.setEnabled(false);
		btn_buscar.setBounds(355, 360, 70, 20);
		btn_buscar.addActionListener(this);
		add(btn_buscar);
		
		btn_guardar = new JButton("Guardar");
		btn_guardar.setBounds(535, 360, 78, 20);
		btn_guardar.addActionListener(this);
		add(btn_guardar);
		
		btn_mod = new JButton("Modifica un Registro");
		btn_mod.setBounds(42, 360, 137, 20);
		btn_mod.addActionListener(this);
		add(btn_mod);
	    
	    btn_cancelar = new JButton("Cancelar");
	    btn_cancelar.setVisible(false);
	    btn_cancelar.setBounds(355, 360, 87, 20);
	    btn_cancelar.addActionListener(this);
	    add(btn_cancelar);

	    btn_darBaja = new JButton("Dar de baja");
	    btn_darBaja.setBackground(new Color(255, 153, 153));
	    btn_darBaja.setVisible(false);
	    btn_darBaja.setBounds(10, 360, 89, 20);
	    btn_darBaja.addActionListener(this);
	    add(btn_darBaja);
	    
	    btn_busca_otro = new JButton("Buscar");
	    btn_busca_otro.setVisible(false);
	    btn_busca_otro.addActionListener(this);
	    btn_busca_otro.setBounds(107, 360, 72, 20);
	    add(btn_busca_otro);

	    JSeparator separator = new JSeparator();
	    separator.setBounds(23, 288, 614, 2);
	    add(separator);
	    
	    JSeparator separator_1 = new JSeparator();
	    separator_1.setBounds(23, 353, 614, 2);
	    add(separator_1);
	    
	    btn_darAlta = new JButton("Dar Alta");
	    btn_darAlta.setBackground(new Color(102, 255, 153));
	    btn_darAlta.setBounds(10, 360, 89, 20);
	    btn_darAlta.addActionListener(this);
	    btn_darAlta.setVisible(false);
	    add(btn_darAlta);
 		
	    visible = Pruebita.visible;
	    
	    guardar = Pruebita.mntmGuardar;
		guardar.addActionListener(miOyente);
//		add(guardar);
	   	    
	}
	
	public void reinicia() 
	{
		btn_buscar.setEnabled(false);
		btn_buscar.setVisible(true);
		
		txt_buscar.setEnabled(false); 		
		txt_buscar.setText("");
		btn_cancelar.setVisible(false);
		
		btn_darBaja.setVisible(false);
		
		btn_mod.setVisible(true);
		
		btn_busca_otro.setVisible(false);
		txt_cve.setEnabled(true);
		
		btn_darAlta.setVisible(false);


		clean();
	}
	int obtieneCveGbl = -1, indiceEncontrado = -1;
	int vieneDeMod = 0;
	public boolean buscar() 
	{
		txt_cve.setEnabled(false);
		
		if(vieneDeMod == 0)
		{
			try {
				Integer.parseInt(txt_buscar.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "El campo de Buscar debe contener datos tipo Entero", "Error", JOptionPane.WARNING_MESSAGE);
	
				txt_buscar.setText("");
	    		txt_buscar.grabFocus();
	    		txt_cve.setEnabled(true);
				return false;
			}
		}
//		if(txt_cve.isEnabled()) {
//			if(txt_buscar.getText().equals("")) {
//				JOptionPane.showMessageDialog(null, "Llena los campos correctamente", "Error", JOptionPane.WARNING_MESSAGE);
//				txt_buscar.grabFocus();
//				return false;
//			}
//		}
//		if(txt_cve.isEnabled()) 
//		{
//			try {Integer.parseInt(txt_buscar.getText());} catch (Exception e2) 
//			{
//				JOptionPane.showMessageDialog(null, "El campo de Clave debe contener datos tipo Entero", "Error", JOptionPane.WARNING_MESSAGE);
//				txt_buscar.setText("");
//				txt_buscar.grabFocus();
//				return false;
//			}
//		}	
		btn_buscar.setVisible(false);
		btn_cancelar.setVisible(true);
		
		btn_busca_otro.setVisible(true);
		
		btn_mod.setVisible(false);
		btn_darBaja.setVisible(true);
		
		txt_buscar.grabFocus();
		try {
			int cacha[] = null;
			cacha = obtenerClaves(2);
			
			if(cacha[1] == 1)
			{
				btn_darAlta.setVisible(false);				
				btn_darBaja.setVisible(true);
			}
			else 
				if(cacha[1] == 2)
				{
					btn_darBaja.setVisible(false);
					btn_darAlta.setVisible(true);
				}
			
			obtieneCveGbl = cacha[0];
			if(obtieneCveGbl != -1 && obtieneCveGbl != -2)
			{
				indiceEncontrado = obtieneCveGbl;
				indiceEncontrado = indiceEncontrado * 62;
			}
			else return false;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		vieneDeMod = 0;
	    cont1 = 0;
	    cont2 = 0;
	    cont3 = 0;
		txt_cve.grabFocus();

		return true;
	}
	String alta = "true ";

	public void actionPerformed(ActionEvent e)
	{
		JButton b = new JButton();
		b = (JButton) e.getSource();

		if(b == btn_cancelar)
		{
			reinicia();
		}
		if(b == btn_darAlta)
		{
			btn_buscar.setVisible(false);
			btn_cancelar.setVisible(true);
			btn_darBaja.setVisible(true);
			btn_darAlta.setVisible(false);
//			reinicia();
//    		JOptionPane.showMessageDialog(null, "El registro ahora está inactivo", "Dar Baja", JOptionPane.INFORMATION_MESSAGE);

			
//			if(btn_darAlta.isVisible())
//			{
//			}
//			if(btn_darBaja.isVisible())
//			{
				alta = "true ";
//			}
			
		}
		if(b == btn_darBaja)
		{
			btn_buscar.setVisible(false);
			btn_cancelar.setVisible(true);
    		btn_darBaja.setVisible(false);
    		btn_darAlta.setVisible(true);
    		alta = "false";
//			reinicia();
//    		JOptionPane.showMessageDialog(null, "El registro ahora está inactivo", "Dar Baja", JOptionPane.INFORMATION_MESSAGE);
    		

		}
		if(b == btn_buscar)
		{
			vieneDeMod = 0;
			if(buscar())
				try {
					existe();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		if(b == btn_busca_otro)
		{
			vieneDeMod = 0;
			if(buscar())
				try {
					existe(); //llena campos con un dato en de la tabla
				} catch (IOException e1) {
					e1.printStackTrace();
				}		}
		if(b == btn_mod)
		{
			txt_buscar.setEnabled(true); 		
			btn_buscar.setEnabled(true);
			
			
		}
		if(b == btn_guardar)
		{
			String textoCompleto = "";
			String copiaAlta = alta;
			
			if(!txt_cve.isEnabled())
				if( indiceEncontrado != -1 && indiceEncontrado != -2)//reescribirá el archivo.dat
			{
				vieneDeMod = 1;
				buscar();
				try {
					Editado.editado(indiceEncontrado,
									txt_cve.getText(),
									txt_descripcion.getText(),
									txt_unidad.getText(),
									txt_existe.getText(),
									alta//////kjkhkhjjkk
									);
				} catch (IOException e1) {e1.printStackTrace();}
				indiceEncontrado = -1;
				obtieneCveGbl = -1;
				clean();
				reinicia();
				JOptionPane.showMessageDialog(null, "Registro Modificaco y Guardado con Éxito", "Modificación", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
				
			alta = "true ";
			if(!validaCampos())
				return;
			int[] cacha = null;
				try {
					cacha = obtenerClaves(1);
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
			if(cacha[0] == -1)
			{
				return;
			}
			
			for (int i = 0; i <30; i++) 
			{
				if(txt_cve.getText().length()<6)
					txt_cve.setText(txt_cve.getText()+" ");
				
				if(txt_unidad.getText().length()<15)
					txt_unidad.setText(txt_unidad.getText()+" ");
				
				if(txt_existe.getText().length()<6)
					txt_existe.setText(txt_existe.getText()+" ");
				
				if(txt_descripcion.getText().length()<30)
					txt_descripcion.setText(txt_descripcion.getText()+" ");
			}
			
			try {
				textoCompleto = ArchivoArtis.MuestraArchivo();
			} catch (IOException e2) {e2.printStackTrace();}
			

			
			textoCompleto = textoCompleto 				+
							txt_cve.getText() 			+ 
							txt_descripcion.getText() 	+
							txt_unidad.getText() 		+ 
							txt_existe.getText()
							+ alta;
			clean();
			reinicia();
			try {
				ArchivoArtis.Archivo(textoCompleto);
			} catch (IOException e1) {e1.printStackTrace();}
    		JOptionPane.showMessageDialog(null, "Registro Guardado con Éxito", "Guardado", JOptionPane.INFORMATION_MESSAGE);
    		alta = copiaAlta;
		}
	
	}

	private int[] obtenerClaves(int var) throws IOException { //var == 2 para mod-buscar, 1 para guardar y que no se repita
		
		int devuelve[] = new int[2];
		String textoCompleto = ArchivoArtis.MuestraArchivo();
		
		int vueltas = textoCompleto.length()/62, valida = 0, indice = -1;
		String claves[] = new String[vueltas];
		String verdad[] = new String[vueltas];
//		dfsf
	    for (int i = 0; i < vueltas; i++)
	    {
	    	int next  = 62 * i;
	    	int next2 = 62 * i;
	    	claves[i] = "";
	    	verdad[i] = "";

	    	
	    	for (int j = 0; j < 5; j++) { //5 porque true false tienen 5 caracteres
	    		verdad[i] = verdad[i] + textoCompleto.charAt(j+next+57);

	    	}
	    	for (int j = 0; j < 6; j++) { //6 porque las claves tienen 6 caracteres
				claves[i] = claves[i] + textoCompleto.charAt(j+next);
			}
	    }	    

	    if(var == 1) 
	    {
	    	  for (int i = 0; i < 6; i++) {//6 porque las claves tienen 6 caracteres
	  			if(txt_cve.getText().length() < 6)
	  				txt_cve.setText(txt_cve.getText()+" ");
	  		}
		    for (int i = 0; i < claves.length; i++) 
		    {
			    if(txt_cve.getText().equals(claves[i]))
		    	{	    		
		    		JOptionPane.showMessageDialog(null, "La Clave que intentas ingresar ya existe, prueba con otra", "Error", JOptionPane.WARNING_MESSAGE);
					txt_cve.setText("");
					devuelve[0] = -1;
					devuelve[1] = 0;
		    		return devuelve;
		    	}
			}
	    }
	    else
	    {
	    	String copia = txt_buscar.getText();
	    	for (int i = 0; i < 6; i++) { //6 porque las claves tienen 6 caracteres
		    	if(txt_buscar.getText().length() < 6)
		    		txt_buscar.setText(txt_buscar.getText()+" ");
		    }
	    	for (int i = 0; i < claves.length; i++) 
		    {
			    if(txt_buscar.getText().equals(claves[i]))
		    	{	    		
			    	indice = i;
			    	valida = 1;
		    	}
			}
	    
	    	
	    	if(valida == 1 && var == 2)
	    	{
	    		if(textoCompleto.charAt((indice * 62 + 57)) == 't')
	    			devuelve[1] = 1;
	    		else 
	    			devuelve[1] = 2;
	    		txt_buscar.setText(copia);
	    		devuelve[0] = indice;
	    		return devuelve;
//	    		return indice;
	    	}
	    	else if(valida == 0 && var == 2)
	    	{
//	    		try {
//	    			Integer.parseInt(txt_buscar.getText());
//	    		} catch (Exception e) {
//	    			JOptionPane.showMessageDialog(null, "El campo de Buscar debe contener datos tipo Entero", "Error", JOptionPane.WARNING_MESSAGE);
//	    			devuelve[0] = -1;
//	    			devuelve[1] = 0;
//	    			txt_buscar.setText("");
//		    		txt_buscar.grabFocus();
//		    		txt_cve.setEnabled(true);
//	    			return devuelve;
//				}
	    		if(vieneDeMod == 0)
	    		{
	    			JOptionPane.showMessageDialog(null, "La Clave que intentas ingresar en el campo Buscar no existe, prueba con otra", "Error", JOptionPane.WARNING_MESSAGE);
		    		txt_buscar.setText("");
		    		txt_buscar.grabFocus();
		    		txt_cve.setEnabled(true);
	    		}
	    		
				devuelve[0] = -1;
				devuelve[1] = 0;
	    		return devuelve;

	    	}
	    }
	    cont1 = 0;
	    cont2 = 0;
	    cont3 = 0;
		devuelve[0] = -2;
		devuelve[1] = 0;
		return devuelve;
	    	
	}

	private boolean validaCampos(){
		
		if(txt_cve.getText().equals("") || txt_unidad.getText().equals("") || txt_existe.getText().equals("") || txt_descripcion.getText().equals("") ) {
			JOptionPane.showMessageDialog(null, "Llena los campos correctamente", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		try {	Integer.parseInt(txt_cve.getText());} catch (Exception e2) 
		{
			JOptionPane.showMessageDialog(null, "El campo de Clave debe contener datos tipo Entero", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
			
		}		
		try {	Double.parseDouble(txt_existe.getText());} catch (Exception e2) 
		{
			JOptionPane.showMessageDialog(null, "El campo de Existencia debe contener datos tipo Double", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
			
		}		
		return true;
	}

	
	private void existe() throws IOException {
		
		
		String textoCompleto = ArchivoArtis.MuestraArchivo();
		String cve = "", desc = "", unidad = "", exist = "", activo = "";
		
		int vueltas = textoCompleto.length()/62;
		String claves[] = new String[vueltas];
		
	    for (int i = 0; i < vueltas; i++)
	    {
	    	int next = 62 * i;
	    	claves[i] = "";
	    	for (int j = 0; j < 6; j++) {
				claves[i] = claves[i] + textoCompleto.charAt(j+next);
			}
	    	
	    }
		String copia = txt_buscar.getText();
    	for (int i = 0; i < 6; i++) { //6 porque las claves tienen 6 caracteres
	    	if(txt_buscar.getText().length() < 6)
	    		txt_buscar.setText(txt_buscar.getText()+" ");
	    }
	    for (int i = 0; i < claves.length; i++) 
    	{
	    	int next = 62 * i;
	    	if(claves[i].equals(txt_buscar.getText())) {
	    		for (int j1 = next; j1 < (next + 62); j1++)
	    		{
	    			if((cve.length()) < ( 6) )
	    			{
	    				cve += textoCompleto.charAt(j1);
	    			}
	    			else if((desc.length()) < (30) )
	    				desc += textoCompleto.charAt(j1);
	    			else if((unidad.length()) < (15 ) )
	    				unidad += textoCompleto.charAt(j1);
	    			else if((exist.length()) < (6) )
	    				exist += textoCompleto.charAt(j1);
	    			else if((activo.length()) < (5 ) )
	    				activo += textoCompleto.charAt(j1);			    	
				}
	    	}
    	}
//    	txt_buscar.setText("");

    	txt_cve.setText(cve);
    	txt_descripcion.setText(desc);
    	txt_unidad.setText(unidad);
    	txt_existe.setText(exist);

	}
	public void clean() 
	{
		txt_cve.setText("");
		txt_unidad.setText("");
		txt_existe.setText("");
		txt_descripcion.setText("");

	}
	public void keyPressed(KeyEvent e)
	{
//		 if(e.getKeyCode()==KeyEvent.VK_ENTER){
//            System.exit(0);
//         }
	}

	public void keyReleased(KeyEvent arg0) {}
	public void keyTyped(KeyEvent e)
	{
    	if (txt_descripcion.isFocusOwner() && txt_descripcion.getText().length() == 30) 
    	{
    		e.consume(); 
    		Toolkit.getDefaultToolkit().beep();
    	}
    	if (txt_unidad.isFocusOwner() && txt_unidad.getText().length() == 15) 
    	{
    		e.consume(); 
    		Toolkit.getDefaultToolkit().beep();
    	}
    	if (txt_buscar.isFocusOwner() && txt_buscar.getText().length() == 6) 
    	{
    		e.consume(); 
    		Toolkit.getDefaultToolkit().beep();
    	}
    	if (txt_cve.isFocusOwner() && txt_cve.getText().length() == 6) 
    	{
    		e.consume(); 
    		Toolkit.getDefaultToolkit().beep();
    	}
    	if (txt_existe.isFocusOwner() && txt_existe.getText().length() == 6) 
    	{
    		e.consume(); 
    		Toolkit.getDefaultToolkit().beep();
    	}
	}

	int cont1 = 0, cont2 = 0, cont3 = 0;
	private JButton btn_darAlta;
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!txt_cve.isEnabled() && txt_descripcion.isFocusOwner() && cont1 == 0)
		{
			cont1++;
			txt_descripcion.setText("");
		}
		if(!txt_cve.isEnabled() && txt_unidad.isFocusOwner() && cont2 == 0)
		{
			cont2++;
			txt_unidad.setText("");
		}
		if(!txt_cve.isEnabled() && txt_existe.isFocusOwner() && cont3 == 0)
		{
			cont3++;
			txt_existe.setText("");
		}
		//pruebaaa
		if(txt_buscar.isFocusOwner())
			txt_buscar.setText("");
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {	
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	
	int contador = 0;
	
	class ManejadorArchivo implements ActionListener 
	{
		public void actionPerformed( ActionEvent evento )
		{
//			System.out.println("entra varias veces");
//			JMenuItem fuente = (JMenuItem) evento.getSource();
			
//			if ( fuente == guardar )
//			{
				if(!visible.visi)
				{
					JOptionPane.showMessageDialog(null, "Debes entrar antes a Actualizar Catálogo", "No Disponible", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String textoCompleto = "";
				String copiaAlta = alta;
				
				if(!txt_cve.isEnabled())
					if( indiceEncontrado != -1 && indiceEncontrado != -2)//reescribirá el archivo.dat
				{
					vieneDeMod = 1;
					buscar();
					try {
						Editado.editado(indiceEncontrado,
										txt_cve.getText(),
										txt_descripcion.getText(),
										txt_unidad.getText(),
										txt_existe.getText(),
										alta//////kjkhkhjjkk
										);
					} catch (IOException e1) {e1.printStackTrace();}
					indiceEncontrado = -1;
					obtieneCveGbl = -1;
					clean();
					reinicia();
					JOptionPane.showMessageDialog(null, "Registro Modificaco y Guardado con Éxito", "Modificación", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
					
				alta = "true ";

				if(!validaCampos())
					return;
				int[] cacha = null;
					try {
						cacha = obtenerClaves(1);
					} catch (IOException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}
				if(cacha[0] == -1)
				{
					return;
				}
				
				for (int i = 0; i <30; i++) 
				{
					if(txt_cve.getText().length()<6)
						txt_cve.setText(txt_cve.getText()+" ");
					
					if(txt_unidad.getText().length()<15)
						txt_unidad.setText(txt_unidad.getText()+" ");
					
					if(txt_existe.getText().length()<6)
						txt_existe.setText(txt_existe.getText()+" ");
					
					if(txt_descripcion.getText().length()<30)
						txt_descripcion.setText(txt_descripcion.getText()+" ");
				}
				
				try {
					textoCompleto = ArchivoArtis.MuestraArchivo();
				} catch (IOException e2) {e2.printStackTrace();}
				

				
				textoCompleto = textoCompleto 				+
								txt_cve.getText() 			+ 
								txt_descripcion.getText() 	+
								txt_unidad.getText() 		+ 
								txt_existe.getText()
								+ alta;
				clean();
				reinicia();
				try {
					ArchivoArtis.Archivo(textoCompleto);
				} catch (IOException e1) {e1.printStackTrace();}
	    		JOptionPane.showMessageDialog(null, "Registro Guardado con Éxito", "Guardado", JOptionPane.INFORMATION_MESSAGE);
	    		alta = copiaAlta;
			}
//			}
		
	}
}