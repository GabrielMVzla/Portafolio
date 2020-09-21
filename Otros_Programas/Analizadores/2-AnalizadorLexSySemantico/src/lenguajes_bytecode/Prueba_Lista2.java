package lenguajes;

import java.util.*;

public class Prueba_Lista2 {
	
	public static void main(String[] args) {
		
		Lista2 l = new Lista2();
		Scanner lee = new Scanner(System.in);
		while(true) {
			
			
			System.out.print(" \nOpciones."
							+ "\n1.- Ingresa."
							+ "\n2.- Muestra."
							+ "\n3.- Elimina."
							+ "\n4.- Salir."
							+ "\nIngresa opción: ");
			int op = lee.nextInt();
			
			switch(op) {
			
			case 1: 
				System.out.print("Ingresa dato numérico: ");
				int num = lee.nextInt();
				l.insertar(num);
				break;
			case 2:
				l.mostrar();
				break;
			case 3: 
				l.retirar();
				break;
			default: System.exit(0);
			
			}
			
		}
		
	}

}
