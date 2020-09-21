package papeleriaa;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Articulo_Historial_C1 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Articulo_Historial_C1 frame = new Articulo_Historial_C1();
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
	JButton btnClave;
	JButton btnTipo;
	JButton btnDescipcin;
	private JTextField campo_clave;
	private JTextField campo_tipo;
	private JTextArea campo_descripcion;
	private JButton btnBuscar;
	private JScrollPane scrollPane;
	private JTable table;
	DefaultTableModel modelo;
	Conexion c;
	JButton btnBusquedaCompleta;
	JButton btnActualiza;
	
	public Articulo_Historial_C1() {
		setResizable(false);

		setTitle("Historial de movimientos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 100, 684, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 658, 311);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		lblBuscarPor.setBounds(50, 11, 68, 14);
		panel.add(lblBuscarPor);
		
		btnClave = new JButton("Clave");
		btnClave.setBounds(40, 36, 89, 23);
		btnClave.addActionListener(this);
		panel.add(btnClave);
		
		btnTipo = new JButton("Tipo");
		btnTipo.setBounds(167, 36, 89, 23);
		btnTipo.addActionListener(this);
		panel.add(btnTipo);
		
		btnDescipcin = new JButton("Descripci\u00F3n");
		btnDescipcin.setBounds(295, 36, 109, 23);
		btnDescipcin.addActionListener(this);
		panel.add(btnDescipcin);
		
		campo_clave = new JTextField();
		campo_clave.setBounds(40, 70, 86, 23);
		panel.add(campo_clave);
		campo_clave.setColumns(10);
		
		campo_tipo = new JTextField();
		campo_tipo.setBounds(167, 70, 86, 23);
		panel.add(campo_tipo);
		campo_tipo.setColumns(10);
		
		campo_descripcion = new JTextArea();
		campo_descripcion.setBounds(295, 70, 109, 23);
		panel.add(campo_descripcion);
		
		btnBuscar = new JButton("Busqueda por campos");
		btnBuscar.setBounds(430, 69, 188, 23);
		btnBuscar.addActionListener(this);
		panel.add(btnBuscar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 118, 658, 193);
		panel.add(scrollPane);
		
		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Clave", "accion", "Tipo", "Descripción", "Cantidad", "$Compra", "$Venta", "fecha", "proveedor"
			}
		));
		scrollPane.setViewportView(table);
		
		
		c = new Conexion();
		try {
		Statement st1 = c.conexion().createStatement();	
		ResultSet rs1 = st1.executeQuery("Select * from historial");
		
		while(rs1.next()) {
			int cve = rs1.getInt(1);
			String ac = rs1.getString(2);
			String tip = rs1.getString(3);
			String des = rs1.getString(4);
			int can= rs1.getInt(5);
			int pc= rs1.getInt(6);
			int pv= rs1.getInt(7);
			String fecha = rs1.getString(8);
			String prov= rs1.getString(9);
	
			
	        Object datos1[] = {cve, ac, tip, des, can, pc, pv, fecha, prov};

//	        Object datos1[] = {nom, ape, ocupa, sueldo};
	        ((DefaultTableModel) table.getModel()).addRow(datos1); 
			
			
		}
		} catch (Throwable e1) {e1.printStackTrace();}
		
		campo_clave.setEnabled(true);
		campo_tipo.setEnabled(false);
		campo_descripcion.setEnabled(false);
		
		btnBusquedaCompleta = new JButton("Busqueda completa");
		btnBusquedaCompleta.setBounds(430, 36, 188, 23);
		btnBusquedaCompleta.addActionListener(this);
		panel.add(btnBusquedaCompleta);
		
		btnActualiza = new JButton("Actualiza");
		btnActualiza.setBounds(529, 0, 89, 23);
		btnActualiza.addActionListener(this);
		panel.add(btnActualiza);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		
		if(b == btnClave) {
			campo_clave.setEnabled(true);
			campo_tipo.setEnabled(false);
			campo_descripcion.setEnabled(false);
			campo_tipo.setText("");
			campo_descripcion.setText("");
		}
		if(b == btnTipo) {
			campo_tipo.setEnabled(true);
			campo_clave.setEnabled(false);
			campo_descripcion.setEnabled(false);
			campo_clave.setText("");
			campo_descripcion.setText("");
		}
		if(b == btnDescipcin) {
			campo_descripcion.setEnabled(true);			
			campo_clave.setEnabled(false);
			campo_tipo.setEnabled(false);
			campo_clave.setText("");
			campo_tipo.setText("");
		}
		if(b == btnBuscar) {
			
			
			if(campo_clave.isEnabled()) {
				llamada_busca("cve",campo_clave.getText());
				
			}
			if(campo_tipo.isEnabled()) {
				llamada_busca("tipo",campo_tipo.getText());

			}
			if(campo_descripcion.isEnabled()) {
				llamada_busca("descripcion",campo_descripcion.getText());

			}
		}
		if(b == btnBusquedaCompleta) {
			llamada_busca("descripcion","");
	
		}
		if(b == btnActualiza) {
			llamada_busca("descripcion","");
			
		}
	}
	public void llamada_busca(String nombre, String busca) {
		
		c = new Conexion();
		eliminar();
		Statement st1 = null;
		ResultSet rs1 = null;
		try {
			st1 = c.conexion().createStatement();	
		if(nombre == "cve") {
			 rs1 = st1.executeQuery("Select * from historial where cve = "+Integer.parseInt(busca));
		}
		else {
			rs1 = st1.executeQuery("Select * from historial where "+nombre+" like '%"+busca+"%'");
			
		}
		
		while(rs1.next()) {
			int cve = rs1.getInt(1);
			String ac = rs1.getString(2);
			String tip = rs1.getString(3);
			String des = rs1.getString(4);
			int can= rs1.getInt(5);
			int pc= rs1.getInt(6);
			int pv= rs1.getInt(7);
			String fecha = rs1.getString(8);
			String prov= rs1.getString(9);
	
			
	        Object datos1[] = {cve, ac, tip, des, can, pc, pv, fecha, prov};

//	        Object datos1[] = {nom, ape, ocupa, sueldo};
	        ((DefaultTableModel) table.getModel()).addRow(datos1); 
			
			
		}
		} catch (Throwable e1) {
			JOptionPane.showMessageDialog(null, "Llena campo clave correctamente", "Advertencia", JOptionPane.WARNING_MESSAGE);
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
}