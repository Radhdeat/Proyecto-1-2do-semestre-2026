package pokemon;

public abstract class MovimientoEstado extends Movimiento {
    private static final long serialVersionUID = 1L;

    // constructor
    protected MovimientoEstado(String nombre, String descripcion) {
        super(nombre, 0, descripcion);
    }
}
