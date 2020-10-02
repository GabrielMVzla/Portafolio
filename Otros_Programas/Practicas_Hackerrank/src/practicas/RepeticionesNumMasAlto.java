package practicas;

import java.util.Arrays;
//calcular la veces que se repite el número más grande, en este caso el "5"
public class RepeticionesNumMasAlto
{
	public static void main(String[] args) 
	{
		int size = Integer.MAX_VALUE;
		System.out.println("size = " + size);
		int ar[] = {1,1,1,5,5,1,1,3,3,4,5,1,3,5,2};
		
		Arrays.sort(ar);
		int rep = 1;

		for (int i = ar.length-1; i > 0; i--)
		{
			if (ar[i] == ar[i-1])
			{
				rep++;
			}
			else
			{
				break;				
			}
		}
		
		rep = 1;
		int i = 0;
		for (i = ar.length-1; ar[i] == ar[i-1] && i > 0 ; i--)
		{
			rep++;
		}
		System.out.println("rep = " + rep);
	}
	
	//calcula las veces que se repite el número que más común
//    Arrays.sort(ar);
//    int cont = 0, numMax = 0;
//
//    for(int i = 1; i < ar.length; i++)
//    {
//        if(ar[i-1] == ar[i])
//        {
//            cont++;
//        
//        }   
//        else
//            cont = 1;
//        if(cont > numMax) 
//            numMax = cont;
//    }
//    return numMa
}
