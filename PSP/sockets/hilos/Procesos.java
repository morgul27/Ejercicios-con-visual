package hilos;

import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Random;

public class Procesos extends Thread {

    private Socket socket;
    private OutputStream os;
    private InputStream is;

    public Procesos(Socket socket, InputStream is) {
        this.socket = socket;
        this.is = is;
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
        Maquina maquina = new Maquina();
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
            maquina.venderRefresco(bebidaCliente);
            Map<String, Integer> inventarioActualizado = maquina.getBebidasDisponibles();
            maquina.actualizarInventario(inventarioActualizado);
            os.write(("\n").getBytes());
            os.write(("Las bebidas de la maquina ahora son:\n").getBytes());
            enviarInventario(inventarioActualizado);
        } else {
            os.write(("No hay la bebida que pides\n").getBytes());
        }

        System.out.println(maquina.bebidaValida(bebidaCliente));

        // out.writeObject(disponiblesConExistencias);

        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    public void enviarInventario(Map<String, Integer> inventarioActualizado) throws IOException {
        for (Map.Entry<String, Integer> bebida : inventarioActualizado.entrySet()) {
            String lista = "Bebida: " + bebida.getKey() + ", Cantidad: " + bebida.getValue() + ". ";
            os.write(lista.getBytes());
        }
    }
}