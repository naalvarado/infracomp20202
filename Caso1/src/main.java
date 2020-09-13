
public class main {
	
	public static Buffer bu;

	public static void main(String[] args) {
		Cliente[] cl = new Cliente[100];
		Servidor[] se = new Servidor[3];
		bu = new Buffer(10);
		for(int i = 0; i < cl.length; i++) {
			cl[i] = new Cliente(i,bu,2);
			cl[i].start();
		}
		for(int i = 0; i < se.length; i++) {
			se[i] = new Servidor(bu);
			se[i].start();
		}
	}

}
