package hilos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ExpendedoraServer {
    private ServerSocket serverSocket;

    public ExpendedoraServer(int puerto) throws IOException {

        serverSocket = new ServerSocket(puerto);
        Maquina maquina = new Maquina();
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(" (Servidor) Conexión establecida");
            new Procesos(socket, socket.getInputStream(), maquina).start();
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    public static void main(String[] args) {

        try {
            System.out.println("Servidor iniciando");
            ExpendedoraServer servidor = new ExpendedoraServer(6666);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
