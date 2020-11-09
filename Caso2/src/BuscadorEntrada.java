import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class BuscadorEntrada extends Thread {
	private String re;
	private char chara;
	private String algoritmo;
	private byte[] hash;
	private long stIE;
	private Respuesta res;
	
	public BuscadorEntrada(String algo, byte[] h, String Pre, char c, Respuesta r) {
		this.re = Pre;
		this.algoritmo = algo;
		this.hash = h;
		this.chara = c;
		res = r;
	}
	
	public void run() {
		stIE = System.currentTimeMillis();
		String temp = chara+"";
		byte[] cod = generar_codigo(temp, algoritmo);
		check(cod,temp);
		for(int a = 97; a < 123; a++) {
			if(!res.getTer()) {
				char c1 = (char)a;
				String sc1 = temp+c1+"";
				byte[] codigo1 = generar_codigo(sc1, algoritmo);
				check(codigo1,sc1);
				for(int b = 97; b < 123; b++) {
					if(!res.getTer()) {
						char c2 = (char)b;
						String sc2 = sc1+c2+"";
						byte[] codigo2 = generar_codigo(sc2, algoritmo);
						check(codigo2, sc2);	
						for(int c = 97; c < 123; c++) {
							if(!res.getTer()) {
								char c3 = (char)c;
								String sc3 = sc2+c3+"";
								byte[] codigo3 = generar_codigo(sc3, algoritmo);
								check(codigo3,sc3);	
								for(int d = 97; d < 123; d++) {
									if(!res.getTer()) {
										char c4 = (char)d;
										String sc4  = sc3+c4+"";
										byte[] codigo4 = generar_codigo(sc4, algoritmo);
										check(codigo4,sc4);
										for(int e = 97; e < 123; e++) {
											if(!res.getTer()) {
												char c5 = (char)e;
												String sc5 = sc4+c5+"";
												byte[] codigo5 = generar_codigo(sc5, algoritmo);
												check(codigo5,sc5);
												for(int f = 97; f < 123; f++) {
													if(!res.getTer()) {
														char c6 = (char)f;
														String sc6 = sc5+c6+"";
														byte[] codigo6 = generar_codigo(sc6, algoritmo);
														check(codigo6,sc6);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void check(byte[] cod, String prime) {
		if(Arrays.equals(hash, cod)) {
			long finIE = System.currentTimeMillis();
			long timeIE = (finIE - stIE);
			re = prime;
			System.out.println(re);
			System.out.println("Se demoro "+convertmillis(timeIE)+" en encontrar la entrada!");
			res.ternimo();
			//System.exit(0);
		}
	}
	
	public static String convertmillis(long input) {
		int hours = 0, minutes = 0, seconds = 0, millis = 0;

		int hour = 3600000;
		int minute = 60000;
		int second = 1000;

		if(input >= hour) {
			hours = (int) (input / hour);
			millis = (int) input % hour;
		}
		else {
			millis = (int) input;
		}

		if(millis >= minute) {
			minutes = millis / minute;
			millis = millis % minute;
		}

		if(millis >= second) {
			seconds = millis / second;
			millis = millis % second;
		}

		return (hours + "h, " + minutes + "min, " + seconds + "s and " + millis + "ms");
	}
	
	public static byte[] generar_codigo(String texto, String algoritmo) {
		byte[] data = texto.getBytes();
		MessageDigest hash;
		byte[] re = null;
		try {
			hash = MessageDigest.getInstance(algoritmo);
			hash.update(data);
			re = hash.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re;
	}
}
