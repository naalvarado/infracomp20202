
public class Servidor extends Thread {
	
	private Buffer buff;
	
	public Servidor(Buffer b) {
		this.buff = b;
	}
	
	public void run() {
		while(true) {
			Mensaje ne = buff.removeMensaje();
			System.out.println("Respuesta: " + ne.getContenido());
		}
	}
	
}
