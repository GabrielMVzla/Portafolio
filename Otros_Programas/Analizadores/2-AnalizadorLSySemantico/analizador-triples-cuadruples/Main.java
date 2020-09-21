package analizador;

import java.util.ArrayList;

public class Main 
{
	public static void main(String[] args) 
	{
	        Lexico analizador = new Lexico("file1.txt");
	        ArrayList<String> a1 = analizador.VoF;
	        Sintactico s = null;
	        SemanticoArbol sem = null;
	        
//			for(int i = 0; i < a1.size(); i++) 
//				System.out.println( a1.get(i) );
//			
			if( a1.get(0).equals("Sin errores lexicos"))
			{
				s = new Sintactico(analizador.tokenRC );
			
			}
			else
				System.exit(0);
			
			
			sem = new SemanticoArbol(s.p, analizador.tokenRC);

	}
}
