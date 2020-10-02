package expresionesRegulares;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPruebaBoundary 
{
	public static void main(String[] args)
	{
		 String txt = "geeksforgeeks"; 
		  
	        // Demonstrating ^ 
	        String regex1 = "^geeks"; //indicamos que geeks se debería encontrar en el inicio de la cadena
	        Pattern pattern1 = Pattern.compile(regex1, Pattern.CASE_INSENSITIVE); 
	        Matcher matcher1 = pattern1.matcher(txt); 
	        while (matcher1.find()) 
	        { 
	            System.out.println("Start index: " + matcher1.start()); 
	            System.out.println("End index: " + matcher1.end()); 
	        } 
	        //obtenemos el indice de la primera vez que damos match con geek tanto inicio como fin
	        
	        String regex2 = "geeks$"; //indicamos que geeks se debería encontrar al final de la cadena
	        Pattern pattern2 = Pattern.compile(regex2, Pattern.CASE_INSENSITIVE); 
	        Matcher matcher2 = pattern2.matcher(txt); 
	        while (matcher2.find()) 
	        { 
	            System.out.println("\nStart index: " + matcher2.start()); 
	            System.out.println("End index: " + matcher2.end()); 
	        } 
	        //obtenemos el indice de la segunda vez que damos match con geek tanto inicio como fin
	      
	        System.out.println("\nboundaries\n");
	            txt = "geeksforgeeks geekspractice"; 
	            txt = "This island is beautiful"; 
	      
	            // Demonstrating beginning of word boundary 
	            regex1 = "\\bgeeks"; // Matched at two places con \\b indicamos el comienzo de cada palabra
	            regex1 = "\\bis\\b"; // en el ejemplo de la isla solo hará match una vez ya que cumple como limite inicial y final
	            
	            pattern1 = Pattern.compile(regex1, Pattern.CASE_INSENSITIVE); 
	            matcher1 = pattern1.matcher(txt); 
	            while (matcher1.find()) 
	            { 
	                System.out.println("Start index: " + matcher1.start()); 
	                System.out.println("End index: " + matcher1.end()); 
	            } 
	      
	            // Demonstrating end of word boundary 
//	            regex2 = "geeks\\b"; // Matched at one place 
//	            pattern2 = Pattern.compile(regex2, Pattern.CASE_INSENSITIVE); 
//	            matcher2 = pattern2.matcher(txt); 
//	            while (matcher2.find()) 
//	            { 
//	                System.out.println("\nStart index: " + matcher2.start()); 
//	                System.out.println("End index: " + matcher2.end()); 
//	            } 
	        }
	}

