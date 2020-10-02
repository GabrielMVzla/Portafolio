package practicas;

public class ConvertirHora
{

	public static void main(String[] args) 
	{
		//01:00:00pm/am - 12:05:45pm/am
	     String s = "01:05:45pM";
	     
	     String dosDigitos = "";
	     int segDig = 0;
	     
	        if(s.charAt(s.length()-2)== 'P' || s.charAt(s.length()-2)== 'p')
	        {
	            for (int i = 0; i < s.length(); i++) 
	            {
	            	
	            	 if(i >= 2)
		                dosDigitos += s.charAt(i);
		                
	            	 if(i == 0 && (s.charAt(0) == '1' && s.charAt(1) != '2') && (s.charAt(1) < '8')) 
	            	 {
	            		 dosDigitos = (char)((int) (s.charAt(0) + 1)) + "" + (char)((int) (s.charAt(1) + 2)) + "";
	            		 i++;
	            	 }
	                if(i == 0 && ((s.charAt(0) != '1' && s.charAt(1) != '2') && (s.charAt(1) < '8') || (s.charAt(0) == '0' && s.charAt(1) == '2')) && (s.charAt(1) < '8')) 
	                {
	                	dosDigitos = (char)((int) (s.charAt(0) + 1)) + "" + (char)((int) (s.charAt(1) + 2)) + "";
	                	i++;
	                }
	                else if(i == 0 && (s.charAt(0) != '1' && s.charAt(1) != '2') && (s.charAt(1) >= '8')) 
	                {
	                	segDig = (char)((int) (s.charAt(1) + 2)); // 8 + 2 = ':' en ascii
	                	
	                	if(s.charAt(1) + 2 > '9')
	                	{
	                		segDig = ((int)s.charAt(1) + 2)%58;
	                	}
	                		
	                	dosDigitos = (char)((int) (s.charAt(0) + 2)) + "" + segDig + ""; //50 en ascii
	                	i++;
	                }
	                else if(i == 0 && (s.charAt(0) != '1' && s.charAt(1) != '2')) 
	                {
	                	segDig = (char)((int) (s.charAt(1) + 2)); // 8 + 2 = ':' en ascii
	                	
	                	if(s.charAt(1) + 2 > '9')
	                	{
	                		segDig = ((int)s.charAt(1) + 2)%58;
	                	}
	                	
	                	dosDigitos = (char)((int) (s.charAt(0) + 1)) + "" + (char)segDig + ""; //50 en ascii
	                	i++;
	                }
	                
	                else if(i == 0 && (s.charAt(0) == '1' && s.charAt(1) == '2'))
	                {
	                	dosDigitos = s;
	                	break;
	                }
	               
	            }
	        }
	        else if((s.charAt(0) == '1' && s.charAt(1) == '2') && (s.charAt(s.length()-2)== 'A' || s.charAt(s.length()-2)== 'a')) 
	        {
	        	dosDigitos = "00";
	        	
	        	for (int i = 2; i < s.length(); i++) 
	        	{
	        		dosDigitos += s.charAt(i);
	        	}
	        }
	        else dosDigitos = s;
	
	        s = dosDigitos;
	        dosDigitos = "";
	        
	        for (int i = 0; i < s.length()-2; i++) 
	        {
	        	dosDigitos += s.charAt(i);
			}
	        System.out.println(dosDigitos);
	}
	
}
