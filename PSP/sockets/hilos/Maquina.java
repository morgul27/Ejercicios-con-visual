package hilos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Maquina {
    private Map<String, Integer> inventario;

    public Maquina() {
        inventario = new HashMap<>();
        inventario.put("Aquarius", 5);
        inventario.put("Nestea", 5);
        inventario.put("Coca Cola", 5);
        inventario.put("Fanta", 5);
        inventario.put("Sprite", 5);
    }

    public Map<String, Integer> getBebidasDisponibles() {
        Map<String, Integer> disponibles = new HashMap<>();
        for (Map.Entry<String, Integer> bebidas : inventario.entrySet()) {
            if (bebidas.getValue() > 0) {
                disponibles.put(bebidas.getKey(), bebidas.getValue());
            }
        }
        return disponibles;
    }

    public boolean bebidaValida(String nombre) {
        return inventario.containsKey(nombre);
    }

    public Refresco venderRefresco(String nombre, int cantidad) {
        if (inventario.containsKey(nombre)) {
            int existencias = inventario.get(nombre);
            if (existencias >= cantidad) {
                inventario.put(nombre, existencias - cantidad);
                return new Refresco(nombre);
            }
        }
        return null;
    }

    public void actualizarInventario(Map<String, Integer> nuevoInventario) {
        this.inventario = nuevoInventario;
    }
}