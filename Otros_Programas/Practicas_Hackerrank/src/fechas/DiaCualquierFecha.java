package fechas;

import java.time.LocalDate;

public class DiaCualquierFecha 
{
    public static void main(String[] args) 
    {
        int month = Integer.parseInt("08");
        
        int day = Integer.parseInt("26");
        
        int year = Integer.parseInt("2020");
        
        System.out.println(LocalDate.of(year, month, day).getDayOfWeek().name());
    }
}