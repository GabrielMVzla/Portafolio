package lenguajes ;

import java.io.*;
import java.util.*;
import java.util.regex.*;


public class Analizador{
		
    int renglon=1,columna=1, col2 = 0;
    static ArrayList<String> resultado; 

    Lista2 todos_tokens;
    String all_tokens = "", lineag = "";
	boolean bandera=true, oband = true;//por si no hay errores desplegar lo siguiente
	public Analizador(String ruta) {
		analizaCodigo(ruta);
		if(bandera) {
			resultado.add("No se encontraron errores lexicos");	
		}
		
		
		
	}
//	public String RegresaTokens() {//devuelve la cadena zjkjg
//		return all_tokens;
//	}
//	public Lista2 TodosTokens() {
////		todos_tokens.mostrar();
//		return todos_tokens; //devuelve cada token ejem class alexia 1
//	}
	public void analizaCodigo(String ruta) {
		String linea="", token="";
		StringTokenizer tokenizer;
		
		try{
			FileReader file = new FileReader(ruta);					//lee archivo
			BufferedReader archivoEntrada = new BufferedReader(file);//tambien lee archivo
			linea = archivoEntrada.readLine();						//lo divide en lineas
			resultado=new ArrayList<String>();
			todos_tokens = new Lista2();
				
			while (linea != null){
				col2 = 0;
				linea = espacios(linea); //separa la linea de cada token
				tokenizer = new StringTokenizer(linea); //guarda la linea ya separada
				while(tokenizer.hasMoreTokens()) { //desmonta cada token 1 x vuelta
					col2++;
					//DENTRO DE ESTE WHILE, EN EL METODO analizadorLexico SE MANDA CADA TOKEN, CADA PALABRA
					token = tokenizer.nextToken();
//					all_tokens = token;
					if(col2 != 0)
						columna=col2;
					else
						columna = 1;
					analizadorLexico(token);
				}
				linea=archivoEntrada.readLine();
				renglon++;
			}
			archivoEntrada.close();
		}catch(IOException e) {
			
			System.out.print("No se encontro el archivo" );
		}
	}
	public void analizadorLexico(String token) {

		String tipo  ="v";

		if(Verifica("true", token))
			tipo ="t";
		if(Verifica("false", token)) 
			tipo ="r";
		if(Verifica("System.in.readln", token)) 
			tipo ="f";
		if(Verifica("do", token)) 
			tipo ="q";
		if(Verifica("until", token)) 
			tipo ="m";
		if(Verifica("<", token)) 
			tipo ="<";
		if(Verifica("+", token)) 
			tipo ="+";
		if(Verifica("-", token)) 
			tipo ="-";
		if(Verifica("*", token)) 
			tipo ="*";
		if(Verifica("(", token)) 
			tipo ="(";
		if(Verifica(")", token)) 
			tipo =")";
		if(Verifica("{", token)) 
			tipo ="{";
		if(Verifica("}", token)) 
			tipo ="}";
		if(Verifica("class", token)) 
			tipo ="z";
		if(Verifica(";", token)) 
			tipo ="g";
		if(Verifica("int", token)) 
			tipo ="k";
		if(Verifica("float", token)) 
			tipo ="l";
		if(Verifica("until", token)) 
			tipo ="m";
		if(Verifica("boolean", token)) 
			tipo ="n";
		if(Verifica("=", token)) 
			tipo ="=";
		if(Pattern.matches("^\\d+$",token)) 
			tipo ="p";
		if(Pattern.matches("^\\d.+$",token)) {
				int punto = 0;
				for (int i = 0; i < token.length(); i++) { // 4.5.6
					if(token.charAt(i) == '.')//para no tener más de 2 puntos en el float
						punto++;
					if(punto == 1)
						tipo = "o";
					if(punto > 1) {
						Error(4,token); //para no tener más de 2 puntos en el float
						break;
					}
					if(!(token.charAt(i) >= '0' && token.charAt(i) <='9') && token.charAt(i) !='.') 
						Error(3,token);
						
				}

		}
		if(tipo=="v") {	//id
//			System.out.println("entró");
//			if(!((token.charAt(0) >= 'a' && token.charAt(0) <= 'z') 
//					|| (token.charAt(0) >= 'A' && token.charAt(0) <= 'Z')))
//			{
//				Error(2, token);
//				bandera = false;
//				return;
//			}
			Pattern pat = Pattern.compile("^[a-zA-Z_\\d]+$"); //esta parte y la de arriba permmite que los 
														//"id" comiencen con letra y no tengan simbolos especiales
			Matcher mat = pat.matcher(token);
			if(mat.find()) 
				tipo ="j";
			else {
				Error(1, token);
				bandera = false;
				return;
			}
		}
		
		todos_tokens.insertar(token);
//		System.out.println("token a = " + token);
		all_tokens += tipo;		
	}
	
	public String espacios(String linea){
		for (String string : Arrays.asList("(",")","{","}","=",";","*","-","+", "<")) {

			if(linea.contains(string)) 
				linea = linea.replace(string, " "+string+" ");
		}
		return linea;
	}
	
	public void Error(int n, String token) {
		String rxc = "Error en la linea "+renglon+", token número "+columna+", token ** "+token+" **";
		if(n == 1)
			resultado.add(rxc);
		if ( n == 2)
		{
			resultado.add("identificador debe inicializar con una letra\n"+rxc);
		}
		if(n == 3)
			resultado.add("lo valores númericos no deben llevar letras"
					+ "\n"+rxc);
		if(n == 4)
			resultado.add("valor numérico con 2 puntos"
					+ "\n"+rxc);
							
		if(n == 5)
			resultado.add("Operador solo"
					+ "\n"+rxc);
		bandera = false;
		
	}
	public boolean Verifica(String ver, String token) {
		
		return Arrays.asList(ver).contains(token);
		
	}
}