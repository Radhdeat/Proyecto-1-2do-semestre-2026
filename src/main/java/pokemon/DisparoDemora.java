package pokemon;

public class DisparoDemora extends MovimientoEstado {
    private static final long serialVersionUID = 1L;

    protected DisparoDemora() {
        super("Disparo demora", "Lanza seda reduciendo la velocidad del oponente un 25%.");
    }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        defensor.modificarVelocidad(0.75);
        System.out.println(atacante.getApodo() + " usó Disparo demora. ¡La velocidad de " + defensor.getApodo() + " bajó un 25%!");
    }
}