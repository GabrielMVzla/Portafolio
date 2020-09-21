package analizador;
import java.io.*;
import java.util.*;

public class Lexico
{		
    boolean flag = true;
    ArrayList<String> VoF = new ArrayList<String>(), every_token = new ArrayList<String>();
    ArrayList<Integer> every_tipo = new ArrayList<Integer>(); 
    int ren = 1;
    int col = 1;
    ArrayList<Token> tokenRC = new ArrayList<Token>();
	
	public Lexico(String archivo) 
	{
		FileReader file;
		BufferedReader a;
		String oracion = null;
		StringTokenizer tokenizer;
		
		try{
			file = new FileReader(archivo);
			a = new BufferedReader(file);
			oracion = a.readLine();
			
			while (oracion != null)
			{
				for (String cadena : Arrays.asList("(", "{", "+", "<", "=", ";", "*", "-", "}", ")"))
				{
					if(oracion.contains(cadena)) 
						oracion = oracion.replace(cadena, " "+cadena+" ");
				}
				tokenizer = new StringTokenizer(oracion); 
				col = 0;
				while(tokenizer.hasMoreTokens()) 
				{				
					col++;
					tipifica(tokenizer.nextToken());
				}
				oracion = a.readLine();
				ren++;
			}
		}catch(IOException e)
		{
			System.out.println("Error: "+e.getMessage());		
		}
		if(flag) /*{*/
			VoF.add("Sin errores lexicos"); 	/*for(int i=0; i<every_token.size();i++) { System.out.println(every_token.get(i));	}}*/
	}
	public void tipifica(String token) 
	{
		int tipo = -1;
		if(token.matches("^[a-zA-Z\\d]+$"))
			tipo = 52;
		if(token.matches("^[0-9]+[.][0-9]+?$")) 
			tipo = 51;
		if(token.matches("^([0-9])+?$"))
			tipo =50;
		String cadenas[] = {"class","do","until","int","boolean","float","{","}", "=", ";","<", //11
							"+","-","*","true","false", "System.in.readln", "(",")"};			//8   total = 19, 0-18
		for (int i = 0; i < cadenas.length; i++) 
		{
			if(token.equals(cadenas[i]))
				tipo = i;
		}
		if(tipo == -1) 
		{
				VoF.add("Error de compilación en Léxico, reglón " + ren + " columna " + col + ", nombre ** " + token + " **");
				flag = false;
				return;
		}
		
		tokenRC.add(new Token(token, ren, col, tipo));
//		System.out.println("token : " + token + Ayudas.blancos(token.length()-10)+ " tipo : " + tipo);
//		every_token.add(token);
//		every_tipo.add(tipo);
	}
}