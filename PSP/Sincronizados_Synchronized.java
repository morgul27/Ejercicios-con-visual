package PSP;

public class Sincronizados_Synchronized extends Thread {
    protected static int c = 0;

    public static void contador() {
        c++; // contador total
    }

    public static class Hilo extends Thread {
        public int id;

        public Hilo(int id) {
            this.id = id; // para saber que hilos es
        }

        public void run() {
            for (int i = 0; i < 10000; i++) {
                contador();
            }
        }
    }

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 10; i++) {
                Hilo hilo = new Hilo(i);
                hilo.start(); // aqui empieza el run de hilos
                Thread.sleep(50);
                System.out.println("Hilo " + i + " terminado, empieza a contar hasta " + 10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Resultado de la cuenta: " + c);

    }
}