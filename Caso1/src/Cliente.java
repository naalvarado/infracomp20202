
public class Cliente extends Thread {
	
	private Mensaje[] mensajes;
	private int id;
	private Buffer buff;
	
	public Cliente(int pId, Buffer pBuff, int numMens) {
		mensajes = new Mensaje[numMens];
		this.id = pId;
		this.buff = pBuff;
		for(int i = 0; i < mensajes.length; i++) {
			int r = (int)(Math.random() * (100 - 1 +1) + 1);
			mensajes[i] = new Mensaje(r);
		}
	}
	
	public void run() {
		for(int i = 0; i < mensajes.length; i++) {
			buff.addMensaje(mensajes[i]);
		}
		System.out.println("Sale cliente: " + id);
	}

}
