package ciclosFor;

public class Palindromo
{
	public static void main(String[] args) 
	{
		String palindromo = "oxxo";
		int reverso = palindromo.length()-1,i = 0;
		
		for (i = 0; i < palindromo.length(); i++) 
		{
			if(palindromo.charAt(i) != palindromo.charAt(reverso))
			{
				System.out.println("No");
				return;
			}
			reverso--;			
		}
		if(i == palindromo.length())
		{
			System.out.println("Yes");
		}
	}
}
