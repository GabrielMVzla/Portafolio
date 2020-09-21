package practicas;

public class OrdenXletra {

	public static void main(String[] args) 
	{
		
		String a[] = {"z", "b", "w", "q", "x", "W", "a", "A", "y tu te vas"}, aux = "";
		
		for (int i = 0; i < a.length; i++) 
		{
			
			for (int j = i+1; j < a.length; j++) 
			{
				
				if(a[i].compareTo(a[j])<0) {
					aux = a[i];
					a[i] = a[j];
					a[j] =  aux;
				}
				
			}
		}
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
	
}
