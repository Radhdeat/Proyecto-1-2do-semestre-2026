package pokemon;

import java.io.Serializable;

public abstract class Movimiento implements Serializable {
    private static final long serialVersionUID = 1L;

    // atributos
    private String nombre;
    private int potencia;
    private String descripcion;

    // constructor
    public Movimiento(String nombre, int potencia, String descripcion) {
        this.nombre = nombre;
        this.potencia = potencia;
        this.descripcion = descripcion;
    }

    public abstract void ejecutar(Pokemon atacante, Pokemon defensor);

    //geters
    public String getNombre() { return nombre; }
    public int getPotencia() { return potencia; }
    public String getDescripcion() { return descripcion; }
}