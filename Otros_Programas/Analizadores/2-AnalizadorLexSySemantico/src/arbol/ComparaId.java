package arbol;

public class ComparaId extends Ex{

	public String identificador1, identificador2, operador;
	public char operador2;
	
	public ComparaId(String id1, String opera, String id2) 
	{
		identificador1 = id1;
		operador 	   = opera;	
		identificador2 = id2;

	}
	public ComparaId(String id1, String opera, char opera2, String id2) //para flotantes
	{ 
		identificador1 = id1;
		operador 	   = opera;
		operador2	   = opera2;
		identificador2 = id2;
		
		identificador2 = opera2 + id2;
	}
	public ComparaId(String id1) 
	{
		identificador1 = id1;
	}
	
}
