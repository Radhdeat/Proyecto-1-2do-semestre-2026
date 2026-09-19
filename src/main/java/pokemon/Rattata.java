package pokemon;

public class Rattata extends Pokemon {
    private static final long serialVersionUID = 1L;

    // Constructor por defecto (Nivel 5)
    public Rattata() {
        this(5);
    }

    // Constructor especificando nivel
    public Rattata(int nivelInicial) {
        super(
                "Rattata",
                19,
                nivelInicial,
                20,
                40,
                30,
                50,
                new Movimiento[]{
                        new Placaje(),
                        new AtaqueRapido(),
                        new Latigo()
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
