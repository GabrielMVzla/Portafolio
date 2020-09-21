package proyecto_topicos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Archivo1 {
	static public String muestraContenido() throws IOException 
	{	
		String cadena, cadena2 ="";
		File f = new File("C:\\Users\\Gabriel\\Desktop\\datos.txt");
		if(!f.exists())
			f.createNewFile();
		try{
			
			FileReader fr = new FileReader(f);
			BufferedReader b = new BufferedReader(fr);
			while((cadena = b.readLine())!=null) {
				cadena2 += cadena;
//				System.out.println(cadena);
			}
			b.close();  
		}catch(Throwable e){e.printStackTrace();}
		return cadena2;
	}
	static public String muestraContenidoDatos() throws IOException 
	{	
		String cadena, cadena2 ="";
		File f = new File("C:\\Users\\Gabriel\\Desktop\\datosConcretos.txt");
		if(!f.exists())
			f.createNewFile();
		try{
			
			FileReader fr = new FileReader(f);
			BufferedReader b = new BufferedReader(fr);
			while((cadena = b.readLine())!=null) {
				cadena2 += cadena;
//				System.out.println(cadena);
			}
			b.close();  
		}catch(Throwable e){e.printStackTrace();}
		return cadena2;
	}
	static public void Archivo(char[] ctext1) throws IOException 
	{

		File f = new File("C:\\Users\\Gabriel\\Desktop\\datos.txt");

		if(!f.exists())
		{
			f.createNewFile();
//			System.out.println("Archivo creado");
		}
//		int tam = ctext1.length;
		try/*(PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter(f, true))))*/{

			FileWriter w = new FileWriter(f);		
			BufferedWriter bw = new BufferedWriter(w);		
			PrintWriter wr = new PrintWriter(bw);  	
			for (int i = 0; i < ctext1.length; i++) {
				wr.write(ctext1[i]);
			}
			wr.close();			
			
		}catch(IOException e){ e.getMessage();};
	}
	static public void ArchivoDatos(char[] ctext1) throws IOException 
	{
		
		File f = new File("C:\\Users\\Gabriel\\Desktop\\datosConcretos.txt");
		
		if(!f.exists())
		{
			f.createNewFile();
//			System.out.println("Archivo creado");
		}
		try/*(PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter(f, true))))*/{
			
			FileWriter w = new FileWriter(f);		
			BufferedWriter bw = new BufferedWriter(w);		
			PrintWriter wr = new PrintWriter(bw);  	
			for (int i = 0; i < ctext1.length; i++) {
				wr.write(ctext1[i]);
			}
			wr.close();			
			
		}catch(IOException e){ e.getMessage();};
	}
}
