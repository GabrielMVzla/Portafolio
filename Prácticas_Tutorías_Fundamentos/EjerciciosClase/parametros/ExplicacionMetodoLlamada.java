package parametros;

public class ExplicacionMetodoLlamada 
{
	public static double RetornaTipoDouble() 
	{
		double retorna = 10.0;
		
		//retorna tiene el valor de 10 en un inicio
		//la siguiente operación es como si dijera 
		//10.0 + 10.0 = nuevo valor en retorna, es decir, 20.0
		//recordar que las instrucciones se ejecutan de derecha a izq.
		retorna = retorna + retorna; 
		
		return retorna;
	}
}
