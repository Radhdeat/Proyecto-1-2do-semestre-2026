package pokemon;

public class Vuelo extends MovimientoFisico {
    private boolean enAire = false;

    protected Vuelo() { super("Vuelo", 90, "En el primer turno vuela e invulnerable, en el segundo ataca."); }

    @Override
    public void ejecutar(Pokemon atacante, Pokemon defensor) {
        if (!enAire) {
            enAire = true;
            atacante.setProtegido(true);
            System.out.println("¡" + atacante.getApodo() + " voló alto en el aire y no puede ser atacado!");
        } else {
            enAire = false;
            atacante.setProtegido(false);
            super.ejecutar(atacante, defensor);
        }
    }
}