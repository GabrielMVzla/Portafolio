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
		CrudVistaInicio vistaI = new CrudVistaInicio();
		CrudVistaConsulta vistaConsulta = new CrudVistaConsulta(vistaI, false);
		CrudModelo modelo = new CrudModelo(estadoBD);

		vistaI.Muestrate();

		//si no ponemos parametros al sig objeto, todos serían independientes
		CrudControlador Controlador = new CrudControlador(vistaI, modelo, vistaConsulta, modelo.getEstado());
		
		vistaI.setControlador(Controlador);
		vistaConsulta.setControlador(Controlador);
	}
}
