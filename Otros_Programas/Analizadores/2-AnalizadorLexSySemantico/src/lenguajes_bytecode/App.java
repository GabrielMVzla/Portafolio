/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lenguajes;

import java.util.ArrayList;
//BYTECODE

public class App {


    public static void main(String[] args) {
    	
        Analizador analizador = new Analizador("g.txt"); 
        ArrayList<String> a1 = analizador.resultado;		//si el lexico tiene errores, los imprime en el siguiente for
        Analiza_Semantica as;
		
		if(analizador.bandera == false)
		{
			for(int i = 0; i < a1.size(); i++) 
				System.out.println( a1.get(i) );
			System.exit(0);
		}
		Sintactico s = new Sintactico(analizador.all_tokens, analizador.todos_tokens );
		as = new Analiza_Semantica(s.p, s.bytecodeDo, s.bytecodeUntil);
//		SoloMuestraS sms = new SoloMuestraS(s.p);
    }
    
}
