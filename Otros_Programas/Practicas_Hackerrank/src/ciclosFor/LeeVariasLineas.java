package ciclosFor;

import java.util.Scanner;

public class LeeVariasLineas 
{
	//Primero se ejecuta la estructura estática.
	static 
	{
		int a = 0;
		if(a < 1 )
		{
			try {
				
			} catch (Exception e) {}
			System.out.println("hola");
		}
	}
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		String text = sc.nextLine();
		System.out.println(text);
		for (int i = 0; i < n; i++)
		{
			text = sc.nextLine();
			System.out.println(text);
		}
	}
}
