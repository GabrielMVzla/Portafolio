package arreglosBidi;
import java.util.*;
public class Mayor 
{
	public static void main(String[] args) 
	{
		Scanner lee = new Scanner(System.in);
		int renglon = 0, columna = 0, mayor = 0;//Declaramos variables

		System.out.print("Ingresa cantidad de renglones: ");
		renglon = lee.nextInt();//asignamos el valor a variable renglon
		System.out.print("Ingresa cantidad de columnas: ");
		columna = lee.nextInt();//asignamos el valor a variable renglon
		
		//declaramos arreglo a y asignamos su tamaño solamente
		int a[][] = new int[renglon][columna];
		
		for (int i = 0; i < a.length; i++) 
		{
			for (int j = 0; j < a.length; j++) 
			{
				System.out.print("Ingresa alor de a[" + (i) + "][" + (j) + "]: ");
				a[i][j] = lee.nextInt(); //añadimos valor a cada subindice
				
				if(a[i][j] > mayor) //comparamos el actual de a contra mayor
					mayor = a[i][j];
			}
		}
		System.out.println("Mayor = " + mayor);//imprimimos mayor
	}
}
