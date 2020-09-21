package analizadorLexico;

	public class InfoToken 
	{	
		private String valor;
		private int tipo;
		private int renglon;
		
		public InfoToken(String valor, int renglon, int tipo) 
		{
			this.valor=valor;
			this.renglon=renglon;
			this.tipo=tipo;
		}
		public int getTipo()
		{
			return tipo;
		}
		public String getValor()
		{
			return valor;
		}
		public int getRenglon() 
		{
			return renglon;
		}
		public void setValor(String valor)
		{
			this.valor = valor;
		}
}
