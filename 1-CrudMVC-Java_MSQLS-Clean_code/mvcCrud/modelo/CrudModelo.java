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
	
	//SALVA LA INFORMACI�N DE USUARIO
	public String guarda(ObjetoPersonaRFC datos) throws SQLException // devuelve Mensaje
	{				
		mensaje = comprobacionError(datos, guardar);
		
		if(!mensaje.equals("0")) //si es cualquier otra cosa que no sea "0", quiere decir que hay error antes de mandarlo a la BDD
			return mensaje;
			
		datos.setRfc(casteo(datos.getRfc())); //datos.RFC quien contiene el RFC pasa a may�scula
		nuevoMensaje = bd.guardado(datos);
		
		if(!nuevoMensaje.equals("0")) //si capturamos otro error ahora relacionado con la BDD (diff a 0) mostraremos se actualiza la variable mensaje
			mensaje = nuevoMensaje;
		else
			mensaje = "Haz GUARDADO correctamente";
		
		return mensaje;
	}
	
	//RECUPERA LOS DATOS DEL USUARIO POR MEDIO DE SU RFC
	public ObjetoMensajePersonaRFC recupera(ObjetoPersonaRFC datos) throws Throwable
	{
		ObjetoMensajePersonaRFC valorPersona;
				
		mensaje = comprobacionError(datos, recuperar);
		
		if(!mensaje.equals("0")) 
			return new ObjetoMensajePersonaRFC(mensaje, new ObjetoPersonaRFC("", "", 0, 0)); 
		
		valorPersona = bd.recuperado(datos);//si trae el valor "recuperado" no saltar� una alerta
		
		return valorPersona;
	}
	
	//MODIFICA LOS DATOS DEL USUARIO EN BASE A SU RFC
	public String modifica(ObjetoPersonaRFC datos) throws SQLException 
	{		
		mensaje = comprobacionError(datos, modificar);
		
		if(!mensaje.equals("0")) 
			return mensaje;
			
		datos.setRfc(casteo(datos.getRfc())); 
		
		nuevoMensaje = bd.modificado(datos);
		
		if(!nuevoMensaje.equals("0")) 
			mensaje = nuevoMensaje;
		else
			mensaje = "Haz MODIFICADO correctamente";
		
		return mensaje;	//procedimiento parecido al del m�todo guarda()
	}
	
	//ELIMINA USUARIO DE LA TABLA BASE A SU RFC
	public String elimina(ObjetoPersonaRFC datos) throws SQLException 
	{
		mensaje = comprobacionError(datos, eliminar);
		
		if(!mensaje.equals("0"))
			return mensaje;
		
		nuevoMensaje = bd.eliminado(datos);
		
		if(!nuevoMensaje.equals("0"))
			mensaje = nuevoMensaje;
		else
			mensaje = "Haz ELIMINADO correctamente";
		
		return mensaje;	 //procedimiento parecido al del m�todo guarda()
	}
	
	//OBTIENE LOS REGISTROS PARA LA TABLA DE LA VISTA CONSULTA
	public ObjetoArrayConsulta consulta() 
	{
		return bd.tablaConsulta(); //retornamos un objeto con un mensaje (si hay error o no) y un objeto donde est�n los datos de los usuarios
	}
	
	private String casteo(String datoRFC)
	{
		return datoRFC = datoRFC.toUpperCase(); // pasa a may�scula el dato que recibimos
	}
	
	//Errores
	private String comprobacionError(ObjetoPersonaRFC datos, String desde)
	{
		String mensajeError = "0";
		
		if(datos.getRfc().equals("") && (desde.equals(recuperar) || desde.equals(eliminar)))
		{
			mensajeError = "Para \"" + desde + "\" NO debe estar vac�o el campo RFC";

		}
		else if((datos.getRfc().equals("") && !desde.equals(recuperar) && !desde.equals(eliminar)) 
				|| (datos.getNombre().equals("") && !desde.equals(recuperar) && !desde.equals(eliminar)))
		{
			mensajeError = "No debe haber campos vac�os al momento de \"" + desde + "\"";

		}
		else if(datos.getRfc().length() != 10)
		{
			mensajeError = "La cantidad de car�cteres es de \""+ datos.getRfc().length() +"\" en el campo RFC (deben ser 10 caracteres)";

		}
		else if((datos.getEdad() < -1 || datos.getEdad() == -1)  && (desde.equals(guardar) || desde.equals(modificar)))//va aqu� dentro ya que si es "" hay posibilidad que sea porque alguno de estos datos no cumpl�a
		{
			mensajeError = "NO debe haber campos vac�os y los campos Edad, IdCiudad deben ser datos num�ricos";

		}
		else if(!datos.getRfc().matches("^[a-zA-Z][a-zA-Z][a-zA-Z][a-zA-Z][0-9][0-9][0-9][0-9][0-9][0-9]?$")) //expresi�n regular [4 letras][6 d�gitos]
		{
			mensajeError = "No se aceptan simbolos especiales y los primeros 4 car�cteres del campo RFC deben ser letras";
		}
		else if(((datos.getEdad() < 0 || datos.getIdCiudad() < 0) || (datos.getEdad() > 150 || datos.getIdCiudad() > 150))
				&& (desde.equals("guardar") || desde.equals("modificar")))
		{
			mensajeError = "Ingresa una edad y/o ID de ciudad v�lida";
		}
//		System.out.println("mensajeError = " + mensajeError);
		
		return mensajeError; //si al final mensajeError queda como "0" es decir que no hubo error
	}
	
}
