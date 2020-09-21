package proy;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Panel_Prestamos_Consultas_Ventana extends JFrame implements ActionListener{

	private JPanel contentPane;
	Panel_Prestamos_C_1 panelPrestamosC1;
	JButton btnMuestraHistorialDe;
	JButton btnMuestraPrstamosEn;
	Panel_Prestamos_C_1 ppc1;
	Panel_Prestamos_C_2 ppc2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel_Prestamos_Consultas_Ventana frame = new Panel_Prestamos_Consultas_Ventana();
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
	public Panel_Prestamos_Consultas_Ventana() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 36, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnMuestraHistorialDe = new JButton("Muestra Historial de pr\u00E9stamos");
		btnMuestraHistorialDe.setBounds(10, 11, 206, 23);
		btnMuestraHistorialDe.addActionListener(this);
		contentPane.add(btnMuestraHistorialDe);

		ppc1 = new Panel_Prestamos_C_1();
		ppc2 = new Panel_Prestamos_C_2();
		ppc1.setVisible(true);
		contentPane.add(ppc1);
		ppc2.setVisible(false);
		contentPane.add(ppc2);
		btnMuestraPrstamosEn = new JButton("Muestra pr\u00E9stamos en marcha");
		btnMuestraPrstamosEn.setBounds(238, 11, 199, 23);
		btnMuestraPrstamosEn.addActionListener(this);
		contentPane.add(btnMuestraPrstamosEn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		JButton b = (JButton) e.getSource();
		if( b== btnMuestraHistorialDe)
		{
			ppc2.setVisible(false);
			 ppc1.setVisible(true);
		}
		if( b== btnMuestraPrstamosEn)
		{
			ppc1.setVisible(false);
			ppc2.setVisible(true);

		}
	}

}
