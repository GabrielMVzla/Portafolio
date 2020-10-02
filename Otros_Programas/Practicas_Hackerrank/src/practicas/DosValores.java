package practicas;

import java.util.*;

public class DosValores {

	public static void main(String[] args) 
	{
		
		Random r = new Random();
		int aux = 0, a[] = new int [12];
		
		for (int i = 0; i < a.length; i++) {
			
			a[i] = r.nextInt(5)+1;
			System.out.println("a["+(i+1)+"]: "+a[i]);
			
		}
		int re = 0, max = 0, moda = 0;
		for (int i = 0; i < a.length; i++) {
			re = 0;
			for (int j = 0; j < a.length; j++) {
				
				if(a[i] == a[j])
				{
					
					re++;
					
				}
				if(re > max) {
					max = re;
					moda = a[i];
					
				}
			}
		}

		System.out.println("moda = "+ moda+ ", veces repetidas "+max);
		
	}
	
}
