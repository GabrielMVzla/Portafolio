package analizador;

import java.util.ArrayList;

import arbol_Sintactico.*;

//import analizadorlexico.copy.Lista3;

public class Sintactico <T>{
	ArrayList<Token> tokenRC;

	ArrayList<String> token;
    ArrayList<Integer>tipo; 
    String tok = "", esperado = "";
    int type;
    String estructura = "";
    Px p;
    int contando = 0;
    public static int contando2 = 0;
	public static ArrayList<Integer> saveLineDo = new ArrayList<Integer>();
	public static int contadorSaveLineDo = 0;

	final int clase = 0, hacer = 1, until = 2, entero = 3, booleano = 4, flotante = 5,
			llaveizq = 6, llaveder = 7, EQ = 8, semi = 9, menor = 10, mas = 11, menos = 12,
			mult = 13, truex = 14, falsex = 15, print = 16, brackizq = 17, brackder = 18,
			num = 50, decimal = 51,  ID = 52; //bool = 21,

//	public Sintactico(ArrayList<String> token, ArrayList<Integer> tipo) 
	public Sintactico(ArrayList<Token> tokenRC) 
	{
		this.tokenRC = tokenRC;
//		this.token = token;
//		this.tipo = tipo; 
		try {			
			
			this.tok = this.tokenRC.get(0).getToken();
			this.type = this.tokenRC.get(0).getTipo();
//			this.type = this..get(0);
//			this.tok = this.token.get(0);
		} catch (Exception e) 
		{
			System.out.println("El archivo está vacío");	
			System.exit(0);
		}
		p = Programa();
	}	
		
	public void Advance() 
	{		
		type = tokenRC.get(contando).getTipo();	tok = tokenRC.get(contando).getToken();
	}		
	public void eat ( int esp )
	{ 
		if ( type == esp )// if( ')' == ';' )
		{
			contando++;
			if(contando < tokenRC.size())
				Advance ();  
		}
		else 
			error (esp); 
	} 

	ArrayList<Dx> dec = new ArrayList<Dx>();
	ArrayList<Sx> stat = new ArrayList<Sx>();
	
	public Px Programa ( ) {
	
		eat(clase);
		eat(ID);
		
		while(type == entero || type == flotante || type == booleano )		
		 dec.add(Declaracion());
		
		eat(llaveizq);
		
		while(type != llaveder ) 
		 stat.add(Statuto());
		
		eat(llaveder);
//		System.out.println((tokenRC.size()) + " contador = " + contando);
		if(contando < tokenRC.size())
			error(1);
		estructura = "estructura correcta";
//		System.out.println(estructura);
		return new Px(dec,stat);
	}
	
	public Dx Declaracion () {
		String tok;
		switch (type) {
			case entero: 
				eat(entero);   tok = this.tok;eat(ID);eat(semi);
				return new Dx("int", tok, "0");
			case flotante:
				eat (flotante);tok = this.tok;eat(ID);eat(semi);
				return new Dx("float", tok, "+0.0");
			case booleano:
				eat (booleano);tok = this.tok;eat(ID);eat(semi);
				return new Dx("boolean", tok, "false"); 
			default: error(); break;
			}
		return null;
	}
	int contador = 0;
	public Sx Statuto ( ) {
		contador = 0;
		String tok;
		Ex e; Sx s = null;
		String saveLineDo;
		
		switch ( type ) {
		case hacer:
			saveLineDo = this.tok;;
			eat(hacer);  
			while(type == hacer || type == print || type == ID  )  //para llamar otro statement dentro del statement
				stat.add(Statuto());
			
			eat(until);eat(brackizq);
			e = Exp ( );	eat (brackder);	eat(semi);
//			while(type == hacer || type == print || type == ID  )  //para llamar otro statement dentro del statement
//			{
//	
////				contador++;
//			}
			return new Dox(e, saveLineDo);
		case print: 
			eat ( print );eat(brackizq);e= Exp( );eat(brackder);eat(semi);
//			while(type == hacer || type == print || type == ID  ) Statuto();
			return new Printx(e);
		case ID:
			tok = this.tok;
			contando2 = contando;
			eat(ID);eat(EQ);	e = Exp();	eat(semi);
//			while(type == hacer || type == print || type == ID  ) Statuto();
			return new Asigna(tok, e, tokenRC, 1);
		default: error();
		}
		return null;	
	}
	public Ex Exp ( ) 
	{	
		Idx i1, i2;
		int simbol = 0;
		String tok, tok2, simbolo, signo;
		
	
		switch (type) {
			case ID: 
				tok = this.tok;eat( ID);
//				if(type == semi || type == brackder)
//					return new ComparaId(i1);
				signo = Simbols();
//					tok = this.tok;
//				System.out.println("Valor de signo " + signo);
				if(type == menos)	simbol = menos;
				else				simbol = mas;
				
				if(type == ID)
				{
//					i2=new Idx(tok);
					tok2 = this.tok;

					eat (ID) ;
					return new ComparaId(tok, tok2, signo);
				}
				if(type == truex)
				{
					eat (truex) ;
					return new Booleanx(tok, true, signo);

				}
				if(type == falsex)
				{
					eat (falsex) ;
					return new Booleanx(tok, false, signo);
				}
				if(type == num)
				{
					tok2 = this.tok;
					eat (num) ;
					return new ComparaNum(tok, Integer.parseInt(tok2), signo);
				}
				if(type == mas)
				{
					simbolo = this.tok;
					eat(simbol);
					tok2 = this.tok;	
					eat(decimal);
					return new Decimalx(tok, simbolo, tok2, signo);
				}
				if(type == menos)
				{
					simbolo = this.tok;
					eat(simbol);	
					tok2 = this.tok;
					eat(decimal);
					return new Decimalx(tok, simbolo, tok2, signo);			
				}
	
			default: error(); break;
		}
		return null;
	}
	public void error(int type)
	{
		String tipo = ValoresInversos(type);
		if(type == 0) 
			tipo = "\nSe esperaba una expresión **class**";
		else if(type == 1)
			tipo = "\nError en los límites, se encontró al menos un token después de la llave cerrada ** " + tok + " **";
		else 
			tipo = "\nSe esperaba este token ** "+tipo+" ** en lugar o al lado de ** " +tok+" **";
		
		System.out.println(tipo);

		System.exit(0);
	}
	
	public void error() 
	{	
		System.out.println("Error en la sintaxis, con el siguiente token ** "+tok+" **");System.exit(0);	
	}
	
	public String Simbols() {
		if (type == menor || type == menos || type == mas || type == mult/*type == mayor || type == dobleEQ ||*/ )
		{
			String tok = this.tok;
			eat(type);
			return tok;
		}
		else 
			error();
		return null;
	}
	
	public String ValoresInversos(int type)
	{
		String devuelve,cadenas[] = {"class","do","until","int","boolean","float","{","}", //10
				"=", ";","<","+","-","*","true","false", "print", "(", ")"};			  //11
		if(type == 50) 
			return devuelve = "numérico";
		if(type == 51) 
			return devuelve = "decimal";
		if(type == 52) 
			return devuelve = "identificador";
		devuelve = cadenas[type];
		
		return  devuelve;
	}
	public String IdSemi() {
		String tok = this.tok;
		eat(ID);
		eat(semi);
		return tok;
	}
}