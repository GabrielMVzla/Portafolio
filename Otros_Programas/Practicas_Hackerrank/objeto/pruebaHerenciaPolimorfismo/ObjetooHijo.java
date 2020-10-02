package pruebaHerenciaPolimorfismo;

public class ObjetooHijo extends Objetoo
{

	public ObjetooHijo(double peso, double volumen) {
		super(peso, volumen);
		// TODO Auto-generated constructor stub
	}
	@Override
	public double SizeTula()
	{
		return (super.SizeTula()) + 2 + 3;
	}
}
