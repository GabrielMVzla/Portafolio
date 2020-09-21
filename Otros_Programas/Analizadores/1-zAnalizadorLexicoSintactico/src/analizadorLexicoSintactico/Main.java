package ehhCabeza;

import java.util.ArrayList;

public class Main 
{
	public static void main(String[] args) 
	{
		
	        Analiza analizador = new Analiza("file1.txt");
	        ArrayList<String> a1 = analizador.resultado;
	        ArrayList<Token> tk = analizador.tokenRC;
	        Tabla t;
	        Sintactico s;
	        
	        
//			for(int i = 0; i < tk.size(); i++)
//			{ 
//				System.out.println( tk.get(i).getToken() + "\t\t" + tk.get(i).getTipo() );
//			}
			
			
			for(int i = 0; i < a1.size(); i++) { 
				System.out.println( a1.get(i) );
			}
	        
			if( a1.get(0).equals("No hay errores lexicos"))
			{
				s = new Sintactico(analizador.tokenRC);
				t = new Tabla(analizador.tokenRC);
				
			}
			
	}
}
