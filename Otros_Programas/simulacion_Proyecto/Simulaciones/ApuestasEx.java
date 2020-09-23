package exa;

import java.util.*;

public class Examen 
{
	public static void main(String[] args) 
	{
		Random rdm = new Random();
		Scanner lee = new Scanner(System.in);
		String gana = "";
		int simulaciones = 10;
		double valor = 0, c = 0;
		
		System.out.println("Ingresa el número de simulaciones: ");
		simulaciones = lee.nextInt();
		
		System.out.println("--------------------------------------------------------------------------------------------");		
		System.out.println("|corrida| antes del volado|   apuesta   | número random|   gana   |  después del volado    |");
		for (int j = 0; j < simulaciones; j++) {
			System.out.println("--------------------------------------------------------------------------------------------");		
			
			double num = rdm.nextDouble();
			int v = 1,  apuesta = 10, despues = 30, valorant; 		//DECLARA Y RESETEA VALORES
			double anteriorapuesta = apuesta;
			
			while(v == 1) //mientras v equivalga a 1 se repetirá el ciclo
			{
				num = rdm.nextDouble();
				
				anteriorapuesta = apuesta;		 //guarda apuesta actual
				valorant = despues;				 //guarda valor "despúes de volado"
				
				if(num > .5) 
				{
					despues = valorant - apuesta; //calcula valor "después de volado"
					gana = "no";
					apuesta = apuesta * 2;		  //multiplica apuesta por 2
					if(anteriorapuesta > valorant) 
					{
						anteriorapuesta = valorant;	//no se adapta en caso de que apuesta sobrepase el dinero
						despues = 0;				//para no hacer el valor negativo
					}
				}
				else 
				{
					if(anteriorapuesta > valorant) 
						anteriorapuesta = valorant;	//no se adapta en caso de que apuesta sobrepase el dinero
					
					despues = valorant + (int) anteriorapuesta; //calcula valor "despues de volado"
					gana = "sí";
					apuesta = 10;								//reestablece apuesta
				}
				if(valorant >= 0 && valorant <= 50)
					System.out.print("|  "+(j+1)+"    |       "+ valorant +"\t  |\t"+anteriorapuesta+"    |" 
						+ "    "+Formatea.alinder("0.0000", num)+"    |    "+gana+"    |    "+despues);

				//calcula si simulación actual es un éxito para después terminar el ciclo
				if(despues >= 50)	
				{
					c++;
					System.out.print("   >>>>>> Sí      |\n");
					v = 0;				
				}
				else if(despues  <= 0)
				{
					System.out.print("    >>>>>> No      |\n");
					v = 0;
				}
				else 
					System.out.print("                  |\n");
			}
				System.out.println("--------------------------------------------------------------------------------------------");		
		}
		valor = c / simulaciones; 
		System.out.println("Probabilidad de llegar a la meta es: "+ valor+"   "+ c);
	}
}
