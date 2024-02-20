import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;

public class ClienteMultihilo{  
     private String serverIP;
     private int serverPort;
     private Socket socket;
     private InputStream is;

     public ClienteMultihilo(String serverIP, int serverPort) {
    	 this.serverIP = serverIP;
    	 this.serverPort = serverPort;
     }
     public void start() throws UnknownHostException,IOException {
    	 System.out.println("(Cliente) Estableciendo conexión...");
    	 socket = new Socket (serverIP, serverPort);
    	 is = socket.getInputStream();
    	 System.out.println("(Cliente) Conexión establecida.");
     }
     public void stop() throws IOException {
    	 System.out.println("(Cliente) Cerrando conexiones...");
    	 is.close();
    	 socket.close();
    	 System.out.println("(Cliente) Conexiones cerradas.");
     }
     public static void main(String[] args) {
    	 ClienteMultihilo cliente = new ClienteMultihilo ("localhost",6666);

         try {
        	 cliente.start();
        	 System.out.println("Mensaje del servidor:" + cliente.is.read());
        	 cliente.stop();
         }catch (UnknownHostException e) {
                  e.printStackTrace();
         } catch (IOException e) {

               e.printStackTrace();
         }
     }   
}