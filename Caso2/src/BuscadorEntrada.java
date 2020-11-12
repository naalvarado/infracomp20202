import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class BuscadorEntrada extends Thread {
	private char chara;
	private String algoritmo;
	private byte[] hash;
	private long stIE;
	private Respuesta res;
	
	public BuscadorEntrada(String algo, byte[] h, char c, Respuesta r) {
		this.algoritmo = algo;
		this.hash = h;
		this.chara = c;
		res = r;
	}
	
	public void run() {
		encontrar(chara);
	}
	
	public void encontrar(char cini) {
		stIE = System.currentTimeMillis();
		String temp = cini+"";
		for(int a = 97; a < 123; a++) {
			if(!res.getTer()) {
				char c1 = (char)a;
				String sc1 = temp+c1+"";
				for(int b = 97; b < 123; b++) {
					if(!res.getTer()) {
						char c2 = (char)b;
						String sc2 = sc1+c2+"";
						for(int c = 97; c < 123; c++) {
							if(!res.getTer()) {
								char c3 = (char)c;
								String sc3 = sc2+c3+"";
								for(int d = 97; d < 123; d++) {
									if(!res.getTer()) {
										char c4 = (char)d;
										String sc4  = sc3+c4+"";
										for(int e = 97; e < 123; e++) {
											if(!res.getTer()) {
												char c5 = (char)e;
												String sc5 = sc4+c5+"";
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
			System.out.println(prime);
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

		return (hours + "h, " + minutes + "min, " + seconds + "s y " + millis + "ms");
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
