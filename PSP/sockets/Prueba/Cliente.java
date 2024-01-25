package Prueba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    private static boolean isValidCommand(String command) {
        return command.equalsIgnoreCase("mem") || command.equalsIgnoreCase("hd") || command.equalsIgnoreCase("salir");
    }

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int portNumber = 49873;

        try (Socket socket = new Socket(serverAddress, portNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Conectado con el servidor");

            String comandos;
            do {
                System.out.print("Ingresa las palabras mem, hd o salir: ");
                comandos = userInput.readLine();

                if (isValidCommand(comandos)) {
                    if (comandos.equalsIgnoreCase("salir")) {
                        break;
                    }
                    out.println(comandos);

                    String respuesta = in.readLine();
                    System.out.println("Respuesta del servidor: " + respuesta);
                } else {
                    System.out.println("Comando no válido. Por favor, ingresa uno de los comandos permitidos.");
                }

            } while (!comandos.equalsIgnoreCase("salir"));
            System.out.print("Desconectando y cerrando servidor");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
