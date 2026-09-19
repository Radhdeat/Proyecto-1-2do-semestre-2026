package pokemon;

public class Venasaur extends Pokemon {
    private static final long serialVersionUID = 1L;

    // Constructor por defecto (Nivel 5)
    public Venasaur() {
        this(5);
    }

    // Constructor especificando nivel
    public Venasaur(int nivelInicial) {
        super(
                "Venasaur",
                3,
                nivelInicial,
                50,
                50,
                50,
                50,
                new Movimiento[]{
                        new Drenadoras(),
                        new RayoSolar(),
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