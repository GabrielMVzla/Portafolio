package parametros;

public class ExplicacionMetodo
{
	//Cada método se compone de la directiva static en este caso, modificador de acceso, 
	//si es vacío o no vacío (void o retorna algún tipo de dato), nombre del método, 
	//parámetros y código
	
	//En modificadores de acceso tenemos public, private y protected, el public sirve para
	//poder acceder a una clase, método, variable desde otra clase, con private solo puedes
	//acceder a esa instrucción desde la misma clase, solamente el código de la misma class, 
	//o bien de una class derivada de esa class, puede acceder al tipo o miembro.(poo).
	
	//void quiere decir que se manda a llamar el método, realiza las instrucciones establecidas
	//y listo
	//no void quiere decir que no es vacío, los no void son aquellos métodos que tienen un 
	//tipo especificado, ej. un método tipo Entero debe regresar un valor de tipo Entero
	//con la palabra clave "return VALOR";
	
	//Los métodos no pueden llevar el mismo nombre a menos que apliques sobrecarga, pero
	//se ve en poo.
	//La idea de realizar esto es que el main no quede con mucho código además de que
	//puede servir para reutilizar las veces que sean necesarias una parte del código.
	public static void main(String[] args) 
	{
		NombreMetodo(5);//mandar llamar un método y mandarle un valor o varios valores
						//a estos valores se les conoce como argumentos.
	}
	static public void NombreMetodo(int variable)//dentro del parentesis se les conoce como parametros, cuando mandes 
												 //a llamar a este método te va a exigir que le mandes 1 argumento
	{
		//Desde main mandamos el valor 5 y se captura en la variable "variable"
		System.out.println("valor capturado = " + variable);
		
		//Mandamos llamar al método RetornaTipoEntero(), no nos pide ningún parámetro
		//el cual nos retorna un valor dependiendo el tipo del método 
		System.out.println("valor recibido = " + RetornaTipoEntero());
		
		//si mandamos llamar al método RetornaTipoEntero() podemos asignarlo a una
		//variable tipo entero ya que son del mismo tipo e incluso podemos realizar
		//operaciones, lo mismo pasaría si el método fuera de otro tipo y la variable
		//declarada fuese compatible, podrían interactuar String con String, etc.
		
		int contener = RetornaTipoEntero() + 10;//contener valdrá 10 + 7 = 17.
		
		System.out.println("contener = " + contener);
		System.out.println("RetornaTipoDouble = " + ExplicacionMetodoLlamada.RetornaTipoDouble());
	}
	private static int RetornaTipoEntero() 
	{
		return 7;//es posible retornar 7 porque es un dato tipo entero.
			 	 //pasaría lo mismo si fuese una variable tipo entero
	}

}
