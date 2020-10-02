package practicas;

public class MultiplicaciónFormulaNum 
{
	static int t=2,  a = 5, b = 3, n = 5, cont = 0;
//Iteración n = 1	,		n =	2			   , 		n  =  3 ...
//		(a+2^n-1 * b), (a+2^n-1 * b + 2^n  * b), (a+2^n-1 * b + 2^n-1  * b +  2^n-1 * b)
//		(a + Math.pow(2,0) * b)
// 		(a + Math.pow(2, 0) * b + Math.pow(2, 1) * b)
//		...
//		(a + Math.pow(2, n) * b + Math.pow(2, n) * b + Math.pow(2, n-1) * b + Math.pow(2, 3) * b)

	public static void main(String[] args) 
	{
		
		double arr[] = new double [n];
		for (int o = 0; o < 1; o++)
		{	
			for (int i = 0; i < n; i++) 
			{
				arr[i] = Math.pow(2, i) * b;
				int suma = 0;
				
				for (int j = 0; j <= i; j++)
				{
					suma += arr[j];
				}
				System.out.print((suma + a) + " ");
			}
			System.out.println();
		}
	}
}
