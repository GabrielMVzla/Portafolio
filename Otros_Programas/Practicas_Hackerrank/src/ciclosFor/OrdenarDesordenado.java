package ciclosFor;

public class OrdenarDesordenado {
//Tenemos una secuencia de números ordenados a excepción de 1, se debe acomodar el que no tiene orden
	public static void main(String[] args) {
		int [] arr = {2,4,6,8,3};
		int aux = arr[arr.length-1];
		
		for (int i = arr.length - 1; i > 0; i--)
		{
			if(i == arr.length - 1)
			{
				arr[i] = arr[i-1];
			}
			else 
			imprime(arr);
			if  (i == 1)
			{
				if(aux > arr[i-1])
				{
					arr[i] = aux;
					
					imprime(arr);
				}else
				{
					arr[i] = arr[i-1];
					imprime(arr);
					arr[i-1] = aux;
					imprime(arr);
				}
			}
			if  (i > 1 && aux > arr[i-1] && aux < arr[i])
			{
				arr[i] = aux;
				imprime(arr);
				return;
			}
			if(aux < arr[i])
				arr[i] = arr[i-1];
		}
	}
	public static void imprime(int[] arr) 
	{
		for (int j = 0; j < arr.length; j++) 
			System.out.print(arr[j] + " ");
		System.out.println();

	
	}
}
