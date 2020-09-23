package practicas;

public class Ejemplo 
{
	public static void main (String[] args)
	{
		int var1 = 1; 
		int var2 = 10;
		int aux = 0;		//auxiliar para conservar el valor de var1
		
		//Codigo para Leer por teclado
		
		//Valores antes del cambio
		System.out.println("Antes del cambio\nVariable 1 = " + var1 + ", variable 2 = " + var2);
		
		//Cambio de valores
		/*	var1 = var2; 		Antes que nada NO podemos hacer esto porque sobreescribiriamos 
		 *						el valor de var1 y se perdería para siempre	.
		 */
		aux = var1;		 //Así que primero guardamos el valor de var1 en la variable auxiliar
		var1 = var2;   	 /*Ahora sí podemos hacer esto ya que tenemos el valor de var1 guardado
						   en la variable auxiliar*/ 
		
		var2 = aux;		 //Y el viejo valor de la var1 ahora almacenado en aux, lo pasamos a var2
		
		//Como resultado de esto las 2 variables fueron intercambiadas de valores

		//Valores después del cambio
		//System.out.println();
		System.out.println("\nDespués del cambio\nVariable 1 = " + var1 + ", variable 2 = " + var2);
		
		//Codigo para realizar una suma
	}
}