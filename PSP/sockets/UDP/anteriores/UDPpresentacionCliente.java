import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPpresentacionCliente {

    private final static int MAX_BYTES = 1400;
    private final static String COD_TEXTO = "UTF-8";
    // private final static String servidorIP = "localhost";
    // private static int puerto = 9999;

    public static void main(String[] args) {
        int puerto = Integer.parseInt(args[0]);
        String servidorIP = args[1];

        try (DatagramSocket serverSocket = new DatagramSocket();

                // COD_TEXTO se utiliza para especificar la codificación de caracteres que se
                // utilizará al leer los datos

                InputStreamReader isrStdIn = new InputStreamReader(System.in, COD_TEXTO);
                BufferedReader brStdIn = new BufferedReader(isrStdIn)) {

            String lineaLeida;
            System.out.println("Introducir lineas. Linea vacia para terminar.");
            System.out.print("Linea>");
            lineaLeida = brStdIn.readLine();

            // Obtenemos la dirección del servidor
            InetAddress IPServidor = InetAddress.getByName(servidorIP);

            byte[] b = lineaLeida.getBytes(COD_TEXTO);
            DatagramPacket enviarNombre = new DatagramPacket(b, b.length, IPServidor, puerto);

            serverSocket.connect(IPServidor, puerto);
            serverSocket.send(enviarNombre);

            // creamos arry de bytes para la recepción de la respuesta
            byte[] datosRecibidos = new byte[MAX_BYTES];
            DatagramPacket paqueteRecibido = new DatagramPacket(datosRecibidos, datosRecibidos.length);

            System.out.print("NOMBRE ENVIADO. ESPERANDO PAQUETE SERVER");
            serverSocket.receive(paqueteRecibido);
            String respuesta = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength(), COD_TEXTO);

            System.out.printf("Recibido datagrama de %s:%d %s\n", paqueteRecibido.getAddress().getHostAddress(),
                    paqueteRecibido.getPort(), respuesta);

        } catch (SocketException ex) {
            System.out.println("Excepción de sockets");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Excepción de E/S");
            ex.printStackTrace();
        }

    }
}
