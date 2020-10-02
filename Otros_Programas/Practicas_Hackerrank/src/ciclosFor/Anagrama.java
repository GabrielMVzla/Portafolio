package ciclosFor;

import java.util.Arrays;
import java.util.Scanner;
//válida si es un anagrama tu programa o no
public class Anagrama 
{
    static boolean isAnagram(String a, String b)
    {
    	a = a.toLowerCase();
    	b = b.toLowerCase();
    	
    	if(a.length() != b.length())
    		return false;
    	
        String [] arrA = a.split("");
        String [] arrB = b.split("");
        Arrays.sort(arrA); //ordena los 2 parámetros y compara en el for si son iguales
        Arrays.sort(arrB);
      
        for (int i = 0; i < arrA.length; i++) 
        {
//        	System.out.println("A = " + arrA[i] + ", B = " + arrB[i]);
			if(!arrA[i].equalsIgnoreCase(arrB[i]))
			{
				return false;
			}
		}
        return true;
    }

  public static void main(String[] args) {
    
        Scanner scan = new Scanner(System.in);
        String a = "a aaa";
        String b = "a a  ";
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }
}
