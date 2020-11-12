import java.lang.management.ManagementFactory;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class ManejadorEncriptacion {
	
	public static Respuesta re;
	
	// Arreglo para 1 thread
	
	public static char[] arreglo11 = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	// Arreglos para 2 threads
	
	public static char[] arreglo21 = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m'};
	public static char[] arreglo22 = new char[] {'n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	// Arreglos para 4 threads
	
	// Arreglos para 8 threads
		
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
			Scanner s = new Scanner(System.in);
			System.out.println("Ingrese la cadena deseada: ");
			String t = s.next();
			if(t.length() != 7) {
				System.out.println("Las cadenas deben ser de 7 caracteres");
				System.exit(0);
			}
			System.out.println("Ingrese el algoritmo deseado: ");
			String a = s.next();
			
			byte[] h = generar_codigo(t,a);
			String hash = imprimirHash(h);
			System.out.println(hash);
			
			BuscadorEntrada[] be = new BuscadorEntrada[26];
			re = new Respuesta();
			for(int i = 97; i < 123; i++) {
				char c = (char)i;
				be[i-97] = new BuscadorEntrada(a,h,c,re);
				be[i-97].start();
			}
			MonitorCPU mcpu = new MonitorCPU(re);
			mcpu.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
