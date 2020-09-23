package arreglos;

public class Arreglo_Intercambio_Ordenamiento 
{
	//Ordenamiento de menor a mayor	
	public static void main(String[] args) 
	{
		
		int a[] = {1, 78, 98, 1};
//		int a[] = {9 , 8 , 7 , 6, 5, 4, 3};
		int aux = 0;
		
		for (int i = 0; i < a.length - 1; i++) 	   //for (int i = 0; i < a.length; i++) -- De esta manera tambén funciona pero es menos
			for (int j = i + 1; j < a.length; j++) //for (int j = i; j < a.length; j++) -- eficiente porque el for da más vueltas
				if(a[i] > a[j]) 
				{
					aux = a[i];
					a[i] = a[j];
					a[j] = aux;
				}
		for (int i = 0; i < a.length; i++) 
		{
			System.out.println((i + 1) +".- " + a[i]);
		}
		//j = 1 porque i + 1 da = 1
		//j = 0 sí existe, simplemente se omite porque no tiene caso comparar 
		//a[0] en i con a[0] en j, ya que en esa posición está el mismo número
//Vuelta 0 en i				 a[i]>a[j]
		//i = 0, j = 1 1ra -> (1 > 78)? = NO, no entra, entonces 1 sigue permaneciendo en la posición 0 del arreglo
		//i = 0, j = 2 2da -> (1 > 98)? = NO, no entra, entonces 1 sigue permaneciendo en la posición 0 del arreglo
		//i = 0, j = 3 3ra -> (1 >  1)? = NO, no entra, entonces 1 sigue permaneciendo en la posición 0 del arreglo
		
		//j = 2 porque i ahora vale 1 + 1 da = 2, pasa lo mismo, por cuestión de optimización no tiene caso empezar
		//desde el indice 0 ya que en el ciclo anterior de i, comprobamos que no había nada menor a ese 1, entonces
		//permanecerá ahí durante toda la ejecución.
//Vuelta 1 en i				  a[i]>a[j]
		//i = 1, j = 2 1ra -> (78 > 98)? = NO, no entra, entonces 78 sigue permaneciendo en la posición 1 del arreglo
		//i = 1, j = 3 2da -> (78 > 1 )? = SI, sí entra, entonces 78 intercambia de lugar con en 1 permaneciendo el "1" 
		//en la posición 1 del arreglo y el 78 en la posición 3 de esta manera {1, 1, 98, 78}
		//														    posiciones [0] [1] [2] [3]
		
		//Como se observa en el último renglón cambiamos de posición a 78 entonces compararemos con ese
//Vuelta 2 en i				  a[i]>a[j]
		//i = 2, j = 3 1ra -> (98 > 78)? = SI, sí entra, entonces 98 intercambia de lugar con el 78 permaneciendo el 78 
		//la posición 2 del arreglo y eñ 98 en la posicion 3 de esta manera {1, 1, 78, 98}
		//													     posiciones [0] [1] [2] [3]		
		
	//Ya no hay más vueltas a pesar de que el tamaño del arreglo es de 4 y se supone debe llegar a la vuelta 3 no lo
	//hace porque al incio especificamos en la condición que "i" debe ser menor a "a.length - 1", es decir, i < (4 - 1)
	//																									    i < 3
	//y como se puede observar ya quedan totalmente acomodados en orden ascendente {1, 1, 78, 98}
	//para cambiar el tipo de orden solo deben de invertir el signo en la condición en if(a[i] > a[j]) a menor que ** < **

		String c[] = {"g", "z", "a", "A"};
		String auxiliar = "";
		
		for (int i = 0; i < c.length - 1; i++) 	   
			for (int j = i + 1; j < c.length; j++) 
			{	
				if(c[i].compareTo(c[j]) > 0) 
				{
					auxiliar = c[i];
					c[i] = c[j];
					c[j] = auxiliar;
				}
			}
		for (int i = 0; i < a.length; i++) 
		{
			System.out.println((i + 1) +".- " + c[i]);
		}
		//Para ordenar Cadenas o Carácteres es de esta manera
	}
}
