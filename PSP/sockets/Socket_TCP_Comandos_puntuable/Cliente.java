package Socket_TCP_Comandos_puntuable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    private String serverIP;
    private int serverPort;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
	private DataInputStream dis;
	private DataOutputStream dos;

    public Cliente(String serverIP, int serverPort) {

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

        Cliente cliente = new Cliente("localhost", 49171);

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("escribe");
            String x = sc.nextLine();

            cliente.start();
            cliente.dos.writeBytes(x);
            System.out.println("Mensaje del servidor:" + cliente.is.read());

        } catch (UnknownHostException e) {

            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

}