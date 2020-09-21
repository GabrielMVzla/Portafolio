package practicas;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.ArrayList;

public class Solution {

    // Complete the birthdayCakeCandles function below.
	public static Scanner r = new Scanner(System.in);
	public static void main(String[] arg) 
	{
		String s, cadena = "";
//		String s = r.next();
		s = "07:05:45PM";
		 int suma = 0;
		if(s.charAt(s.length()-2)== 'P' || s.charAt(s.length()-2)== 'p')
        {
			System.out.println("neta" + s.charAt(s.length()-2) + s.charAt(s.length()-1));
			for (int i = 0; i < s.length(); i++) {
				if( i == 0)
				{
					if( s.charAt(0) == '0')// && ( s.charAt(1) == '8' || s.charAt(1) == '9')) 
					{
						cadena += '1'; 
						if(s.charAt(1) < '8') {
							cadena =  "1" + String.valueOf(((char)(s.charAt(1)+((int)(char)2))));
							System.out.println("cadena ) " + cadena);
						}
						if( s.charAt(1) == '8') 
							cadena = String.valueOf('2'+'0');
						if( s.charAt(1) == '9') 
							cadena = String.valueOf('2'+'1');
					}
				}
				else
					cadena += s.charAt(i);
				if(i == 1)
				{
					if(cadena.charAt(0) == '1')
					{
						suma =  '1'+ '2';
						cadena += String.valueOf(suma);
						System.out.println("suma = " + suma);
					}
					else
						cadena += s.charAt(i);	
				}
			}
        }
       System.out.println(cadena);

	}
}
