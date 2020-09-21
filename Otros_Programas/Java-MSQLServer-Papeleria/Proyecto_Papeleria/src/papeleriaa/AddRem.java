package papeleriaa;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.SwingConstants;

//import papeleriaa.copy.Proveedor;

import java.awt.SystemColor;

public class AddRem extends JPanel implements ActionListener{
	  
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField campo_clave;
	private JTextField campo_cantidad;
	private JTextField campo_clave_elimina;
	private JTextField campo_buscar;
	JButton btnBuscar;
	JButton btnAceptar;
	JButton btnAceptar_elimina;
	public JButton btnProveedor;
	JRadioButton rdbtnEliminaArtculo;
	JRadioButton rdbtnAgregaArtculo;
	JComboBox comboBox_tipo;
	JComboBox comboBox;
	int ID, contcamp1 = 0, contcamp2 = 0;
	Conexion c;
	private JButton btnNuevo;
	private JTextArea campo_descripcion;
	private JTextField campo_preciou;
	private JLabel lblPrecioUVenta;
	private JTextField campo_preciou_venta;
	Proveedor prov;
	private JButton button;
	Factura f;
	/**
	 * Create the panel.
//	 * @throws Throwable 
	 */
	public void campo_provee(String s) {
//		System.out.println("valor de s = "+s);
//		campo_proveedor.setText(s);
//		campo_proveedor.revalidate();
//		System.out.println("entra aqui?");
		}
	
	public AddRem() throws Throwable {
		
		
		setBackground(SystemColor.menu);
		setBounds(190, 11, 328, 304);
		setLayout(null);
		
		campo_clave = new JTextField();
		campo_clave.setBounds(104, 47, 38, 23);
		campo_clave.setText(String.valueOf(Id()+1));
		add(campo_clave);
		campo_clave.setColumns(10);
		
		comboBox_tipo = new JComboBox();
		comboBox_tipo.setModel(new DefaultComboBoxModel(new String[] {"Acabados", "Papel", "Tintas", "Articulo Oficina", "Cart\u00F3n", "Etiquetas", "Solventes", "Pl\u00E1sticos"}));
		comboBox_tipo.setBounds(104, 79, 108, 25);
		add(comboBox_tipo);
		
		campo_descripcion = new JTextArea();
		campo_descripcion.setBounds(104, 113, 108, 23);
		add(campo_descripcion);
		
		campo_cantidad = new JTextField();
		campo_cantidad.setBounds(150, 146, 62, 23);
		add(campo_cantidad);
		campo_cantidad.setColumns(10);
////////////////////////////////////////RADIO LISTENER BUTTON

		rdbtnAgregaArtculo = new JRadioButton("Agrega art\u00EDculo");
		rdbtnAgregaArtculo.setBounds(6, 7, 109, 23);
		rdbtnAgregaArtculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					activado_agrega();
				} catch (Throwable e2) {	e2.printStackTrace();}
			try {
			} catch (Throwable e1) {e1.printStackTrace();}

		}

		
	});
		add(rdbtnAgregaArtculo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 212, 308, 2);
		add(separator);
		
		rdbtnEliminaArtculo = new JRadioButton("Venta de art\u00EDculo");
		rdbtnEliminaArtculo.setBounds(6, 212, 136, 23);
		rdbtnEliminaArtculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				rdbtnEliminaArtculo.setEnabled(false);
//				rdbtnAgregaArtculo.setEnabled(true);
				activado_elimina();
				campo_clave_elimina.setEnabled(true);
//				comboBox_tipo.setEnabled(false);
//				campo_tipo_elimina.setEnabled(false);
			try {
			} catch (Throwable e1) {e1.printStackTrace();}

		}
	});
		add(rdbtnEliminaArtculo);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setBackground(Color.DARK_GRAY);
		btnAceptar.setBounds(229, 146, 89, 23);
		btnAceptar.addActionListener(this);
		add(btnAceptar);
		
		campo_clave_elimina = new JTextField();
		campo_clave_elimina.setBounds(118, 238, 69, 23);
		add(campo_clave_elimina);
		campo_clave_elimina.setColumns(10);
		
		btnAceptar_elimina = new JButton("Aceptar");
		btnAceptar_elimina.setForeground(Color.WHITE);
		btnAceptar_elimina.setBackground(Color.DARK_GRAY);
		btnAceptar_elimina.setBounds(197, 238, 89, 23);
		btnAceptar_elimina.addActionListener(this);
		add(btnAceptar_elimina);
		
		JLabel lblClave = new JLabel("Clave:");
		lblClave.setBounds(22, 51, 46, 14);
		add(lblClave);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(22, 84, 46, 14);
		add(lblTipo);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setBounds(22, 118, 86, 14);
		add(lblDescripcion);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(22, 149, 62, 14);
		add(lblCantidad);
		
		campo_buscar = new JTextField();
		campo_buscar.setBounds(159, 8, 53, 23);
		add(campo_buscar);
		campo_buscar.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setBackground(Color.DARK_GRAY);
		btnBuscar.setBounds(229, 7, 89, 23);
		btnBuscar.addActionListener(this);
		add(btnBuscar);

		ButtonGroup bg = new ButtonGroup();

		bg.add( rdbtnEliminaArtculo );
		bg.add( rdbtnAgregaArtculo ); 
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setBackground(Color.DARK_GRAY);
		btnNuevo.setBounds(143, 47, 69, 23);
		btnNuevo.addActionListener(this);
		add(btnNuevo);
		

		
		JTextArea txtrIngresaClavePara = new JTextArea();
		txtrIngresaClavePara.setEnabled(false);
		txtrIngresaClavePara.setEditable(false);
		txtrIngresaClavePara.setFont(new Font("Arial", Font.PLAIN, 11));
		txtrIngresaClavePara.setWrapStyleWord(true);
		txtrIngresaClavePara.setLineWrap(true);
		txtrIngresaClavePara.setText("Ingresa clave para  buscar art\u00EDculo y luego la cantidad que deseas eliminar de este.");
		txtrIngresaClavePara.setBounds(10, 265, 308, 32);
		add(txtrIngresaClavePara);
		
		JLabel lblClave_1 = new JLabel("por Clave:");
		lblClave_1.setBounds(39, 242, 69, 14);
		add(lblClave_1);
		
		campo_preciou = new JTextField();
		campo_preciou.setEnabled(false);
		campo_preciou.setBounds(229, 60, 89, 23);
		
		
		campo_preciou.addKeyListener(new KeyAdapter()
				{
					   public void keyTyped(KeyEvent e)
					   {
					      char caracter = e.getKeyChar();
					      String cadena = campo_preciou.getText();
//					      System.out.println(cadena);
					      // Verificar si la tecla pulsada no es un digito
					      if(((caracter < '0') ||
					         (caracter > '9')) &&
					         (caracter != '\b' /*corresponde a BACK_SPACE*/)
					    	)
					      
					      {
					         e.consume();  // ignorar el evento de teclado
					      }
					      if(caracter > '0' &&  caracter < '9')
					    	  contcamp1++;
//						   )
					      if(caracter == '\b')
					    	  contcamp1--;
					      if(contcamp1 < 0)
					    	  contcamp1 = 0;

					     
					     }
					});
			

		add(campo_preciou);
		campo_preciou.setColumns(10);
		
		campo_cantidad.addKeyListener(new KeyAdapter()
		{
			   public void keyTyped(KeyEvent e)
			   {
			      char caracter = e.getKeyChar();
			      // Verificar si la tecla pulsada no es un digito
			      if(((caracter < '0') ||
			    		  (caracter > '9')) &&
			    		  (caracter != '\b' /*corresponde a BACK_SPACE*/)
			    		  && caracter != '-')
			    	  
			      {
			    	  e.consume();  // ignorar el evento de teclado
			      }
			      
			      if(caracter > '0' &&  caracter < '9')
			    	  contcamp2++;
//				   )
			      if(caracter == '\b')
			    	  contcamp2--;
			      if(contcamp2 < 0)
			    	  contcamp2 = 0;
			      
			   }
		});

		
		JLabel lblPrecioUnitario = new JLabel("Precio u/ compra");
		lblPrecioUnitario.setBounds(222, 41, 96, 14);
		add(lblPrecioUnitario);
		
		lblPrecioUVenta = new JLabel("Precio u/ venta");
		lblPrecioUVenta.setBounds(229, 90, 89, 14);
		add(lblPrecioUVenta);
		
		campo_preciou_venta = new JTextField();
		campo_preciou_venta.setEnabled(false);
		campo_preciou_venta.addKeyListener(new KeyAdapter()
		{
			   public void keyTyped(KeyEvent e)
			   {
			      char caracter = e.getKeyChar();
			      String cadena = campo_preciou.getText();
			      // Verificar si la tecla pulsada no es un digito
			      if(((caracter < '0') ||
			    		  (caracter > '9')) &&
			         (caracter != '\b' /*corresponde a BACK_SPACE*/)
			    	)
			      
			      {
			         e.consume();  // ignorar el evento de teclado
			      }
	     }
	});
		campo_preciou_venta.setBounds(229, 114, 89, 23);
		add(campo_preciou_venta);
		campo_preciou_venta.setColumns(10);
		
		btnProveedor = new JButton("Proveedor");
		btnProveedor.addActionListener(this);
		btnProveedor.setBounds(22, 178, 89, 23);
		add(btnProveedor);

		prov = new Proveedor();
		prov.setVisible(false);
		
		
		comboBox = new JComboBox();
		ActualizaBox();

		comboBox.setBounds(125, 181, 150, 20);
		add(comboBox);
		
		button = new JButton("");
		button.setBounds(281, 180, 33, 23);
		button.addActionListener(this);
		add(button);
		f = new Factura();
		f.setVisible(false);
		///INICIALIZACIÓN
		
		activado_elimina();
		rdbtnEliminaArtculo.setSelected(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton b = (JButton) e.getSource();
		 c = new Conexion();
		 ResultSet rs = null;
		 Statement st = null;
		 int conta = 0;
		 try {
			 
			 st = c.conexion().createStatement();
			 rs = st.executeQuery("Select * from articulos");
		} 
		 catch (SQLException e1) {	e1.printStackTrace();}
		 catch (Throwable e1) {	e1.printStackTrace();}
		 
		if(b == btnBuscar) 
		{
			
			 try {
				while(rs.next()){
					 
				        int ID = rs.getInt(1);
				        if(ID == Integer.parseInt(campo_buscar.getText()))
				        {
				        	btnNuevo.setEnabled(true);
				        	campo_cantidad.setText("0");
				        	comboBox_tipo.setEnabled(false);
				        	campo_descripcion.setEditable(false);
				        	comboBox.setEnabled(false);
				        	button.setEnabled(false);

				    		campo_clave.setText(String.valueOf(rs.getInt(1)));
				    		comboBox_tipo.setSelectedItem(String.valueOf(rs.getString(2)));
				    		campo_descripcion.setText(rs.getString(3));
				    		campo_preciou.setText(String.valueOf(rs.getInt(5)));
				    		campo_preciou_venta.setText(String.valueOf(rs.getInt(6)));
				    		
				    		comboBox.setSelectedItem((String)rs.getString(7));
				        	return;
				        }
				    }
			}
			 catch (SQLException e1) {e1.printStackTrace();}
			 catch (Throwable e1)
			 {	
				 JOptionPane.showMessageDialog(null, "Llena campo buscar correctamente", "Advertencia",
				        JOptionPane.WARNING_MESSAGE);
				 return;
			 }
			 
			 JOptionPane.showMessageDialog(null, "Esta clave no está registrada en ningún artículo", "Advertencia",
				        JOptionPane.WARNING_MESSAGE);
		}
		if(b == btnProveedor) {
			try {
				 
				 prov.setVisible(true);
				 
			} catch (Throwable e1) {	e1.printStackTrace();	}
		}
		if( b == button) {
			try {
				ActualizaBox();
			} catch (Throwable e1) {
				e1.printStackTrace();
			}
		}
		if(b == btnAceptar)
		{
			int o = 0;
			try {
				if(!campo_cantidad.getText().equals("0"))
					o = JOptionPane.showConfirmDialog(null, "Costo total "+

					Integer.parseInt(campo_preciou.getText()) *
					Integer.parseInt(campo_cantidad.getText()) 

							);
				
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Llena correctamente los campos", "Advertencia",
				        JOptionPane.WARNING_MESSAGE);
				return;
			}
		if(o != 0)
			return;
		String entras = "Entrada";
			if(btnNuevo.isEnabled()) 
			{
				 ResultSet rs1 = null;
				 Statement st1 = null;
				 try {
					 
					 st1 = c.conexion().createStatement();
					 rs1 = st1.executeQuery("Select cve, max(cantidad) from articulos "
					 		+ "where cve = "+Integer.parseInt(campo_clave.getText())
					 		+ " group by cve");
					 
					 while(rs1.next())
					 {
						 int cve = rs1.getInt(1);
						 int cant = rs1.getInt(2);
						 
						 if(cve == Integer.parseInt(campo_clave.getText())) {
							 
							 st.execute("update articulos set precioCompra = "+Integer.parseInt(campo_preciou.getText())
							 +" where cve = "+cve);
							 
							 st.execute("update articulos set precioVenta = "+Integer.parseInt(campo_preciou_venta.getText())
							 +" where cve = "+cve);
							 
								st.execute("insert into historial values("
										+ "'"+ entras +"' "
										+ ",'"+String.valueOf(comboBox_tipo.getSelectedItem())+"'"
										+ ",'"+campo_descripcion.getText()+"'"
										+ ","+Integer.parseInt(campo_cantidad.getText())
										+ ","+Integer.parseInt(campo_preciou.getText())
										+ ","+Integer.parseInt(campo_preciou_venta.getText())
										+ ", getdate() "
										+ ",'"+String.valueOf(comboBox.getSelectedItem())+"')");
								
							 cant = cant + Integer.parseInt(campo_cantidad.getText());
							 st1.execute("update articulos set cantidad = "+cant+" where cve = "+Integer.parseInt(campo_clave.getText()));
							 JOptionPane.showMessageDialog(null, "Datos actualizados");

								campo_cantidad.setText("0");
							 return;
						 }
					 }
					 
				} 
				 catch (SQLException e1) {	e1.printStackTrace();}
				 catch (Throwable e1) {	 JOptionPane.showMessageDialog(null, "Llena campo cantidad correctamente", "Advertencia",
					        JOptionPane.WARNING_MESSAGE);
				 return;}
				 
			
			}
			else {
				
		        try {
					st.execute("insert into articulos values("
							+ "'"+String.valueOf(comboBox_tipo.getSelectedItem())+"'"
							+ ", '"+campo_descripcion.getText()+"'"
							+ ","+Integer.parseInt(campo_cantidad.getText())
							+ ","+Integer.parseInt(campo_preciou.getText())
							+ ","+Integer.parseInt(campo_preciou_venta.getText())
							+ ",'"+String.valueOf(comboBox.getSelectedItem())+"')");
						
					st.execute("insert into historial values("
							+ "'"+ entras +"' "
							+ ",'"+String.valueOf(comboBox_tipo.getSelectedItem())+"'"
							+ ",'"+campo_descripcion.getText()+"'"
							+ ","+Integer.parseInt(campo_cantidad.getText())
							+ ","+Integer.parseInt(campo_preciou.getText())
							+ ","+Integer.parseInt(campo_preciou_venta.getText())
							+ ", getdate() "
							+ ",'"+String.valueOf(comboBox.getSelectedItem())+"')");
					JOptionPane.showMessageDialog(null, "Registro exitoso");

				} catch (SQLException e1) {e1.printStackTrace();}
		        catch (Throwable e1) { JOptionPane.showMessageDialog(null, "Llena campo cantidad correctamente", "Advertencia",
				        JOptionPane.WARNING_MESSAGE);}
				try {
					campo_clave.setText(String.valueOf(Id()+1));
					campo_cantidad.setText("");

				} catch (Throwable e1) {e1.printStackTrace();}
			}

		}
		if(b == btnNuevo)
		{
			
			try {
				campo_descripcion.setEditable(true);
				campo_descripcion.setText("");
				campo_buscar.setText("");
				activado_agrega();
				campo_cantidad.setText("");
			} catch (Throwable e1) {e1.printStackTrace();}

		}
		if(b == btnAceptar_elimina) {
			int total = 0;
//			if(campo_tipo_elimina.isEnabled()) {
			String entras = "Salida";
//			}
			if(campo_clave_elimina.isEnabled()) {

				 try {
					Statement st2 = c.conexion().createStatement();

					ResultSet rs2 = st2.executeQuery("Select * from articulos");
					 while(rs2.next()) 
						 
						 if(rs2.getInt(1) == Integer.parseInt(campo_clave_elimina.getText())) 
						 {
							 int cve = rs2.getInt(1);
							 String tipo = rs2.getString(2);
							 String desc = rs2.getString(3);
							 int cant = rs2.getInt(4);
							 int precioC = rs2.getInt(5);
							 int precioV = rs2.getInt(6);
							 String prov = rs2.getString(7);
							 String sal = "";
							 int val = 0;
							 
						 	 sal = JOptionPane.showInputDialog(cant+" unidades del artículo con clave \""+cve+"\" y de tipo "+tipo+". Precio unitario = "+precioV+"\n"
						 		+ "Ingresa cantidad a vender: ");
//							 	 System.out.println(sal);
						 	 if(sal == null)
						 		 return;
						 	  val = Integer.parseInt(sal);
						 	  if(val>cant)
						 	  {
						 		  JOptionPane.showMessageDialog(null, "No se pudo hacer el borrado\n"
						 		  									+ "Cantidad a eliminar ingresada es mayor a la \n"
						 				  							+ "existente, vuelve a introducir los datos", "Error",JOptionPane.ERROR_MESSAGE);
						 		  return;
						 	  }
						 	  total = (Integer.parseInt(sal)* precioV );
						 	  int o = JOptionPane.showConfirmDialog(null,"Continuar con compra\nTotal = "+ total/*(Integer.parseInt(sal)* precioV )*/);
						 	  if(o != 0)
						 		  return;
						 	 st.execute("insert into historial values("
										+ "'"+ entras +"' "
//											+ ",'"+cve+"'"
										+ ",'"+tipo+"'"
										+ ",'"+desc+"'"
										+ ","+val
										+ ","+precioC
										+ ","+precioV
										+ ", getdate()"
										+ ",'"+prov+"')");
						 	//////////////////////////////////////////////////////___________________
						 	 cant = cant - val;
						 	 st2.execute("update articulos set cantidad = "+cant+" where cve = "+Integer.parseInt(campo_clave_elimina.getText()));
						 	 JOptionPane.showMessageDialog(null, "Datos actualizados");
						 	 campo_clave_elimina.setText("");
						 	 int i = JOptionPane.showConfirmDialog(null,"¿Desea hacer factura? ");
						 	 if(i == 0) 
						 	 {
						 		 f.llenar_campos(tipo, Integer.parseInt(sal),desc, total);
						 		 f.setVisible(true);
						 	 }
						
						 return;
							 	  //--------------
					
						 }
					 
//					 c = new Conexion();
//					 Statement st2 = c.conexion().createStatement();
//					 st2.execute("update");
				} 
				catch (SQLException e1) {	e1.printStackTrace();	} 
				catch (Throwable e1)	{	
//					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Llena campos con valores válidos", "Advertencia", JOptionPane.WARNING_MESSAGE);
					return;
					}
			}
		}
	}
	private int Id() throws Throwable
	{
		 c = new Conexion();
		 Statement st = c.conexion().createStatement();
		 ResultSet rs = st.executeQuery("Select max(cve) from articulos");
		        
		 while(rs.next()) 
			 ID = rs.getInt(1);
		        
		return ID;
	}
	private void activado_agrega() throws Throwable {
		
		btnAceptar.setEnabled(true);
		btnBuscar.setEnabled(true);
		campo_clave.setEnabled(false);
		campo_clave.setText(String.valueOf(Id()+1));;
		comboBox_tipo.setEnabled(true);
		campo_descripcion.setEnabled(true);;
		campo_preciou.setEnabled(true);
		campo_preciou_venta.setEnabled(true);
		campo_cantidad.setEnabled(true);;
		campo_buscar.setEnabled(true);
		btnProveedor.setEnabled(true);
		comboBox.setEnabled(true);
		button.setEnabled(true);
		
		campo_clave_elimina.setEnabled(false);
		campo_clave_elimina.setText("");
		btnAceptar_elimina.setEnabled(false);
		btnNuevo.setEnabled(false);
		
	}
	private void activado_elimina() {
		btnAceptar.setEnabled(false);
		btnBuscar.setEnabled(false);
		campo_clave.setEnabled(false);
		campo_clave.setText("");
		comboBox_tipo.setEnabled(false);
		btnProveedor.setEnabled(false);
		comboBox.setEnabled(false);
		button.setEnabled(false);

		campo_descripcion.setEnabled(false);;
		campo_descripcion.setText("");
		campo_cantidad.setEnabled(false);;
		campo_cantidad.setText("");
		campo_buscar.setEnabled(false);
		campo_buscar.setText("");
		campo_preciou.setEnabled(false);
		campo_preciou.setText("");
		campo_preciou_venta.setEnabled(false);
		campo_preciou_venta.setText("");
		
		campo_clave_elimina.setEnabled(true);
		btnAceptar_elimina.setEnabled(true);
		btnNuevo.setEnabled(false);
	}

	public void ActualizaBox() throws Throwable {
		
		int cont = 0;
		String[] guardaAntes = new String[cont];
		String[] guarda = new String[cont];			//guarda = 0
		try {
		Statement st1 = c.conexion().createStatement();	
		ResultSet rs1 = st1.executeQuery("Select * from proveedor");
		while(rs1.next()) {
			
			String cve = rs1.getString(1);
				
			for (int i = 0; i < cont; i++) {    //i = 0, i < 0			
					guardaAntes[i] = guarda[i];				 //guarda[0] = 1erdato	
													 //guarda[0] = 1erdato	
				}
			guarda = new String[guarda.length+1];
			for (int i = 0; i < guarda.length; i++) {    //i = 0, i < 1	
				if(i<guarda.length-1)
					guarda[i] = guardaAntes[i];					 //guarda[0] = 1erdato
				if(i == guarda.length-1)
					guarda[i] = cve;					 //guarda[0] = 1erdato	
			}
			guardaAntes = new String[guarda.length+1];
			
	        cont++;				
		}
		comboBox.setModel(new DefaultComboBoxModel(guarda));
		} catch (Throwable e1) {e1.printStackTrace();}
	}

}
