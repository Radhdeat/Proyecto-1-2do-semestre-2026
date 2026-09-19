package jugador;

import java.io.Serializable;
import objetos.Objeto;

public class Mochila implements Serializable {
    private static final long serialVersionUID = 1L;

    // atributos
    private Objeto[] items;
    private int cantidadDiferentes;

    // constructor
    protected Mochila() {
        // Capacidad para hasta 10 tipos de objetos distintos
        this.items = new Objeto[10];
        this.cantidadDiferentes = 0;
    }

    //
    public void agregarObjeto(Objeto nuevoObjeto, int cantidad) {
        // Verifica si el objeto ya existe en la mochila para acumularlo
        for (int i = 0; i < cantidadDiferentes; i++) {
            if (items[i].getNombre().equalsIgnoreCase(nuevoObjeto.getNombre())) {
                items[i].agregarCantidad(cantidad);
                return;
            }
        }

        // Si es un tipo nuevo, colocarlo en el siguiente espacio libre
        if (cantidadDiferentes < items.length) {
            items[cantidadDiferentes] = nuevoObjeto;
            cantidadDiferentes++;
        } else {
            System.out.println("La mochila no tiene más espacio para nuevos tipos de objetos.");
        }
    }

    public void mostrarMochila() {
        System.out.println("-------------------------------------------------");
        System.out.println("               MOCHILA DEL JUGADOR               ");
        System.out.println("-------------------------------------------------");

        // se muestran las cosas que tiene el jugador
        int visibles = 0;
        for (int i = 0; i < cantidadDiferentes; i++) {
            if (items[i].getCantidad() > 0) {
                visibles++;
                System.out.println(visibles + ". " + items[i].getNombre() +
                        " x" + items[i].getCantidad() +
                        " - " + items[i].getDescripcion());
            }
        }

        // en caso de que no tenga nada
        if (visibles == 0) {
            System.out.println("La mochila está vacía.");
        }
        System.out.println("-------------------------------------------------");
    }

    // Obtiene la cantidad actual de un objeto específico
    public int getCantidadObjeto(String nombre) {
        for (int i = 0; i < cantidadDiferentes; i++) {
            if (items[i].getNombre().equalsIgnoreCase(nombre)) {
                return items[i].getCantidad();
            }
        }
        return 0;
    }

    // Resta 1 unidad del objeto indicado
    public boolean consumirObjeto(String nombre) {
        for (int i = 0; i < cantidadDiferentes; i++) {
            if (items[i].getNombre().equalsIgnoreCase(nombre) && items[i].getCantidad() > 0) {
                items[i].agregarCantidad(-1); // Disminuye en 1
                return true;
            }
        }
        return false;
    }

    // cuando se compra algo
    private Objeto obtenerObjeto(int indice) {
        if (indice >= 0 && indice < cantidadDiferentes) {
            return items[indice];
        }
        return null;
    }

    // geter y seter
    protected Objeto[] getItems() { return items; }
    protected int getCantidadDiferentes() { return cantidadDiferentes; }
}