package iterativas_for;

import java.util.Scanner;

public class Mayor
{
	//Programa para calcular la calificaci�n mayor de 5 alumnos
	public static void main(String[] args) 
	{
		int mayor = 0;
		int calificacion = 0, numAlum = 0;
		Scanner lee = new Scanner(System.in);
		
		for (int i = 0; i < 5; i++) 
		{
			System.out.print("Ingresa la calificaci�n del alumno n�mero " + (i+1) + ": ");
			calificacion = lee.nextInt();
			
			if(calificacion > mayor)
			{
				mayor = calificacion;
				numAlum = (i + 1);
			}
		}
		System.out.println("El alumno " + numAlum + " obtuvo la mejor calificaci�n "
				+ "con = " + mayor);
	}
}
