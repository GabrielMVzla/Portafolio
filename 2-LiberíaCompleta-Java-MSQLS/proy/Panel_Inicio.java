package proy;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Panel_Inicio extends JPanel {
	
	private JTextField txtInicioBliblioteca;

	public Panel_Inicio() 
	{
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		setBounds(110,10, 415, 290);
		
		txtInicioBliblioteca = new JTextField();	
		txtInicioBliblioteca.setHorizontalAlignment(SwingConstants.CENTER);
		txtInicioBliblioteca.setForeground(Color.WHITE);
		txtInicioBliblioteca.setBackground(Color.DARK_GRAY);
		txtInicioBliblioteca.setFont(new Font("Tempus Sans ITC", Font.BOLD, 36));
		txtInicioBliblioteca.setBounds(10, 118, 395, 47);
		txtInicioBliblioteca.setText("inicio bliblioteca");
		add(txtInicioBliblioteca);
		txtInicioBliblioteca.setColumns(10);

	}

}
