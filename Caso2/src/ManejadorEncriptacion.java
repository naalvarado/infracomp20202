import java.security.MessageDigest;

public class ManejadorEncriptacion {
	
	public static byte[] generar_codigo(String texto, String algoritmo) throws Exception {
		byte[] data = texto.getBytes();
		MessageDigest hash = MessageDigest.getInstance(algoritmo);
		hash.update(data);
		return hash.digest();
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
	}

}
