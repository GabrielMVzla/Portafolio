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
				+ "1.- Suma de 2 n�meros.\n"
				+ "2.- Resta de 2 n�meros.\n"
				+ "3.- Multiplicaci�n de 2 n�meros.\n"
				+ "4.- Divisi�n de 2 n�meros");
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
			System.out.println("\nNo elegiste una opci�n v�lida en los casos");
			break;
	}}
	public static void suma() 
	{
		System.out.print("\nElige el primer n�mero: ");
		int n1 = lee.nextInt();
		System.out.print("Elige el segundo n�mero: ");
		int n2 = lee.nextInt();
		System.out.println("\nLa suma de " + n1 + " + " + n2 + " = " + (n1+n2));
	}
}
