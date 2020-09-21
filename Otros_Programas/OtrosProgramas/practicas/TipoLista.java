package practicas;

import java.util.*;

public class TipoLista {
	
	static int contador = 0;
	static Persona pa[] = new Persona[contador];
	public static void main(String[] args) 
	{
		Scanner lee = new Scanner(System.in);
		while(true) {
		System.out.print("Opciones."
					   + "\n1.- Agregar."
					   + "\n2.- Eliminar."
					   + "\n3.- Buscar."
					   + "\n4.- Consulta todo."
					   + "\n5.- Salir."
					   + "\nIngresa opción: ");
		int n = lee.nextInt();
		TipoLista tp = new TipoLista();
		System.out.println();
		switch (n) {
		case 1:
			String nombre = lee.nextLine();
			System.out.print("Teclea nombre: ");
			 nombre = lee.nextLine();
			System.out.print("Teclea apellido: ");
			String apellido = lee.nextLine();
//			apellido = lee.nextLine();

			System.out.print("Teclea edad: ");
			int edad = lee.nextInt();
			tp.Agrega(nombre, apellido, edad);
			break;
		case 2:
			System.out.print("Teclea nombre: ");
			nombre = lee.nextLine();
			nombre = lee.nextLine();

				tp.Elimina(nombre);
			break;
		case 3:
			
			break;
		case 4:
			for (int j = 0; j < pa.length; j++) {
					
					System.out.println(pa[j].getNombre() + " " + pa[j].getApellido() + ", edad " + pa[j].getEdad() + " años.");		 //1
				
			}
			break;
		case 5:
			System.out.println("Haz salido correctamente.");
			return;
			
		
		}
		System.out.println("");

		}
	}
	Persona pac[] = new Persona[contador]; 			 //0
	public void Agrega(String nombre,String apellido,int edad)
	{
		if(contador > 0)
			for (int i = 0; i < pac.length; i++)
			{
				pac[i] = pa[i];
			}
		contador++;											 //1
		pa = new Persona[contador];
		for (int i = 0; i < pa.length-1; i++) 
		{
			pa[i/*contador-1*/] = pac[i];//0
		}
		for (int i = contador-1; i < pa.length; i++) 
		{
			pa[i/*contador-1*/] = new Persona(nombre, apellido, edad);//0
		}
	}
	public void Elimina(String nombre) {
		
		int e = -1; 
		
		for (int j = 0; j < pa.length; j++) 
		{
//			System.out.println("se mete?");
			if( nombre.equalsIgnoreCase(pa[j].getNombre())) 
			{
				e = j;
				System.out.println("existe");
			}
		}
		
		pa[e] = null;
		
		Persona[] pac2 = new Persona[pa.length-1];
		 int mark = 0;
		for (int i = 0; i < pa.length; i++) {
			if(pa[i] != null) 
			{
				pac2[i - mark] = pa[i];
				System.out.println(pa[i].getNombre());
			}
			if(pa[i] == null) 
			{
				mark = 1;
			}
			
		}
		pa = new Persona[pac2.length];
		for (int i = 0; i < pac2.length; i++) {

			pa[i] = pac2[i];
			
			
		}
	}

}
