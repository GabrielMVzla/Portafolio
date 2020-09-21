package practicas;

public class Prueba {
	
	
	public static void main(String[] args) 
	{
		
		float nums[] = {0.9f, 0.4f, 0.76f, 0.4f}, suma = 0, promedio = 0;
		
		for (int i = 0; i < nums.length; i++) {
			
			suma += nums[i];
			
		}
		promedio = suma/nums.length;
		
		float VecesR = 0, MaxVecesR = 0, moda = 0, pos = 0;
		for (int i = 0; i < nums.length; i++) 
		{
			
			for (int j = 0; j < nums.length; j++)
			{
				
				if(nums[i] == nums[j]) 
				{
					VecesR++;
				}
				if(VecesR > MaxVecesR) 
				{
					MaxVecesR = VecesR;
					moda = nums[i];
					pos = i;
				}
			}
			
		}
		
		System.out.println("moda = "+moda);
	}

}
