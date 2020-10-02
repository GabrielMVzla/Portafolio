package pruebaHerenciaPolimorfismo;

public class Objetoo
{
	double peso, volumen;
	public Objetoo(double peso,double volumen) 
	{
		this.peso = peso;
		this.volumen = volumen;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getVolumen() {
		return volumen;
	}
	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}
	
	protected double SizeTula() {
		
		
		return peso * volumen;
		
	}
}
