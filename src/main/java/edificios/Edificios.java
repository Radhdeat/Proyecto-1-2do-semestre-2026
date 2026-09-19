package edificios;

import java.io.Serializable;

public abstract class Edificios implements Serializable {
    private static final long serialVersionUID = 1L;

    // atributo
    protected String nombre;

    // constructor
    protected Edificios(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}