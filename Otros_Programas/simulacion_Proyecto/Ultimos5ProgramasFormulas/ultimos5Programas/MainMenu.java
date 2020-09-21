package ultimos5Programas;

import java.util.Scanner;

public class MainMenu {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = 0;
	  System.out.println("    OPCIONES");
		System.out.print("1.- Chi cuadrado\n"
						+"2.- Kolmogorov\n"
						+"3.- Series\n"
						+"4.- Distancias\n"
						+"5.- Poker\n"
						+"Ingresa opción: ");
		x = sc.nextInt();
		
		switch (x) {
		case 1:
			new PruebaChi2N();
			break;
		case 2:
			new Kolmogorov();
			break;
		case 3:
			new PruebaSeries();
			break;
		case 4:
			new Hoyos();
			break;
		case 5:
			new Poker();
			break;
		}
		System.out.println("________________________________________________________________\n\n");
		main(null);
	}
}