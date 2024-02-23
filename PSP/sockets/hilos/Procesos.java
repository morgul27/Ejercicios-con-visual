package hilos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public class Procesos extends Thread {

    private Socket socket;
    private OutputStream os;
    private InputStream is;
    protected Maquina maquina;

    public Procesos(Socket socket, InputStream is, Maquina maquina) {
        this.socket = socket;
        this.is = is;
        this.maquina = maquina;
    }

    @Override
    public void run() {
        try {
            realizarProceso();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void realizarProceso() throws IOException {

        os = this.socket.getOutputStream();
        Map<String, Integer> inventario;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        // inventario de la maquina
        inventario = maquina.getBebidasDisponibles();

        os.write(("La lista de las bebidas es: \n").getBytes());
        enviarInventario(inventario);
        os.write("\n".getBytes());
        os.write(("De todas estas bebidas ¿Cual desea?\n").getBytes());
        String bebidaCliente = reader.readLine();
        System.out.println(bebidaCliente);

        // comprobar las bebidas
        if (maquina.bebidaValida(bebidaCliente)) {
            os.write(1);
            os.write(("Ahora dime cuantas bebidas quieres beber:\n").getBytes());
            int cantidadBebida = is.read();
            System.out.println("el cliente quiere " + cantidadBebida + " de " + bebidaCliente);

            Refresco a;
            a = maquina.venderRefresco(bebidaCliente, cantidadBebida);
            if (a == null) {
                os.write(("no hay suficientes bebidas disponibles \n").getBytes());
            } else {
                os.write(("Se te ha entregado " + cantidadBebida + " " + bebidaCliente + "\n").getBytes());
            }
            Map<String, Integer> inventarioActualizado = maquina.getBebidasDisponibles();
            maquina.actualizarInventario(inventarioActualizado);

            os.write(("Las bebidas de la maquina ahora son:\n").getBytes());
            enviarInventario(inventarioActualizado);
        } else {
            os.write((byte) 0);
            os.write(("No hay de la bebida que pides, por favor, reinicia el programa\n").getBytes());

        }

        os.close();
        is.close();
    }

    public void enviarInventario(Map<String, Integer> inventarioActualizado) throws IOException {
        for (Map.Entry<String, Integer> bebida : inventarioActualizado.entrySet()) {
            String lista = "| Bebida: " + bebida.getKey() + ", Cantidad: " + bebida.getValue() + ". |";
            os.write(lista.getBytes());
        }
    }
}