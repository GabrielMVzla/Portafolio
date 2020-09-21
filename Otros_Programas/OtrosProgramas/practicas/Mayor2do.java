package practicas;

import java.util.Random;

public class Mayor2do {

	public static void main(String[] args) {
		

		Random r = new Random();
		int a[] = new int [5], mayor = 0, pos = 0, segmay = 0, pos2 = 0;
	
		for (int i = 0; i < a.length; i++) 
		{
			a[i] = r.nextInt(100)+1;
			System.out.println("a["+(i+1)+"]: "+a[i]);
		}
		for (int i = 0; i < a.length; i++)
		{
			if(a[i] > mayor) 
			{
				mayor = a[i];
				pos = (i+1);
			}
		}
		for (int i = 0; i < a.length; i++) 
		{
			if(a[i] < mayor && a[i] > segmay) 
			{
				segmay = a[i];
				pos2 = i+1;
			}
		}
		System.out.println("el número mayor es = "+mayor+" su posición es = "+pos);
		System.out.println("y el segundo mayor es = "+segmay+" su posición es = "+pos2);
	}
}
