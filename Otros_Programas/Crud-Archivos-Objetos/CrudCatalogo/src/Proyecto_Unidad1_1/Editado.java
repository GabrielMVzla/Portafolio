package Proyecto_Unidad1_1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class Editado {

//	public static void main(String[] args) throws IOException {
//		
//		String archivo = ArchivoArtis.MuestraArchivo();
//		System.out.println("archivo\n"+archivo);
//		editado(0, "", "", "", "", false);
//	}
	public static void editado(int indice, String cve, String desc, String uni, String exis, String alta) throws IOException
	{

//		String alta = "false";
//		if(t)
//			alta = "true ";

		for (int i = 0; i <30; i++) 
		{
			if(cve.length()<6)
				cve += " ";
			if(desc.length()<30)
				desc += " ";
			
			if(uni.length()<15)
				uni += " ";
			
			if(exis.length()<6)
				exis += " ";
//			if(alta.length()<5)
//				alta += " ";
		}
//		System.out.println(
//				"indice = " + indice +
//				"\ncve = " + cve +
//				"\ndesc = " + desc +
//				"\nuni = " + uni +
//				"\nexis = " + exis +
//				"\nalta = " + alta
//				);
		String archivo = ArchivoArtis.MuestraArchivo();
		String txtAgregar = cve + desc + uni + exis + alta;
		String nvoText = "";
		
		if(archivo.length() == 62)
		{
			nvoText += txtAgregar;
		}
		else
		{
			for (int i = 0; i < archivo.length(); i++) 
			{
				if(i == indice)
				{
					nvoText += txtAgregar;
					i = i + 61;
				}
				else
					nvoText = nvoText + archivo.charAt(i);

			}
		}
		File filextense = new File("archivo.Dat");
//		RandomAccessFile Archivo = new RandomAccessFile(filextense,"rw");
//		if(!filextense.exists())
//		{
//			filextense.createNewFile();
//		}
//			int tam = ctext1.length;
		try/*(PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter(f, true))))*/
		{
			FileWriter w = new FileWriter(filextense);		
			BufferedWriter bw = new BufferedWriter(w);		
			PrintWriter wr = new PrintWriter(bw);  	
			
			wr.write(nvoText);
			
			wr.close();			
		}catch(IOException e){ e.getMessage();};
	
	}
	
}
