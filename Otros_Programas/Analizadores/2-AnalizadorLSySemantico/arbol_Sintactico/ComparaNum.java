package arbol_Sintactico;

public class ComparaNum  extends Ex{

	public String i1, signo;
	public int e2;
 
//	public ComparaNum(Ex e3, Ex e4) {
//		i1 = e3;
//		e2 = e4;		
//	}
//	public ComparaNum(Numx i1) {
//		// TODO Auto-generated constructor stub
//	}
	public ComparaNum(String tok, int parseInt, String signo) {

		i1 = tok;
		e2 = parseInt;
		this.signo = signo;
	}
	
}


/**
class hola 

boolean m;
float a;
int l;
int j;
{
m = c + false;
c = m * k;
j = j + 2;
do 
	a = h + true; 
	b = m + false;
	System.in.readln (a + 2);
	l = n + 9349;
	b = p * 78;
	System.in.readln ( X < k5);
	System.in.readln ( X < -30.5);
	System.in.readln ( X < false);
	E = flaso + false;
	M = kkk + -30.5;
	
until (a<p);	
}*/