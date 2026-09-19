package pokemon;

public class Butterfree extends Pokemon {
    private static final long serialVersionUID = 1L;

    public Butterfree() {
        this(5);
    }

    public Butterfree(int nivel) {
        super("Butterfree",
                12,
                nivel,
                40,
                30,
                30,
                50,
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
