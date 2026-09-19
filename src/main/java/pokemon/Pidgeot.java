package pokemon;

public class Pidgeot extends Pokemon {
    private static final long serialVersionUID = 1L;

    // Constructor por defecto (Nivel 5)
    public Pidgeot() {
        this(5);
    }

    // Constructor especificando nivel
    public Pidgeot(int nivelInicial) {
        super(
                "Pidgeot",
                17,
                nivelInicial,
                50,
                50,
                50,
                60,
                new Movimiento[]{
                        new Vuelo(),
                        new AtaqueAla(),
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