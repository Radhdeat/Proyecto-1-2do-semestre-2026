package pokemon;

public class GiroFuego extends MovimientoFisico {
    protected GiroFuego() { super("Giro Fuego", 35, "Un aro de fuego atrapa y daña al objetivo."); }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        super.ejecutar(atacante, defensor);
        System.out.println("¡" + defensor.getApodo() + " ha quedado atrapado en el aro de fuego!");
    }
}