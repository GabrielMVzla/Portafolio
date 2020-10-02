package expresionesRegulares;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class ExpresionREmailExplicacion 
{
	static Scanner lee = new Scanner(System.in);
	
//    public static void main(String[] args) 
//    {
//        String email;
//        boolean emailCorrecto;
//        do{
//        	System.out.print("Ingresa tu email: " );
//            email = lee.nextLine();
////        	email=JOptionPane.showInputDialog("Introduce un email");
//            emailCorrecto=email.matches("[-\\w\\.]+@\\w+\\.\\w+");
//        }while(!emailCorrecto);
// 
//        System.out.println("El email "+email+" es correcto");
//    }
    public static void main(String[] args) 

//    public static void otroCorreo()
    {
    	String correo = "gabriel_12montesvzla@s.c";
    	int i = -1;
    	boolean arrobaPunto = false;
    	
    	if(correo.length()>0)    		
    	{
    		do
	    	{
    			i++;
	    		if(correo.charAt(i) == '@' && i != 0)
	    		{
	    			arrobaPunto = true;
	    			break;
	    		}
	    		else if(comprueba(correo.charAt(i)))
	    		{
	    			System.out.print("" + correo.charAt(i));
	    		}
			}while(i < correo.length()-1);
    	}
    	else 
    	{
    		System.out.println("\ncorreo invalido2");
    		return;
    	}
    	if(!arrobaPunto)
    	{
			System.out.println("\ncorreo invalido3");
			return;
    	}
    	System.out.print("@");
    	arrobaPunto = false;
    	
    	if(i < correo.length()-1)
    	{
    		do
	    	{
				i++;
	    		if(correo.charAt(i) == '.' && i != 0)
	    		{
	    			arrobaPunto = true;
	    			break;
	    		}
	    		else if(comprueba(correo.charAt(i)))
	    		{
	    			System.out.print("" + correo.charAt(i));
	    		}
			}while(i < correo.length()-1);
    	}
    	if(!arrobaPunto)
    	{
			System.out.println("\ncorreo invalidop");
			return;
    	}
    	System.out.print(".");

    	if(i < correo.length()-1)
    	{
    		do
	    	{
				i++;
	    	
	    		if(comprueba(correo.charAt(i)))
	    		{
	    			System.out.print("" + correo.charAt(i));
	    		}
			}while(i < correo.length()-1);
    		System.out.println(" Es un correo correcto");
    	}
    	else
    		System.out.println("\ncorreo Invalido4");
    	
    	
    }
    public static boolean comprueba(char caracter) 
    {
    	if((caracter >= 65 && caracter <= 90)|| (caracter >= 97 && caracter <= 122) || (caracter == 95)
    			|| (caracter >= 48 && caracter <= 57))
    	{
    		return true;
    	}
    	return false;
    }
}
//\b word boundary either the start or end of a word
//?:i da como resultado 2 matches
//El ?: se pone para indicar que NO queremos extraer en nuestro código el trozo que
//está entre estos paréntesis. Recuerda que poner algo entre paréntesis en nuestra
//expresión regular es que vamos a querer extraer ese trozo en nuestro código. Si lo
//encerramos entre (?: ) queremos decir que ese trozo concreto no nos interesa y que
//lo ignore. Necesitamos los paréntesis solo para poner el * detrás.

//\+ para agregar el simbolo más ( + )
//[a-zA-Z]{2,} dos o más letras minúsculas o mayúsculas
//\. para un punto
//\d , dígito, es igual que [0-9]
//\D , no dígito, es igual que [^0-9]
//\s , carácter en blanco, es igual que [\t\n\x0B\f\r]
//\S , no carácter en blanco, es igual que [^\s]
//\w , carácter alfanumérico, es igual que [a-zA-Z_0-9]
//\W , no carácter alfanumérico, es igual que [^\w]
//Opciones de caracteres, se usa el corchete. Por ejemplo, [afgd] significa que puede contener a, f, g o d.
//Negación de caracteres, funciona al revés que el anterior, se usa ^. Por ejemplo, [^afgd]
//Rangos, se usa para que incluya un rango de caracteres. Por ejemplo, para que incluya los caracteres entre a y z [a-z]
//Intersección: permite unir dos condiciones, es como el operador &&.
//Cualquier carácter: se usa un punto.
//Opcional: se usa el símbolo ?, indica que un carácter puede o no aparecer.
//Repetición: se usa el símbolo *, indica que un conjunto de caracteres se pueden repetir o no.
//Repetición obligada: se usa el símbolo +, es como el anterior pero debe aparecer mínimo una vez.
//Repetición un número exacto de veces: después de una expresión abrimos llaves {} 
//con un número dentro, indica el numero de veces que debe repetirse un carácter 
//o expresión. Si después del numero escribimos una coma, indica que debe repetirse 
//como mínimo el número que indiquemos y como máximo los que queramos. Si después 
//de la coma escribimos un número, indica que debe repetirse entre los números que
//le indiquemos como si fuera un rango.
//^	The beginning of a line
//[^ ] lo contrario a lo que esté aquí dentro
//$	The end of a line
//\b	A word boundary
//\B	A non-word boundary
//\A	The beginning of the input
//\G	The end of the previous match
//\Z	The end of the input but for the final terminator, if any
//\z	The end of the input
//\\1 referencia a la primera palabra, So (a)\\1* is equivalent to (a)a* in this particular case.


//The \b boundaries are needed for special cases such as "Bob and Andy" --> and y and"y"
//(we don't want to match "and" twice). Another special case is "My thesis
//is great" (we don't want to match "is" twice).


//Input: txt = "geeksforgeeks geekspractice", pat = "\\bgeeks"
//Output: Found from index 0 to 5 and from index 14 to 19
//Explanation : The pattern "geeks" is present at the beginning
//              of two words "geeksforgeeks" and "geekspractice"
//Input: txt = "geeksforgeeks geekspractice", pat = "geeks\\b"
//Output: Found from index 8 to 13
//Explanation : The pattern "geeks" is present at the end of one
//              word "geeksforgeeks"
