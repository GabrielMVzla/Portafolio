//Gabriel Montes
package aplicacion;

import controlador.CrudControlador;
import modelo.CrudModelo;
import vistas.CrudVistaConsulta;
import vistas.CrudVistaInicio;

public class CrudAplicacion 
{

	public static void main(String[] args) throws Throwable 
	{
		boolean estadoBD = false;
		CrudVistaInicio VistaI = new CrudVistaInicio();
		CrudVistaConsulta VistaConsulta = new CrudVistaConsulta(VistaI, false);
		CrudModelo Modelo = new CrudModelo(estadoBD);

		VistaI.Muestrate();

		//si no ponemos parametros al sig objeto, todos serían independientes
		CrudControlador Controlador = new CrudControlador(VistaI, Modelo, VistaConsulta, Modelo.getEstado());
		
		VistaI.setControlador(Controlador);
	}
}
