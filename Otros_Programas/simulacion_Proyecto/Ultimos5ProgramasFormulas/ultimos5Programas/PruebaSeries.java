package ultimos5Programas;
import java.text.DecimalFormat;
import java.util.Scanner;


public class PruebaSeries 
{
public PruebaSeries() {
main(null);	// TODO Auto-generated constructor stub
}
	public static void main(String[] args) 
	{
		double acuChicuadrada=0;
		Scanner sc = new Scanner(System.in);
		int n, porcentaje = 0;
		DecimalFormat a= new DecimalFormat("0.0");
		System.out.println("\n----------------------SERIES----------------------\n");

		do
		{
			System.out.print("Ingresa cantidad de números (cantidad mayor o igual a 34): ");
			n=sc.nextInt();
		}while (n<34);
		while(porcentaje != 1 && porcentaje != 2)
		{
			System.out.print("Opciones\n\"1\".- 5%\n\"2\".- 10%\nIngresa opción: ");
			porcentaje = sc.nextInt();
		}
		System.out.println("\n\n-----------NÚMEROS RANDOM------------\n");

		double vector[]= new double[n];
		double vector2[][] = new double [n][2];
		double vector3[][] = new double [5][5];
		for(int i=0;i<n;i++)
		{
			vector[i]= Math.random();
		}
		
		for (int i = 0; i < vector.length; i++) {

			System.out.print(Formatea.alinder("###", i+1) +".- ");
			System.out.print(Formatea.alinder("0.00000",vector[i])+"\t");
			if((i+1)%4 == 0 || i == (n-1))
				System.out.println();
		}
		System.out.println("\n\n-----PARES ORDENADOS---\n\n"
						     + "---------------------------"
						     + "\n  n  |     U1           U2\n"
						     + "---------------------------");
		for (int i = 0; i < vector.length; i++) {
			if(i!=vector.length-1)
				System.out.println(Formatea.alinder("###",(i+1))+"  |  "+Formatea.alinder("0.00000",vector[i]) +"     "+Formatea.alinder("0.00000",vector[i+1]));
			else
				System.out.println(Formatea.alinder("###",(i+1))+"  |  "+Formatea.alinder("0.00000",vector[i]) +"     "+Formatea.alinder("0.00000",vector[i]));
			
		}
		for(int i=0;i<n;i++)
		{
			vector2[i][0]=vector[i];
			if((i+1)!=n)
				vector2[i][1]=vector[i+1];
			else
				vector2[i][1]=vector[i];
		}
	
		for(int i=0;i<n;i++)
		{
			if(vector2[i][0]<=.2 && vector2[i][1]<=.2)
				vector3[4][0]=vector3[4][0]+1;
			if(vector2[i][0]<=.2 && vector2[i][1]>.2 && vector2[i][1]<=.4)
				vector3[4][1]=vector3[4][1]+1;
			if(vector2[i][0]<=.2 && vector2[i][1]>.4 && vector2[i][1]<=.6)
				vector3[4][2]=vector3[4][2]+1;
			if(vector2[i][0]<=.2 && vector2[i][1]>.6 && vector2[i][1]<=.8)
				vector3[4][3]=vector3[4][3]+1;
			if(vector2[i][0]<=.2 && vector2[i][1]>.8 && vector2[i][1]<=1.0)
				vector3[4][4]=vector3[4][4]+1;
			
			if(vector2[i][0]>.2 && vector2[i][0]<=.4   && vector2[i][1]<=.2)
				vector3[3][0]=vector3[3][0]+1;
			if(vector2[i][0]>.2 && vector2[i][0]<=.4   && vector2[i][1]>.2 && vector2[i][1]<=.4)
				vector3[3][1]=vector3[3][1]+1;
			if(vector2[i][0]>.2 && vector2[i][0]<=.4   && vector2[i][1]>.4 && vector2[i][1]<=.6)
				vector3[3][2]=vector3[3][2]+1;
			if(vector2[i][0]>.2 && vector2[i][0]<=.4   && vector2[i][1]>.6 && vector2[i][1]<=.8)
				vector3[3][3]=vector3[3][3]+1;
			if(vector2[i][0]>.2 && vector2[i][0]<=.4   && vector2[i][1]>.8 && vector2[i][1]<=1.0)
				vector3[3][4]=vector3[3][4]+1;
			
			if(vector2[i][0]>.4 && vector2[i][0]<=.6   && vector2[i][1]<=.2)
				vector3[2][0]=vector3[2][0]+1;
			if(vector2[i][0]>.4 && vector2[i][0]<=.6   && vector2[i][1]>.2 && vector2[i][1]<=.4)
				vector3[2][1]=vector3[2][1]+1;
			if(vector2[i][0]>.4 && vector2[i][0]<=.6   && vector2[i][1]>.4 && vector2[i][1]<=.6)
				vector3[2][2]=vector3[2][2]+1;
			if(vector2[i][0]>.4 && vector2[i][0]<=.6   && vector2[i][1]>.6 && vector2[i][1]<=.8)
				vector3[2][3]=vector3[2][3]+1;
			if(vector2[i][0]>.4 && vector2[i][0]<=.6   && vector2[i][1]>.8 && vector2[i][1]<=1.0)
				vector3[2][4]=vector3[2][4]+1;
			
			if(vector2[i][0]>.6 && vector2[i][0]<=.8   && vector2[i][1]<=.2)
				vector3[1][0]=vector3[1][0]+1;
			if(vector2[i][0]>.6 && vector2[i][0]<=.8   && vector2[i][1]>.2 && vector2[i][1]<=.4)
				vector3[1][1]=vector3[1][1]+1;
			if(vector2[i][0]>.6 && vector2[i][0]<=.8   && vector2[i][1]>.4 && vector2[i][1]<=.6)
				vector3[1][2]=vector3[1][2]+1;
			if(vector2[i][0]>.6 && vector2[i][0]<=.8   && vector2[i][1]>.6 && vector2[i][1]<=.8)
				vector3[1][3]=vector3[1][3]+1;
			if(vector2[i][0]>.6 && vector2[i][0]<=.8   && vector2[i][1]>.8 && vector2[i][1]<=1.0)
				vector3[1][4]=vector3[1][4]+1;
			
			if(vector2[i][0]>.8 && vector2[i][0]<=1.0   && vector2[i][1]<=.2)
				vector3[0][0]=vector3[0][0]+1;
			if(vector2[i][0]>.8 && vector2[i][0]<=1.0   && vector2[i][1]>.2 && vector2[i][1]<=.4)
				vector3[0][1]=vector3[0][1]+1;
			if(vector2[i][0]>.8 && vector2[i][0]<=1.0   && vector2[i][1]>.4 && vector2[i][1]<=.6)
				vector3[0][2]=vector3[0][2]+1;
			if(vector2[i][0]>.8 && vector2[i][0]<=1.0   && vector2[i][1]>.6 && vector2[i][1]<=.8)
				vector3[0][3]=vector3[0][3]+1;
			if(vector2[i][0]>.8 && vector2[i][0]<=1.0  && vector2[i][1]>.8 && vector2[i][1]<=1.0)
				vector3[0][4]=vector3[0][4]+1;
		 }
		double c=1.0;
		System.out.println("\n\n--------TABLAS----------------------------"
				+ "\n\nOij\t0.2\t0.4\t0.6\t0.8\t1.0");
		System.out.println("____________________________________________");
		for(int i=0;i<=4;i++)
		{
			
			System.out.print(a.format(c)+"|");
			c=(c-0.2);
			
			for(int j=0;j<=4;j++)
			{
				System.out.print("\t"+a.format(vector3[i][j]));
			}
			System.out.println("");
		}
		System.out.println();
		
		c=1.0;
		double valor=(double)n/25;
		System.out.println("E\t0.2\t0.4\t0.6\t0.8\t1.0");
		System.out.println("____________________________________________");
		for(int i=0;i<=4;i++)
		{
			
			System.out.print(a.format(c)+"|");
			c=(c-0.2);
			
			for(int j=0;j<=4;j++)
			{
				System.out.print("\t"+a.format(valor));
			}
			System.out.println("");
		}
		System.out.println();
		
		c=1.0;
		System.out.println("(O-E)\n\t0.2\t0.4\t0.6\t0.8\t1.0");
		System.out.println("____________________________________________");
		for(int i=0;i<=4;i++)
		{
			
			System.out.print(a.format(c)+"|");
			c=(c-0.2);
			
			for(int j=0;j<=4;j++)
			{
				System.out.print("\t"+a.format((vector3[i][j]-valor)));
			}
			System.out.println("");
		}
		System.out.println();
		
		c=1.0;
		System.out.println("(O-E)²/E\n\t0.2\t0.4\t0.6\t0.8\t1.0");
		System.out.println("____________________________________________");
		for(int i=0;i<=4;i++)
		{
			
			System.out.print(a.format(c)+"|");
			c=(c-0.2);
			
			for(int j=0;j<=4;j++)
			{
				acuChicuadrada=acuChicuadrada + (((vector3[i][j]-valor)*(vector3[i][j]-valor))/valor);
				System.out.print("\t"+a.format(((vector3[i][j]-valor)*(vector3[i][j]-valor))/valor));
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("Chi² = "+ acuChicuadrada);

		double chicuadrado2410=33.1962;
		if(porcentaje == 1)
		{
			System.out.println("suma Chi² <= Chi² ("+24+",5%) \n"+ Formatea.alinder("###0.0000", acuChicuadrada) +" <= " + Tablas.Chi2do5(24));
			
			if(acuChicuadrada <= Tablas.Chi2do5(24))
				System.out.println("Se acepta H0, números uniformemente distribuidos");
			else
				System.out.println("Se acepta H1, números NO uniformemente distribuidos");
		}
		else
		{
			System.out.println("suma Chi² <= Chi² ("+24+",10%) \n"+ Formatea.alinder("###0.0000", acuChicuadrada) +" <= " + Tablas.Chi2do10(24));
			
			if(acuChicuadrada <= Tablas.Chi2do10(24))
				System.out.println("Se acepta H0, números uniformemente distribuidos");
			else
				System.out.println("Se acepta H1, números NO uniformemente distribuidos");
		}
//		if(acuChicuadrada<chicuadrado2410)
//			System.out.println("Se acepta la prueba");
//		else
//			System.out.println("Se rechaza la prueba");
//		Puntos obj= new Puntos(vector2,500,500);
	}

}
