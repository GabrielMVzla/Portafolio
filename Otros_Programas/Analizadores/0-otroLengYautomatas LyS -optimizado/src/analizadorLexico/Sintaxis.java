package analizadorLexico;

import java.util.ArrayList;

public class Sintaxis {
	
	final int ifx = 0, thenx = 1, elsex = 2, printx = 3, beginx = 4,
			  endx = 5 , eqx = 6, semix = 7, numx; //numx toma el valor más adelante por si cambia su clave, esta se calcula con una operación
	
	public ArrayList<InfoToken> infoTokens;
	int tipo = 0, linea = 0, contador = 0;
	String token;
	
	public Sintaxis(ArrayList<InfoToken> infoTokens, int num) 
	{
		this.numx = num;
		this.infoTokens = infoTokens;
		
		if(infoTokens.size() == 0)
		{
			System.out.println("Archivo Vacío, escribe código.");
			System.exit(0);
		}
		tipo = infoTokens.get(0).getTipo();
		token = infoTokens.get(0).getValor();
		
		P();
	}
	public void advance()
	{
		if(infoTokens.size()-1 != contador) //infoTokens = 4 - 1 = 3, contador = 0, 1, 2, 3  4
		{
			contador++;
			tipo = infoTokens.get(contador).getTipo(); // then -> 1
			return;
    	}
		contador++; //importante para hacer un conteo si se han declarado más tokens de la cuenta
	}
	public void eat(int t) // t = 1
	{
        if(tipo == t) // 1 == 4? --> false
        {
        	advance();
        }
        else{
            error(t);
        }
	}
	public void P() 
	{
		//llama los statements
		if(tipo!=ifx && tipo!=beginx && tipo!=printx)
			error(99);
		S();
		System.out.println("contador = " + contador +", infoToken = " + infoTokens.size());
		if(contador < infoTokens.size())
		{
			System.out.println("Error en sintaxis, Hay tokens de más al final.");
			System.exit(0);
		}
		System.out.println("Ejecución correcta");

	}
	public void S() 
	{
		switch (tipo)
		{
			case printx: // 0 == 3? X
				eat(printx); E(); L();
				break;
			case ifx: // 0 == 0? sí
				eat(ifx);
				E(); 
				eat(thenx); S(); eat(elsex); S();
				break;
			case beginx:
				eat(beginx); S(); L();
				break;
			default: error(99); break;
		}
	}
	
	public void E() 
	{
		eat(numx); eat(eqx); eat(numx);
	}
	
	public void L() 
	{
		if(tipo == endx)
			eat(endx);
		else if(tipo == semix)
			eat(semix);
		else
			error(99);
	}
	public String TipoAvalor(int tipo) //tipo = 1
	{
		
		String 	elseifthen[] = {"if", "then", "else"},
				pribegend [] = {"print", "begin", "end"},
   			  	simbolos  [] = {"=", ";"};

	    if(tipo >= 0 && tipo < elseifthen.length) { // tipo 0, 1 o 2 		elseifthen = 3
	    	
	    	return elseifthen[tipo]; 
	    }													// 6
	    else if(tipo >= elseifthen.length && tipo < (elseifthen.length + pribegend.length)) //3, 4, 5
	    {
	    	return pribegend[(tipo - elseifthen.length)];

	    }	
	    else if(tipo >= (elseifthen.length + pribegend.length) && tipo < (elseifthen.length + pribegend.length + simbolos.length)) // 6, 7
	    {
	    	return simbolos[(tipo - (elseifthen.length + pribegend.length))];

	    }
	    if (tipo == (elseifthen.length + pribegend.length + simbolos.length + 1))
			return "num";
	    
	    return null;
	}
	
	private void error(int t) 
	{
		if(t == 99)
			System.out.println("Error Sintáctico\n"
					 + "El token ** " + infoTokens.get(contador).getValor() + " ** en "
					 + "Linea ** " + infoTokens.get(contador).getRenglon() + " ** no se esperaba");
		else
			System.out.println("Error Sintáctico\n"
					+ "El token ** " + infoTokens.get(contador).getValor() + " ** en "
					+ "Linea ** " + infoTokens.get(contador).getRenglon() + " ** no se esperaba,"
					+ "En lugar de ello se esperaba ** " + TipoAvalor(t) + " **");
		System.exit(0);
	}
}
