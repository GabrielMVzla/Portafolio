package iterativas_for;

import java.util.Scanner;

public class SegMay 
{
	public static void main(String[] args) 
	{
		int mayor = 0, segMay = 0;
		int calificacion = 0, numAlum = 0, numAlum2 = 0;
		Scanner lee = new Scanner(System.in);
		
		for (int i = 0; i < 5; i++) 
		{
		
			System.out.print("Ingresa la calificaci�n del alumno n�mero " + (i+1) + ": ");
			calificacion = lee.nextInt();
			//25, 95, 70, 88, 79 aqu� el mayor de los mayores est� antes que el segundo mayor
			//88, 70, 95, 25, 79 aqu� el mayor de los mayores est� despu�s del segundo mayor
			//importa porque dependiendo de c�mo est� el algoritmo toma diferentes caminos
			if(calificacion > mayor)
			{
				numAlum2 = numAlum;
				segMay = mayor;
				mayor = calificacion;
				numAlum = (i + 1);
			}
			
			if(calificacion > segMay && calificacion < mayor) 
			{
				segMay = calificacion;
				numAlum2 = (i + 1);
			}
		}
		
		System.out.println("\nEl alumno " + numAlum + " obtuvo la mejor calificaci�n "
				+ "con = " + mayor);
		System.out.println("\nEl alumno " + numAlum2 + " obtuvo la segunda mejor calificaci�n "
				+ "con = " + segMay);	}
}
