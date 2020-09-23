package arreglos;

import java.util.Scanner;

public class Arreglo
{
	static Scanner lee = new Scanner(System.in);
	//Introducción a los Arreglos
	public static void main(String[] args) 
	{
		//declaracion de arreglos

		//de esta manera todos serán arreglos
		int[] var1, var2;
		//de esta manera solo variable1 es arreglo
		int variable1[], variable2;

		//los arreglos son espacios para guardar valores
		//de esta manera definimos el tamaño del arreglo que queremos, es decir
		// 		la cantidad de valores que ingresaremos en un arreglo
		int variable[] = new int [5];
		
		//Así como hay arreglos de tipo entero donde almacena puros valores tipos enteros
		//hay de tipo Cadena, Flotante, Char, etc
		String 	a[] = new String[1];
		float 	b[] = new float[1];
		char 	c[] = new char[1];
		double 	d[] = new double[1];
		
		//como se indica el arreglo de enteros tiene un tamaño de 5 espacios
		//a estos espacios se les conocen como subindices, cada arreglo
		//comienza desde el subindice 0, es decir, que si el tamaño 
		//de tu arreglo es de 5, sería... de 0 a 4... 0, 1, 2, 3, 4
		
		variable[0] = 12;	//valor cualquiera de tipo entero
		variable[1] = 4234; //valor cualquiera de tipo entero
		variable[2] = 545;  //valor cualquiera de tipo entero
		variable[3] = 9;    //valor cualquiera de tipo entero
		variable[4] = 5;    //valor cualquiera de tipo entero
		
		//y así es como almacenamos distintos valores en una misma variable, muy útil 
		//para optimizar declaraciones...
		
		//normalmente se suelen utilizar en los ciclos como el "for" para optimizar las lineas de codigo
		
		//variable.length = al tamaño del arreglo, es lo mismo que poner directamente en este caso 
		//el número 5
		//de esta manera podemos observar lo que hay dentro del arreglo sin tantas lineas de código
		//con distintos System.out....variable[0] hasta otros System.out con los demás subindices
		//...[1],[2],[3] y [4]
		/*
			System.out.println(variable[0]);
			System.out.println(variable[1]);
			System.out.println(variable[2]);
			System.out.println(variable[3]);
			System.out.println(variable[4]);
		*/
		for (int i = 0; i < variable.length; i++) 
		{ 
			System.out.println("valor en el subindice " + 0 + " = " +variable[i] );
		}
		//También podemos asignarles valores de esta manera desde el teclado
		for (int i = 0; i < variable.length; i++) 
		{ 
			System.out.print("asigna valor al subindice " + 0 + " = " );
			variable[i] = lee.nextInt(i);
		}
		//Se reescribe todo el arreglo porque
		//Comenzamos a reescribir desde el subindice 0 como se indica en el valor de la "i".
		
		//Ahora vemos los nuevos valores con esta misma instrucción
		for (int i = 0; i < variable.length; i++) 
		{ 
			System.out.println("valor en el subindice " + 0 + " = " +variable[i] );
		}
	}
	
}
