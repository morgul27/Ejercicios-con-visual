import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class clienteTareaTCP {

    public static void main(String[] args) {
        final String DIRECCION_SERVIDOR = "localhost";
        final int PUERTO = 12345;

        try (Socket socket = new Socket(DIRECCION_SERVIDOR, PUERTO);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Conectado al servidor. Ingrese la ruta completa del fichero:");

            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
            String rutaFichero = userInputReader.readLine();

            // Enviar la ruta al servidor
            socket.getOutputStream().write(rutaFichero.getBytes());

            // Recibir y mostrar el contenido del fichero
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}