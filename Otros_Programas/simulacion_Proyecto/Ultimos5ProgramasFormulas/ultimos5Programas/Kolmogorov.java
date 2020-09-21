package ultimos5Programas;

import java.util.Random;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Kolmogorov {
	
	public Kolmogorov() {
		main(null);	// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
	
		Scanner lee = new Scanner(System.in);
		Random r = new Random();
		int porcentaje = 0;
		double mayor = 0,c = 0;
//		porcentaje = 1;
//		while(true) {
		System.out.println("\n----------------------KOLMOGOROV----------------------\n");

		while(c<34)  
		{
			System.out.print("Ingresa cantidad de números (cantidad mayor o igual a 34): ");
			c = lee.nextInt();
		}
//			c=36;
		while(porcentaje != 1 && porcentaje != 2)
		{
			System.out.print("Opciones\n\"1\".- 5%\n\"2\".- 10%\nIngresa opción: ");
			porcentaje = lee.nextInt();
		}
		System.out.println("\n\n-----------NÚMEROS RANDOM------------\n");
		double a[] = new double[(int)c];
		double b[] = new double[(int)c];

		for (int i = 0; i < a.length; i++) {
			a[i] = r.nextDouble();
			System.out.print(Formatea.alinder("###", i+1) +".- ");
			System.out.print(Formatea.alinder("0.00000", a[i])+"\t");
			if((i+1)%4 == 0 || i == (c-1))
				System.out.println();
		}
		double aux = 0;
		for(int i = 0; i < a.length-1; i++)
			for(int j = i+1; j < a.length; j++)
			{
				if(a[i]>a[j])
				{			
					aux =  a[i];
					a[i] = a[j];
					a[j] = aux;				
				}
			}
		
		System.out.println("\n---------------------TABLA---------------------\n"
					    +  "\n-----------------------------------------------"
					    +  "\n|   i        Ui           i/N           D     |"
					    +  "\n|---------------------------------------------|");
//		conta = c;
		XYSeries ui = new XYSeries("Ui");
		XYSeries iEntreN = new XYSeries("i / N");
		XYSeries dif = new XYSeries("Mayor separación");
		
		int im = 0;
		for (int i = 0; i < a.length; i++) {
		b[i] = Math.abs(a[i]-((i+1)/c));
//		conta++;
//		b[i] = a[i]-(i/conta);
//		System.out.print("b[i] = "+Formatea.alinder("0.00000",b[i])+"    ");
			System.out.println("|"+Formatea.alinder("####",(i+1))+"|"+Formatea.alinder("#####0.00000", a[i] )+"| "+Formatea.alinder("#####0.00000", ((i+1)/c))+"| "+Formatea.alinder("#####0.00000", b[i])+"|");
			if(b[i] > mayor)
			{
				mayor = b[i];
				im = i;
			}
			
			ui.add((i),a[i]);
			iEntreN.add((i),(i)/c);

		}
		dif.add(im, 1);
		dif.add(im, 0);
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(ui);
		dataset.addSeries(dif);
		dataset.addSeries(iEntreN);
		
		JFreeChart chart = ChartFactory.createXYLineChart("Kolmogorov", "i", "Intervalo", dataset);
			
		ChartFrame frame= new ChartFrame("Grafica Kolmogorov",chart);
		frame.pack();
		frame.setVisible(true);
		System.out.println("-----------------------------------------------");
		System.out.println("D = "+ mayor);
		if(porcentaje == 1)
		{
			System.out.println("Dmayor <= D ("+(int)c+",5%) \n"+ Formatea.alinder("0.00000", mayor) +" <= " + Tablas.Kolmogorov5P((int) c));
			
			if(mayor <= Tablas.Kolmogorov5P((int)c))
				System.out.println("Se acepta H0, números uniformemente distribuidos");
			else
				System.out.println("Se acepta H1, números NO uniformemente distribuidos");
		}
		else
		{
			System.out.println("Dmayor <= D ("+(int)c+",10%) \n"+ Formatea.alinder("0.00000", mayor) +" <= " + Tablas.Kolmogorov10P((int) c));
			
			if(mayor <= Tablas.Kolmogorov10P((int)c))
				System.out.println("Se acepta H0, números uniformemente distribuidos");
			else
				System.out.println("Se acepta H1, números NO uniformemente distribuidos");
		}
	}
}
