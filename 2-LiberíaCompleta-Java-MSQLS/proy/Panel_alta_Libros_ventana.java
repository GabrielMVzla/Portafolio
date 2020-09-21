package proy;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Panel_alta_Libros_ventana extends JFrame implements ActionListener{

	private JPanel contentPane;
	JButton btnArticulos ;
	Panel_Alta_libro_C1 panelItem;
	Panel_Alta_libro_C2 panelCopy;
	JButton btnCopy;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					Prueba_Alta_Libros pal = new Prueba_Alta_Libros();
//					pal.setVisible(true);

					Panel_alta_Libros_ventana frame = new Panel_alta_Libros_ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} catch (Throwable e) {	e.printStackTrace();	}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Throwable 
	 */
	public Panel_alta_Libros_ventana() throws Throwable {	
		
		setTitle("Biblioteca - Libros");

//		Prueba_Alta_Libros pal = new Prueba_Alta_Libros();
//		pal.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, 36, 730, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnArticulos = new JButton("Articulos");
		btnArticulos.setBounds(10, 11, 89, 23);
		btnArticulos.addActionListener(this);
		contentPane.add(btnArticulos);
		
		panelItem = new Panel_Alta_libro_C1();
		panelItem.setVisible(true);
		getContentPane().add(panelItem);
		
		btnCopy = new JButton("Copias");
		btnCopy.setBounds(179, 11, 89, 23);
		btnCopy.addActionListener(this);

		panelCopy = new Panel_Alta_libro_C2();
		panelCopy.setVisible(false);
		getContentPane().add(panelCopy);
		contentPane.add(btnCopy);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton b =  (JButton) e.getSource();
		if(b == btnArticulos) {
			
			panelCopy.setVisible(false);
			panelItem.setVisible(true);
			
		}
		if(b == btnCopy) {
			
			panelItem.setVisible(false);
			panelCopy.setVisible(true);
			
		}
		
		
	}
}
