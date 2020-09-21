package ultimos5Programas;

import java.util.*;

public class Poker {
	public Poker() {
		main(null);// TODO Auto-generated constructor stub
	}
	public static void main(String[] args)
	{
		Scanner lee = new Scanner(System.in);
		Random r = new Random();
		int conta = 0, c = 0, p = 0, p1 = 0, p2 = 0, t = 0, f = 0, pk = 0, q = 0, porcentaje = 0;
		String s = "no";
		String cadena = "", cadena5 = "";
//		porcentaje = 1;
		System.out.println("\n----------------------POKER----------------------\n");
		System.out.print("Ingresa cantidad de números a generar: ");
		c = lee.nextInt();
		while(porcentaje != 1 && porcentaje != 2)
		{
			System.out.print("Opciones\n\"1\".- 5%\n\"2\".- 10%\nIngresa opción: ");
			porcentaje = lee.nextInt();
		}
		System.out.println("\n\n-----------NÚMEROS RANDOM------------\n");

//		c = 36;
		double a[] = new double[c];
		
		System.out.println();
		for (int i = 0; i < c; i++)
		{
			cadena5 = "";
			a[i] = r.nextDouble();
			cadena = cadena.valueOf(a[i]);
			for (int j = 2; j < 7; j++) 
				cadena5 += cadena.charAt(j);
			
			System.out.print(Formatea.alinder("###", i+1) +".- ");
			System.out.print("0."+cadena5+"\t");
			if((i+1)%4 == 0 || i == (c-1))
				System.out.println();
		}
		System.out.println();
		for (int i = 0; i < a.length; i++) 
		{
			cadena5 = "";

			cadena = cadena.valueOf(a[i]);
			for (int j = 2; j < 7; j++) 
				cadena5 += cadena.charAt(j);
			conta = 0;
			for (int j = 0; j < 5; j++) 
			{
//				System.out.println(cadena.charAt(j));
				for (int j2 = 0; j2 < 5; j2++)
				{
					if(cadena5.charAt(j) == cadena5.charAt(j2) && j != j2)
						conta++;
				}
//				System.out.println("contador "+conta);
			}
			if(conta == 0)
				p++;
			if(conta == 2)
				p1++;
			if(conta == 4)
				p2++;
			if(conta == 6)
				t++;
			if(conta == 8)
				f++;
			if(conta == 12)
				pk++;
			if(conta == 20)
				q++;
			
//			System.out.println();
		}
		double pp = (c*0.3024), pp1 = (c*0.5040), pp2 = (c*0.1080), tt = (c*0.0720), ff = (c*0.0090), ppk = (c*0.0045), qq = (c*0.0001);
		System.out.println("----------------------------------------------------------------------");
		System.out.println("|   Evento     | Prob. Esperada |  Fo  |      Fe      |   (O-E)²/E   |\n"
						 + "|--------------------------------------------------------------------|");
		System.out.println("|   Pachucas   |     0.3024     | "+Formatea.alinder("####", p) +" | "+Formatea.alinder("####0.000", pp) +"    |  "+Formatea.alinder("####0.000",(Math.pow(p-pp, 2))/pp)   +"   |\n"
						+  "|   1 par      |     0.5040     | "+Formatea.alinder("####", p1)+" | "+Formatea.alinder("####0.000", pp1)+"    |  "+Formatea.alinder("####0.000",(Math.pow(p1-pp1, 2))/pp1)+"   |\n"
						+  "|   2 pares    |     0.1080     | "+Formatea.alinder("####", p2)+" | "+Formatea.alinder("####0.000", pp2)+"    |  "+Formatea.alinder("####0.000",(Math.pow(p2-pp2, 2))/pp2)+"   |\n"
						+  "|   Tercia     |     0.0720     | "+Formatea.alinder("####", t) +" | "+Formatea.alinder("####0.000", tt) +"    |  "+Formatea.alinder("####0.000",(Math.pow(t-tt, 2))/tt)   +"   |\n"
						+  "|   Full       |     0.0090     | "+Formatea.alinder("####", f) +" | "+Formatea.alinder("####0.000", ff) +"    |  "+Formatea.alinder("####0.000",(Math.pow(f-ff, 2))/ff)   +"   |\n"
						+  "|   Poker      |     0.0045     | "+Formatea.alinder("####", pk)+" | "+Formatea.alinder("####0.000", ppk)+"    |  "+Formatea.alinder("####0.000",(Math.pow(pk-ppk, 2))/ppk)+"   |\n"
						+  "|   Quintilla  |     0.0001     | "+Formatea.alinder("####", q) +" | "+Formatea.alinder("####0.000", qq) +"    |  "+Formatea.alinder("####0.000",(Math.pow(q-qq, 2))/qq)   +"   |");
		System.out.println("----------------------------------------------------------------------");
		
		double suma = Math.pow(q-qq, 2)/qq
					+ Math.pow(pk-ppk, 2)/ppk
					+ Math.pow(f-ff, 2)/ff
					+ Math.pow(t-tt, 2)/tt
					+ Math.pow(p2-pp2, 2)/pp2
					+ Math.pow(p1-pp1, 2)/pp1
					+ Math.pow(p-pp, 2)/pp;
		
		System.out.println("Chi² = "+ suma);
		if(porcentaje == 1)
		{
			System.out.println("suma Chi² <= Chi² (6,5%) \n"+ Formatea.alinder("###0.0000", suma) +" <= " + Tablas.Chi2do5(6));

			if(suma <= Tablas.Chi2do5(6))
				System.out.println("Se acepta H0, números uniformemente distribuidos");
			else
				System.out.println("Se acepta H1, números NO uniformemente distribuidos");
		}
		else
		{
			System.out.println("suma Chi² <= Chi² (6,10%) \n"+ Formatea.alinder("###0.0000", suma) +" <= " + Tablas.Chi2do10(6));
			
			if(suma <= Tablas.Chi2do10(6))
				System.out.println("Se acepta H0, números uniformemente distribuidos");
			else
				System.out.println("Se acepta H1, números NO uniformemente distribuidos");
		}
		
		
		
		

	}

}
