package simulacion2;

public class Cola_Llegadas {

	double random1;		//Numero aleatorio 1
	int tiempoLlegada; 	//tiempo entre llegadas
	String hora;		//Tiempo de llegada
	double random2;		//Numero aleatorio 2
	int tiempoServicio; 	//tiempo de servicios
	
	
	public Cola_Llegadas(double ran1, int timeL, String hr, double ran2, int timeS) 
	{
	
		random1 = ran1;
		tiempoLlegada = timeL;
		hora = hr;
		random2 = ran2;		
		tiempoServicio = timeS; 
		
	}
	
}
