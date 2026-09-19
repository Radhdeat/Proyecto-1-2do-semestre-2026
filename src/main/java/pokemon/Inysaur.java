package pokemon;

public class Inysaur extends Pokemon {
    private static final long serialVersionUID = 1L;

    // Constructor por defecto (Nivel 5)
    protected Inysaur() {
        this(5);
    }

    // Constructor especificando nivel
    public Inysaur(int nivelInicial) {
        super(
                "Inysaur",
                2,
                nivelInicial,
                40,
                40,
                40,
                40,
                new Movimiento[]{
                        new Descanso(),
                        new Drenadoras(),
                        new HojaAfilada()
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
