package pokemon;

public class Caterpie extends Pokemon {
    private static final long serialVersionUID = 1L;

    public Caterpie() {
        this(5);
    }

    public Caterpie(int nivel) {
        super("Cartepie",
                10,
                nivel,
                30,
                20,
                30,
                30,
                new Movimiento[]{
                        new Placaje(),
                        new DisparoDemora()
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
