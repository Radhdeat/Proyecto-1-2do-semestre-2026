package pokemon;

public class Fearow extends Pokemon {
    private static final long serialVersionUID = 1L;

    public Fearow() {
        this(5);
    }

    public Fearow(int nivel) {
        super("Fearow",
                22,
                nivel,
                40,
                60,
                40,
                60,
                new Movimiento[]{
                        new AtaqueAla(),
                        new Picotazo(),
                        new DobleFilo()
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