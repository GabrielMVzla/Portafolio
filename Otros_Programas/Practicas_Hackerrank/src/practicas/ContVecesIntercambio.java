package practicas;

public class ContVecesIntercambio 
{
	public static void main(String[] args) 
	{
		int a[] = {1,1,2,2,3,3,5,5,7,7,9,9}, aux = 0;

		 int count = 0;
	     
	     for (int i = 0; i < a.length -1; i++) 
	     {
	         for (int j = i+1; j < a.length; j++) 
	         {
	             if(a[i] > a[j]) // > ordena de menor a mayor y < para ordenar de mayor a menor
	             {
	                 aux = a[j];
	                 a[j] = a[i];
	                 a[i] =  aux;
	                 count++;
	             }
	         }
	     }
	     for (int i = 0; i < a.length; i++)
	     {
		     System.out.println("i[" + (i + 1) + "] = " + a[i]);
	     }
	     System.out.println("contador = " + count);
	}
}
