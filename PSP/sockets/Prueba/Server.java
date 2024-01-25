package Prueba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        int puerto = 49873;

        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor esperando conexiones en el puerto " + puerto);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    String comandoCliente;
                    while ((comandoCliente = in.readLine()) != null) {
                        String respuesta = procesarComandoCliente(comandoCliente);
                        out.println(respuesta);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Conexión cerrada");
        }
    }

    private static String procesarComandoCliente(String comandoCliente) {
        String respuesta = "";

        if ("mem".equals(comandoCliente)) {
            respuesta = obtenerMemoriaDisponible();
        } else if ("hd".equals(comandoCliente)) {
            respuesta = obtenerEspacioLibreEnDisco();
        }

        return respuesta;
    }

    private static String obtenerMemoriaDisponible() {
        Runtime runtime = Runtime.getRuntime();
        long memoriaTotal = runtime.totalMemory() / (1024 * 1024);
        return "Memoria total del sistema: " + memoriaTotal + " MB";
    }

    private static String obtenerEspacioLibreEnDisco() {
        return obtenerEspacioEnDisco();
    }

    private static String obtenerEspacioEnDisco() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "wmic logicaldisk get FreeSpace");
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder salida = new StringBuilder();
            String linea;
            boolean headerLeido = false;

            while ((linea = reader.readLine()) != null) {
                if (!headerLeido) {
                    headerLeido = true;
                    continue;
                }

                try {
                    long espacioLibre = Long.parseLong(linea.trim());
                    salida.append("Espacio libre en disco: ").append(espacioLibre).append(" bytes\n");
                } catch (NumberFormatException e) {
                }
            }

            return salida.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al obtener el espacio libre en disco.";
        }
    }
}
