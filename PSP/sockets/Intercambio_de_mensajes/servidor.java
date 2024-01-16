package Intercambio_de_mensajes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {

    public static void main(String[] args) {
        final int PUERTO = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("1 (Servidor) Esperando conexiones...");

            try (Socket socket = serverSocket.accept()) {
                System.out.println("2 (Servidor) Conexión establecida.");

                // Abriendo canales de texto
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                System.out.println("4 (Servidor) Canales de texto abiertos.");

                // Leyendo mensaje
                System.out.println("5 (Servidor) Leyendo mensaje...");
                String mensajeRecibido = reader.readLine();
                System.out.println("6 (Servidor) Mensaje leído.");

                // Enviando mensaje
                System.out.println("7 (Servidor) Enviando mensaje...");
                writer.println("Mensaje recibido: " + mensajeRecibido);
                System.out.println("8 (Servidor) Mensaje enviado: " + mensajeRecibido);

                // Cerrando canales de texto
                System.out.println("10 (Servidor) Cerrando canales de texto.");
            }

            System.out.println("11 (Servidor) Canales de texto cerrados.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("12 (Servidor) Cerrando conexiones...");
        System.out.println("13 (Servidor) Conexiones cerradas.");
    }
}
