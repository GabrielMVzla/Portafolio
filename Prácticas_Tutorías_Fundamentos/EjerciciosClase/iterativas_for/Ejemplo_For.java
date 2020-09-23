package iterativas_for;

public class Ejemplo_For {
	
	public static void main(String[] args) 
	{
		//El for es otro tipo de ciclo donde tiene todo en uno
		//for( Declaración de Variable; Condición; Contador)
		/*
			"i" comienza en 0 y dará las vueltas que especifiquemos en la condición
			por medio del contador "i++" en este ejemplo tenemos que inicie en 0 y dé vueltas
			mientras i sea menor a 10, osea que cuando "i" sea igual a 10, se saldrá del for
			diciendo es (10 menor a 10) --> NO, no es menor, SON IGUALES, por lo tanto no cumple
			la condición y se sale del for.
		 */
		for (int i = 0; i < 10; i++) 
		{
			System.out.println("indice = " + i);
			
		}
		//10 iteraciones, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9  desde 0 a 9
		//                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
		
		
		System.out.println("\n\n");
		
		int contador_ixj = 0;
		int contador_i = 0;
		//También tenemos los for anidados, donde tenemos un for dentro de otro
		for (int i = 0; i < 3; i++) 
		{
			int contador_solo_j = 0;
			contador_i++;

			//el for de j dará 3 vueltas cada vuelta del for de i, y en este ejemplo
			//el for de i, da también 3 vueltas, lo que quiere decir que este ciclo dará 
			//9 vueltas
			for (int j = 0; j < 3; j++)
			{
				contador_solo_j++;
				contador_ixj++;
			}
			System.out.println("solo j = " + contador_solo_j);
		}
		System.out.println("contador i = " + contador_i);
		System.out.println("contador ixj = " + contador_ixj);
	}

}
