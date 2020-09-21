package papeleriaa;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import papeleriaa.Conexion;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class Proveedor extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proveedor frame = new Proveedor();
					frame.setVisible(true);
				} catch (Exception e) {e.printStackTrace();} 
				  catch (Throwable e) {e.printStackTrace();}
			} 
		});
	}

	/**
	 * Create the frame.
	 */
	
	Conexion c;
	DefaultTableModel modelo;
	JTable table;
	JButton btnAceptar;
	JTextField campo_proveedor;
	public JComboBox comboBox;
	JButton buttonAceptaElimina;
	private JTextField campo_elimina_proveedor;
	private JButton btnRegresar;
	AddRem ar;
	
	public Proveedor() throws Throwable {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 346, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 12, 311, 20);
		

		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Clave", "Tipo", "Descripción", "Cantidad"
			}
		));
		c = new Conexion();
		ActualizaBox();
		contentPane.add(comboBox);
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 77, 54, 14);
		contentPane.add(lblNombre);
		
		campo_proveedor = new JTextField();
		campo_proveedor.setBounds(84, 73, 140, 22);
		contentPane.add(campo_proveedor);
		campo_proveedor.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(234, 73, 87, 23);
		contentPane.add(btnAceptar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 102, 311, 2);
		contentPane.add(separator);
		
		JLabel lblAadeProveedor = new JLabel("A\u00F1ade proveedor");
		lblAadeProveedor.setBounds(10, 43, 107, 14);
		contentPane.add(lblAadeProveedor);
		
		JLabel lblEliminaProveedor = new JLabel("Elimina proveedor");
		lblEliminaProveedor.setBounds(10, 117, 107, 14);
		contentPane.add(lblEliminaProveedor);
		
		campo_elimina_proveedor = new JTextField();
		campo_elimina_proveedor.setBounds(84, 142, 140, 20);
		contentPane.add(campo_elimina_proveedor);
		campo_elimina_proveedor.setColumns(10);
		
		JLabel label = new JLabel("Nombre:");
		label.setBounds(10, 145, 54, 14);
		contentPane.add(label);
		
		buttonAceptaElimina = new JButton("Aceptar");
		buttonAceptaElimina.setBounds(234, 141, 87, 23);
		buttonAceptaElimina.addActionListener(this);
		contentPane.add(buttonAceptaElimina);
		
		btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(this);
		btnRegresar.setBounds(232, 192, 89, 23);
		contentPane.add(btnRegresar);

}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton b = (JButton) e.getSource();
		c = new Conexion();
		
		if(b == btnAceptar) {
				
	        Statement instruccion;
			try {
				instruccion = c.conexion().createStatement();
				instruccion.execute("insert into proveedor values('"+campo_proveedor.getText()+"')");
				ActualizaBox();
			} catch (Throwable e1) {e1.printStackTrace();			}
		}
		if(b == buttonAceptaElimina) {
			Statement instruccion;
			try {
				instruccion = c.conexion().createStatement();
				instruccion.execute("delete from proveedor where nombre = '"+campo_elimina_proveedor.getText()+"'");
				ActualizaBox();
			} catch (Throwable e1) {	e1.printStackTrace();			}
			
		}
		if(b == btnRegresar) {
			
			try {		
				
				ar = new AddRem();
				ar.setVisible(false);
				ar.campo_provee(String.valueOf(comboBox.getSelectedItem()));
//				ar.campo_proveedor.setText(String.valueOf(comboBox.getSelectedItem()));
//				ar.pausa(true);
//				ar.btnProveedor.doClick();
				ar.ActualizaBox();
				ar.repaint();
				} catch (Throwable e1) {	e1.printStackTrace();}
			
//			try {
////				Thread.sleep(3000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			this.dispose();
			
		}
	}
	
	public void ActualizaBox() throws Throwable {
		int cont = 0;
		String[] guardaAntes = new String[cont];
		String[] guarda = new String[cont];			//guarda = 0
		try {
		Statement st1 = c.conexion().createStatement();	
		ResultSet rs1 = st1.executeQuery("Select * from proveedor");
		while(rs1.next()) { //5
			
			String cve = rs1.getString(1);
//			System.out.println(cve);
//			if(cont != 0)
				
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
