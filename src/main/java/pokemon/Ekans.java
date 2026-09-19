package pokemon;

public class Ekans extends Pokemon {
    private static final long serialVersionUID = 1L;

    public Ekans() {
        this(5);
    }

    public Ekans(int nivel) {
        super("Ekans",
                23,
                nivel,
                30,
                40,
                30,
                40,
                new Movimiento[]{
                        new Placaje(),
                        new PicotazoVenenoso(),
                        new Mordisco()
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
