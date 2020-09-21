package arbol_Sintactico;

import java.util.ArrayList;

import analizador.Token;

public class Asigna extends Sx
{
	public String id;
	public Ex e;
	public 	ArrayList<Token> tokens;

	public Asigna(String id1, Ex e, ArrayList<Token> tokens, int valor)
	{
		this.e = e;	
		id = id1;
		this.tokens = tokens;
		int inc = analizador.Sintactico.contadorSaveLineDo;
		if(valor != 0) {
			for (int i = 0; i < tokens.size(); i++) {
				if(i != 0 && i != tokens.size()-1)
				if(tokens.get(analizador.Sintactico.contando2).getToken().equals(id1)
						&& tokens.get(analizador.Sintactico.contando2-1).getToken().equals("do") 
						&& tokens.get(analizador.Sintactico.contando2+1).getToken().equals("=")) 
				{
					analizador.Sintactico.saveLineDo.add(inc);
				}
			}
			inc = analizador.Sintactico.contadorSaveLineDo+=2;

		}
	}
	
}
