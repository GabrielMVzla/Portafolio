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
	//private final int cantColumnasDatos = 4;
	
	public CrudBD(boolean estadoBD) throws Throwable 
	{
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
	//OBTIENE LOS REGISTROS PARA LA TABLA DE LA VISTA CONSULTA
	public ObjetoArrayConsulta tablaConsulta() 
	{
		ArrayList<ObjetoPersonaRFC> devuelveDatos = new ArrayList<ObjetoPersonaRFC>(); //array de objetos, cada indice lleva la info de un renglón
		ResultSet rs = null;
		String rfc = "", nombre = "";
		int edad = -1, idCiudad = -1;
		
		try {
			
			Statement st1 = conexion.createStatement();
			
			rs = st1.executeQuery("select * from datosPersona");

			while(rs.next())
			{
				rfc = rs.getString(1);
				nombre = rs.getString(2);
				edad = rs.getInt(3);
				idCiudad = rs.getInt(4);
				
				devuelveDatos.add(new ObjetoPersonaRFC(rfc, nombre, edad, idCiudad));
			}
			 return new ObjetoArrayConsulta("consultado", devuelveDatos);
		} 
		catch (SQLException e)
		{
			 return new ObjetoArrayConsulta(e.getMessage(), devuelveDatos);
		} 
		catch (Throwable e) 
		{
			 return new ObjetoArrayConsulta("Error al tratar de consultar los datos", devuelveDatos);
		}
	}
}
