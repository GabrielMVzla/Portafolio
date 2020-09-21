package proy;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Panel_Pago extends JPanel implements ActionListener {
	private JTextField campo_member;
	private JTextField campo_total;
	JButton btnAceptar;
	Conexion c;
	private JLabel lblMotoAPagar;
	private JTextField campo_monto;
	private JButton btnPagar;
	/**
	 * Create the panel.
	 */
	public Panel_Pago() {
		setBackground(new Color(0, 0, 128));
		setLayout(null);
		setBounds(10, 11, 10, 281);
		JLabel lblRealizaUnPago = new JLabel("Realiza un pago");
		lblRealizaUnPago.setHorizontalAlignment(SwingConstants.CENTER);
		lblRealizaUnPago.setForeground(new Color(255, 255, 255));
		lblRealizaUnPago.setBounds(10, 11, 100, 14);
		add(lblRealizaUnPago);
		
		campo_member = new JTextField();
		campo_member.setBounds(10, 61, 100, 25);
		campo_member.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
		add(campo_member);
		campo_member.setColumns(10);
		
		campo_total = new JTextField();
		campo_total.setBounds(10, 158, 100, 25);
		campo_total.setEnabled(false);
		add(campo_total);
		campo_total.setColumns(10);
		
		campo_monto = new JTextField();
		campo_monto.setBounds(10, 220, 100, 25);
		campo_monto.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();

				// Verificar si la tecla pulsada no es un digito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b' /* corresponde a BACK_SPACE */)) {
					e.consume(); // ignorar el evento de teclado
				}
			}
		});
		add(campo_monto);
		campo_monto.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setForeground(new Color(255, 255, 255));
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setBounds(10, 136, 100, 14);
		add(lblCantidad);
		
		JLabel lblCveMiembro = new JLabel("Cve miembro:");
		lblCveMiembro.setForeground(new Color(255, 255, 255));
		lblCveMiembro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCveMiembro.setBounds(10, 36, 100, 14);
		add(lblCveMiembro);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(10, 101, 100, 23);
		btnAceptar.addActionListener(this);
		
		add(btnAceptar);
		
		lblMotoAPagar = new JLabel("Monto a pagar");
		lblMotoAPagar.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotoAPagar.setForeground(new Color(255, 255, 255));
		lblMotoAPagar.setBounds(10, 194, 100, 14);
		add(lblMotoAPagar);
		
		btnPagar = new JButton("Pagar");
		btnPagar.setBounds(10, 252, 100, 23);
		btnPagar.addActionListener(this);
		add(btnPagar);
		btnPagar.setEnabled(false);
		campo_monto.setEnabled(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		Statement st = null;
		ResultSet rs = null, rs1 = null;
		c = new Conexion();
		if(b == btnAceptar) {
	
			try {
				 st = c.conexion().createStatement();
				 CallableStatement cal1 = c.conexion().prepareCall("{call con_memb_deu(?)}");	
				 cal1.setInt(1, Integer.parseInt(campo_member.getText()));
				 rs = cal1.executeQuery();
				while(rs.next()) { 
					
					if(rs.getInt(1) == Integer.parseInt(campo_member.getText())) {				
						int fine = rs.getInt(2);
						campo_total.setText(String.valueOf(fine));
						btnPagar.setEnabled(true);
						campo_monto.setEnabled(true);
						return;
					}
					
				}
			} catch (SQLException e1)
			{	
				JOptionPane.showMessageDialog(null,	e1.getMessage());
				return;
			} catch (Throwable e1) {	
				JOptionPane.showMessageDialog(null, "Llena los campos correctamente ");	
				return;
			}
			JOptionPane.showMessageDialog(null, "Clave \""+(Integer.parseInt(campo_member.getText()))+"\" no existe.");
		}
		if(b == btnPagar) {
			int suma = 0;
			try {	
//				st = c.conexion().createStatement();

				CallableStatement cal1 = c.conexion().prepareCall("{call adeudo(?,?)}");		
				cal1.setInt   (1, Integer.parseInt(campo_member.getText()));
				cal1.setInt(2, Integer.parseInt(campo_monto.getText()));
				
				suma = (Integer.parseInt(campo_total.getText())-
							Integer.parseInt(campo_monto.getText()));
				
				cal1.execute();

			}catch (SQLException sql) {
				
				JOptionPane.showMessageDialog(null, sql.getMessage());
				return;
			
			} catch (Throwable e2) {
				
				JOptionPane.showMessageDialog(null, "Llena campo correctamente");
				return;
			}
//			JOptionPane.showMessageDialog(null, "Clave \""+(Integer.parseInt(campo_member.getText()))+"\" no existe.");
			
			campo_member.setText("");
			campo_total.setText("");
			campo_monto.setText("");
			JOptionPane.showMessageDialog(null, "Pago realizado correctamente, nuevo total = "+suma);
			btnPagar.setEnabled(false);
			campo_monto.setEnabled(false);

		}
			
		}
		
	

}
