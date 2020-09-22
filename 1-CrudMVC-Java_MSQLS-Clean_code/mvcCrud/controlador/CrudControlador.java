package controlador;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.SQLException;

import javax.swing.JButton;

import modelo.CrudModelo;
import objetos.ObjetoArrayConsulta;
import objetos.ObjetoPersonaRFC;
import objetos.ObjetoMensajePersonaRFC;
import vistas.CrudVistaConsulta;
import vistas.CrudVistaInicio;

public class CrudControlador implements ActionListener, ComponentListener
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
				return;
			}
			
			Vista.txtNombre.setText(recuperado.getDatos().getNombre());
			Vista.txtEdad.setText(String.valueOf(recuperado.getDatos().getEdad()));
			Vista.txtIdCiudad.setText(String.valueOf(recuperado.getDatos().getIdCiudad()));
			
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
			ObjetoArrayConsulta recuperado = Modelo.consulta(0); //valor Cero, puntoPartidaConsulta comenzará 0 cuando se llama desde aquí
			
			if(!recuperado.getMensaje().equals(mensajeConsultado))//si no es "recuperado" (msj esperado) lanzará una ventana de error desde la Vista
			{
				Vista.muestraExitoError(recuperado.getMensaje());
				return;
			}
			
			VistaConsulta.eliminar();
			// creamos un nuevo objeto para que desde la vista nos acepte el mensaje de error
			VistaConsulta.muestraDatosTabla(recuperado.getPersona(), recuperado.getPagina(), recuperado.getLimiteTuplas(), recuperado.isActivoBtnAnterior(), recuperado.isActivoBtnSiguiente()); 
			VistaConsulta.setVisible(true);
			
			return;
		}
		if(b == VistaConsulta.btnAnterior)
		{
			ObjetoArrayConsulta recuperado = Modelo.consulta(1); //valor 1, puntoPartidaConsulta comenzará en las tuplas mostradas anteriormente
			VistaConsulta.eliminar();
			VistaConsulta.muestraDatosTabla(recuperado.getPersona(), recuperado.getPagina(), recuperado.getLimiteTuplas(), recuperado.isActivoBtnAnterior(), recuperado.isActivoBtnSiguiente()); 
		}
		if(b == VistaConsulta.btnSiguiente) 
		{
			ObjetoArrayConsulta recuperado = Modelo.consulta(2); //valor 2, puntoPartidaConsulta comenzará en el ultimo valor que se quedó a consultar 
			VistaConsulta.eliminar();
			VistaConsulta.muestraDatosTabla(recuperado.getPersona(), recuperado.getPagina(), recuperado.getLimiteTuplas(), recuperado.isActivoBtnAnterior(), recuperado.isActivoBtnSiguiente()); 
		}
	}
	@Override
	public void componentHidden(ComponentEvent e) {}
	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void componentResized(ComponentEvent e) //Incrementamos el tamaño de la fuente dependiendo del tamaño del frame
	{
		Dimension dimensionActual, dimensionInicial;

		dimensionInicial = Vista.dimensionInicial;
		dimensionActual = Vista.getSize();

		int incFuenteLetra = Modelo.calculoIncremento(dimensionInicial, dimensionActual);
	
		Vista.sizeFuenteLetra(incFuenteLetra);
	}
	@Override
	public void componentShown(ComponentEvent e) {}
}
