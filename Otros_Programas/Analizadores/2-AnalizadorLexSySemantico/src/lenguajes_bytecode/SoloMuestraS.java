package lenguajes;

import java.util.ArrayList;

import arbol.Asignacionx;
import arbol.Booleanx;
import arbol.ComparaDecimalx;
import arbol.ComparaId;
import arbol.ComparaNum;
import arbol.Dox;
import arbol.Dx;
import arbol.Printx;
import arbol.Px;
import arbol.Sx;


public class SoloMuestraS {
	
	Px programa;
	ArrayList<Dx> d = new ArrayList<Dx>();
	ArrayList<Sx> s = new ArrayList<Sx>();
	public SoloMuestraS(Px p) {
		
		programa = p;
		ArrayList<Dx> decs;
		ArrayList<Sx> estats;
		d 	= p.declaraciones;
		s 	= p.estatutos;
	
		System.out.println();

		for (int i = 0; i < s.size(); i++) 
		{
			if(s.get(i) instanceof Dox) 
			{
				System.out.println("Dox");

				dox = (Dox) s.get(i);
				if(dox.expresion instanceof ComparaId)
				{
					comparaidx = (ComparaId) dox.expresion;
					System.out.println(comparaidx.identificador1 + " " + comparaidx.operador + " " + comparaidx.identificador2);	
				}	
				if(dox.expresion instanceof ComparaDecimalx)
				{
					
					comparadecimalx = (ComparaDecimalx) dox.expresion;
					System.out.println(comparadecimalx.decimal1 + " " + comparadecimalx.operador + " " + comparadecimalx.decimal2);

				}
				if(dox.expresion instanceof ComparaNum) 
				{
					comparanumx = (ComparaNum) dox.expresion;
					System.out.println(comparanumx.numeroString1+ " " + comparanumx.operador + " " + comparanumx.numeroString2);
				}
				if(dox.expresion instanceof Booleanx) 
				{
					booleanx = (Booleanx) dox.expresion;
					System.out.println(booleanx.booleano);
				}
			}
			if(s.get(i) instanceof Printx) 
			{
				System.out.println("printx");
				printx = (Printx) s.get(i);
				if(printx.expresion instanceof ComparaId)
				{
					comparaidx = (ComparaId) printx.expresion;
					System.out.println(comparaidx.identificador1 + " " + comparaidx.operador + " " + comparaidx.identificador2);	
				}
				if(printx.expresion instanceof ComparaDecimalx)
				{
					comparadecimalx = (ComparaDecimalx) printx.expresion;
					System.out.println(comparadecimalx.decimal1 + " " + comparadecimalx.operador + " " + comparadecimalx.decimal2);
				}
				if(printx.expresion instanceof ComparaNum) 
				{
					comparanumx = (ComparaNum) printx.expresion;
					System.out.println(comparanumx.numeroString1+ " " + comparanumx.operador + " " + comparanumx.numeroString2);
				}
				if(printx.expresion instanceof Booleanx) 
				{
					booleanx = (Booleanx) printx.expresion;
					System.out.println(booleanx.booleano);
				}
			}
			
			if(s.get(i) instanceof Asignacionx) 
			{
				asignacionx = (Asignacionx) s.get(i);
				System.out.print("asignacion\n"+ asignacionx.identificador+" = ");
				if(asignacionx.expresion instanceof ComparaId)
				{
					comparaidx = (ComparaId) asignacionx.expresion;
					System.out.println(comparaidx.identificador1 + " " + comparaidx.operador + " " + comparaidx.identificador2);	
				}
				if(asignacionx.expresion instanceof ComparaDecimalx)
				{
					comparadecimalx = (ComparaDecimalx) asignacionx.expresion;
					System.out.println(comparadecimalx.decimal1 + " " + comparadecimalx.operador + " " + comparadecimalx.decimal2);
				}
				if(asignacionx.expresion instanceof ComparaNum) 
				{
					comparanumx = (ComparaNum) asignacionx.expresion;
					System.out.println(comparanumx.numeroString1+ " " + comparanumx.operador + " " + comparanumx.numeroString2);
				}
				if(asignacionx.expresion instanceof Booleanx) 
				{
					booleanx = (Booleanx) asignacionx.expresion;
					System.out.println(booleanx.booleano);
				}
			}
		}
		
		
	}
	
	Dox			dox;			Printx 			printx;				Asignacionx asignacionx;			
	ComparaId 	comparaidx;		ComparaDecimalx	comparadecimalx;	ComparaNum 	comparanumx;	
	Booleanx  	booleanx;
	int 		load = 0;// 	store = 0;
	
	
	
}
