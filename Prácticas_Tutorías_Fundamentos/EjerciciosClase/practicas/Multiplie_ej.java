package practicas;

import java.util.Scanner;

public class Multiplie_ej {

	
	public static void main(String[] args) 
	{
		Scanner lee = new Scanner(System.in);
		int var1 = 0; //declaramos variable
		
		int op1 = 0, op2 = 0;
		
		//Damos una serie de opciones
		System.out.print("Opciones.\n"
				+ "1.- Realizar una suma de 2 numeros.\n"
				+ "2.- realizar una resta de 2 n�meros.\n"
				+ "3.- realizar una multiplicaci�n de 2 n�meros."
				+ "\nElige una de las 3 opciones: ");
		var1 = lee.nextInt(); //ingresamos un dato por teclado, el cual se guardar� en la variable var1

		switch (var1) { 
		case 1:
			System.out.println("\nHaz elegido realizar una Suma\n");
			System.out.print("Ingresa el primer n�mero: ");
			op1 = lee.nextInt();
			System.out.print("Ingresa el segundo n�mero: ");
			op2 = lee.nextInt();
			
			op1 = op1 + op2;
			System.out.println("la suma es igual a \"" + op1 + "\"");
			
			break;
		case 2:
			System.out.println("\nHaz elegido realizar una Resta\n");
			System.out.print("Ingresa el primer n�mero: ");
			op1 = lee.nextInt();
			System.out.print("Ingresa el segundo n�mero: ");
			op2 = lee.nextInt();
			
			op1 = op1 - op2;
			System.out.println("la resta es igual a \"" + op1 + "\"");	
			break;
		case 3:
			System.out.println("\nHaz elegido realizar una multiplicaci�n\n");
			System.out.print("Ingresa el primer n�mero: ");
			op1 = lee.nextInt();
			System.out.print("Ingresa el segundo n�mero: ");
			op2 = lee.nextInt();
			
			op1 = op1 * op2;
			System.out.println("la multiplicaci�n es igual a \"" + op1 + "\"");	
			break;
						//en caso de que no ingreses un valor que coincida con uno de los casos (valor 1, 2 o 3)
						//el programa se ir� a la secci�n de default
		default:
			System.out.println("No has elegido una de los opciones especificadas");
			break;
		}}
}
