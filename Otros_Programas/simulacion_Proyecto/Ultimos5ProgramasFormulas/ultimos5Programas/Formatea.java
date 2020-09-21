package ultimos5Programas;
import java.text.*;


public class Formatea 
{
	static public String local(double dato)
	{
		NumberFormat formato=NumberFormat.getCurrencyInstance();
		String salida=formato.format(dato);
		return salida;
	}
	
	static public String person(String patron, double dato)
	{
		DecimalFormat formato= new DecimalFormat(patron);
		String salida=formato.format(dato);
		return salida;
	}

	static public StringBuffer alinder(String patron, double dato)
	{
		FieldPosition fp=new FieldPosition(NumberFormat.FRACTION_FIELD);
		DecimalFormat formato= new DecimalFormat(patron);
		StringBuffer salida=new StringBuffer();
		formato.format(dato,salida,fp);
	
		for (int i=0; i< (patron.length()-fp.getEndIndex()); i++)
			salida.insert(0,' ');
		return salida;
	}
	
}
