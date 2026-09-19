package pokemon;

public class Blastoise extends Pokemon {
    private static final long serialVersionUID = 1L;

    public Blastoise() {
        this(5);
    }

    public Blastoise(int nivel) {
        super("Blastoise",
                9,
                nivel,
                50,
                50,
                60,
                50,
                new Movimiento[]{
                        new Mordisco(),
                        new RayoBurbuja(),
                        new HidroCañon()
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

