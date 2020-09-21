package analizadorLexico;
import java.io.*;
import java.util.*;

public class MainLexico
{		
    static String elseifthen[] = {"if", "then", "else"},   // 0 - if
    			  pribegend [] = {"print", "begin", "end"},// 0 - print -> if
    			  simbolos  [] = {"=", ";"},
    			  file 		   = "prueba1bien.txt";
//				  file 		   = "prueba2bien.txt";
//				  file 		   = "prueba3error.txt";

    static ArrayList<InfoToken> infoTokens= new ArrayList<InfoToken>();
    static ArrayList<String> detalles= new ArrayList<String>();
    // 0 -> 1 dato     	0 -> 1 objeto ( dato1, dato2)
    // 1 -> 1 dato		1 -> 1 objeto ( dato1, dato2)
    // 2 -> 1 dato		2 -> 1 objeto ( dato1, dato2)
    // 3 -> 1 dato		3 -> 1 objeto ( dato1, dato2)
    
     
    static int lane = 1, num = (elseifthen.length + pribegend.length + simbolos.length + 1), //ID o valor de num en el sistema
    		   correcto = 1;
    
    public static void main(String[] args) 
    {
		try{
			BufferedReader archivo = new BufferedReader(new FileReader(file));
			String linea = archivo.readLine();
			
			while (linea != null)
			{	
				String puntoComa = ";", espacio = " ";
				String eq = "=";
				
				if(linea.contains(puntoComa)) 
					linea = linea.replace(puntoComa, espacio + puntoComa + espacio);
				if(linea.contains(eq)) 
					linea = linea.replace(eq, espacio + eq + espacio);

				StringTokenizer tokenizer = new StringTokenizer(linea); 
			
				while(tokenizer.hasMoreTokens()) 
				{
					String token = tokenizer.nextToken(); 
					int type = -1;
				
					if(token.matches("[0-9]+")) {
						type = num; //para que nunca tome otro tipo que define la palabra reservada, id o num 9
					}
					
					for (int i = 0; i < elseifthen.length; i++) // 3
						if(token.equals(elseifthen[i])) // if, then, else
							type = i;					//  0,  1  ,   2
					
					for (int i = 0; i < pribegend.length; i++)  
						if(token.equals(pribegend[i]))		//print, begin, end
							type = i + (elseifthen.length);// 3, 		4, 	5
					//suma para que tipo no empiece desde 0 y abarque todos los tipos 
					
					for (int i = 0; i < simbolos.length; i++) 
						if(token.equals(simbolos[i]))	// = , ;
							type = i + (pribegend.length + elseifthen.length); //6 -> "=", 7 -> ";"
					
					if(type == -1) 
					{
						detalles.add("Error Léxico, Linea " + lane + ", nombre ** " + token + " **");
						correcto = -1;
					}
					System.out.println("token : " + token);
					System.out.println("type : " +  type + "\n"
									  +"renglon: "+ lane +"\n");
					
					infoTokens.add(new InfoToken(token, lane, type));

				}
				linea = archivo.readLine();
				lane++;
			}
		}catch(IOException e)
		{
			System.out.println("Error: "+e.getMessage());		
		}
		if(correcto == 1) 
		{
			System.out.println("Sin errores léxicos");			
		}
		else
		{
			for(int i = 0; i < detalles.size(); i++) 
			{ 
				System.out.println( detalles.get(i) );
			}
			System.exit(0);
		}
//-------------------------------------------------------Analizador Sintáctico--------------------------------------------------------
		Sintaxis sin = new Sintaxis(infoTokens, num);
	}
	
}