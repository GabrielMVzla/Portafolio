package proy;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import java.awt.Panel;

public class Panel_Miembros extends JPanel implements ActionListener {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
	java.util.Date date = null;
	
	int ID = 0;
	JLabel lblAgregaMiembro;			JButton btnBorrar;
	JLabel lblClave;
	JLabel lblNombre;
	JLabel lblApellido;
	 JTextField member_no;
	 JTextField Nombre;
	 JTextField Apellido;
	 JRadioButton radio_Adulto;
	 JRadioButton radio_Joven;
	 JLabel lblClave_1;
	 JLabel lblCiudad;
	 JLabel lblEstado;
	 JLabel lblExpiracin;
	 JLabel lblClave_2;
	 JLabel lblCveTutor;
	 JLabel lblCumpleaos;
	 JTextField clave_adulto;
	 JTextField ciudad_Adulto;
	 JTextField estado_Adulto;
	 JTextField expiración_Adulto;
	 JTextField clave_Joven;
	 JTextField cve_Joven_Tutor;
	 JButton btnConsultaMember;
	 JButton btnInsertar;
	 JButton btnCancelar;
	 Conexion c;
	 JDateChooser dateChooser ;
	 private JLabel lblCalle;
	 private JLabel lblZipCode;
	 private JLabel lblTelfono;
	 private JTextField calle_adulto;
	 private JTextField zipcode_adulto;
	 private JTextField adulto_telefono;
	 String longi = "";
	 int longitud = 0;
	 Panel_Miembros_Consultas_ventana panelMiembrosCv;
	 private JTable table;
	 DefaultTableModel modelo;
	 private JTextField campo_elimina;
	 private JButton btnEliminar;
	 JLabel lblIClaveDel;

	public Panel_Miembros() throws Throwable {
		
		
		setBackground(new Color(240, 255, 255));
		setLayout(null);
		setBounds(110,10, 521, 290); //415 - 

		lblAgregaMiembro = new JLabel("Agrega miembro:");
		lblAgregaMiembro.setBounds(10, 7, 126, 26);
		add(lblAgregaMiembro);
		
		lblClave = new JLabel("Clave:");
		lblClave.setBounds(10, 44, 46, 14);
		add(lblClave);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 69, 46, 14);
		add(lblNombre);
		
		lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(10, 94, 46, 14);
		add(lblApellido);
		///////////////////////----------TEXTFIELD--------\\\\\\\\\\\\\\\\\
		member_no = new JTextField();
		member_no.setFont(new Font("Tahoma", Font.PLAIN, 13));
		member_no.setBackground(new Color(255, 255, 255));
		member_no.setText(String.valueOf(Id()+1));
		member_no.setEnabled(false);
		member_no.setBounds(76, 35, 86, 30);
		add(member_no);
		member_no.setColumns(10);
		
		Nombre = new JTextField();
		Nombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Nombre.setBackground(new Color(255, 255, 255));
		Nombre.setText("");
		Nombre.setBounds(76, 66, 86, 30);
		add(Nombre);
		Nombre.setColumns(10);
		
		Apellido = new JTextField();
		Apellido.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Apellido.setBackground(new Color(255, 255, 255));
		Apellido.setText("");
		Apellido.setBounds(76, 98, 86, 30);
		add(Apellido);
		Apellido.setColumns(10);
										
										
										
//////////--------DATOS DE NUEVO MIEMBRO---------\\\\\\\\\\
										//			LABEL
		lblClave_1 = new JLabel("Clave:");
		lblClave_1.setBounds(172, 44, 62, 14);
		add(lblClave_1);
		
		lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(172, 69, 62, 14);
		add(lblCiudad);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(172, 94, 62, 14);
		add(lblEstado);
		
		lblExpiracin = new JLabel("Expiraci\u00F3n:");
		lblExpiracin.setBounds(172, 119, 62, 14);
		add(lblExpiracin);
		
		lblClave_2 = new JLabel("Clave:");
		lblClave_2.setBounds(172, 193, 62, 14);
		add(lblClave_2);
		
		lblCveTutor = new JLabel("Cve Tutor");
		lblCveTutor.setBounds(172, 218, 62, 14);
		add(lblCveTutor);
		
		lblCumpleaos = new JLabel("Cumplea\u00F1os:");
		lblCumpleaos.setBounds(172, 246, 62, 14);
		add(lblCumpleaos);
										
//////////-------	TXT_FIELD   ---------\\\\\\\\\\
										
		clave_adulto = new JTextField();
		clave_adulto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		clave_adulto.setBackground(SystemColor.inactiveCaption);
		clave_adulto.setText(String.valueOf(Id()+1));
		clave_adulto.setEnabled(false);
		clave_adulto.setBounds(244, 35, 86, 26);
		add(clave_adulto);
		clave_adulto.setColumns(10);
		
		ciudad_Adulto = new JTextField();
		ciudad_Adulto.setFont(new Font("Tahoma", Font.PLAIN, 13));

		ciudad_Adulto.setBackground(new Color(210,238,255));
//		ciudad_Adulto.setBackground(SystemColor.inactiveCaption);
//		ciudad_Adulto.setText("");
		ciudad_Adulto.setBounds(244, 60, 86, 26);
		add(ciudad_Adulto);
		ciudad_Adulto.setColumns(10);
		
		estado_Adulto = new JTextField();
		estado_Adulto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		estado_Adulto.setBackground(new Color(210,238,255));
//		estado_Adulto.setText("");
		estado_Adulto.setBounds(244, 85, 86, 26);
		add(estado_Adulto);
		estado_Adulto.setColumns(10);
		
		expiración_Adulto = new JTextField();
		expiración_Adulto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		expiración_Adulto.setBackground(SystemColor.inactiveCaption);
//		expiración_Adulto.setText("");
		expiración_Adulto.setEnabled(false);
		expiración_Adulto.setBounds(244, 110, 86, 26);
		add(expiración_Adulto);
		expiración_Adulto.setColumns(10);
		
		clave_Joven = new JTextField();
		clave_Joven.setFont(new Font("Tahoma", Font.PLAIN, 13));
		clave_Joven.setBackground(new Color(245, 245, 220));
//		clave_Joven.setText("");
		clave_Joven.setText(String.valueOf(Id()+1));
		clave_Joven.setEnabled(false);
		clave_Joven.setBounds(244, 177, 86, 33);
		add(clave_Joven);
		clave_Joven.setColumns(10);
		
		cve_Joven_Tutor = new JFormattedTextField (new Integer(3));
//		cve_Joven_Tutor = new JTextField();
		cve_Joven_Tutor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cve_Joven_Tutor.setBackground(new Color(245, 245, 220));
		cve_Joven_Tutor.addKeyListener(new KeyAdapter()
		{
		   public void keyTyped(KeyEvent e)
		   {
		      char caracter = e.getKeyChar();

		      // Verificar si la tecla pulsada no es un digito
		      if(((caracter < '0') ||
		         (caracter > '9')) &&
		         (caracter != '\b' /*corresponde a BACK_SPACE*/))
		      {
		         e.consume();  // ignorar el evento de teclado
		      }
		   }
		});
//		cve_Joven_Tutor.setText("");
//		JTextField cve_Joven_Tutor = new JTextField(10);
		cve_Joven_Tutor.addKeyListener(new KeyAdapter()
		{
		   public void keyTyped(KeyEvent e)
		   {
		      char caracter = e.getKeyChar();

		      // Verificar si la tecla pulsada no es un digito
		      if(((caracter < '0') ||
		         (caracter > '9')) &&
		         (caracter != '\b' /*corresponde a BACK_SPACE*/))
		      {
		         e.consume();  // ignorar el evento de teclado
		      }
		   }
		});
		cve_Joven_Tutor.setBounds(244, 209, 86, 30);
		add(cve_Joven_Tutor);
		cve_Joven_Tutor.setColumns(10);
		
//		activado();//-----------------------------------------------------------------------------------
			
		
		radio_Adulto = new JRadioButton("Adulto");
		radio_Adulto.setHorizontalAlignment(SwingConstants.CENTER);
		radio_Adulto.setFont(new Font("Source Sans Pro Black", Font.BOLD, 12));
		radio_Adulto.setBackground(new Color(240, 255, 255));
		radio_Adulto.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			activado_adulto();
		try {
			clave_adulto.setText(String.valueOf(Id()+1));
		} catch (Throwable e1) {e1.printStackTrace();}

	}
});
			radio_Adulto.setBounds(181, 7, 62, 23);
	
		add(radio_Adulto);
		
		radio_Joven = new JRadioButton("Joven");
		radio_Joven.setFont(new Font("Source Sans Pro Black", Font.BOLD, 12));
		radio_Joven.setBackground(Color.CYAN);
		radio_Joven.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			activado_joven();
			
		try {
			clave_Joven.setText(String.valueOf(Id()+1));
		} catch (Throwable e1) {e1.printStackTrace();}
		
	}
});
		radio_Joven.setBounds(181, 147, 62, 23);
		add(radio_Joven);
		ButtonGroup bg = new ButtonGroup();

		bg.add( radio_Joven );
		bg.add( radio_Adulto ); 
		
		btnConsultaMember = new JButton("Consulta member");
		btnConsultaMember.setForeground(SystemColor.window);
		btnConsultaMember.setBackground(Color.DARK_GRAY);
		btnConsultaMember.setBounds(374, 9, 133, 23);
		btnConsultaMember.addActionListener(this);
		add(btnConsultaMember);
		
//		Botones boton = new Botones();

		btnInsertar = new JButton("Insertar");
		btnInsertar.setForeground(SystemColor.window);
		btnInsertar.setBackground(Color.DARK_GRAY);
		btnInsertar.addActionListener(this);
		btnInsertar.setBounds(418, 242, 89, 23);
		add(btnInsertar);
		
//		btnCancelar = new JButton("Cancelar");
//		btnCancelar.setForeground(SystemColor.window);
//		btnCancelar.setBackground(Color.DARK_GRAY);
//		btnCancelar.addActionListener(this);
//		btnCancelar.setBounds(418, 242, 89, 23);
//		add(btnCancelar);
		
		dateChooser = new JDateChooser();
		dateChooser.setEnabled(false);
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addKeyListener(new KeyAdapter()
				{
					   public void keyTyped(KeyEvent e)
					   {
					      char caracter = e.getKeyChar();

					      // Verificar si la tecla pulsada no es un digito
					      if(((caracter < '0') ||
					         (caracter > '9')) &&
					         (caracter != '\b' /*corresponde a BACK_SPACE*/)
					    	  && caracter != '-'
					    	  && caracter != '/')
					      {
					         e.consume();  // ignorar el evento de teclado
					      }
					   }
					});
			}
		});
		dateChooser.setBackground(new Color(245, 245, 220));
		dateChooser.setBounds(244, 239, 119, 26);
		add(dateChooser);
		
		lblCalle = new JLabel("Calle: ");
		lblCalle.setBounds(350, 44, 46, 14);
		add(lblCalle);
		
		lblZipCode = new JLabel("ZipCode");
		lblZipCode.setBounds(350, 69, 46, 14);
		add(lblZipCode);
		
		lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(350, 94, 46, 14);
		add(lblTelfono);
		
		calle_adulto = new JTextField();
		calle_adulto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		calle_adulto.setEnabled(false);
		calle_adulto.setColumns(10);
		calle_adulto.setBackground(new Color(210,238,255));
		calle_adulto.setBounds(421, 35, 86, 26);
		add(calle_adulto);
		
		zipcode_adulto = new JTextField();
		zipcode_adulto.setFont(new Font("Tahoma", Font.PLAIN, 13));
		zipcode_adulto.setEnabled(false);
		zipcode_adulto.setColumns(10);
		zipcode_adulto.setBackground(new Color(210,238,255));
		zipcode_adulto.addKeyListener(new KeyAdapter()
		{
			   public void keyTyped(KeyEvent e)
			   {
				  
			      char caracter = e.getKeyChar();
			      
			      if(longitud > 0 && caracter == '\b') {
			    	  longitud--;
			      }
			      // Verificar si la tecla pulsada no es un digito
			      else if(((caracter < '0') ||
			         (caracter > '9')) &&
			         (caracter != '\b' /*corresponde a BACK_SPACE*/)
			    	 || longitud>=5)
			      {
			         e.consume();  // ignorar el evento de teclado
			      }
			      else  if(caracter != '\b')
			    	  longitud++;
			      System.out.println("tamaño de longi = "+longitud);
			      
		
			   }
			});
		
		zipcode_adulto.setBounds(421, 58, 86, 26);
		add(zipcode_adulto);
		
		adulto_telefono = new JTextField();
		adulto_telefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		adulto_telefono.setEnabled(false);
		adulto_telefono.setColumns(10);
		adulto_telefono.setBackground(new Color(210,238,255));
		adulto_telefono.setBounds(421, 82, 86, 26);
		adulto_telefono.addKeyListener(new KeyAdapter()
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
			   }
			});
		add(adulto_telefono);
		
		radio_Adulto.setSelected(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(350, 140, 157, 67);
		add(scrollPane);
		modelo = new DefaultTableModel();
		table = new JTable(modelo);
//		table = new JTable();
//		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"clave", "Nombre"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblltimoRegistro = new JLabel("\u00DAltimos registro");
		lblltimoRegistro.setBounds(350, 119, 89, 14);
		add(lblltimoRegistro);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(10, 177, 157, 83);
		add(panel);
		panel.setLayout(null);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(67, 41, 81, 23);
		btnEliminar.addActionListener(this);
		panel.add(btnEliminar);
		
		campo_elimina = new JTextField();
		campo_elimina.setBounds(10, 41, 53, 22);
		adulto_telefono.addKeyListener(new KeyAdapter()
		{
			   public void keyTyped(KeyEvent e)
			   {
			      char caracter = e.getKeyChar();

			      // Verificar si la tecla pulsada no es un digito
			      if(((caracter < '0') ||
			         (caracter > '9')) &&
			         (caracter != '\b' /*corresponde a BACK_SPACE*/))
			      {
			         e.consume();  // ignorar el evento de teclado
			      }
			   }
			});
		panel.add(campo_elimina);
		campo_elimina.setColumns(10);
		
		lblIClaveDel = new JLabel("Clave del miembro:");
		lblIClaveDel.setBounds(10, 16, 138, 14);
		panel.add(lblIClaveDel);
		
		JLabel lblEliminaUnMiembro = new JLabel("Elimina un miembro");
		lblEliminaUnMiembro.setBounds(10, 151, 117, 14);
		add(lblEliminaUnMiembro);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(210, 238, 255));
		panel_1.setBounds(244, 7, 22, 23);
		add(panel_1);
		
		Panel panel_2 = new Panel();
		panel_2.setBackground(new Color(245, 245, 220));
		panel_2.setBounds(244, 145, 22, 26);
		add(panel_2);
		activado_adulto();
		panelMiembrosCv = new Panel_Miembros_Consultas_ventana();
		panelMiembrosCv.setVisible(false);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton b = (JButton) e.getSource();
		try {
			c = new Conexion(); 
		} catch (Throwable e2) {	e2.printStackTrace();}
		
		if(b == btnInsertar) 
		{
			if(radio_Adulto.isSelected())
			{
				try {
			        
					c = new Conexion(); 
					Calendar c1 = Calendar.getInstance();
					Calendar c2 = new GregorianCalendar();
					CallableStatement cal = c.conexion().prepareCall("{call alta_adulto(?,?,?,?,?,?,?,?,?)}");
					expiración_Adulto.setText(c2.get(Calendar.DATE)+"/"+(c2.get(Calendar.MONTH)+1)+"/"+(	c2.get(Calendar.YEAR)+1));
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
					java.util.Date date = null;
					try {
						date = sdf.parse(expiración_Adulto.getText());
						} catch (Exception e2) {}
					java.sql.Date d = new java.sql.Date(date.getTime());

					//					try {
//						date = sdf.parse(expiración_Adulto.getText());
//						} catch (Exception e2) {}
					cal.setInt   (1, Integer.parseInt(member_no.getText()));
//					java.sql.Date d = new java.sql.Date(date.getTime());
		            cal.setDate(9, d);
					cal.setString(2, Nombre.getText());
		            cal.setString(3, Apellido.getText());
		            cal.setString(4, calle_adulto.getText());
		            cal.setString(5, zipcode_adulto.getText());
		            cal.setString(6, ciudad_Adulto.getText());
		            cal.setString(7, estado_Adulto.getText());
		            cal.setString(8, adulto_telefono.getText());
//		            cal.setString(9, expiración_Adulto.getText());
					cal.execute();
				} catch (SQLServerException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					return;
				}  catch (Throwable e1) {
					System.out.println("hubo error "+e1.getMessage());
					return;
	//	            e1.printStackTrace();
		        }
			}else if(radio_Joven.isSelected()) {
				int dia = 0, mes = 0, año = 0;
				try {
					dia = dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH);
					mes = dateChooser.getCalendar().get(Calendar.MONTH);
					año = dateChooser.getCalendar().get(Calendar.YEAR);	
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, "llena los campos correctamente");
					return;
				}
				String fecha = dia+"/"+mes+"/"+año;
//				System.out.println("fecha = "+fecha);
				
				try {
					date = sdf.parse(fecha);
					} catch (Exception e2) {}
				java.sql.Date d = new java.sql.Date(date.getTime());
				
				try {
			        
					c = new Conexion(); 
					CallableStatement cal = c.conexion().prepareCall("{call ingresa_miembro(?,?,?,?,?)}");
					
					cal.setInt   (1, Integer.parseInt(member_no.getText()));
					cal.setString(2, Nombre.getText());
		            cal.setString(3, Apellido.getText());
		            cal.setInt   (4, Integer.parseInt(cve_Joven_Tutor.getText()));
		            cal.setDate (5, d);
		            
					cal.execute();
				  
				} catch (SQLServerException e1) 
				{
					JOptionPane.showMessageDialog(null, e1.getMessage());
//					System.out.println(e1.getMessage());
					return;
				}
			catch (Throwable e1) {
				JOptionPane.showMessageDialog(null, "Llena correctamente los campos");
				return;
		        }
			}
			int ID = Integer.parseInt(member_no.getText());
			String nombre =Nombre.getText();
			
			Object datos1[] = {ID,nombre};
//			        Object datos1[] = {nom, ape, ocupa, sueldo};
			((DefaultTableModel) table.getModel()).addRow(datos1);
			longitud = 0;
			member_no.setText(String.valueOf(Integer.parseInt(member_no.getText())+1));
			clave_adulto.setText(String.valueOf(Integer.parseInt(member_no.getText())));
			clave_Joven.setText(String.valueOf(Integer.parseInt(member_no.getText())));
			Nombre.setText("");
			Apellido.setText("");
			ciudad_Adulto.setText("");
			estado_Adulto.setText("");
			calle_adulto.setText("");
			zipcode_adulto.setText("");
			adulto_telefono.setText("");
			//			expiración_Adulto.setText("");
		//		clave_Joven.setText("");
				cve_Joven_Tutor.setText("");
				dateChooser.setDate(null);
				Statement instruccion;
				try {
					instruccion = c.conexion().createStatement();
					instruccion.execute("insert into claves values("+(Integer.parseInt(member_no.getText())-1)+")");
				} catch (Throwable e1) {e1.printStackTrace();	}
				JOptionPane.showMessageDialog(null, "Miembro registrado");

				
		}
		if(b == btnConsultaMember) {
			
			panelMiembrosCv.setVisible(true);

		
		}
		if(b == btnEliminar) {
			try {	
				
				c = new Conexion(); 
				CallableStatement cal = c.conexion().prepareCall("{call elimina_member_adulto(?)}");			
				cal.setInt   (1, Integer.parseInt(campo_elimina.getText()));
				
				cal.execute();
				
			}catch (SQLException sql) {
				
				JOptionPane.showMessageDialog(null, sql.getMessage());
				return;
			
			} catch (Throwable e2) {
				
				JOptionPane.showMessageDialog(null, "Llena campo correctamente");
				return;
			}
			JOptionPane.showMessageDialog(null, "Miembro eliminado correctamente");
		}
			
		
	}
	private int Id() throws Throwable {
		 c = new Conexion();
//		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		 String connectionURL =   "jdbc:sqlserver://GABRIEL-PC\\SQLEXPRESS:1433;databaseName=LIBRARYY;user=Libreria;password=123;";
//		 Connection con = DriverManager.getConnection(connectionURL);
		 Statement st = c.conexion().createStatement();
		 ResultSet rs = st.executeQuery("Select max(cves) from claves");
		        
		 while(rs.next()) 
			 ID = rs.getInt(1);
		        
		return ID;
	}
//	private void activado() {
//		clave_adulto.setEnabled(false);
//		ciudad_Adulto.setEnabled(false);
//		estado_Adulto.setEnabled(false);
//		expiración_Adulto.setEnabled(false);
//		clave_Joven.setEnabled(false);
//		cve_Joven_Tutor.setEnabled(false);
////		dateChooser.setEnabled(true);
//
////		clave_adulto.setText("");
//		ciudad_Adulto.setText("");
//		estado_Adulto.setText("");
////		calle_adulto.setText("");
////		zipcode_adulto.setText("");
////		adulto_telefono.setText("");
//		expiración_Adulto.setText("");
//	//	clave_Joven.setText("");
//		cve_Joven_Tutor.setText("");
//		
//	}
	private void activado_joven() {
		
		clave_adulto.setEnabled(false);
		ciudad_Adulto.setEnabled(false);
		estado_Adulto.setEnabled(false);
		calle_adulto.setEnabled(false);
		zipcode_adulto.setEnabled(false);
		adulto_telefono.setEnabled(false);
		expiración_Adulto.setEnabled(false);
		clave_adulto.setText("");
		ciudad_Adulto.setText("");
		estado_Adulto.setText("");
		expiración_Adulto.setText("");
		clave_Joven.setEnabled(false);
		cve_Joven_Tutor.setEnabled(true);
		dateChooser.setEnabled(true);
	}
	private void activado_adulto() {

		
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = new GregorianCalendar();
		
		clave_adulto.setEnabled(false);
		ciudad_Adulto.setEnabled(true);
		estado_Adulto.setEnabled(true);
		calle_adulto.setEnabled(true);
		adulto_telefono.setEnabled(true);
		zipcode_adulto.setEnabled(true);
//		expiración_Adulto.setEnabled(true);
		expiración_Adulto.setText(c2.get(Calendar.DATE)+"/"+(c2.get(Calendar.MONTH)+1)+"/"+(	c2.get(Calendar.YEAR)+1));
		clave_Joven.setEnabled(false);
		cve_Joven_Tutor.setEnabled(false);
		dateChooser.setEnabled(false);
		clave_Joven.setText("");
		cve_Joven_Tutor.setText("");
		dateChooser.setDate(null);
		
	}
}
