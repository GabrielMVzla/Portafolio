package practicas;

public class MultiplicacionFormula
{
	//Iteración n = 1	,		n =	2			   , 		n  =  3 ...
//	(a+2^n-1 * b), (a+2^n-1 * b + 2^n  * b), (a+2^n-1 * b + 2^n-1  * b +  2^n-1 * b)
//	(a + Math.pow(2,0) * b)
//		(a + Math.pow(2, 0) * b + Math.pow(2, 1) * b)
//	...
//	(a + Math.pow(2, n) * b + Math.pow(2, n) * b + Math.pow(2, n-1) * b + Math.pow(2, 3) * b)
	static int t=2,  a = 0, b = 2, n = 4, cont = 0;
	public static void main(String[] args) 
	{


		for (int j = 0; j < t; j++) 
		{
			String acum = "";
			acum = "(a + Math.pow(2," + 0 +") * b)";
			System.out.println(acum);
			
			for (int j2 = 1; j2 < n; j2++)
			{
				String nvoAcum = "";
				String base = "";
			
					if( j2 == 1)
					{
						base = "(";
						base += "a + Math.pow(2, 0) * b";
						acum = base + " + Math.pow(2, "+ j2 +") * b";
						
						acum = acum + ")";
						System.out.println(acum);
					}
					else
					{ 
						for (int k = 0; k < acum.length()-1; k++) 
						{
							nvoAcum += acum.charAt(k);
						}
//						acum = nvoAcum;
						acum = nvoAcum + " + Math.pow(2, "+ j2 +") * b";
						acum = acum + ")";
						System.out.println(acum);
					}
			}
			System.out.println();
		}
	}
}
