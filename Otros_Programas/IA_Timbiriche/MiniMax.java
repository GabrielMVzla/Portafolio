package IA;

public class MiniMax 
{
	public static int[] mover(Tablero node, int depth)
	{
		Tablero raiz = new Tablero(node);
		int mejor = miniMax(raiz, depth, -10000, 10000), movimiento[] = new int[2];
		for(Tablero n : raiz.hijos)
			if(n.val == mejor)
			{
				movimiento[0] = n.getCambiarRenglon();
				movimiento[1] = n.getCambiarColumna();
				return movimiento;
			}
		return movimiento;
	}

	private static int miniMax(Tablero raiz, int depth, int alpha, int beta)
	{
		raiz.agregaHijos();
		if(raiz.depth >= depth || raiz.hijos.isEmpty())
		{
			raiz.heuristica();
			return raiz.val;
		}
		if(raiz.getTurno() == 2)	//2 máquina 
		{
			int valor, mejorRespuesta = -10000;
			for(Tablero n : raiz.hijos)
			{
				valor = miniMax(n, depth - 1, alpha, beta);
				if(valor > mejorRespuesta)
					mejorRespuesta = valor;
				
				if(mejorRespuesta > alpha)
					alpha = mejorRespuesta;
				
				if(beta <= alpha)
					break;
			}
			raiz.val = mejorRespuesta;
			return mejorRespuesta;
		}
		else					//Jugador  
		{
			int valor, mejorRespuesta = 10000;
			for(Tablero n : raiz.hijos)
			{
				valor = miniMax(n, depth - 1, alpha, beta);
				if(valor < mejorRespuesta)
					mejorRespuesta = valor;
				
				if(mejorRespuesta < beta)
					beta = mejorRespuesta;
				
				if(beta <= alpha)
					break;
			}
			raiz.val = mejorRespuesta;
			return mejorRespuesta;
		}
	}
}
