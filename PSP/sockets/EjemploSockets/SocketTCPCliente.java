package EjemploSockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketTCPCliente {

      private String serverIP;
      private int serverPort;
      private Socket socket;
      private InputStream is;
      private OutputStream os;

      public SocketTCPCliente(String serverIP, int serverPort) {

            this.serverIP = serverIP;
            this.serverPort = serverPort;
      }

      public void start() throws UnknownHostException, IOException {

            System.out.println("(Cliente) Estableciendo conexión...");
            socket = new Socket(serverIP, serverPort);
            os = socket.getOutputStream();
            is = socket.getInputStream();
            System.out.println("(Cliente) Conexión establecida.");
      }

      public void stop() throws IOException {

            System.out.println("(Cliente) Cerrando conexiones...");
            is.close();
            os.close();
            socket.close();
            System.out.println("(Cliente) Conexiones cerradas.");
      }

      public static void main(String[] args) {

            SocketTCPCliente cliente = new SocketTCPCliente("10.88.8.41", 49171);

            try {
                  cliente.start();
                  cliente.os.write(100);
                  System.out.println("Mensaje del servidor:" + cliente.is.read());

            } catch (UnknownHostException e) {

                  e.printStackTrace();

            } catch (IOException e) {
                  e.printStackTrace();

            }

      }

}
