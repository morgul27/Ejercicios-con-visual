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
    OutputStream os;

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
        Scanner sc = new Scanner(System.in);

        try {
            cliente.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(cliente.is));
            System.out.println(reader.readLine()); // lista
            System.out.println(reader.readLine()); // las bebidas
            System.out.println(reader.readLine()); // Pregunta
            String bebidaCliente = sc.nextLine();
            cliente.os.write((bebidaCliente + "\n").getBytes());
            
            // miro si hay o no la bebida que ha pedido
            int a = cliente.is.read();
            boolean booleano = (a != 1); // en el caso de que sea correcto sera falso este booleano
            if (booleano == true) {
                System.out.println(reader.readLine()); //recibe el mensaje de que el programa se va a cerrar
                System.exit(0); // aqui cierra el programa
            }

            // recibir mensaje cantidad
            System.out.println(reader.readLine());
            // enviar cantidad
            int cantidadBebida = sc.nextInt();
            cliente.os.write(cantidadBebida);

            System.out.println(reader.readLine()); // ver lo que ha comprado el cliente
            System.out.println(reader.readLine()); // ahora hay
            System.out.println(reader.readLine()); // lista

            cliente.is.close();
            cliente.os.close();
            cliente.stop();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}