package IA;
import java.util.Scanner;
//"Made by Daniel Ziegler for CSC 480 versión DotsAndBoxes"
//Actualizado por Gabriel Montes versión a Timbiriche Tablero 5x5
public class Timbiriche 
{
	static Scanner lee = new Scanner(System.in);
	public static void main(String[] args)
	{
		Tablero raiz = new Tablero(); //5,5 es el tamaño del tablero por defecto, para manipular hay que modificar el constructor de tablero
		int depth = 3, movimient[] = new int [2], resultado[] = new int[2], resultado2[] = new int[2];
		String movimiento[] = new String [2];
		System.out.println("Bienvenido a timbiriche\nInteligencia Artificial 10-11\nIngresa las coordenadas (1ro. renglón y después columna).");
		raiz.muestraTablero();
		
		while(!raiz.full())
		{
			do {
				System.out.print("Turno de Jugador\nrenglón: ");
				movimiento[0] = lee.nextLine();
				System.out.print("columna: ");
				movimiento[1] = lee.nextLine();
				resultado[0] = 1;

				try {
					movimient[0] = Integer.parseInt(movimiento[0]);
					movimient[1] = Integer.parseInt(movimiento[1]);
				} catch (Exception e)
				{
					resultado[0] = 2;
					System.out.println("¡¡¡Ingresa un valor numérico Natural válido!!!.\n");
				}
				resultado = raiz.mueve(movimient[1], movimient[0]);

				if(resultado[0] == 3) 	// 3 espacio invalido
					System.out.println("No puedes dibujar una linea aquí.\n");
				if(resultado[0] == 4) 	// 4 fuera de límites
					System.out.println("Esta coordenada esta fuera del límite del tablero.\n");
				if(resultado[0] == 5) 	//5 espacio lleno
					System.out.println("Esta coordenada ya fué colocada.\n");
			}
			while (resultado[0] != 1);  //1 indica éxito

			raiz.muestraTablero();
			
			if((!raiz.full() && resultado[1] != 1) ) // != 1 indica que habrá turno extra de parte del jugador
			{
				do {
					movimient = MiniMax.mover(raiz, depth);
					System.out.println("\nMovimiento de Computadora: (" + movimient[1] + ", " + movimient[0] + ")");
					resultado2 = raiz.mueve(movimient[0], movimient[1]);
					raiz.muestraTablero();
				}while(!raiz.full() && resultado2[1] == 1);
			}
		}
		System.out.println("-------------------------\n|    resultado Final    |\n-------------------------"
				+ "\nPuntaje de Jugador: "+raiz.getMinPuntaje()
				+"\nPuntaje de Computadora: "+raiz.getMaxPuntaje());
		
		if(raiz.getMaxPuntaje() > raiz.getMinPuntaje())
			System.out.println("Ganador: IA");
		else if(raiz.getMinPuntaje() > raiz.getMaxPuntaje())
			System.out.println("Ganador: Jugador");
		else
			System.out.println("Empate.");
	}
}