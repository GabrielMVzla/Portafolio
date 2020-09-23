package arreglosBidi;

import java.util.Scanner;

public class Calificaciones_Alumnos_Bidimensional 
{
	public static void main(String[] args) 
	{
		Scanner lee = new Scanner(System.in);
		int alumnos = 0, calif = 0, suma = 0;
		
		System.out.print("Ingresa la cantidad de alumnos: ");
		alumnos = lee.nextInt();
		System.out.print("Ingresa la cantidad de calificaciones por alumno: ");
		calif = lee.nextInt();
		
		int[][] alumCalif = new int[alumnos][calif];
		int promedio[] = new int[alumnos];
		
		System.out.println("tamaño de alumCalif = " + alumCalif.length);
		
		for (int i = 0; i < alumCalif.length; i++) 
		{
			suma = 0;
			for (int j = 0; j < alumCalif.length; j++) 
			{
				System.out.print("Ingresa la calificación #" + (j + 1) + " del alumno "+ (i + 1) + ": ");
				alumCalif[i][j] = lee.nextInt();
				suma += alumCalif[i][j];
			}
			promedio[i] = suma / calif;
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < promedio.length; i++)
			System.out.println("Promedio del alumno #" + (i + 1) +": " + promedio[i]);
	}
}
