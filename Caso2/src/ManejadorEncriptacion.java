import java.security.MessageDigest;
import java.util.ArrayList;

public class ManejadorEncriptacion {
	
	public static ArrayList<String> arregloLetras1 = new ArrayList();
	
	public static byte[] generar_codigo(String texto, String algoritmo) throws Exception {
		byte[] data = texto.getBytes();
		MessageDigest hash = MessageDigest.getInstance(algoritmo);
		hash.update(data);
		return hash.digest();
	}
	
	public static String identificar_entrada(byte[] hash, String algoritmo) throws Exception {
		String re = "";
		for(String s : arregloLetras1) {
			byte[] temp = generar_codigo(s,algoritmo);
			if(temp.equals(hash)) {
				re = s;
			}
		}
		return re;
	}
	
	public static void llenarArreglos() {
		for(int i = 97; i < 122; i++) {
			char c = (char)i;
			String sc = c+"";
			arregloLetras1.add(sc);
		}
	}
	
	public static void imprimirHash(byte[] hash) {
		String pri = "";
		for(int i = 0; i < hash.length; i++) {
			if((hash[i] & 0xff) <= 0xf) {
				pri += "0";
			}
			pri += Integer.toHexString(hash[i] & 0xff).toLowerCase();
		}
		System.out.println(pri);
	}
	
	public static void main(String args[]) {
		llenarArreglos();
		String t = "c";
		String a = "MD5";
		try {
			byte[] h = generar_codigo(t,a);
			imprimirHash(h);
			System.out.println(identificar_entrada(h,a));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
