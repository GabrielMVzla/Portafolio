package proyecto_topicos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
import javax.xml.soap.Text;

public class Proyecto_Topicos{

	public static void main(String[] args) {
		
		MiPanelito mp = new MiPanelito("Inicio");

		
		mp.setVisible(true);
		mp.setLocation(450,150);
		mp.setSize(250, 350);
		mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
class MiPanelito extends JFrame{

	private static final long serialVersionUID = 1L;
	private BorderLayout esquema;
	private JMenuItem elementoAcercaDe, actualiza;
	JMenuItem elementoSalir, imprime,cosultaC,guardar,recuperar;
	int desbloquea = 0;
	JButton jb;
	Text t;
	JLabel jl;
	static JFrame ven2, nv;
	
	public MiPanelito(String a) 
	{
		super(a);	
		UIManager.LookAndFeelInfo[] apariencias =  UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel( apariencias[3].getClassName() );
			SwingUtilities.updateComponentTreeUI( this );

		} catch (ClassNotFoundException e) {e.printStackTrace();} 
		catch (InstantiationException e) {e.printStackTrace();} 
		catch (IllegalAccessException e) {e.printStackTrace();}
		catch (UnsupportedLookAndFeelException e) {e.printStackTrace();} 
		

		esquema = new BorderLayout( 5, 5 );
		setLayout( esquema );
//		MiPanela mp = new MiPanela();
	    JMenuBar barra = new JMenuBar(); // crea la barra de menús
	    setJMenuBar(barra);
	    
	    ManejadorArchivo manejadorArchivo = new ManejadorArchivo(); // manejador elementos del menú archivo
	    JMenu menuArchivo = new JMenu( "Archivo" ); // crea el menú archivo
	    menuArchivo.setMnemonic( 'A' ); 
	    actualiza = new JMenuItem("Actualizar Catálogo");
	    actualiza.addActionListener( manejadorArchivo );

	    imprime = new JMenuItem("Imprime");
	    imprime.addActionListener( manejadorArchivo );
	      
	    cosultaC = new JMenuItem("Cosulta catálogo");
	    cosultaC.addActionListener( manejadorArchivo );
	    
	    MiPanela mp1 = new MiPanela();
	    guardar = new JMenuItem("Guardar");
	    guardar.addActionListener( mp1.VieneDeFuera() );

	    recuperar = new JMenuItem("Recuperar");
	    recuperar.addActionListener( manejadorArchivo );

	    elementoSalir = new JMenuItem( "Salir" );
	    elementoSalir.addActionListener( manejadorArchivo );
	    
	    menuArchivo.add(actualiza);
	    menuArchivo.add(cosultaC);
	    menuArchivo.add(guardar);
	    menuArchivo.add(recuperar);
	    menuArchivo.add(imprime);
	    menuArchivo.add(elementoSalir ); 
	    
	    JMenu ayuda = new JMenu("Ayuda");
	    elementoAcercaDe = new JMenuItem( "Acerca de..." );
	    elementoAcercaDe.addActionListener(manejadorArchivo);
	    ayuda.add(elementoAcercaDe);
	    
	    barra.add( menuArchivo );
	    barra.add(ayuda);
	      
	    JLabel mostrarJLabel = new JLabel( "Clientes", SwingConstants.CENTER );
	    mostrarJLabel.setForeground( Color.BLACK);
	    getContentPane().setBackground( new Color(182, 255,251, 150) );
	    Font nuevo = new Font( "Castellar", Font.BOLD, 20);
	      mostrarJLabel.setFont( nuevo );
	      
	    add(new JLabel(new ImageIcon("imagenes\\cliente.png")));
	    add(mostrarJLabel, BorderLayout.NORTH);

	    actualiza.addActionListener( new ActionListener() {
	    	public  void actionPerformed( ActionEvent evento ) {
	    		ven2 = new JFrame("Programa");
//	    		dispose();
	    		ven2.setVisible(true);
//	    		ven2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		ven2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    		ven2.add(mp1);
	    		ven2.pack();
	    		ven2.setLocation(650,150);	
	    		MiPanelito.ven2.validate();		

	    		
	    	}
	    });
	}
	class ManejadorArchivo implements ActionListener 
	{
	// procesa las selecciones de color y fuente
		public void actionPerformed( ActionEvent evento )
		{
			JMenuItem fuente = (JMenuItem) evento.getSource();
			
			if ( fuente == actualiza )
				return;
			else if ( fuente == elementoAcercaDe )
			{	  
				JOptionPane.showMessageDialog(null, "Ibarra Rivera Alexia Dayane\nMontes Valenzuela José Gabriel");
			}
			else if(fuente == elementoSalir)
			{
				System.exit( 0 ); // sale de la aplicación
			}
//			else if(fuente == guardar) {
//				MiPanela mo = new MiPanela();
//				guardar.addActionListener((ActionListener) evento);asdfasdf
//				mo.VieneDeFuera();				
//				
//			}
			else if(fuente == recuperar) {
				String cadena = "";
				try {
					cadena = Archivo1.muestraContenidoDatos();
				} catch (IOException e) {e.printStackTrace();}
				nv = new JFrame("Datos");
				nv.setVisible(true);
				nv.setSize(700, 150);;
				nv.setLocationRelativeTo(null);
				
				JPanel nvp = new JPanel();
				
				JTextArea txtDestino = new JTextArea(5,76);
				txtDestino.setLineWrap( true );
				txtDestino.setWrapStyleWord( true );

				txtDestino.setEditable( false );
				txtDestino.append( cadena );
				
				nvp.add( new JScrollPane( txtDestino ) );
//				nvp.add(txtDestino);
				nv.add(nvp);
			
				MiPanelito.nv.validate();
			}
			else {
				JOptionPane.showMessageDialog(null, "Reservado para futuras vesiones.");
				
			}
//			System.out.println("sipi");
			esquema.layoutContainer( MiPanelito.this );
			MiPanelito.this.validate();		
		}	
	}
}
class MiPanela extends JPanel
{
	private static final long serialVersionUID = 1L;

	final static int valor = 35, Cas = 6;

	JTextField		txtCampo01, campoTel, edad, campoCveBusca, txtCampo02, txtCampo03;
	JLabel user, espacios, poderMo, guardado;
	JCheckBox		cbMostrar;

	private GridLayout cuadricula1;
	JButton agrega , save, buscar, regresa, modifica;
	FlowLayout esquema, esquem;
	BorderLayout esquema1;

//	MiPanelito mp = null;
	boolean acc = false, activo, activoDesactivo;
	char			Original;
	int c = 1, c2 = 0, modificar, edadUser;
	String nombre,tel, agg, eda;
	String domicilio, use;
	Archivo archi = null;
	MiOyente3 miOyente3 = new MiOyente3();

	public MiOyente3 VieneDeFuera() {
		
		return miOyente3;
		
	}
	public MiPanela()
	 {
		
//		MiOyente miOyente = new MiOyente();
		MiOyente2 miOyente2 = new MiOyente2();
		MiOyente4 miOyente4 = new MiOyente4();
		esquema1 = new BorderLayout(5,5);
		setLayout( esquema1 );

		FlowLayout guar = new FlowLayout();
		guar.setAlignment(FlowLayout.RIGHT);
		FlowLayout reg = new FlowLayout();
		reg.setAlignment(FlowLayout.LEFT);
		JPanel guardar = new JPanel();
		guardar.setLayout(guar);
		agrega = new JButton("Agrega");	
		agrega.addActionListener(miOyente2);
		
		espacios = new JLabel("                                                                                                  ");
		poderMo = new JLabel("                     Puedes modificar                        ");

		poderMo.setFont(new Font("serif", Font.BOLD, 16));
		poderMo.setForeground( Color.GREEN);
		poderMo.setVisible(false);
//		modifica.addActionListener(miOyente4);

		
		regresa = new JButton(new ImageIcon("imagenes\\regresa (4).png"));		
		regresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MiPanelito.ven2.dispose();
			}
			
		});
		guardar.add(regresa);
		guardar.add(new JLabel("Regresar"));
		guardar.add(espacios);
		guardar.add(poderMo);

		guardar.add(agrega);

		String cadena = "", sumita = "";
		try {
			cadena = Archivo1.muestraContenido();
			sumita = "";
		} catch (IOException e) {e.printStackTrace();}
		int numero = 1;
		if(cadena.length()>0)
		{
			numero =((cadena.length()/(valor*Cas))-1);	
			
				sumita = String.valueOf(cadena.charAt(((valor*Cas)*numero)));					
				sumita = String.valueOf((numero + 2));
				c=Integer.parseInt(sumita);
				
		}
		else
			sumita = "1";
		c=Integer.parseInt(sumita);

		tel = "";
		nombre = "";
		domicilio = "";
		agg = "1";
		use ="";
		eda = "";
		
		JPanel info = new JPanel();
		cuadricula1 = new GridLayout( 6, 2, 0, 5 );
		info.setLayout( cuadricula1 );
		
		txtCampo01 = new JTextField(sumita);
		txtCampo01.setEditable( false );
//		txtCampo01.addActionListener( miOyente );

		txtCampo02 = new JTextField( "" );
		txtCampo02.setEditable( false );
//		txtCampo02.addActionListener( miOyente );

		campoTel = new JTextField( /*"667"+*/String.valueOf(tel) );
		campoTel.setEditable( false );
//		campoTel.addActionListener( miOyente );
		
		edad = new JTextField(String.valueOf(eda));
		edad.setEditable( false );
//		edad.addActionListener( miOyente );

		txtCampo03 = new JTextField(domicilio , 10 );
		txtCampo03.setEditable( false );
//		txtCampo03.addActionListener( miOyente );
		
		user = new JLabel("Usuario activo");
		user.setFont(new Font("serif", Font.BOLD, 20));
	    user.setForeground( Color.GREEN);

//		//PANEL DE BOTONES PARTE INFERI
//		JLabel jl = new JLabel("Activar/Desactivar");
//		jl.setToolTipText("activar y desactivar cliente");

	    JButton cara = new JButton((new ImageIcon("imagenes\\cliente (1).png")));
	    cara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activo = user.getForeground() == Color.GREEN;
				
				if (activo ) { 
					user.setText("Usuario inactivo");
					user.setFont(new Font("serif", Font.BOLD, 20));
					user.setForeground( Color.RED);
				}
					else if(!activo) {

					user.setForeground( Color.GREEN);
					user.setFont(new Font("serif", Font.BOLD, 20));
					user.setText("Usuario activo");
				}
				esquema1.layoutContainer( MiPanela.this );
			}
	    	
	    });
		info.add(cara  );
		info.add(user);
		info.add( new JLabel( "Clave" ) );
		info.add( txtCampo01 );
		info.add( new JLabel( "Nombre" ) );
		info.add( txtCampo02 );
		info.add( new JLabel( "Edad" ) );
		info.add( edad );		
		info.add( new JLabel( "telefono" ) );
		info.add( campoTel );
		info.add( new JLabel( "Domicilio" ) );
		info.add( txtCampo03 );

		 
		esquem = new FlowLayout();
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout( esquem );
		esquem.setAlignment( FlowLayout.RIGHT );
				
		modifica = new JButton("Modificar");
		campoCveBusca = new JTextField( nombre, 10 );
		save = new JButton("Guardar"  );
		buscar = new JButton("Buscar" );
		
		save.setEnabled(false);//para no duplicar registro
		save.addActionListener(miOyente3);
		buscar.addActionListener(miOyente4);
		modifica.addActionListener(miOyente4);

		panelBotones.add( new JLabel( "Clave:" ) );
		panelBotones.add( campoCveBusca);
		panelBotones.add( buscar);
		panelBotones.add( new JLabel( " ó " ));
		panelBotones.add(modifica);
		panelBotones.add( new JLabel( "                            " ));
		panelBotones.add(save );
		
//		JPanel borde = new JPanel();
		add(guardar, BorderLayout.NORTH);
		add(info, BorderLayout.CENTER);
		panelBotones.setBorder( BorderFactory.createTitledBorder( "" ) );
		add( panelBotones, BorderLayout.SOUTH );
//		Border border = BorderFactory.createLineBorder( Color.BLACK );
	}
//	class MiOyente implements ActionListener
//	{
//		
//		public void actionPerformed( ActionEvent e )
//		{
//			JTextField fuente = ( JTextField ) e.getSource();
//			String mensaje = "";
//			
//				mensaje = fuente.getText();
//
//			JOptionPane.showMessageDialog( null, mensaje );
//		}
//	}
	class MiOyente4 implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{    
			save.setEnabled(false);//para no duplicar registro
			
			
			Reset res = new Reset();
			Edita ed = new Edita();
			boolean act = false;

			
			espacios.setVisible(true);	poderMo.setVisible(false);
				if(campoCveBusca.getText().equals(""))
				{	
					ed.Editar(act);
					JOptionPane.showMessageDialog(null, "Campo clave/buscar, vacío");
				return;}
				
				res.Resete();
//			txtCampo01.setEditable(act);
				ed.Editar(act);

			int enable = 0, cont = 0, gi = 0, si = 0;
			
			String sum = "";
			Archivo arch = new Archivo();
			try {
				arch.Archivito("");
			} catch (IOException e1) {e1.printStackTrace();}
			
			for (int i = 0; i < arch.cadena2.length(); i++) 
			{
				if((i%(Cas*valor) == 0))
				{
					gi = i;// para recuperar el número adelante
					sum += arch.cadena2.charAt(i);
			
					if(arch.cadena2.charAt(i+1) != 32)
					{	
						sum+=arch.cadena2.charAt(i+1);// entra cuando son 2 digitos
						        		
					}
					if(arch.cadena2.charAt(i+2) != 32)
					{	
						sum+=arch.cadena2.charAt(i+2);
						
					}
					i = gi;
					enable = 1;
				}
				if(campoCveBusca.getText().equals(sum))
				{
//					acc = true;
					txtCampo01.setText(sum);
					JButton btn = (JButton) e.getSource();
					if(btn == modifica ) {
					
						act = true;
						ed.Editar(act);
						
						poderMo.setVisible(act);
						espacios.setVisible(!act);
						if(poderMo.isVisible()) {
//							System.out.println("entra");
							save.setEnabled(true);
						}
						return;
						}
					txtCampo01.setText(sum);
					 si = 1;
					enable = 1;
					if(enable == 1)
					{
						cont++;
						if(cont  == 75)
						{
							cont = 0;
							sum = "";
							eda = "";
							tel = "";
							domicilio ="";
							use = "";
							
//							System.out.println(	"archivo buscado  " +sum2); //despliega el archivo con la clave que coincide
							for (int j = gi; j < ((gi+valor)*Cas); j++) 
							{
								if(j<(gi+valor))
								{
									sum += arch.cadena2.charAt(j+(valor));
//	        						System.out.println(sum);//imprime cada letra que se agrega
	        						txtCampo02.setText(sum);
								}	
								else if(j>=(gi+(valor)) && j<(gi+(valor*2)))
								{
									domicilio += arch.cadena2.charAt(j+(valor));
									txtCampo03.setText(domicilio);
								}	
								else if(j>=(gi+(valor*2)) && j<(gi+(valor*3)))
								{
									tel += arch.cadena2.charAt(j+(valor));
									campoTel.setText(tel);
								}	
								else if(j>=(gi+(valor)*3) && j<(gi+(valor)*4))
								{
									eda += arch.cadena2.charAt(j+(valor));
									edad.setText(eda);
								}
								else if(j>=(gi+(valor)*4) && j<(gi+(valor)*5))
								{
									use += arch.cadena2.charAt(j+(valor));
									user.setText(use);
									activoDesactivo = user.getText().equalsIgnoreCase("usuario activo                     ");
									if(activoDesactivo) {
										user.setText("Usuario activo  ");
										user.setForeground(Color.GREEN);}
									else {
										user.setForeground(Color.RED);
									user.setText("Usuario inactivo  ");}
								}
							}
						}	
					}
	//        	
				}
				else {
					sum ="";
				}
			}
			if(si == 0)
				JOptionPane.showMessageDialog(null, "Clave no existe");
			esquema1.layoutContainer( MiPanela.this );
		
	}}
	class MiOyente3 implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			Reset res = new Reset();
			Edita ed = new Edita();
			
			String sum = "", sumpsum = "";
			Archivo arch = new Archivo();
			
			
			if(!txtCampo02.isEditable()) {
				save.setEnabled(false);
				JOptionPane.showMessageDialog(null, "Selecciona \"Archivo\",\"Actualizar catálogo\",\n\"Agrega\" y coloca un Nombre");

//				JOptionPane.showMessageDialog(null, "no puedes guardar");
				return;
			}
			boolean act = false;
			ed.Editar(act);
			try {
				arch.Archivito("");
				arch.ArchivoDatos("");
			} catch (IOException e1) {e1.printStackTrace();}
			
			String text1 = null, text, otext, otext1 = null;
				text1 = arch.cadena2;
				text = "";
				otext = "";
				otext1 = arch.ocadena2;
//			System.out.println("antes de asignarse "+otext1);
			char ctext1[] = new char[text1.length()];
			char ctext2[] = new char[otext1.length()];
			
			if(txtCampo02.getText().equals("")) {
				save.setEnabled(false);
				JOptionPane.showMessageDialog(null, "Campo nombre vacío\nSelecciona \"Archivo\",\"Actualizar catálogo\",\n\"Agrega\" y coloca un Nombre");
				return;
			}
//			System.out.println("cadena 2 "+arch.cadena2.length());
			for (int i = 0; i < arch.cadena2.length(); i++) 
			{
				if((i%(Cas*valor) == 0))
				{
					sum += arch.cadena2.charAt(i);
	
					if(arch.cadena2.charAt(i+1) != 32)
						sum+=arch.cadena2.charAt(i+1);
						        		
					if(arch.cadena2.charAt(i+2) != 32)
						sum+=arch.cadena2.charAt(i+2);
				}
				ctext1[i] = text1.charAt(i);
				if(txtCampo01.getText().equals(sum))
				{
					sumpsum = sum;//System.out.println("ugyakes "+sumpsum);
				}
				sum = "";
			}
			for (int j = 0; j < ctext2.length; j++) {
			
				ctext2[j] = otext1.charAt(j);
			}			
	
			if(poderMo.isVisible()) {
//				System.out.println("aqui otro?");
				save.setEnabled(false);
				act = true;
				ed.Editar(act);
				poderMo.setVisible(false);espacios.setVisible(true);
				
				
				otext = blanki(txtCampo01.getText());
				otext += blank(txtCampo02.getText());
				otext += blank(user.getText());

				text = blank(txtCampo01.getText());
				text += blank(txtCampo02.getText());
				text += blank(txtCampo03.getText());
				text += blank(campoTel.getText());
				text += blank(edad.getText());
				text += blank(user.getText());//--------------+}
				txtCampo01.setText(Integer.toString((c)));

				int indice = 0;
//				System.out.println("nvoi "+ sumpsum +"*"+valor+"*"+Cas);
				int nvoi = ((Integer.parseInt(sumpsum))*(valor*Cas));
				int nvoi2 = ((Integer.parseInt(sumpsum))*((valor*2)+6));
//				System.out.println(otext);
				for (int x = nvoi-(valor*Cas); x < (nvoi); x++)
				{	
					
					ctext1[x] = text.charAt(indice);//solo agrega la parte a modificar
					indice++;						
				}
				indice = 0;
				for (int x = nvoi2-((valor*2)+6); x < nvoi2; x++) {
//					System.out.println("si aqui? "+x);
					ctext2[x] = otext.charAt(indice);//solo agrega la parte a modificar
					indice++;
				}
//				c--;

				try {
				Archivo1.ArchivoDatos(ctext2);
					Archivo1.Archivo(ctext1);
				} catch (IOException e1) {e1.printStackTrace();}
				
				act = false;
				res.Resete();;
				ed.Editar(act);
				return;
				}
			Archivo archivo = new Archivo();
			
			poderMo.setVisible(false);espacios.setVisible(true);
			
			otext = blanki(txtCampo01.getText());
			otext += blank(txtCampo02.getText());
			otext += blank(user.getText());
//			Archivo1.Archivo(ctext1);

			text = blank(txtCampo01.getText());
			text += blank(txtCampo02.getText());
			text += blank(txtCampo03.getText());
			text += blank(campoTel.getText());
			text += blank(edad.getText());
			text += blank(user.getText());//--------------
			//Asigna el nuevo valor a la clave
					
			domicilio =  text;
			c++;

			 try {
				 archivo.ArchivoDatos(otext);
				 archivo.Archivito(domicilio);
			} catch (IOException e1) {e1.printStackTrace();}
			 act = false;
			 res.Resete();
				ed.Editar(act);
				save.setEnabled(false);
			text = "";
			
		}
		public String blank(String palabra) 
		{
			String blank = " ";
			for (int i = palabra.length(); i < valor; i++)
				palabra += blank;
			return palabra ;
		}
		public String blanki(String palabra) 
		{
			String blank = " ";
			for (int i = palabra.length(); i < 6; i++)
				palabra += blank;
			return palabra ;
		}
	}class Reset {
		public void Resete() 
		{
			txtCampo01.setText(Integer.toString((c)));
			txtCampo02.setText( "" );
			txtCampo03.setText( "" );
			edad.setText( "" );
			campoTel.setText( "" );
		}
	}
	class Edita 
	{
		public void Editar(boolean act) 
		{
			txtCampo02.setEditable( act );
			txtCampo03.setEditable( act );
			edad.setEditable( act );
			campoTel.setEditable( act );
		}
	}
	class MiOyente2 implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			save.setEnabled(true);//para no duplicar registro
			poderMo.setVisible(false);
			espacios.setVisible(true);
			Reset res = new Reset();
			Edita ed = new Edita();

			boolean act = true;
			res.Resete();
			ed.Editar(act);

		}
	}
	class Archivo
	{
		public void ArchivoDatos(String domicili) throws IOException 
		{
			
			File f = new File("C:\\Users\\Gabriel\\Desktop\\datosConcretos.txt");
			
			//Escritura
			if(!f.exists())
				f.createNewFile();
			
			try/*(PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter(f, true))))*/{
				
				FileWriter w = new FileWriter(f, true);		
				BufferedWriter bw = new BufferedWriter(w);		
				PrintWriter wr = new PrintWriter(bw, true);  	
				wr.write(domicili);//escribimos en el archivo
				wr.close();			
				
			}catch(IOException e){ e.getMessage();};
			muestraContenidoDato(f);
		}
		public void Archivito(String domicili) throws IOException 
		{

			File f = new File("C:\\Users\\Gabriel\\Desktop\\datos.txt");
			
			//Escritura
			if(!f.exists())
				f.createNewFile();
			
			try/*(PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter(f, true))))*/{
				
				FileWriter w = new FileWriter(f, true);		
				BufferedWriter bw = new BufferedWriter(w);		
				PrintWriter wr = new PrintWriter(bw, true);  	
				wr.write(domicili);//escribimos en el archivo
				wr.close();			
				
			}catch(IOException e){ e.getMessage();};
			muestraContenido(f);
		}
		String cadena, cadena2 = "";
		public void muestraContenido(File archivo) 
		{
//			MiPanela mp = new MiPanela();
			try{
				
				FileReader f = new FileReader(archivo);
				BufferedReader b = new BufferedReader(f);
				while((cadena = b.readLine())!=null) {
					cadena2 += cadena;
				}
				b.close();  
			}catch(Throwable e){e.printStackTrace();}
		}
		String ocadena, ocadena2 = "";
		public void muestraContenidoDato(File archivo) 
		{
//			MiPanela mp = new MiPanela();
			try{
				
				FileReader f = new FileReader(archivo);
				BufferedReader b = new BufferedReader(f);
				while((ocadena = b.readLine())!=null) {
					ocadena2 += ocadena;
				}
				b.close();  
			}catch(Throwable e){e.printStackTrace();}
		}
	}
}