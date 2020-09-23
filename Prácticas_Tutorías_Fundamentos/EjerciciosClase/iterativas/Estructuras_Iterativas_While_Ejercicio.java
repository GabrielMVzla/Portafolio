package iterativas;

public class Estructuras_Iterativas_While_Ejercicio 
{
	//Programa que imprima los números del 1 al 100 con while o doWhile
	public static void main(String[] args) 
	{
		
		int iteracion = 0; 
		while(iteracion < 100) 
		{
			iteracion++;
			System.out.println(iteracion);
		}

		//Imprimir solamente los números par del número 2 desde 2 hasta 100
		iteracion = 0; 
		while(iteracion < 100)
		{
			iteracion++;
			//% es para calcular el residuo, cuando en una división de iteracion (que va aumentando)
			//entre 2 no tiene residuo, cumple con la condición y entra, 2/2 da 1, sin puntos decimales,
			//los puntos decimales son el residuo, 3/2 = 1.5, tiene residuo por lo tanto no cumple
			if(iteracion % 2 == 0) 
				System.out.println(iteracion);
		}
	}
}
