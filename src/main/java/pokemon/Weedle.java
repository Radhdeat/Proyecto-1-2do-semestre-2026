package pokemon;

public class Weedle extends Pokemon {
    private static final long serialVersionUID = 1L;

    // Constructor por defecto (Nivel 5)
    public Weedle() {
        this(5);
    }

    // Constructor especificando nivel
    public Weedle(int nivelInicial) {
        super(
                "Inysaur",
                13,
                nivelInicial,
                30,
                30,
                20,
                30,
                new Movimiento[]{
                        new PicotazoVenenoso(),
                        new DisparoDemora()
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