package ciclosFor;

import java.util.*;

public class OrderRandomNums {

	public static void main(String[] args) 
	{
		Random r = new Random();
		int a[] = new int [10], aux = 0;
		
		for (int i = 0; i < a.length; i++) 
		{
		
			a[i] = r.nextInt(100)+1;
			System.out.println("a["+(i+1)+"] = "+a[i]);
		
		}
		
		for (int i = 0; i < a.length; i++) 
		{
			
			for (int j = i+1; j < a.length; j++) 
			{
				
				if(a[i] > a[j]) 
				{
					aux = a[i];
					a[i] = a[j];
					a[j] = aux;
				}
				
			}
		}
		System.out.println("ordenado");
		for (int i = 0; i < a.length; i++)
		{
			System.out.println(a[i]);
		}
		
	}
	
}
