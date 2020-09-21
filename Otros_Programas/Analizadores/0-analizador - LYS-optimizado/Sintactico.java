package analizador;

import java.util.ArrayList;

public class Sintactico {
	
	final int els = 0, ifs = 1, then = 2, print = 3, begin = 4,
			  end = 5 , eq = 6, coma = 7, num = 8;
	
	String tokenRealString;
	int tokenReal = -1, indice = 0, renglon = 0, columna = 0;
	public ArrayList<Integer> tipos;
	public ArrayList<Integer> renglones;
	public ArrayList<Integer> columnas;
	public ArrayList<String> tokens;
	
	public Sintactico(ArrayList<Integer> tipos, ArrayList<String> tokens, ArrayList<Integer> renglones,  ArrayList<Integer> columnas) 
	{
		this.tipos = tipos;
		this.renglones = renglones;
		this.columnas = columnas;
		this.tokens = tokens;
		
		tokenReal = tipos.get(indice);
		tokenRealString = tokens.get(indice);
		renglon = renglones.get(indice);
		columna = columnas.get(indice);
		
		Programa();
		
		System.out.println("fin del programa, pasó las pruebas!!");

	}
	
	public void eat(int t)
	{
	    int tokenEsperado = t;
//	    System.out.println("tipos.size = " + tipos.size() + " //// indice = " + indice);
        if(tokenReal == tokenEsperado)
        {
        	if(tipos.size()-1 == indice)
        	{
        		indice++; //para saber que no hay nada después del final como indica la sintaxis
        		return; //size abarca la cantidad que hay en la lista, el -1 es para que abarque
        				//todo contando desde el indice 0
        	}
        }
        else{
            error("renglon : " + renglon + ", columna: " + columna + ", token \""+ tokenRealString + "\" tipo esperado: " + tokenEsperado +", llegó tipo: " + tokenReal);
        }
        indice++;
        tokenReal = tipos.get(indice);
        tokenRealString = tokens.get(indice);
        renglon = renglones.get(indice);
        columna = columnas.get(indice);
        
	}
	
	private void error(String string) 
	{
		System.out.println("Error sintáctico\n"
				 		 + string);
		System.exit(0);
	}
	private void error() 
	{
		System.out.println("Error sintáctico\n"
						 + "Token \"" + tokenRealString + "\" en renglón \"" + renglon + "\", columna \"" + columna + "\" no se esperaba");
		System.exit(0);
	}

	public void advance(){
		indice++;
		tokenReal = tipos.get(indice);
		tokenRealString = tokens.get(indice);
	}
	
	public void Programa() {
		
		
		if(tokenReal == ifs || tokenReal == begin || tokenReal == print)
			S();
		else error();
//		System.out.println("tamaño indice = " + indice + ", tamaño lista = " + tokens.size());
		if(tokens.size() > indice)
		{
			System.out.println("Error sintáctico, error en los límites del código almenos un token extra \"" + tokens.get(indice) + "\"");
			System.exit(0);
		}
	
	}
	public void S() {
		
		switch (tokenReal)
		{
		case ifs:
			eat(ifs);
			E(); eat(then); S(); eat(els); S();
			break;
		case print:
			eat(print); E(); eat(coma);
			break;
		case begin:
			eat(begin); S(); L();
			break;
		default: error(); break;
		}
	}
	
	public void E() 
	{
		eat(num); eat(eq); eat(num);
	}
	
	public void L() 
	{
		eat(end);
	}

}
