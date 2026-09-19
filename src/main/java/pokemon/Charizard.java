package pokemon;

public class Charizard extends Pokemon {
    private static final long serialVersionUID = 1L;

    public Charizard() {
        this(5);
    }

    public Charizard(int nivel) {
        super("Charizard",
                6,
                nivel,
                50,
                50,
                50,
                60,
                new Movimiento[]{
                        new GiroFuego(),
                        new LanzaLlamas(),
                        new AnilloIgneo()
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
