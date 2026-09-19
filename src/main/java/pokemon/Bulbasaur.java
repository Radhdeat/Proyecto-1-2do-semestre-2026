package pokemon;

public class Bulbasaur extends Pokemon {
    private static final long serialVersionUID = 1L;

    public Bulbasaur() {
        this(5);
    }

    public Bulbasaur(int nivel) {
        super("Bulbasaur",
                1,
                nivel,
                30,
                30,
                30,
                30,
                new Movimiento[]{
                    new Placaje(),
                    new Latigo(),
                    new Gruñido()
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