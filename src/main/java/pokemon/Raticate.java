package pokemon;

public class Raticate extends Pokemon {
    private static final long serialVersionUID = 1L;

    // Constructor por defecto (Nivel 5)
    public Raticate() {
        this(5);
    }

    // Constructor especificando nivel
    public Raticate(int nivelInicial) {
        super(
                "Raticate",
                20,
                nivelInicial,
                40,
                50,
                40,
                60,
                new Movimiento[]{
                    new Mordisco(),
                    new Descanso()
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
