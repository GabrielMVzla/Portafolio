package IA;
import java.util.LinkedList;

public class Tablero 
{
	public Tablero padre;
	public LinkedList<Tablero> hijos;
	private int tablero[][], turno, renglonCambio, columnaCambio,
				puntajeIA, puntajeJugador;
	public int val, depth;
	
	public Tablero() //Tablero Nodo principal
	{
		//el valor 5 es el tamaño del tablero
		tablero = new int[((5 * 2) + 1)][((5 * 2) + 1)];
		
		turno = 1;		
		renglonCambio = 0;
		columnaCambio = 0;		
		puntajeIA = 0;
		puntajeIA = 0;			
		val = 0;
		depth = 0;				
		padre = null;
		hijos = new LinkedList<Tablero>();
	}
	
	public Tablero(Tablero tabNodo)////Tableros Nodos posibles
	{
		tablero = new int[tabNodo.tablero.length][tabNodo.tablero[0].length];
		for(int i = 0; i < tablero.length; i++)
		{
			for(int j = 0; j < tablero[i].length; j++)
				tablero[i][j] = tabNodo.tablero[i][j];
		}
		turno = tabNodo.turno;
		renglonCambio = tabNodo.renglonCambio;
		columnaCambio = tabNodo.columnaCambio;
		puntajeIA = tabNodo.puntajeIA;
		puntajeJugador = tabNodo.puntajeJugador;
		val = 0;
		depth = 0;
		padre = null;
		hijos = new LinkedList<Tablero>();
	}
	public int getMaxPuntaje() 		{ return puntajeIA; 		}
	public int getMinPuntaje() 		{ return puntajeJugador; 	}
	public int getCambiarRenglon() 	{ return renglonCambio; 	} //	1
	public int getCambiarColumna() 	{ return columnaCambio; 	} //	2
	public int getTurno() 			{ return turno; 			}
	
	public void muestraTablero()
	{
		System.out.print("\n   ");
		for(int col = 0; col < tablero[0].length; col++)
			System.out.print(" " + col);
		
		System.out.println("\n");
		for(int ren = 0; ren < tablero.length; ren++)
		{
			System.out.print(""+ ren +  blancos(String.valueOf(ren).length())+ " ");
			if(ren % 2 == 0)
			{
				for(int col = 0; col < tablero[ren].length; col++)
				{
					if(col % 2 == 0)
						System.out.print("•");
					else if(tablero[ren][col] > 0)
						System.out.print("---");
					else
						System.out.print("   ");
				}
			}
			else
			{
				for(int col = 0; col < tablero[ren].length; col++)
				{
					if(col % 2 != 0)
						System.out.print("   ");
					else if(tablero[ren][col] > 0)
						System.out.print("|");
					else
						System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
		System.out.println("-------------------------\n|       Marcador        |\n-------------------------");
		System.out.println("\tJugador: " + puntajeJugador+"\n\tIA: " + puntajeIA + "\n");
	}
	
	public int[] mueve(int ren, int col)
	{
		int[] devuelta = new int[2];
		devuelta[0] = 1;	//5 en indice 0 con valor de 1 es igual a éxito
		devuelta[1] = 0;//si no se modifica en ningún lugar adelante no habrá turno extra para este jugador
		
		if(ren < 0 || ren > tablero.length || col < 0 || col > tablero[0].length)
		{
			devuelta[0] = 4;
			return devuelta; 	// 4 fuera de límites
		}
		
		// ^ xor | si ambos son true o ambos false el resultado será false
		//esto para evitar lineas sin sentido
		if(!(ren % 2 == 0 ^ col % 2 == 0))
		{
			devuelta[0] = 3;
			return devuelta; 	// 3 linea invalida
		}	
		
		if(tablero[ren][col] > 0)
		{
			devuelta[0] = 5;
			return devuelta; 	//5 espacio lleno
		}
		
		tablero[ren][col] = 1; 	//1 = ocupada posición de tablero | 0 = no ocupada
		renglonCambio = ren;
		columnaCambio = col;
		
		//Turno Extra para true
		if(actualizaPuntaje(ren, col))
			devuelta[1] = 1; //1 en indice 1 indica true y dará un turno extra de trazar cuando alguien hace un cuadrado
		else
			if(turno == 2)	//máquina
				turno = 1;	//humano
			else
				turno = 2;	//máquina
		
		return devuelta; 
	}
	public void agregaHijos()
	{
		for(int ren = 0; ren < tablero.length; ren++)
		{
			if(ren % 2 == 0)
			{
				for(int col = 1; col < tablero[ren].length; col += 2)
					if(tablero[ren][col] < 1)
					{
						Tablero hijo = new Tablero(this);
						hijo.depth = depth + 1;
						hijo.padre = this;
						//si no hace cuadrado el siguiente hijo será simulando movimiento del jugador
						int[] resultado = hijo.mueve(ren, col); 
						if(resultado[0] != 1)//1 indica éxito y si no es 1, no se añade hijo
							return;
						hijos.add(hijo);
					}
			}
			else
			{
				for(int col = 0; col < tablero[ren].length; col += 2)
					if(tablero[ren][col] < 1)
					{
						Tablero hijo = new Tablero(this);
						hijo.depth = depth + 1;
						hijo.padre = this;
						//si no hace cuadrado el siguiente hijo será simulando movimiento del jugador
						int[] resultado = hijo.mueve(ren, col); 
						if(resultado[0] != 1)//1 indica éxito y si no es 1, no se añade hijo
							return;
						hijos.add(hijo);
					}
			}
		}
	}

	public void heuristica()
	{
		val = puntajeIA - puntajeJugador;
	}
	
	private boolean actualizaPuntaje(int ren, int col)
	{
		boolean turnoExtra = false;
		if(ren % 2 == 0)
		{
			int arriba = ren - 1;
			int abajo = ren + 1;
			if(isCuadrado(arriba, col))
			{
				if(turno == 2)//máquina
					puntajeIA += 1;
				else
					puntajeJugador += 1;
				
				turnoExtra = true;
			}
			if(isCuadrado(abajo, col))
			{
				if(turno == 2)//máquina
					puntajeIA += 1;
				else
					puntajeJugador += 1;
			
				turnoExtra = true;
			}
		}
		else
		{					   //ren = 1
			int izq = col - 1; //2 - 1 = 1
			int der = col + 1; //2 + 1 = 3
			if(isCuadrado(ren, izq))// (1,1)
			{
				if(turno == 2)//máquina
					puntajeIA += 1;
				else
					puntajeJugador += 1;
				
				turnoExtra = true;
			}
			if(isCuadrado(ren, der))
			{
				if(turno == 2)//máquina
					puntajeIA += 1;
				else
					puntajeJugador += 1;
			
				turnoExtra = true;
			}
		}
		return turnoExtra;
	}
	private boolean isCuadrado(int ren, int col)// (1, 1)
	{
		if(ren > 0 && ren < tablero.length && col > 0 && col < tablero[ren].length) //T T T T
			return tablero[ren - 1][col] > 0 && tablero[ren + 1][col] > 0 &&		//F F T T --> False
				   tablero[ren][col - 1] > 0 && tablero[ren][col + 1] > 0;

		return false;
	}

	public boolean full()
	{
		for(int ren = 0; ren < tablero.length; ren++)
		{
			if(ren % 2 == 0)
			{
				for(int col = 1; col < tablero[ren].length; col += 2)
					if(tablero[ren][col] == 0)
						return false;
			}
			else
			{
				for(int col = 0; col < tablero[ren].length; col += 2)
					if(tablero[ren][col] == 0)
						return false;
			}
		}
		return true;
	}
	
	private String blancos(int longitud)
	{
		String blancos = "";
		for (int i = longitud; i < 3; i++) 
			blancos += " ";
		
		return blancos;
	}
}
