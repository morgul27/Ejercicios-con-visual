import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPHoraServidor {
	private final static int MAX_BYTES = 1400;
	private final static String COD_TEXTO = "UTF-8";
	private static int puerto = 9999;

	public static void main(String[] args) {

		try (var serverSocket = new DatagramSocket(puerto)) {
			System.out.printf("Creado socket de datagrama para puerto %s.\n", puerto);

			while (true) {

				System.out.println("Esperando datagramas ...");
				// creamos array de bytes para almacenar mensaje recibido
				byte[] datosRecibidos = new byte[MAX_BYTES];
				// creamos datagrama usando el buffer
				DatagramPacket paqueteRecibido = new DatagramPacket(datosRecibidos, datosRecibidos.length);
				// recepcion del datagrama
				serverSocket.receive(paqueteRecibido);

				// codificamos el texto en UTF-8
				String lineaRecibida = (new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength(),
						COD_TEXTO));

				// obtenemos la IP y puerto del cliente que está contenida en el datagrama
				InetAddress IPCliente = paqueteRecibido.getAddress();
				int puertoCliente = paqueteRecibido.getPort();

				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				String respuesta = dateFormat.format(date);

				System.out.printf("\n", IPCliente.getHostAddress(),
						puertoCliente,
						lineaRecibida);

			
				byte[] responseData = respuesta.getBytes(COD_TEXTO);
				DatagramPacket paqueteEnviado = new DatagramPacket(responseData, responseData.length, IPCliente, puertoCliente);
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
