package expresionesRegulares;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTags
{
	
//	<a>uno</a><b>dos</b><c>tres</c>
	
	public static void main(String[] args) {
		
		String cadena = "<a>uno</a><b>dos</b><c>tres</c>";
		Pattern pattern1 = Pattern.compile("<[^>]*>([^<]*)</[^>]*>");
		Matcher matcher1 = pattern1.matcher(cadena);

		for (int i = 0; i < 1; i++) {
		   while (matcher1.find()) {
		      System.out.println(matcher1.group(1));
		   }
		}
	}
}
