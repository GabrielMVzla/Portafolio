package analizador;
import java.io.*;
import java.util.*;

public class Lexico
{		
    boolean flag = true;
    ArrayList<String> VoF = new ArrayList<String>(), tokens = new ArrayList<String>();
    public ArrayList<Integer> tipos = new ArrayList<Integer>(), renglones = new ArrayList<Integer>(),
    		columnas = new ArrayList<Integer>(); 
    int ren = 1;
    int col = 1;
	
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
				for (String cadena : Arrays.asList("=", ";"))
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
			VoF.add("Sin errores lexicos"); 
	}
	public void tipifica(String token) 
	{
		int tipo = -1;
		//^ = inicio de palabra "expresión regular" / $ = fin de palabra / ? = indica 0 o 1 vez
		if(token.matches("^[a-zA-Z]+([a-zA-Z]*[0-9]*)*$"))
			tipo = -2;
		if(token.matches("([0-9])+"))
			tipo =8;
		String cadenas[] = {"else", "if", "then", "print", "begin", "end", "=", ";"};//8 tokens
		
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
		if(tipo == -2) 
		{
			VoF.add("Error de compilación en Léxico, el programa no acepta identificadores, reglón " + ren + " columna " + col + ", nombre ** " + token + " **");
			flag = false;
			return;
		}
		System.out.println(token);
		tokens.add(token);
		tipos.add(tipo);
		renglones.add(ren);
		columnas.add(col);
	}
}