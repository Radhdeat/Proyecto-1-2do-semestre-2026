package pokemon;

public class Fortaleza extends MovimientoEstado {
    private static final long serialVersionUID = 1L;

    protected Fortaleza() {
        super("Fortaleza", "Tensa el cuerpo para aumentar la defensa propia un 20%.");
    }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        atacante.modificarDefensa(1.20);
        System.out.println(atacante.getApodo() + " usó Fortaleza. ¡Su defensa aumentó un 20%!");
    }
}