package pokemon;

public class Beedrill extends Pokemon {
    private static final long serialVersionUID = 1L;

    public Beedrill() {
        this(5);
    }

    public Beedrill(int nivel) {
        super("Beedrill",
                15,
                nivel,
                40,
                60,
                30,
                50,
                new Movimiento[]{
                        new AtaqueAla(),
                        new Fortaleza(),
                        new Picotazo()
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

