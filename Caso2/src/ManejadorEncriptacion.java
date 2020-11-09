import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ManejadorEncriptacion {
	
	public static String resultado;
	public static boolean encont;
		
	public static byte[] generar_codigo(String texto, String algoritmo) throws Exception {
		byte[] data = texto.getBytes();
		MessageDigest hash = MessageDigest.getInstance(algoritmo);
		hash.update(data);
		return hash.digest();
	}
	
	public static byte[] stringToByteA(String h) {
		byte[] re = new byte[h.length()/2];
		int j = 0;
		for(int i = 0; i < h.length(); i=i+2) {
			String temp = h.charAt(i) + h.charAt(i+1) + "";
			re[j] = hexToByte(temp);
			j++;
		}
		return re;
	}
	
	public static byte hexToByte(String s) {
		int primero = Character.digit(s.charAt(0), 16);
		int segundo = Character.digit(s.charAt(1), 16);
		return (byte) ((primero << 4) + segundo);
	}
	
	public static String imprimirHash(byte[] hash) {
		String pri = "";
		for(int i = 0; i < hash.length; i++) {
			if((hash[i] & 0xff) <= 0xf) {
				pri += "0";
			}
			pri += Integer.toHexString(hash[i] & 0xff).toLowerCase();
		}
		return pri;
	}	
	
	public static void main(String args[]) {
		System.out.println("Bienbenido!");
		try {
			//Scanner s = new Scanner(System.in);
			//System.out.println("para crear un hash oprima 1");
			//System.out.println("para encontrar el texto original de un hash oprima 2");
			//String op = s.next();
			//if(op.equals("1")) {
				//crear hash
			//}
			//else if(op.equals("2")) {
				//romper hash
			//}
			String t = "aazzzzz";
			String a = "MD5";
			byte[] h = generar_codigo(t,a);
			String hash = imprimirHash(h);
			//byte[] test = stringToByteA(hash);
			//System.out.println(Arrays.equals(h,test));
			System.out.println(hash);
			
			BuscadorEntrada[] be = new BuscadorEntrada[26];
			encont = false;
			for(int i = 97; i < 123; i++) {
				char c = (char)i;
				be[i-97] = new BuscadorEntrada(a,h,resultado,c,i);
				be[i-97].start();
			}
			
			//long stIE = System.currentTimeMillis();
			//System.out.println(identificar_entrada(h,a));
			//long finIE = System.currentTimeMillis();
			//long timeIE = (finIE - stIE)/1000;
			//System.out.println("Se demoro "+timeIE+"s en encontrar la entrada!");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
