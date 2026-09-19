package pokemon;

public class Spearow extends Pokemon {
    private static final long serialVersionUID = 1L;

    // Constructor por defecto (Nivel 5)
    public Spearow() {
        this(5);
    }

    // Constructor especificando nivel
    public Spearow(int nivelInicial) {
        super(
                "Spearow",
                21,
                nivelInicial,
                30,
                40,
                20,
                50,
                new Movimiento[]{
                        new Placaje(),
                        new Gruñido(),
                        new AtaqueFuria()
                }
        );
    }

    @Override
    public void atacar(Pokemon oponente) {
        if (oponente != null && oponente.getSaludActual() > 0) {
            System.out.println(getApodo() + " usó Hoja Afilada contra " + oponente.getApodo());

        }
    }
}
