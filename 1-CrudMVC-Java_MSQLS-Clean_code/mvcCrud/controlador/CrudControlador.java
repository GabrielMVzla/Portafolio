package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import modelo.CrudModelo;
import objetos.ObjetoArrayConsulta;
import objetos.ObjetoPersonaRFC;
import objetos.ObjetoMensajePersonaRFC;
import vistas.CrudVistaConsulta;
import vistas.CrudVistaInicio;

public class CrudControlador implements ActionListener
{
	private CrudVistaInicio Vista; 
	private CrudModelo Modelo;
	private CrudVistaConsulta VistaConsulta;
	private boolean estadoBD;
	
	public CrudControlador(CrudVistaInicio Vista, CrudModelo Modelo, CrudVistaConsulta VistaConsulta, boolean estadoBD) 
	{
		this.Vista = Vista;
		this.Modelo = Modelo;
		this.VistaConsulta = VistaConsulta;
		this.estadoBD = estadoBD;
		estadoBD();
	}
	public boolean estadoBD() 
	{
		if(!estadoBD)
		{
			//la conexión solo la comprobó una vez, y eso te devolvió un valor, ahora solo preguntas siempre por ese valor
			Vista.errorConexion(estadoBD);
			return estadoBD;
		}
		return estadoBD;
	}
	//constantes para validaciones, estos mensajes son de los casos de exito para cada evento
	
	private final String mensajeGuardado = "Haz GUARDADO correctamente",
				 		 mensajeModificado = "Haz MODIFICADO correctamente",
				 		 mensajeEliminado = "Haz ELIMINADO correctamente",
				 		 mensajeRecuperado = "recuperado",
				 		 mensajeConsultado = "consultado";			

	public void actionPerformed(ActionEvent evt)
	{
		if(!estadoBD())
			return;
		//Clase CrudVistaInicio
		if(evt.getSource() == Vista.mntmSalir) //se pone antes para que no haya "conflicto en el casteo a JButton ya que es menuItem"
		{
			System.exit(0);
		}
		
		JButton b = (JButton) evt.getSource();
		ObjetoPersonaRFC datos;
		
		if(b == Vista.btnGuardar)
		{
			datos = Vista.datos();
			String recepcion = null;
			try {
				recepcion = Modelo.guarda(datos);
			} catch (SQLException e) {}

			Vista.muestraExitoError(recepcion); //lanza msj de error en la vista
			
			if(recepcion.equals(mensajeGuardado))
			{
				Vista.limpiarPantalla();
			}
			
			return;
		}
		if(b == Vista.btnRecuperar)
		{		
			datos = Vista.datos();
			//datos almacena los 4 campos, pero solo se usará con el 1ro (RFC)
			ObjetoMensajePersonaRFC recuperado = null;
			try {
				recuperado = Modelo.recupera(datos);//datos - rfc original, Modelo.recuper --> rfc original, nombre, edad, idciudad
			} catch (Throwable e) {	} 
			
			if(!recuperado.getMensaje().equals(mensajeRecuperado))//si no es "recuperado" (msj esperado) lanzará una ventana de error desde la Vista
			{
				Vista.muestraExitoError(recuperado.getMensaje());
				Vista.limpiarPantalla();
			}
			else
			{
				Vista.txtNombre.setText(recuperado.getDatos().getNombre());
				Vista.txtEdad.setText(String.valueOf(recuperado.getDatos().getEdad()));
				Vista.txtIdCiudad.setText(String.valueOf(recuperado.getDatos().getIdCiudad()));
			}
		
			return;
		}
		if(b == Vista.btnModificar)
		{	
			datos = Vista.datos();
			String recuperado = null;
			try 
			{
				recuperado = Modelo.modifica(datos);
			} catch (Exception e) {}
			
			Vista.muestraExitoError(recuperado);
			
			if(recuperado.equals(mensajeModificado)) 
			{
				Vista.limpiarPantalla();
			}
			
			return;
		}
		if(b == Vista.btnEliminar)
		{	
			
			datos = Vista.datos();
			
			String recuperado = null;
			try 
			{
				recuperado = Modelo.elimina(datos);
			} catch (Exception e) {}
			
			Vista.muestraExitoError(recuperado);
			
			if(recuperado.equals(mensajeEliminado)) 
			{
				Vista.limpiarPantalla();
			}
			
			return;
		}
		//Clase CrudModeloVistaConsulta
		if(b == Vista.btnConsultar)
		{
			//obtenemos el dato tipo ObjetoArrayConsulta
			ObjetoArrayConsulta recuperado = Modelo.consulta();
			
			if(!recuperado.getMensaje().equals(mensajeConsultado))//si no es "recuperado" (msj esperado) lanzará una ventana de error desde la Vista
				Vista.muestraExitoError(recuperado.getMensaje());
			else
			{
				VistaConsulta.eliminar();
				// creamos un nuevo objeto para que desde la vista nos acepte el mensaje de error
				VistaConsulta.muestraDatosTabla(recuperado.getPersona()); 
				VistaConsulta.setVisible(true);
			}
			return;
		}
	}	
}
