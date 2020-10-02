package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calculadora.Calculadora;

//Con el método "fijarNúmero" redondeamos los decimales tipo **0.999 --> 1.0, 0.4999 --> 0.5** debido
//a que como la máquina funciona en binario trabaja de manera un poco distinta a nuestro sistema decimal,
//hay muchas operaciones con decimales que devuelven un valor de este estilo (2.2 - 2.3 -->  0.0999)
//todo esto para que tome correctamente las respuestas de las pruebas.  -->  (2.2 - 2.3 --> -0.1   )
//Aunque claro no todos las operaciones decimales dan un valor parecido, como el ejemplo de ( 2.2 - 2.2 ) sí despliega un "0 exacto"
/*	  
 *   public static double fijarNumero(double numeroConDecimales, int digitosDecimalesQueQueremosMostrar)
*/
	public class AppPrueba 
	{
	    @Test public void testSuma() 
	    {
	        Calculadora c = new Calculadora();

	        //Se utiliza este método debido a que la computadora devuelve el resultado en muchos decimales (3.5999) en lugar de 3.6
	        //Dentro de este método (fijarNumero) mandamos llamar el método de Suma de Calculadora
	        double mandaSuma = fijarNumero(c.suma(1.3,2.3),1);
	        assertTrue(mandaSuma == 3.6);
	        
	        assertTrue(c.suma(2,3)==5);	        
	    }

	    @Test public void testResta() 
	    {
	        Calculadora c = new Calculadora();
	        //Se utiliza este método debido a que la computadora devuelve el resultado en muchos decimales (-0.0999) en lugar de -0.1
	        //Dentro de este método (fijarNumero) mandamos llamar el método de Resta de Calculadora
	        double mandaResta = fijarNumero(c.resta(2.2,2.3),1);
	        assertTrue(mandaResta == -0.1);
	        
	        assertTrue(c.resta(2.2,2.2) == 0);
	        assertTrue(c.resta(2,3)==-1);
	    }

	    @Test public void testMult() 
	    {
	        Calculadora c = new Calculadora();

	        //se utiliza este método debido a que la computadora devuelve el resultado en muchos decimales (10.7999) en lugar de 10.8
	        //Dentro de este método (fijarNumero) mandamos llamar el método de multiplicación de Calculadora
	        double mandaMult = fijarNumero(c.multiplica(2.4,4.5),1);        
	        assertTrue(mandaMult == 10.8);
	        
	        assertTrue(c.multiplica(2,3)==6);	        
	    }
	    @Test public void testDiv() 
	    {
	    	Calculadora c = new Calculadora();
	    		
	    	assertTrue(c.divide(12,4)==3);
	    	//En este ejemplo lo comparamos con un valor de cadena debido a que eso es lo que devuelve la operacion **12/0**
	    	//Para ello realizamos otro método en la Clase Calculadora que funcione solamente para cuando haya Ceros de 
	    	//segundo parámetro y lo rescatamos en una variable tipo String
	    	assertTrue(c.divideCero(12,0).equals("Infinity"));
	    }
	    @Test public void testSeno() 
	    {
	    	Calculadora c = new Calculadora();
	    	
	        //Se utiliza este método debido a que la computadora devuelve el resultado en muchos decimales (0.4999) en lugar de 0.5
	        //Dentro de este método (fijarNumero) mandamos llamar el método de seno de Calculadora
	        double mandaSeno = fijarNumero(c.seno(30),1);     
	        //mandaMult --> mandaSeno
	        assertTrue(mandaSeno == 0.5);
	    }
	    
	    @Test public void testCoseno() 
	    {
	    	Calculadora c = new Calculadora();
	    	
	        //Se utiliza este método debido a que la maquina nos devuelve un número exponencialmente absurdo (6.123233995736766E-17),
	    	//debido a que quiere	representar un número aproximado a 0, esto solo ocurre cuando intentas ingresar un 90 y un 270
	        //Dentro de este método (fijarNumero) mandamos llamar el método de coseno de Calculadora
	        double mandaCos = fijarNumero(c.coseno(90),1);        
	        assertTrue(mandaCos == 0.0);
	    }
	    
	    @Test public void testTangente() 
	    {
	    	Calculadora c = new Calculadora();
	    	
	        //se utiliza este método debido a que la computadora devuelve el resultado en muchos decimales (0.9999) en lugar de 1
	        //Dentro de este método (fijarNumero) mandamos llamar el método de tangente de Calculadora
	        double mandaTang = fijarNumero(c.tangente(45),1);        
	        assertTrue(mandaTang == 1);
	    }
	    
	    public static double fijarNumero(double numero, int digitos)
	    {
	        double resultado;
	        resultado = numero * Math.pow(10, digitos);
	        resultado = Math.round(resultado);
	        resultado = resultado/Math.pow(10, digitos);
	        return resultado;
	    }
	}
//}
