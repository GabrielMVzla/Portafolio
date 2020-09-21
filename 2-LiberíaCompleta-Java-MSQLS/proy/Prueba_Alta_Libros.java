package proy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Prueba_Alta_Libros extends JFrame implements ActionListener{
	
	
	int C = 0;
	Connect c ;
	Panel_alta_Libros_ventana palv;
	private JPanel contentPane;
	JPanel panel;
	private JTextField campo_titulo;
	private JLabel lblTtulo_1;
	private JLabel lblAutor;
	private JLabel lblSinopsis;
	private JLabel lblIdioma;
	private JLabel lblPasta;
	private JLabel lblCopias;
	private JTextField campo_isbn;
	private JTextField campo_titulotxt;
	private JTextField campo_autor;
	private JTextField campo_idioma;
	private JTextField campo_copiasNo;
	JTextArea txtrCamposinopsis;
	private JTable table;
	private JButton btnBuscar;
	private JTextField campo_buscar;
	private JLabel lblConsultaSiClave;
	private JButton btnRefresh;
	JButton btnConsultar;
	JButton btnRegistrar;
	JComboBox comboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prueba_Alta_Libros frame = new Prueba_Alta_Libros();
					frame.setVisible(true);
				} catch (Exception e) {} 
				catch (Throwable e) {e.printStackTrace();}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Throwable 
	 */
	public Prueba_Alta_Libros() throws Throwable {
		
		UIManager.LookAndFeelInfo[] apariencias =  UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel( apariencias[1].getClassName() );
			SwingUtilities.updateComponentTreeUI( this );

		} catch (ClassNotFoundException e) {e.printStackTrace();} 
		catch (InstantiationException e) {e.printStackTrace();} 
		catch (IllegalAccessException e) {e.printStackTrace();}
		catch (UnsupportedLookAndFeelException e) {e.printStackTrace();} 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550+106, 350);		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(110,10, 521, 290);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAltaDeLibros = new JLabel("Alta de libros");
		lblAltaDeLibros.setBounds(10, 8, 77, 14);
		panel.add(lblAltaDeLibros);
		
		JLabel lblTtulo = new JLabel("Clave T\u00EDtulo:");
		lblTtulo.setBounds(10, 33, 97, 14);
		panel.add(lblTtulo);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(10, 58, 97, 14);
		panel.add(lblIsbn);
		
		lblTtulo_1 = new JLabel("T\u00EDtulo:");
		lblTtulo_1.setBounds(10, 83, 97, 14);
		panel.add(lblTtulo_1);
		
		lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(10, 108, 97, 14);
		panel.add(lblAutor);
		
		lblSinopsis = new JLabel("Sinopsis:");
		lblSinopsis.setBounds(10, 144, 97, 14);
		panel.add(lblSinopsis);
		
		lblIdioma = new JLabel("Idioma:");
		lblIdioma.setBounds(189, 61, 46, 14);
		panel.add(lblIdioma);
		
		lblPasta = new JLabel("Pasta:");
		lblPasta.setBounds(189, 86, 46, 14);
		panel.add(lblPasta);
		
		lblCopias = new JLabel("Copias:");
		lblCopias.setBounds(189, 111, 46, 14);
		panel.add(lblCopias);
		
		lblConsultaSiClave = new JLabel("Consulta si clave de t\u00EDtulo existe:");
		lblConsultaSiClave.setBounds(120, 13, 180, 14);
		panel.add(lblConsultaSiClave);
		
		campo_titulo = new JTextField();
		campo_titulo.setBounds(82, 33, 56, 25);
		campo_titulo.setText(String.valueOf(Id()+1));
		campo_titulo.setEnabled(false);
		panel.add(campo_titulo);
		campo_titulo.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 169, 402, 110);
		panel.add(scrollPane);
		
		
		txtrCamposinopsis = new JTextArea();
		txtrCamposinopsis.setText("Consulta en el campo \"Consulta si clave de t\u00EDtulo existe\" para verificar si tu t\u00EDtulo ha sido registrado anteriormente y as\u00ED recuperar datos del mismo libro.");
		txtrCamposinopsis.setForeground(Color.GRAY); 
		txtrCamposinopsis.addMouseListener(new MouseAdapter()		//borra lo que hay en el campo sinopsis
		{
			public void mouseClicked(MouseEvent e) {
				if(C == 0)
					txtrCamposinopsis.setText("");
				C++;
			}
		});
		txtrCamposinopsis.setLineWrap( true );
		txtrCamposinopsis.setWrapStyleWord( true );
		scrollPane.setViewportView(txtrCamposinopsis);
		
		campo_isbn = new JTextField();
		campo_isbn.setBounds(82, 58, 86, 25);
		campo_isbn.addKeyListener(new KeyAdapter()
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
		panel.add(campo_isbn);
		campo_isbn.setColumns(10);
		
		campo_titulotxt = new JTextField();
		campo_titulotxt.setBounds(82, 83, 86, 25);
		panel.add(campo_titulotxt);
		campo_titulotxt.setColumns(10);
		
		campo_autor = new JTextField();
		campo_autor.setText("");
		campo_autor.setBounds(82, 108, 86, 25);
		panel.add(campo_autor);
		campo_autor.setColumns(10);
		
		campo_idioma = new JTextField();
		campo_idioma.setBounds(245, 58, 86, 25);
		panel.add(campo_idioma);
		campo_idioma.setColumns(10);
		
		campo_copiasNo = new JTextField();
		campo_copiasNo.setText("");
		campo_copiasNo.setBounds(245, 108, 86, 25);
		campo_copiasNo.addKeyListener(new KeyAdapter()
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
		panel.add(campo_copiasNo);
		campo_copiasNo.setColumns(10);
		
		campo_buscar = new JTextField();
		campo_buscar.setBounds(310, 8, 86, 25);
		campo_buscar.addKeyListener(new KeyAdapter()
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
		panel.add(campo_buscar);
		campo_buscar.setColumns(10);
		
		btnConsultar = new JButton("Consulta libros");
		btnConsultar.setBounds(422, 168, 89, 29);
		btnConsultar.addActionListener(this);
		panel.add(btnConsultar);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(422, 208, 89, 31);
		btnRegistrar.addActionListener(this);
		panel.add(btnRegistrar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(356, 44, 155, 89);
		scrollPane_1.setEnabled(false);
		panel.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No. copias", "ISBN"
			}
		));
		table.setEnabled(false);
		scrollPane_1.setViewportView(table);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(422, 250, 89, 29);
		panel.add(btnRegresar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(414, 7, 97, 25);
		btnBuscar.addActionListener(this);
		panel.add(btnBuscar);
		
		btnRefresh = new JButton("Refresh para nuevo titulo");
		btnRefresh.setBounds(140, 34, 191, 23);
		btnRefresh.setEnabled(false);
		btnRefresh.addActionListener(this);
		panel.add(btnRefresh);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Delgada", "Gruesa"}));
		comboBox.setBounds(245, 80, 86, 28);
		comboBox.setSelectedIndex(0);
		panel.add(comboBox);
		comboBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                //Execute when a selection has been made
            	System.out.println(comboBox.getSelectedItem());

            }
        }); 

		palv = new Panel_alta_Libros_ventana ();
		palv.setVisible(false);
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		ResultSet rs = null;
//		ResultSet rstit = null;

		if(b == btnBuscar) 
		{
			C = 1;
			campo_copiasNo.setText("");
			Statement st = null;
			int titulo = 0, IDt , tit_n = 0 ;
			try {
				c = new Connect();
				st = c.conexion().createStatement();
				titulo = Integer.parseInt(campo_buscar.getText());
			} catch (Throwable e1) {
				JOptionPane.showMessageDialog(null, "Llena el campo \"Consulta si ISBN del libro existe\" correctamente");
				return;
			}
			
			try {
				ResultSet rs1 = st.executeQuery("Select * from item"/* where title_no = "+titulo*/);
				
				while(rs1.next()) 
				{
					
					
					IDt = rs1.getInt(1);
					if(IDt == titulo) {
						campo_autor.setEnabled(false);
//						campo_buscar.setEnabled(false);
//						campo_copiasNo.setEnabled(false);

						comboBox.setEnabled(false);
						campo_idioma.setEnabled(false);
						campo_isbn.setEnabled(false);
//						campo_pasta.setEnabled(false);
						campo_titulo.setEnabled(false);
						campo_titulotxt.setEnabled(false);
						txtrCamposinopsis.setEnabled(false);

//						campo_titulo.setText(String.valueOf(titulo));
						campo_isbn.setText(String.valueOf(titulo));

						CallableStatement cal = c.conexion().prepareCall("{call rellena_campos(?)}");		
//						CallableStatement cal1 = c.conexion().prepareCall("{call rellena_campo_isbn(?)}");		//en realidad rellena title_no
						
//						cal1.setInt   (1, titulo);
						cal.setInt   (1, titulo);
						
						rs = cal.executeQuery();
					
						while(rs.next()){
					        
					      int i = 0;
					        String tit = rs.getString(1);
					        String aut = rs.getString(2);
					        String idio = rs.getString(3);
					        String cov = rs.getString(4);
					        campo_titulotxt.setText(tit);
					        campo_autor.setText(aut);
					        campo_idioma.setText(idio);
					        txtrCamposinopsis.setText(rs.getString(5));
//					        campo_pasta.setText(cov);
					        System.out.println("cov = "+cov);
					        if(cov.equalsIgnoreCase("Delgada"))
					        	i = 0;
					        else i = 1;
					     
					        
					        	comboBox.setSelectedIndex(i);
					        
							btnRefresh.setEnabled(true);
							ResultSet rstit = st.executeQuery("select title_no from item where isbn = "+titulo+"");
							while(rstit.next())
							{
								if(IDt == titulo) {
									tit_n	= rstit.getInt(1);
									campo_titulo.setText(String.valueOf(tit_n));
									return;
									
								}
								
							}
						}
					}					
					
						
				}
					
				JOptionPane.showMessageDialog(null, "NO se encontró el artículo con el correspondiente ISBN");
				
				
			} catch (SQLException e1) {e1.printStackTrace();} catch (Throwable e1) {
				JOptionPane.showMessageDialog(null, "Llena el campo \"Consulta si clave de título existe\" correctamente");
				return;
			}
		}if(b == btnConsultar) {
			
			palv.setVisible(true);
		}
		if(b == btnRefresh) 
		{
			try {
				campo_titulo.setText(String.valueOf(Id()+1));
				btnRefresh.setEnabled(false);
				campo_autor.setEnabled(true);
//				campo_buscar.setEnabled(false);
//				campo_copiasNo.setEnabled(false);
				campo_idioma.setEnabled(true);
				campo_isbn.setEnabled(true);
//				campo_pasta.setEnabled(true);
				comboBox.setEnabled(true);
				comboBox.getSelectedItem();
				campo_titulo.setEnabled(false);
				campo_titulotxt.setEnabled(true);
				campo_autor.setText("");
//				campo_buscar.setEnabled(false);
//				campo_copiasNo.setEnabled(false);
				campo_idioma.setText("");
				campo_isbn.setText("");
//				campo_pasta.setText("");
				campo_titulotxt.setText("");
				campo_copiasNo.setText("");
			} catch (Throwable e1) {e1.printStackTrace();}
		}if(b == btnRegistrar) 
		{
			if (C==0)
				txtrCamposinopsis.setText("");
				try {
		        C = 0;
				c = new Connect(); 
				CallableStatement cal = c.conexion().prepareCall("{call alta_libros(?,?,?,?,?,?,?,?)}");
//				try {
//					date = sdf.parse(expiración_Adulto.getText());
//					} catch (Exception e2) {}
				cal.setInt(1, Integer.parseInt(campo_titulo.getText()));
				cal.setInt(2, Integer.parseInt(campo_isbn.getText()));
	            cal.setString(3, campo_titulotxt.getText());
	            cal.setString(4, campo_autor.getText());
	            cal.setString(5, txtrCamposinopsis.getText());
	            cal.setString(6, campo_idioma.getText());
//	            cal.setString(7, campo_pasta.getText());
	            cal.setString(7, String.valueOf(comboBox.getSelectedItem()));
				cal.setInt(8, Integer.parseInt(campo_copiasNo.getText()));
//	            cal.setDate(9, d);
				cal.execute();
				int isbn = Integer.parseInt(campo_isbn.getText());
				int copis = Integer.parseInt(campo_copiasNo.getText());
				campo_autor.setText("");
//				campo_buscar.setEnabled(false);
//				campo_copiasNo.setEnabled(false);
				campo_idioma.setText("");
				campo_isbn.setText("");
//				campo_pasta.setText("");
				campo_titulotxt.setText("");
				campo_copiasNo.setText("");
				txtrCamposinopsis.setText("Consulta en el campo \"Consulta si clave de t\u00EDtulo existe\" para verificar si tu t\u00EDtulo ha sido registrado anteriormente y as\u00ED recuperar datos del mismo libro.");
				Object datos1[] = {copis,isbn};
//				        Object datos1[] = {nom, ape, ocupa, sueldo};
				((DefaultTableModel) table.getModel()).addRow(datos1);
			} catch (SQLServerException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				return;
			}  catch (Throwable e1) {
				JOptionPane.showMessageDialog(null, "Llena campos correctamente");
				return;
//	            e1.printStackTrace();
	        }

			campo_titulo.setText(String.valueOf(Integer.parseInt(campo_titulo.getText())+1));
			JOptionPane.showMessageDialog(null, "Registro(s) correctamente guardado(s)");

		}
	}
	
	private int Id() throws Throwable {
		
		c = new Connect(); 
		int ID = 0;
		Statement st = c.conexion().createStatement();
		ResultSet rs = st.executeQuery("Select * from title ");
		        
		 while(rs.next()) 
			 ID = rs.getInt(1);
		        
		return ID;
	}
}
