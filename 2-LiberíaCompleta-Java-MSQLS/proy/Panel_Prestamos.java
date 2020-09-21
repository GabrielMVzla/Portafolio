package proy;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Panel_Prestamos extends JPanel implements ActionListener{
	
	Panel_Prestamos_Consultas_Ventana ppcv;
	int C = 0, multa = 0;
	Conexion c ;
	JPanel panel;
	private JTextField campo_isbn;
	private JTextField campo_cvecopia;
	private JTextField campo_cvetitulo;
	private JTextField campo_cvemiembro;
	private JTextField campo_fechaplazo;
	JButton btnAceptar;
	JButton btnRegistrar;
	JButton btnConsultaReservaciones;
	JButton btnConsultaPrstamos;
	JButton btnConsultaHistorial;
	private JRadioButton rdbtnDevuelve;
	JRadioButton rdbtnPrstamo;
	JTextArea txtrCampoareacomentarios;
	JTextArea campo_area_reserva;
	private JTextField campo_fechavencida;
	private JTextField campo_multa;
	private JTextField campo_totalpago;
	private JTextField campo_isbn_reserva;
	private JTextField campo_cve_miembro_reserva;
	private JButton btnAceptarInfo;
	PanelF_Reservaciones pfr;
	private JLabel lblHistorialPrstamo;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public Panel_Prestamos() {
		
		UIManager.LookAndFeelInfo[] apariencias =  UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel( apariencias[1].getClassName() );
			SwingUtilities.updateComponentTreeUI( this );

		} catch (ClassNotFoundException e) {e.printStackTrace();} 
		catch (InstantiationException e) {e.printStackTrace();} 
		catch (IllegalAccessException e) {e.printStackTrace();}
		catch (UnsupportedLookAndFeelException e) {e.printStackTrace();} 

		//		setBounds(100, 100, 550+106, 350);	
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
		
//		panel = new JPanel();
		setBackground(new Color(240, 255, 255));
		setBounds(110,10, 521, 290);
//		contentPane.add(panel);
		setLayout(null);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(10, 12, 46, 14);
		add(lblIsbn);
		
		JLabel lblNoDeCopia = new JLabel("Cve copia:");
		lblNoDeCopia.setBounds(164, 9, 84, 14);
		add(lblNoDeCopia);
		
		JLabel lblCveTtulo = new JLabel("Cve t\u00EDtulo:");
		lblCveTtulo.setBounds(10, 64, 84, 14);
		add(lblCveTtulo);
		
		JLabel lblCveMiembro = new JLabel("Cve miembro:");
		lblCveMiembro.setBounds(10, 89, 84, 14);
		add(lblCveMiembro);
		
		JLabel lblFechaDePlazo = new JLabel("fecha de plazo:");
		lblFechaDePlazo.setBounds(10, 114, 84, 14);
		add(lblFechaDePlazo);
		
		JLabel lblFechaDeVencimiento = new JLabel("Fecha de vencimiento:");
		lblFechaDeVencimiento.setBounds(266, 63, 121, 14);
		add(lblFechaDeVencimiento);
		
		JLabel lblMulta = new JLabel("Multa:");
		lblMulta.setBounds(266, 88, 68, 14);
		add(lblMulta);
		
		JLabel lblMontoaPagar = new JLabel("Monto a pagar:");
		lblMontoaPagar.setBounds(266, 114, 84, 14);
		add(lblMontoaPagar);
		
		JLabel lblComentarios = new JLabel("Comentarios: ");
		lblComentarios.setBounds(266, 140, 84, 14);
		add(lblComentarios);
		
		campo_isbn = new JTextField();
		campo_isbn.setBounds(60, 5, 94, 23);
		campo_isbn.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
		add(campo_isbn);
		campo_isbn.setColumns(10);
		
		campo_cvecopia = new JTextField();
		campo_cvecopia.setBounds(223, 3, 94, 23);
		campo_cvecopia.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
		add(campo_cvecopia);
		campo_cvecopia.setColumns(10);
		
		campo_cvetitulo = new JTextField();
		campo_cvetitulo.setBounds(132, 58, 94, 27);
		campo_cvetitulo.setBackground(new Color(210,238,255));
		campo_cvetitulo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
		add(campo_cvetitulo);
		campo_cvetitulo.setColumns(10);
		
		campo_cvemiembro = new JTextField();
		campo_cvemiembro.setText("");
		campo_cvemiembro.setBounds(132, 84+8, 94, 27);
		campo_cvemiembro.setBackground(new Color(210,238,255));
		campo_cvemiembro.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
		add(campo_cvemiembro);
		campo_cvemiembro.setColumns(10);
		
		campo_fechaplazo = new JTextField();
		campo_fechaplazo.setText("");
		campo_fechaplazo.setBounds(132, 110+16, 94, 27);
		add(campo_fechaplazo);
		campo_fechaplazo.setColumns(10);
		
		rdbtnPrstamo = new JRadioButton("Pr\u00E9stamo");
		rdbtnPrstamo.setFont(new Font("Source Sans Pro Black", Font.BOLD, 12));
		rdbtnPrstamo.setBounds(41, 34, 84, 23);
		rdbtnPrstamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnPrstamo.isSelected()) 
				{
					rdbtnPrstamo.setEnabled(false);
					rdbtnDevuelve.setEnabled(true);
				}

				campo_isbn.setText("");
				campo_cvecopia.setText("");
				btnAceptarInfo.setEnabled(false);
				btnAceptar.setEnabled(true);
				prestamo();
				
			
		}
			
	});
		add(rdbtnPrstamo);
		
		rdbtnDevuelve = new JRadioButton("Devuelve");
		rdbtnDevuelve.setBounds(297, 33, 84, 23);
		rdbtnDevuelve.setFont(new Font("Source Sans Pro Black", Font.BOLD, 12));
		rdbtnDevuelve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnDevuelve.isSelected()) 
				{
					rdbtnDevuelve.setEnabled(false);
					rdbtnPrstamo.setEnabled(true);
				}
				

				btnAceptarInfo.setEnabled(true);
				btnAceptar.setEnabled(false);

				devuelve();

			
		}
	});
		add(rdbtnDevuelve);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnDevuelve);
		bg.add(rdbtnPrstamo);
		
		campo_fechavencida = new JTextField();
		campo_fechavencida.setBackground(new Color(245, 245, 220));
		campo_fechavencida.setBounds(423, 58, 86, 25);
		add(campo_fechavencida);
		campo_fechavencida.setColumns(10);
		
		campo_multa = new JTextField();
		campo_multa.setBackground(new Color(245, 245, 220));
		campo_multa.setBounds(423, 83, 86, 25);
		add(campo_multa);
		campo_multa.setColumns(10);
		
		campo_totalpago = new JTextField();
		campo_totalpago.setBackground(new Color(245, 245, 220));
		campo_totalpago.setText("");
		campo_totalpago.setBounds(423, 108, 86, 25);
		campo_totalpago.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
		campo_totalpago.setText("");
		campo_totalpago.setText("0");
		
		campo_totalpago.addMouseListener(new MouseAdapter()		//borra lo que hay en el campo sinopsis
		{
			public void mouseClicked(MouseEvent e) {
				if(C == 0)
					campo_totalpago.setText("");
				C++;
			}
		});
		add(campo_totalpago);
		campo_totalpago.setColumns(10);
		
		txtrCampoareacomentarios = new JTextArea();
		txtrCampoareacomentarios.setBackground(new Color(245, 245, 220));
		txtrCampoareacomentarios.setBounds(360, 133, 149, 25);
		add(txtrCampoareacomentarios);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(10, 174, 324, 105);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblReservacin = new JLabel("Reservaci\u00F3n");
		lblReservacin.setBounds(10, 5, 90, 14);
		panel_1.add(lblReservacin);
		
		JLabel lblIsbn_1 = new JLabel("ISBN:");
		lblIsbn_1.setBounds(10, 30, 46, 14);
		panel_1.add(lblIsbn_1);
		
		JLabel lblCveMiembro_1 = new JLabel("Cve miembro:");
		lblCveMiembro_1.setBounds(10, 55, 77, 14);
		panel_1.add(lblCveMiembro_1);
		
		JLabel lblComentarios_Reserva = new JLabel("Comentarios:");
		lblComentarios_Reserva.setBounds(10, 80, 64, 14);
		lblComentarios_Reserva.setVisible(false);
		panel_1.add(lblComentarios_Reserva);
		
		campo_isbn_reserva = new JTextField();
		campo_isbn_reserva.setBounds(101, 25, 86, 25);
		campo_isbn_reserva.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
		panel_1.add(campo_isbn_reserva);
		campo_isbn_reserva.setColumns(10);
		
		campo_cve_miembro_reserva = new JTextField();
		campo_cve_miembro_reserva.setBounds(101, 50, 86, 25);
		campo_cve_miembro_reserva.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
		panel_1.add(campo_cve_miembro_reserva);
		campo_cve_miembro_reserva.setColumns(10);
		
		campo_area_reserva = new JTextArea();
		campo_area_reserva.setText("crear realizar pago");
		campo_area_reserva.setBounds(101, 75, 213, 25);
		campo_area_reserva.setVisible(false);
		panel_1.add(campo_area_reserva);
		
		btnRegistrar = new JButton("Registrar");                     //---------------------------------------------------
		btnRegistrar.setBounds(184, 50, 130, 25);
		btnRegistrar.addActionListener(this);
		panel_1.add(btnRegistrar);
		
		btnConsultaReservaciones = new JButton("Consulta reservaciones");
		btnConsultaReservaciones.setBounds(184, 25, 130, 25);
		btnConsultaReservaciones.addActionListener(this);

		panel_1.add(btnConsultaReservaciones);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setBackground(Color.DARK_GRAY);
		btnAceptar.setBounds(360, 252, 149, 27);
		add(btnAceptar);
		
		btnConsultaPrstamos = new JButton("Consulta pr\u00E9stamos");
		btnConsultaPrstamos.setForeground(Color.WHITE);
		btnConsultaPrstamos.setBackground(Color.DARK_GRAY);
		btnConsultaPrstamos.setBounds(344, 165, 14, 14);
		btnConsultaPrstamos.addActionListener(this);
		btnConsultaPrstamos.setVisible(false);
		btnConsultaPrstamos.setEnabled(false);
		add(btnConsultaPrstamos);
		
		btnConsultaHistorial = new JButton("Consulta");
		btnConsultaHistorial.setForeground(Color.WHITE);
		btnConsultaHistorial.setBackground(Color.DARK_GRAY);
		btnConsultaHistorial.setBounds(360, 213, 149, 27);
		btnConsultaHistorial.addActionListener(this);
		add(btnConsultaHistorial);
		btnAceptar.addActionListener(this);

		
		btnAceptarInfo = new JButton("Buscar");
		btnAceptarInfo.setForeground(Color.WHITE);
		btnAceptarInfo.setBackground(Color.DARK_GRAY);
		btnAceptarInfo.setBounds(381, 3, 131, 23);
		btnAceptarInfo.addActionListener(this);
		add(btnAceptarInfo);

		//inicia con campos préstamo activos, boton buscar deshabilitado y campos de devolver deshabilitados
		rdbtnPrstamo.setSelected(true);
		btnAceptarInfo.setEnabled(false);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(245, 245, 220));
		panel_2.setBounds(266, 34, 25, 23);
		add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 34, 25, 23);
		panel_3.setBackground(new Color(210,238,255));
		add(panel_3);
		
		lblHistorialPrstamo = new JLabel("Historial / Pr\u00E9stamo");
		lblHistorialPrstamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialPrstamo.setBounds(361, 188, 148, 14);
		add(lblHistorialPrstamo);
		prestamo();
		
		pfr = new PanelF_Reservaciones();
		pfr.setVisible(false);
		ppcv = new Panel_Prestamos_Consultas_Ventana();
		ppcv.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) throws NumberFormatException {
		
		c = new Conexion();
		JButton b = (JButton) e.getSource();
		ResultSet rs = null;
		Statement st; 
//		ResultSet rstit = null;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = new GregorianCalendar();
		String fechav = "";
		if(b == btnAceptarInfo) 
		{
			btnAceptar.setEnabled(true);

			try {
				
				CallableStatement cal = c.conexion().prepareCall("{call existencia_isbn_copy(?,?)}");
				cal.setInt   (1, Integer.parseInt(campo_isbn.getText()));
				cal.setInt   (2, Integer.parseInt(campo_cvecopia.getText()));
				cal.execute();

				
				st = c.conexion().createStatement();
				
				if(rdbtnDevuelve.isSelected()) 
				{
					
					rs = st.executeQuery("Select FORMAT(due_date,'dd/MM/yyyy') from loan where " + Integer.parseInt(campo_isbn.getText())+ " = isbn "
									  +  "and copy_no = " + Integer.parseInt(campo_cvecopia.getText()));
					 while(rs.next()) 
					 {

						 fechav = rs.getString(1);
						 campo_fechavencida.setText(fechav);
//						 System.out.println("fecha vencimiento = "+ fechav );
						 
					 }
						CallableStatement cal1 = c.conexion().prepareCall("{call regresa_multa(?)}");
						cal1.setString(1, fechav);
						cal1.execute();

						
						rs = cal1.executeQuery();
						while(rs.next()) 
					 {
						 multa = rs.getInt(1);
						 campo_multa.setText(String.valueOf(multa));
//			
//						 System.out.println("multa = "+ multa );
					 }  
				}
//				else if(rdbtnPrstamo.isSelected()) 
//				{
//				
//				}
			}
			catch(SQLException sql){
				JOptionPane.showMessageDialog(null, sql.getMessage());

			}
			catch(Throwable t){
				JOptionPane.showMessageDialog(null, "Llena correctamente los campos");
			}

		}
		if(b == btnAceptar) 
		{
		 
			if(rdbtnPrstamo.isSelected()) {
//				CallableStatement cal = c.conexion().prepareCall("{call alta_adulto(?,?,?,?,?,?,?,?,?)}");
				campo_fechaplazo.setText((c2.get(Calendar.DATE)+15)+"/"+(c2.get(Calendar.MONTH)+1)+"/"+(	c2.get(Calendar.YEAR)));
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
				java.util.Date date = null;
				try {
					date = sdf.parse(campo_fechaplazo.getText());
					} catch (Exception e2) {}
				java.sql.Date d = new java.sql.Date(date.getTime());

				//					try {
//					date = sdf.parse(expiración_Adulto.getText());
//					} catch (Exception e2) {}
//				java.sql.Date d = new java.sql.Date(date.getTime());
				try {
					c = new Conexion();
					CallableStatement cal = c.conexion().prepareCall("{call alta_prestamo(?,?,?,?,?)}");
					
					cal.setInt   (1, Integer.parseInt(campo_isbn.getText()));
					cal.setInt   (2, Integer.parseInt(campo_cvecopia.getText()));
					cal.setInt   (3, Integer.parseInt(campo_cvetitulo.getText()));
					cal.setInt   (4, Integer.parseInt(campo_cvemiembro.getText()));
					cal.setDate(5, d);
//					cal.setString(5, campo_fechaplazo.getText());		            
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
				JOptionPane.showMessageDialog(null, "Préstamo realizado.");

			}
			else if(rdbtnDevuelve.isSelected()) {
				
				try {
					if(campo_totalpago.getText().equals("")) {
						campo_totalpago.setText(String.valueOf(0));

					}
					if(Integer.parseInt(campo_totalpago.getText()) > multa) {
						JOptionPane.showMessageDialog(null, "Cambio = "+
								(Integer.parseInt(campo_totalpago.getText()) - multa)
								);
						campo_totalpago.setText(campo_multa.getText());
					}
						
					c = new Conexion();
					CallableStatement cal = c.conexion().prepareCall("{call regresar_libro(?,?,?,?)}");
					
					cal.setInt   (1, Integer.parseInt(campo_isbn.getText()));
					cal.setInt   (2, Integer.parseInt(campo_cvecopia.getText()));
					cal.setInt   (3, Integer.parseInt(campo_totalpago.getText()));
					cal.setString(4, txtrCampoareacomentarios.getText());		            
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
			
				   JOptionPane.showMessageDialog(null, "Libro recibido.");
			}
			campo_isbn.setText("");
			campo_cvecopia.setText("");
			if (C==0)
				campo_totalpago.setText("");
			C = 0;
			campo_totalpago.setText("0");

		}
		if(b == btnConsultaReservaciones) {
			
			pfr.setVisible(true);
			
		}
		if(b == btnRegistrar) {
			c = new Conexion();
			CallableStatement cal1;
			try {
				 cal1 = c.conexion().prepareCall("{call alta_reservacion(?,?,?)}");
				cal1.setInt (1, Integer.parseInt(campo_isbn_reserva.getText()));
				cal1.setInt   (2, Integer.parseInt(campo_cve_miembro_reserva.getText()));
				cal1.setString(3, txtrCampoareacomentarios.getText());		            
				cal1.execute();
			}
			catch ( SQLException e1) {
//				System.out.println("sqllll");
				JOptionPane.showMessageDialog(null, e1.getMessage());
				return;

			}
			catch (Throwable e1) {	
//				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Llena correctamente los campo");
				return;

			}
			JOptionPane.showMessageDialog(null, "Reservación creada");
			
			
		}
		if(b == btnConsultaHistorial) {
			ppcv.setVisible(true);
			
		}
		if(b == btnConsultaPrstamos) {
			ppcv.setVisible(true);
			
			
		}
		
	}
	private void prestamo() {
		

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = new GregorianCalendar();
		campo_fechavencida.setText("");
		campo_multa.setText("");
		campo_totalpago.setText("");
		txtrCampoareacomentarios.setText("");
		campo_fechavencida.setEnabled(false);
		campo_multa.setEnabled(false);
		campo_totalpago.setEnabled(false);
		txtrCampoareacomentarios.setEnabled(false);
		campo_fechaplazo.setText((c2.get(Calendar.DATE)+15)+"/"+(c2.get(Calendar.MONTH)+1)+"/"+(	c2.get(Calendar.YEAR)));
		//de prestamo
//		campo_isbn.setEnabled(true);
//		campo_cvecopia.setEnabled(true);
		campo_cvetitulo.setEnabled(true);
		campo_cvemiembro.setEnabled(true);
		campo_fechaplazo.setEnabled(false);
		
	}
	private void devuelve() {

		
		//de prestamo
//		campo_isbn.setEnabled(false);
//		campo_cvecopia.setEnabled(false);
		campo_cvetitulo.setEnabled(false);
		campo_cvemiembro.setEnabled(false);
		campo_fechaplazo.setEnabled(false);
	
		campo_isbn.setText("");
		campo_cvecopia.setText("");
		campo_cvetitulo.setText("");
		campo_cvemiembro.setText("");
		campo_totalpago.setText("0");
		campo_fechaplazo.setText("");
		
		//de devolucion
//		campo_isbn_dev.setEnabled(true);
//		campo_cve_copia_dev.setEnabled(true);
		campo_fechavencida.setEnabled(false);
		campo_multa.setEnabled(false);
		campo_totalpago.setEnabled(true);
		txtrCampoareacomentarios.setEnabled(true);
		
		
	}
}

