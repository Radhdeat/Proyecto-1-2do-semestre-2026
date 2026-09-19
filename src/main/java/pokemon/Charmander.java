package pokemon;

public class Charmander extends Pokemon {
    private static final long serialVersionUID = 1L;

    public Charmander() {
        this(5);
    }

    public Charmander(int nivel) {
        super("Charmander",
                4,
                nivel,
                30,
                40,
                30,
                40,
                new Movimiento[]{
                        new Placaje(),
                        new Gruñido(),
                        new GiroFuego()
                }
        );
    }

    @Override
    public void atacar(Pokemon oponente) {
        if (getMovimientos()[0] != null) {
            getMovimientos()[0].ejecutar(this, oponente);
        }
    }
}