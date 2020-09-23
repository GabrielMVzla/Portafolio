package parametros;

import java.util.Scanner;

public class Llamada_Metodos 
{

	static Scanner lee = new Scanner(System.in);
	static float n = 5;
	
	public static void main(String[] args) 
	{
//		int n = Leer();
//		Ciclo_For(n);
		Ciclo_For(Leer());
	}
	
	private static int Leer() 
	{
		int n = 0;
		System.out.print("Ingresa el número de n para iteraciones en for: ");
		n = lee.nextInt();
		
		return n;
	}
	
	public static void Ciclo_For(int n)
	{
		
		for (int i = 0; i < n; i++) {
			System.out.println((i+1));
		}
		
		
	}
}
