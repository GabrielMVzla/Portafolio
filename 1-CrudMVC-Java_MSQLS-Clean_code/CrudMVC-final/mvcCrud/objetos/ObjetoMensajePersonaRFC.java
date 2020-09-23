package objetos;

public class ObjetoMensajePersonaRFC
{
	private String mensaje;
	private ObjetoPersonaRFC datos;
	
	public ObjetoMensajePersonaRFC(String mensaje, ObjetoPersonaRFC datos) 
	{
		this.datos = datos;
		this.mensaje = mensaje;
	}
	
	public String getMensaje() 
	{
		return mensaje;
	}
	
	public ObjetoPersonaRFC getDatos() 
	{
		return datos;
	}
	
	public void setMensaje(String mensaje)
	{
		this.mensaje = mensaje;
	}
	public void setDatos(ObjetoPersonaRFC datos)
	{
		this.datos = datos;
	}
}
