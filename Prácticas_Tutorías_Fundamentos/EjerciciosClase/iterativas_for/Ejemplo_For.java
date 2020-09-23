package iterativas_for;

public class Ejemplo_For {
	
	public static void main(String[] args) 
	{
		//El for es otro tipo de ciclo donde tiene todo en uno
		//for( Declaraci�n de Variable; Condici�n; Contador)
		/*
			"i" comienza en 0 y dar� las vueltas que especifiquemos en la condici�n
			por medio del contador "i++" en este ejemplo tenemos que inicie en 0 y d� vueltas
			mientras i sea menor a 10, osea que cuando "i" sea igual a 10, se saldr� del for
			diciendo es (10 menor a 10) --> NO, no es menor, SON IGUALES, por lo tanto no cumple
			la condici�n y se sale del for.
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
		//Tambi�n tenemos los for anidados, donde tenemos un for dentro de otro
		for (int i = 0; i < 3; i++) 
		{
			int contador_solo_j = 0;
			contador_i++;

			//el for de j dar� 3 vueltas cada vuelta del for de i, y en este ejemplo
			//el for de i, da tambi�n 3 vueltas, lo que quiere decir que este ciclo dar� 
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
