package practicas;

import java.util.Random;

public class Moda {

	public static void main(String[] args) {
	
		Random r = new Random();
		int a[] = new int [10];
		
		for (int i = 0; i < 10; i++) {
			
			a[i] = r.nextInt(5)+1;
			System.out.println("a["+(i+1)+"]: "+a[i]);
			
		}
		
		int numMMax = 0;
		int moda = 0;
		
		for (int i = 0; i < a.length; i++) {
			
			int numMax = 0;

			for (int j = 0; j < a.length; j++) {
				if(a[i] == a[j]) {
					numMax++;
				}
				if(numMax > numMMax) {
					numMMax = numMax;
					moda = a[i];
				}
			
		}}
		System.out.println("moda = "+moda+", numero de veces repetidas = "+numMMax);
		
	}
	
}
