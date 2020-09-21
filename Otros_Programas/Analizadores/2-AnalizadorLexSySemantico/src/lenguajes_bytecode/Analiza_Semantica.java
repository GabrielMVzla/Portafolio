package lenguajes;

import arbol.Px;
import arbol.Sx;
import java.util.ArrayList;

import arbol.*;

//En la grámatica hago que si hay una variable sola en un until, digo que debe ser Lógica (boolean)
//De acuerdo a la gramatica solo puedo aceptar id ( operador ) (num | id | boolean | decimal) 
//	 (num | id | boolean | decimal)( operador ) (num | id | boolean | decimal) siempre y cuando las 2 partes sean del mismo tipo
//	 (num | id | boolean | decimal) en algunos casos, como en until, solo permitiría los tipo boolean solos
//	 NO permito  (num | id | boolean | decimal)  ( operador ) id

public class Analiza_Semantica {
	
	
	Px programa;
	ArrayList<Dx> d = new ArrayList<Dx>();
	ArrayList<Sx> s = new ArrayList<Sx>();
	ArrayList<Tabla> tabla = new ArrayList<Tabla>();
	ArrayList<String> bytecode = new ArrayList<String>();
	ArrayList<String> bytecodedoString = new ArrayList<String>();
	ArrayList<String> loadEnUntil = new ArrayList<String>();
	ArrayList<Integer> bytecodeDo = new ArrayList<Integer>();
	ArrayList<Integer> bytecodeUntil = new ArrayList<Integer>();
	ArrayList<Tabla> store;
	
	public Analiza_Semantica(Px p, ArrayList<Integer> bCodeDo, ArrayList<Integer> bCodeUntil) 
	{
		bytecodeDo 		= bCodeDo;
		bytecodeUntil 	= bCodeUntil;
		programa = p;
//		System.out.println("Tamaño de bytecode = " + bytecodeDo.size());
		String arregloByCodeDo[] = new String[bytecodeDo.size()];
		for (int i = 0; i < bytecodeDo.size(); i++) 
		{
			System.out.println(bytecodeDo.get(i));
			arregloByCodeDo[i] = String.valueOf(bytecodeDo.get(i));
		}
	
		ArrayList<Dx> decs;
		ArrayList<Sx> estats;
		decs 	= p.declaraciones;
		estats 	= p.estatutos;
		
													// Al momento de guardarlos en la lista de "declaraciones" de "Px" se guardaron de
		for (int i = decs.size()-1; i >= 0 ; i--)   // manera inversa, pasandolo a este otro array al desplegarlo lo hará correctamente
			d.add(decs.get(i));						// (A como está escrito en el script, la parte de las declaraciones (el archivo))
	
		for (int i = estats.size()-1; i >= 0 ; i--) // lo mismo sucede en este caso
			s.add(estats.get(i));						

		store = new ArrayList<Tabla>();
		
		VariablesRepetidas(); // 1
		
			for (int i = 0; i < tabla.size(); i++) 
			{
				//guardo el nombre y el índice, para guardar el índice tuve que transformarlo a tipo String
//				System.out.println("tabla_valor2 = " + tabla.get(i).valor2);
				if(i >= 1)
				store.add(new Tabla(tabla.get(i).valor2, String.valueOf(i-1))); //Lo reduzco en i-1 ya que la primera vuelta valor2 está vacío
																				//y en ese mismo indice valor1 vale Tabla de Simbolos
			}
//				System.out.println();
		SemanticaEnDO();
//				System.out.println();
		SemanticaEnPrint();
//				System.out.println();
		SemanticaEnAsignacion();

		int contanding = 0;
		
//		for (int i = 0; i <bytecodedoString.size(); i++) {
//			System.out.println("bytecodeString = " + bytecodedoString.get(i));
//		}
		System.out.println("\nbytecode until\n");
		for (int i = 0; i < bytecodeUntil.size(); i++) {
			System.out.println(bytecodeUntil.get(i));
		}
		System.out.println("bytecodeuntil = " + bytecodeUntil.size());
		System.out.println("bytecode = " + bytecode.size());
		int bytecodeAnt = bytecode.size();
		for (int j = bytecodedoString.size()-1; j >= 0 ; j--) 
			System.out.println("bytecodeString = " + bytecodedoString.get(j));
		
		for (int j = bytecodedoString.size()-1; j >= 0 ; j--) 
		{
			if(contanding < bytecodeUntil.size())
			{
				System.out.println("Contanding 1 = " + contanding);
//				System.out.println("cuanto enta 2");
//				System.out.println("bytecodeUntil = " + bytecodeUntil.get(contanding));
				if(bytecodeUntil.get(contanding) > bytecodeAnt-1)//añado las comparaciones de los until, esta validación es en dado caso de que el número de un indice de bytecodeUntil 
				{											  //sema mayor a la de las instrucciones ya creadas, por ejemplo puede haber un until hasta el final donde ya pasaron todas
															  //las asignaciones
									
					if(bytecodedoString.get(j).equals("if_cmptrue") || bytecodedoString.get(j).equals("if_cmpfalse") 
							|| bytecodedoString.get(j).equals("if_cmpboolean"))
					{
	//					System.out.println("Nunca entra?");
						bytecode.add(bytecodedoString.get(j+1)/*+" "+arregloByCodeDo[cont_Inv]+" no"*/);
						bytecode.add(bytecodedoString.get(j)/*+" "+arregloByCodeDo[cont_Inv]+" no"*/);
						bytecodedoString.remove(j+1);
						bytecodedoString.remove(j);
						if(contanding < bytecodeUntil.size())
						{
							bytecodeUntil.remove(contanding);
							contanding++;
	//						System.out.println("Contanding 32311 = " + contanding);
						}
						
					}
					else if(bytecodedoString.get(j).equals("if_cmpge")) {
	//						System.out.println("Segundo intento?");
							bytecode.add(bytecodedoString.get(j+1)/*+" "+arregloByCodeDo[cont_Inv]+" no"*/);
							bytecode.add(bytecodedoString.get(j+2)/*+" "+arregloByCodeDo[cont_Inv]+" no"*/);
							bytecode.add(bytecodedoString.get(j)/*+" "+arregloByCodeDo[cont_Inv]+" no"*/);
							bytecodedoString.remove(j+2);
							bytecodedoString.remove(j+1);
							bytecodedoString.remove(j);
							
	//				System.out.println("btyecodedoSTring = " + bytecodedoString.get(j-1));
	//				System.out.println("btyecodedoSTring = " + bytecodedoString.get(j-2));
							if(contanding < bytecodeUntil.size())
							{
								bytecodeUntil.remove(contanding);
								contanding++;
	//							System.out.println("Contanding 323 = " + contanding);
							}
					}
				}
			}
			
		}
		System.out.println("\nbytecode until\n");
		for (int i = 0; i < bytecodeUntil.size(); i++) {
			System.out.println(bytecodeUntil.get(i));
		}
		System.out.println("bytecodeuntil = " + bytecodeUntil.size());
		
//		for (int j = bytecodedoString.size()-1; j >= 0 ; j--) 
//			{
//			System.out.println(bytecodedoString.get(j)	);
//			System.out.println(bytecodeUntil.get(j)	);
//			}
		contanding = 0;
		for (int i = 0; i < bytecode.size(); i++) 
//			for (int i = bytecode.size()-1; i >= 0  ; i--) 
		{
				for (int j = bytecodedoString.size()-1; j >= 0; j--) 
				{
//					System.out.println("valor de  i = "+ i);
					if(contanding == bytecodeUntil.size()) 		contanding = 0;

													//pregunta si el valor donde se registró que debería la validacion del until coincide con alguna vuelta de i
					if(bytecodeUntil.get(contanding) == i)	//si es así entonces registra el if en ese lugar y recorre el bytecode a 1 más (1ro -> 16 load, después -> 16 if_cmp, 17 load
					{								//a diferencia del de arriba el indice i sí llega al registro de dado bytecodeUntil, arriba, el registro del bytecodeUntil
						//sería mayor a el alcance de i entonces nunca entraría y se perderían validaciones, se perdería bytecode, por eso lo fuerzo a que se metan
						if(contanding < bytecodeUntil.size())
							
								if(bytecodedoString.get(j).equals("if_cmptrue") || bytecodedoString.get(j).equals("if_cmpfalse") 
										|| bytecodedoString.get(j).equals("if_cmpboolean"))
								{
									bytecode.add(bytecodedoString.get(j+1)/*+" "+arregloByCodeDo[cont_Inv]+" no"*/);
									bytecode.add(bytecodedoString.get(j)/*+" "+arregloByCodeDo[cont_Inv]+" no"*/);
									bytecodedoString.remove(j+1);
									bytecodedoString.remove(j);
									if(contanding < bytecodeUntil.size())
									{
										bytecodeUntil.remove(contanding);
										contanding++;
									}
								}
								else {
									
									if(bytecodedoString.get(j).equals("if_cmpge")) {
										bytecode.add(bytecodedoString.get(j+1)/*+" "+arregloByCodeDo[cont_Inv]+" no"*/);
										bytecode.add(bytecodedoString.get(j+2)/*+" "+arregloByCodeDo[cont_Inv]+" no"*/);
										bytecode.add(bytecodedoString.get(j)/*+" "+arregloByCodeDo[cont_Inv]+" no"*/);
										bytecodedoString.remove(j+2);
										bytecodedoString.remove(j+1);
										bytecodedoString.remove(j);
										
										if(contanding < bytecodeUntil.size())
										{
											bytecodeUntil.remove(contanding);
											contanding++;
										}
									}
								}
					}
				}
			}

		int cont_Inv  = bytecodeDo.size()-1; //contador para ingresar datos en un for de manera inversa al contador del for
		for (int i = 0; i < bytecode.size(); i++) 
		{
			if(bytecode.get(i).indexOf("if_cm") > -1) //anteriormente solo asigno el tipo de comparación en una validación (son if_cmpge, if_cmptrue, if_cmpfalse, if_cmpboolean) 
													   //pero la posición a donde debe regresarse la omito, para ello es este for y esta comparación, para colocar de manera correcta
			{										   //la dirección a donde debe viajar, entonces pregunto si en tal indice en bytecode coincide alguna parte de la palabra con "if_cmp"
														//uso esa palabra clave ya que todas las validaciones cuentan con ella
				if(bytecodeDo.get(cont_Inv) == 0)
				bytecode.set(i, bytecode.get(i) +" "+(bytecodeDo.get(cont_Inv)));
				if(bytecodeDo.get(cont_Inv) != 0)
					bytecode.set(i, bytecode.get(i) +" "+(bytecodeDo.get(cont_Inv)));
				cont_Inv--; //lo hago así ya que al registrarlos en otra lista se registró de la manera correcta pero inversamente, aquí solo recorro esa lista
			}
		}
		
		for (int i = 0; i < tabla.size(); i++) { //Despliega la tabla de símbolos
			if(i == 0)
				System.out.println("\n\t"+tabla.get(i).valor1+"\n");
			else
				System.out.println(i+"\t"+tabla.get(i).valor1+"\t\t"+tabla.get(i).valor2);
			
		}
		
		//asigna el indice de Store que le toca a cada var
		//utilizaré un arreglo de tipo tabla para ello ya que tiene 2 parametros String, lo que necesito para hacer lo que quiero
		System.out.println("\nbytecode\n");
		for (int i = 0; i < bytecode.size(); i++) {
			System.out.println(i+" "+bytecode.get(i));
		}
		
	}
	////////////////////////////////////////////////VariablesRepetidas()//////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////VariablesRepetidas()//////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////VariablesRepetidas()//////////////////////////////////////////////////////////////////////////////////

	private void VariablesRepetidas() 
	{
		for (int i = 0; i < d.size(); i++) 
		{
			for (int j = 0; j < d.size(); j++) 
			{
				if(i != j)
					if(d.get(i).id.equals(d.get(j).id)) //Busca variables repetidas, si encuentra termina el programa
					{
						System.out.println("Haz declarado más de una vez la variable --> " + d.get(i).id);
						System.exit(0);
					}
			}
			if(i == d.size()-1)	 						//si no hubo error arriba, antes de salir del for "i" imprimo la tabla 
														//validada de repeticiones
				for (int j = 0; j < d.size(); j++) 
				{
					if(j == 0) 							//Antes de imprimir los primeros datos de la tabla indico con esta 
														//validación que despliegue el sig. msj
						tabla.add(new Tabla("Tabla de Simbolos",""));
					tabla.add(new Tabla(d.get(j).t, d.get(j).id));
						
				}
		}		
	}

	//////////////////////////////////////////////SemanticaEnDO()////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////SemanticaEnDO()////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////SemanticaEnDO()////////////////////////////////////////////////////////////////////////////////////

	Dox			dox;			Printx 			printx;				Asignacionx asignacionx;			
	ComparaId 	comparaidx;		ComparaDecimalx	comparadecimalx;	ComparaNum 	comparanumx;	
	Booleanx  	booleanx;
	int 		load = 0;// 	store = 0;
	
	private void SemanticaEnDO()
	{
		String[] indicador1, indicador2;
		
		for (int i = 0; i < s.size(); i++) 
		{
			boolean id = false, nulo2 = false;
			if(s.get(i) instanceof Dox) 
			{
				dox = (Dox) s.get(i);
				if(dox.expresion instanceof ComparaId)
				{
					
					comparaidx = (ComparaId) dox.expresion;
					
					if(comparaidx.identificador2 == (null))
					{
						nulo2 = true; // si nulo es igual a true, quiere decir que sí es nulo el valor de identificador2
						
					}

					indicador1 = ExisteEnTablaDeSimbolos(comparaidx.identificador1);

					if(indicador1[0].equals("0")) //Válido el valor de indicador[0], si es igual a cero (0) quiere decir que la 
												  //variable mandada a ExisteEnTablaDeSimbolo(), NO existe
					{
						System.out.println("La variable --> "+comparaidx.identificador1+", NO fue declarada");
						System.exit(0);
					}
					if(nulo2 == false) //Si identificador2 NO es nulo, entonces hago movimientos con ello
					{
						indicador2 = ExisteEnTablaDeSimbolos(comparaidx.identificador2); //Aquí tomo pregunto si existe y su tipo, si existe me devolverá "1" en el subIndice [0]
																						 //y su tipo en el subIndice [1], si no existe el subIndice[0] de vuelve "0" y el subInd[1] ya no importaría
//						if(!nulo2)
//						{
							if(!comparaidx.operador.equals("<")) //si el operador lógico no es "lógico" entra en esta validación y termina el programa
							{
								System.out.println("El operador se esperaba de tipo lógico (<), en"
										+ " \nsu lugar está en el until ("+comparaidx.identificador1+" --> "+comparaidx.operador+" <-- "+comparaidx.identificador2+")");
								System.exit(0);
							}
//						}
						
						if(!indicador2[0].equals("0"))
						{
							if(indicador1[1].equals(indicador2[1]))									
								System.out.print(""/*"todo bien, tipo = " + indicador1[1]*/);
							else
							{
								System.out.println("Los tipos no coinciden en la validación en el until" //Si los tipos en las variables comparadas dentro de un until no coinciden
										+"\n"+comparaidx.identificador1+", tipo: "+indicador1[1]		 //entra aquí resaltando el error y terminando el programa
										+"\n"+comparaidx.identificador2+", tipo: "+indicador2[1]
												);
								System.exit(0);
							}
						}
						else
						{
							System.out.println("Variable --> " + comparaidx.identificador2 + ", NO fue declarada");
							System.exit(0);
						}
					}
					else if(nulo2 == true)
					{
						if(indicador1[1].equals("boolean")) {
							System.out.print(""/*"todo bien, identificador es lógico"*/);
						}
						else 
						{
							System.out.println("En la válidación dentro de until ("+comparaidx.identificador1+") hay sólo un dentificador "
									+ "\ny NO es lógico (boolean), debe ser lógico");
							System.exit(0);
						}
					}
						
					if(nulo2)
					{	
						
						bytecodedoString.add("if_cmpboolean");
						for (int j = 0; j < store.size(); j++) 
						{
							if(store.get(j).valor1.equals(comparaidx.identificador1))
							{
								bytecodedoString.add("load_"+store.get(j).valor2/*+" "+comparadecimalx.decimal1*/);
							}
						}
//						bytecode.add("if_cmpboolean");
					}
					else
					{
						bytecodedoString.add("if_cmpge");
						for (int j = 0; j < store.size(); j++) 
						{
							if(store.get(j).valor1.equals(comparaidx.identificador1))
								bytecodedoString.add("load_"+store.get(j).valor2/*+" "+comparadecimalx.decimal1*/);
							if(store.get(j).valor1.equals(comparaidx.identificador2))
								bytecodedoString.add("load_"+store.get(j).valor2/*+" "+comparadecimalx.decimal1*/);
						}
//						bytecode.add("if_cmpge");
					}
						
				}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if(dox.expresion instanceof ComparaDecimalx)
				{
					
					comparadecimalx = (ComparaDecimalx) dox.expresion;
					
					if(comparadecimalx.decimal2 == (null)) //Si comparadecimalx.decimal2 es igual a null finalizo el programa ya que en mi lógica no se pone
					{									   //un número o decimal solo dentro de un until, debe ser coparacion con otro, o simplemente true o false
//						nulo2 = true;
						System.out.println("No se permite ingresar solo un valor en el until ("+comparadecimalx.decimal1+"), a menos que sea una inserción lógica (boolean)");
						System.exit(0);
					}
					if(!comparadecimalx.decimal1.matches("^[+][0-9]+[.][0-9]+?$")		//pregunto si comparadecimalx.decimal1 no es un decimal directo, si NO lo es, id será true
					    && !comparadecimalx.decimal1.matches("^[-][0-9]+[.][0-9]+?$"))	//representando que es un id, si SÍ lo es, id será false representando que sí es un decimal directo
					{																   
						id = true;
					}
					
					if(!nulo2)
					{
						if(!comparadecimalx.operador.equals("<"))
						{
							System.out.println("El operador se esperaba de tipo lógico (<), en"
									+ " \nsu lugar está en el until ("+comparadecimalx.decimal1+" --> "+comparadecimalx.operador+" <-- "+comparadecimalx.decimal2+")");
							System.exit(0);
						}
					}
					if(id) //si id resultó ser true, entonces comparo comparadecimalx.decimal1 verificando si existe en las declaraciones o bien verifico si está declarado
					{
						indicador1 = ExisteEnTablaDeSimbolos(comparadecimalx.decimal1);
						
						if(comparadecimalx.decimal2 == null)
							nulo2 = true;
						
						if(indicador1[0].equals("0"))
						{
							System.out.println("La variable --> "+comparadecimalx.decimal1+", NO ha sido declarada");
							System.exit(0);
						}
						if(!indicador1[1].equals("float"))
							System.out.println("Los tipos no coinciden en la validación en el until" //Si los tipos en las entradas dadas comparadas dentro de un until no coinciden
									+"\n"+comparadecimalx.decimal1+", tipo: "+indicador1[1]			 //entra aquí resaltando el error y terminando el programa
									+"\n"+comparadecimalx.decimal2+", tipo: float"
							);
						
						bytecodedoString.add("if_cmpge");		
						for (int j = 0; j < store.size(); j++) 
						{
							if(store.get(j).valor1.equals(comparadecimalx.decimal1))
							{
								bytecodedoString.add("load_"+store.get(j).valor2/*+" "+comparadecimalx.decimal1*/);
							}
						}
						bytecodedoString.add("decimal_"+comparadecimalx.decimal2);
//						bytecode.add("if_cmpge");
//						if(nulo2 == false)
//						{					
//							if(comparadecimalx.decimal2.matches("^[+][0-9]+[.][0-9]+?$")) 
//							{
//								System.out.println("todo bien+, tipo = " + indicador1[1]);				
//							}
//							else if(comparadecimalx.decimal2.matches("^[-][0-9]+[.][0-9]+?$"))
//							{
//								System.out.println("todo bien-, tipo = " + indicador1[1]);				
//							}
//						}
					}
					else
					{
						bytecodedoString.add("if_cmpge");
						bytecodedoString.add("decimal_"+comparadecimalx.decimal1);
						bytecodedoString.add("decimal_"+comparadecimalx.decimal2);
					}
					
				}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if(dox.expresion instanceof ComparaNum) 
				{
					comparanumx = (ComparaNum) dox.expresion;
					
					if(comparanumx.numeroString2 == null)//Si comparanumx.numeroString2 es igual a null finalizo el programa ya que en mi lógica no se pone
					{									   //un número o decimal solo dentro de un until, debe ser coparacion con otro, o simplemente true o false
						System.out.println("No se permite ingresar solo un valor en el until ("+comparanumx.numeroString1+"), a menos que sea una inserción lógica (boolean)");
						nulo2 = true;
						System.exit(0);
					}
					
					if(!comparanumx.numeroString1.matches("^([0-9])+?$")) //Si comparanumx.numeroString1 es igual a null finalizo el programa ya que en mi lógica no se pone
					{									   //un número o decimal solo dentro de un until, debe ser coparacion con otro, o simplemente true o false
						id = true;
					}
					if(!nulo2)
					{
						if(!comparanumx.operador.equals("<"))
						{
							System.out.println("El operador se esperaba de tipo lógico (<), en"
									+ " \nsu lugar está en el until ("+comparanumx.numeroString1+" --> "+comparanumx.operador+" <-- "+comparanumx.numeroString2+")");
							System.exit(0);
						}
					}
					
					if(id) //si id resultó ser true, entonces comparo comparadecimalx.decimal1 verificando si existe en las declaraciones o bien verifico si está declarado
					{
						indicador1 = ExisteEnTablaDeSimbolos(comparanumx.numeroString1);

						if(indicador1[0].equals("0"))
						{
							System.out.println("La variable --> "+comparanumx.numeroString1+", NO ha sido declarada");
							System.exit(0);
						}
						if(!indicador1[1].equals("int"))
							System.out.println("Los tipos no coinciden en la validación en el until" //Si los tipos en las entradas dadas comparadas dentro de un until no coinciden
									+"\n"+comparanumx.numeroString1+", tipo: "+indicador1[1]			 //entra aquí resaltando el error y terminando el programa
									+"\n"+comparanumx.numeroString2+", tipo: int"
							);
						bytecodedoString.add("if_cmpge");	
						for (int j = 0; j < store.size(); j++) 
						{
							if(store.get(j).valor1.equals(comparanumx.numeroString1))
							{
								bytecodedoString.add("load_"+store.get(j).valor2/*+" "+comparadecimalx.decimal1*/);
							}
						}
						bytecodedoString.add("num_"+comparanumx.numeroString2);
//						bytecode.add("if_cmpge");
					}else
					{
						bytecodedoString.add("if_cmpge");
						bytecodedoString.add("num_"+comparanumx.numeroString1);
						bytecodedoString.add("num"+comparanumx.numeroString2);
					}
					
				}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if(dox.expresion instanceof Booleanx) 
				{
					booleanx = (Booleanx) dox.expresion;
					
					if(booleanx.booleano != true)
						if( booleanx.booleano != false) 
						{
							System.exit(0);
						}
					if(booleanx.booleano)
					{
						
						bytecodedoString.add("if_cmptrue");
						bytecodedoString.add("load_true");
//						bytecode.add("if_cmpfalse");

					}
					else
					{
						bytecodedoString.add("if_cmpfalse");
						bytecodedoString.add("load_false");
					}

				}
				
			}
				
		}
		
	}
	////////////////////////////////////////////////////////SemanticaEnPrint()//////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////SemanticaEnPrint()//////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////SemanticaEnPrint()//////////////////////////////////////////////////////////////////////////
	
	private void SemanticaEnPrint()
	{
		String[] indicador1, indicador2;
		
		for (int i = 0; i < s.size(); i++) 
		{
			boolean id = false, nulo2 = false;
			if(s.get(i) instanceof Printx) 
			{
				printx = (Printx) s.get(i);
				if(printx.expresion instanceof ComparaId)
				{
					
					comparaidx = (ComparaId) printx.expresion;
					
					if(comparaidx.identificador2 == (null))
					{
						nulo2 = true; // si nulo es igual a true, quiere decir que sí es null el valor de identificador2
					}

					indicador1 = ExisteEnTablaDeSimbolos(comparaidx.identificador1); //compruebo que exista declarado comparaidx.identificador1 y me devuelve datos para continuar la ejecución

					if(indicador1[0].equals("0")) //Válido el valor de indicador[0], si es igual a cero (0) quiere decir que la 
												  //variable mandada a ExisteEnTablaDeSimbolo(), NO existe
					{
						System.out.println("La variable --> "+comparaidx.identificador1+", NO fue declarada");
						System.exit(0);
					}
					if(nulo2 == false) //Si identificador2 NO es nulo, entonces hago movimientos con ello
					{
						indicador2 = ExisteEnTablaDeSimbolos(comparaidx.identificador2); //Aquí pregunto si existe y su tipo, si existe me devolverá "1" en el subIndice [0]
																						 //y su tipo en el subIndice [1], si no existe el subIndice[0] de vuelve "0" y el subInd[1] ya no importaría
//						if(!nulo2)
//						{
							if(comparaidx.operador.equals("<")) //si el operador es "lógico" entra en esta validación y termina el programa
							{
								System.out.println("El operador se esperaba que fuera (+ | - | *), en"
										+ " \nsu lugar está en el System.in.readln ("+comparaidx.identificador1+" --> "+comparaidx.operador+" <-- "+comparaidx.identificador2+")");
								System.exit(0);
							}
//						}
						
						if(!indicador2[0].equals("0"))  //si es diferente a 0 quiere decir que sí fue declarada, en cambio es devuelve 0 en el subIndice[0]
						{								//quiere decir que no fue declarada y brinca al else, donde imprime el error y termina el programa
							if(indicador1[1].equals(indicador2[1]))									
								System.out.print(""/*"todo bien, tipo = " + indicador1[1]*/);
							else
							{
								System.out.println("Los tipos no coinciden en la validación en el System.in.readln" //Si los tipos en las variables comparadas dentro de un System.in.readln no coinciden
										+"\n"+comparaidx.identificador1+", tipo: "+indicador1[1]		 //entra aquí resaltando el error con sus diferencias de tipos y terminando el programa
										+"\n"+comparaidx.identificador2+", tipo: "+indicador2[1]
												);
								System.exit(0);
							}
						}
						else
						{
							System.out.println("Variable --> " + comparaidx.identificador2 + ", NO fue declarada");
							System.exit(0);
						}
					}
//					else if(nulo2 == true)
//	a diferencia	{
//	del do, aquí		if(indicador1[1].equals("boolean")) 
//	no importa que		{
//	sean de tipo			System.out.print(""/*"todo bien, identificador es lógico"*/);
//	boolean si solo		}
//	se quiere 			else 
//	imprimir una		{
//	variable				System.out.println("En la válidación dentro de until ("+comparaidx.identificador1+") hay sólo un dentificador "
//									+ "\ny NO es lógico (boolean), debe ser lógico");
//							System.exit(0);
//						}
//					}
					
//					bytecode.add()
				}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if(printx.expresion instanceof ComparaDecimalx)
				{
					
					comparadecimalx = (ComparaDecimalx) printx.expresion;
					
					
//					System.out.println(comparadecimalx.decimal2);
					
					if(comparadecimalx.decimal2 == (null)) //Si comparadecimalx.decimal2 es igual a nulo2 le asigno el valor de true indicando que sí, que es verdad
					{									   //que es null el valor de comparadecimalx.decimal2 para tomar una decisión más adelante
						nulo2 = true;
					}
					if(!comparadecimalx.decimal1.matches("^[+][0-9]+[.][0-9]+?$")		//pregunto si comparadecimalx.decimal1 no es un decimal directo, si NO lo es, id será true
					    && !comparadecimalx.decimal1.matches("^[-][0-9]+[.][0-9]+?$"))	//representando que es un id, si SÍ es decimal, id será false representando que sí es un decimal directo
					{																   
						id = true;
					}
					
					if(!nulo2) //si nulo2 es false entrará
					{
						if(comparadecimalx.operador.equals("<")) //si el operador de estos decimales es "<" imprimirá error y terminará el programa
						{
							System.out.println("El operador se esperaba que fuera (+ | - | *), en"
									+ " \nsu lugar está en el until ("+comparadecimalx.decimal1+" --> "+comparadecimalx.operador+" <-- "+comparadecimalx.decimal2+")");
							System.exit(0);
						}
					}
					if(id) //si id resultó ser true, entonces comparo comparadecimalx.decimal1 verificando si existe en las declaraciones o bien verifico si está declarado
					{
						indicador1 = ExisteEnTablaDeSimbolos(comparadecimalx.decimal1);
						
						if(comparadecimalx.decimal2 == null)
							nulo2 = true;
						
						if(indicador1[0].equals("0"))
						{
							System.out.println("La variable --> "+comparadecimalx.decimal1+", NO ha sido declarada");
							System.exit(0);
						}
						if(!indicador1[1].equals("float"))
							System.out.println("Los tipos no coinciden en la validación en el until" //Si los tipos en las entradas dadas comparadas dentro de un System.in.readln no coinciden
									+"\n"+comparadecimalx.decimal1+", tipo: "+indicador1[1]			 //entra aquí resaltando el error y terminando el programa, el tipo del id debe ser float
									+"\n"+comparadecimalx.decimal2+", tipo: float"
							);
//						if(nulo2 == false)
//						{					
//							if(comparadecimalx.decimal2.matches("^[+][0-9]+[.][0-9]+?$")) 
//							{
//								System.out.println("todo bien+, tipo = " + indicador1[1]);				
//							}
//							else if(comparadecimalx.decimal2.matches("^[-][0-9]+[.][0-9]+?$"))
//							{
//								System.out.println("todo bien-, tipo = " + indicador1[1]);				
//							}
//						}
					}
					
				}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if(printx.expresion instanceof ComparaNum) 
				{
					comparanumx = (ComparaNum) printx.expresion;
					
					if(comparanumx.numeroString2 == (null)) //Si comparanumx.numeroString2 es igual a nulo2 le asigno el valor de true indicando que sí, que es verdad
					{									   //que es null el valor de comparanumx.numeroString2 para tomar una decisión más adelante
						nulo2 = true;
					}
					
					if(!comparanumx.numeroString1.matches("^([0-9])+?$")) //pregunto si comparanumx.numeroString1 no es un entero directo, si NO lo es, id será true
					{									   //representando que es un id, si SÍ es entero, id será false representando que sí es un entero directo
						id = true;
					}
					if(!nulo2)
					{
						if(comparanumx.operador.equals("<"))
						{
							System.out.println("El operador se esperaba que fuera (+ | - | *), en"
									+ " \nsu lugar está en el until ("+comparanumx.numeroString1+" --> "+comparanumx.operador+" <-- "+comparanumx.numeroString2+")");
							System.exit(0);
						}
					}
					
					if(id) //si id resultó ser true, entonces comparo comparanumx.numeroString1 verificando si existe en las declaraciones o bien verifico si está declarado
					{
						indicador1 = ExisteEnTablaDeSimbolos(comparanumx.numeroString1);

						if(indicador1[0].equals("0"))
						{
							System.out.println("La variable --> "+comparanumx.numeroString1+", NO ha sido declarada");
							System.exit(0);
						}
						if(!indicador1[1].equals("int"))
							System.out.println("Los tipos no coinciden en la validación en el System.in.readln" //Si los tipos en las entradas dadas comparadas dentro de un until no coinciden
									+"\n"+comparanumx.numeroString1+", tipo: "+indicador1[1]				 //entra aquí resaltando el error y terminando el programa
									+"\n"+comparanumx.numeroString2+", tipo: int"
							);
					}
					
				}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if(printx.expresion instanceof Booleanx) 
				{
					booleanx = (Booleanx) printx.expresion;
					
					if(booleanx.booleano != true)
						if( booleanx.booleano != false) 
						{
							System.exit(0);
						}
				}
				
			}
				
		}
		
	}
	////////////////////////////////////////////////////////SemanticaEnAsignacion()//////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////SemanticaEnAsignacion()//////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////SemanticaEnAsignacion()//////////////////////////////////////////////////////////////////////////
	
	private void SemanticaEnAsignacion()
	{
		String[] indicador1, indicador2, asignacion;
		
		for (int i = 0; i < s.size(); i++) 
		{
			boolean id = false, nulo2 = false;
			if(s.get(i) instanceof Asignacionx) 
			{
				asignacionx = (Asignacionx) s.get(i);
				asignacion = ExisteEnTablaDeSimbolos(asignacionx.identificador); //verifico si la variable a la que se le asigna el valor existe
				
				if(asignacion[0].equals("0"))//si no se encontró la anterior varibale manda un cero 0 y si es cero termina el programa
				{
					System.out.println("La variable --> "+asignacionx.identificador+", NO fue declarada");
					System.exit(0);
				}
				
				if(asignacionx.expresion instanceof ComparaId)
				{
					
					comparaidx = (ComparaId) asignacionx.expresion;
					
					if(comparaidx.identificador2 == (null))
					{
						nulo2 = true; // si nulo es igual a true, quiere decir que sí es null el valor de identificador2
					}
					indicador1 = ExisteEnTablaDeSimbolos(comparaidx.identificador1); //compruebo que exista declarado comparaidx.identificador1 y me devuelve datos para continuar la ejecución

					if(!asignacion[1].equals(indicador1[1])) //verifico la variable a la que se le va a asignar y la variable a asignar son del mismo tipo, si no lo son finalizo el programa
					{
						System.out.println("En la asignación a "+asignacionx.identificador+", los tipos entre éste y "+comparaidx.identificador1+", NO coinciden"
								+"\n"+asignacionx.identificador+", tipo: "+asignacion[1]		 
								+"\n"+comparaidx.identificador1+", tipo: "+indicador1[1]);
						System.exit(0);
					}
					
					if(indicador1[0].equals("0")) //Válido el valor de indicador[0], si es igual a cero (0) quiere decir que la 
												  //variable mandada a ExisteEnTablaDeSimbolo(), NO existe
					{
						System.out.println("La variable --> "+comparaidx.identificador1+", NO fue declarada");
						System.exit(0);
					}
					if(nulo2 == false) //Si identificador2 NO es nulo, entonces hago movimientos con ello
					{
						indicador2 = ExisteEnTablaDeSimbolos(comparaidx.identificador2); //Aquí pregunto si existe y su tipo, si existe me devolverá "1" en el subIndice [0]
																						 //y su tipo en el subIndice [1], si no existe el subIndice[0] de vuelve "0" y el subInd[1] ya no importaría del todo
//						if(!nulo2)
//						{
							if(comparaidx.operador.equals("<")) //si el operador es "lógico" entra en esta validación y termina el programa
							{
								System.out.println("El operador se esperaba que fuera (+ | - | *), en"
										+ " \nsu lugar está en la asignación ("+asignacionx.identificador+" = "+comparaidx.identificador1+" --> "+comparaidx.operador+" <-- "+comparaidx.identificador2+")");
								System.exit(0);
							}
//						}
						
						if(!indicador2[0].equals("0"))  //si es diferente a 0 quiere decir que sí fue declarada, en cambio si devuelve 0 en el subIndice[0]
						{								//quiere decir que no fue declarada y brinca al else, donde imprime el error y termina el programa
														//pero en este caso, entra al if
							if(indicador1[1].equals(indicador2[1]))	//pregunta si los tipos de las 2 variables asignadas son del mismo tipo, si sí, todo está correcto, si no, brinca al else y termina el programa					
								System.out.print(""/*"todo bien, tipo = " + indicador1[1]*/);
							else
							{
								System.out.println("Los tipos no coinciden en la asignacion hacia --> "+asignacionx.identificador+"" //Si los tipos en las variables comparadas dentro de una asignación no coinciden
										+"\n"+asignacionx.identificador+", tipo: "+asignacion[1]		 //entra aquí resaltando el error con sus diferencias de tipos y terminando el programa
										+"\n"+comparaidx.identificador1+", tipo: "+indicador1[1]		 
										+"\n"+comparaidx.identificador2+", tipo: "+indicador2[1]
												);
								System.exit(0);
							}
						}
						else
						{
							System.out.println("Variable --> " + comparaidx.identificador2 + ", NO fue declarada");
							System.exit(0);
						}
					}
					for (int j = 0; j < store.size(); j++) 
						if(store.get(j).valor1.equals(comparaidx.identificador1))
							bytecode.add("load_"+store.get(j).valor2/*+" "+comparadecimalx.decimal1*/);
					

					if(!nulo2) //si no es null se agregará otro load y una operacion (add, sub..)
					{
						for (int j = 0; j < store.size(); j++) 
							if(store.get(j).valor1.equals(comparaidx.identificador2))
								bytecode.add("load_"+store.get(j).valor2/*+" "+comparadecimalx.decimal1*/);
						
						bytecode.add(Operador(comparaidx.operador));
						for (int j = 0; j < store.size(); j++) {
							if(store.get(j).valor1.equals(asignacionx.identificador))
								bytecode.add("store_"+store.get(j).valor2);
								
						}
					}
					else //si sí es null solo se agregará una instrucción al bytecode
					{
						load = 0;
						for (int j = 0; j < store.size(); j++) 
						{
							if(store.get(j).valor1.equals(asignacionx.identificador))
								bytecode.add("store_"+store.get(j).valor2);
								
						}
					}
				}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if(asignacionx.expresion instanceof ComparaDecimalx)
				{
					
					comparadecimalx = (ComparaDecimalx) asignacionx.expresion;
					
					
//					System.out.println(comparadecimalx.decimal2);
					
					if(comparadecimalx.decimal2 == (null)) //Si comparadecimalx.decimal2 es igual a nulo2 le asigno el valor de true indicando que sí, que es verdad
					{									   //que es null el valor de comparadecimalx.decimal2 para tomar una decisión más adelante
						nulo2 = true;
					}
					if(!comparadecimalx.decimal1.matches("^[+][0-9]+[.][0-9]+?$")		//pregunto si comparadecimalx.decimal1 no es un decimal directo, si NO lo es, id será true
					    && !comparadecimalx.decimal1.matches("^[-][0-9]+[.][0-9]+?$"))	//representando que es un id, si SÍ es decimal, id será false representando que sí es un decimal directo
					{																   
						id = true;
					}
					
					if(!nulo2) //si nulo2 es false entrará
					{
						if(comparadecimalx.operador.equals("<")) //si el operador de estos decimales es "<" imprimirá error y terminará el programa
						{
							System.out.println("El operador se esperaba que fuera (+ | - | *), en"
									+ " \nsu lugar está en la asignación ("+asignacionx.identificador+" = "+comparadecimalx.decimal1+" --> "+comparadecimalx.operador+" <-- "+comparadecimalx.decimal2+")");
							System.exit(0);
						}
					}
					if(id) //si id resultó ser true, entonces comparo comparadecimalx.decimal1 verificando si existe en las declaraciones o bien verifico si está declarado
					{
						indicador1 = ExisteEnTablaDeSimbolos(comparadecimalx.decimal1);
						
						if(!asignacion[1].equals(indicador1[1])) //verifico la variable a la que se le va a asignar y la variable a asignar son del mismo tipo, si no lo son finalizo el programa
						{
							System.out.println("En la asignación a "+asignacionx.identificador+", los tipos entre éste y "+comparadecimalx.decimal1+", NO coinciden"
									+"\n"+asignacionx.identificador+", tipo: "+asignacion[1]		 
									+"\n"+comparadecimalx.decimal1+ ", tipo: "+indicador1[1]);
							System.exit(0);
						}
						
						if(indicador1[0].equals("0"))
						{
							System.out.println("La variable --> "+comparadecimalx.decimal1+", NO ha sido declarada");
							System.exit(0);
						}
						if(!indicador1[1].equals("float"))
						{							
							System.out.println("Los tipos no coinciden en la asignación hacia --> "+asignacionx.identificador //Si los tipos en las entradas dadas comparadas dentro de un System.in.readln no coinciden
									+"\n"+comparadecimalx.decimal1+", tipo: "+indicador1[1]			 //entra aquí resaltando el error y terminando el programa, el tipo del id debe ser float
									+"\n"+comparadecimalx.decimal2+", tipo: float"
							);
							System.exit(0);
						}
					}
					else if(!asignacion[1].equals("float"))
					{
						System.out.println("Los tipos no coinciden en la asignación hacia --> "+asignacionx.identificador //Si los tipos en las entradas dadas comparadas dentro de un System.in.readln no coinciden
								+"\n"+asignacionx.identificador+", tipo: "+asignacion[1]			 //entra aquí resaltando el error y terminando el programa, el tipo del id debe ser float
								+"\n"+comparadecimalx.decimal1+", tipo: float"
						);
						System.exit(0);
					}
					
					if(id)
					{
						for (int j = 0; j < store.size(); j++) 
							if(store.get(j).valor1.equals(comparadecimalx.decimal1))
								bytecode.add("load_"+store.get(j).valor2/*+" "+comparadecimalx.decimal1*/);
					}
					else
						bytecode.add("decimal_"+comparadecimalx.decimal1);
					load++;
					if(!nulo2)
					{
						id = false;
						if(!comparadecimalx.decimal2.matches("^[+][0-9]+[.][0-9]+?$")		//pregunto si comparadecimalx.decimal1 no es un decimal directo, si NO lo es, id será true
							    && !comparadecimalx.decimal2.matches("^[-][0-9]+[.][0-9]+?$"))	//representando que es un id, si SÍ es decimal, id será false representando que sí es un decimal directo
							{																   
								id = true;
							}
						if(id)
						{
							for (int j = 0; j < store.size(); j++) 
								if(store.get(j).valor1.equals(comparadecimalx.decimal2))
									bytecode.add("load_"+store.get(j).valor2/*+" "+comparadecimalx.decimal1*/);
						}
						else
							bytecode.add("decimal_"+comparadecimalx.decimal2);

						load = 0;
						bytecode.add(Operador(comparadecimalx.operador));
						for (int j = 0; j < store.size(); j++) {
							if(store.get(j).valor1.equals(asignacionx.identificador))
								bytecode.add("store_"+store.get(j).valor2);
								
						}
					}
					else
					{
						load = 0;
						for (int j = 0; j < store.size(); j++) 
						{
							if(store.get(j).valor1.equals(asignacionx.identificador))
								bytecode.add("store_"+store.get(j).valor2);
								
						}
					}
				}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if(asignacionx.expresion instanceof ComparaNum) 
				{
					comparanumx = (ComparaNum) asignacionx.expresion;
					
					if(comparanumx.numeroString2 == (null)) //Si comparanumx.numeroString2 es igual a nulo2 le asigno el valor de true indicando que sí, que es verdad
					{									   //que es null el valor de comparanumx.numeroString2 para tomar una decisión más adelante
						nulo2 = true;
					}
					
					if(!comparanumx.numeroString1.matches("^([0-9])+?$")) //pregunto si comparanumx.numeroString1 no es un entero directo, si NO lo es, id será true
					{									   //representando que es un id, si SÍ es entero, id será false representando que sí es un entero directo
						id = true;
					}
					if(!nulo2)
					{
						if(comparanumx.operador.equals("<"))
						{
							System.out.println("El operador se esperaba que fuera (+ | - | *), en"
									+ " \nsu lugar está en la asignación ("+asignacionx.identificador+" = "+comparanumx.numeroString1+" --> "+comparanumx.operador+" <-- "+comparanumx.numeroString2+")");
							System.exit(0);
						}
					}
					
					if(id) //si id resultó ser true, entonces comparo comparanumx.numeroString1 verificando si existe en las declaraciones o bien verifico si está declarado
					{
						indicador1 = ExisteEnTablaDeSimbolos(comparanumx.numeroString1);

						if(!asignacion[1].equals(indicador1[1])) //verifico la variable a la que se le va a asignar y la variable a asignar son del mismo tipo, si no lo son finalizo el programa
						{
							System.out.println("En la asignación a "+asignacionx.identificador+", los tipos entre éste y "+comparadecimalx.decimal1+", NO coinciden"
									+"\n"+asignacionx.identificador+", tipo: "+asignacion[1]		 
									+"\n"+comparadecimalx.decimal1+ ", tipo: "+indicador1[1]);
							System.exit(0);
						}
						
						if(indicador1[0].equals("0"))
						{
							System.out.println("La variable --> "+comparanumx.numeroString1+", NO ha sido declarada");
							System.exit(0);
						}
						if(!indicador1[1].equals("int"))
						{
							
							System.out.println("Los tipos no coinciden en la asignacion hacia --> "+asignacionx.identificador //Si los tipos en las entradas dadas comparadas dentro de un until no coinciden
									+"\n"+comparanumx.numeroString1+", tipo: "+indicador1[1]				 //entra aquí resaltando el error y terminando el programa
									+"\n"+comparanumx.numeroString2+", tipo: int"
							);
							System.exit(0);
						}

					}
					else if(!asignacion[1].equals("int"))
					{
						System.out.println("Los tipos no coinciden en la asignación hacia --> "+asignacionx.identificador //Si los tipos en las entradas dadas comparadas dentro de un System.in.readln no coinciden
								+"\n"+asignacionx.identificador+", tipo: "+asignacion[1]			 //entra aquí resaltando el error y terminando el programa, el tipo del id debe ser float
								+"\n"+comparanumx.numeroString1+", tipo: int"
						);
						System.exit(0);
					}
					if(id)
					{
						for (int j = 0; j < store.size(); j++) 
							
							if(store.get(j).valor1.equals(comparanumx.numeroString1))
								bytecode.add("load_"+store.get(j).valor2/*+" "+comparadecimalx.decimal1*/);
					}
					else
						bytecode.add("num_"+comparanumx.numeroString1);
					load++;
					if(!nulo2)
					{
						id = false;
						if(!comparanumx.numeroString2.matches("^([0-9])+?$"))		//pregunto si comparanumx.numeroString1 no es un entero directo, si NO lo es, id será true
																		//representando que es un id, si SÍ es entero, id será false representando que sí es un entero directo
						{																   
								id = true;
						}
						if(id)
						{
							for (int j = 0; j < store.size(); j++) 
							
								if(store.get(j).valor1.equals(comparanumx.numeroString2))
									bytecode.add("load_"+store.get(j).valor2/*+" "+comparadecimalx.decimal1*/);
						}
						else
							bytecode.add("num_"+comparanumx.numeroString2);
						load = 0;
						bytecode.add(Operador(comparanumx.operador));
						for (int j = 0; j < store.size(); j++) 
						{
							if(store.get(j).valor1.equals(asignacionx.identificador))
								bytecode.add("store_"+store.get(j).valor2);
								
						}
					}
					else 
					{
						load = 0;
						for (int j = 0; j < store.size(); j++) 
						{
							if(store.get(j).valor1.equals(asignacionx.identificador))
								bytecode.add("store_"+store.get(j).valor2);
								
						}
					}
				}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if(asignacionx.expresion instanceof Booleanx) 
				{
					booleanx = (Booleanx) asignacionx.expresion;
					
					if(booleanx.booleano != true)
						if( booleanx.booleano != false) 
						{
							System.exit(0);
						}
					
					bytecode.add("bool_"+booleanx.booleano/*+" "+comparadecimalx.decimal1*/);

					{
						load = 0;
						for (int j = 0; j < store.size(); j++) 
						{
							if(store.get(j).valor1.equals(asignacionx.identificador))
								bytecode.add("store_"+store.get(j).valor2);
								
						}
					}
				}
				
			}
				
		}
		
	}
	private String[] ExisteEnTablaDeSimbolos(String declarado) 
	{
		String respuesta[] = new String[2];
		
		respuesta[0] = "0";
		respuesta[1] = "diferente";
		
		for (int i = 0; i < d.size(); i++) 
			if( d.get(i).id.equals(declarado))
			{
				respuesta[0] = "1";
				respuesta[1] = d.get(i).t;
			}
		
		return respuesta;
	}
	private String Operador(String opera)
	{
		if(opera.equals("+"))
			return "add";
		if(opera.equals("-"))
			return "sub";
		if(opera.equals("*"))
			return "mul";
		
		return null;
	}
}
