package pokemon;

public class Squirtle extends Pokemon {
    private static final long serialVersionUID = 1L;

    // Constructor por defecto (Nivel 5)
    public Squirtle() {
        this(5);
    }

    // Constructor especificando nivel
    public Squirtle(int nivelInicial) {
        super(
                "Squirtle",
                7,
                nivelInicial,
                30,
                30,
                40,
                30,
                new Movimiento[]{
                        new Placaje(),
                        new Latigo(),
                        new RayoBurbuja()
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