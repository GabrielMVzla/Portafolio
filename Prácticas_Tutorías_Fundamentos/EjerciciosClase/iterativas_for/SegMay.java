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
		
			System.out.print("Ingresa la calificación del alumno número " + (i+1) + ": ");
			calificacion = lee.nextInt();
			//25, 95, 70, 88, 79 aquí el mayor de los mayores está antes que el segundo mayor
			//88, 70, 95, 25, 79 aquí el mayor de los mayores está después del segundo mayor
			//importa porque dependiendo de cómo esté el algoritmo toma diferentes caminos
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
		
		System.out.println("\nEl alumno " + numAlum + " obtuvo la mejor calificación "
				+ "con = " + mayor);
		System.out.println("\nEl alumno " + numAlum2 + " obtuvo la segunda mejor calificación "
				+ "con = " + segMay);	}
}
