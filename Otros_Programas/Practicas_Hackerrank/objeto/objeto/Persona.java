package objeto;

public class Persona {

	public String Nombre;
	public String Apellido;
	public int edad;
	
	public Persona(String nombre, String apellido, int edad) {
		
		this.Nombre = nombre;
		Apellido = apellido;
		this.edad = edad;
		
	}
	public String getNombre() {	return Nombre;	}

	public void setNombre(String nombre) {	Nombre = nombre;	}

	public String getApellido() {	return Apellido;	}

	public void setApellido(String apellido) {Apellido = apellido;	}

	public int getEdad() {return edad;}

	public void setEdad(int edad) {this.edad = edad;}
}
