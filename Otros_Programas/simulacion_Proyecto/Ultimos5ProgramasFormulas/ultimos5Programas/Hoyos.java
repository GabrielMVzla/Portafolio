package ultimos5Programas;

import java.util.Random;
import java.util.Scanner;

public class Hoyos {
	public Hoyos() {
		main(null);	// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		
		Scanner lee = new Scanner(System.in);
		Random r = new Random();
		int muestra = 0, conta = 0, c = 0, diferentes = 0, ceros = 0, porcentaje = 0, mayor = 0, indice = 0;
		double valorI = 0, valorF = 0, antesconta = 0, modulos = 0, modulo = 0, k = 0, esperado = 0;
//		while(true) {
			c = 0;
//			porcentaje = 1;
//			c=36;
			System.out.println("\n------------------------DISTANCIAS------------------------\n");

		while(c<34)  
		{
			System.out.print("Ingresa cantidad de números (cantidad mayor o igual a 34): ");
			c = lee.nextInt();
		}
		while(porcentaje != 1 && porcentaje != 2)
		{
			System.out.print("Opciones\n\"1\".- 5%\n\"2\".- 10%\nIngresa opción: ");
			porcentaje = lee.nextInt();
		}
		
		while(valorI <=0 || valorI >1 || valorF < valorI || valorF <= 0 || valorF > 1) {
		System.out.print("\nValores de TETA entre 0 y 1"
				+ "\nIngresa el valor Inicial de TETA: ");
		valorI = lee.nextDouble();
//		valorI = .3;
		System.out.print("Ingresa el valor Final de TETA  : ");
		valorF = lee.nextDouble();
//		valorF = .65;
		}
		System.out.println("\n\n-----------NÚMEROS RANDOM------------\n");
		
		double a[] = new double[c];
		muestra = (int) Math.round(Math.sqrt(c));
		double muestraEnt = muestra;
		k = muestra - 1;
		modulo = 1/muestraEnt;
//		System.out.println("modulo = "+modulo);
		System.out.println();
		for (int i = 0; i < a.length; i++) {
			a[i] = r.nextDouble();
			System.out.print(Formatea.alinder("###", i+1) +".- ");
			System.out.print(Formatea.alinder("0.00000", a[i])+"\t");
			if((i+1)%4 == 0 || i == (c-1))
				System.out.println();
		}
		int E[] = new int[a.length];
		System.out.println("\n---------------TABLA----------------");
		System.out.println("--------------------------------------\n"
					     + "|  n    #Random         E       i    |\n"
					     + "|------------------------------------|");
		for (int i = 0; i < a.length; i++) {
			
			if(a[i] > valorI && a[i] < valorF)
				E[i] = 1;
			else 
				E[i] = 0;
		}
		for (int i = 0; i < a.length; i++) {
			antesconta = conta;
			System.out.print("|"+Formatea.alinder("###",(i+1))+"\t"+Formatea.alinder("0.00000", a[i])+"\t\t"+E[i]+"    ");
//			
//			if(E[i] == 1)
//				if(i < a.length-1 && E[i+1] == 0 )
//				{
////					conta++;
////					antesconta = conta;
//					conta = 0;
//				}
////				else conta++;
			if(E[i] == 0)
				if(i < a.length-1 && E[i+1] == 1 )
				{
//					conta++;
					conta = 0;
				}
				else conta++;
			
			if(E[i] == 1)
				if(i==a.length-1)
				{
					ceros++;
					System.out.print("\t"+Formatea.alinder("##", (int)(0))+"   |\n"
									+ "|------------------------------------|");
					
				}
				else if(E[i+1] == 1 && i<a.length-1)
					System.out.print("        |");
				else
				{
					ceros++;
					System.out.print("\t"+Formatea.alinder("##", (int)(0))+"   |\n"
							+ "|------------------------------------|");
				}
			if(E[i] == 0)
				if(i==a.length-1) 
				{
					if((antesconta+1) > mayor )
						mayor = (int) (antesconta+1);
					diferentes++;
					System.out.print("\t"+Formatea.alinder("##", (int)(antesconta+1))+"   |\n"
							+ "|------------------------------------|");
					
				}
				else if(E[i+1] == 0 && i<a.length-1)
					System.out.print("        |");
				else
				{
					if((antesconta+1) > mayor )
						mayor = (int) (antesconta+1);
					diferentes++;
					System.out.print("\t"+Formatea.alinder("##", (int)(antesconta+1))+"   |\n"
							+ "|------------------------------------|");

				}
			System.out.println();
		}
//		System.out.println("Diferentes = "+diferentes+" Ceros = "+ceros+" mayor = "+mayor);
		System.out.println();
		int mayornum[] = new int[diferentes+1], indiceind = 0;
		antesconta = 0;
		conta = 0;
		diferentes = 0;
		ceros = 0;
		for (int i = 0; i < a.length; i++)
		{
			antesconta = conta;
			if(E[i] == 0)
				if(i < a.length-1 && E[i+1] == 1 )
					conta = 0;
				else conta++;
			
			if(E[i] == 1)
				if(i==a.length-1)
					ceros++;
			
				else if(E[i+1] == 1 && i<a.length-1)
					System.out.print("");
				else
					ceros++;
			if(E[i] == 0)
				if(i==a.length-1) 
				{
//					if((antesconta+1) > mayor )
//						mayor = (int) (antesconta+1);
					diferentes++;
					mayornum[indice] = (int)(antesconta+1);
					indice++;
				}
				else if(E[i+1] == 0 && i<a.length-1)
					System.out.print("");
				else
				{
//					if((antesconta+1) > mayor )
//						mayor = (int) (antesconta+1);
					diferentes++;
					mayornum[indice] = (int)(antesconta+1);
					indice++;
									}
//			System.out.println();
		}
//		for (int i = 0; i < mayornum.length; i++)
//			System.out.println("mayornum = "+mayornum[i]);
		
		double aux = 0;
		for(int i = 0; i < mayornum.length-1; i++)
			for(int j = i+1; j < mayornum.length; j++)
			{
				if(mayornum[i]>mayornum[j])
				{			
					aux =  mayornum[i];
					mayornum[i] = mayornum[j];
					mayornum[j] = (int)aux;				
				}
			}
				
				
		int ind[] = new int[mayornum.length];
		
		for (int i = 0; i < mayornum.length; i++)
		{
			if( i < mayornum.length-1 && mayornum[i] == mayornum[i+1] )
				ind = new int[ind.length-1];
//			System.out.println(mayornum[i]);
			
		}
		int indO[] = new int[ind.length], contaO = 0;
		indice = 0;
		for (int i = 0; i < mayornum.length; i++) {//OBSERVADO
			if(i == mayornum.length-1)// la ultima vuelta entra aqui, pq la condicion de abajo supera los limites
			{
					indO[indice] = (contaO+1);
					indice++;
			}
			else if(mayornum[i] == mayornum[i+1])
				contaO++;
			else {
				
				indO[indice] = (contaO+1);
				indice++;
				contaO = 0;
			}
				
		}
//		for (int i = 0; i < indO.length; i++) {
//			System.out.println("indO = "+ indO[i]);
//		}
		
		
		for (int i = 0; i < mayornum.length; i++) {
			if( i < mayornum.length-1 && mayornum[i] != mayornum[i+1] )
			{
				ind[indiceind] = mayornum[i];
				indiceind++;
			}
			else if(i == mayornum.length-1)
				ind[indiceind] = mayornum[i];
		}
		 int contamayores = 0;
		for (int i = 0; i < ind.length-1; i++) //contando observado de los >=
		{
//			System.out.println("ind---- "+ind[i]);
			if(ind[i]+1 != ind[i+1] || i == ind.length-2) 
			{
//				System.out.println("entra?");
				for (int l = 0; l < mayornum.length; l++) {
					if(ind[i]+1 <= mayornum[l])
						contamayores++;
				}
				break;
			}

		}
		double teta = valorF - valorI, observadoEc1 = 0, observadoEc2 = 0, FE = 0, suma = 0, chi2do = 0;
		int observados = diferentes + ceros, grados = 0;
//		System.out.println("observado = "+observados);
		//hacer el conteo antes del break arriba de este for
		System.out.println("Valor TETA = "+Formatea.alinder("0.00", Math.abs(valorF-valorI))+"\n"
					   + "------------------------------------------------"
					   + "\n|  i        PI      FO      FE      (FO-FE)²/E |"
					   + "\n------------------------------------------------");
		for (int i = 0; i < ind.length-1; i++)
		{
			chi2do = 0;
//			System.out.println("ind = "+ind[i]);
//			if(i == ind.length-2)
//				System.out.println("ind = "+ind[i+1]);
			observadoEc1 = Math.abs(Math.pow(((teta-1)), (ind[i]))*teta);
			observadoEc2 = Math.abs(Math.pow((teta-1), (ind[i]+1)));
			if(i == 0)
			{
				FE = teta*observados;	
				chi2do  = Math.pow((ceros-FE), 2)/FE;
				System.out.println("|  "+ind[i] +   "      "+Formatea.alinder("0.00000", teta)       +"   "+Formatea.alinder("##", ceros)    +    "    "+Formatea.alinder("0.00000", FE)+"    "+Formatea.alinder("0.00000", chi2do)  +"   |"); //solo para el indice 0
			}
			else 
			{
				FE = observadoEc1*observados;
				chi2do  = Math.pow((indO[i]-FE), 2)/FE;
				System.out.println("|  "+ind[i]   + "      "+Formatea.alinder("0.00000",observadoEc1)+"   "+Formatea.alinder("##",indO[i] )  +   "    "+Formatea.alinder("0.00000", FE)+"    "+Formatea.alinder("0.00000", chi2do)  +"   |");
			}
			suma += chi2do;
			if(ind[i]+1 != ind[i+1] || i == ind.length-2) 
			{
				FE = observadoEc2*observados;
				chi2do  = Math.pow((contamayores-FE), 2)/FE;
				suma += chi2do;
				grados = ind[i]+ 1;
				System.out.println("|>="+(ind[i]+1)+"      "+Formatea.alinder("0.00000",observadoEc2)+"   "+Formatea.alinder("##",contamayores)+ "    "+Formatea.alinder("0.00000", FE)+"    "+Formatea.alinder("0.00000", chi2do)  +"   |");
//				System.out.println("|>="+(ind[i]+1)+"      "+Formatea.alinder("0.00000",Math.abs(Math.pow((teta-1), (ind[i]+1))))     +"   "+contamayores);
				break;				
			}
//			if(i == ind.length-2)
//			 System.out.println("|  "+ind[i+1]+"  "+teta+"   "+indO[i]);//despliega el ultimo
		
//				System.out.println("aquí ya no son iguales "+ind[i]+" y "+ind[i+1]);
		}
		System.out.println("------------------------------------------------"
					   + "\n                     "+observados+"              "+Formatea.alinder("0.00000", suma));
		
		System.out.println("Chi² = "+ suma);
		if(porcentaje == 1)
		{
			System.out.println("suma Chi² <= Chi² ("+grados+",5%) \n"+ Formatea.alinder("###0.0000", suma) +" <= " + Tablas.Chi2do5(grados));
			
			if(suma <= Tablas.Chi2do5(grados))
				System.out.println("Se acepta H0, números uniformemente distribuidos");
			else
				System.out.println("Se acepta H1, números NO uniformemente distribuidos");
		}
		else
		{
			System.out.println("suma Chi² <= Chi² ("+grados+",10%) \n"+ Formatea.alinder("###0.0000", suma) +" <= " + Tablas.Chi2do10(grados));
			
			if(suma <= Tablas.Chi2do10(grados))
				System.out.println("Se acepta H0, números uniformemente distribuidos");
			else
				System.out.println("Se acepta H1, números NO uniformemente distribuidos");
		}
	}
}
