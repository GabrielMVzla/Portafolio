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
				+ "2.- realizar una resta de 2 números.\n"
				+ "3.- realizar una multiplicación de 2 números."
				+ "\nElige una de las 3 opciones: ");
		var1 = lee.nextInt(); //ingresamos un dato por teclado, el cual se guardará en la variable var1

		switch (var1) { 
		case 1:
			System.out.println("\nHaz elegido realizar una Suma\n");
			System.out.print("Ingresa el primer número: ");
			op1 = lee.nextInt();
			System.out.print("Ingresa el segundo número: ");
			op2 = lee.nextInt();
			
			op1 = op1 + op2;
			System.out.println("la suma es igual a \"" + op1 + "\"");
			
			break;
		case 2:
			System.out.println("\nHaz elegido realizar una Resta\n");
			System.out.print("Ingresa el primer número: ");
			op1 = lee.nextInt();
			System.out.print("Ingresa el segundo número: ");
			op2 = lee.nextInt();
			
			op1 = op1 - op2;
			System.out.println("la resta es igual a \"" + op1 + "\"");	
			break;
		case 3:
			System.out.println("\nHaz elegido realizar una multiplicación\n");
			System.out.print("Ingresa el primer número: ");
			op1 = lee.nextInt();
			System.out.print("Ingresa el segundo número: ");
			op2 = lee.nextInt();
			
			op1 = op1 * op2;
			System.out.println("la multiplicación es igual a \"" + op1 + "\"");	
			break;
						//en caso de que no ingreses un valor que coincida con uno de los casos (valor 1, 2 o 3)
						//el programa se irá a la sección de default
		default:
			System.out.println("No has elegido una de los opciones especificadas");
			break;
		}}
}
