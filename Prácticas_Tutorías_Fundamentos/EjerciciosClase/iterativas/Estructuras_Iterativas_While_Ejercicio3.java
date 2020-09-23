package iterativas;

import java.util.Scanner;

public class Estructuras_Iterativas_While_Ejercicio3 
{
	//Realizar un programa que sume los primeros 50 números naturales
	public static void main(String[] args) 
	{
		Scanner lee = new Scanner(System.in);
		int suma = 0, indice = 0;
		
		while(indice < 50)
		{
			indice++;
			suma = suma + indice;
			System.out.println("suma = " + suma);
		}
	}
}