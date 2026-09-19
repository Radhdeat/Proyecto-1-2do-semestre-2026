package objetos;

import pokemon.Pokemon;

import java.io.Serializable;

public abstract class Objeto implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String nombre;
    protected int precio;
    protected String descripcion;
    protected int cantidad;

    protected Objeto(String nombre, int precio, String descripcion, int cantidadInicial) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.cantidad = cantidadInicial;
    }

    public void agregarCantidad(int cant) {
        this.cantidad += cant;
    }

    private boolean reducirCantidad(int cant) {
        if (this.cantidad >= cant) {
            this.cantidad -= cant;
            return true;
        }
        return false;
    }

    public String getNombre() { return nombre; }
    protected int getPrecio() { return precio; }
    public String getDescripcion() { return descripcion; }
    public int getCantidad() { return cantidad; }

    protected abstract boolean aplicarEfecto(Pokemon objetivo);
}