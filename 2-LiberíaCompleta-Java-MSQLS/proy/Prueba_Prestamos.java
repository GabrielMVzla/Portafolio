package proy;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Prueba_Prestamos extends JFrame implements ActionListener{
	
	Panel_Prestamos_Consultas_Ventana ppcv;
	int C = 0, multa = 0;
	Conexion c ;
	private JPanel contentPane;
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prueba_Prestamos frame = new Prueba_Prestamos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Prueba_Prestamos() {
		
		UIManager.LookAndFeelInfo[] apariencias =  UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel( apariencias[1].getClassName() );
			SwingUtilities.updateComponentTreeUI( this );

		} catch (ClassNotFoundException e) {e.printStackTrace();} 
		catch (InstantiationException e) {e.printStackTrace();} 
		catch (IllegalAccessException e) {e.printStackTrace();}
		catch (UnsupportedLookAndFeelException e) {e.printStackTrace();} 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550+106, 350);	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(110,10, 521, 290);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(10, 36, 46, 14);
		panel.add(lblIsbn);
		
		JLabel lblNoDeCopia = new JLabel("Cve copia:");
		lblNoDeCopia.setBounds(164, 33, 84, 14);
		panel.add(lblNoDeCopia);
		
		JLabel lblCveTtulo = new JLabel("Cve t\u00EDtulo:");
		lblCveTtulo.setBounds(10, 64, 84, 14);
		panel.add(lblCveTtulo);
		
		JLabel lblCveMiembro = new JLabel("Cve miembro:");
		lblCveMiembro.setBounds(10, 89, 84, 14);
		panel.add(lblCveMiembro);
		
		JLabel lblFechaDePlazo = new JLabel("fecha de plazo:");
		lblFechaDePlazo.setBounds(10, 114, 84, 14);
		panel.add(lblFechaDePlazo);
		
		JLabel lblFechaDeVencimiento = new JLabel("Fecha de vencimiento:");
		lblFechaDeVencimiento.setBounds(266, 63, 121, 14);
		panel.add(lblFechaDeVencimiento);
		
		JLabel lblMulta = new JLabel("Multa:");
		lblMulta.setBounds(266, 88, 68, 14);
		panel.add(lblMulta);
		
		JLabel lblMontoaPagar = new JLabel("Monto a pagar:");
		lblMontoaPagar.setBounds(266, 114, 84, 14);
		panel.add(lblMontoaPagar);
		
		JLabel lblComentarios = new JLabel("Comentarios: ");
		lblComentarios.setBounds(266, 140, 84, 14);
		panel.add(lblComentarios);
		
		campo_isbn = new JTextField();
		campo_isbn.setBounds(60, 29, 94, 23);
		panel.add(campo_isbn);
		campo_isbn.setColumns(10);
		
		campo_cvecopia = new JTextField();
		campo_cvecopia.setBounds(223, 27, 94, 23);
		panel.add(campo_cvecopia);
		campo_cvecopia.setColumns(10);
		
		campo_cvetitulo = new JTextField();
		campo_cvetitulo.setBounds(132, 58, 94, 27);
		panel.add(campo_cvetitulo);
		campo_cvetitulo.setColumns(10);
		
		campo_cvemiembro = new JTextField();
		campo_cvemiembro.setText("");
		campo_cvemiembro.setBounds(132, 84+8, 94, 27);
		panel.add(campo_cvemiembro);
		campo_cvemiembro.setColumns(10);
		
		campo_fechaplazo = new JTextField();
		campo_fechaplazo.setText("");
		campo_fechaplazo.setBounds(132, 110+16, 94, 27);
		panel.add(campo_fechaplazo);
		campo_fechaplazo.setColumns(10);
		
		rdbtnPrstamo = new JRadioButton("Pr\u00E9stamo");
		rdbtnPrstamo.setBounds(10, 3, 109, 23);
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
		panel.add(rdbtnPrstamo);
		
		rdbtnDevuelve = new JRadioButton("Devuelve");
		rdbtnDevuelve.setBounds(266, 3, 109, 23);
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
		panel.add(rdbtnDevuelve);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnDevuelve);
		bg.add(rdbtnPrstamo);
		
		campo_fechavencida = new JTextField();
		campo_fechavencida.setBounds(423, 58, 86, 25);
		panel.add(campo_fechavencida);
		campo_fechavencida.setColumns(10);
		
		campo_multa = new JTextField();
		campo_multa.setBounds(423, 83, 86, 25);
		panel.add(campo_multa);
		campo_multa.setColumns(10);
		
		campo_totalpago = new JTextField();
		campo_totalpago.setText("");
		campo_totalpago.setBounds(423, 108, 86, 25);
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
		panel.add(campo_totalpago);
		campo_totalpago.setColumns(10);
		
		txtrCampoareacomentarios = new JTextArea();
		txtrCampoareacomentarios.setBounds(360, 133, 149, 25);
		panel.add(txtrCampoareacomentarios);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 255, 127));
		panel_1.setBounds(10, 174, 324, 105);
		panel.add(panel_1);
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
		panel_1.add(lblComentarios_Reserva);
		
		campo_isbn_reserva = new JTextField();
		campo_isbn_reserva.setBounds(101, 25, 86, 25);
		panel_1.add(campo_isbn_reserva);
		campo_isbn_reserva.setColumns(10);
		
		campo_cve_miembro_reserva = new JTextField();
		campo_cve_miembro_reserva.setBounds(101, 50, 86, 25);
		panel_1.add(campo_cve_miembro_reserva);
		campo_cve_miembro_reserva.setColumns(10);
		
		campo_area_reserva = new JTextArea();
		campo_area_reserva.setText("crear realizar pago");
		campo_area_reserva.setBounds(101, 75, 213, 25);
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
		btnAceptar.setBounds(360, 252, 149, 27);
		panel.add(btnAceptar);
		
		btnConsultaPrstamos = new JButton("Consulta pr\u00E9stamos");
		btnConsultaPrstamos.setBounds(360, 174, 149, 27);
		btnConsultaPrstamos.addActionListener(this);
		panel.add(btnConsultaPrstamos);
		
		btnConsultaHistorial = new JButton("Consulta historial");
		btnConsultaHistorial.setBounds(360, 213, 149, 27);
		btnConsultaHistorial.addActionListener(this);
		panel.add(btnConsultaHistorial);
		btnAceptar.addActionListener(this);

		
		btnAceptarInfo = new JButton("Buscar");
		btnAceptarInfo.setBounds(381, 3, 131, 23);
		btnAceptarInfo.addActionListener(this);
		panel.add(btnAceptarInfo);

		//inicia con campos préstamo activos, boton buscar deshabilitado y campos de devolver deshabilitados
		rdbtnPrstamo.setSelected(true);
		btnAceptarInfo.setEnabled(false);
		prestamo();
		
		ppcv = new Panel_Prestamos_Consultas_Ventana();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
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
				
				try {
					c = new Conexion();
					CallableStatement cal = c.conexion().prepareCall("{call alta_prestamo(?,?,?,?,?)}");
					
					cal.setInt   (1, Integer.parseInt(campo_isbn.getText()));
					cal.setInt   (2, Integer.parseInt(campo_cvecopia.getText()));
					cal.setInt   (3, Integer.parseInt(campo_cvetitulo.getText()));
					cal.setInt   (4, Integer.parseInt(campo_cvemiembro.getText()));
					cal.setString(5, campo_fechaplazo.getText());		            
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
			PanelF_Reservaciones pfr = new PanelF_Reservaciones();
			pfr.setVisible(true);
			
		}
		if(b == btnRegistrar) {
			c = new Conexion();
		
			try {
				CallableStatement cal = c.conexion().prepareCall("{call alta_reservacion(?,?,?)}");
				cal.setInt   (1, Integer.parseInt(campo_isbn.getText()));
				cal.setString(2, txtrCampoareacomentarios.getText());		            
				cal.setString(3, txtrCampoareacomentarios.getText());		            
				cal.execute();
			}
			catch (NumberFormatException | SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());

			}
			catch (Throwable e1) {	
				JOptionPane.showMessageDialog(null, "Llena correctamente los campo");

			}
			
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
		campo_fechaplazo.setText(c2.get(Calendar.DATE)+"/"+(c2.get(Calendar.MONTH)+1)+"/"+(	c2.get(Calendar.YEAR)+1));
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

