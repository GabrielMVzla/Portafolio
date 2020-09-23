package parametros;

import java.util.Scanner;

public class Metodos
{
	
	//explicar public static void		--- void / no void
	//		   private int
	public static void main(String[] args) 
	{
	
		Metodo_Promedio();
		
	}

	public static void Metodo_Promedio()
	{
		int suma = 0, calificacion = 0, promedio = 0;
		Scanner lee = new Scanner(System.in);
		
		for (int i = 0; i < 5; i++) 
		{
		
			System.out.print("Ingresa la calificación número " + (i+1) + ": ");
			calificacion = lee.nextInt();
			
			suma = suma + calificacion;
		
		}
		promedio = suma/5;
		
		System.out.println("Suma = " + suma);
		System.out.println("Promedio = " + promedio);
	}
	
	
	
}
