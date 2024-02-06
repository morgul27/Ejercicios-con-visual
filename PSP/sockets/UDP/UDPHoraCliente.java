import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class UDPHoraCliente {
    private final static int MAX_BYTES = 1400;
    private final static String COD_TEXTO = "UTF-8";
    private final static String servidorIP = "localhost";
    private static int puerto = 9999;
    private static int timeout = 5000;

    public static void main(String[] args) {

        try (DatagramSocket serverSocket = new DatagramSocket();
                InputStreamReader isrStdIn = new InputStreamReader(System.in, COD_TEXTO);
                BufferedReader brStdIn = new BufferedReader(isrStdIn)) {

            serverSocket.setSoTimeout(timeout);
            String lineaLeida;
            System.out.println("Introducir palabra para petición al servidor.");
            System.out.print("Linea>");

            while ((lineaLeida = brStdIn.readLine()) != null && lineaLeida.length() > 0) {

                // Obtenemos la dirección del servidor
                InetAddress IPServidor = InetAddress.getByName(servidorIP);
                byte[] b = lineaLeida.getBytes(COD_TEXTO);
                DatagramPacket paqueteEnviado = new DatagramPacket(b, b.length, IPServidor, puerto);

                serverSocket.connect(IPServidor, puerto);
                serverSocket.send(paqueteEnviado);

                byte[] respuesta = new byte[MAX_BYTES];
                DatagramPacket respuestaPacket = new DatagramPacket(respuesta, respuesta.length);

                try {
                    serverSocket.receive(respuestaPacket);
                    String fecha = new String(respuestaPacket.getData(), 0, respuestaPacket.getLength(), COD_TEXTO);
                    System.out.println("El dia y la hora de hoy es: " + fecha);
                } catch (SocketTimeoutException e) {
                    System.out.println("Error: Tiempo de espera agotado. No se recibió respuesta del servidor.");
                }
                System.out.println(
                        "Si desea ver otra vez la fecha envia un caracter, si desea terminar envia el mensaje vacio");
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
