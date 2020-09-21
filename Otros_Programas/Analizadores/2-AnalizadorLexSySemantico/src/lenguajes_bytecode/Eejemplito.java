package lenguajes;

import java.util.ArrayList;

public class Eejemplito {
	
	public static void main(String[] args) {
		
		ArrayList<Integer> dato = new ArrayList<Integer>();
		
		dato.add(1);
		dato.add(2);
		dato.add(4);
		dato.add(5);
		
		for (int i = 0; i < dato.size(); i++) 
		{
			if(i == 2)
				dato.add(2, 3);
			System.out.println(dato.get(i));
			
		}
		
	}

}
