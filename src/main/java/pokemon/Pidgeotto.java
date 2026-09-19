package pokemon;

public class Pidgeotto extends Pokemon {
    private static final long serialVersionUID = 1L;

    // Constructor por defecto (Nivel 5)
    public Pidgeotto() {
        this(5);
    }

    // Constructor especificando nivel
    public Pidgeotto(int nivelInicial) {
        super(
                "Pigdeotto",
                17,
                nivelInicial,
                40,
                40,
                40,
                50,
                new Movimiento[]{
                        new Placaje(),
                        new Gruñido(),
                        new AtaqueAla()
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