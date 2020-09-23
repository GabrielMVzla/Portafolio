package parametros;
import java.util.*;
public class MetodosCasos 
{
	//Completar el siguiente programa
	static Scanner lee = new Scanner(System.in);
	public static void main(String[] args) 
	{	
		System.out.print("Elige una de las opciones: ");
		int n = lee.nextInt();
		
		opciones(n);
	}
	public static void mostrarOpciones()
	{
		System.out.println( "    Opciones.\n"
				+ "1.- Suma de 2 números.\n"
				+ "2.- Resta de 2 números.\n"
				+ "3.- Multiplicación de 2 números.\n"
				+ "4.- División de 2 números");
	}
	public static void opciones(int n) {
		switch (n) {
		case 1:
			suma();
			break;
//		case 2:
//			resta();
//			break;
		default:
			System.out.println("\nNo elegiste una opción válida en los casos");
			break;
	}}
	public static void suma() 
	{
		System.out.print("\nElige el primer número: ");
		int n1 = lee.nextInt();
		System.out.print("Elige el segundo número: ");
		int n2 = lee.nextInt();
		System.out.println("\nLa suma de " + n1 + " + " + n2 + " = " + (n1+n2));
	}
}
