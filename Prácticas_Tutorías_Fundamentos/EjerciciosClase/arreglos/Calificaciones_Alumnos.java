package arreglos;
import java.util.Scanner;
public class Calificaciones_Alumnos 
{
	//Calcular el promedio de X calificaciones de Y alumnos
	public static void main(String[] args) 
	{
		Scanner lee = new Scanner(System.in);
		int alumnos = 0, calif = 0, suma = 0;
		
		System.out.print("Ingresa la cantidad de alumnos: ");
		alumnos = lee.nextInt();
		
		System.out.print("Ingresa la cantidad de calificaciones por alumno: ");
		calif = lee.nextInt();
		
		int[] arregloAlum = new int[alumnos], promedio = new int[alumnos];
		int arregloCalif[] = new int[calif];
		
		for (int i = 0; i < arregloAlum.length; i++) /*for (int i = 0; i < alumnos; i++)*/ 
		{
			suma = 0;
			for (int j = 0; j < arregloCalif.length; j++) 
			{
				System.out.print("Ingresa la calificación #" + (j + 1) + " del alumno "+ (i + 1) + ": ");
				arregloCalif[j] = lee.nextInt();
				suma += arregloCalif[j]; /* suma = suma + arregloCalif[i];*/
			}
			promedio[i] = suma / arregloCalif.length;
			System.out.println();
		}
		arregloAlum = promedio;
		for (int i = 0; i < arregloAlum.length; i++)
			System.out.println("Promedio del alumno #" + (i + 1) +": " + arregloAlum[i]);
	}
}
