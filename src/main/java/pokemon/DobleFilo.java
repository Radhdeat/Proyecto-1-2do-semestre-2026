package pokemon;

public class DobleFilo extends MovimientoFisico {
    protected DobleFilo() { super("Doble filo", 100, "Hiere al usuario con un 20% del daño infligido."); }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        int daño = calcularDaño(atacante, defensor);
        defensor.recibirDano(daño);
        int retroceso = (int) (daño * 0.20);
        atacante.recibirDano(retroceso);
        System.out.println(atacante.getApodo() + " usó Doble filo causando " + daño + " de daño, pero recibió " + retroceso + " HP de daño por retroceso!");
    }
}