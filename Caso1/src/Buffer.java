import java.util.ArrayList;

public class Buffer {
	
	private ArrayList<Mensaje> mensages;
	private int max;
	private int noMensajes;
	
	public Buffer(int maximo) {
		this.max = maximo;
		this.noMensajes = 0;
		this.mensages = new ArrayList<Mensaje>();
	}

	public void addMensaje(Mensaje m) {
		while(noMensajes == max) {
			try {
				wait();
			}
			catch(Exception e) {
				// do non
			}
		}
		synchronized(this) {
			this.mensages.add(m);
			noMensajes++;
			notifyAll();
		}
	}
	
	public void removeMensaje(Mensaje m) {
		while(noMensajes == 0) {
			try {
				wait();
			}
			catch(Exception e) {
				//do non
			}
		}
		synchronized(this) {
			this.mensages.remove(m);
			noMensajes--;
			notifyAll();
		}
	}

	public int getMax() {
		return max;
	}

	public int getNoMensajes() {
		return noMensajes;
	}

}
