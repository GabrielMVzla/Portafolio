package arreglos;

import java.util.Scanner;

public class Arreglo02 
{
//Explicaci�n de Arreglos y Ejemplo de Programa que lea el tama�o de tu 
//arreglo de 2 a 5 validando para que no pase de estos valores
	public static void main(String[] args) 
	{
		Scanner lee = new Scanner(System.in);
		int arreglo[], n = 0;
		
		do{	
			System.out.print("Ingresa el tama�o de tu arreglo: ");
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
		
		//copiamos de nuevo, ahora los valores de arreglo pasan a ser de otroArreglo tambi�n
		
		otroArreglo = arreglo;
		
		//Desplegamos valores de otroArreglo
		for (int i = 0; i < arreglo.length; i++) 
			System.out.println("arreglo[" + (i) + "]: " + arreglo[i]);

		//Desplegamos valores de otroArreglo
		for (int i = 0; i < otroArreglo.length; i++)
			System.out.println("otroArreglo[" + (i) + "]: " + otroArreglo[i]);
		
		
		
		
		//Aqu� le asignamos el tama�o al arreglo b y m�s adelante copiamos el arreglo a
		//en b y se sobreescribe su tama�o
		{
			int a[] = new int[5];
			int b[] = new int[2];
			
			for (int i = 0; i < b.length; i++) 
				System.out.println("b[" + i + "]: " + b[i]);
			
			//sobreescribimos el tama�o de b correspondiente al tama�o de a
			b = a;
			
			for (int i = 0; i < b.length; i++) 
				System.out.println("b[" + i + "]: " + b[i]);
		}
		
		/*{
			//deben asignarle un valor a los arreglos si no, lanzar� error en consola,
			//aqu� no nos marca en error pero una vez ejecutado lo har�
			int a[] = null;
			int b[] = null;
			
			for (int i = 0; i < b.length; i++) 
				System.out.println("b[" + i + "]: " + b[i]);
			
			//sobreescribimos el tama�o de b correspondiente al tama�o de a
			b = a;
			
			for (int i = 0; i < b.length; i++) 
				System.out.println("b[" + i + "]: " + b[i]);
		}*/
		
		
		
	}
}
