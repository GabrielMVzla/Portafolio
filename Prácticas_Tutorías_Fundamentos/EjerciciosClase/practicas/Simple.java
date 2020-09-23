package practicas;

public class Simple 
{
	public static void main(String[] args) 
	{
		int var1 = 15, var2 = 20;
		
		// && = and, || = or
		
		//"si" var1 "o" var2 es mayor a 20 "entonces" desplegará el mensaje contenido en 
		//el System.out, conque solo una de las 2 condiciones cumpla, el mensaje. 
		if(var1 > 20 || var2 > 20)
		{
			System.out.println("uno de las 2 variables es mayor que 20");
		}
		
		//"si" var1 "y" var2 son mayores a 10 "entonces" desplegará el mensaje contenido 
		//en el System.out, aquí las 2 condiciones tienen que cumplir para poder desplegar
		//el mensaje
		if(var1 > 10 && var2 > 10)
			System.out.println("var1 y var2 contienen valores mayores a 10");
	}
}
