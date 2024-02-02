import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPpresentacionServer {
    private final static int MAX_BYTES = 1400;
    private final static String COD_TEXTO = "UTF-8";
    // private static int puerto = 9999;

    public static void main(String[] args) {
        int puerto = Integer.parseInt(args[0]);

        try (var serverSocket = new DatagramSocket(puerto);
                InputStreamReader isrStdIn = new InputStreamReader(System.in, COD_TEXTO);
                BufferedReader brStdIn = new BufferedReader(isrStdIn)) { // crea el socket con el número de puerto
            System.out.printf("Creado socket de datagrama para puerto %s.\n", puerto);

            System.out.println("Esperando datagramas ...");
            // creamos array de bytes para almacenar mensaje recibido
            byte[] datosRecibidos = new byte[MAX_BYTES];
            // creamos datagrama usando el buffer
            DatagramPacket paqueteRecibido = new DatagramPacket(datosRecibidos, datosRecibidos.length);

            // recepcion del datagrama
            serverSocket.receive(paqueteRecibido);

            // codificamos el texto en UTF-8
            String lineaRecibida = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength(), COD_TEXTO);

            // obtenemos la IP y puerto del cliente que está contenida en el datagrama
            InetAddress IPCliente = paqueteRecibido.getAddress();
            int puertoCliente = paqueteRecibido.getPort();

            String lineaLeida;
            System.out.println("Introducir lineas. Linea vacia para terminar.");
            System.out.print("Linea>");
            lineaLeida = brStdIn.readLine();

            byte[] b = lineaLeida.getBytes(COD_TEXTO);
            DatagramPacket paqueteEnviado = new DatagramPacket(b, b.length, IPCliente, puertoCliente);
            serverSocket.send(paqueteEnviado);

            System.out.println();
            System.out.printf("Recibido datagrama de %s:%d(%s)\n", IPCliente.getHostAddress(), puertoCliente,
                    lineaRecibida);

        } catch (SocketException ex) {
            System.out.println("Excepción de sockets");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Excepción de E/S");
            ex.printStackTrace();
        }

    }
}
