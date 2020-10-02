package ciclosFor;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class EliminaPalabrsRepNoREGEX {
	 public static void main(String[] args)
	    {
		 
	
		 
	    	 String input = "Hello hello Ab aB"; 
//	    	 String input = "Goodbye bye bye world world world"; 
	    	 		
	    	 String[] palabras = input.split(" ");
	    	 ArrayList<String> unico = new ArrayList<String>();
	    	 
	    	 System.out.println(input+"\n");
	    	 for (int i = 0; i < palabras.length; i++) {
	     		System.out.println(palabras[i]);

			}
	    	 System.out.println();
	    	 int i = 0;
	    	 for (i = 0; i < palabras.length; i++)
	    	 {
	    		 unico.add(palabras[i]);
	    		 if(i < palabras.length-1 && palabras[i].equalsIgnoreCase(palabras[i+1]))
		    		 do
		        	 {
		        		 i++;
		        		 if(i >= palabras.length-1 || !palabras[i].equalsIgnoreCase(palabras[i+1]))
		        		 {	
//		        			 i--;
		        			 break;
		        		 }
		        	 }while(!palabras[i].equalsIgnoreCase(palabras[i+1]));
	    		 if(i < palabras.length-1)
	    			 if(palabras[i].equalsIgnoreCase(palabras[i+1]))
	    				 i++;
	    	 }
	    	for (int j = 0; j < unico.size(); j++) 
	    	{
				
	    		System.out.println(unico.get(j));
			}
	    }
}
