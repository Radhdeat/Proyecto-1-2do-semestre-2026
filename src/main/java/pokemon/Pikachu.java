package pokemon;

public class Pikachu extends Pokemon {
    private static final long serialVersionUID = 1L;

    // Constructor por defecto (Nivel 5)
    public Pikachu() {
        this(5);
    }

    // Constructor especificando nivel
    public Pikachu(int nivelInicial) {
        super(
                "Pikachu",
                25,
                nivelInicial,
                30,
                40,
                63,
                60,
                new Movimiento[]{
                        new Impactrueno(),
                        new Atactrueno(),
                        new Rayo()
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
