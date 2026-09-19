package pokemon;

public class Pidgey extends Pokemon {
    private static final long serialVersionUID = 1L;

    // Constructor por defecto (Nivel 5)
    public Pidgey() {
        this(5);
    }

    // Constructor especificando nivel
    public Pidgey(int nivelInicial) {
        super(
                "Pidgey0",
                16,
                nivelInicial,
                30,
                30,
                30,
                40,
                new Movimiento[]{
                        new Placaje(),
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
