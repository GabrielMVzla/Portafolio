package lenguajes;

import java.util.ArrayList;

import arbol.*;

public class Sintactico {
	
			//Tokens esperados
	final char clase = 'z', print = 'f', semi = 'g',	num = 'p', decimal = 'o', EQ = '=', id = 'j', intx = 'k',
			floatx = 'l', pard = ')', pari = '(', brackab = '{', brackce = '}', bool = 'n', whilex = 'm',  suma = '+',
			resta = '-',   mult = '*', doing = 'q',  menor = '<', truex = 't', falso = 'r';
	
	int cont = 0, totalDos = 0; //asignar el siguiente token a la variable que recibe los tokens "tok"
	char tok;
	String token, tokenPuro;//token recibe las cadenas de tipos, esto "zjk" esto no "class ID int"
							//tokenPuro recibe el token en sí, esto no "zjk", esto "class Alexia1 int"
//	ArrayList<PosicionDoUntil> bytecodeDo = new ArrayList<PosicionDoUntil>();
//	ArrayList<PosicionDoUntil> bytecodeUntil = new ArrayList<PosicionDoUntil>();
	ArrayList<Integer> bytecodeDo = new ArrayList<Integer>();
	ArrayList<Integer> bytecodeUntil = new ArrayList<Integer>();
	
	
	ArrayList<Dx> declaraciones = new ArrayList<Dx>();
	ArrayList<Sx> estatutos 	= new ArrayList<Sx>();
	public int bytecode = 0, bytecodeuntil = 0;
	public int bytecodeON = 0;
	boolean esprint = false;
	Lista2<String> lista2;			 // esta lista guarda un dato, el token "clase, do, until, int, etc"
//	Lista2 declaraciones = new Lista2(), estatutos = new Lista2();
	Px p;
	public Sintactico(String s, Lista2<String> as)
	{
	    
		token = s; 				// guarda todos los tipos como String, cada tipo equivale a una letra
		lista2 = as;  			// guarda cada token
								// para poder manipular globalmente los tokens
//		System.out.println(token);
		
//		System.out.println("lista2.mostrar---------------");
//		lista2.mostrar();
		
		tok = token.charAt(0); 	//asigna a "tok" una variable tipo char, el primer caracter de la cadena "token"
		tokenPuro = lista2.BuscarxIndex(0);//serviá para tomar directamente el valor de un token, por si llega a ser numerico
		p =  P();
	}
	void advance ( ) //toma el siguiente valor
	{
		for (int i = cont; i  < cont+1 && i <token.length(); i++) //2
		{
			tok = token.charAt(i);
			tokenPuro = lista2.BuscarxIndex(i);
		}
	}
		
	void eat ( char tokenEsperado) //z
	{ 
		//System.out.println("valor de token = "+tok+", valor esperado = "+tokenEsperado);

		if ( tok == tokenEsperado)
		{
			cont++; //para que token tome el siguiente valor de la cadena
			advance (); 
		}
		else 
			error (tokenEsperado); 
			
	}
	
	Px P ( ) {
		eat(clase);
		eat(id);
			Dx d= D();
			declaraciones.add(d);
		eat(brackab);
			Sx s= S();
			estatutos.add(s);
		eat(brackce);
		
//		System.out.println("Todo correcto, no hay errores lexicos ni de sintaxis");
		
		return new Px(declaraciones,estatutos);
	}
	Dx D () {
		String token;
		
		switch (tok) {
			case intx: 
				
				eat(intx);
				token = tokenPuro;
				eat(id);
				eat(semi);
				while(tok == intx || tok == floatx || tok == bool  )
					declaraciones.add(D());
//				System.out.println("int correcto");
				return new Dx("int", token);
			case floatx:
				eat (floatx);
				token = tokenPuro;
				eat(id); 
				eat(semi);
				while(tok == intx || tok == floatx || tok == bool  )
					declaraciones.add(D());
//				System.out.println("float correcto");
				return new Dx("float", token);
			case bool:
				eat (bool);
				token = tokenPuro;
				eat(id); 
				eat(semi);
				while(tok == intx || tok == floatx || tok == bool  )
					declaraciones.add(D());

//				System.out.println("bool correcto");
				return new Dx("boolean", token);

			default: error(); 
			}
		return null;
	
	}
	Sx S ( ) {
		Ex e;
		String token;
		
		switch ( tok ) {
		case doing:
			esprint = false;
//			totalDos++;
//			bytecodeON++;
			//bytecode++ se queda sin bytecode?
			bytecodeDo.add(bytecode);//----almacena a donde se regresará la validación
//			bytecode++;
			eat(doing);
//				if(tok =='a' || tok == 'k' || tok == 'l' || tok == 'q')
				estatutos.add(S());
//				if(bytecodeON != 0)
//					bytecodeuntil++;
					
				bytecodeUntil.add(bytecodeuntil);//----almacena dónde irán los validación
			eat(whilex);
			eat(pari);
			e = E ( );	
			eat(pard);
			eat(semi);
//				System.out.println("while correcto");
			while(tok == doing || tok == print || tok == id)
				estatutos.add(S());
//			bytecodeON--;
			return new Dox(e);
		case print: 
			esprint = true; //si es print con esto no perjudicará el bytecode
//			if(bytecodeON != 0)
//				bytecodeuntil++;
			eat ( print );
			eat(pari);
			e = E( ); 
			eat(pard);
			eat(semi);
			while(tok == doing || tok == print || tok == id)
				estatutos.add(S());

			return new Printx(e);
		case id:
			esprint = false;
//			if(bytecodeON != 0)
//			{
				bytecode++;
				bytecodeuntil++;
//			}
			token = tokenPuro;
			eat(id);
			eat(EQ);
			e = E();
			eat(semi);
			while(tok == doing || tok == print || tok == id)
				estatutos.add(S());

			return new Asignacionx(token, e);	
		default: error();
		}
		return null;	
	}

	Ex E ( ) {

//		int simbol = 0;
		String operador = null;
		String token1, token2 = null;
		char nvoOperador;
		
		
		switch (this.tok) {
			case resta:
				eat(resta);
				token1 = tokenPuro;
				eat ( decimal );
				operador = tokenPuro;
				if(Simbols()) // si sí hay es símbolo el siguiente token y no un semi colon se realizará el comparaDecimalx
				{
					nvoOperador = tok;
					eat ( nvoOperador );
					token2 = tokenPuro;
					eat ( decimal );
//					if(bytecodeON != 0 && !esprint)
					if(!esprint)
					{
						bytecodeuntil+=3;
						bytecode+=3; //aumenta con solo entrar, aumentará 1 más si hay una suma
					}

				}
				else
				{
					if(!esprint) {
						
						bytecodeuntil++;
						bytecode++; 
					}
					return new ComparaDecimalx(resta, token1); 
				}
				
				return new ComparaDecimalx(resta, token1, operador, nvoOperador, token2); 
			case suma:
				eat(suma);
				token1 = tokenPuro;
				eat ( decimal );
				operador = tokenPuro;
				if(Simbols()) // si sí hay es símbolo el siguiente token y no un semi colon se realizará el comparaDecimalx
				{
					nvoOperador = tok;
					eat ( nvoOperador );
					token2 = tokenPuro;
 					eat ( decimal );
//					if(bytecodeON != 0 && !esprint)
					if(!esprint)

					{
						bytecodeuntil+=3;
						bytecode+=3; //aumenta con solo entrar, aumentará 1 más si hay una suma
					}

				}
				else
				{
					
					bytecodeuntil++;
					bytecode++; 
					return new ComparaDecimalx(suma, token1); 
				}
					
				return new ComparaDecimalx(suma, token1, operador, nvoOperador, token2); 
			case id: // En las asignaciones con id, puede ser id ( + | - | * | <) seguido de (entero | decimal | id)
				token1 = tokenPuro;
				eat( id);
				operador = tokenPuro;
				if(!Simbols())
				{
					bytecodeuntil++;
					bytecode++; 
					return new ComparaId(token1);
				}
//				if(Simbols()) // esta validación es solo para que el método eat(); avance y me arroje el siguiente token para
//				{			  // guardarlo en la variable --> token, ya que la negación no entraba en el eat() y no avanzaba la secuencia
					if(tok == suma)
					{
						nvoOperador = tok;
						eat(suma);
						token2 = tokenPuro;
						eat(decimal);
//						if(bytecodeON != 0 && !esprint)
						if(!esprint)

						{
							bytecodeuntil+=3;
							bytecode+=3; //aumenta con solo entrar, aumentará 1 más si hay una suma
						}
						return new ComparaDecimalx(token1, operador, nvoOperador, token2); 
					}
					else if(tok == resta)
					{
						nvoOperador = tok;
						eat(resta);
						token2 = tokenPuro;
						eat(decimal);
//						if(bytecodeON != 0 && !esprint)
						if(!esprint)

						{
							bytecodeuntil+=3;
							bytecode+=3; //aumenta con solo entrar, aumentará 1 más si hay una suma
						}
						return new ComparaDecimalx(token1, operador, nvoOperador, token2); 
					}
					else if(tok == num)
					{
						token2 = tokenPuro;
						eat(num);
//						if(bytecodeON != 0 && !esprint)
						if(!esprint)

						{
							bytecodeuntil+=3;
							bytecode+=3; //aumenta con solo entrar, aumentará 1 más si hay una suma
						}
						return new ComparaNum(token1, operador, token2);
					}
					
					else if(tok == id)
					{
						token2 = tokenPuro;
						eat (id);
//						if(bytecodeON != 0 && !esprint)
						if(!esprint)
						{
							bytecodeuntil+=3;
							bytecode+=3; //aumenta con solo entrar, aumentará 1 más si hay una suma
						}
						return new ComparaId(token1, operador, token2);
					}
					
//				}
			case truex:
				bytecodeuntil++;
				bytecode++; 
				eat(truex);
				return new Booleanx(true);
			case falso:
				bytecodeuntil++;
				bytecode++; 
				eat(falso);
				return new Booleanx(false);
			case num:
				token1 = tokenPuro;
				eat(num);
				operador = tokenPuro;
				if(Simbols()) // si sí hay es símbolo el siguiente token y no un semi colon se realizará el comparaDecimalx
				{
					token2 = tokenPuro;
 					eat ( num );
//					if(bytecodeON != 0 && !esprint)
					if(!esprint)
					{
						bytecodeuntil+=3;
						bytecode+=3; //aumenta con solo entrar, aumentará 1 más si hay una suma
					}
				}
				else {
					bytecodeuntil++;
					bytecode++; 
					return new ComparaNum(token1);}
				
				return new ComparaNum(token1, operador, token2); 
				
			default: error(); break;
		}
		return null;
	}
	void error(char e) {

		String en =  "\nSe esperaba este token **"+TiposDeErrores(e)+"** en lugar de \"" +tokenPuro+"\"" ;
			
		System.out.println(en);

		System.exit(0);
		}
	void error() {
		
	System.out.println("Error en la sintaxis");

		System.exit(0);
		}
	boolean Simbols() {
		
		if ( tok == '+' || tok == '*' || tok == '-' || tok == '<')
		{
			eat(tok);
			return true;
		}
		else return false;
	}
	String TiposDeErrores(char type) {
		String tipo = "";
		if(type == clase)	tipo = "clase";				if(type == print)	tipo = "System.in.readln";
		if(type == semi)	tipo = ";";					if(type == num)		tipo = "numérico";
		if(type == decimal)	tipo = "flotante";			if(type == EQ)		tipo = "=";
		if(type == id)		tipo = "identificador";		if(type == intx)	tipo = "int";
		if(type == floatx)	tipo = "float";				if(type == pard)	tipo = ")";
		if(type == pari)	tipo = "(";					if(type == brackab)	tipo = "{";
		if(type == brackce)	tipo = "}";					if(type == bool)	tipo = "boolean";
		if(type == whilex)	tipo = "until";				if(type == suma)	tipo = "+";
		if(type == resta)	tipo = "-";					if(type == mult)	tipo = "*";
		if(type == doing)	tipo = "do";				if(type == menor)	tipo = "<";
		if(type == truex)	tipo = "true";				if(type == falso)	tipo = "false";
		return tipo;
		
		
	}
}
