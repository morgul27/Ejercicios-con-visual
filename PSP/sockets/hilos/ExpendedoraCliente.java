package hilos;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;

public class ExpendedoraCliente {
    private String serverIP;
    private int serverPort;
    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public ExpendedoraCliente(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public void start() throws UnknownHostException, IOException {
        System.out.println("(Cliente) Estableciendo conexión...");
        socket = new Socket(serverIP, serverPort);
        is = socket.getInputStream();
        os = socket.getOutputStream();
        System.out.println("(Cliente) Conexión establecida.");
        System.out.println("");
    }

    public void stop() throws IOException {
        System.out.println("");
        System.out.println("(Cliente) Cerrando conexiones...");
        is.close();
        os.close();
        socket.close();
        System.out.println("(Cliente) Conexiones cerradas.");
    }

    public static void main(String[] args) {
        ExpendedoraCliente cliente = new ExpendedoraCliente("localhost", 6666);
        boolean pedidoValido = false;
        Scanner sc = new Scanner(System.in);

        try {
            cliente.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(cliente.is));
            System.out.println(reader.readLine()); // lista
            System.out.println(reader.readLine()); // las bebidas
            System.out.println(reader.readLine()); // Pregunta
            String bebidaCliente = sc.nextLine();
            cliente.os.write((bebidaCliente + "\n").getBytes());
            System.out.println(reader.readLine()); // ahora hay
            System.out.println(reader.readLine()); // lista
            System.out.println(reader.readLine()); 

            cliente.stop();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        while (!pedidoValido) {
        }
    }
}