package iterativas;

import java.util.Scanner;

public class Estructuras_Iterativas_While_Ejercicio2 
{
	public static void main(String[] args) 
	{
		Scanner lee = new Scanner(System.in);
		int opc = 0;
		//While tambi�n es �til para los men�s
		while(true) 
		{
			do {
				
				System.out.print("Opciones.\n"
						+ "1.- Opci�n 1.\n"
						+ "2.- Opci�n 2.\n"
						+ "3.- Opci�n 3.\n"
						+ "4.- Salir.\n"
						+ "Elige una opci�n: ");
				 opc = lee.nextInt();
				 if(opc <= 0 || opc >= 5)
					 System.out.println("\nEsa opci�n no se encuentra en las opciones.\n"
					 		+ "Intenta de nuevo.\n");
			//Es muy �til para v�lidar 
			}while(opc <= 0 || opc >= 5);
			
			System.out.println();
			switch (opc) 
			{
			case 1:
				System.out.println("Elegiste la opci�n 1.");
				break;
			case 2:
				System.out.println("Elegiste la opci�n 2.");
				break;
			case 3:
				System.out.println("Elegiste la opci�n 3.");
				break;
			case 4:
				System.out.println("Elegiste la opci�n de salir.\nHasta luego.");
				System.exit(0);
				break;

			default:
				System.out.println("Elegiste una opci�n que no se encuentra en la lista de opciones.");
				break;
			}
			System.out.println();
		}
	}
}
