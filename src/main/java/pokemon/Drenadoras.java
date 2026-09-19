package pokemon;

public class Drenadoras extends MovimientoEstado {
    private static final long serialVersionUID = 1L;

    protected Drenadoras() {
        super("Drenadoras", "Planta semillas que drenan el 7% de salud del oponente cada turno.");
    }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        if (!defensor.tieneDrenadoras()) {
            defensor.setTieneDrenadoras(true);
            System.out.println(atacante.getApodo() + " usó Drenadoras. ¡" + defensor.getApodo() + " ha sido infectado por las semillas!");
        } else {
            System.out.println("¡" + defensor.getApodo() + " ya estaba infectado por Drenadoras!");
        }
    }
}