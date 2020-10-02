package calculadora;
//wsCalculadora.iscrquinter.hotmail.com.Calculadora;

public class Calculadora {

	public double suma(double x, double y)
	{
		return x + y;
	}

	public double resta(double x, double y) 
	{
		return x-y;
	}

	public double multiplica(double x, double y) 
	{
		return x * y;
	}
	
	public double divide(double x, double y) 
	{
		return x / y;
	}
	public String divideCero(double x, double y) 
	{
		String resultado = String.valueOf(x / y);
		return resultado;
	}
	public double coseno(double x)
	{
		double y = Math.toRadians(x);
		 return Math.cos(y);
	}
	public double seno(double x)
	{
		double y = Math.toRadians(x);
		return Math.sin(y);
	}
	public double tangente(double x) 
	{
		double y = Math.toRadians(x);
		return Math.tan(y);
	}
}