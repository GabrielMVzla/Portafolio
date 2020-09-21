package proy;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;


public class PanelF_Reservaciones extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField campo_busqueda;
	private JTable table;
	DefaultTableModel modelo;
	JButton btnConsultaTodasLas;
	JButton btnBuscarPorClave;
	Conexion c;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelF_Reservaciones frame = new PanelF_Reservaciones();
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
	public PanelF_Reservaciones() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 255));
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblReservaciones = new JLabel("Reservaciones");
		lblReservaciones.setBounds(10, 11, 83, 14);
		panel.add(lblReservaciones);
		
		btnConsultaTodasLas = new JButton("Consulta todas las reservaciones");
		btnConsultaTodasLas.setBounds(10, 36, 244, 23);
		btnConsultaTodasLas.addActionListener(this);

		panel.add(btnConsultaTodasLas);
	
		btnBuscarPorClave = new JButton("Buscar por clave de miembro");
		btnBuscarPorClave.setBounds(10, 65, 244, 23);
		btnBuscarPorClave.addActionListener(this);
		panel.add(btnBuscarPorClave);
		
		campo_busqueda = new JTextField();
		campo_busqueda.setBounds(283, 66, 121, 20);
		campo_busqueda.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
		panel.add(campo_busqueda);
		campo_busqueda.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 394, 125);
		panel.add(scrollPane);
		

		modelo = new DefaultTableModel();
		table = new JTable(modelo);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				
				"isbn", "member no", "log date"
			}
		));
		scrollPane.setViewportView(table);
		
		
		//llamada inicial a la consulta
		c = new Conexion();
		Statement st;
		try {
			st = c.conexion().createStatement();	
			ResultSet rs = st.executeQuery("Select * from reservation");
			
			while(rs.next()) {
				
				
					
					int isbn = rs.getInt(1);
					int member= rs.getInt(2);
					String logd = rs.getString(3);
					String remark= rs.getString(4);

					
			        Object datos1[] = {isbn,member , logd, remark};
//			        Object datos1[] = {nom, ape, ocupa, sueldo};
			        ((DefaultTableModel) table.getModel()).addRow(datos1); 


				
			}
			} catch (Throwable e1) {
			}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton b = (JButton) e.getSource();
		Statement st;
		c = new Conexion();
		if(btnConsultaTodasLas == b) {
			llamada_inicial();

		}
		if(btnBuscarPorClave == b)
		{
			eliminar();
			try {
				st = c.conexion().createStatement();	
				ResultSet rs = st.executeQuery("Select * from reservation where member_no = "+Integer.parseInt(campo_busqueda.getText()));
//				if(campo_busqueda.getText().equals(""))
					
				while(rs.next()) {
						if(rs.getInt(2) == Integer.parseInt(campo_busqueda.getText())) {
							
							int isbn = rs.getInt(1);
							int member= rs.getInt(2);
							String logd = rs.getString(3);
							String remark= rs.getString(4);
	
							
					        Object datos1[] = {isbn,member , logd, remark};
		//			        Object datos1[] = {nom, ape, ocupa, sueldo};
					        ((DefaultTableModel) table.getModel()).addRow(datos1); 
					        return;
						}
//					else
//					{
//						JOptionPane.showMessageDialog(null, "Cve nXo existe");
//						 return;
//					}
	
					}
				} catch (Throwable e1) {
//					System.out.println("porqeeee");
					JOptionPane.showMessageDialog(null, " Llena el campo correctamente");
					return;
				}
			JOptionPane.showMessageDialog(null, "Clave \""+(Integer.parseInt(campo_busqueda.getText()))+"\" no existe.");

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
	 public void llamada_inicial() {
		 c = new Conexion();
			Statement st;
			eliminar();
			try {
				st = c.conexion().createStatement();	
				ResultSet rs = st.executeQuery("Select * from reservation");
				
				while(rs.next()) {
					
						
						int isbn = rs.getInt(1);
						int member= rs.getInt(2);
						String logd = rs.getString(3);
						String remark= rs.getString(4);

						
				        Object datos1[] = {isbn,member , logd, remark};
	//			        Object datos1[] = {nom, ape, ocupa, sueldo};
				        ((DefaultTableModel) table.getModel()).addRow(datos1); 
					

				}
				} catch (Throwable e1) {
				}
		 
	 }
}
