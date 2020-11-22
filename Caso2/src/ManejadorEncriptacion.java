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
	
	public static char[] arreglo41 = new char[] {'a','b','c','d','e','f'};
	public static char[] arreglo42 = new char[] {'g','h','i','j','k','l','m'};
	public static char[] arreglo43 = new char[] {'n','o','p','q','r','s'};
	public static char[] arreglo44 = new char[] {'t','u','v','w','x','y','z'};
	
	// Arreglos para 8 threads
	
	public static char[] arreglo81 = new char[] {'a','b','c'};
	public static char[] arreglo82 = new char[] {'d','e','f'};
	public static char[] arreglo83 = new char[] {'g','h','i'};
	public static char[] arreglo84 = new char[] {'j','k','l','m'};
	public static char[] arreglo85 = new char[] {'n','o','p'};
	public static char[] arreglo86 = new char[] {'q','r','s'};
	public static char[] arreglo87 = new char[] {'t','u','v'};
	public static char[] arreglo88 = new char[] {'w','x','y','z'};
		
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
			
			System.out.println("Cuantos threads quiere usar? (1,2,4,8)");
			int thre = s.nextInt();
			if(thre != 1 && thre != 2 && thre != 4 && thre != 8) {
				System.out.println("Solo se puede 1,2,4 o 8 threads");
				System.exit(0);
			}
			
			s.close();
			
			byte[] h = generar_codigo(t,a);
			String hash = imprimirHash(h);
			System.out.println(hash);
			
			re = new Respuesta();
			if(thre == 8) {
				BuscadorEntrada b1 = new BuscadorEntrada(a,h,arreglo81,re);
				BuscadorEntrada b2 = new BuscadorEntrada(a,h,arreglo82,re);
				BuscadorEntrada b3 = new BuscadorEntrada(a,h,arreglo83,re);
				BuscadorEntrada b4 = new BuscadorEntrada(a,h,arreglo84,re);
				BuscadorEntrada b5 = new BuscadorEntrada(a,h,arreglo85,re);
				BuscadorEntrada b6 = new BuscadorEntrada(a,h,arreglo86,re);
				BuscadorEntrada b7 = new BuscadorEntrada(a,h,arreglo87,re);
				BuscadorEntrada b8 = new BuscadorEntrada(a,h,arreglo88,re);
				
				MonitorCPU mcpu = new MonitorCPU(re);
				
				b1.start();
				b2.start();
				b3.start();
				b4.start();
				b5.start();
				b6.start();
				b7.start();
				b8.start();
				
				mcpu.start();
			}
			else if(thre == 4) {
				BuscadorEntrada b1 = new BuscadorEntrada(a,h,arreglo41,re);
				BuscadorEntrada b2 = new BuscadorEntrada(a,h,arreglo42,re);
				BuscadorEntrada b3 = new BuscadorEntrada(a,h,arreglo43,re);
				BuscadorEntrada b4 = new BuscadorEntrada(a,h,arreglo44,re);
				
				MonitorCPU mcpu = new MonitorCPU(re);
				
				b1.start();
				b2.start();
				b3.start();
				b4.start();
				
				mcpu.start();
			}
			else if(thre == 2) {
				BuscadorEntrada b1 = new BuscadorEntrada(a,h,arreglo21,re);
				BuscadorEntrada b2 = new BuscadorEntrada(a,h,arreglo22,re);
				
				MonitorCPU mcpu = new MonitorCPU(re);
				
				b1.start();
				b2.start();
				
				mcpu.start();
			}
			else if(thre == 1) {
				BuscadorEntrada b = new BuscadorEntrada(a,h,arreglo11,re);
				
				MonitorCPU mcpu = new MonitorCPU(re);
				
				b.start();
				mcpu.start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Runtime.getRuntime().totalMemory() + "HOla");
	}

}
