package ultimos5Programas;

public class Tablas {

	public static double Kolmogorov10P(int grados) {
		
double resultado = 0;
		
		if(grados == 1)
			resultado = 0.95000;
		if(grados == 2)
			resultado = 0.77639;
		if(grados == 3)
			resultado = 0.63604;
		if(grados == 4)
			resultado = 0.56522;
		if(grados == 5)
			resultado = 0.50945;
		if(grados == 6)
			resultado = 0.46799;
		if(grados == 7)
			resultado = 0.43607;
		if(grados == 8)
			resultado = 0.40962;
		if(grados == 9)
			resultado = 0.38746;
		if(grados == 10)
			resultado = 0.36866;
		if(grados == 11)
			resultado = 0.35242;
		if(grados == 12)
			resultado = 0.33815;
		if(grados == 13)
			resultado = 0.32549;
		if(grados == 14)
			resultado = 0.31417;
		if(grados == 15)
			resultado = 0.30397;
		if(grados == 16)
			resultado = 0.29472;
		if(grados == 17)
			resultado = 0.28627;
		if(grados == 18)
			resultado = 0.27851;
		if(grados == 19)
			resultado = 0.27136;
		if(grados == 20)
			resultado = 0.26473;
		if(grados == 21)
			resultado = 0.25858;
		if(grados == 22)
			resultado = 0.25283;
		if(grados == 23)
			resultado = 0.24746;
		if(grados == 24)
			resultado = 0.24242;
		if(grados == 25)
			resultado = 0.23768;
		if(grados == 26)
			resultado = 0.23320;
		if(grados == 27)
			resultado = 0.22898;
		if(grados == 28)
			resultado = 0.22497;
		if(grados == 29)
			resultado = 0.22117;
		if(grados == 30)
			resultado = 0.21756;
		if(grados == 31)
			resultado = 0.21412;
		if(grados == 32)
			resultado = 0.21085;
		if(grados == 33)
			resultado = 0.20771;
		if(grados == 34)
			resultado = 0.20472;
		if(grados == 35)
			resultado = 0.20185;
		if(grados == 36)
			resultado = 0.19910;
		if(grados == 37)
			resultado = 0.19646;
		if(grados == 38)
			resultado = 0.19392;
		if(grados == 39)
			resultado = 0.19148;
		if(grados == 40)
			resultado = 0.18913;
		if(grados == 41)
			resultado = 0.18687;
		if(grados == 42)
			resultado = 0.18468;
		if(grados == 43)
			resultado = 0.18257;
		if(grados == 44)
			resultado = 0.18051;
		if(grados == 45)
			resultado = 0.17856;
		if(grados == 46)
			resultado = 0.17665;
		if(grados == 47)
			resultado = 0.17481;
		if(grados == 48)
			resultado = 0.17301;
		if(grados == 49)
			resultado = 0.17128;
		if(grados == 50)
			resultado = 0.16959;
		if(grados > 50)
			resultado = (1.22/(Math.pow(grados, 2)));
		
			return resultado;
	}
	public  static double Kolmogorov5P(int grados) {
		
		double resultado = 0;
		
		if(grados == 1)
			resultado = 0.97500;
		if(grados == 2)
			resultado = 0.84189;
		if(grados == 3)
			resultado = 0.70760;
		if(grados == 4)
			resultado = 0.62394;
		if(grados == 5)
			resultado = 0.56328;
		if(grados == 6)
			resultado = 0.51926;
		if(grados == 7)
			resultado = 0.48342;
		if(grados == 8)
			resultado = 0.45427;
		if(grados == 9)
			resultado = 0.43001;
		if(grados == 10)
			resultado = 0.40925;
		if(grados == 11)
			resultado = 0.39122;
		if(grados == 12)
			resultado = 0.37543;
		if(grados == 13)
			resultado = 0.36143;
		if(grados == 14)
			resultado = 0.34890;
		if(grados == 15)
			resultado = 0.33750;
		if(grados == 16)
			resultado = 0.32733;
		if(grados == 17)
			resultado = 0.31796;
		if(grados == 18)
			resultado = 0.30936;
		if(grados == 19)
			resultado = 0.30143;
		if(grados == 20)
			resultado = 0.29408;
		if(grados == 21)
			resultado = 0.28724;
		if(grados == 22)
			resultado = 0.28087;
		if(grados == 23)
			resultado = 0.27490;
		if(grados == 24)
			resultado = 0.26931;
		if(grados == 25)
			resultado = 0.26404;
		if(grados == 26)
			resultado = 0.25908;
		if(grados == 27)
			resultado = 0.25438;
		if(grados == 28)
			resultado = 0.24993;
		if(grados == 29)
			resultado = 0.24571;
		if(grados == 30)
			resultado = 0.24170;
		if(grados == 31)
			resultado = 0.23788;
		if(grados == 32)
			resultado = 0.23424;
		if(grados == 33)
			resultado = 0.23076;
		if(grados == 34)
			resultado = 0.22743;
		if(grados == 35)
			resultado = 0.22425;
		if(grados == 36)
			resultado = 0.22119;
		if(grados == 37)
			resultado = 0.21826;
		if(grados == 38)
			resultado = 0.21544;
		if(grados == 39)
			resultado = 0.21273;
		if(grados == 40)
			resultado = 0.21012;
		if(grados == 41)
			resultado = 0.20760;
		if(grados == 42)
			resultado = 0.20517;
		if(grados == 43)
			resultado = 0.20283;
		if(grados == 44)
			resultado = 0.20056;
		if(grados == 45)
			resultado = 0.19837;
		if(grados == 46)
			resultado = 0.19625;
		if(grados == 47)
			resultado = 0.19420;
		if(grados == 48)
			resultado = 0.19221;
		if(grados == 49)
			resultado = 0.19028;
		if(grados == 50)
			resultado = 0.18841;
		if(grados > 50)
			resultado = (1.36/(Math.pow(grados, 2)));
		
			return resultado;
	}
	public static  double Chi2do5(int grados) {
	double resultado = 0;
		
		if(grados == 1)
			resultado = 3.8415;
		if(grados == 2)
			resultado = 5.9915;
		if(grados == 3)
			resultado = 7.8147;
		if(grados == 4)
			resultado = 9.4877;
		if(grados == 5)
			resultado = 11.0705;
		if(grados == 6)
			resultado = 12.5916;
		if(grados == 7)
			resultado = 14.0671;
		if(grados == 8)
			resultado = 15.5073;
		if(grados == 9)
			resultado = 16.9190;
		if(grados == 10)
			resultado = 18.3070;
		if(grados == 11)
			resultado = 19.6752;
		if(grados == 12)
			resultado = 21.0261;
		if(grados == 13)
			resultado = 22.3620;
		if(grados == 14)
			resultado = 23.6848;
		if(grados == 15)
			resultado = 24.9958;
		if(grados == 16)
			resultado = 26.2962;
		if(grados == 17)
			resultado = 27.5871;
		if(grados == 18)
			resultado = 28.8693;
		if(grados == 19)
			resultado = 30.1435;
		if(grados == 20)
			resultado = 31.4104;
		if(grados == 21)
			resultado = 32.6706;
		if(grados == 22)
			resultado = 33.9245;
		if(grados == 23)
			resultado = 35.1725;
		if(grados == 24)
			resultado = 36.4150;
		if(grados == 25)
			resultado = 37.6525;
		if(grados == 26)
			resultado = 38.8851;
		if(grados == 27)
			resultado = 40.1133;
		if(grados == 28)
			resultado = 41.3372;
		if(grados == 29)
			resultado = 42.5569;
		if(grados == 30)
			resultado = 43.7730;
		if(grados == 31)
			resultado = 44.9853;
		if(grados == 32)
			resultado = 46.1942;
		if(grados == 33)
			resultado = 47.3999;
		if(grados == 34)
			resultado = 48.6024;
		if(grados == 35)
			resultado = 49.8018;
		if(grados == 36)
			resultado = 50.9985;
		if(grados == 37)
			resultado = 52.1923;
		if(grados == 38)
			resultado = 53.3835;
		if(grados == 39)
			resultado = 54.5722;
		if(grados >= 40 && grados < 45)
			resultado = 55.7585;
		if(grados >= 45 && grados < 50)
			resultado = 61.6562;
		if(grados >= 50 && grados < 55)
			resultado = 67.5048;
		if(grados >= 55 && grados < 60)
			resultado = 73.3115;
		if(grados >= 60 && grados < 70)
			resultado = 79.0820;
		if(grados >= 70 && grados < 80)
			resultado = 90.5313;
		if(grados >= 80 && grados < 90)
			resultado = 101.8795;
		if(grados >= 90 && grados < 100)
			resultado = 113.1452;
		if(grados >= 100 && grados < 120)
			resultado = 124.3421;
		if(grados >= 120 && grados < 140)
			resultado = 146.5673;
		if(grados >= 140 && grados < 160)
			resultado = 168.6130;
		if(grados >= 160 && grados < 180)
			resultado = 190.5164;
		if(grados >= 180 && grados < 200)
			resultado = 212.3039;
		if(grados >= 200 && grados < 250)
			resultado = 233.9942;
		if(grados >= 250 && grados < 300)
			resultado = 287.8815;
		if(grados >= 300 && grados < 500)
			resultado = 341.3951;
		if(grados >= 500 && grados < 600)
			resultado = 553.1269;
		if(grados > 600)
			resultado = 658.0936;
		
			return resultado;
		
	}
	public  static double Chi2do10(int grados) {
	double resultado = 0;
		
	
	if(grados == 1)
		resultado = 2.7055;
	if(grados == 2)
		resultado = 4.6052;
	if(grados == 3)
		resultado = 6.2514;
	if(grados == 4)
		resultado = 7.7794;
	if(grados == 5)
		resultado = 9.2363;
	if(grados == 6)
		resultado = 10.6446;
	if(grados == 7)
		resultado = 12.0170;
	if(grados == 8)
		resultado = 13.3616;
	if(grados == 9)
		resultado = 14.6837;
	if(grados == 10)
		resultado = 15.9872;
	if(grados == 11)
		resultado = 17.2750;
	if(grados == 12)
		resultado = 18.5493;
	if(grados == 13)
		resultado = 19.8119;
	if(grados == 14)
		resultado = 21.0641;
	if(grados == 15)
		resultado = 22.3071;
	if(grados == 16)
		resultado = 23.5418;
	if(grados == 17)
		resultado = 24.7690;
	if(grados == 18)
		resultado = 25.9894;
	if(grados == 19)
		resultado = 27.2036;
	if(grados == 20)
		resultado = 28.4120;
	if(grados == 21)
		resultado = 29.6151;
	if(grados == 22)
		resultado = 30.8133;
	if(grados == 23)
		resultado = 32.0069;
	if(grados == 24)
		resultado = 33.1962;
	if(grados == 25)
		resultado = 34.3816;
	if(grados == 26)
		resultado = 35.5632;
	if(grados == 27)
		resultado = 36.7412;
	if(grados == 28)
		resultado = 37.9159;
	if(grados == 29)
		resultado = 39.0875;
	if(grados == 30)
		resultado = 40.2560;
	if(grados == 31)
		resultado = 41.4217;
	if(grados == 32)
		resultado = 42.5847;
	if(grados == 33)
		resultado = 43.7452;
	if(grados == 34)
		resultado = 44.9032;
	if(grados == 35)
		resultado = 46.0588;
	if(grados == 36)
		resultado = 47.2122;
	if(grados == 37)
		resultado = 48.3634;
	if(grados == 38)
		resultado = 49.5126;
	if(grados == 39)
		resultado = 50.6598;
	if(grados >= 40 && grados < 45)
		resultado = 51.8050;
	if(grados >= 45 && grados < 50)
		resultado = 57.5053;
	if(grados >= 50 && grados < 55)
		resultado = 63.1671;
	if(grados >= 55 && grados < 60)
		resultado = 68.7962;
	if(grados >= 60 && grados < 70)
		resultado = 74.3970;
	if(grados >= 70 && grados < 80)
		resultado = 85.5270;
	if(grados >= 80 && grados < 90)
		resultado = 96.5782;
	if(grados >= 90 && grados < 100)
		resultado = 107.5650;
	if(grados >= 100 && grados < 120)
		resultado = 118.4980;
	if(grados >= 120 && grados < 140)
		resultado = 140.2326;
	if(grados >= 140 && grados < 160)
		resultado = 161.8270;
	if(grados >= 160 && grados < 180)
		resultado = 183.3106;
	if(grados >= 180 && grados < 200)
		resultado = 204.7036;
	if(grados >= 200 && grados < 250)
		resultado = 226.0210;
	if(grados >= 250 && grados < 300)
		resultado = 279.0504;
	if(grados >= 300 && grados < 500)
		resultado = 331.7885;
	if(grados >= 500 && grados < 600)
		resultado = 540.9303;
	if(grados > 600)
		resultado = 644.8004;
	
			return resultado;
		
	}
}
