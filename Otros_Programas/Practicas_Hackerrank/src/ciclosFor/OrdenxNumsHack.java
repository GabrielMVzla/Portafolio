package ciclosFor;

import java.util.Arrays;

public class OrdenxNumsHack 
{
	public static void main(String[] args) 
	{
//		int a[] = {1,4,3,5,6,2};
		int a[] = {1,4,3,5,6,2};
//		int a[] = {4,4,3,4};
		int aux = 0, cont = 0;
		
		for (int i = 0; i < a.length; i++) 
		{
			for (int j = 0; j < i; j++) 
			{
				if(a[j] > a[i])
				{
					aux = a[i];
					a[i] = a[j];
					a[j] =  aux;
					cont++;
				}
			}
			if(i != 0)
			{
				for (int i2 = 0; i2 < a.length; i2++) 
				{
					System.out.print(a[i2] + " ");
				}
				System.out.println();
			}
		}
		System.out.println("contador = " + cont);
	}
}
