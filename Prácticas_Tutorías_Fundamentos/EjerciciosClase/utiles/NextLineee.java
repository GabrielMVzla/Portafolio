package utiles;

import java.util.Scanner;

public class NextLineee 
{

	public static void main(String[] args) {
		
		Scanner lee = new Scanner (System.in);
		String a = "";
		
		//Cuando el nextline es primero no es necesario ponerlo antes del system
		System.out.print("mensaje c: ");
		String c = lee.nextLine();
		
		//ni las que van después de este nextline
		System.out.print("mensaje ddd : ");
		String ddd = lee.nextLine();
		
		System.out.print("Ingresa tu edad: ");
		int edad = lee.nextInt();

		//cuando hay otro tipo de datos antes de nextline es necesario 
		//poner 2 nextline y en los siguientes será normal
		a = lee.nextLine();
		System.out.print("Ingresa un mensaje: ");
		a = lee.nextLine();
		
		//aquí es normal ya que arriba pusimos doble nextline
		System.out.print("Mensajito: ");
		String x = lee.nextLine();
		
		System.out.print("Ingresa tu edad: ");
		int edad1 = lee.nextInt();
		
		//es igual que el ejemplo anterior de nextline, ponemos doble nextline
		//para que funcione correctamente
		String b = lee.nextLine();
		System.out.print("Ingresa un mensaje 2: ");
		b = lee.nextLine();
		
		System.out.println("tu mensaje es: " + a + "\n"
						+ "el mensaje 2 es: " + b + "\n"
						+ "el mensaje x es: " + x + "\n"
						+ "la edad 1 es: "+ edad + ", la edad 2 es: " + edad1);

		
	}
	
}
