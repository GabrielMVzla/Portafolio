package analizador;

public class Mainn {

	public static void main(String[] args) 
	{
	     Lexico analizador = new Lexico("file1.txt");
	     if (!analizador.flag)
	     {
	    	for(int i = 0; i < analizador.VoF.size(); i++)
	    	{ 
				System.out.println( analizador.VoF.get(i) );
			}	    	 
	    	 System.exit(0);
	     }
	        
		Sintactico sintactico = new Sintactico(analizador.tipos, analizador.tokens, analizador.renglones, analizador.columnas);
	}

}
