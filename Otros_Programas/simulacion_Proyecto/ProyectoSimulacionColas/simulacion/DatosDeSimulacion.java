package simulacion2;

public class DatosDeSimulacion {
	
	String random1, random2;
	int timeEntreLlega, timeService, ocio, esperaCamion, cola;
	String timeLlega, inicioService, terminaService, string;
	
	public DatosDeSimulacion(String ran1, int timeEL, String timeLl, String inicioS, String ran2, int timeSer, String terminaSer, int ocio, int espCam, int cola)
	{

		random1 = ran1;
		timeEntreLlega = timeEL;
		timeLlega = timeLl;
		inicioService = inicioS;
		random2 = ran2;
		timeService = timeSer;
		terminaService = terminaSer;
		this.ocio = ocio;
		esperaCamion = espCam;
		this.cola = cola;
	}

	public DatosDeSimulacion(String ran1, String string, String hora, String convierteHora, String format,
			int tiempoServicio, String convierteHora2, int ocio2, int esperaCamion2, int cola2) {
		random1 = ran1;
		this.string = string;
		timeLlega = hora;
		inicioService = convierteHora;
		random2 = format;
		timeService = tiempoServicio;
		terminaService = convierteHora2;
		this.ocio = ocio2;
		esperaCamion = esperaCamion2;
		this.cola = cola2;	}

	

}
