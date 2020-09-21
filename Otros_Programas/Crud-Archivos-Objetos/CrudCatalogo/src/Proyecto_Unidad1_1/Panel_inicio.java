package Proyecto_Unidad1_1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;

public class Panel_inicio extends JPanel {

	/**
	 * Create the panel.
	 */
	public Panel_inicio() {
		setBackground(new Color(0, 0, 0));

//		setBounds(0, 0, 761, 200);
		JLabel mostrarJLabel = new JLabel( "Articulos", SwingConstants.CENTER );
	    mostrarJLabel.setForeground( Color.white);
	    Font nuevo = new Font( "Castellar", Font.BOLD, 48);
	    mostrarJLabel.setFont( nuevo );
	    mostrarJLabel.setBounds( 0, 0, 650 , 695);
	      
	    add(mostrarJLabel, BorderLayout.CENTER);
	    setLayout(null);
	    setBounds(0, 0, 660, 420);
	    JLabel label = new JLabel(new ImageIcon("dibujo_articulos01.jpg"));
	    label.setBounds(-50, 0, 761, 300);
//	    label.setBounds(-50, 10, 761, 420);
	    add(label);
		setBorder(new EmptyBorder(1, 1, 1, 1));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(22, 313, 613, 2);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(22, 376, 613, 2);
		add(separator_1);

	}

}
