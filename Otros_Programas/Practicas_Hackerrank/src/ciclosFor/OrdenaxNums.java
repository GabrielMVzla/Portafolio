package ciclosFor;

public class OrdenaxNums 
{
	public static void main(String[] args) 
	{
		
		int a[] = {1,4,3,5,6,2}, aux = 0;
		
		for (int i = 0; i < a.length; i++) 
		{
			for (int j = 0; j < a.length; j++) 
			{
				if(a[i] < a[j])
				{
					aux = a[i];
					a[i] = a[j];
					a[j] =  aux;
				}
			}
			for (int i2 = 0; i2 < a.length; i2++) 
			{
				System.out.print(a[i2] + " ");
			}
		System.out.println();
		}
	}
}
