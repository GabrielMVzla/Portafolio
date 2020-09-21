package proy;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import javax.swing.JTextField;
import javax.swing.JLabel;

public class Panel_Prestamos_C_1 extends JPanel implements ActionListener {

	/**
	 * Create the panel.
	 */
	Conexion c;
	private JTable table;
	DefaultTableModel modelo;
	JScrollPane scrollPane;
	private JTextField campo_busqueda;
	JButton btnBusquedaCompleta;
	JButton btnBuscar;
	private JButton btnActualizar;
	private JLabel lblHistorial;

	public Panel_Prestamos_C_1() {

		setBackground(SystemColor.inactiveCaption);
		setLayout(null);
		setBounds(10, 36, 766, 318);
		
		modelo = new DefaultTableModel();
		table = new JTable(modelo);
//		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"isbn", "copy no", "title no", "member no", "out date", "due date", "in date", "Deuda", "Pagado", "comentarios"
			}
		));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 72, 728, 200);
		scrollPane.setViewportView(table);
		add(scrollPane);
		
		btnBusquedaCompleta = new JButton("Busqueda completa");
		btnBusquedaCompleta.setBounds(28, 11, 143, 28);
		btnBusquedaCompleta.addActionListener(this);
		add(btnBusquedaCompleta);
		
		btnBuscar = new JButton("Buscar por clave de miembro");
		btnBuscar.setBounds(331, 11, 192, 28);
		btnBuscar.addActionListener(this);
		add(btnBuscar);
		
		campo_busqueda = new JTextField();
		campo_busqueda.setBounds(223, 12, 86, 28);
		campo_busqueda.addKeyListener(new KeyAdapter()
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
		add(campo_busqueda);
		campo_busqueda.setColumns(10);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(667, 14, 89, 23);
		btnActualizar.addActionListener(this);
		add(btnActualizar);
		
		lblHistorial = new JLabel("Historial");
		lblHistorial.setBounds(544, 11, 46, 14);
		add(lblHistorial);
		
		
		
		
		c = new Conexion();
		try {
		Statement st1 = c.conexion().createStatement();	
		ResultSet rs1 = st1.executeQuery("Select * from loanhist");
		
		while(rs1.next()) {
			int isbn = rs1.getInt(1);
			int copy = rs1.getInt(2);
			int title = rs1.getInt(3);
			int member= rs1.getInt(4);
			String odate = rs1.getString(5);
			String ddate= rs1.getString(6);
			String idate= rs1.getString(7);
			int deuda= rs1.getInt(8);
			int pagado= rs1.getInt(9);
			String comm= rs1.getString(10);
			
	        Object datos1[] = {isbn,copy, title,member , odate,ddate, idate,deuda,pagado,comm};

//	        Object datos1[] = {nom, ape, ocupa, sueldo};
	        ((DefaultTableModel) table.getModel()).addRow(datos1); 
			
			
		}
		} catch (Throwable e1) {e1.printStackTrace();}
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		c = new Conexion();
		Statement st;
		if(b == btnActualizar) 
		{	eliminar();
			llamada_inicial();
		}
		if(b == btnBusquedaCompleta) 
		{	eliminar();
			try {
				st = c.conexion().createStatement();	
				ResultSet rs = st.executeQuery("Select * from loanhist");
				
				while(rs.next()) {
					int isbn = rs.getInt(1);
					int copy = rs.getInt(2);
					int title = rs.getInt(3);
					int member= rs.getInt(4);
					String odate = rs.getString(5);
					String ddate= rs.getString(6);
					String idate= rs.getString(7);
					int deuda= rs.getInt(8);
					int pagado= rs.getInt(9);
					String comm= rs.getString(10);
					
			        Object datos1[] = {isbn,copy, title,member , odate,ddate, idate,deuda,pagado,comm};
//			        Object datos1[] = {nom, ape, ocupa, sueldo};
			        ((DefaultTableModel) table.getModel()).addRow(datos1); 
					
					
				}
				} catch (Throwable e1) {e1.printStackTrace();}
		}
		if( b == btnBuscar) {
			eliminar();
			try {
				st = c.conexion().createStatement();	
				ResultSet rs = st.executeQuery("Select * from loanhist where member_no = "+Integer.parseInt(campo_busqueda.getText()));
				
				while(rs.next()) {
					
					if(rs.getInt(4) == Integer.parseInt(campo_busqueda.getText())) {
						
						int isbn = rs.getInt(1);
						int copy = rs.getInt(2);
						int title = rs.getInt(3);
						int member= rs.getInt(4);
						String odate = rs.getString(5);
						String ddate= rs.getString(6);
						String idate= rs.getString(7);
						int deuda= rs.getInt(8);
						int pagado= rs.getInt(9);
						String comm= rs.getString(10);
						
				        Object datos1[] = {isbn,copy, title,member , odate,ddate, idate,deuda,pagado,comm};
	//			        Object datos1[] = {nom, ape, ocupa, sueldo};
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
	public void llamada_inicial() {
		c = new Conexion();
		Statement st;
			eliminar();
			try {
				st = c.conexion().createStatement();	
				ResultSet rs = st.executeQuery("Select * from loanhist");
				
				while(rs.next()) {
					int isbn = rs.getInt(1);
					int copy = rs.getInt(2);
					int title = rs.getInt(3);
					int member= rs.getInt(4);
					String odate = rs.getString(5);
					String ddate= rs.getString(6);
					String idate= rs.getString(7);
					int deuda= rs.getInt(8);
					int pagado= rs.getInt(9);
					String comm= rs.getString(10);
					
			        Object datos1[] = {isbn,copy, title,member , odate,ddate, idate,deuda,pagado,comm};
//			        Object datos1[] = {nom, ape, ocupa, sueldo};
			        ((DefaultTableModel) table.getModel()).addRow(datos1); 
					
					
				}
				} catch (Throwable e1) {e1.printStackTrace();}
		
	}
	 public void eliminar(){
	        DefaultTableModel tb = (DefaultTableModel) table.getModel();
	        int a = table.getRowCount()-1;
	        for (int i = a; i >= 0; i--) {           
	        tb.removeRow(tb.getRowCount()-1);
	        } 
	        //cargaTicket();
	    }
}
