package pokemon;

public class Charmeleon extends Pokemon {
    private static final long serialVersionUID = 1L;

    public Charmeleon() {
        this(5);
    }

    public Charmeleon(int nivel) {
        super("Charmeleon",
                5,
                nivel,
                40,
                40,
                40,
                50,
                new Movimiento[]{
                        new DobleFilo(),
                        new GiroFuego(),
                        new LanzaLlamas()
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
