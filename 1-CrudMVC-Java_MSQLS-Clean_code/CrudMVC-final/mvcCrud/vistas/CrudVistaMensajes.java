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
			mensajeError = "Para \"" + desde + "\" NO debe estar vacío el campo RFC";

		}
		else if((datos.getRfc().equals("") && !desde.equals(recuperar) && !desde.equals(eliminar)) 
				|| (datos.getNombre().equals("") && !desde.equals(recuperar) && !desde.equals(eliminar)))
		{
			mensajeError = "No debe haber campos vacíos al momento de \"" + desde + "\"";

		}
		else if(datos.getRfc().length() != 10)
		{
			mensajeError = "La cantidad de carácteres es de \""+ datos.getRfc().length() +"\" en el campo RFC (deben ser 10 caracteres)";

		}
		else if((datos.getEdad() < 1 || datos.getIdCiudad() < 1)  && (desde.equals(guardar) || desde.equals(modificar)))//va aquí dentro ya que si es "" hay posibilidad que sea porque alguno de estos datos no cumplía
		{
			mensajeError = "NO debe haber campos vacíos y los campos \"Edad e IdCiudad\"\n"
					+ "deben ser válidos (datos numéricos positivos)";
		}
		else if(!datos.getRfc().matches("^[a-zA-Z]{4}[0-9]{6}?$")) //expresión regular [4 letras][6 dígitos]
//		else if(!datos.getRfc().matches("^[a-zA-Z][a-zA-Z][a-zA-Z][a-zA-Z][0-9][0-9][0-9][0-9][0-9][0-9]?$")) //expresión regular [4 letras][6 dígitos]
		{
			mensajeError = "No se aceptan simbolos especiales y los primeros 4 carácteres del campo RFC deben ser letras";
		}
		
		return mensajeError; //si al final mensajeError queda como "0" es decir que no hubo error
	}
	//Validaciones para VentanaConsulta Método sobrecargado
	public String comprobacionError(String rfcBusqueda)
	{
		String mensajeError = "0";
		
		if(rfcBusqueda.equals(""))
		{
			mensajeError = " NO debe estar vacío el campo RFC busqueda";
		}
		else if(rfcBusqueda.length() != 10)
		{
			mensajeError = "La cantidad de carácteres es de \""+ rfcBusqueda.length() +"\" en el campo RFC busqueda (deben ser 10 caracteres)";

		}
		else if(!rfcBusqueda.matches("^[a-zA-Z]{4}[0-9]{6}?$")) //expresión regular [4 letras][6 dígitos]
		{
			mensajeError = "No se aceptan simbolos especiales y los primeros 4 carácteres del campo RFC busqueda deben ser letras";
		}
		return mensajeError;
		// mandamos llamar directamente para que muestre el msj de error
	}

	public void muestraExitoError(String mensaje)
	{
		JOptionPane.showMessageDialog(null, mensaje);
	}
}

