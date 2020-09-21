package simulacion2;

import java.util.Random;

public class Probabilidades {

	//Calcula cuántos camiones habrá cuando abra el almacén
	public int Apertura() {
	
		int numCamiones = 0;
		Random rnd = new Random();
		
		double random = rnd.nextDouble();
		
		if(random < 0.5)
			numCamiones = 0;
		if(random >= 0.5 && random < 0.75)
			numCamiones = 1;
		if(random >= 0.75 && random < 0.9)
			numCamiones = 2;
		if(random >= 0.9)
			numCamiones = 3;
		
		return numCamiones;
	}
	
	//Calcula tiempo de llegada de cada camión
	public int Llegada(double random) {
		
		int timeLlegada = 0;
		
//		double random = rnd.nextDouble();
		
		if(random < 0.02)
			timeLlegada = 20;
		if(random >= 0.02 && random < 0.1)
			timeLlegada = 25;
		if(random >= 0.1  && random < 0.22)
			timeLlegada = 30;
		if(random >= 0.22 && random < 0.47)
			timeLlegada = 35;
		if(random >= 0.47 && random < 0.67)
			timeLlegada = 40;
		if(random >= 0.67 && random < 0.82)
			timeLlegada = 45;
		if(random >= 0.82 && random < 0.92)
			timeLlegada = 50;
		if(random >= 0.92 && random < 0.97)
			timeLlegada = 55;
		if(random >= 0.97)
			timeLlegada = 60;
		
		return timeLlegada;
		
	}
	//Calcula tiempo de servicio para 3 personas
	public int Servicio3(double random)
	{
		
		int timeService = 0;

		if(random < 0.05)
			timeService = 20;
		if(random >= 0.05 && random < 0.15)
			timeService = 25;
		if(random >= 0.15  && random < 0.35)
			timeService = 30;
		if(random >= 0.35 && random < 0.60)
			timeService = 35;
		if(random >= 0.60 && random < 0.72)
			timeService = 40;
		if(random >= 0.72 && random < 0.82)
			timeService = 45;
		if(random >= 0.82 && random < 0.9)
			timeService = 50;
		if(random >= 0.9 && random < 0.96)
			timeService = 55;
		if(random >= 0.96)
			timeService = 60;
		
		return timeService;
	}
	//Calcula tiempo de servicio para 4 personas
	public int Servicio4(double random)
	{
		
		int timeService = 0;
		
		if(random < 0.05)
			timeService = 15;
		if(random >= 0.05 && random < 0.20)
			timeService = 20;
		if(random >= 0.20  && random < 0.4)
			timeService = 25;
		if(random >= 0.4 && random < 0.60)
			timeService = 30;
		if(random >= 0.60 && random < 0.75)
			timeService = 35;
		if(random >= 0.75 && random < 0.87)
			timeService = 40;
		if(random >= 0.87 && random < 0.95)
			timeService = 45;
		if(random >= 0.95 && random < 0.99)
			timeService = 50;
		if(random >= 0.99)
			timeService = 55;
		
		return timeService;
	}
	//Calcula tiempo de servicio para 5 personas
	public int Servicio5(double random)
	{
		
		int timeService = 0;
		
		if(random < 0.1)
			timeService = 10;
		if(random >= 0.1 && random < 0.28)
			timeService = 15;
		if(random >= 0.28  && random < 0.5)
			timeService = 20;
		if(random >= 0.5 && random < 0.68)
			timeService = 25;
		if(random >= 0.68 && random < 0.78)
			timeService = 30;
		if(random >= 0.78 && random < 0.86)
			timeService = 35;
		if(random >= 0.86 && random < 0.92)
			timeService = 40;
		if(random >= 0.92 && random < 0.97)
			timeService = 45;
		if(random >= 0.97)
			timeService = 50;
		
		return timeService;
	}
	//Calcula tiempo de servicio para 6 personas
	public int Servicio6(double random)
	{
		
		int timeService = 0;
		
		if(random < 0.12)
			timeService = 5;
		if(random >= 0.12 && random < 0.27)
			timeService = 10;
		if(random >= 0.27  && random < 0.53)
			timeService = 15;
		if(random >= 0.53 && random < 0.68)
			timeService = 20;
		if(random >= 0.68 && random < 0.80)
			timeService = 25;
		if(random >= 0.80 && random < 0.88)
			timeService = 30;
		if(random >= 0.88 && random < 0.94)
			timeService = 35;
		if(random >= 0.94 && random < 0.98)
			timeService = 40;
		if(random >= 0.98)
			timeService = 45;
		
		return timeService;
	}
}
