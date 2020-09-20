import java.io.File;
import java.util.Scanner;

public class main {
	
	public static Buffer bu;
	private static int numClientes;
	private static int numServidores;
	private static int numMensajes;
	
	private static void readFile() {
		try {
			File f = new File("./src/config.txt");
			Scanner s = new Scanner(f);
			while(s.hasNextLine()) {
				String linea = s.nextLine();
				String[] re = linea.split(":");
				if(re[0].equals("clientes")) {
					numClientes = Integer.parseInt(re[1]);
				}
				else if(re[0].equals("Servidores")) {
					numServidores = Integer.parseInt(re[1]);
				}
				else if(re[0].equals("mensajes")) {
					numMensajes = Integer.parseInt(re[1]);
				}
			}
			s.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {	
		readFile();
		Cliente[] cl = new Cliente[numClientes];
		Servidor[] se = new Servidor[numServidores];
		bu = new Buffer(10);
		for(int i = 0; i < cl.length; i++) {
			cl[i] = new Cliente(i,bu, numMensajes);
			cl[i].start();
		}
		for(int i = 0; i < se.length; i++) {
			se[i] = new Servidor(bu);
			se[i].start();
		}
	}

}
