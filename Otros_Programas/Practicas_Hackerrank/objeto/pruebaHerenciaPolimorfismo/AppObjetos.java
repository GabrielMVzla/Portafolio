package pruebaHerenciaPolimorfismo;

import java.lang.reflect.Array;
import java.util.Arrays;

public class AppObjetos 
{
	public static void main(String[] args) 
	{
		int [] arr = {1,2,4,5,3};
		int aux = arr[arr.length-4];
		
		for (int i = arr.length - 1; i >= 0; i--)
		{
			if(i == arr.length - 1)
				
			if(aux < arr[i])
				arr[i] = arr[i-1];
			else
				arr[i] = aux;
			System.out.println(arr[i]);
		}
	}
}