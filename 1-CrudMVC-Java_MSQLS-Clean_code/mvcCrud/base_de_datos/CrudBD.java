package base_de_datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import objetos.ObjetoArrayConsulta;
import objetos.ObjetoPersonaRFC;
import objetos.ObjetoMensajePersonaRFC;

public class CrudBD 
{
	private Connection conexion;
	private boolean estadoBD;
	int indiceConsulta;
	//private final int cantColumnasDatos = 4;
	
	public CrudBD(boolean estadoBD) throws Throwable 
	{
		indiceConsulta = 0;
		try 
		{
			this.conexion = conexion();
			estadoBD = true;
		} 
		catch (Exception e)
		{
			estadoBD = false;
		}
		this.estadoBD = estadoBD;
		
		
	}
	public boolean getEstado() //importante al momento de ejecutar el programa, si no se establece correctamente la conexion de aquí vamos a obtener ese valor
	{
		return estadoBD;
	}
	
	public Connection conexion() throws Throwable
	{	
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//ruta de tu base de datos y tabla
		String connectionURL =   "jdbc:sqlserver://GABRIEL-PC\\MSSQLSERVER01:1433;databaseName=mvcrud;user=MVCrud;password=123;";
		Connection con = DriverManager.getConnection(connectionURL);

		return con;
	}
	
	//SALVA LA INFORMACIÓN DE USUARIO
	public String guardado(ObjetoPersonaRFC datos) throws SQLException 
	{
		CallableStatement cal = conexion.prepareCall("{call altaPersona(?,?,?,?)}");
		try 
		{
			cal.setString(1, datos.getRfc());					//para rfc
			cal.setString(2, datos.getNombre());				//para nombre
			cal.setInt(3, datos.getEdad());						//para edad
			cal.setInt(4, datos.getIdCiudad());					//para idciudad
			
			cal.execute();
			cal.close();
			
			return "0"; // Si no entramos al catch todo marchó bien
			
		} catch (SQLServerException e1)
		{
			cal.close();
			return e1.getMessage(); //Error al momento de guardar desde el SQL Server
		}
		catch (Throwable e1)
		{
			cal.close();
			return "Error al tratar de guardar los datos"; //Error al momento de guardar inesperado
		}
	}
	
	//RECUPERA LOS DATOS DEL USUARIO POR MEDIO DE SU RFC
	public ObjetoMensajePersonaRFC recuperado(ObjetoPersonaRFC datos) throws Throwable
	{
		String nombre = "";
		int edad = -1, idCiudad = -1;
		ResultSet rs = null;
		ObjetoMensajePersonaRFC oMsjPersona = null;
		
		CallableStatement cal = conexion.prepareCall("{call recuperaPersona(?)}");
		try 
		{
			cal.setString(1, datos.getRfc());
			rs = cal.executeQuery();

			while (rs.next()) 
			{
				nombre = rs.getString(2);
				edad = rs.getInt(3);
				idCiudad  = rs.getInt(4);
			}
			cal.close();
			
			oMsjPersona = new ObjetoMensajePersonaRFC("recuperado", new ObjetoPersonaRFC(datos.getRfc(), nombre, edad, idCiudad));

			return oMsjPersona;
		}
		catch (SQLServerException e1)
		{
			cal.close();

			oMsjPersona = new ObjetoMensajePersonaRFC(e1.getMessage(), null);
			return oMsjPersona;
		}
		catch (Throwable e1)
		{
			cal.close();

			return new ObjetoMensajePersonaRFC("Error al tratar de recuperar los datos", null);
		}
		
	}
	
	//MODIFICA LOS DATOS DEL USUARIO EN BASE A SU RFC
	public String modificado(ObjetoPersonaRFC datos) throws SQLException 
	{
		CallableStatement cal = conexion.prepareCall("{call modificaPersona(?,?,?,?)}");
		try 
		{
			
			cal.setString(1, datos.getRfc());					//para rfc
			cal.setString(2, datos.getNombre());				//para nombre
			cal.setInt(3, datos.getEdad());						//para edad
			cal.setInt(4, datos.getIdCiudad());					//para idciudad
			
			cal.execute();
			cal.close();
			
			return "0";
		}
		catch (SQLServerException e1)
		{
			cal.close();
			return e1.getMessage();
		}
		catch (Throwable e1)
		{
			cal.close();
			return "Error al tratar de modificar los datos";
		}
	}
	
	//ELIMINA USUARIO DE LA TABLA BASE A SU RFC
	public String eliminado(ObjetoPersonaRFC datos) throws SQLException 
	{
		CallableStatement cal = conexion.prepareCall("{call eliminarPersona(?)}");
		try 
		{
			cal.setString(1, datos.getRfc());						//indice 0 para rfc
			
			cal.execute();
			cal.close();
			
			return "0";
		}
		catch (SQLServerException e1)
		{
			cal.close();
			return e1.getMessage();
		}
		catch (Throwable e1)
		{
			cal.close();
			return "Error al tratar de eliminar los datos";
		}
	}
	
	//OBTIENE LAS TUPLAS PARA LA TABLA DE LA VISTA CONSULTA
	final int limiteC = 20; //
	boolean proximoAnterior = false; //cuando próximoAnterior es igual a true, quiere decir que estamos en el tope/final de las tuplas
									 //y para que no regrese 2 saltos, solo será uno debido a que no se actualiza "indiceConsulta asignándole finaC"
	public ObjetoArrayConsulta tablaConsulta(int puntoPartidaConsulta) 
	{
		boolean activoBtnAnterior = true, activoBtnSiguiente = true;
		
		if(puntoPartidaConsulta == 0) //hará consulta desde el comienzo de las tuplas, reinicia el valor de indiceConsulta
			indiceConsulta = 0;
		else if(puntoPartidaConsulta == 1) //consulta las la cantidad de limiteC anterior
		{
			if(!proximoAnterior)
			{
				//2 sumas porque abajo suma a valor limiteC y se cancela esto, estaría consultando lo mismo siempre al volver al anterior
				indiceConsulta -= (limiteC + limiteC);
			}
			else
			{
				indiceConsulta -= (limiteC);
				proximoAnterior = false;
			}
		}
		//el valor de 2 será por descarte (puntoPartidaConsulta == 2)
			
		if(indiceConsulta <= 0)//por si pasa a negativo por parte de la condición (puntoPartidaConsulta == 1), obtendrá desde 0
		{
			activoBtnAnterior = false;
			indiceConsulta = 0;
		}
		
		ArrayList<ObjetoPersonaRFC> devuelveDatos = new ArrayList<ObjetoPersonaRFC>(); //array de objetos, cada indice lleva la info de un renglón
		ResultSet rs = null;
		String rfc = "", nombre = "";
		int edad = -1, idCiudad = -1;
		
		int inicioC = indiceConsulta, finalC = indiceConsulta + limiteC, contadorC = indiceConsulta;
		
		try {
			
			Statement st1 = conexion.createStatement();
			
			rs = st1.executeQuery("select * from datosPersona");
			
			for (int i = 0; i < indiceConsulta; i++) //para comenzar desde el último indice en el que te quedaste
				rs.next();
				
			while(rs.next() && (contadorC < finalC))
			{
				contadorC++;
				rfc = rs.getString(1);
				nombre = rs.getString(2);
				edad = rs.getInt(3);
				idCiudad = rs.getInt(4);
				devuelveDatos.add(new ObjetoPersonaRFC(rfc, nombre, edad, idCiudad));
			}
	
			if(rs.isAfterLast()) //para saber que ya finalizó, si no te da la oportunidad de presionar siguiente en la vistaConsulta si termina en un número cerrado
				
				activoBtnSiguiente = false;
			
			if(contadorC < finalC)
			{
				indiceConsulta = inicioC;
				proximoAnterior = true;
				activoBtnSiguiente = false;
			}
			else 
			{  
				indiceConsulta = finalC;
			}
			
			 return new ObjetoArrayConsulta("consultado", devuelveDatos, inicioC, limiteC, activoBtnAnterior, activoBtnSiguiente);
		} 
		catch (SQLException e)
		{
			 return new ObjetoArrayConsulta(e.getMessage(), devuelveDatos, inicioC, limiteC, activoBtnAnterior, activoBtnSiguiente);
		} 
		catch (Throwable e) 
		{
			 return new ObjetoArrayConsulta("Error al tratar de consultar los datos", devuelveDatos, inicioC, limiteC, activoBtnAnterior, activoBtnSiguiente);
		}
	}
}
