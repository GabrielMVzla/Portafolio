package iterativas_for;

import java.util.Scanner;

public class Menor 
{
	//Calcular la calificación menor de 4 alumnos
	public static void main(String[] args) 
	{
		Scanner lee = new Scanner(System.in);
		int menor = 999999, calificacion;
		
		for (int i = 0; i < 4; i++) 
		{
			System.out.print("Ingresa la calificación para el alumno " + (i + 1) + ": ");
			calificacion = lee.nextInt();
			
			if(calificacion < menor)
			{
				menor = calificacion;
			}
		}
		System.out.println("La calificacion menor es " + menor);
	}	
}
