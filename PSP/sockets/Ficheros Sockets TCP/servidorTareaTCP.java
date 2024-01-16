import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class servidorTareaTCP {

    public static void main(String[] args) {
        final int PUERTO = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Cliente conectado desde " + socket.getInetAddress());

                    BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\JoséMiguelNavarroDeA\\Desktop\\trabajos\\Ejercicios con visual\\PSP\\sockets\\fichero.txt"));
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        writer.println(linea);
                    }

                    System.out.println("Contenido enviado al cliente.");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
