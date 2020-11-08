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
		long stIE = System.currentTimeMillis();
			String temp = chara+"";
			try {
				byte[] cod = generar_codigo(temp, algoritmo);
				if(Arrays.equals(hash, cod)) {
					long finIE = System.currentTimeMillis();
					long timeIE = (finIE - stIE)/1000;
					re = temp;
					encontrado = true;
					System.out.println(re);
					System.out.println("Se demoro "+timeIE+"s en encontrar la entrada!");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			if(!encontrado) {
				for(int a = 97; a < 123; a++) {
					char c1 = (char)a;
					String sc1 = temp+c1+"";
					byte[] codigo1;
					try {
						codigo1 = generar_codigo(sc1, algoritmo);
						if(Arrays.equals(hash, codigo1)) {
							long finIE = System.currentTimeMillis();
							long timeIE = (finIE - stIE)/1000;
							re = sc1;
							encontrado = true;
							System.out.println(re);
							System.out.println("Se demoro "+timeIE+"s en encontrar la entrada!");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if(!encontrado) {
						for(int b = 97; b < 123; b++) {
							char c2 = (char)b;
							String sc2 = sc1+c2+"";
							byte[] codigo2;
							try {
								codigo2 = generar_codigo(sc2, algoritmo);
								if(Arrays.equals(hash, codigo2)) {
									long finIE = System.currentTimeMillis();
									long timeIE = (finIE - stIE)/1000;
									re = sc2;
									encontrado = true;
									System.out.println(re);
									System.out.println("Se demoro "+timeIE+"s en encontrar la entrada!");
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							if(!encontrado) {
								for(int c = 97; c < 123; c++) {
									char c3 = (char)c;
									String sc3 = sc2+c3+"";
									byte[] codigo3;
									try {
										codigo3 = generar_codigo(sc3, algoritmo);
										if(Arrays.equals(hash, codigo3)) {
											long finIE = System.currentTimeMillis();
											long timeIE = (finIE - stIE)/1000;
											re = sc3;
											encontrado = true;
											System.out.println(re);
											System.out.println("Se demoro "+timeIE+"s en encontrar la entrada!");
										}
									} catch (Exception e1) {
										e1.printStackTrace();
									}
									if(!encontrado) {
										for(int d = 97; d < 123; d++) {
											char c4 = (char)d;
											String sc4  = sc3+c4+"";
											byte[] codigo4;
											try {
												codigo4 = generar_codigo(sc4, algoritmo);
												if(Arrays.equals(hash, codigo4)) {
													long finIE = System.currentTimeMillis();
													long timeIE = (finIE - stIE)/1000;
													re = sc4;
													encontrado = true;
													System.out.println(re);
													System.out.println("Se demoro "+timeIE+"s en encontrar la entrada!");
												}
											} catch (Exception e1) {
												e1.printStackTrace();
											}
											if(!encontrado) {
												for(int e = 97; e < 123; e++) {
													char c5 = (char)e;
													String sc5 = sc4+c5+"";
													byte[] codigo5;
													try {
														codigo5 = generar_codigo(sc5, algoritmo);
														if(Arrays.equals(hash, codigo5)) {
															long finIE = System.currentTimeMillis();
															long timeIE = (finIE - stIE)/1000;
															re = sc5;
															encontrado = true;
															System.out.println(re);
															System.out.println("Se demoro "+timeIE+"s en encontrar la entrada!");
														}
													} catch (Exception e1) {
														e1.printStackTrace();
													}
													if(!encontrado) {
														for(int f = 97; f < 123; f++) {
															char c6 = (char)f;
															String sc6 = sc5+c6+"";
															byte[] codigo6;
															try {
																codigo6 = generar_codigo(sc6, algoritmo);
																if(Arrays.equals(hash, codigo6)) {
																	long finIE = System.currentTimeMillis();
																	long timeIE = (finIE - stIE)/1000;
																	re = sc6;
																	System.out.println(re);
																	System.out.println("Se demoro "+timeIE+"s en encontrar la entrada!");
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
