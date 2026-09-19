package pokemon;

public class Arbok extends Pokemon {
    private static final long serialVersionUID = 1L;

    // Constructor por defecto (Nivel 5)
    public Arbok() {
        this(5);
    }

    // Constructor especificando nivel
    public Arbok(int nivelInicial) {
        super(
                "Arbok",
                24,
                nivelInicial,
                40,
                60,
                50,
                50,
                new Movimiento[]{
                        new PicotazoVenenoso(),
                        new Mordisco()
                }
        );
    }

    @Override
    public void atacar(Pokemon oponente) {
        if (oponente != null && oponente.getSaludActual() > 0) {
            if (getMovimientos()[0] != null) {
                getMovimientos()[0].ejecutar(this, oponente);
            }
        }
    }
}
