package vistas;

import javax.swing.JOptionPane;

import objetos.ObjetoPersonaRFC;

public class CrudVistaMensajes
{	
	final String guardar = "guardar", recuperar = "recuperar", modificar = "modificar", eliminar = "eliminar", consultar = "consultadoUnico"; 

	//Validaciones para Ventana de inicio
	public String comprobacionError(ObjetoPersonaRFC datos, String desde)
	{
		String mensajeError = "0";
		
		if(datos.getRfc().equals("") && (desde.equals(recuperar) || desde.equals(eliminar) ))
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
		else if((datos.getEdad() < 1 || datos.getIdCiudad() < 1)  && (desde.equals(guardar) || desde.equals(modificar)))//va aqu� dentro ya que si es "" hay posibilidad que sea porque alguno de estos datos no cumpl�a
		{
			mensajeError = "NO debe haber campos vac�os y los campos \"Edad e IdCiudad\"\n"
					+ "deben ser v�lidos (datos num�ricos positivos)";
		}
		else if(!datos.getRfc().matches("^[a-zA-Z]{4}[0-9]{6}?$")) //expresi�n regular [4 letras][6 d�gitos]
//		else if(!datos.getRfc().matches("^[a-zA-Z][a-zA-Z][a-zA-Z][a-zA-Z][0-9][0-9][0-9][0-9][0-9][0-9]?$")) //expresi�n regular [4 letras][6 d�gitos]
		{
			mensajeError = "No se aceptan simbolos especiales y los primeros 4 car�cteres del campo RFC deben ser letras";
		}
		
		return mensajeError; //si al final mensajeError queda como "0" es decir que no hubo error
	}
	//Validaciones para VentanaConsulta M�todo sobrecargado
	public String comprobacionError(String rfcBusqueda)
	{
		String mensajeError = "0";
		
		if(rfcBusqueda.equals(""))
		{
			mensajeError = " NO debe estar vac�o el campo RFC busqueda";
		}
		else if(rfcBusqueda.length() != 10)
		{
			mensajeError = "La cantidad de car�cteres es de \""+ rfcBusqueda.length() +"\" en el campo RFC busqueda (deben ser 10 caracteres)";

		}
		else if(!rfcBusqueda.matches("^[a-zA-Z]{4}[0-9]{6}?$")) //expresi�n regular [4 letras][6 d�gitos]
		{
			mensajeError = "No se aceptan simbolos especiales y los primeros 4 car�cteres del campo RFC busqueda deben ser letras";
		}
		return mensajeError;
		// mandamos llamar directamente para que muestre el msj de error
	}

	public void muestraExitoError(String mensaje)
	{
		JOptionPane.showMessageDialog(null, mensaje);
	}
}

