package objeto;

public class AreaStatic {
	
	//De esta manera cuando queramos consultar area en diferentes objetos
	//estarás haciendo referencia a la misma área y al valor que contiene
	public static double area = 10;
	
	public AreaStatic(double area) 
	{
		this.area = this.area + area;
	}

}
