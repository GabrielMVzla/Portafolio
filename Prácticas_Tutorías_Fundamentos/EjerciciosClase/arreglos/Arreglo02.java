package arreglos;

import java.util.Scanner;

public class Arreglo02 
{
//Explicación de Arreglos y Ejemplo de Programa que lea el tamaño de tu 
//arreglo de 2 a 5 validando para que no pase de estos valores
	public static void main(String[] args) 
	{
		Scanner lee = new Scanner(System.in);
		int arreglo[], n = 0;
		
		do{	
			System.out.print("Ingresa el tamaño de tu arreglo: ");
			n = lee.nextInt();
		}while(n < 2 || n > 5);
		
		int otroArreglo[] = new int[n];
		
		arreglo = otroArreglo; 
		
		for (int i = 0; i < arreglo.length; i++)
		{
			System.out.println("arreglo[" + (i) + "]: " + arreglo[i]); //Ejemplo de Ceros
		}
		
		//le asignamos valores a arreglo desde el teclado
		for (int i = 0; i < arreglo.length; i++) 
		{
			System.out.print("Valor " + (i + 1) + ": ");
			arreglo[i] = lee.nextInt();
		}
		
		//copiamos de nuevo, ahora los valores de arreglo pasan a ser de otroArreglo también
		
		otroArreglo = arreglo;
		
		//Desplegamos valores de otroArreglo
		for (int i = 0; i < arreglo.length; i++) 
			System.out.println("arreglo[" + (i) + "]: " + arreglo[i]);

		//Desplegamos valores de otroArreglo
		for (int i = 0; i < otroArreglo.length; i++)
			System.out.println("otroArreglo[" + (i) + "]: " + otroArreglo[i]);
		
		
		
		
		//Aquí le asignamos el tamaño al arreglo b y más adelante copiamos el arreglo a
		//en b y se sobreescribe su tamaño
		{
			int a[] = new int[5];
			int b[] = new int[2];
			
			for (int i = 0; i < b.length; i++) 
				System.out.println("b[" + i + "]: " + b[i]);
			
			//sobreescribimos el tamaño de b correspondiente al tamaño de a
			b = a;
			
			for (int i = 0; i < b.length; i++) 
				System.out.println("b[" + i + "]: " + b[i]);
		}
		
		/*{
			//deben asignarle un valor a los arreglos si no, lanzará error en consola,
			//aquí no nos marca en error pero una vez ejecutado lo hará
			int a[] = null;
			int b[] = null;
			
			for (int i = 0; i < b.length; i++) 
				System.out.println("b[" + i + "]: " + b[i]);
			
			//sobreescribimos el tamaño de b correspondiente al tamaño de a
			b = a;
			
			for (int i = 0; i < b.length; i++) 
				System.out.println("b[" + i + "]: " + b[i]);
		}*/
		
		
		
	}
}
