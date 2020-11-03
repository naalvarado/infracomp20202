import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;

public class ManejadorEncriptacion {
		
	public static byte[] generar_codigo(String texto, String algoritmo) throws Exception {
		byte[] data = texto.getBytes();
		MessageDigest hash = MessageDigest.getInstance(algoritmo);
		hash.update(data);
		return hash.digest();
	}	
	
	public static String identificar_entrada(byte[] hash, String algo) throws Exception {
		for(int a = 97; a < 123; a++) {
			char c1 = (char)a;
			String sc1 = c1+"";
			byte[] codigo1 = generar_codigo(sc1, algo);
			if(Arrays.equals(hash, codigo1)) {
				return sc1;
			}
			for(int b = 97; b < 123; b++) {
				char c2 = (char)b;
				String sc2 = c1+""+c2+"";
				byte[] codigo2 = generar_codigo(sc2, algo);
				if(Arrays.equals(hash, codigo2)) {
					return sc2;
				}
				for(int c = 97; c < 123; c++) {
					char c3 = (char)c;
					String sc3 = sc2+c3+"";
					byte[] codigo3 = generar_codigo(sc3, algo);
					if(Arrays.equals(hash, codigo3)) {
						return sc3;
					}
					for(int d = 97; d < 123; d++) {
						char c4 = (char)d;
						String sc4  = sc3+c4+"";
						byte[] codigo4 = generar_codigo(sc4, algo);
						if(Arrays.equals(hash, codigo4)) {
							return sc4;
						}
						for(int e = 97; e < 123; e++) {
							char c5 = (char)e;
							String sc5 = sc4+c5+"";
							byte[] codigo5 = generar_codigo(sc5, algo);
							if(Arrays.equals(hash, codigo5)) {
								return sc5;
							}
							for(int f = 97; f < 123; f++) {
								char c6 = (char)f;
								String sc6 = sc5+c6+"";
								byte[] codigo6 = generar_codigo(sc6, algo);
								if(Arrays.equals(hash, codigo6)) {
									return sc6;
								}
								for(int g = 97; g < 123; g++) {
									char c7 = (char)g;
									String sc7 = sc6+c7+"";
									byte[] codigo7 = generar_codigo(sc7, algo);
									if(Arrays.equals(hash, codigo7)) {
										return sc7;
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
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
		// NO PONGA VALORES MAYORES A 'a' EN EL PRIMER CARACTER
		// NO PONGA VALORES MOTORES A 'f' EN EL SEGUNDO CARACTER
		String t = "afbcccc";
		String a = "MD5";
		try {
			byte[] h = generar_codigo(t,a);
			String hash = imprimirHash(h);
			System.out.println(hash);
			
			long stIE = System.currentTimeMillis();
			System.out.println(identificar_entrada(h,a));
			long finIE = System.currentTimeMillis();
			long timeIE = (finIE - stIE)/1000;
			System.out.println("Se demoro "+timeIE+"s en encontrar la entrada!");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
