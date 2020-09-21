package Proyecto_Unidad1_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class ArchivoArtis {

	static public void Archivo(String text) throws IOException 
	{
				
		File filextense = new File("archivo.Dat");
		RandomAccessFile Archivo = new RandomAccessFile(filextense,"rw");
		if(!filextense.exists())
		{
			filextense.createNewFile();
//			System.out.println("Archivo creado");
		}
//		int tam = ctext1.length;
		try/*(PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter(f, true))))*/
		{
			FileWriter w = new FileWriter(filextense);		
			BufferedWriter bw = new BufferedWriter(w);		
			PrintWriter wr = new PrintWriter(bw);  	
			
			wr.write(text);
			
			wr.close();			
		}catch(IOException e){ e.getMessage();};
	}
	static public String MuestraArchivo() throws IOException 
	{	
		String cadena, cadena2 = "";
		File filextense = new File("archivo.Dat");
//		RandomAccessFile Archivo = new RandomAccessFile(filextense,"rw");
		
		if(!filextense.exists())
			filextense.createNewFile();
		try{
			
			FileReader fr = new FileReader(filextense);
			BufferedReader b = new BufferedReader(fr);
			while((cadena = b.readLine())!=null) {
				cadena2 += cadena;
			}
			b.close();  
		}catch(Throwable e){e.printStackTrace();}
		return cadena2;
	}
}
