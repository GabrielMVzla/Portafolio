package practicas;

public class Prueba {

	public static void main(String[] args) 
	{
		int maria, juan = 15;
		
		maria = 17;	// maria tiene 17 a�os
					// juan tiene 15 a�os

		//Calcularemos quien es el mayor

		if(maria > juan)
		//""si"" maria es mayor que juan
		//""entonces"" maria es la mayor de todos, en este caso que juan.
		{
			System.out.println("maria es la mayor con " + maria + " a�os de edad.");
			//recordemos que estamos tomando a maria como una persona pero sigue
			//siendo una variable con un valor asignado.
		}
		else
		//""si no"" se cumple la condici�n (maria siendo mayor que juan)
		//anterior entra en este else
		{
			System.out.println("juan es la mayor con " + juan + " a�os de edad.");
			//es el mismo caso del System de arriba para la variable juan
		}
	}
}
