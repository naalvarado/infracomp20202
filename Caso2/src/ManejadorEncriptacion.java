import java.security.MessageDigest;

public class ManejadorEncriptacion {
	
	public static byte[] generar_codigo(String texto, String algoritmo) throws Exception {
		byte[] data = texto.getBytes();
		MessageDigest hash = MessageDigest.getInstance(algoritmo);
		hash.update(data);
		return hash.digest();
	}
	
	public static String identificar_entrada(byte[] hash, String algoritmo) throws Exception {
		String re = "";
		for(int i = 96; i < 123; i++) {
			if(i == 96) {
				re = "";
			}
			else {
				char aux = (char)i;
				re = aux+""; 
			}
			//Esto no va a funcionar toca hacer arreglos de posibles combinaciones por tamaño y pasar el tamaño por parametro
			for(int j = 96; j < 123; j++) {
				for(int k = 96; k < 123; k++) {
					
				}
			}	
		}
		return null;
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
		String t = "Hola mundo";
		String a = "MD5";
		try {
			byte[] h = generar_codigo(t,a);
			imprimirHash(h);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int la = (int) 'a';
		int lz = (int) 'z';
		System.out.println(""+la+" el a y z es "+lz);
	}

}
