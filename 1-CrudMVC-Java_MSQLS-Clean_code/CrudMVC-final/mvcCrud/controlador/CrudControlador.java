package controlador;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JButton;

import modelo.CrudModelo;
import objetos.ObjetoArrayConsulta;
import objetos.ObjetoPersonaRFC;
import objetos.ObjetoMensajePersonaRFC;
import vistas.CrudVistaInicio;
import vistas.CrudVistaConsulta;
import vistas.CrudVistaMensajes;

public class CrudControlador implements ActionListener, ComponentListener
{
	private CrudVistaInicio vista; 
	private CrudModelo modelo;
	private CrudVistaConsulta vistaConsulta;
	private boolean estadoBD;
	private CrudVistaMensajes vistaPrintMensaje;
	
	public CrudControlador(CrudVistaInicio vista, CrudModelo modelo, CrudVistaConsulta vistaConsulta, boolean estadoBD) 
	{
		this.vista = vista;
		this.modelo = modelo;
		this.vistaConsulta = vistaConsulta;
		this.estadoBD = estadoBD;
		estadoBD();
	}
	public boolean estadoBD() 
	{
		if(!estadoBD)
		{
			//la conexión solo la comprobó una vez, y eso te devolvió un valor, ahora solo preguntas siempre por ese valor, si quieres realizar una acción mostrará este msj hasta estar solucionado
			vista.errorConexion(estadoBD);
			return estadoBD;
		}
		return estadoBD;
	}
	
	//Válida los datos que vengan desde la vista principal, desde la llamada dentro de actionPerformed, si no cumple con lo esperado 
	//va a la vista donde se encuentran los tipos de errores (CrudVistaMensajes) y despliega el error en una ventana JDialog
	private boolean validaDatosVista(ObjetoPersonaRFC datos, String desde)
	{
		String primerBarrera = vistaPrintMensaje.comprobacionError(datos, desde);
		
		if(!primerBarrera.equals("0"))
		{				
			vistaPrintMensaje.muestraExitoError(primerBarrera); //lanza msj de error en la vista
			return false;
		}
		return true;
	}
	
	//constantes para validaciones, estos mensajes son de los casos de exito para cada evento //tipo de error desed el SQL
	private final String mensajeGuardado = "Haz GUARDADO correctamente",
				 		 mensajeModificado = "Haz MODIFICADO correctamente",
				 		 mensajeEliminado = "Haz ELIMINADO correctamente",
				 		 mensajeRecuperado = "recuperado",
				 		 mensajeConsultado = "consultado",			
						 mensajeConsultadoUnico = "consultadoUnico";			
	private final String guardar = "guardar", recuperar = "recuperar", modificar = "modificar", eliminar = "eliminar";//, consultar = "consultar"; //tipo de error donde no se cumple con las características que debe llevar tal campo

	public void actionPerformed(ActionEvent evt)
	{
		if(!estadoBD())
			return;
		//Clase CrudVistaInicio
		if(evt.getSource() == vista.mntmSalir) //se pone antes para que no haya "conflicto en el casteo a JButton ya que es menuItem"
		{
			System.exit(0);
		}
		
		JButton b = (JButton) evt.getSource();
		ObjetoPersonaRFC datos = vista.datos();
		
		vistaPrintMensaje = new CrudVistaMensajes();
		
		String recuperado = "";
		ObjetoArrayConsulta consultaRecuperada = null;
		ObjetoMensajePersonaRFC msjPersonRecuperada = null;

		if(b == vista.btnGuardar)
		{
			if(!validaDatosVista(datos, guardar)) //negarlo quiere decir que no cumple
				return;
			
			try {
				recuperado = modelo.guarda(datos);
			} catch (Throwable e) {}

			vistaPrintMensaje.muestraExitoError(recuperado); //lanza msj de error en la vista
			
			if(recuperado.equals(mensajeGuardado))
			{
				vista.limpiarPantalla();
			}
			
			return;
		}
		if(b == vista.btnRecuperar)
		{		
			if(!validaDatosVista(datos, recuperar)) 
				return;
			
			//datos almacena los 4 campos, pero solo se usará con el 1ro (RFC)
			try {
				msjPersonRecuperada = modelo.recupera(datos);//datos - rfc original, Modelo.recuper --> rfc original, nombre, edad, idciudad
			} catch (Throwable e) {	} 
			
			if(!msjPersonRecuperada.getMensaje().equals(mensajeRecuperado))//si no es "recuperado" (msj esperado) lanzará una ventana de error desde la Vista
			{
				vistaPrintMensaje.muestraExitoError(msjPersonRecuperada.getMensaje());
				vista.limpiarPantalla();
				return;
			}
			
			vista.txtNombre.setText(msjPersonRecuperada.getDatos().getNombre());
			vista.txtEdad.setText(String.valueOf(msjPersonRecuperada.getDatos().getEdad()));
			vista.txtIdCiudad.setText(String.valueOf(msjPersonRecuperada.getDatos().getIdCiudad()));
			
			return;
		}
		if(b == vista.btnModificar)
		{	
			if(!validaDatosVista(datos, modificar)) //negarlo quiere decir que no cumple
				return;
			
			try 
			{
				recuperado = modelo.modifica(datos);
			} catch (Throwable e) {}
			
			vistaPrintMensaje.muestraExitoError(recuperado);
			
			if(recuperado.equals(mensajeModificado)) 
			{
				vista.limpiarPantalla();
			}
			
			return;
		}
		if(b == vista.btnEliminar)
		{	
			if(!validaDatosVista(datos, eliminar)) //negarlo quiere decir que no cumple
				return;
			
			try 
			{
				recuperado = modelo.elimina(datos);
			} catch (Throwable e) {}
			
			vistaPrintMensaje.muestraExitoError(recuperado);
			
			if(recuperado.equals(mensajeEliminado)) 
			{
				vista.limpiarPantalla();
			}
			
			return;
		}
		//para consultar todo desde 0
		if(b == vista.btnConsultar || b == vistaConsulta.btnConsultaTodo)
		{
													 //obtenemos el dato tipo ObjetoArrayConsulta
			consultaRecuperada = modelo.consulta(0); //valor Cero, puntoPartidaConsulta comenzará 0 cuando se llama desde aquí
			
			if(!consultaRecuperada.getMensaje().equals(mensajeConsultado))//si no es "recuperado" (msj esperado) lanzará una ventana de error desde la Vista
			{
				vistaPrintMensaje.muestraExitoError(consultaRecuperada.getMensaje());
				return;
			}
			
			vistaConsulta.eliminar();
			// creamos un nuevo objeto para que desde la vista nos acepte el mensaje de error
			vistaConsulta.muestraDatosTabla(consultaRecuperada.getPersona(), consultaRecuperada.getPagina(), consultaRecuperada.getLimiteTuplas(), consultaRecuperada.isActivoBtnAnterior(), consultaRecuperada.isActivoBtnSiguiente()); 
			vistaConsulta.txtBuscarRfc.setText("");
			vistaConsulta.setVisible(true);

			return;
		}
		if(b == vistaConsulta.btnAnterior) //La escucha de otros eventos puede ser desde otra clase Controlador
		{
			consultaRecuperada = modelo.consulta(1); //valor 1, puntoPartidaConsulta comenzará en las tuplas mostradas anteriormente
			vistaConsulta.eliminar();
			vistaConsulta.muestraDatosTabla(consultaRecuperada.getPersona(), consultaRecuperada.getPagina(), consultaRecuperada.getLimiteTuplas(), consultaRecuperada.isActivoBtnAnterior(), consultaRecuperada.isActivoBtnSiguiente()); 
			return;
		}
		if(b == vistaConsulta.btnSiguiente) 
		{
			consultaRecuperada = modelo.consulta(2); //valor 2, puntoPartidaConsulta comenzará en el ultimo valor que se quedó a consultar 
			vistaConsulta.eliminar();
			vistaConsulta.muestraDatosTabla(consultaRecuperada.getPersona(), consultaRecuperada.getPagina(), consultaRecuperada.getLimiteTuplas(), consultaRecuperada.isActivoBtnAnterior(), consultaRecuperada.isActivoBtnSiguiente()); 
			return;
		}
		if(b == vistaConsulta.btnBuscarRfc) 
		{
			String primerBarrera = vistaPrintMensaje.comprobacionError(vistaConsulta.txtBuscarRfc.getText()); //verifica en método sobrecargado
			
			if(!primerBarrera.equals("0")) //negarlo quiere decir que no cumple
			{
				vistaPrintMensaje.muestraExitoError(primerBarrera);

				return;
			}
			
			try {
				msjPersonRecuperada = modelo.consulta(vistaConsulta.txtBuscarRfc.getText());
			} catch (Throwable e) {}
			//valor tipo String se sobrecarga en modelo
			if(!msjPersonRecuperada.getMensaje().equals(mensajeConsultadoUnico))//si no es "recuperado" (msj esperado) lanzará una ventana de error desde la Vista
			{
				vistaPrintMensaje.muestraExitoError(msjPersonRecuperada.getMensaje());
				return;
			}
			vistaConsulta.eliminar();
			vistaConsulta.muestraDatosTabla(msjPersonRecuperada.getDatos()); 
			vistaConsulta.txtBuscarRfc.setText("");
			return;
		}
	}
	@Override
	public void componentResized(ComponentEvent e) //Incrementamos el tamaño de la fuente dependiendo del tamaño del frame
	{
		Dimension dimensionActual, dimensionInicial;

		dimensionInicial = vista.dimensionInicial;
		dimensionActual = vista.getSize();

		int incFuenteLetra = vista.calculoIncremento(dimensionInicial, dimensionActual);
	
		vista.sizeFuenteLetra(incFuenteLetra);
	}

	
	@Override
	public void componentShown(ComponentEvent e) {}
	@Override
	public void componentHidden(ComponentEvent e) {}
	@Override
	public void componentMoved(ComponentEvent e) {}
}
