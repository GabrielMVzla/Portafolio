package proy;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.microsoft.sqlserver.jdbc.SQLServerException;

public class Panel_Alta_libro_C1 extends JPanel implements ActionListener{

	
private JPanel contentPane;
	private JTextField campo_clave;
	JButton btnBusquedaCompleta;
	JButton btnBuscar;
	JLabel lblClave;
	JScrollPane scrollPane;
//	private JTextField campo_nombre;
	private JTable table;
//	private JTextField campo_apellido;
	DefaultTableModel modelo;
	private JScrollPane scrollPane_1;
	private JButton btnActualizar;
	private JLabel lblMiembrosAdultos;
	private JTextField campo_busqueda;
	Conexion c; 
	
	public Panel_Alta_libro_C1() throws Throwable {
	
		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);
		setBounds(10, 36, 696, 269);
		
		modelo = new DefaultTableModel();
		table = new JTable(modelo);
//		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {

//select isbn, i.title_no, title, idioma, cover, loanable, author from item i, title t where i.title_no = t.title_no
				"ISBN", "Cve título", "Título", "Idioma", "Pasta","Prestable", "Autor"
			}
		));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 75, 641, 166);
		scrollPane.setViewportView(table);
		add(scrollPane);
		
		btnBuscar = new JButton("buscar");
		btnBuscar.setBounds(124, 41, 134, 23);
		btnBuscar.addActionListener(this);
		add(btnBuscar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(580, 41, 89, 23);
		btnActualizar.addActionListener(this);
		add(btnActualizar);
		
		btnBusquedaCompleta = new JButton("busqueda completa");
		btnBusquedaCompleta.setBounds(28, 11, 230, 23);
		btnBusquedaCompleta.addActionListener(this);
		add(btnBusquedaCompleta);
		
		campo_busqueda = new JTextField();
		campo_busqueda.setBounds(28, 44, 86, 20);
		campo_busqueda.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
		add(campo_busqueda);
		campo_busqueda.setColumns(10);
		llamada_incial();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Statement st = null;
		ResultSet rs = null;
	
		c = new Conexion();
		
		JButton b = (JButton) e.getSource();
		if(btnActualizar == b) {
			try {
				eliminar();
				llamada_incial();
			} catch (Throwable e2) {	e2.printStackTrace();}}
//		if(btnBuscar == e.getSource()) {
//			try 
//			{
//				CallableStatement cal = conexion().prepareCall("{call consulta_miembros_adultos(?)}");
//				if(campo_clave.getText().isEmpty()) {
//					JOptionPane.showMessageDialog(null, "Inserta valor al campo");
//					return;
//				}
//				int cve = Integer.parseInt(campo_clave.getText());
//				cal.setInt   (1, cve);
//				rs = cal.executeQuery();
//				
//			} catch (SQLServerException sqle)
//			{
//				JOptionPane.showMessageDialog(null, sqle.getMessage());
//				return;
//			
//			} catch (Throwable e1) {e1.printStackTrace();	}
//			eliminar();
//			try {
//				while(rs.next()){
//				        
//				        int ID = rs.getInt(1);
//				        String nombre = rs.getString(2);
//				        String apellido = rs.getString(3);
//				        String calle = rs.getString(4);
//				        String zip = rs.getString(5);
//				        String city = rs.getString(6);
//				        String est = rs.getString(7);
//				        String phone = rs.getString(8);
//				        String date = rs.getString(9);
//
//				        Object datos1[] = {ID,nombre, apellido, calle, zip, city, est, phone, date};
////				        Object datos1[] = {nom, ape, ocupa, sueldo};
//				        ((DefaultTableModel) table.getModel()).addRow(datos1);
//				
//				}
//			}catch (SQLException e1) {e1.printStackTrace();}
//			
//		}
		if(btnActualizar == b) {
		try {
			eliminar();
			llamada_incial();
		} catch (Throwable e2) {	e2.printStackTrace();}}
		if(btnBusquedaCompleta == e.getSource()) {
			eliminar();
			try {
			CallableStatement cal = c.conexion().prepareCall("{call consulta_miembros_adultos_todos}"); 
			Statement st1 = c.conexion().createStatement();
			 rs = st1.executeQuery("select isbn, i.title_no, title, idioma, cover, loanable, author from item i, title t where i.title_no = t.title_no");		
//			 rs = cal.executeQuer8y();
			 
			 while(rs.next()){
				        
				        int isbn = rs.getInt(1);
				        int titn = rs.getInt(2);
				        String title = rs.getString(3);
				        String idioma = rs.getString(4);
				        String cover = rs.getString(5);
				        String loanable = rs.getString(6);
				        String author = rs.getString(7);

				        Object datos1[] = {isbn,titn, title, idioma, cover, loanable, author}  ;
				        ((DefaultTableModel) table.getModel()).addRow(datos1);
				}
			}
			catch (Throwable e1) {e1.printStackTrace();	}
		}
		if(b == btnBuscar) {
			c = new Conexion();
			eliminar();
			try {
			CallableStatement cal = c.conexion().prepareCall("{call consulta_miembros_adultos_todos}"); 
			Statement st1 = c.conexion().createStatement();
			 rs = st1.executeQuery("select isbn, i.title_no, title, idioma, cover, loanable, author from item i, title t"
			 		+ " where i.title_no = t.title_no and isbn = "+Integer.parseInt(campo_busqueda.getText()));		
//			 rs = cal.executeQuer8y();
			 
			 while(rs.next())
			 {
				        if(rs.getInt(1) == Integer.parseInt(campo_busqueda.getText())) 
				        {
				        int isbn = rs.getInt(1);
				        int titn = rs.getInt(2);
				        String title = rs.getString(3);
				        String idioma = rs.getString(4);
				        String cover = rs.getString(5);
				        String loanable = rs.getString(6);
				        String author = rs.getString(7);
				        
				        Object datos1[] = {isbn,titn, title, idioma, cover, loanable, author}  ;
				        ((DefaultTableModel) table.getModel()).addRow(datos1);
				        return;
				}
			 }
		} catch (Throwable e1) {
			JOptionPane.showMessageDialog(null, "Llena el campo correctamente");
			return;
		}
	try {
		
		JOptionPane.showMessageDialog(null, "Clave \""+(Integer.parseInt(campo_busqueda.getText()))+"\" no existe.");
	} catch (Exception e2) {
	}
		}
		
	}
	 public void eliminar(){
	        DefaultTableModel tb = (DefaultTableModel) table.getModel();
	        int a = table.getRowCount()-1;
	        for (int i = a; i >= 0; i--) {           
	        tb.removeRow(tb.getRowCount()-1);
	        } 
	        //cargaTicket();
	    }
//	 public Connection conexion() throws Throwable {
//		 
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			String connectionURL =   "jdbc:sqlserver://GABRIEL-PC\\SQLEXPRESS:1433;databaseName=LIBRARYY;user=Libreria;password=123;";
//		 	Connection con = DriverManager.getConnection(connectionURL);
//
//			
//		return con;
//		 
//	 }
	 public void llamada_incial() throws Throwable {
			ResultSet rs = null;
			c = new Conexion();
			try {
				CallableStatement cal = c.conexion().prepareCall("{call consulta_miembros_adultos_todos}"); 
				Statement st1 = c.conexion().createStatement();
				 rs = st1.executeQuery("select isbn, i.title_no, title, idioma, cover, loanable, author from item i, title t where i.title_no = t.title_no");		
//				 rs = cal.executeQuer8y();
				 
				 while(rs.next()){
					        
					        int isbn = rs.getInt(1);
					        int titn = rs.getInt(2);
					        String title = rs.getString(3);
					        String idioma = rs.getString(4);
					        String cover = rs.getString(5);
					        String loanable = rs.getString(6);
					        String author = rs.getString(7);

					        Object datos1[] = {isbn,titn, title, idioma, cover, loanable, author}  ;
					        ((DefaultTableModel) table.getModel()).addRow(datos1);
					}
				}
				catch (Throwable e1) {e1.printStackTrace();	
			}
	 }

}
