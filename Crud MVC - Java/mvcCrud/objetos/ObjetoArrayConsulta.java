package objetos;

import java.util.ArrayList;

public class ObjetoArrayConsulta {
	
	private ArrayList<ObjetoPersonaRFC> persona;
	private String mensaje;

	public ObjetoArrayConsulta(String mensaje, ArrayList<ObjetoPersonaRFC> persona) 
	{
		this.persona = persona;
		this.mensaje = mensaje;
	}

	public ArrayList<ObjetoPersonaRFC> getPersona() {
		return persona;
	}

	public void setPersona(ArrayList<ObjetoPersonaRFC> persona) {
		this.persona = persona;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
