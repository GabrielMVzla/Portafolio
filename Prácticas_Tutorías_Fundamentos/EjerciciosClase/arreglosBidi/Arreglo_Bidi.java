package arreglosBidi;

public class Arreglo_Bidi 
{
	//Ejemplo de arreglo bidimensional
	public static void main(String[] args) 
	{
	//para declarar un arreglo bidimensinal es necesario ponerle 2 pares de corchetes
	//despues asignamos el tamaño de cada par de corchetes y la cantidad de valores
	//que podrá almacenar se calcula mediante una multiplicación 
	//en el siguiente ejemplo la cantidad de espacios disponibles será de 3 x 4 = 12
	//12 espacios disponibles para almacenar información de tipo entero
		int a[][] = new int[3][4], contador = 0;
		
		for (int i = 0; i < a.length; i++)
		{
			for (int j = 0; j < a.length; j++)
				a[i][j] = contador++;
		}
		
		for (int i = 0; i < a.length; i++) 
		{
			for (int j = 0; j < a.length; j++) 
				System.out.print("a["+ i +"]["+ j +"] = " +a[i][j] + "\t");
			System.out.println();
		}
		
		System.out.println("\n==================================================\n");
		//de esta manera es más clara de entender ya que en matemáticas se suele
		//representar comenzando desde el 1 y no desde el 0, de la manera que funcionan
		//los arreglos.
		for (int i = 0; i < a.length; i++) 
		{
			for (int j = 0; j < a.length; j++) 
				System.out.print("("+ (i + 1) +", "+ (j + 1) +") = " +a[i][j] + "\t");
			System.out.println();
		}
	}
	
}
