package analizador;

public class Simbolo {

	public String getValor() {
		return valor;
	}
	public void setValor(String f) {
		this.valor = f;
	}

	private int tipo;
	private String token;
	private int renglon;
	private int columna;
	private String tipoNom;
	private String valor;
	
	public Simbolo(String tipoNom, String tok, String valor, int r, int c, int tipo) {
		this.tipo = tipo;
		token = tok;
		this.valor = valor;
		renglon = r;
		columna = c;
		this.tipoNom = tipoNom;
	}
	public void muestra() 
	{
		System.out.println(	tipoNom + Ayudas.blancos(tipoNom.length())					+
							token	+ Ayudas.blancos(token.length()-5)					+
							valor	+ Ayudas.blancos(String.valueOf(valor).length())	+
							renglon + Ayudas.blancos(String.valueOf(renglon).length())	+
							columna	+ Ayudas.blancos(String.valueOf(columna).length())	+
							tipo	+ Ayudas.blancos(String.valueOf(tipo).length())	
							);
	}
	
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getRenglon() {
		return renglon;
	}

	public void setRenglon(int renglon) {
		this.renglon = renglon;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public String getTipoNom() {
		return tipoNom;
	}

	public void setTipoNom(String tipoNom) {
		this.tipoNom = tipoNom;
	}
	
}
