package pokemon;

public class Latigo extends MovimientoEstado {
    private static final long serialVersionUID = 1L;

    protected Latigo() {
        super("Látigo", "Agita la cola reduciendo la defensa del oponente en un 20%.");
    }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        defensor.modificarDefensa(0.80);
        System.out.println(atacante.getApodo() + " usó Látigo. ¡La defensa de " + defensor.getApodo() + " bajó un 20%!");
    }
}