package ciclosFor;
//Obtienes la subString dependiendo de los parametros
public class ObtenerSubString
{
	//En este caso lo que mostrará será desde el 3 a 7
	public static void main(String[] args) 
	{
	    String S = "Helloworld";
	    int start = 3;
	    int end = 7;
	
	    String s2 = "";

		for (int i = start; i < end; i++) 
		{
			s2 += S.charAt(i);
		}
		System.out.println(s2);
	}
}
