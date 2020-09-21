package analizador;

import java.util.ArrayList;

import arbol_Sintactico.*;

public class SemanticoArbol {

	Dox dox;
	Asigna asigna;
	Printx print;
	ComparaNum comparaNum;
	ComparaId comparaId;
	Decimalx decimal;
	Booleanx booleano;
	ArrayList<String> cadaVariableNoRepeat = new ArrayList<String>(), cadaVariable = new ArrayList<String>(),
			variableNoexiste = new ArrayList<String>(), variableSiExiste = new ArrayList<String>();
	ArrayList<Asigna> asignaciones = new ArrayList<Asigna>();
//	ArrayList<Integer> saveLineDo = new ArrayList<Integer>();
	Px program;
	String tipo = "";
	ArrayList<Token> tokens;
	ArrayList<Tabla> tabla = new ArrayList<Tabla>();
	int error = 0;
	
	public SemanticoArbol(Px p, ArrayList<Token> tokenRC) {
		
		
		tokens = tokenRC;
		program = p;
		
		System.out.println();
//		Detecta las variables repetidas y deja solo la primera declarada de esa misma variable
		for (int i = 0; i < p.d.size(); i++) 
		{
			String temp2 = p.d.get(i).id;
			for (int j = p.d.size()-1; j >= 0; j--) {
				if(i != j && temp2.equals(p.d.get(j).id)) {
					System.out.println("** " + temp2 + Ayudas.blancos(temp2.length()) + "** Variable repetida, se mostrará en la tabla solo la primera que fue declarada." );
					p.d.remove(j);
				}
			}
//			System.out.println(( i + 1 ) + Ayudas.blancos(String.valueOf(i + 1).length()+5) + temp1 +
//					Ayudas.blancos(temp1.length()) + temp2 + Ayudas.blancos(temp2.length()-5) + valor);
		}

	 //ingreso cada identificador a el arraylist cadaVariable para el sig for
		for (int i = 0; i < p.s.size(); i++)
		{
			if(p.s.get(i) instanceof Dox)
			{
				dox = (Dox) p.s.get(i);
				Instancias(i, dox.e);
			}
			if(p.s.get(i) instanceof Asigna)
			{
				asigna = (Asigna) p.s.get(i);
				Instancias(i, asigna.e);
			}
			if(p.s.get(i) instanceof Printx)
			{
				print = (Printx) p.s.get(i);
				Instancias(i, print.e);
			}
		}
		//Deshecho variables repetidas, pero la idea de esto es más adelante validar las variables no declaradas
		for (int i = 0; i < cadaVariable.size(); i++) 
		{
			for (int j = cadaVariable.size()-1; j >= 0 ; j--)
			{
				if(i != j) 
					if(cadaVariable.get(i).equals(cadaVariable.get(j))) 
//						System.out.println("se repite " + cadaVariable.get(i) + " con " + cadaVariable.get(j));
						cadaVariable.remove(j);
					
			}
		}
	
		//Comparo variables declaradas con las que hay en el código que no fueron declaradas
		int cont = 0;
		String temp = "";
		for (int i = 0; i < cadaVariable.size(); i++) 
		{
			cont = 0;
			for (int j = 0; j < program.d.size(); j++) {
				
				
				temp = program.d.get(j).id;
				if(!cadaVariable.get(i).equals(temp))
				{	
//					System.out.println(cadaVariable.get(i) + " = " + temp);
					cont++;
				}
			}
			if(cont == program.d.size()) //Para las que sí fueron declaradas, cont llegará a la cantidad de declaraciones
			{
				variableNoexiste.add(cadaVariable.get(i));
				System.out.println( "** " + cadaVariable.get(i) +  Ayudas.blancos(cadaVariable.get(i).length()) +"** Variable NO declarada anteriormente." );
//				cadaVariable.remove(i);
			}
		}

		//Dejo las asignaciones las cuales sí fueron declaradas, y si no existe no se le asigna nada a tal variable
		//asignaciones contiene los datos de la asignacion id = exp1 signo exp2, reutilizo la clase asigna ya usada 
		//en el sintactico, pero los valores están dentro de otro array
		String valorCompleto = "0", noCoinciden = "", noCoincideValor = "", declaracionAct = "", operadorLogic = "";		
		int contando = 0, flag = 0 ;
		boolean bool = false;
		for (int i = 0; i < asignaciones.size(); i++) {
			flag = 0;
			noCoinciden = "";
			for (int j = 0; j < program.d.size(); j++) {

				declaracionAct = program.d.get(j).id;
				
				try {
					if(asignaciones.get(i).e instanceof ComparaNum)
					{
						comparaNum = (ComparaNum) asignaciones.get(i).e;
						
						//si tiene el mismo nombre pero no es tipo int entra y arroja el msj
						if(asignaciones.get(i).id.equals(program.d.get(j).id) && !program.d.get(j).t.equals("int"))
						{
							
							System.out.println("** " + asignaciones.get(i).id + Ayudas.blancos((asignaciones.get(i).id.length())) +
									"** NO es de tipo \"Entero\" para asignarle el valor \"" + comparaNum.e2 + "\"");
							error = 1;
						}
							
//						if(!NoExistencia(comparaNum.i1) && String.valueOf(comparaNum.e2).matches("^([0-9])+?$") 
//								&&  !program.d.get(j).t.equals("int"))
						//lo denego para confirmar que SI EXISTE, si no lo denegara estaría queriendo validar que el valor NO EXISTE
						//debe entrar al menos una vez si es tipo comparanum
						if(!NoExistencia(comparaNum.i1) && String.valueOf(comparaNum.e2).matches("^([0-9])+?$") 
								&&  program.d.get(j).t.equals("int"))
						{
							if(!comparaNum.signo.equals("<")) 
							{
								valorCompleto = comparaNum.i1 + " " + comparaNum.signo + " " + comparaNum.e2;							
							
								if(TipoEQ(asignaciones.get(i).id, comparaNum.i1)) {
//									System.out.println("falag1");
									flag = 1; 
								}
								else{
//									System.out.println("falag2");

									flag = 2;
									noCoinciden = "** " + asignaciones.get(i).id + " & " + comparaNum.i1 +
											Ayudas.blancos((asignaciones.get(i).id + " & " + comparaNum.i1).length())+ "** En la asignación para \"" + asignaciones.get(i).id + "\" ** ";
								}
							}
							else {
								flag = 3;

								operadorLogic = "Operador Lógico ** " +  comparaNum.i1 + " " + comparaNum.signo + " " + comparaNum.e2 +
										" ** en la asignación a " + asignaciones.get(i).id;
							}
								
						}
					}
				} catch (Exception e) {}
				try {
					if(asignaciones.get(i).e instanceof ComparaId)
					{
						comparaId = (ComparaId) asignaciones.get(i).e;
						//solo entrará si las 2 variables existen en la declaración inicial
						if(!NoExistencia(comparaId.e1) && !NoExistencia(comparaId.e2))
						{
							if(!comparaId.signo.equals("<")) {
								valorCompleto = comparaId.e1 + " " + comparaId.signo + " " + comparaId.e2;	
							
								bool = TipoEQ(asignaciones.get(i).id, comparaId.e1);
								
								
								if(bool && TipoEQ(asignaciones.get(i).id, comparaId.e2)) {
									flag = 1; 
	//								System.out.println("Los tipos de los enteros coinciden");
								}
								else if(!bool && !TipoEQ(asignaciones.get(i).id, comparaId.e2)){
									flag = 2;
									noCoinciden = "** " + asignaciones.get(i).id + " & " + comparaId.e1 + ""
											+ Ayudas.blancos((asignaciones.get(i).id + " & " + comparaId.e1).length())+ "** En la asignación para \"" + asignaciones.get(i).id + "\" ** y además,"
											+ "\n ** " + asignaciones.get(i).id + " & " + comparaId.e2 + ""
											+ Ayudas.blancos((asignaciones.get(i).id + " & " + comparaId.e2).length()+1)+ "** ";
	;
								}
								else if(!bool)
								{
									flag = 2;
									noCoinciden = "** " + asignaciones.get(i).id + " & " + comparaId.e1 + ""
											+ Ayudas.blancos((asignaciones.get(i).id + " & " + comparaId.e1).length())+ "En la asignación para \"" + asignaciones.get(i).id + "\" ** ";
	;
								}
								else if(!TipoEQ(asignaciones.get(i).id, comparaId.e2)) {
									flag = 2;
								noCoinciden = "** " + asignaciones.get(i).id + " & " + comparaId.e2 + ""
										+ Ayudas.blancos((asignaciones.get(i).id + " & " + comparaId.e2).length())+ "En la asignación para \"" + asignaciones.get(i).id + "\" ** ";
	;
	
								}
							}else 
							{
								if(!bool && !TipoEQ(asignaciones.get(i).id, comparaId.e2)){
								
								flag = 3;
								operadorLogic = "Operador Lógico ** " +  comparaId.e1 + " " + comparaId.signo + " " + comparaId.e2 +
										" ** en la asignación a " + asignaciones.get(i).id;
;
								}
								else if(!bool)
								{
									flag = 3;
									operadorLogic = "Operador Lógico ** " +  comparaId.e1 + " " + comparaId.signo + " " + comparaId.e2 +
											" ** en la asignación a " + asignaciones.get(i).id;

								}
								else if(!TipoEQ(asignaciones.get(i).id, comparaId.e2)) {
									flag = 3;
									operadorLogic = "Operador Lógico ** " +  comparaId.e1 + " " + comparaId.signo + " " + comparaId.e2 +
											" ** en la asignación a " + asignaciones.get(i).id;
	
								}
								
							}
						}
					}
				} catch (Exception e) {}
				
				try {
					if(asignaciones.get(i).e instanceof Decimalx)
					{
						decimal = (Decimalx) asignaciones.get(i).e;
						
						if(asignaciones.get(i).id.equals(program.d.get(j).id) && !program.d.get(j).t.equals("float"))
						{
							
							System.out.println("** " + asignaciones.get(i).id + Ayudas.blancos((asignaciones.get(i).id.length())) +
									"** NO es de tipo \"Flotante\" para asignarle el valor \"" + decimal.decimal1 + "\"");
							error = 1;
						}
						
						if(!NoExistencia(decimal.i) && (String.valueOf(decimal.decimal1).matches("^[+][+0-9]+[.][0-9]+?$") 
								|| String.valueOf(decimal.decimal1).matches("^[-][0-9]+[.][0-9]+?$" ))
								&&  program.d.get(j).t.equals("float"))
						{
//							System.out.println("entra al flotante " + decimal.decimal1);
							if(!decimal.signo.equals("<")) {
								valorCompleto = decimal.i + " " + decimal.signo + " " + decimal.decimal1;
							
							//Este if es para validar que los tipos sean iguales (ej. int con int)
							if(TipoEQ(asignaciones.get(i).id, decimal.i)) {
								flag = 1; 
							}
							else{
							
								flag = 2;
								noCoinciden = "** " + asignaciones.get(i).id + " & " + decimal.i + "En la asignación para \"" + asignaciones.get(i).id + "\" "
										+ Ayudas.blancos((asignaciones.get(i).id + " & " + decimal.i).length())+ "** ";
;
							}
							}
							else {
								flag = 3;
								
//								operadorLogic = "operador Lógico en " + decimal.i + " " + comparaNum.signo + " " + decimal.decimal1;
								operadorLogic = "Operador Lógico ** " +  decimal.i + " " + comparaNum.signo + " " + decimal.decimal1 +
										" ** en la asignación a " + asignaciones.get(i).id;
							}
						}
					}
				} catch (Exception e) {}
				
				try {
					if(asignaciones.get(i).e instanceof Booleanx)
					{
						booleano = (Booleanx) asignaciones.get(i).e;
					

						if(asignaciones.get(i).id.equals(program.d.get(j).id) && !program.d.get(j).t.equals("boolean"))
						{
							
							System.out.println("** " + asignaciones.get(i).id + Ayudas.blancos((asignaciones.get(i).id.length())) +
									"** NO es de tipo \"Flotante\" para asignarle el valor \"" + booleano.TF0 + "\"");
							error = 1;
						}
						
						
						if(!NoExistencia(booleano.id) && (booleano.TF0 == false || booleano.TF0 == true) 
//								&&  tipo.equals("boolean"))
							&&  program.d.get(j).t.equals("boolean"))
						{
							if(!booleano.signo.equals("<")) {
								valorCompleto = booleano.id + " " + booleano.signo + " " + booleano.TF0;
								if(TipoEQ(asignaciones.get(i).id, booleano.id)) {
									if(tipo.equals("boolean")) 
										flag = 1; 
								}else{
									flag = 2;
									noCoinciden = "** " + asignaciones.get(i).id + " & " + booleano.id + "En la asignación para \"" + asignaciones.get(i).id + "\" "
											+ Ayudas.blancos((asignaciones.get(i).id + " & " + booleano.id).length())+ "** ";
								}
							}else {
								flag = 3;

								operadorLogic = "Operador Lógico ** " +  booleano.id + " " + booleano.signo + " " +
								booleano.TF0 + " ** en la asignación a " + asignaciones.get(i).id;
							}
						}
						
					}
				} catch (Exception e) {}
				
				if(asignaciones.get(i).id.equals(program.d.get(j).id) && (flag == 1 || flag == 2 || flag == 3 ))
				{
					
					if(flag == 3)
					{
						error = 1;
						System.out.println(operadorLogic);
					}
					if(flag == 2)
					{
						error = 1;
						System.out.println(noCoinciden + ", NO coinciden ");
					}
					else
						program.d.get(j).valor = valorCompleto;
//						System.out.println("valorId = " + program.d.get(j).id + ", valorC = " + valorCompleto);
				}else {
					
					if(flag == 0 )
					{
						if(program.d.get(j).t.equals("boolean"))
							valorCompleto = "false";
						if(program.d.get(j).t.equals("float"))
							valorCompleto = "0.0";
						if(program.d.get(j).t.equals("int"))
							valorCompleto = "0";
					}
				}
			}
//			System.out.println("asig " + ( i + 1 ) + " " + asignaciones.get(i));
		}
		
		
		for (int k = 0; k < p.d.size(); k++) 
		{
			variableSiExiste.add(p.d.get(k).id);
//			System.out.println("variableSiExiste = " + variableSiExiste.get(k));
		}
//		int salvaI = -1;
//		String salvaValor = "";
//		int count = 0;
//		for (int i = 0; i < variableSiExiste.size(); i++) 
//		{
//			for (int j = 0; j < variableSiExiste.size(); j++)
//			{
//				if(i != j) 
//					if(variableSiExiste.get(i).equals(variableSiExiste.get(j)) && count == 0)
//					{
////						System.out.println("valor de i = " + i + ", valor de j = " + j);
//						count = 1;
//						salvaI = i;
//						salvaValor = variableSiExiste.get(i);
//					}
//			}
//		}
//		//si no lo hago así se buggea, puede que estén "a" en el indice 3 y 4, al momento de borrar 3, la lista
//		//se recorrerá, entonces el que era el indice 4 pasará a ser el indice 3 y a su vez "i" avanzará
//		//NT: pude haber reducido el indice en i--
//		for (int i = variableSiExiste.size()-1; i >= 0; i--) {
//			if(i != salvaI) {
//				if(salvaValor.equals(variableSiExiste.get(i)))
//				{
////					System.out.println("valor de i = " + i + ", valor de salvaValor = " + salvaValor); 
//					variableSiExiste.remove(i);
//				}
//				
//			}
//		}
//		
		
		ExpresionesDo();
		ExpresionesPrint();
		Intermedio();
		if(error == 0)
			for (int i = 0; i < tabla.size(); i++) {
				System.out.println(	   tabla.get(i).i	+ Ayudas.blancos(String.valueOf(tabla.get(i).i).length())
				+ tabla.get(i).signo	+ Ayudas.blancos(tabla.get(i).signo.length())
				+ tabla.get(i).id1	+ Ayudas.blancos(tabla.get(i).id1.length())
				+ tabla.get(i).e2s	+ Ayudas.blancos(String.valueOf(tabla.get(i).e2s).length())
	);			
			}

		
		System.out.println("\nNo." + Ayudas.blancos("No.".length()+5) + "Tipo" + Ayudas.blancos("Tipo".length()) + 
				"Nombre" + Ayudas.blancos("Nombre".length()-5) + "Valor");
		for (int i = 0; i < p.d.size(); i++) 
		{
			String temp1 = p.d.get(i).t;
			String temp2 = p.d.get(i).id;
			String valor = p.d.get(i).valor;
		
			System.out.println(( i + 1 ) + Ayudas.blancos(String.valueOf(i + 1).length()+5) + temp1 +
					Ayudas.blancos(temp1.length()) + temp2 + Ayudas.blancos(temp2.length()-5) + valor);
		}
	}
	
	private boolean TipoEQ(String tipo1, String tipo2) {
		for (int i = 0; i < program.d.size(); i++) 
		{
			//a - a,b,c    b - a,b,c    c - a,b,c
			if(program.d.get(i).id.equals(tipo1))
			for (int j = 0; j < program.d.size(); j++) 
			{
					if( program.d.get(j).id.equals(tipo2))
//						if(i != j)
						if( program.d.get(j).t.equals( program.d.get(i).t)) {
							tipo = program.d.get(j).t;
//							tipo = " es tipo " + tipo;
							return true;	
						}
			}
		}
		
		return false;
		
	}
	
	private boolean NoExistencia(String cadena) {
		
		for (int j = 0; j < variableNoexiste.size(); j++) 
			if(variableNoexiste.get(j).equals(cadena)) 
				return true;

		return false;
		
	}
	private boolean Existencia(String cadena) {
		
		for (int j = 0; j < variableSiExiste.size(); j++) 
			if(variableSiExiste.get(j).equals(cadena)) 
				return true;
		
		return false;
		
	}
	
	private void Instancias(int i, Object obj) {
		try {
			
			if(obj == dox.e && obj instanceof ComparaNum)
			{
				comparaNum = (ComparaNum) obj;
//				System.out.println(( i + 1 ) + " " + comparaNum.i1 + " " + comparaNum.signo + " " + comparaNum.e2 );
				cadaVariable.add(comparaNum.i1);
				
		
			}
			if(obj == dox.e && obj instanceof ComparaId)
			{
				comparaId = (ComparaId) obj;
//				System.out.println(( i + 1 ) + " " + comparaId.e1  + " " + comparaId.signo + " " + comparaId.e2 );
				cadaVariable.add(comparaId.e1);
				cadaVariable.add(comparaId.e2);
			}
			if(obj == dox.e && obj instanceof Decimalx)
			{
				decimal = (Decimalx) obj;
//				System.out.println(( i + 1 ) + " " + decimal.i + " " + decimal.signo + " " + decimal.decimal1 );
				cadaVariable.add(decimal.i);
			}
			if(obj == dox.e && obj instanceof Booleanx)
			{
				booleano = (Booleanx) obj;
//				System.out.println(( i + 1 ) + " " + booleano.id + " " + booleano.signo + " " + booleano.TF0 );
				cadaVariable.add(booleano.id);
			}
		} catch (Exception e) {}
		try {
			
			if(obj == print.e && obj instanceof ComparaNum)
			{
				comparaNum = (ComparaNum) obj;
//				System.out.println(( i + 1 ) + " " + comparaNum.i1 + " " + comparaNum.signo + " " + comparaNum.e2  );
				cadaVariable.add(comparaNum.i1);
//				cadaVariable.add(comparaNum.e2);
			}
			if(obj == print.e && obj instanceof ComparaId)
			{
				comparaId = (ComparaId) obj;
//				System.out.println(( i + 1 ) + " " + comparaId.e1 + " " + comparaId.signo + " " + comparaId.e2  );
				cadaVariable.add(comparaId.e1);
				cadaVariable.add(comparaId.e2);
			}
			if(obj == print.e && obj instanceof Decimalx)
			{
				decimal = (Decimalx) obj;
//				System.out.println(( i + 1 ) + " " + decimal.i + " " + decimal.signo + " " + decimal.decimal1  );
				cadaVariable.add(decimal.i);
//				cadaVariable.add(comparaId.e2);
			}
			if(obj == print.e && obj instanceof Booleanx)
			{
				booleano = (Booleanx) obj;
//				System.out.println(( i + 1 ) + " " + booleano.id + " " + booleano.signo + " " + booleano.TF0  );
				cadaVariable.add(booleano.id);

			}
			
		} catch (Exception e) {}
		try {
			
			if(obj == asigna.e && obj instanceof ComparaNum)
			{
				comparaNum = (ComparaNum) obj;
//				System.out.println(( i + 1 ) + " " + comparaNum.i1 + " " + comparaNum.signo + " " + comparaNum.e2 + " = " + asigna.id );
				cadaVariable.add(asigna.id);
				cadaVariable.add(comparaNum.i1);
				
				Ex e = (Ex) obj;
				asignaciones.add( new Asigna (asigna.id, (e), tokens, 0));
			}
			if(obj == asigna.e && obj instanceof ComparaId)
			{
				comparaId = (ComparaId) obj;
//				System.out.println(( i + 1 ) + " " + comparaId.e1 + " " + comparaId.signo + " " + comparaId.e2 + " = " + asigna.id );
				cadaVariable.add(asigna.id);
				cadaVariable.add(comparaId.e1);
				cadaVariable.add(comparaId.e2);
				
				Ex e = (Ex) obj;
				asignaciones.add( new Asigna (asigna.id, (e), tokens, 0));

			}
			if(obj == asigna.e && obj instanceof Decimalx)
			{
				decimal = (Decimalx) obj;
//				System.out.println(( i + 1 ) + " " + decimal.i + " " + decimal.signo + " " + decimal.decimal1 + " = " + asigna.id );
				cadaVariable.add(asigna.id);
				cadaVariable.add(decimal.i);
				
				Ex e = (Ex) obj;
				asignaciones.add( new Asigna (asigna.id, (e), tokens, 0));
			}
			if(obj == asigna.e && obj instanceof Booleanx)
			{
				booleano = (Booleanx) obj;
//				System.out.println(( i + 1 ) + " " + booleano.id + " " + booleano.signo + " " + booleano.TF0 + " = " + asigna.id );
				cadaVariable.add(asigna.id);
				cadaVariable.add(booleano.id);
				
				Ex e = (Ex) obj;
				asignaciones.add( new Asigna (asigna.id, (e), tokens, 0));
				}
		} catch (Exception e) {}
	
	}
	private void ExpresionesPrint() {
		
		int check = 0;
		int checka = 0;
		String funca = "";
		for (int i = 0; i < program.s.size(); i++)
		{
			check = 0;
			checka = 0;
		
			if(program.s.get(i) instanceof Printx)
			{
				print = (Printx) program.s.get(i);
				for (int j = 0; j < program.d.size(); j++) {
						
					try {
						
						if(print.e instanceof ComparaNum)
						{
							comparaNum = (ComparaNum) print.e;	
							
							if(comparaNum.signo.equals("<")) 
								checka++;
							if(checka == program.d.size())
							{
								error = 1;
								System.out.println("no se esperaba una comparación en System.in.realn ( " + comparaNum.i1 + " " + comparaNum.signo + " " + comparaNum.e2+ " );");
							}
							
							
							if(Existencia(comparaNum.i1) && comparaNum.i1.equals(program.d.get(j).id) && program.d.get(j).t.equals("int") )
							{
								check = 0;
							}
							else {
								check++;
								funca = "** " +  comparaNum.i1 + " & " + comparaNum.e2 + " ** NO son del mismo tipo, se esperaba a \"" + comparaNum.i1 + "\" como tipo ENTERO" ;
							}
						}
						if(print.e instanceof ComparaId)
						{
							comparaId = (ComparaId) print.e;
							
							if(comparaId.signo.equals("<")) 
								checka++;
							if(checka == program.d.size())
							{
								error = 1;

								System.out.println("no se esperaba una comparación en System.in.realn ( " + comparaId.e1 + " " + comparaId.signo + " " + comparaId.e2+ " );");
							}
							
							if(TipoEQ(comparaId.e1, comparaId.e2) && Existencia(comparaId.e1)&& Existencia(comparaId.e2))
							{
								check = 0;
							}
							else {
								check++;
								funca = "** " +  comparaId.e1 + " & " + comparaId.e2 + " ** NO son del mismo tipo";
							}
						}
						if(print.e instanceof Decimalx)
						{
							decimal = (Decimalx) print.e;
							
							if(decimal.signo.equals("<")) 
								checka++;
							if(checka == program.d.size())
							{
								error = 1;

								System.out.println("no se esperaba una comparación en System.in.realn ( " + decimal.i + " " + decimal.signo + " " + decimal.decimal1+ " );");
							}
							
							if(Existencia(decimal.i) && decimal.i.equals(program.d.get(j).id) && program.d.get(j).t.equals("float") )
							{
								check = 0;
							}
							else {
								check++;
								funca = "** " +  decimal.i + " & " + decimal.decimal1 + " ** NO son del mismo tipo, se esperaba a \"" + decimal.i + "\" como tipo FLOTANTE" ;
							}
						}
						if(print.e instanceof Booleanx)
						{
							booleano = (Booleanx) print.e;
							
							if(booleano.signo.equals("<")) 
								checka++;
							if(checka == program.d.size()) {
								error = 1;
								System.out.println("no se esperaba una comparación en System.in.realn ( " + booleano.id + " " + booleano.signo + " " + booleano.TF0+ " );");
							}
							
							
								if(Existencia(booleano.id) && booleano.id.equals(program.d.get(j).id) && program.d.get(j).t.equals("boolean") )
								{
									check = 0;
								}
								else {
									check++;
									funca = "** " +  booleano.id + " & " + booleano.TF0 + " ** NO son del mismo tipo, se esperaba a \"" + booleano.id + "\" como tipo BOOLEANO" ;
								}

						}
					} catch (Exception e) {}
					if(check == program.d.size()) 
					{
						System.out.println(funca);
						error = 1;
					}
				}
			}
			
		}
		
	}
	private void ExpresionesDo() {

		int check = 0;
		int checka = 0;
		String funca = "";
		for (int i = 0; i < program.s.size(); i++)
		{
			check = 0;
			checka = 0;
		
			if(program.s.get(i) instanceof Dox)
			{
				dox = (Dox) program.s.get(i);

				for (int j = 0; j < program.d.size(); j++) {
						
					try {

						if(dox.e instanceof ComparaNum)
						{

							comparaNum = (ComparaNum) dox.e;
							
							if(!comparaNum.signo.equals("<")) 
								checka++;
							if(checka == program.d.size())
							{
								error = 1;
								System.out.println("no se esperaba una comparación en Until ( " + comparaNum.i1 + " " + comparaNum.signo + " " + comparaNum.e2+ " );");
							}
							
							if(Existencia(comparaNum.i1) && comparaNum.i1.equals(program.d.get(j).id) && program.d.get(j).t.equals("int") )
							{
								check = 0;
							}
							else {
								check++;
								funca = "** " +  comparaNum.i1 + " & " + comparaNum.e2 + " ** NO son del mismo tipo, se esperaba a \"" + comparaNum.i1 + "\" como tipo ENTERO" ;
							}
						}
						if(dox.e instanceof ComparaId)
						{

							comparaId = (ComparaId) dox.e;
							
							if(!comparaId.signo.equals("<")) 
								checka++;
							if(checka == program.d.size())
							{
								error = 1;
								System.out.println("no se esperaba una comparación en Until ( " + comparaId.e1 + " " + comparaId.signo + " " + comparaId.e2+ " );");
							}
							
							if(TipoEQ(comparaId.e1, comparaId.e2) && Existencia(comparaId.e1) && Existencia(comparaId.e2))
							{
								check = 0;
							}
							else {
								check++;

								funca = "** " +  comparaId.e1 + " & " + comparaId.e2 + " ** NO son del mismo tipo";
							}
						}
						if(dox.e instanceof Decimalx)
						{
							decimal = (Decimalx) dox.e;
							
							if(!decimal.signo.equals("<")) 
								checka++;
							if(checka == program.d.size())
							{
								error = 1;
								System.out.println("no se esperaba una comparación en sasa ( " + decimal.i + " " + decimal.signo + " " + decimal.decimal1+ " );");
							}
							
							if(Existencia(decimal.i) && decimal.i.equals(program.d.get(j).id) && program.d.get(j).t.equals("float") )
							{
								check = 0;
							}
							else {
								check++;
								funca = "** " +  decimal.i + " & " + decimal.decimal1 + " ** NO son del mismo tipo, se esperaba a \"" + decimal.i + "\" como tipo FLOTANTE" ;
							}
						}
						if(dox.e instanceof Booleanx)
						{
							booleano = (Booleanx) dox.e;
							
							if(!booleano.signo.equals("<")) 
								checka++;
							if(checka == program.d.size())
							{
								error = 1;
								System.out.println("no se esperaba una comparación en Until ( " + booleano.id + " " + booleano.signo + " " + booleano.TF0+ " );");
							}
							
							
							if(Existencia(booleano.id) && booleano.id.equals(program.d.get(j).id) && program.d.get(j).t.equals("boolean") )
							{
								check = 0;
							}
							else {
								check++;
								funca = "** " +  booleano.id + " & " + booleano.TF0 + " ** NO son del mismo tipo, se esperaba a \"" + booleano.id + "\" como tipo BOOLEANO" ;
							}
						}
					} catch (Exception e) {}
					if(check == program.d.size()) {
						System.out.println(funca);
						error = 1;
					}
				}
			}
			
		}
		
	}
	private void Intermedio() {
		int indiceVerdadero = 0;
		for (int i = 0; i < program.s.size(); i++)
		{
			if(program.s.get(i) instanceof Dox)
			{
				dox = (Dox) program.s.get(i);
				try {
					
					if(dox.e instanceof ComparaNum)
					{
						comparaNum = (ComparaNum) dox.e;
						PrintIntermedio(indiceVerdadero, "-", comparaNum.i1, String.valueOf(comparaNum.e2));
						indiceVerdadero++;
						
						if(comparaNum.signo.equals("<")) 
						{
							for (int j = 0; j < Sintactico.saveLineDo.size(); j++) {
								for (int  j2 = Sintactico.saveLineDo.size()-1; j2 >= 0 ; j2--) {
									if(j != j2)
										if(Sintactico.saveLineDo.get(j2) == Sintactico.saveLineDo.get(j))
											Sintactico.saveLineDo.remove(j2);
								}
							}	
							PrintIntermedio(indiceVerdadero, "JZ", "("+(indiceVerdadero-1)+Ayudas.blancos(
									String.valueOf(indiceVerdadero-2).length()+8) +")","("+ Sintactico.saveLineDo.get(Sintactico.saveLineDo.size()-1)+")");

							indiceVerdadero++;
								PrintIntermedio(indiceVerdadero, "JPOS", "("+(indiceVerdadero-2)+Ayudas.blancos(
									String.valueOf(indiceVerdadero-2).length()+8) +")", "("+ Sintactico.saveLineDo.get(Sintactico.saveLineDo.size()-1)+")");
							indiceVerdadero++;
							
							Sintactico.saveLineDo.remove(Sintactico.saveLineDo.size()-1);

						}
						
					}
					if(dox.e instanceof ComparaId)
					{
						comparaId = (ComparaId) dox.e;
						PrintIntermedio(indiceVerdadero, "-", comparaId.e1, comparaId.e2);
						indiceVerdadero++;
						if(comparaId.signo.equals("<")) {
							
							for (int j = 0; j < Sintactico.saveLineDo.size(); j++) {
								for (int  j2 = Sintactico.saveLineDo.size()-1; j2 >= 0 ; j2--) {
									if(j != j2)
										if(Sintactico.saveLineDo.get(j2) == Sintactico.saveLineDo.get(j))
											Sintactico.saveLineDo.remove(j2);
								}
							}	
							
							PrintIntermedio(indiceVerdadero, "JZ", "("+(indiceVerdadero-1)+Ayudas.blancos(
								String.valueOf(indiceVerdadero-1).length()+8) +")","("+ Sintactico.saveLineDo.get(Sintactico.saveLineDo.size()-1)+")");

							indiceVerdadero++;
							PrintIntermedio(indiceVerdadero, "JPOS", "("+(indiceVerdadero-2)+Ayudas.blancos(
									String.valueOf(indiceVerdadero-2).length()+8) +")", "("+Sintactico.saveLineDo.get(Sintactico.saveLineDo.size()-1)+")");
							indiceVerdadero++;

							Sintactico.saveLineDo.remove(Sintactico.saveLineDo.size()-1);
							
						
						}
					}
					if(dox.e instanceof Decimalx)
					{
						decimal = (Decimalx) dox.e;
						PrintIntermedio(indiceVerdadero, "-", decimal.i, decimal.decimal1);
						indiceVerdadero++;
						
						if(decimal.signo.equals("<")){
							for (int j = 0; j < Sintactico.saveLineDo.size(); j++) {
								for (int  j2 = Sintactico.saveLineDo.size()-1; j2 >= 0 ; j2--) {
									if(j != j2)
										if(Sintactico.saveLineDo.get(j2) == Sintactico.saveLineDo.get(j))
											Sintactico.saveLineDo.remove(j2);
								}
							}			
//							System.out.println("\nSaveLine tamaño = " + Sintactico.saveLineDo.size() + "\n");
//							System.out.println("bablabla");
//							for (int j = 0; j < Sintactico.saveLineDo.size(); j++) {
//								System.out.println("Aqui o que pex");
//								System.out.println(Sintactico.saveLineDo.get(j));
//							}
////							
						
							PrintIntermedio(indiceVerdadero, "JZ", "("+(indiceVerdadero-1)+Ayudas.blancos(
									String.valueOf(indiceVerdadero-1).length()+8) +")", "("+
											Sintactico.saveLineDo.get(Sintactico.saveLineDo.size()-1) +
											Ayudas.blancos(String.valueOf(Sintactico.saveLineDo.get(Sintactico.saveLineDo.size()-1)).length()+8)
											+ ")");
//								String.valueOf(indiceVerdadero-1).length()+8) +")", Sintactico.saveLineDo.get(Sintactico.saveLineDo.size()-1));
							indiceVerdadero++;

							PrintIntermedio(indiceVerdadero, "JPOS", "("+(indiceVerdadero-2)+Ayudas.blancos(
									String.valueOf(indiceVerdadero-2).length()+8) +")", "("+
							Sintactico.saveLineDo.get(Sintactico.saveLineDo.size()-1) +
							Ayudas.blancos(String.valueOf(Sintactico.saveLineDo.get(Sintactico.saveLineDo.size()-1)).length()+8)
							+ ")");
							indiceVerdadero++;
							
							
							
							Sintactico.saveLineDo.remove(Sintactico.saveLineDo.size()-1);
							
						}
					}
					if(dox.e instanceof Booleanx)
					{
						booleano = (Booleanx) dox.e;
						PrintIntermedio(indiceVerdadero, "-", booleano.id, String.valueOf(booleano.TF0));
						indiceVerdadero++;
						if(booleano.signo.equals("<")){
							
							for (int j = 0; j < Sintactico.saveLineDo.size(); j++) {
								for (int  j2 = Sintactico.saveLineDo.size()-1; j2 >= 0 ; j2--) {
									if(j != j2)
										if(Sintactico.saveLineDo.get(j2) == Sintactico.saveLineDo.get(j))
											Sintactico.saveLineDo.remove(j2);
								}
							}	
							
							PrintIntermedio(indiceVerdadero, "JZ", "("+(indiceVerdadero-1)+Ayudas.blancos(
								String.valueOf(indiceVerdadero-1).length()+8) +")", asigna.id);
							indiceVerdadero++;
							PrintIntermedio(indiceVerdadero, "JPOS", "("+(indiceVerdadero-2)+Ayudas.blancos(
									String.valueOf(indiceVerdadero-2).length()+8) +")", asigna.id);
							indiceVerdadero++;

						}
					}
				} catch (Exception e) {}
			}
			if(program.s.get(i) instanceof Printx)


			{
				print = (Printx) program.s.get(i);
				try {
					
					if(print.e instanceof ComparaNum)
					{
						comparaNum = (ComparaNum) print.e;
					}
					if(print.e instanceof ComparaId)
					{
						comparaId = (ComparaId) print.e;
					}
					if(print.e instanceof Decimalx)
					{
						decimal = (Decimalx) print.e;
					}
					if(print.e instanceof Booleanx)
					{
						booleano = (Booleanx) print.e;

					}
					
				} catch (Exception e) {}
			}
			if(program.s.get(i) instanceof Asigna)	{
				asigna = (Asigna) program.s.get(i);
				try {
					
					if(asigna.e instanceof ComparaNum)
					{
						comparaNum = (ComparaNum) asigna.e;

//						if(CapturaDo(asigna.id)) {
//							saveLineDo.add(indiceVerdadero);
//						}
						PrintIntermedio(indiceVerdadero, comparaNum.signo, comparaNum.i1, String.valueOf(comparaNum.e2));
						indiceVerdadero++;
						PrintIntermedio(indiceVerdadero, "=", "("+(indiceVerdadero-1)+Ayudas.blancos(
										String.valueOf(indiceVerdadero-1).length()+8) +")", asigna.id);
						indiceVerdadero++;
					}
					if(asigna.e instanceof ComparaId)
					{
						comparaId = (ComparaId) asigna.e;
//						if(CapturaDo(asigna.id))
//							saveLineDo.add(indiceVerdadero);
						
						PrintIntermedio(indiceVerdadero, comparaId.signo, comparaId.e1, comparaId.e2);
						indiceVerdadero++;
						PrintIntermedio(indiceVerdadero, "=", "("+(indiceVerdadero-1)+Ayudas.blancos(
										String.valueOf(indiceVerdadero-1).length()+8) +")", asigna.id);
						indiceVerdadero++;

					}
					if(asigna.e instanceof Decimalx)
					{
						decimal = (Decimalx) asigna.e;
						
//						for (int j = 0; j < tokens.size(); j++) {
//							if(asigna.id.equals(tokens.get(j).getToken())) {}
//						}	
						
//						if(CapturaDo(asigna.id))
//							saveLineDo.add(indiceVerdadero);
						
						PrintIntermedio(indiceVerdadero, decimal.signo, decimal.i, decimal.decimal1);
						indiceVerdadero++;
						PrintIntermedio(indiceVerdadero, "=", "("+(indiceVerdadero-1)+Ayudas.blancos(
										String.valueOf(indiceVerdadero-1).length()+8) +")", asigna.id);
						indiceVerdadero++;
						
						
					}
					if(asigna.e instanceof Booleanx)
					{
						booleano = (Booleanx) asigna.e;
//						if(CapturaDo(asigna.id))
//							saveLineDo.add(indiceVerdadero);
						
						PrintIntermedio(indiceVerdadero, booleano.signo, booleano.id, String.valueOf(booleano.TF0));
						indiceVerdadero++;
						PrintIntermedio(indiceVerdadero, "=", "("+(indiceVerdadero-1)+Ayudas.blancos(
										String.valueOf(indiceVerdadero-1).length()+8) +")", asigna.id);
						indiceVerdadero++;
					}
				} catch (Exception e) {}
			}
		}
		
	}
	private void PrintIntermedio(int i, String signo, String id1, String e2) {//int cambiar a String
//		if(SimbolMenor(signo))
//		{
//			return;
//		}
		
		tabla.add(new Tabla(i, signo, id1, e2));
		
//		System.out.println(	   i	+ Ayudas.blancos(String.valueOf(i).length())
//							+ signo	+ Ayudas.blancos(signo.length())
//							+ id1	+ Ayudas.blancos(id1.length())
//							+ e2	+ Ayudas.blancos(String.valueOf(e2).length())
//				);
	}
}
