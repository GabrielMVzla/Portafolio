package lenguajes;

//import lista.Nodo;

public class Lista2 <T>{

	@SuppressWarnings("rawtypes")
	Nodo2<T> inicio = null, fin = null;
	public boolean vacia(){
		
		if(inicio != null)
			return false;
		else
			return true;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean insertar(T num){
		
		Nodo2 n = new Nodo2(num);
//		nodo aux = inicio, aux2 = null;
		
//		if(!vacia())
//			System.out.println("\n"+n.numero+"\n");
		if(vacia())
		{
			inicio = n;
			fin = n;		
//			System.out.println("Vacía\n"+n.numero+"\n");
			return true;
			
		}
	
		fin.siguiente = n;			//f		= 6,7
		n.anterior=fin;			//nuevo = 5,6
		fin=n;					//f 	= 6,7
		return true;

	}	
	public void mostrar() {
		Nodo2 aux = inicio;
		System.out.println("\nTokens\n");
		while(aux != null){
			System.out.println( aux.numero );
			aux = aux.siguiente;
		}
	}
	public Nodo2 buscar(T dato){
		Nodo2 Aux=inicio;
		while(Aux !=null){
			if(Aux.numero.equals(dato))
				return Aux;
			Aux=Aux.siguiente;
		}
		return null;
	}
	//Guardo tokens
	public String BuscarxIndex(int dato){
		
		Nodo2 Aux=inicio;
		int indice = 0;
		while(dato != indice) { //recorrerá la lista mediante nodos hasta que dato e indice coincidan
			//si no coincide seguira subiendo indice hasta que lo haga y manda los datos de ese nodo
			indice++;
			Aux = Aux.siguiente;
		}
		if(indice == dato) {
			return (String) Aux.numero;							
		}
//		while(indice != dato){
//			
//			Aux = Aux.siguiente;
//			indice++;
//		}
		return null;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int retirar() {
		Nodo2 aux = inicio, auxant = null;
		
//		if(vaz
		
		System.out.println(inicio.numero);
		inicio = inicio.siguiente;
		

		System.out.println("\nRegistro retirado.");
		return 0;
		
	}
}
