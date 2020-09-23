package arreglos;

public class Arreglo01 
{
	//Introducción a los Arreglos

	public static void main(String[] args)
	{
		//otra manera de asignar valores a un arreglo es la siguiente
		int i[] = {5, 10, 20};
		//así interpretamos que este arreglo de enteros tiene 3 valores
		//es lo mismo para otros tipos de datos
		String 	s[] = {"dato", "en", "forma", "de", "cadena"};
		char 	c[] = {'c','a','r','a','c','t','e','r'};
		double 	d[] = {1.5, 2.0, 5, 0.005};
		
		//para los float es importante añadirle ** f ** al final a lo números que no sean enteros
		float f[] = {1.5f, 1, 2.0f, 0.001f};
		
		System.out.println("Desplegando arreglo de Enteros");
		for (int j = 0; j < i.length; j++) 
			System.out.println(i[j]);
		
		System.out.println("\nDesplegando arreglo de Cadenas");
		for (int j = 0; j < s.length; j++) 
			System.out.println(s[j]);
		
		System.out.println("\nDesplegando arreglo de Carácteres");
		for (int j = 0; j < c.length; j++) 
			System.out.println(c[j]);
		
		System.out.println("\nDesplegando arreglo de Dobles");
		for (int j = 0; j < d.length; j++) 
			System.out.println(d[j]);
		
		System.out.println("\nDesplegando arreglo de Flotantes");
		for (int j = 0; j < f.length; j++) 
			System.out.println(f[j]);
			
		
	}
	
}
