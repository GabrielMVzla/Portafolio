package aplicacion;

import controlador.CrudControlador;
import modelo.CrudModelo;
import vistas.CrudVistaConsulta;
import vistas.CrudVistaInicio;

public class CrudAplicacion 
{

	public static void main(String[] args) throws Throwable 
	{
		CrudVistaInicio vistaI = new CrudVistaInicio();
		CrudVistaConsulta vistaConsulta = new CrudVistaConsulta(vistaI, false);
		CrudModelo modelo = new CrudModelo();

		vistaI.Muestrate();

		//si no ponemos parametros al sig objeto, todos serían independientes
		CrudControlador Controlador = new CrudControlador(vistaI, modelo, vistaConsulta, modelo.isEstadoBDD());
		//EstadoBDD desde para no ir e ir al modelo y BD desde controlador
		
		vistaI.setControlador(Controlador);
		vistaConsulta.setControlador(Controlador);
	}
}
