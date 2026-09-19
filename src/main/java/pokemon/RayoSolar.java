package pokemon;

public class RayoSolar extends MovimientoFisico {
    private boolean cargando = false;

    protected RayoSolar() { super("Rayo solar", 120, "Absorbe luz en el primer turno y ataca en el segundo."); }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        if (!cargando) {
            cargando = true;
            System.out.println("¡" + atacante.getApodo() + " está cargando energía solar!");
        } else {
            cargando = false;
            super.ejecutar(atacante, defensor);
        }
    }
}