package papeleriaa;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

public class Factura extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Factura frame = new Factura();
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
	
	JLabel campo_fecha;
	JLabel campo_total;
	JLabel campo_cantidad;
	JLabel campo_compra;
	JTextArea campo_descripcion;  
	JTextField textField;
	JButton btnImprimir;
	public Factura() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 219, 367);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(65, 32, 36, 14);
		contentPane.add(lblFecha);
		
		campo_fecha = new JLabel("");
		campo_fecha.setBounds(128, 32, 65, 14);
		contentPane.add(campo_fecha);
		
		JLabel lblCompra = new JLabel("Compra");
		lblCompra.setBounds(10, 57, 65, 14);
		contentPane.add(lblCompra);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 82, 65, 14);
		contentPane.add(lblCantidad);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(10, 107, 75, 14);
		contentPane.add(lblDescripcion);
		
		JLabel lblGraciasPorSu = new JLabel("Gracias por su compra");
		lblGraciasPorSu.setHorizontalAlignment(SwingConstants.CENTER);
		lblGraciasPorSu.setBounds(10, 248, 183, 41);
		contentPane.add(lblGraciasPorSu);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setBounds(78, 181, 60, 14);
		contentPane.add(lblTotal);
		
		campo_total = new JLabel("");
		campo_total.setBounds(97, 206, 60, 25);
		contentPane.add(campo_total);
		
		campo_cantidad = new JLabel("");
		campo_cantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		campo_cantidad.setBounds(110, 82, 83, 14);
		contentPane.add(campo_cantidad);
		
		campo_compra = new JLabel("");
		campo_compra.setHorizontalAlignment(SwingConstants.RIGHT);
		campo_compra.setBounds(85, 57, 108, 14);
		contentPane.add(campo_compra);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 240, 46, 14);
		contentPane.add(lblNombre);
		
		textField = new JTextField();
		textField.setBounds(65, 232, 128, 25);
		textField.setSelectionStart(0);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 295, 203, 33);
		contentPane.add(panel);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(this);
		panel.add(btnImprimir);
		
		JLabel lblImprentaSaDe = new JLabel("Imprenta S.A de C.V.");
		lblImprentaSaDe.setFont(new Font("Yu Gothic Light", Font.BOLD, 11));
		lblImprentaSaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblImprentaSaDe.setBounds(29, 11, 139, 14);
		contentPane.add(lblImprentaSaDe);
		
		campo_descripcion = new JTextArea();
		campo_descripcion.setSelectionStart(1);
		campo_descripcion.setForeground(Color.BLACK);
		campo_descripcion.setEditable(false);
		campo_descripcion.setLineWrap(true);
		campo_descripcion.setWrapStyleWord(true);
		campo_descripcion.setBounds(97, 107, 96, 64);
		contentPane.add(campo_descripcion);
		
		JLabel label = new JLabel("$");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(58, 206, 29, 25);
		contentPane.add(label);
	}
	
	public void llenar_campos( String compra, int cantidad, String descripcion, int total) {

		Calendar c2 = new GregorianCalendar();
		campo_fecha.setText(c2.get(Calendar.DATE)+"/"+(c2.get(Calendar.MONTH)+1)+"/"+(	c2.get(Calendar.YEAR)));

		campo_compra.setText(compra);
		campo_cantidad.setText(String.valueOf(cantidad));
		campo_descripcion.setText(descripcion);
		campo_total.setText(String.valueOf(total));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b == btnImprimir) {
			this.dispose();

		}
	}
}
