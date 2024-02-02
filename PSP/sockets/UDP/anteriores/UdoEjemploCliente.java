import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdoEjemploCliente {

    private final static int MAX_BYTES = 1400;
	private final static String COD_TEXTO = "UTF-8";
	private final static String servidorIP = "localhost";
	private static int puerto = 9999;

    public static void main(String[] args) {

        try (DatagramSocket serverSocket = new DatagramSocket();

                // COD_TEXTO se utiliza para especificar la codificación de caracteres que se
                // utilizará al leer los datos

                InputStreamReader isrStdIn = new InputStreamReader(System.in, COD_TEXTO);
                BufferedReader brStdIn = new BufferedReader(isrStdIn)) {

            String lineaLeida;
            System.out.println("Introducir lineas. Linea vacia para terminar.");
            System.out.print("Linea>");

            while ((lineaLeida = brStdIn.readLine()) != null && lineaLeida.length() > 0) {

                // Obtenemos la dirección del servidor
                InetAddress IPServidor = InetAddress.getByName(servidorIP);

                byte[] b = lineaLeida.getBytes(COD_TEXTO);
                DatagramPacket paqueteEnviado = new DatagramPacket(b, b.length, IPServidor, puerto);

                serverSocket.connect(IPServidor, puerto);
                serverSocket.send(paqueteEnviado);

                // creamos arry de bytes para la recepción de la respuesta
                byte[] datosRecibidos = new byte[MAX_BYTES];
                DatagramPacket paqueteRecibido = new DatagramPacket(datosRecibidos, datosRecibidos.length);
                serverSocket.receive(paqueteRecibido);
                String respuesta = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength(), COD_TEXTO);

                System.out.printf("Recibido datagrama de %s:%d %s\n", paqueteRecibido.getAddress().getHostAddress(),
                        paqueteRecibido.getPort(), respuesta);
                System.out.print("Linea>");
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