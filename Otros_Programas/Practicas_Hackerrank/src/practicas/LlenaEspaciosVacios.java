package practicas;

import java.util.Scanner;

public class LlenaEspaciosVacios 
{
	//Despliega cadena con 15 espacios contando desde el inicio de la cadena
	   public static void main(String[] args) {
           Scanner sc=new Scanner(System.in);
           System.out.println("================================");
           for(int i=0;i<3;i++)
           {
               String s1=sc.next();
               int x=sc.nextInt();
               System.out.println(blancos(s1, 15) + ceros(String.valueOf(x), 3));
           }
           System.out.println("================================");

   }
   public static String blancos(String s1, int cant)
   {
       String blancos = " ";
       for(int i = s1.length(); i < cant; i++)
       {
           s1 += blancos;
       }
       return s1;
   }
   public static String ceros(String s1, int cant)
   {
       String ceros = "";
       boolean bandera = false;
       for(int i = s1.length(); i < cant; i++)
       {
           ceros += "0"; //añado cada 0
           bandera = true;
       }
       if(bandera)
           return ceros + s1;//finalmente uno los ceros al inicio
       return s1;
   }
}
