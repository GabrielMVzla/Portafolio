package iterativas_for;
import java.util.*;
public class Promedio 
{
	//Programa para calcular Promedio de calificaciones 
	//por medio de 5 números ingresados por teclado.
	public static void main(String[] args) 
	{
	
		int suma = 0, numeros = 0, promedio = 0;
		Scanner lee = new Scanner(System.in);
		
		for (int i = 0; i < 5; i++) 
		{
		
			System.out.print("Ingresa la calificación número " + (i+1) + ": ");
			numeros = lee.nextInt();
			
			suma = suma + numeros;
		
		}
		promedio = suma/5;
		
		System.out.println("Suma = " + suma);
		System.out.println("Promedio = " + promedio);
	}
	
}
