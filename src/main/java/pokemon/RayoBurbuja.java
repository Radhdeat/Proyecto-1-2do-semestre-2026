package pokemon;

public class RayoBurbuja extends MovimientoFisico {
    protected RayoBurbuja() { super("Rayo burbuja", 70, "Ráfaga de burbujas que reduce la velocidad del oponente en un 5%."); }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        super.ejecutar(atacante, defensor);
        defensor.modificarVelocidad(0.95);
        System.out.println("¡La velocidad de " + defensor.getApodo() + " disminuyó un 5%!");
    }
}