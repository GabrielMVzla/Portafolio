package practicas;

import java.util.Scanner;

public class Multiple
{
	public static void main(String[] args) 
	{
		Scanner lee = new Scanner(System.in);
		int var1 = 0; //declaramos variable
		
		//Damos una serie de opciones
		System.out.print("Opciones.\n1.- Opci�n 1.\n2.- Opci�n 2.\n3.- Opci�n 3.\nElige una de las 3 opciones: ");
		var1 = lee.nextInt(); //ingresamos un dato por teclado, el cual se guardar� en la variable var1

		switch (var1) { //en el switch se le manda la variable var1 para que dependiendo de su valor
						//entre en uno de los casos, ej. si ingresaste el valor por teclado 1, te mandar�
						//al caso 1 e imprimir� por consola el mensaje dentro del System.out
		case 1:
			System.out.println("Elegiste la opci�n 1");
			break;
		case 2:
			System.out.println("Elegiste la opci�n 2");
			break;
		case 3:
			System.out.println("Elegiste la opci�n 3");
			break;
						//en caso de que no ingreses un valor que coincida con uno de los casos (valor 1, 2 o 3)
						//el programa se ir� a la secci�n de default
		default:
			System.out.println("No has elegido una de los opciones especificadas");
			break;
		}
		
	}
}
