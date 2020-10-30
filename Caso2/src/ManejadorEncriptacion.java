import java.security.MessageDigest;
import java.util.ArrayList;

public class ManejadorEncriptacion {
	
	public static ArrayList<String> arregloLetras1 = new ArrayList();
	public static ArrayList<String> arregloLetras2 = new ArrayList();
	public static ArrayList<String> arregloLetras3 = new ArrayList();
	public static ArrayList<String> arregloLetras4 = new ArrayList();
	public static ArrayList<String> arregloLetras5 = new ArrayList();
	
	public static byte[] generar_codigo(String texto, String algoritmo) throws Exception {
		byte[] data = texto.getBytes();
		MessageDigest hash = MessageDigest.getInstance(algoritmo);
		hash.update(data);
		return hash.digest();
	}
	
	public static String identificar_entrada(String hash, String algoritmo) throws Exception {
		String re = "";
		for(String s : arregloLetras1) {
			byte[] temp = generar_codigo(s,algoritmo);
			String stemp = imprimirHash(temp);
			if(stemp.equals(hash)) {
				re = s;
			}
		}
		if(re.equals("")) {
			for(String s : arregloLetras2) {
				byte[] temp = generar_codigo(s,algoritmo);
				String stemp = imprimirHash(temp);
				if(stemp.equals(hash)) {
					re = s;
				}
			}
			if(re.equals("")) {
				for(String s : arregloLetras3) {
					byte[] temp = generar_codigo(s,algoritmo);
					String stemp = imprimirHash(temp);
					if(stemp.equals(hash)) {
						re = s;
					}
				}
				if(re.equals("")) {
					for(String s : arregloLetras4) {
						byte[] temp = generar_codigo(s,algoritmo);
						String stemp = imprimirHash(temp);
						if(stemp.equals(hash)) {
							re = s;
						}
					}
					if(re.equals("")) {
						for(String s : arregloLetras5) {
							byte[] temp = generar_codigo(s,algoritmo);
							String stemp = imprimirHash(temp);
							if(stemp.equals(hash)) {
								re = s;
							}
						}
					}
				}
			}
		}
		return re;
	}
	
	public static void llenarArreglos() {
		for(int a = 97; a < 123; a++) {
			char c = (char)a;
			String sc = c+"";
			arregloLetras1.add(sc);
			for(int b = 97; b < 123; b++) {
				char c2 = (char)b;
				String sc2 = c+""+c2+"";
				arregloLetras2.add(sc2);
				for(int d = 97; d < 123; d++) {
					char c3 = (char)d;
					String sc3 = sc2+c3+"";
					arregloLetras3.add(sc3);
					for(int e = 97; e < 123; e++) {
						char c4 = (char)e;
						String sc4  = sc3+c4+"";
						arregloLetras4.add(sc4);
						for(int f = 97; f < 123; f++) {
							char c5 = (char)f;
							String sc5 = sc4+c5+"";
							arregloLetras5.add(sc5);
						}
					}
				}
			}
		}
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
		long stLL = System.currentTimeMillis();
		llenarArreglos();
		long finLL = System.currentTimeMillis();
		long timeLL = (finLL - stLL)/1000;
		System.out.println("Se demoro "+timeLL+"s en crear las listas!");
		String t = "neraa";
		String a = "MD5";
		try {
			long stGC = System.currentTimeMillis();
			byte[] h = generar_codigo(t,a);
			long finGC = System.currentTimeMillis();
			long timeGC = finGC - stGC;
			System.out.println("Se demora "+timeGC+"ms en generar un hash.");
			long stIH = System.currentTimeMillis();
			String hash = imprimirHash(h);
			long finIH = System.currentTimeMillis();
			long timeIH = finIH - stIH;
			System.out.println("Se demora "+timeIH+"ms en pasar el hash de byte[] a String.");
			System.out.println(hash);
			long stIE = System.currentTimeMillis();
			System.out.println(identificar_entrada(hash,a));
			long finIE = System.currentTimeMillis();
			long timeIE = (finIE - stIE)/1000;
			System.out.println("Se demoro "+timeIE+"s en encontrar la entrada!");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
