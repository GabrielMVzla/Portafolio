package arbol_Sintactico;

public class Decimalx extends Ex{

	public String decimal1, simbolo, i, signo;
	public Ex e1, e2;
	public Decimalx(String i1, String sim, String decimal2, String signo) {
	
		i = i1;
		simbolo = sim;
		decimal1 = decimal2;
		this.signo = signo;
		decimal1 = sim + decimal2;
//		System.out.println("nuevo flotante = " + decimal1);
	}
}
