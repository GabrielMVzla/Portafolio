package lenguajes;

public class Declaraciones {
	
	String nombrevar;
	String primitivo;
	
	public Declaraciones(String nom, String primitiv)
	{
		nombrevar = nom;
		primitivo = primitiv;
		
	}

	public String getNombrevar() {
		return nombrevar;
	}

	public void setNombrevar(String nombrevar) {
		this.nombrevar = nombrevar;
	}

	public String getPrimitivo() {
		return primitivo;
	}

	public void setPrimitivo(String primitivo) {
		this.primitivo = primitivo;
	}
}
