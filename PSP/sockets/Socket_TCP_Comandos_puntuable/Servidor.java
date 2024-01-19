package Socket_TCP_Comandos_puntuable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	private ServerSocket serverSocket;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

	public Servidor (int puerto) throws IOException {
	    serverSocket = new ServerSocket (puerto);
	}

	public void start() throws IOException {
		System.out.println("(Servidor) Esperando conexiones...");
		socket = serverSocket.accept();
		is = socket.getInputStream();
		os = socket.getOutputStream();
		System.out.println("(Servidor) Conexión establecida.");
	}

	public void stop() throws IOException {
		System.out.println("(Servidor) Cerrando conexiones...");
		is.close();
		os.close();
		socket.close();
		serverSocket.close();
		System.out.println("(Servidor) Conexiones cerradas.");
	}

	public static void main(String[] args) {

		try {
			Servidor servidor = new Servidor(49171);
			servidor.start();
			System.out.println("Mensaje del cliente:");
			System.out.println("El cliente a enviado el mensaje... " + servidor.dis.read());
			servidor.os.write(200);
			servidor.stop();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
