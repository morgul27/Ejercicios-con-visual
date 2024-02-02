import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdoEjemploServidor {
	private final static int MAX_BYTES = 1400;
	private final static String COD_TEXTO = "UTF-8";
	private static int puerto = 9999;

	public static void main(String[] args) {

		try (var serverSocket = new DatagramSocket(puerto)) { // crea el socket con el número de puerto
			System.out.printf("Creado socket de datagrama para puerto %s.\n", puerto);

			while (true) {

				System.out.println("Esperando datagramas ...");
				// creamos array de bytes para almacenar mensaje recibido
				byte[] datosRecibidos = new byte[MAX_BYTES];
				// creamos datagrama usando el buffer
				DatagramPacket paqueteRecibido = new DatagramPacket(datosRecibidos, datosRecibidos.length); // crea un
																											// paquete
																											// para
																											// recibir
																											// los datos
				// recepcion del datagrama
				serverSocket.receive(paqueteRecibido);

				// codificamos el texto en UTF-8
				int lineaRecibida = Integer
						.parseInt(new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength(), COD_TEXTO));

				// obtenemos la IP y puerto del cliente que está contenida en el datagrama
				InetAddress IPCliente = paqueteRecibido.getAddress();
				int puertoCliente = paqueteRecibido.getPort();

				System.out.println();

				System.out.printf("Recibido datagrama de %s:%d(%s)\n", IPCliente.getHostAddress(),
						puertoCliente,
						lineaRecibida * lineaRecibida);
				String respuesta = "#" + lineaRecibida;

				byte[] b = respuesta.getBytes(COD_TEXTO);
				DatagramPacket paqueteEnviado = new DatagramPacket(b, b.length, IPCliente, puertoCliente);
				serverSocket.send(paqueteEnviado);
			}

		} catch (SocketException ex) {
			System.out.println("Excepción de sockets");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("Excepción de E/S");
			ex.printStackTrace();
		}

	}
}
