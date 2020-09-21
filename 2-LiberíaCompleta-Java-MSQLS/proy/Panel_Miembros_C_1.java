package proy;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.awt.Color;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Panel_Miembros_C_1 extends JPanel implements ActionListener {
	
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
	private JLabel lblMiembros;
	Conexion c;
	
	public Panel_Miembros_C_1() throws Throwable {
	
		setBackground(SystemColor.inactiveCaption);
		setLayout(null);
		setBounds(10, 36, 696, 269);

		modelo = new DefaultTableModel();
		table = new JTable(modelo);
//		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"clave", "nombre", "apellido"
			}
		));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 75, 641, 166);
		scrollPane.setViewportView(table);
		add(scrollPane);
		
		lblClave = new JLabel("Clave");
		lblClave.setBounds(188, 15, 46, 14);
		add(lblClave);
		
		campo_clave = new JTextField();
		campo_clave.setColumns(10);
		campo_clave.setBounds(244, 12, 86, 20);
		campo_clave.addKeyListener(new KeyAdapter()
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
		add(campo_clave);
		
		btnBuscar = new JButton("buscar");
		btnBuscar.setBounds(360, 11, 134, 23);
		btnBuscar.addActionListener(this);
		add(btnBuscar);
		
		btnBusquedaCompleta = new JButton("busqueda completa");
		btnBusquedaCompleta.setBounds(266, 42, 160, 23);
		btnBusquedaCompleta.addActionListener(this);
		add(btnBusquedaCompleta);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(28, 41, 89, 23);
		btnActualizar.addActionListener(this);
		add(btnActualizar);
		
		lblMiembros = new JLabel("Miembros");
		lblMiembros.setBounds(28, 15, 72, 14);
		add(lblMiembros);
		
//--------------------INICIA CON ESTO AL ABRIR
		llamada_incial();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Statement st = null;
		ResultSet rs = null;
		JButton b = (JButton) e.getSource();
		if(btnActualizar == b)
			try {
				eliminar();
				llamada_incial();
			} catch (Throwable e2) {	e2.printStackTrace();}
		if(btnBuscar == e.getSource()) {
			c = new Conexion();
			try 
			{
				
				CallableStatement cal = c.conexion().prepareCall("{call consulta_miembros_cve(?)}");
				if(campo_clave.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Inserta valor al campo");
					return;
				}
				int cve = Integer.parseInt(campo_clave.getText());
				cal.setInt   (1, cve);
				rs = cal.executeQuery();
				
			} catch (SQLServerException sqle)
			{
				JOptionPane.showMessageDialog(null, sqle.getMessage());
				return;
			
			} catch (Throwable e1) {e1.printStackTrace();	}
			eliminar();
			try {
				while(rs.next()){
				        
				        int ID = rs.getInt(1);
				        String nombre = rs.getString(2);
				        String apellido = rs.getString(3);
				
				        Object datos1[] = {ID,nombre, apellido};
//				        Object datos1[] = {nom, ape, ocupa, sueldo};
				        ((DefaultTableModel) table.getModel()).addRow(datos1);
				
				}
			}catch (SQLException e1) { e1.printStackTrace();
				
			}
			
		}
		if(btnBusquedaCompleta == e.getSource()) {
			eliminar();
			c = new Conexion();
			try {
			CallableStatement cal = c.conexion().prepareCall("{call consulta_miembros}"); 	
			rs = cal.executeQuery();
				while(rs.next()){
				        
				        int ID = rs.getInt(1);
				        String nombre = rs.getString(2);
				        String apellido = rs.getString(3);
				
				        Object datos1[] = {ID,nombre, apellido};
				        ((DefaultTableModel) table.getModel()).addRow(datos1);
				}
			} 
			
			catch (Throwable e1) {e1.printStackTrace();	}
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
		 c = new Conexion();
			ResultSet rs = null;
			try 
			{
				CallableStatement cal = c.conexion().prepareCall("{call consulta_miembros}"); 	
				rs = cal.executeQuery();
					while(rs.next()){
					        
					        int ID = rs.getInt(1);
					        String nombre = rs.getString(2);
					        String apellido = rs.getString(3);
					
					        Object datos1[] = {ID,nombre, apellido};
					        ((DefaultTableModel) table.getModel()).addRow(datos1);
					}
			
				}
			catch (SQLException e1) {e1.printStackTrace();
	 }
	 }
}
