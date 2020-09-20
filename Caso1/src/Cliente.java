
public class Cliente extends Thread {
	
	private Mensaje[] mensaje;
	private int id;
	private Buffer buff;
	
	/**
	 * Constructor de Cliente
	 * @param pId
	 * @param pBuff
	 */
	public Cliente(int pId, Buffer pBuff, int pNMens) {
		this.id = pId;
		this.buff = pBuff;
		this.mensaje = new Mensaje[pNMens];
		for(int i = 0; i < mensaje.length; i++) {
			int r = (int)(Math.random() * (100 - 1 +1) + 1);
			Mensaje m = new Mensaje(r);
			mensaje[i] = m;
		}
	}
	
	public void run() {
		while(true) {
			if(this.buff.getNoMensajes() == this.buff.getMax()) {
				yield();
			}
			else {
				for(int i = 0; i < mensaje.length; i++) {
					System.out.println("Cliente: " + id + " mandando mensaje: " + mensaje[i].getContenido());
					buff.addMensaje(mensaje[i]);
					int v = this.mensaje[i].getContenido();
					try {
						while(true) {
							if(mensaje[i].getContenido() == v) {
								wait();
							}
							else {
								break;
							}
						}
					}
					catch(Exception e) {
						//
					}
					System.out.println("Cliente: " + id + " lee respuesta y sale ");
				}
				break;
			}
		}
	}

}
