package practicas;

import java.util.ArrayList;

public class Prioridad_Simbolos 
{

	public static void main(String[] args) 
	{
		
		String operacion = " 5 * 2 + (3 - 3)";
		String cola[] = new String [operacion.length()];
		int cantidadCorrecta = 0 ;
		
		for (int i = 0; i < operacion.length(); i++)
		{
			if(operacion.charAt(i) != ' ')
			{
				cola[i] = String.valueOf(operacion.charAt(i));
				cantidadCorrecta++;
			}
		}

		String colaFinal[] = new String [cantidadCorrecta];
		int otroCont = 0 ;
		
		for (int i = 0; i < cola.length; i++)
		{
			if(cola[i] != null) 
			{
				colaFinal[otroCont] = cola[i];
				otroCont++;
			}
		}
		for (int i = 0; i < colaFinal.length; i++)
		{
			if(colaFinal[i].equals("("))
			System.out.println( "colaFinal[" + ( i + 1 ) +"] = " + colaFinal[i]);
		}
		
	}
}
