import java.security.MessageDigest;
import java.util.Arrays;

public class BuscadorEntrada extends Thread {
	private String re;
	private int id;
	private char chara;
	private String algoritmo;
	private byte[] hash;
	private boolean encontrado;
	
	public BuscadorEntrada(String algo, byte[] h, String Pre, char c, int Pid, boolean en) {
		this.re = Pre;
		this.algoritmo = algo;
		this.hash = h;
		this.chara = c;
		this.id = Pid;
		this.encontrado = en;
	}
	
	public void run() {
			String temp = chara+"";
			System.out.println("Comienza thrad: "+id+" con letra: "+temp);
			try {
				byte[] cod = generar_codigo(temp, algoritmo);
				if(Arrays.equals(hash, cod)) {
					re = temp;
					System.out.println(re);
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			for(int a = 97; a < 123; a++) {
				char c1 = (char)a;
				String sc1 = temp+c1+"";
				byte[] codigo1;
				try {
					codigo1 = generar_codigo(sc1, algoritmo);
					if(Arrays.equals(hash, codigo1)) {
						re = sc1;
						System.out.println(re);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				for(int b = 97; b < 123; b++) {
					char c2 = (char)b;
					String sc2 = sc1+c2+"";
					byte[] codigo2;
					try {
						codigo2 = generar_codigo(sc2, algoritmo);
						if(Arrays.equals(hash, codigo2)) {
							re = sc2;
							System.out.println(re);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					for(int c = 97; c < 123; c++) {
						char c3 = (char)c;
						String sc3 = sc2+c3+"";
						byte[] codigo3;
						try {
							codigo3 = generar_codigo(sc3, algoritmo);
							if(Arrays.equals(hash, codigo3)) {
								re = sc3;
								System.out.println(re);
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						for(int d = 97; d < 123; d++) {
							char c4 = (char)d;
							String sc4  = sc3+c4+"";
							byte[] codigo4;
							try {
								codigo4 = generar_codigo(sc4, algoritmo);
								if(Arrays.equals(hash, codigo4)) {
									re = sc4;
									System.out.println(re);
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							
							for(int e = 97; e < 123; e++) {
								char c5 = (char)e;
								String sc5 = sc4+c5+"";
								byte[] codigo5;
								try {
									codigo5 = generar_codigo(sc5, algoritmo);
									if(Arrays.equals(hash, codigo5)) {
										re = sc5;
										System.out.println(re);
									}
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								
								for(int f = 97; f < 123; f++) {
									char c6 = (char)f;
									String sc6 = sc5+c6+"";
									byte[] codigo6;
									try {
										codigo6 = generar_codigo(sc6, algoritmo);
										if(Arrays.equals(hash, codigo6)) {
											re = sc6;
											System.out.println(re);
										}
									} catch (Exception e1) {
										e1.printStackTrace();
									}
										
								}
							}
						}
					}
				}
			}
	}
	
	public static byte[] generar_codigo(String texto, String algoritmo) throws Exception {
		byte[] data = texto.getBytes();
		MessageDigest hash = MessageDigest.getInstance(algoritmo);
		hash.update(data);
		return hash.digest();
	}
}
