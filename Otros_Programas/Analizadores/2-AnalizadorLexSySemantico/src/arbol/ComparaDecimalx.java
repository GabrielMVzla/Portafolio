package arbol;

public class ComparaDecimalx extends Ex{

	public String decimal1, decimal2, operador;
	public char operador1, operador2;
	
	public ComparaDecimalx(char opera1, String dec1, String opera, char opera2, String dec2) 
	{
		operador1 	= opera1;
		decimal1 	= dec1;
		operador 	= opera;
		operador2 	= opera2;
		decimal2 	= dec2;

		decimal1 	= opera1 + dec1;
		decimal2 	= opera2 + dec2;

	}
	public ComparaDecimalx(String dec1, String opera, char opera2, String dec2) 
	{
		decimal1 	= dec1;
		operador 	= opera;
		operador2 	= opera2;
		decimal2 	= dec2;
		
		decimal2 	= opera2 + dec2;
	}
	
	public ComparaDecimalx(char opera1, String dec1) 
	{
		decimal1 	= dec1;
		operador1 	= opera1;
		
		decimal1 	= opera1 + dec1;
	}
}
