package pokemon;

public class Kakuna extends Pokemon {
    private static final long serialVersionUID = 1L;

    // Constructor por defecto (Nivel 5)
    public Kakuna() {
        this(5);
    }

    // Constructor especificando nivel
    public Kakuna(int nivelInicial) {
        super(
                "Kakuna",
                14,
                nivelInicial,
                30,
                20,
                30,
                30,

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