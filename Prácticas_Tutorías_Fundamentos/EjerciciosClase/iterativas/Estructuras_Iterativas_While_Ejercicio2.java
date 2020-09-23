package iterativas;

import java.util.Scanner;

public class Estructuras_Iterativas_While_Ejercicio2 
{
	public static void main(String[] args) 
	{
		Scanner lee = new Scanner(System.in);
		int opc = 0;
		//While también es útil para los menús
		while(true) 
		{
			do {
				
				System.out.print("Opciones.\n"
						+ "1.- Opción 1.\n"
						+ "2.- Opción 2.\n"
						+ "3.- Opción 3.\n"
						+ "4.- Salir.\n"
						+ "Elige una opción: ");
				 opc = lee.nextInt();
				 if(opc <= 0 || opc >= 5)
					 System.out.println("\nEsa opción no se encuentra en las opciones.\n"
					 		+ "Intenta de nuevo.\n");
			//Es muy útil para válidar 
			}while(opc <= 0 || opc >= 5);
			
			System.out.println();
			switch (opc) 
			{
			case 1:
				System.out.println("Elegiste la opción 1.");
				break;
			case 2:
				System.out.println("Elegiste la opción 2.");
				break;
			case 3:
				System.out.println("Elegiste la opción 3.");
				break;
			case 4:
				System.out.println("Elegiste la opción de salir.\nHasta luego.");
				System.exit(0);
				break;

			default:
				System.out.println("Elegiste una opción que no se encuentra en la lista de opciones.");
				break;
			}
			System.out.println();
		}
	}
}
