package pokemon;

public class Metapod extends Pokemon {
    private static final long serialVersionUID = 1L;

    // Constructor por defecto (Nivel 5)
    private Metapod() {
        this(5);
    }

    // Constructor especificando nivel
    public Metapod(int nivelInicial) {
        super(
                "Metapod",
                11,
                nivelInicial,
                30,
                20,
                40,
                20,
                new Movimiento[]{new Fortaleza()}
        );
    }

    @Override
    public void atacar(Pokemon oponente) {
        if (getMovimientos()[0] != null) {
            getMovimientos()[0].ejecutar(this, oponente);
        }
    }
}