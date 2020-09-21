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

public class Panel_Alta_libro_C2 extends JPanel implements ActionListener{

	
private JPanel contentPane;
	private JTextField campo_clave;
	JLabel lblClave;
	JScrollPane scrollPane;
//	private JTextField campo_nombre;
	private JTable table;
//	private JTextField campo_apellido;
	DefaultTableModel modelo;
	private JScrollPane scrollPane_1;
	private JButton btnActualizar;
	private JLabel lblMiembrosAdultos;
	Conexion c; 
	
	public Panel_Alta_libro_C2() throws Throwable {
	
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
				"ISBN", "Copy No", "Título No", "En préstamo"
			}
		));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 75, 641, 166);
		scrollPane.setViewportView(table);
		add(scrollPane);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(28, 41, 89, 23);
		btnActualizar.addActionListener(this);
		add(btnActualizar);
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
		
				eliminar();
				try {
//				CallableStatement cal = c.conexion().prepareCall("{call consulta_miembros_adultos_todos}"); 
				Statement st1 = c.conexion().createStatement();
				 rs = st1.executeQuery("select isbn, copy_no, title_no, on_loan from copy");		
//				 rs = cal.executeQuer8y();
				 
				 while(rs.next()){
					        
					        int isbn = rs.getInt(1);
					        int titn = rs.getInt(2);
					        int title = rs.getInt(3);
					        String idioma = rs.getString(4);

					        Object datos1[] = {isbn,titn, title, idioma}  ;
					        ((DefaultTableModel) table.getModel()).addRow(datos1);
					}
				}
				catch (Throwable e1) {e1.printStackTrace();	}
			
	 }

}