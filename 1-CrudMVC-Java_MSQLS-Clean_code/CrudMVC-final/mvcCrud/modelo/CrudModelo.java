package modelo;

import java.sql.SQLException;

import base_de_datos.CrudBD;
import objetos.ObjetoArrayConsulta;
import objetos.ObjetoPersonaRFC;
import objetos.ObjetoMensajePersonaRFC;

public class CrudModelo  
{
	private CrudBD bd;
	private boolean estadoBD;

	private String mensaje = "", nuevoMensaje = "";
	
	//constantes para validaciones
	final String guardar = "guardar", recuperar = "recuperar", modificar = "modificar", eliminar = "eliminar"; 
	
	public CrudModelo(boolean estadoBD) throws Throwable 
	{
		bd = new CrudBD(estadoBD);
		this.estadoBD = bd.getEstado();
	}

	public boolean getEstado()
	{
		return estadoBD;
	}
	
	//SALVA LA INFORMACIÓN DE USUARIO
	public String guarda(ObjetoPersonaRFC datos) throws SQLException // devuelve Mensaje
	{				
		datos.setRfc(casteo(datos.getRfc())); //datos.RFC quien contiene el RFC pasa a mayúscula
		nuevoMensaje = bd.guardado(datos);    //mensaje recuperado de la BDD
		
		if(!nuevoMensaje.equals("0")) //si capturamos otro error ahora relacionado con la BDD (diff a 0) mostraremos se actualiza la variable mensaje
			mensaje = nuevoMensaje;
		else
			mensaje = "Haz GUARDADO correctamente";
		
		return mensaje;
	}
	
	//RECUPERA LOS DATOS DEL USUARIO POR MEDIO DE SU RFC
	public ObjetoMensajePersonaRFC recupera(ObjetoPersonaRFC datos) throws Throwable
	{	
		ObjetoMensajePersonaRFC valorPersona = bd.recuperado(datos);//si trae el valor "recuperado" no saltará una alerta
		
		return valorPersona;
	}
	
	//MODIFICA LOS DATOS DEL USUARIO EN BASE A SU RFC
	public String modifica(ObjetoPersonaRFC datos) throws SQLException 
	{					
		datos.setRfc(casteo(datos.getRfc())); 
		
		nuevoMensaje = bd.modificado(datos);
		
		if(!nuevoMensaje.equals("0")) 
			mensaje = nuevoMensaje;
		else
			mensaje = "Haz MODIFICADO correctamente";
		
		return mensaje;	//procedimiento parecido al del método guarda()
	}
	
	//ELIMINA USUARIO DE LA TABLA BASE A SU RFC
	public String elimina(ObjetoPersonaRFC datos) throws SQLException 
	{		
		nuevoMensaje = bd.eliminado(datos);
		
		if(!nuevoMensaje.equals("0"))
			mensaje = nuevoMensaje;
		else
			mensaje = "Haz ELIMINADO correctamente";
		
		return mensaje;	 //procedimiento parecido al del método guarda()
	}
	
	//OBTIENE LOS REGISTROS PARA LA TABLA DE LA VISTA CONSULTA
	public ObjetoArrayConsulta consulta(int puntoPartidaConsulta) 
	{
		return bd.tablaConsulta(puntoPartidaConsulta); //retornamos un objeto con un mensaje (si hay error o no) y un objeto donde están los datos de los usuarios
	}
	//Sobrecargado para dato especifico
	public ObjetoMensajePersonaRFC consulta(String rfc) throws SQLException 
	{
		return bd.tablaConsulta(rfc); //retornamos un objeto con un mensaje (si hay error o no) y un objeto donde están los datos de los usuarios
	}
	
	private String casteo(String datoRFC)
	{
		return datoRFC = datoRFC.toUpperCase();// pasa a mayúscula el dato que recibimos
		
	}
	
}
