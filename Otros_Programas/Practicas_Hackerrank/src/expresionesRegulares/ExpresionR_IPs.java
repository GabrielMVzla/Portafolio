package expresionesRegulares;

import java.util.Scanner;

public class ExpresionR_IPs {
	  public static void main(String[] args){
	        Scanner in = new Scanner(System.in);
	        
	            String IP = "192.168.000.204";
	            System.out.println(IP.matches(new MyRegex().pattern));
	    }
	}

	class MyRegex{
	    public String pattern;
	    public MyRegex()
	    {
	        String base = "((\\d{1,2})|([0|1][0-9][0-9])|(2[0-4][0-9])|(25[0-5]))";
	        pattern = base + "." + base + "." + base + "." + base;
	        System.out.println(pattern);

	    }
	}