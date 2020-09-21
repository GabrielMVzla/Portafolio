package practicas;
import java.util.Scanner;

/*
 * @author Ariel
 */
public class FuncionSCT {

    /* @param args the command line arguments
     */
    public static void main(String[] args) {
        double a;
        Scanner num = new Scanner(System.in);
        
//        double convertir = (Math.cos(Math.PI*0/180));
//        System.out.println("Convertir = " + convertir);
        
        double b = Math.toRadians(270);
        System.out.println((double)Math.cos(b));
        
    }
    static void anguloCal(double a){
        //Primer punto, pasa "a" a radianes.
        double b = Math.toDegrees(a);
        System.out.println("Seno de " + a + " es: " + Math.sin(b) );
        System.out.println("Coseno de " + a + " es: " + Math.cos(b) );
        System.out.println("Tangente de " + a + " es: " + Math.tan(b) );
    }
}