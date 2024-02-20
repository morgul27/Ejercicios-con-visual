package hilos;

import java.io.Serializable;

public class Refresco implements Serializable {
    private String nombre;

    public Refresco(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
