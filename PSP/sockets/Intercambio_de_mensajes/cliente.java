package Intercambio_de_mensajes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class cliente {

    public static void main(String[] args) {
        final String DIRECCION_SERVIDOR = "localhost";
        final int PUERTO = 12345;

        try (Socket socket = new Socket(DIRECCION_SERVIDOR, PUERTO)) {
            System.out.println("1 (Cliente) Estableciendo conexión..");

            // Abriendo canales de texto
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("4 (Cliente) Canales de texto abiertos.");

            // Enviando mensaje
            System.out.println("5 (Cliente) Enviando mensaje...");
            writer.println("Mensaje enviado desde el cliente");
            System.out.println("6 (Cliente) Mensaje enviado.");

            // Leyendo mensaje
            System.out.println("7 (Cliente) Leyendo mensaje...");
            String mensajeRecibido = reader.readLine();
            System.out.println("8 (Cliente) Mensaje leído. Mensaje recibido: " + mensajeRecibido);

            // Cerrando canales de texto
            System.out.println("10 (Cliente) Cerrando canales de texto.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("11 (Cliente) Canales de texto cerrados.");
        System.out.println("12 (Cliente) Cerrando conexiones...");
        System.out.println("13 (Cliente) Conexiones cerradas");
    }
}
