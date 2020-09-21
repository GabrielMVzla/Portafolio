package ultimos5Programas;

import java.util.Random;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeriesCollection;

public class PruebaChi2N {
	public PruebaChi2N() {
		main(null);// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		Scanner lee = new Scanner(System.in);
		Random r = new Random();
		int muestra = 0, conta = 0, c = 0, indice = 0, otroconta = 0, porcentaje = 0;
		double antesmodulos = 0, modulos = 0, modulo = 0, k = 0, esperado = 0;
//		porcentaje = 1;
//		while(true) {
//			c = 0;
//			c=100;
		System.out.println("\n------------------------CHI²------------------------\n");

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
		System.out.println("\n\n-----------NÚMEROS RANDOM------------\n");

		double a[] = new double[c];
		muestra = (int) Math.round(Math.sqrt(c));
		double muestraEnt = muestra;
		k = muestra - 1;
		modulo = 1/muestraEnt;
//		System.out.println("modulo = "+modulo);
		for (int i = 0; i < a.length; i++) {
			a[i] = r.nextDouble();
			System.out.print(Formatea.alinder("###", i+1) +".- ");
			System.out.print(Formatea.alinder("0.00000", a[i])+"\t");
			if((i+1)%4 == 0 || i == (c-1))
				System.out.println();
		}
		double inter[] = new double[muestra];
		double modarray[] = new double[muestra];
		double sumaChi[] = new double[muestra];
		
//		System.out.println("muestra = "+muestra+"\nmodulos = "+modulos);
		while(modulos < 0.99999)
		{
			antesmodulos = modulos;
			modulos += modulo;
			modarray[indice] = modulos;
			conta = 0;
			otroconta = 0;
			while(conta < a.length) 
			{	
				if(a[conta] > antesmodulos && a[conta] <= modulos)
				{
					otroconta++;
//					System.out.println("entra "+otroconta);
				}
					conta++;
			}
			inter[indice] = otroconta;
			indice++;
//		System.out.println("antesmodulos = "+modulos);//+"\n"
//						 + "modulos      = "+modulos);
		}
		double longitudA = a.length;
		esperado = longitudA / inter.length;
//		System.out.println("esperado = "+esperado);+
		double suma = 0;
		System.out.println("\n\n------------------- TABLA-------------------\n\n"
					       +  "|-------------------------------------------|\n"
						+     "|  i\t O \t E \t(O-E) \t  (O-E)²/E  |\n"
						+     "|-------------------------------------------|");
		for (int i = 0; i < inter.length; i++) 
		{
			sumaChi[i] = Math.pow(inter[i]-esperado, 2)/esperado;
			System.out.println("|"+Formatea.alinder("0.000", modarray[i])+"\t "+(int)inter[i]+"\t"+Formatea.alinder("#0.00", esperado)+"\t"+Formatea.alinder("#0.00",(inter[i]-esperado))+"\t"+Formatea.alinder("####0.000",sumaChi[i]) +"   |");
			suma += sumaChi[i];
		}
		System.out.println("|-------------------------------------------|");
		System.out.println("Chi² = "+ suma);
		if(porcentaje == 1)
		{
			System.out.println("suma Chi² <= Chi² ("+(int)k+",5%) \n"+ Formatea.alinder("###0.0000", suma) +" <= " + Tablas.Chi2do5((int) k));
			
			if(suma <= Tablas.Chi2do5((int)k))
				System.out.println("Se acepta H0, números uniformemente distribuidos");
			else
				System.out.println("Se acepta H1, números NO uniformemente distribuidos");
		}
		else
		{
			System.out.println("suma Chi² <= Chi² ("+(int)k+",10%) \n"+ Formatea.alinder("###0.0000", suma) +" <= " + Tablas.Chi2do10((int) k));
			
			if(suma <= Tablas.Chi2do10((int)k))
				System.out.println("Se acepta H0, números uniformemente distribuidos");
			else
				System.out.println("Se acepta H1, números NO uniformemente distribuidos");
		}
//	}
		DefaultCategoryDataset categoria= new DefaultCategoryDataset();
		for (int i = 0; i < inter.length; i++) {
			categoria.setValue(inter[i], "", String.valueOf(Formatea.alinder("0.00", modarray[i])));
		}
		JFreeChart f= ChartFactory.createBarChart("Grafica", "Intervalos", "Observado", categoria);
		ChartFrame frame= new ChartFrame("Chi2", f);
		frame.pack();
		frame.setVisible(true);
		
	}
}
