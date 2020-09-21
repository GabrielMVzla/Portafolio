//Clase utilizada para crear una tabla sobre los datos ingresados en la tabla de nuestra Ba
package objetos;

public class ObjetoPersonaRFC 
{

	private String rfc;
	private String nombre;
	private int edad; 		
	private int idCiudad;	
	
	public ObjetoPersonaRFC(String rfc, String nombre, int edad, int idCiudad)
	{
		this.rfc = rfc;
		this.nombre = nombre;
		this.edad = edad;
		this.idCiudad = idCiudad;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getRfc()
	{
		return rfc;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public int getEdad() 
	{
		return edad;
	}

	public int getIdCiudad() 
	{
		return idCiudad;
	}
}
