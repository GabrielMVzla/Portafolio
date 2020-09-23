package iterativas;

public class Estructuras_Iterativas_While 
{
	public static void main(String[] args)
	{
		int iteracion = 0;
		int numRepeticiones = 3;
		
		//while("Condición"){ Código a repetir }
		//En el while primero válida y luego ejecuta
		
		while(iteracion < numRepeticiones) // 0 < 3? ✓ -- 1 < 3? ✓ -- 2 < 3? ✓ -- 3 < 3?X 
		{	
			System.out.println(iteracion + ".- Hola Mundo");
			iteracion++;
		}
		
		System.out.println("\nvalor de iteración = " + iteracion);
		iteracion = 0; //iteracion = 3 --> iteracion = 0
		System.out.println("valor de iteración = " + iteracion + "\n");
		
		//En el doWhile primero ejecuta una instrucción y luego válida
		do {
			System.out.println(iteracion + ".- Hola Mundo");
			iteracion++;
		}while(iteracion < numRepeticiones); // 1 < 3?✓ -- 2 < 3?✓ -- 3 < 3?X 
	
	}}
