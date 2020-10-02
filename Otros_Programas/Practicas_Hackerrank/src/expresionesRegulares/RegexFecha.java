package expresionesRegulares;

import java.util.regex.Pattern;

public class RegexFecha {
	public static void main(String[] args) {
		
		//acepta formato tipo dia = "de 1 a 2 numeros"/ mes = "de 1 a 2 numeros" /
		//y a�o = "2 o 4 n�meros"
		
		 String regexp = "\\d{1,2}/\\d{1,2}/(\\d{4}|\\d{2})";

		 // Lo siguiente devuelve true
		 System.out.println(Pattern.matches(regexp, "11/12/2014"));
		 System.out.println(Pattern.matches(regexp, "1/12/2014"));
		 System.out.println(Pattern.matches(regexp, "11/2/20"));

//		 Supongamos que queremos que el mes se exprese como "ene", "feb", "mar", ... 
//		 en vez de como un n�mero. Cuando hay varias posibles cadenas v�lidas, en la 
//		 expresi�n regular se ponen entre par�ntesis y separadas por |. Es decir, algo
//		 como esto (ene|feb|mar|abr|may|jun|jul|ago|sep|oct|nov|dic). Si adem�s nos
//		 da igual may�sculas o min�sculas, justo delante ponemos el flag de
//		 case insensitive (?i) (la 'i' es de ignore case)
		 
		 String literalMonthRegexp = "\\d{1,2}/(?i)(ene|feb|mar|abr|may|jun|jul|ago|sep|oct|nov|dic)/\\d{4}";

		 // Lo siguiente devuelve true
		 System.out.println(Pattern.matches(literalMonthRegexp, "11/dic/2014"));
		 System.out.println(Pattern.matches(literalMonthRegexp, "1/nov/2014"));
	}
}
