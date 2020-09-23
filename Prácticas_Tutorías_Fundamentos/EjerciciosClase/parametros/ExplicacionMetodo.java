package parametros;

public class ExplicacionMetodo
{
	//Cada m�todo se compone de la directiva static en este caso, modificador de acceso, 
	//si es vac�o o no vac�o (void o retorna alg�n tipo de dato), nombre del m�todo, 
	//par�metros y c�digo
	
	//En modificadores de acceso tenemos public, private y protected, el public sirve para
	//poder acceder a una clase, m�todo, variable desde otra clase, con private solo puedes
	//acceder a esa instrucci�n desde la misma clase, solamente el c�digo de la misma class, 
	//o bien de una class derivada de esa class, puede acceder al tipo o miembro.(poo).
	
	//void quiere decir que se manda a llamar el m�todo, realiza las instrucciones establecidas
	//y listo
	//no void quiere decir que no es vac�o, los no void son aquellos m�todos que tienen un 
	//tipo especificado, ej. un m�todo tipo Entero debe regresar un valor de tipo Entero
	//con la palabra clave "return VALOR";
	
	//Los m�todos no pueden llevar el mismo nombre a menos que apliques sobrecarga, pero
	//se ve en poo.
	//La idea de realizar esto es que el main no quede con mucho c�digo adem�s de que
	//puede servir para reutilizar las veces que sean necesarias una parte del c�digo.
	public static void main(String[] args) 
	{
		NombreMetodo(5);//mandar llamar un m�todo y mandarle un valor o varios valores
						//a estos valores se les conoce como argumentos.
	}
	static public void NombreMetodo(int variable)//dentro del parentesis se les conoce como parametros, cuando mandes 
												 //a llamar a este m�todo te va a exigir que le mandes 1 argumento
	{
		//Desde main mandamos el valor 5 y se captura en la variable "variable"
		System.out.println("valor capturado = " + variable);
		
		//Mandamos llamar al m�todo RetornaTipoEntero(), no nos pide ning�n par�metro
		//el cual nos retorna un valor dependiendo el tipo del m�todo 
		System.out.println("valor recibido = " + RetornaTipoEntero());
		
		//si mandamos llamar al m�todo RetornaTipoEntero() podemos asignarlo a una
		//variable tipo entero ya que son del mismo tipo e incluso podemos realizar
		//operaciones, lo mismo pasar�a si el m�todo fuera de otro tipo y la variable
		//declarada fuese compatible, podr�an interactuar String con String, etc.
		
		int contener = RetornaTipoEntero() + 10;//contener valdr� 10 + 7 = 17.
		
		System.out.println("contener = " + contener);
		System.out.println("RetornaTipoDouble = " + ExplicacionMetodoLlamada.RetornaTipoDouble());
	}
	private static int RetornaTipoEntero() 
	{
		return 7;//es posible retornar 7 porque es un dato tipo entero.
			 	 //pasar�a lo mismo si fuese una variable tipo entero
	}

}
