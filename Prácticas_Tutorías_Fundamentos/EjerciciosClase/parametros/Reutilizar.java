package parametros;

import java.util.Scanner;

public class Reutilizar 
{
	//Ejemplo de reutilización con uso de métodos 
	//optimizando el código.
	static Scanner lee = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		int n = N();
		String arreglo[] = Escribe(n);
		Leer(arreglo);
		
		System.out.println("Siguiente...");
		
//		n = N();				podemos llamar de nuevo sin declarar de nuevo las variables
//		arreglo = Escribe(n);	o bien podemos hacer lo siguiente
//		Leer(arreglo);
		Leer(Escribe(N())); //	lo mismo de arriba con solo 1 línea de código
	}
	static public int N()
	{
		System.out.print("Ingresa le número de lecturas para el for: ");
		int n = lee.nextInt();
		
		return n;
	}
	public static String[] Escribe(int n)
	{
		String arreglo_Nombres[] = new String[n];
		arreglo_Nombres[0] = lee.nextLine();

		for (int i = 0; i < arreglo_Nombres.length; i++) 
		{
			System.out.print((i + 1) + ".- Ingresa el nombre: " );
			arreglo_Nombres[i] = lee.nextLine();
		}
		return arreglo_Nombres;
	}
	public static void Leer(String[] nombres) 
	{
		for (int i = 0; i < nombres.length; i++)
			System.out.println((i + 1) + ".- nombre: " + nombres[i]);
	}
}
