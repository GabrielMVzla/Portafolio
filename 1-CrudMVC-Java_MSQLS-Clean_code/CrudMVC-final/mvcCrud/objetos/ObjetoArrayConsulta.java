package objetos;

import java.util.ArrayList;

public class ObjetoArrayConsulta {
	
	private ArrayList<ObjetoPersonaRFC> persona;
	private String mensaje;
	private int pagina, limiteTuplas;
	private boolean activoBtnAnterior, activoBtnSiguiente;

	public ObjetoArrayConsulta(String mensaje, ArrayList<ObjetoPersonaRFC> persona, int pagina, int limiteTuplas, boolean activoBtnAnterior, boolean activoBtnSiguiente) 
	{
		this.persona = persona;
		this.mensaje = mensaje;
		this.pagina = pagina;
		this.limiteTuplas = limiteTuplas;
		this.activoBtnAnterior = activoBtnAnterior;
		this.activoBtnSiguiente = activoBtnSiguiente;
	}

	public boolean isActivoBtnAnterior() {
		return activoBtnAnterior;
	}

	public void setActivoBtnAnterior(boolean activoBtnAnterior) {
		this.activoBtnAnterior = activoBtnAnterior;
	}

	public boolean isActivoBtnSiguiente() {
		return activoBtnSiguiente;
	}

	public void setActivoBtnSiguiente(boolean activoBtnSiguiente) {
		this.activoBtnSiguiente = activoBtnSiguiente;
	}

	public int getLimiteTuplas()
	{
		return limiteTuplas;
	}
	public void setLimiteTuplas(int limiteTuplas)
	{
		this.limiteTuplas = limiteTuplas;
	}
	
	public int getPagina()
	{
		return pagina;
	}
	public void setPagina(int pagina)
	{
		this.pagina = pagina;
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
