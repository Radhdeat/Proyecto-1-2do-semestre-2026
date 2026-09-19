package pokemon;

public class Wartortle extends Pokemon {
    private static final long serialVersionUID = 1L;

    // Constructor por defecto (Nivel 5)
    public Wartortle() {
        this(5);
    }

    // Constructor especificando nivel
    public Wartortle(int nivelInicial) {
        super(
                "Inysaur",
                8,
                nivelInicial,
                40,
                40,
                50,
                40,
                new Movimiento[]{
                        new Mordisco(),
                        new Proteccion(),
                        new RayoSolar()
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
