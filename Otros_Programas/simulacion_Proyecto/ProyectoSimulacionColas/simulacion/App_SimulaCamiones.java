package simulacion2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class App_SimulaCamiones extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	
	double salarioNormal3 = 0, salarioExtra3 = 0, ocioCamion3 = 0, operacionAlmacen3 = 0, costoTotal3 = 0;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JButton btnNvoValor;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App_SimulaCamiones frame = new App_SimulaCamiones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	//cuadruplos solo para operaciones
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton) e.getSource();
		if(b == btnNvoValor)
		{		
			dispose(); 
			App_SimulaCamiones frame = new App_SimulaCamiones();
			frame.setVisible(true);
			
		}
	}
		
	/**
	 * Create the frame.
	 */
	public App_SimulaCamiones() {
		
		UIManager.LookAndFeelInfo[] apariencias =  UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel( apariencias[1].getClassName() );
			SwingUtilities.updateComponentTreeUI( this );

		} catch (ClassNotFoundException e) {e.printStackTrace();} 
		catch (InstantiationException e) {e.printStackTrace();} 
		catch (IllegalAccessException e) {e.printStackTrace();}
		catch (UnsupportedLookAndFeelException e) {e.printStackTrace();} 
		
		setTitle("Simulación de camiones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 967, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 931, 245);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Random", "Tiempo / Llegada", "Tiempo Llegada", "Inicio Servicio", "Random", "Tiempo Servicio", "Terminaci\u00F3n servicio", "Ocio de personal", "Espera del cami\u00F3n", "Tama\u00F1o cola"
			}
		));
		scrollPane.setViewportView(table);
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		String empleadosO = "";
		try
		{
		
			do
			{
				empleadosO = JOptionPane. showInputDialog(null, "Ingresa cantidad de empleados que van a operar. (mín. 3, máx. 6)", JOptionPane.INFORMATION_MESSAGE );
				
			}while(Integer.parseInt(empleadosO) < 3 || Integer.parseInt(empleadosO) > 6);
			
		} 
		catch (Exception e) 
		{
		  JOptionPane.showMessageDialog(null, "Tú inserción no es un número");
		  System.exit(0);
		}
		int empleadosOperando = Integer.parseInt(empleadosO);
//		Scanner lee = new Scanner(System.in);
		Random rnd = new Random();
		DecimalFormat df = new DecimalFormat("0.00000");

		Ayudas b = new Ayudas();
		Probabilidades probs = new Probabilidades();
		JLabel lblNewLabel;
		int camionesAlAbrir = probs.Apertura();
		if(camionesAlAbrir > 0)
			lblNewLabel = new JLabel("\n\n\t\t\t\t\t\t\t\tAlmacén abre! \"" + camionesAlAbrir + "\" camiones en espera.\n ");
		else
			lblNewLabel = new JLabel("\n\n\t\t\t\t\t\t\t\tAlmacén abre! no hay camiones esperando.\n ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(212, 11, 521, 20);
		contentPane.add(lblNewLabel);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 325, 931, 54);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Tama\u00F1o del equipo", "Salario normal", "Salario extra", "Ocio del camion", "Operaci\u00F3n del almacen", "Costos totales"
			}
		));
		table_1.getColumnModel().getColumn(2).setResizable(false);
		scrollPane_1.setViewportView(table_1);
		
		btnNvoValor = new JButton("Nvo. valor");
		btnNvoValor.setMnemonic('n');
		btnNvoValor.addActionListener(this);
		btnNvoValor.setBounds(764, 5, 177, 33);
		contentPane.add(btnNvoValor);
		
		int i = 0;
		ArrayList<Cola_Llegadas> colaLlega = new ArrayList<Cola_Llegadas>();
		
		//510 es equivalente a 8:30 (ocho horas y media) => 23:00 - 07:30
		double random2 = 0;
		int finalLlegadas = 0;
		int finalServicios = 0;
		int tiempoS = 0;
		int ultimoServicio = 0;
		ArrayList<Integer> hr = new ArrayList<Integer>();
		ArrayList<Integer> hra = new ArrayList<Integer>();
		ArrayList<Integer> encola = new ArrayList<Integer>();
		ArrayList<Integer> colaCamiones = new ArrayList<Integer>();
		ArrayList<DatosDeSimulacion> simulacion = new ArrayList<DatosDeSimulacion>();
		
		
		//en este while solo hará que trabajen hasta las 3 de la mañana
		//(300 = 300 minutos) => (300 minutos = 4:00 horas) => (4:00 horas = de las 23:00 hrs a las 3:00 hrs) 
		//510 = 8:30, 8:00 - 30 => 23:00 - 7:30
		while(finalLlegadas < 510){
			double random1 = rnd.nextDouble();
			finalLlegadas += probs.Llegada(random1);
			
			if(finalLlegadas > 510)
				break;
			
			if(i == 0) 
			{			
				//Los que llegaron antes de abrir el almacén
				for (int j = 0; j < camionesAlAbrir; j++) 
				{
					random2 = rnd.nextDouble();
					if(empleadosOperando == 3)
						tiempoS = probs.Servicio3(random2);
					if(empleadosOperando == 4)
						tiempoS = probs.Servicio4(random2);
					if(empleadosOperando == 5)
						tiempoS = probs.Servicio5(random2);
					if(empleadosOperando == 6)
						tiempoS = probs.Servicio6(random2);
					
					colaLlega.add(new Cola_Llegadas(-1, -1, b.ConvierteHora(0), random2, tiempoS));
					
					hr.add(0);
					hra.add(0);
				}
			}
			//para todo los demás camiones
			random2 = rnd.nextDouble();
			if(empleadosOperando == 3)
				tiempoS = probs.Servicio3(random2);
			if(empleadosOperando == 4)
				tiempoS = probs.Servicio4(random2);
			if(empleadosOperando == 5)
				tiempoS = probs.Servicio5(random2);
			if(empleadosOperando == 6)
				tiempoS = probs.Servicio6(random2);			
			hr.add(finalLlegadas);
			hra.add(finalLlegadas);
			colaLlega.add(new Cola_Llegadas(random1, probs.Llegada(random1), b.ConvierteHora(finalLlegadas), random2, tiempoS));
			
			i++;
		};
		
		ArrayList<Cola_Llegadas> l = colaLlega;
		int cola = 0 ;

		i = 0;
		int j = 0, i2 = 0, i3 = 0, ocio = 0;
		String longitudOcio = "", longitudEsperaCa = "";
		
		int inicio = 0, terminadoAnterior = 0, esperaCamion = 0, blanquito1 = 0, blanquito2 = 0, i4 = 0;
		while(colaLlega.size() != 0)
		{
			int minServicios = l.get(j).tiempoServicio;
			if(i2 == 0)//1, 2, 3, 4
				inicio = hr.get(j);
			
			finalServicios += minServicios;
			
			while(l.get(j).tiempoLlegada == -1) 
			{
				
				if(hr.get(j) > inicio )
				{
					inicio = hr.get(j);
				}
				encola.add(inicio + l.get(j).tiempoServicio);
				
				if(inicio != (terminadoAnterior)) //me da el tiempo de ocio de los empleados
					ocio = inicio - terminadoAnterior ;
				else 
					ocio = 0;
				
				if(ocio == 0) //me da el tiempo de espera de los camiones
					esperaCamion = inicio - hr.get(j);
				else
					esperaCamion = 0;
				
				
				//Esto no se ocupa porque ya está en grafico
				longitudOcio = String.valueOf(ocio); //para que se imprima ordenado después de ocio
				if(longitudOcio.length() == 1)
					blanquito1 = 1;
				else if(longitudOcio.length() == 2)
					blanquito1 = 2;
				else
					blanquito1 = 3;
				longitudEsperaCa = String.valueOf(esperaCamion); //para que se imprima ordenado después de ocio
				if(longitudEsperaCa.length() == 1)
					blanquito2 = 1;
				else if(longitudEsperaCa.length() == 2)
					blanquito2 = 2;
				else
					blanquito2 = 3;
				
				
				
				simulacion.add(new DatosDeSimulacion(
						""							//+ b.Blancos(7) 			+ 
						,""     								//+ b.Blancos(2) 			+
						,l.get(j).hora 			   								//+ b.Blancos(5)			+
						,b.ConvierteHora(inicio) 								//+ b.Blancos(5) 			+
						,df.format(l.get(j).random2) 	 						//+ b.Blancos(7)			+ 
						,l.get(j).tiempoServicio			   		 				//+ b.Blancos(2) 			+
						,b.ConvierteHora(inicio + l.get(j).tiempoServicio)		//+ b.Blancos(5) 			+
						,ocio													//+ b.Blancos(blanquito1) +
						,esperaCamion											//+ b.Blancos(blanquito2)	+
						,0)
				);
				terminadoAnterior = inicio + l.get(j).tiempoServicio;
				inicio += l.get(j).tiempoServicio;
				
				colaLlega.remove(j);
				hr.remove(j);

			}
			
			if(hr.get(j) > inicio )
			{
				inicio = hr.get(j);
			}
			
			encola.add(inicio + l.get(j).tiempoServicio);
			
			if(inicio != (terminadoAnterior)) //me da el tiempo de ocio de los empleados
				ocio = inicio - terminadoAnterior ;
			else 
				ocio = 0;
			
			
			if(terminadoAnterior < 240 && inicio > 240)
			{
				
				i++;
				inicio = 270;
				if(terminadoAnterior < 240)
					ocio = 240 - terminadoAnterior ;
			}
			else if((terminadoAnterior) > 239 &&  i == 0) 
			{
				i++;
				inicio = terminadoAnterior + 30;
				if(hr.get(j) > (terminadoAnterior + 30) )
					ocio = hr.get(j) - (terminadoAnterior + 30);
				else 
					ocio = 0;
			}
			if(ocio == 0) //me da el tiempo de espera de los camiones
				esperaCamion = inicio - hr.get(j);
			else
				esperaCamion = 0;
			
			
			//Esto no se ocupa porque ya está en grafico

			
			longitudOcio = String.valueOf(ocio); //para que se imprima ordenado después de ocio
			if(longitudOcio.length() == 1)
				blanquito1 = 1;
			else if(longitudOcio.length() == 2)
				blanquito1 = 2;
			else
				blanquito1 = 3;
			longitudEsperaCa = String.valueOf(esperaCamion); //para que se imprima ordenado después de ocio
			if(longitudEsperaCa.length() == 1)
				blanquito2 = 1;
			else if(longitudEsperaCa.length() == 2)
				blanquito2 = 2;
			else
				blanquito2 = 3;
//			if(i == 1 && ( hr.get(j) > (inicio - 30) && hr.get(j) < inicio))
//			{
//				esperaCamion =  inicio - hr.get(j);
//			}
		
//			System.out.println( 
//					Object datos1[] = {
					simulacion.add(new DatosDeSimulacion(
							df.format(l.get(j).random1)								//+ b.Blancos(7) 			+ 
							,l.get(j).tiempoLlegada     								//+ b.Blancos(2) 			+
							,l.get(j).hora 			   								//+ b.Blancos(5)			+
							,b.ConvierteHora(inicio) 								//+ b.Blancos(5) 			+
							,df.format(l.get(j).random2) 	 						//+ b.Blancos(7)			+ 
							,l.get(j).tiempoServicio			   		 				//+ b.Blancos(2) 			+
							,b.ConvierteHora(inicio + l.get(j).tiempoServicio)		//+ b.Blancos(5) 			+
							,ocio													//+ b.Blancos(blanquito1) +
							,esperaCamion											//+ b.Blancos(blanquito2)	+
//							,colaCamiones.get(i2)
							,0)
					);

			terminadoAnterior = inicio + l.get(j).tiempoServicio;
			inicio += l.get(j).tiempoServicio	;
			
//			if(finalLlegadas > 510)
//				System.out.println("inicio = " + (inicio - l.get(j).tiempoServicio) + "\tterminadoAnt = " + terminadoAnterior);
			colaLlega.remove(j);
			hr.remove(j);
			
			i2++;
		}
		
		salarioNormal3 = 480 ;
		salarioNormal3 = ((salarioNormal3/60) * 25) * empleadosOperando;
		
		salarioExtra3 = (terminadoAnterior - 510) ;
		salarioExtra3 = ((salarioExtra3/60) * 37.5) * empleadosOperando; 
		if(salarioExtra3 < 0)
			salarioExtra3 = 0;
		operacionAlmacen3 = terminadoAnterior;
		operacionAlmacen3 = (operacionAlmacen3/60) * 500;
		
		cola = 0 ;
		
		for (int y = 0; y < encola.size()-1; y++) 
		{
			
			cola = 0;
			if(encola.get(y) > hra.get(y+1))
				cola++;
			if(y < encola.size()-2)
				if(encola.get(y) > hra.get(y+2))
					cola++;
			if(y < encola.size()-3)
				if(encola.get(y) > hra.get(y+3))
					cola++;
			if(y < encola.size()-4)
				if(encola.get(y) > hra.get(y+4))
					cola++;
			if(y < encola.size()-5)
				if(encola.get(y) > hra.get(y+5))
					cola++;
			
			colaCamiones.add(cola);
			
			if(y == encola.size()-2)
			{
				cola = 0;
				colaCamiones.add(cola);
			}
		}
		int indice = 0;
		for (int k = 0; k < simulacion.size(); k++) 
		{
	        Object datos1[] = {
	        		simulacion.get(k).random1
					,simulacion.get(k).timeEntreLlega
					,simulacion.get(k).timeLlega
					,simulacion.get(k).inicioService
					,simulacion.get(k).random2
					,simulacion.get(k).timeService
					,simulacion.get(k).terminaService
					,simulacion.get(k).ocio
					,simulacion.get(k).esperaCamion
					,colaCamiones.get(indice)
	        
	        };
	        
	        ocioCamion3 += simulacion.get(k).esperaCamion;
	        indice++;
	        ((DefaultTableModel) table.getModel()).addRow(datos1); 
			
		}
		ocioCamion3 = (ocioCamion3/60) * 100;
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		costoTotal3 =  salarioNormal3 + salarioExtra3 + ocioCamion3 + operacionAlmacen3;
		  Object datos1[] = {
				  empleadosOperando,
				  salarioNormal3 , 
				  salarioExtra3 , 
				  ocioCamion3 ,
				  operacionAlmacen3,
				  costoTotal3
	        };
	        
	        ((DefaultTableModel) table_1.getModel()).addRow(datos1); 
		
		
	}
}
