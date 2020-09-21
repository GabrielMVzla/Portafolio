package analizador;

import java.util.ArrayList;

public class Mainn {

	public static void main(String[] args) 
	{
	     Lexico analizador = new Lexico("file1.txt");
//	     for(int i = 0; i < analizador.tokenRC.size(); i++)
//	     { 
//	    	 System.out.println("token =  " + analizador.tokenRC.get(i).getToken() + "   |  tipo = " + analizador.tokenRC.get(i).getTipo());
//	     }	
	     if (!analizador.flag)
	     {
	    	for(int i = 0; i < analizador.VoF.size(); i++)
	    	{ 
				System.out.println( analizador.VoF.get(i) );
			}	    	 
	    	System.exit(0);
	     }
	 	if( analizador.VoF.get(0).equals("Sin errores lexicos"))
		{
			Sintactico sint = new Sintactico(analizador.tokenRC);
			Tabla tabla_Sintactica = new Tabla(analizador.tokenRC);
			Intermedio_datos datos_Intermedio = new Intermedio_datos(analizador.tokenRC, tabla_Sintactica.valoresTab);
			Intermedio cod_Intermedio = new Intermedio(datos_Intermedio.tokens_intermedionoifinal);

//			Intermedio cod_Intermedio = new Intermedio(analizador.tokenRC);
		}
	 	
	 	
	}

}
