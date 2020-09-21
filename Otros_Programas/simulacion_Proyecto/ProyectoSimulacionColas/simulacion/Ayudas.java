package simulacion2;

import java.text.DecimalFormat;

public class Ayudas {
	
	int hora = 23;
	
//	public String Blancos(int longitud) 
//	{
//		String blancos = "";
//		
//		for (int i = longitud; i < 15; i++) {
//			
//			blancos += " ";
//			
//		}
//		
//		return blancos;
//	}
	
	public String ConvierteHora(int minutos) {

		DecimalFormat df = new DecimalFormat("00.00");

		String cadenaHora = "";
		int obtieneHora = minutos/60;
		int obtieneMinutos = minutos%60;
		hora = 23;
			hora += obtieneHora;
		if(hora >= 24)
			hora -= 24;
		
//		System.out.print("minuto = " + obtieneMinutos+ "\t");
//		System.out.println("hora = " + obtieneHora);
		if(obtieneMinutos == 5)
		cadenaHora = hora + "." + '0' + obtieneMinutos;
		else
			cadenaHora = hora + "." + obtieneMinutos;
		cadenaHora = df.format(Double.parseDouble(cadenaHora));
		
		String ultimaCadenaHora = "";
		for (int i = 0; i < cadenaHora.length(); i++) {
			
			if(i != 2)
				ultimaCadenaHora += cadenaHora.charAt(i);
			else
				ultimaCadenaHora += ":";
		}
		return ultimaCadenaHora;
	}
}
